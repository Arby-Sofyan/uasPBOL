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
    
    private ModelTransaksi dt = new ModelTransaksi();
    public ModelTransaksi getModelTransaksi() {return dt;}
    public void setModelTransaksi(ModelTransaksi s) {dt = s;}

    
    public ObservableList<ModelTransaksi> Load() {
        try {
            ObservableList<ModelTransaksi> TableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();

            ResultSet rs = con.statement.executeQuery(
                    "Select id_transaksi, judul_film, nama_studio, nama, jumlah_tiket, total_bayar from transaksi t " +
                    "join customer c on (t.id_customer = c.id_customer) " +
                    "join jadwal_film j ON (t.id_jadwal = j.id_jadwal) " +
                    "join studio s on (j.id_studio = s.id_studio) " +
                    "join film f ON (j.id_film = f.id_film)");
            int i = 1;
            while (rs.next()) {
                ModelTransaksi d = new ModelTransaksi();
                d.setId_transaksi(rs.getString("id_transaksi"));
                d.setJudul_film(rs.getString("judul_film"));
                d.setNama_studio(rs.getString("nama_studio"));
                d.setNama_customer(rs.getString("nama"));
                d.setJumlah_tiket(rs.getInt("jumlah_tiket"));
                d.setTotal_bayar(rs.getInt("total_bayar"));
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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from transaksi where id_transaksi = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into transaksi (id_transaksi, id_jadwal, id_customer, jumlah_tiket, total_bayar) values (?,?,?,?,?)");
            con.preparedStatement.setString(1, getModelTransaksi().getId_transaksi());
            con.preparedStatement.setString(2, getModelTransaksi().getId_jadwal());
            con.preparedStatement.setString(3, getModelTransaksi().getId_customer());
            con.preparedStatement.setInt(4, getModelTransaksi().getJumlah_tiket());
            con.preparedStatement.setInt(5, getModelTransaksi().getTotal_bayar());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from transaksi where id_transaksi  = ? ");
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
    
    
    public ObservableList<ModelTransaksi> LookUp(String dt) {
        try {
            ObservableList<ModelTransaksi> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select id_transaksi, judul_film, nama_studio, nama, jumlah_tiket, total_bayar from transaksi t " +
                    "join customer c on (t.id_customer = c.id_customer) " +
                    "join jadwal_film j ON (t.id_jadwal = j.id_jadwal) " +
                    "join studio s on (j.id_studio = s.id_studio) " +
                    "join film f ON (j.id_film = f.id_film) where judul_film like '%" + dt + "%' or nama like '%" + dt + "%' or nama_studio like '%" + dt + "%' ");
            int i = 1;
            while (rs.next()) {
                ModelTransaksi d = new ModelTransaksi();
                d.setId_transaksi(rs.getString("id_transaksi"));
                d.setJudul_film(rs.getString("judul_film"));
                d.setNama_studio(rs.getString("nama_studio"));
                d.setNama_customer(rs.getString("nama"));
                d.setJumlah_tiket(rs.getInt("jumlah_tiket"));
                d.setTotal_bayar(rs.getInt("total_bayar"));
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
    
    public ArrayList<String> ListID(){
        try {
            ArrayList<String> List = new ArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select id_transaksi from transaksi");
            while (rs.next()) {
                List.add(rs.getString("id_transaksi"));
            }
            return List;
        } catch (Exception e) {            
            e.printStackTrace();            
            return null;        
        }
    }
    
}
