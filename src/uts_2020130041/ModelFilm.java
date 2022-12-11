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
public class ModelFilm {
    private String id_film, judul_film, genre;

    /*public ModelFilm(String a, String b, String c){
        this.id_film = a;
        this.judul_film = b;
        this.genre = c;
    }*/
    
    public String getId_film() {
        return id_film;
    }

    public void setId_film(String id_film) {
        this.id_film = id_film;
    }

    public String getJudul_film() {
        return judul_film;
    }

    public void setJudul_film(String judul_film) {
        this.judul_film = judul_film;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    
}
