/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts_2020130041;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Arby Sofyan
 */
public class FXMLDisplayTiketController implements Initializable {

    @FXML
    private Label namacust;
    @FXML
    private Label judul;
    @FXML
    private Label namastudio;
    @FXML
    private Label jumlah;
    @FXML
    private Label total;

    /**
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        namacust.setText(FXMLDocumentController.transaksi[0]);
        judul.setText(FXMLDocumentController.transaksi[1]);
        namastudio.setText(FXMLDocumentController.transaksi[2]);
        jumlah.setText(FXMLDocumentController.transaksi[3]);
        total.setText(FXMLDocumentController.transaksi[4]);
    }    

    @FXML
    private void close(ActionEvent event) {
        namacust.getScene().getWindow().hide();
    }
    
}
