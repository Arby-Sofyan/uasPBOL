/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts_2020130041;

import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Arby Sofyan
 */
public class DBCustomer {

    private CustomerModel dt = new CustomerModel();

    public CustomerModel getCustomerModel() {
        return dt;
    }

    public void setCustomerModel(CustomerModel s) {
        dt = s;
    }

    public ObservableList<CustomerModel> Load() {
        try {
            ObservableList<CustomerModel> TableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();

            ResultSet rs = con.statement.executeQuery(
                    "Select * from customer ");

            int i = 1;
            while (rs.next()) {
                CustomerModel d = new CustomerModel();
                d.setEmail(rs.getString("Id_Customer"));
                d.setEmail(rs.getString("Email"));
                d.setNama(rs.getString("Nama"));
                d.setNoHp(rs.getString("NoHp"));

               
                i++;
            }

            con.tutupKoneksi();
            return TableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
