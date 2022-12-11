/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts_2020130041;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Arby Sofyan
 */
public class FXMLDisplayCustomerController implements Initializable {

    @FXML
    private TableView<ModelCustomer> tbvcust;
    @FXML
    private TextField txtcari;

    /**
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
        firstclick();
        // TODO
    }    

    @FXML
    private void keluarklik(ActionEvent event) {
        tbvcust.getScene().getWindow().hide();
    }

    @FXML
    private void hapusklik(ActionEvent event) {
        ModelCustomer s= new ModelCustomer();       
        s=tbvcust.getSelectionModel().getSelectedItem();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
           if(FXMLDocumentController.dtcust.delete(s.getId_customer())){
               Alert b=new Alert(Alert.AlertType.INFORMATION,"Data berhasil dihapus", ButtonType.OK);
               b.showAndWait();
           } else {
               Alert b=new Alert(Alert.AlertType.ERROR,"Data gagal dihapus", ButtonType.OK);
               b.showAndWait();               
           }    
           showdata();  
           firstclick();
        }
    }

    @FXML
    private void editklik(ActionEvent event) {
        ModelCustomer s= new ModelCustomer();
        s=tbvcust.getSelectionModel().getSelectedItem();
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLTambahCustomer.fxml"));    
        Parent root = (Parent)loader.load();
        FXMLTambahCustomerController isidt=(FXMLTambahCustomerController)loader.getController();
        isidt.execute(s);                
        Scene scene = new Scene(root);
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);
        stg.setIconified(false);
        stg.setScene(scene);
        stg.showAndWait();
        } catch (IOException e){   e.printStackTrace();   }
        showdata();  
        firstclick();
    }

    @FXML
    private void tambahklik(ActionEvent event) {
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLTambahCustomer.fxml"));   
        Parent root = (Parent)loader.load();        
        Scene scene = new Scene(root);        
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);        
        stg.setIconified(false);        
        stg.setScene(scene);
        stg.showAndWait();
        } catch (IOException e){   
            e.printStackTrace();   }
        showdata();        
        firstclick();
    }

    @FXML
    private void search(KeyEvent event) {
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
}
