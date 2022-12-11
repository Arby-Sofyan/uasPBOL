/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts_2020130041;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Arby Sofyan
 */
public class ModelJadwal {
    private String id_jadwal, id_studio, id_film, judul_film, nama_studio, genre;
    private Date tanggal;
    private Time waktu_mulai, waktu_selesai;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public String getJudul_film() {
        return judul_film;
    }

    public void setJudul_film(String judul_film) {
        this.judul_film = judul_film;
    }
    
    public String getNama_studio() {
        return nama_studio;
    }

    public void setNama_studio(String nama_studio) {
        this.nama_studio = nama_studio;
    }
    
    
    
    public String getId_jadwal() {
        return id_jadwal;
    }

    public void setId_jadwal(String id_jadwal) {
        this.id_jadwal = id_jadwal;
    }

    public String getId_studio() {
        return id_studio;
    }

    public void setId_studio(String id_studio) {
        this.id_studio = id_studio;
    }

    public String getId_film() {
        return id_film;
    }

    public void setId_film(String id_film) {
        this.id_film = id_film;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Time getWaktu_mulai() {
        return waktu_mulai;
    }

    public void setWaktu_mulai(Time waktu_mulai) {
        this.waktu_mulai = waktu_mulai;
    }

    public Time getWaktu_selesai() {
        return waktu_selesai;
    }

    public void setWaktu_selesai(Time waktu_selesai) {
        this.waktu_selesai = waktu_selesai;
    }
    
    
}
