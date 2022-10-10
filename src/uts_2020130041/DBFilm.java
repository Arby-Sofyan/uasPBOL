/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
public class DBFilm {
    private Jadwal_FilmModel dt = new Jadwal_FilmModel();

    public Jadwal_FilmModel getJadwal_FilmModel() {
        return dt;
    }

    public void setJadwal_FilmModel(Jadwal_FilmModel s) {
        dt = s;
    }

    //LookUP
    /*
    public ObservableList<Jadwal_FilmModel> LookUp(String fld, String dt) {
        try {
            ObservableList<Jadwal_FilmModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select NPM, Nama, Alamat from jadwal_film where " + fld + " like '%" + dt + "%'");
            int i = 1;
            while (rs.next()) {
                Jadwal_FilmModel d = new Jadwal_FilmModel();
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

    
    public ObservableList<Jadwal_FilmModel> Load() {
        try {
            ObservableList<Jadwal_FilmModel> TableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();

            ResultSet rs = con.statement.executeQuery(
                    "Select * from jadwal_film ");

            int i = 1;
            while (rs.next()) {
                Jadwal_FilmModel d = new Jadwal_FilmModel();
                d.setId_Jadwal(rs.getString("Id_Jadwal"));
                d.setJudul_Film(rs.getString("Judul_Film"));
                d.setJamTayang(rs.getTime("Waktu_Mulai"));
                d.setJamSelesai(rs.getTime("Waktu_Selesai"));
                d.setTanggal(rs.getDate("Tanggal"));

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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from jadwal_film where Id_Jadwal = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into jadwal_film (Id_Jadwal, Judul_Film, Tanggal, Waktu_Mulai, Waktu_Selesai) values (?,?,?,?,?)");
            con.preparedStatement.setString(1, getJadwal_FilmModel().getId_Jadwal());
            con.preparedStatement.setString(2, getJadwal_FilmModel().getJudul_Film());
            con.preparedStatement.setDate(3, getJadwal_FilmModel().getTanggal());
            con.preparedStatement.setTime(4, getJadwal_FilmModel().getJamTayang());
            con.preparedStatement.setTime(5, getJadwal_FilmModel().getJamSelesai());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from jadwal_film where Id_Jadwal  = ? ");
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
                    "update jadwal_film set Judul_Film = ?, Tanggal = ?, Waktu_Mulai = ?, Waktu_Selesai = ?  where  Id_Jadwal = ? ; ");
            con.preparedStatement.setString(1, getJadwal_FilmModel().getJudul_Film());
            con.preparedStatement.setDate(2, getJadwal_FilmModel().getTanggal());
            con.preparedStatement.setTime(3, getJadwal_FilmModel().getJamTayang());
            con.preparedStatement.setTime(4, getJadwal_FilmModel().getJamSelesai());
            con.preparedStatement.setString(5, getJadwal_FilmModel().getId_Jadwal());
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
    
    public JamFilm AmbilJam(String Id){
        
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();

            ResultSet rs = con.statement.executeQuery(
                    "Select Waktu_Mulai, Waktu_Selesai from jadwal_film where Id_Jadwal = '" + Id + "'");

            JamFilm jam = new JamFilm();
            jam.setJamTayang(rs.getTime("Waktu_Mulai"));
            jam.setJamSelesai(rs.getTime("Waktu_Selesai"));
            return jam;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
