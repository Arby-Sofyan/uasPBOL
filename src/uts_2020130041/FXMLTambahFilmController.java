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
public class FXMLTambahFilmController implements Initializable {

    public boolean editdata= false;
    @FXML
    private TextField txtjudul;
    @FXML
    private TextField txtgenre;
    /**
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void execute(ModelFilm d){
        if(!d.getId_film().isEmpty()){
        editdata= true;
        txtjudul.setText(d.getJudul_film());
        txtgenre.setText(d.getGenre());
        FXMLDocumentController.dtfilm.setModelFilm(d);
        }
    }

    @FXML
    private void tambah(ActionEvent event) {
        ModelFilm n = new ModelFilm();
        if(txtjudul.getText()!="" &&  txtgenre.getText()!=""){
        n.setJudul_film(txtjudul.getText());
        n.setGenre(txtgenre.getText());
        
        ArrayList<String> List =FXMLDocumentController.dtfilm.ListID();
        String urutan;
        if(!List.isEmpty()){
           int urut = Integer.valueOf(List.get(List.size()-1).substring(1)) + 1;
           if(urut<100){urutan = "00"+String.valueOf(urut);}
           else if(urut<100){urutan = "0"+String.valueOf(urut);}
           else {urutan = String.valueOf(urut);}
       } else {
           urutan ="001";
       }
        n.setId_film("6"+urutan);
        }
        
        
        if(editdata){
            n.setId_film(FXMLDocumentController.dtfilm.getModelFilm().getId_film());
            FXMLDocumentController.dtfilm.setModelFilm(n);
            if(FXMLDocumentController.dtfilm.update()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();   
               txtjudul.getScene().getWindow().hide();
            } else {               
                Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
                a.showAndWait(); 
            }
        }   else if(FXMLDocumentController.dtfilm.validasi(n.getId_film())<=0){
            FXMLDocumentController.dtfilm.setModelFilm(n);
            if(FXMLDocumentController.dtfilm.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan",ButtonType.OK);
               a.showAndWait();            
               batal(event);
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
        }
        
    }

    @FXML
    private void batal(ActionEvent event) {
        txtjudul.setText("");
        txtgenre.setText("");
    }
}
