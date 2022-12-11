/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts_2020130041;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Arby Sofyan
 */
public class FXMLTambahCustomerController implements Initializable {

    public boolean editdata= false;
    @FXML
    private TextField txtnama;
    @FXML
    private TextField txtemail;
    @FXML
    private TextField txtnomorhp;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // TODO
    }    

    @FXML
    private void batal(ActionEvent event) {
        txtnama.setText("");
        txtemail.setText("");
        txtnomorhp.setText("");
    }

    @FXML
    private void tambah(ActionEvent event) {
        ModelCustomer n = new ModelCustomer();
        if(txtnama.getText()!="" &&  txtnomorhp.getText()!=""){
        n.setNama(txtnama.getText());
        n.setEmail(txtemail.getText());
        n.setNohp(txtnomorhp.getText());
        
        ArrayList<String> List =FXMLDocumentController.dtcust.ListID();
        String urutan;
        if(!List.isEmpty()){
           int urut = Integer.valueOf(List.get(List.size()-1).substring(1)) + 1;
           if(urut<100){urutan = "00"+String.valueOf(urut);}
           else if(urut<100){urutan = "0"+String.valueOf(urut);}
           else {urutan = String.valueOf(urut);}
       } else {
           urutan ="001";
       }
        n.setId_customer("1"+urutan);
        }
        
        
        if(editdata){
            n.setId_customer(FXMLDocumentController.dtcust.getModelCustomer().getId_customer());
            FXMLDocumentController.dtcust.setModelCustomer(n);
            if(FXMLDocumentController.dtcust.update()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();   
               txtnama.getScene().getWindow().hide();
            } else {               
                Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
                a.showAndWait(); 
            }
        }   else if(FXMLDocumentController.dtcust.validasi(n.getId_customer())<=0){
            FXMLDocumentController.dtcust.setModelCustomer(n);
            if(FXMLDocumentController.dtcust.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan",ButtonType.OK);
               a.showAndWait();            
               batal(event);
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
        }
    }
    
    public void execute(ModelCustomer d){
        if(!d.getId_customer().isEmpty()){
        editdata= true;
        txtnama.setText(d.getNama());
        txtemail.setText(d.getEmail());
        txtnomorhp.setText(d.getNohp());
        FXMLDocumentController.dtcust.setModelCustomer(d);
        }
    }
    
}
