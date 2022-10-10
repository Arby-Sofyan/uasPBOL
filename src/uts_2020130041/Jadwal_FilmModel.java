/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts_2020130041;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Arby Sofyan
 */
public class Jadwal_FilmModel {
    private String Id_Jadwal,Judul_Film;
    private Time JamTayang, JamSelesai;
    private Date Tanggal;

    public Time getJamTayang() {
        return JamTayang;
    }

    public void setJamTayang(Time JamTayang) {
        this.JamTayang = JamTayang;
    }

    public Time getJamSelesai() {
        return JamSelesai;
    }

    public void setJamSelesai(Time JamSelesai) {
        this.JamSelesai = JamSelesai;
    }

    
    
    public String getId_Jadwal() {
        return Id_Jadwal;
    }

    public void setId_Jadwal(String Id_Jadwal) {
        this.Id_Jadwal = Id_Jadwal;
    }

    public String getJudul_Film() {
        return Judul_Film;
    }

    public void setJudul_Film(String Judul_Film) {
        this.Judul_Film = Judul_Film;
    }

    public Date getTanggal() {
        return Tanggal;
    }

    public void setTanggal(Date Tanggal) {
        this.Tanggal = Tanggal;
    }

}
