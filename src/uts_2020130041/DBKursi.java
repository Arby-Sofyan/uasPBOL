/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts_2020130041;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Arby Sofyan
 */
public class DBKursi {
    private Detail_KursiModel dt = new Detail_KursiModel();

    public Detail_KursiModel getDetail_KursiModel() {
        return dt;
    }

    public void setDetail_KursiModel(Detail_KursiModel s) {
        dt = s;
    }

    //LookUP
    /*
    public ObservableList<Detail_KursiModel> LookUp(String fld, String dt) {
        try {
            ObservableList<Detail_KursiModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select NPM, Nama, Alamat from jadwal_film where " + fld + " like '%" + dt + "%'");
            int i = 1;
            while (rs.next()) {
                Detail_KursiModel d = new Detail_KursiModel();
                d.setNPM(rs.getString("NPM"));
                d.setNama(rs.getString("Nama"));
                d.setAlamat(rs.getString("Alamat"));
                tableData.add(d);
                i++;
            }
            con.tutupKoneksi();
            return tableData;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }*/

    
    public ObservableList<Detail_KursiModel> Load() {
        try {
            ObservableList<Detail_KursiModel> TableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();

            ResultSet rs = con.statement.executeQuery(
                    "Select * from detail_kursi ");

            int i = 1;
            while (rs.next()) {
                Detail_KursiModel d = new Detail_KursiModel();
                d.setId_Jadwal(rs.getString("Id_Jadwal"));
                d.setId_Kursi(rs.getString("Id_Kursi"));
                d.setStatus(rs.getString("Status"));
       
                TableData.add(d);
                i++;
            }

            con.tutupKoneksi();
            return TableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int validasi(String nomor) {
        int val = 0;
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from detail_kursi where Id_Jadwal = '" + nomor + "'");
            while (rs.next()) {
                val = rs.getInt("jml");
            }
            con.tutupKoneksi();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return val;
    }

    public boolean insert() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into detail_kursi (Id_Jadwal, Id_Kursi, Status, ) values (?,?,?)");
            con.preparedStatement.setString(1, getDetail_KursiModel().getId_Jadwal());
            con.preparedStatement.setString(2, getDetail_KursiModel().getId_Kursi());
            con.preparedStatement.setString(3, getDetail_KursiModel().getStatus());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }

    public boolean delete(String nomor) {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from detail_kursi where Id_Jadwal  = ? ");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }

    public boolean update() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement(
                    "update detail_kursi set Status = ? where Id_Jadwal = ? and Id_Kursi = ?");
            con.preparedStatement.setString(1, getDetail_KursiModel().getStatus());
            con.preparedStatement.setString(2, getDetail_KursiModel().getId_Jadwal());
            con.preparedStatement.setString(3, getDetail_KursiModel().getId_Kursi());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }
    
    public ArrayList<String> CheckKursi(String nomor){
        try {
            ArrayList<String> List = new ArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select Status from detail_kursi where Id_Jadwal = '" + nomor + "'");
            while (rs.next()) {
                List.add(rs.getString("Status"));
            }
            return List;
        } catch (Exception e) {            
            e.printStackTrace();            
            return null;        
        }
        
        
    }
}
