/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class DBJadwal {
    private ModelJadwal dt = new ModelJadwal();
    public ModelJadwal getModelJadwal() {return dt;}
    public void setModelJadwal(ModelJadwal s) {dt = s;}

    
    public ObservableList<ModelJadwal> Load() {
        try {
            ObservableList<ModelJadwal> TableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();

            ResultSet rs = con.statement.executeQuery(
                    "Select j.*, f.judul_film, s.nama_studio, f.genre from jadwal_film j "
                            + "join film f on (j.id_film = f.id_film) "
                            + "join studio s on (j.id_studio = s.id_studio)");
            int i = 1;
            while (rs.next()) {
                ModelJadwal d = new ModelJadwal();
                d.setId_jadwal(rs.getString("id_jadwal"));
                d.setId_studio(rs.getString("id_studio"));
                d.setId_film(rs.getString("id_film"));
                d.setTanggal(rs.getDate("tanggal"));
                d.setWaktu_mulai(rs.getTime("waktu_mulai"));
                d.setWaktu_selesai(rs.getTime("waktu_selesai"));
                d.setJudul_film(rs.getString("judul_film"));
                d.setNama_studio(rs.getString("nama_studio"));
                d.setGenre(rs.getString("genre"));
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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from jadwal_film where id_jadwal = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into jadwal_film (id_jadwal, id_studio, id_film, tanggal, waktu_mulai, waktu_selesai) values (?,?,?,?,?,?)");
            con.preparedStatement.setString(1, getModelJadwal().getId_jadwal());
            con.preparedStatement.setString(2, getModelJadwal().getId_studio());
            con.preparedStatement.setString(3, getModelJadwal().getId_film());
            con.preparedStatement.setDate(4, getModelJadwal().getTanggal());
            con.preparedStatement.setTime(5, getModelJadwal().getWaktu_mulai());
            con.preparedStatement.setTime(6, getModelJadwal().getWaktu_selesai());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from jadwal_film where id_jadwal  = ? ");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update jadwal_film set id_studio = ?, id_film = ?, tanggal = ?, waktu_mulai = ?, waktu_selesai = ? where  id_jadwal = ?");
            con.preparedStatement.setString(1, getModelJadwal().getId_studio());           
            con.preparedStatement.setString(2, getModelJadwal().getId_film()); 
            con.preparedStatement.setDate(3, getModelJadwal().getTanggal());
            con.preparedStatement.setTime(4, getModelJadwal().getWaktu_mulai());
            con.preparedStatement.setTime(5, getModelJadwal().getWaktu_selesai());
            con.preparedStatement.setString(6, getModelJadwal().getId_jadwal());
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
    
   
    
    public ObservableList<ModelJadwal> SearchJadwal(String dt) {
        try {
            ObservableList<ModelJadwal> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select j.*, f.judul_film, s.nama_studio, f.genre from jadwal_film j "
                            + "join film f on (j.id_film = f.id_film) "
                            + "join studio s on (j.id_studio = s.id_studio) where judul_film like '%" + dt + "%' or nama_studio like '%" + dt + "%' or tanggal like '%" + dt + "%'");
            int i = 1;
            while (rs.next()) {
                ModelJadwal d = new ModelJadwal();
                d.setId_jadwal(rs.getString("id_jadwal"));
                d.setId_studio(rs.getString("id_studio"));
                d.setId_film(rs.getString("id_film"));
                d.setTanggal(rs.getDate("tanggal"));
                d.setWaktu_mulai(rs.getTime("waktu_mulai"));
                d.setWaktu_selesai(rs.getTime("waktu_selesai"));
                d.setJudul_film(rs.getString("judul_film"));
                d.setNama_studio(rs.getString("nama_studio"));
                d.setGenre(rs.getString("genre"));
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
            ResultSet rs = con.statement.executeQuery("select id_jadwal from jadwal_film");
            while (rs.next()) {
                List.add(rs.getString("id_jadwal"));
            }
            return List;
        } catch (Exception e) {            
            e.printStackTrace();            
            return null;        
        }
    }
   
}
