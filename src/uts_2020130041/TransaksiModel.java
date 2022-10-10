/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts_2020130041;

import java.sql.Time;

/**
 *
 * @author Arby Sofyan
 */
public class TransaksiModel {
    String Id_Transaksi,Id_Customer, Judul_Film, Id_Jadwal;
    Time Jam_Mulai, Jam_Selesai;
    int Harga_Bayar,Jumlah_Tiket;

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

    public Time getJam_Mulai() {
        return Jam_Mulai;
    }

    public void setJam_Mulai(Time Jam_Mulai) {
        this.Jam_Mulai = Jam_Mulai;
    }

    public Time getJam_Selesai() {
        return Jam_Selesai;
    }

    public void setJam_Selesai(Time Jam_Selesai) {
        this.Jam_Selesai = Jam_Selesai;
    }
    
    
    
    public String getId_Transaksi() {
        return Id_Transaksi;
    }

    public void setId_Transaksi(String Id_Transaksi) {
        this.Id_Transaksi = Id_Transaksi;
    }

    public String getId_Customer() {
        return Id_Customer;
    }

    public void setId_Customer(String Id_Customer) {
        this.Id_Customer = Id_Customer;
    }

    public int getHarga_Bayar() {
        return Harga_Bayar;
    }

    public void setHarga_Bayar(int Harga_Bayar) {
        this.Harga_Bayar = Harga_Bayar;
    }

    public int getJumlah_Tiket() {
        return Jumlah_Tiket;
    }

    public void setJumlah_Tiket(int Jumlah_Tiket) {
        this.Jumlah_Tiket = Jumlah_Tiket;
    }
}
