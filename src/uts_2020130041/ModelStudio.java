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
public class ModelStudio {
    private String id_studio, nama_studio;
    private int jumlah_kursi;

    /*public ModelStudio(String a, String b, int c){
        this.id_studio = a;
        this.nama_studio = b;
        this.jumlah_kursi = c;
    }*/
    
    public String getId_studio() {
        return id_studio;
    }

    public void setId_studio(String id_studio) {
        this.id_studio = id_studio;
    }

    public String getNama_studio() {
        return nama_studio;
    }

    public void setNama_studio(String nama_studio) {
        this.nama_studio = nama_studio;
    }

    public int getJumlah_kursi() {
        return jumlah_kursi;
    }

    public void setJumlah_kursi(int jumlah_kursi) {
        this.jumlah_kursi = jumlah_kursi;
    }
    
    
}
