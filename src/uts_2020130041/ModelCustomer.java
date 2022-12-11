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
public class ModelCustomer {
    
    String id_customer, nama, email, nohp;

    /*public ModelCustomer(String a, String b, String c, String d){
        this.id_customer = a;
        this.nama = b;
        this.email = c;
        this.nohp = d;
    }*/
    
    public String getId_customer() {
        return id_customer;
    }

    public void setId_customer(String id_customer) {
        this.id_customer = id_customer;
    }
    
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }
    
}
