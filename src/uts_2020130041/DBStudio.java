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
public class DBStudio {
    
    private ModelStudio dt = new ModelStudio();
    public ModelStudio getModelStudio() {return dt;}
    public void setModelStudio(ModelStudio s) {dt = s;}

    
    public ObservableList<ModelStudio> Load() {
        try {
            ObservableList<ModelStudio> TableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();

            ResultSet rs = con.statement.executeQuery(
                    "Select * from studio ");
            int i = 1;
            while (rs.next()) {
                ModelStudio d = new ModelStudio();
                d.setId_studio(rs.getString("id_studio"));
                d.setNama_studio(rs.getString("nama_studio"));
                d.setJumlah_kursi(rs.getInt("jumlah_kursi"));
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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from studio where id_studio = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into studio (id_studio, nama_studio, jumlah_kursi) values (?,?,?)");
            con.preparedStatement.setString(1, getModelStudio().getId_studio());
            con.preparedStatement.setString(2, getModelStudio().getNama_studio());
            con.preparedStatement.setInt(3, getModelStudio().getJumlah_kursi());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from studio where id_studio  = ? ");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update studio set nama_studio = ?, jumlah_kursi = ? where  id_studio = ?");
            con.preparedStatement.setString(1, getModelStudio().getNama_studio());           
            con.preparedStatement.setInt(2, getModelStudio().getJumlah_kursi()); 
            con.preparedStatement.setString(3, getModelStudio().getId_studio());
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
    
    public ObservableList<ModelStudio> LookUp(String fld, String dt) {
        try {
            ObservableList<ModelStudio> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select * from studio where " + fld + " like '%" + dt + "%'");
            int i = 1;
            while (rs.next()) {
                ModelStudio d = new ModelStudio();
                d.setId_studio(rs.getString("id_studio"));
                d.setNama_studio(rs.getString("nama"));
                d.setJumlah_kursi(rs.getInt("email"));
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
            ResultSet rs = con.statement.executeQuery("select id_studio from studio");
            while (rs.next()) {
                List.add(rs.getString("id_studio"));
            }
            return List;
        } catch (Exception e) {            
            e.printStackTrace();            
            return null;        
        }
    }
    
}
