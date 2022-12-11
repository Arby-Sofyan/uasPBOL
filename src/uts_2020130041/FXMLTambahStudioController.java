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
public class FXMLTambahStudioController implements Initializable {

    @FXML
    private TextField txtnama;
    /**
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void tambah(ActionEvent event) {
        ModelStudio n = new ModelStudio();
        if(txtnama.getText()!=""){
        n.setNama_studio(txtnama.getText());
        n.setJumlah_kursi(16);
        
        ArrayList<String> List =FXMLDocumentController.dtstudio.ListID();
        String urutan;
        if(!List.isEmpty()){
           int urut = Integer.valueOf(List.get(List.size()-1).substring(1)) + 1;
           if(urut<100){urutan = "00"+String.valueOf(urut);}
           else if(urut<100){urutan = "0"+String.valueOf(urut);}
           else {urutan = String.valueOf(urut);}
       } else {
           urutan ="001";
       }
        n.setId_studio("5"+urutan);
        }
        
        
        if(FXMLDocumentController.dtstudio.validasi(n.getId_studio())<=0){
            FXMLDocumentController.dtstudio.setModelStudio(n);
            if(FXMLDocumentController.dtstudio.insert()){
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
        txtnama.setText("");
    }
    
}
