/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts_2020130041;

/**
 *
 * @author Arby Sofyan
 */
public class ModelKursi {
    private String id_kursi, id_studio, id_jadwal, nomor_kursi, status_kursi;

    /*public ModelKursi(String a, String b, String c, String d, String e){
        this.id_kursi =a;
        this.id_studio =b;
        this.id_jadwal =c;
        this.nomor_kursi =d;
        this.status = e;
    }*/
    
    public String getId_kursi() {
        return id_kursi;
    }

    public void setId_kursi(String id_kursi) {
        this.id_kursi = id_kursi;
    }

    public String getId_studio() {
        return id_studio;
    }

    public void setId_studio(String id_studio) {
        this.id_studio = id_studio;
    }

    public String getId_jadwal() {
        return id_jadwal;
    }

    public void setId_jadwal(String id_jadwal) {
        this.id_jadwal = id_jadwal;
    }

    public String getNomor_kursi() {
        return nomor_kursi;
    }

    public void setNomor_kursi(String nomor_kursi) {
        this.nomor_kursi = nomor_kursi;
    }

    public String getStatus_kursi() {
        return status_kursi;
    }

    public void setStatus_kursi(String status) {
        this.status_kursi = status;
    }
    
    
}
