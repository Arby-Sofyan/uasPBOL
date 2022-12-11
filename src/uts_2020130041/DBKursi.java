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

    private ModelKursi dt = new ModelKursi();
    public ModelKursi getModelKursi() {return dt;}
    public void setModelKursi(ModelKursi s) {dt = s;}

    
    public ObservableList<ModelKursi> Load() {
        try {
            ObservableList<ModelKursi> TableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();

            ResultSet rs = con.statement.executeQuery(
                    "Select * from kursi ");
            int i = 1;
            while (rs.next()) {
                ModelKursi d = new ModelKursi();
                d.setId_kursi(rs.getString("id_kursi"));
                d.setId_jadwal(rs.getString("id_jadwal"));
                d.setId_studio(rs.getString("id_studio"));
                d.setNomor_kursi(rs.getString("nomor_kursi"));
                d.setStatus_kursi(rs.getString("status_kursi"));
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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from kursi where id_kursi = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into kursi (id_kursi, id_jadwal, id_studio, nomor_kursi, status_kursi) values (?,?,?,?,?)");
            con.preparedStatement.setString(1, getModelKursi().getId_kursi());
            con.preparedStatement.setString(2, getModelKursi().getId_jadwal());
            con.preparedStatement.setString(3, getModelKursi().getId_studio());
            con.preparedStatement.setString(4, getModelKursi().getNomor_kursi());
            con.preparedStatement.setString(5, getModelKursi().getStatus_kursi());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from kursi where id_kursi  = ? ");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update kursi set id_jadwal = ?, id_studio = ?, nomor_kursi = ?, status_kursi = ? where id_kursi = ?");
            con.preparedStatement.setString(1, getModelKursi().getId_jadwal());
            con.preparedStatement.setString(2, getModelKursi().getId_studio());
            con.preparedStatement.setString(3, getModelKursi().getNomor_kursi());
            con.preparedStatement.setString(4, getModelKursi().getStatus_kursi());
            con.preparedStatement.setString(5, getModelKursi().getId_kursi());
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
    
    public ObservableList<ModelKursi> LookUp(String fld, String dt) {
        try {
            ObservableList<ModelKursi> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select * from kursi where " + fld + " like '%" + dt + "%'");
            int i = 1;
            while (rs.next()) {
                ModelKursi d = new ModelKursi();
                d.setId_kursi(rs.getString("id_kursi"));
                d.setId_jadwal(rs.getString("id_jadwal"));
                d.setId_studio(rs.getString("id_studio"));
                d.setNomor_kursi(rs.getString("nomor_kursi"));
                d.setStatus_kursi(rs.getString("status_kursi"));
                tableData.add(d);
                i++;
            }
            con.tutupKoneksi();
            return tableData;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<String> ListKursi(String a){
        try {
            ArrayList<String> List = new ArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select status_kursi from kursi where id_jadwal ="+ a);
            while (rs.next()) {
                List.add(rs.getString("status_kursi"));
            }
            return List;
        } catch (Exception e) {            
            e.printStackTrace();            
            return null;        
        }
    }
    
    public boolean belikursi(String id){
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {       
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("update kursi set status_kursi = ? where id_kursi = ?");
            con.preparedStatement.setString(1, "true");
            con.preparedStatement.setString(2, id);
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
    
}
