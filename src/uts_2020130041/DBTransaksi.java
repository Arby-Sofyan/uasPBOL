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
public class DBTransaksi {
    private TransaksiModel dt = new TransaksiModel();

    public TransaksiModel getTransaksiModel() {
        return dt;
    }

    public void setTransaksiModel(TransaksiModel s) {
        dt = s;
    }

    //LookUP
    /*
    public ObservableList<TransaksiModel> LookUp(String fld, String dt) {
        try {
            ObservableList<TransaksiModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select NPM, Nama, Alamat from transaksi where " + fld + " like '%" + dt + "%'");
            int i = 1;
            while (rs.next()) {
                TransaksiModel d = new TransaksiModel();
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

    
    public ObservableList<TransaksiModel> Load() {
        try {
            ObservableList<TransaksiModel> TableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();

            ResultSet rs = con.statement.executeQuery(
                    "Select Id_Transaksi, Id_Customer, Judul_Film, Waktu_Mulai, Waktu_Selesai, Jumlah_Tiket, Harga_Bayar from transaksi t "
                            + "join jadwal_film j on (t.Id_Jadwal = j.Id_Jadwal)");

            int i = 1;
            while (rs.next()) {
                TransaksiModel d = new TransaksiModel();
                d.setId_Transaksi(rs.getString("Id_Transaksi"));
                d.setId_Customer(rs.getString("Id_Customer"));
                d.setJudul_Film(rs.getString("Judul_Film"));
                d.setJam_Mulai(rs.getTime("Waktu_Mulai"));
                d.setJam_Selesai(rs.getTime("Waktu_Selesai"));
                d.setJumlah_Tiket(rs.getInt("Jumlah_Tiket"));
                d.setHarga_Bayar(rs.getInt("Harga_Bayar"));
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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from transaksi where Id_Transaksi = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into transaksi (Id_Transaksi, Id_Customer, Harga_Bayar, Jumlah_Tiket, Id_Jadwal) values (?,?,?,?,?)");
            con.preparedStatement.setString(1, getTransaksiModel().getId_Transaksi());
            con.preparedStatement.setString(2, getTransaksiModel().getId_Customer());
            con.preparedStatement.setInt(3, getTransaksiModel().getHarga_Bayar());
            con.preparedStatement.setInt(4, getTransaksiModel().getJumlah_Tiket());
            con.preparedStatement.setString(5, getTransaksiModel().getId_Jadwal());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from transaksi where Id_Transaksi  = ? ");
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
                    "update transaksi set Id_Customer = ?, Harga_Bayar = ?, Jumlah_Tiket = ?, Id_Jadwal = ?  where  Id_Transaksi = ? ; ");
            con.preparedStatement.setString(1, getTransaksiModel().getId_Customer());
            con.preparedStatement.setInt(2, getTransaksiModel().getHarga_Bayar());
            con.preparedStatement.setInt(3, getTransaksiModel().getJumlah_Tiket());
            con.preparedStatement.setString(4, getTransaksiModel().getId_Jadwal());
            con.preparedStatement.setString(5, getTransaksiModel().getId_Transaksi());
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
    
    public ArrayList<String> CheckKode(){
        try {
            ArrayList<String> List = new ArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select Id_Transaksi from transaksi");
            while (rs.next()) {
                List.add(rs.getString("Id_Transaksi"));
            }
            return List;
        } catch (Exception e) {            
            e.printStackTrace();            
            return null;        
        }
        
        
    }
    
}
