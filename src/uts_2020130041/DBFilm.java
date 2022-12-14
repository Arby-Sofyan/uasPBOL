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
public class DBFilm {
    private ModelFilm dt = new ModelFilm();
    public ModelFilm getModelFilm() {return dt;}
    public void setModelFilm(ModelFilm s) {dt = s;}

    
    public ObservableList<ModelFilm> Load() {
        try {
            ObservableList<ModelFilm> TableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();

            ResultSet rs = con.statement.executeQuery(
                    "Select * from film ");
            int i = 1;
            while (rs.next()) {
                ModelFilm d = new ModelFilm();
                d.setId_film(rs.getString("id_film"));
                d.setJudul_film(rs.getString("judul_film"));
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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from film where id_film = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into film (id_film, judul_film, genre) values (?,?,?)");
            con.preparedStatement.setString(1, getModelFilm().getId_film());
            con.preparedStatement.setString(2, getModelFilm().getJudul_film());
            con.preparedStatement.setString(3, getModelFilm().getGenre());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from film where idfilm  = ? ");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update film set judul_film = ?, genre = ? where  id_film = ?");
            con.preparedStatement.setString(1, getModelFilm().getJudul_film());           
            con.preparedStatement.setString(2, getModelFilm().getGenre()); 
            con.preparedStatement.setString(3, getModelFilm().getId_film());
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
    
    public ObservableList<ModelFilm> LookUp(String fld, String dt) {
        try {
            ObservableList<ModelFilm> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select * from film where " + fld + " like '%" + dt + "%'");
            int i = 1;
            while (rs.next()) {
                ModelFilm d = new ModelFilm();
                d.setId_film(rs.getString("id_film"));
                d.setJudul_film(rs.getString("judul_film"));
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
            ResultSet rs = con.statement.executeQuery("select id_film from film");
            while (rs.next()) {
                List.add(rs.getString("id_film"));
            }
            return List;
        } catch (Exception e) {            
            e.printStackTrace();            
            return null;        
        }
    }
    
}

