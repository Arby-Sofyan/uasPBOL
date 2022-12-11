/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts_2020130041;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Arby Sofyan
 */
public class DBDetilTransaksi {
     private ModelDetilTransaksi dt = new ModelDetilTransaksi();
    public ModelDetilTransaksi getModelDetilTransaksi() {return dt;}
    public void setModelDetilTransaksi(ModelDetilTransaksi s) {dt = s;}

    
    public ObservableList<ModelDetilTransaksi> Load() {
        try {
            ObservableList<ModelDetilTransaksi> TableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();

            ResultSet rs = con.statement.executeQuery(
                    "Select * from detiltransaksi ");
            int i = 1;
            while (rs.next()) {
                ModelDetilTransaksi d = new ModelDetilTransaksi();
                d.setId_transaksi(rs.getString("id_transaksi"));
                d.setId_kursi(rs.getString("id_kursi"));
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
    
    
   

    public int validasi(String nomor, String nomor1) {
        int val = 0;
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from detiltransaksi where id_transaksi = '" + nomor + "' and id_kursi = '" + nomor1 + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into detiltransaksi (id_transaksi, id_kursi) values (?,?)");
            con.preparedStatement.setString(1, getModelDetilTransaksi().getId_transaksi());
            con.preparedStatement.setString(2, getModelDetilTransaksi().getId_kursi());
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
    
    public boolean delete(String nomor, String nomor1) {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from detiltransaksi where id_transaksi  = ? and id_kursi = ?");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.setString(2, nomor1);
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }
    
    public boolean deleteall(String nomor) {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from detiltransaksi where id_transaksi =?");
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
    
    public ObservableList<ModelDetilTransaksi> LookUp(String fld, String dt) {
        try {
            ObservableList<ModelDetilTransaksi> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select * from detiltransaksi where " + fld + " like '%" + dt + "%'");
            int i = 1;
            while (rs.next()) {
                ModelDetilTransaksi d = new ModelDetilTransaksi();
                d.setId_transaksi(rs.getString("id_transaksi"));
                d.setId_kursi(rs.getString("id_kursi"));
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
    
    public ObservableList<ModelDetilTransaksi> SearchDetil(String dt) {
        try {
            ObservableList<ModelDetilTransaksi> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select id_transaksi, d.id_kursi, nomor_kursi from detiltransaksi d join kursi k on (d.id_kursi = k.id_kursi) where id_transaksi = "+ dt);
            int i = 1;
            while (rs.next()) {
                ModelDetilTransaksi d = new ModelDetilTransaksi();
                d.setId_transaksi(rs.getString("id_transaksi"));
                d.setId_kursi(rs.getString("id_kursi"));
                d.setNama_kursi(rs.getString("nomor_kursi"));
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
    
}
