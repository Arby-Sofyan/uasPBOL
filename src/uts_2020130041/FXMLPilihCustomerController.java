/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts_2020130041;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Arby Sofyan
 */
public class FXMLPilihCustomerController implements Initializable {

    
    private int hasil=0;
    private ModelCustomer cust = new ModelCustomer();    
    public int getHasil(){return(hasil);}
    public ModelCustomer getModelCustomer(){return(cust);}
    
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnpilih;
    @FXML
    private TableView<ModelCustomer> tbvcust;
    @FXML
    private Label lidcustomer;
    @FXML
    private Label lnama;
    @FXML
    private TextField txtcari;

    /**
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showdata();
        firstclick();
    }    

    @FXML
    private void batalklik(ActionEvent event) {
        hasil=0;
        btnbatal.getScene().getWindow().hide();
    }

    @FXML
    private void pilihklik(ActionEvent event) {
        hasil=1;
        cust = tbvcust.getSelectionModel().getSelectedItem();
        btnpilih.getScene().getWindow().hide();
    }

    
    public void showdata(){
        ObservableList<ModelCustomer> data=FXMLDocumentController.dtcust.Load();
        if(data!=null){            
            show(data);
    }else {Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvcust.getScene().getWindow().hide();
        }     
    }
    
    public void show(ObservableList<ModelCustomer> data){
            tbvcust.getColumns().clear();            
            tbvcust.getItems().clear();
            TableColumn col = new TableColumn("Id Customer");
            col.setCellValueFactory(new PropertyValueFactory<ModelCustomer, String>("id_customer"));
            tbvcust.getColumns().addAll(col);
            
            col=new TableColumn("Nama Customer");
            col.setCellValueFactory(new PropertyValueFactory<ModelCustomer, String>("nama"));
            tbvcust.getColumns().addAll(col);
            
            col=new TableColumn("Email");
            col.setCellValueFactory(new PropertyValueFactory<ModelCustomer, String>("email"));
            tbvcust.getColumns().addAll(col);
            
            col=new TableColumn("No Handphone");
            col.setCellValueFactory(new PropertyValueFactory<ModelCustomer, String>("nohp"));
            tbvcust.getColumns().addAll(col);
            tbvcust.setItems(data);
    }
    
    public void firstclick(){
        tbvcust.getSelectionModel().selectFirst();        
        tbvcust.requestFocus();
    }

    @FXML
    private void Cari(KeyEvent event) {
        ModelCustomer s = new ModelCustomer();
        String key = txtcari.getText();
        if(key!=""){
        ObservableList<ModelCustomer> data=FXMLDocumentController.dtcust.LookUp("*","nama", key);
        if(data!=null){            
            show(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvcust.getScene().getWindow().hide();;
        }            
            } else{
               showdata();
            }
    }

    @FXML
    private void PilihID(MouseEvent event) {
        lnama.setText(tbvcust.getSelectionModel().getSelectedItem().getNama());
        lidcustomer.setText(tbvcust.getSelectionModel().getSelectedItem().getId_customer());
    }
    
}
