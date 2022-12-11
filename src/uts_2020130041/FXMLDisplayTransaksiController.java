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
import javafx.scene.control.ButtonType;
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
public class FXMLDisplayTransaksiController implements Initializable {

    @FXML
    private TableView<ModelTransaksi> tbvmaster;
    @FXML
    private TableView<ModelDetilTransaksi> tbvdetil;
    @FXML
    private TextField txtcari;

    /**
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdatamaster();
        // TODO
    }    

    @FXML
    private void tutup(ActionEvent event) {
        tbvmaster.getScene().getWindow().hide();
    }
    
    public void showdatadetil(String id){
        ObservableList<ModelDetilTransaksi> data=FXMLDocumentController.dtdetil.SearchDetil(id);
        if(data!=null){            
            showdetil(data);
    }else {Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvdetil.getScene().getWindow().hide();
        }     
    }
    
    public void showdetil(ObservableList<ModelDetilTransaksi> data){
            tbvdetil.getColumns().clear();            
            tbvdetil.getItems().clear();
            TableColumn col = new TableColumn("Id Transaksi");
            col.setCellValueFactory(new PropertyValueFactory<ModelDetilTransaksi, String>("id_transaksi"));
            tbvdetil.getColumns().addAll(col);
            
            col=new TableColumn("Id Kursi");
            col.setCellValueFactory(new PropertyValueFactory<ModelDetilTransaksi, String>("id_kursi"));
            tbvdetil.getColumns().addAll(col);
            
            col=new TableColumn("Nama Kursi");
            col.setCellValueFactory(new PropertyValueFactory<ModelDetilTransaksi, String>("nama_kursi"));
            tbvdetil.getColumns().addAll(col);
            tbvdetil.setItems(data);
    }
    
    
    public void showdatamaster(){
        ObservableList<ModelTransaksi> data=FXMLDocumentController.dttrans.Load();
        if(data!=null){            
            showmaster(data);
    }else {Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvmaster.getScene().getWindow().hide();
        }     
    }
    
    public void showmaster(ObservableList<ModelTransaksi> data){
            tbvmaster.getColumns().clear();            
            tbvmaster.getItems().clear();
            TableColumn col = new TableColumn("Id Transaksi");
            col.setCellValueFactory(new PropertyValueFactory<ModelTransaksi, String>("id_transaksi"));
            tbvmaster.getColumns().addAll(col);
            
            col=new TableColumn("Judul Film");
            col.setCellValueFactory(new PropertyValueFactory<ModelTransaksi, String>("judul_film"));
            tbvmaster.getColumns().addAll(col);
            
            col=new TableColumn("Nama Studio");
            col.setCellValueFactory(new PropertyValueFactory<ModelTransaksi, String>("nama_studio"));
            tbvmaster.getColumns().addAll(col);
            
            col=new TableColumn("Nama Customer");
            col.setCellValueFactory(new PropertyValueFactory<ModelTransaksi, String>("nama_customer"));
            tbvmaster.getColumns().addAll(col);
            
            col=new TableColumn("Jumlah Tiket");
            col.setCellValueFactory(new PropertyValueFactory<ModelTransaksi, String>("jumlah_tiket"));
            tbvmaster.getColumns().addAll(col);
            
            col=new TableColumn("Total Bayar");
            col.setCellValueFactory(new PropertyValueFactory<ModelTransaksi, String>("total_bayar"));
            tbvmaster.getColumns().addAll(col);
            tbvmaster.setItems(data);
    }


    @FXML
    private void Search(KeyEvent event) {
        ModelTransaksi s = new ModelTransaksi();
        String key = txtcari.getText();
        if(key!=""){
        ObservableList<ModelTransaksi> data=FXMLDocumentController.dttrans.LookUp(key);
        if(data!=null){            
            showmaster(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvmaster.getScene().getWindow().hide();;
        }            
            } else{
               showdatamaster();
            } 
    }

    @FXML
    private void TampilkanDetil(MouseEvent event) {
        showdatadetil(tbvmaster.getSelectionModel().getSelectedItem().getId_transaksi());
    }
    
}
