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
public class FXMLDisplayStudioController implements Initializable {

    @FXML
    private TextField txtcari;
    @FXML
    private TableView<ModelStudio> tbvstudio;

    /**
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showdata();
        firstclick();
    }    

    @FXML
    private void search(KeyEvent event) {
        ModelStudio s = new ModelStudio();
        String key = txtcari.getText();
        if(key!=""){
        ObservableList<ModelStudio> data=FXMLDocumentController.dtstudio.LookUp("nama_studio", key);
        if(data!=null){            
            show(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvstudio.getScene().getWindow().hide();;
        }            
            } else{
               showdata();
            } 
    }

    @FXML
    private void tambahklik(ActionEvent event) {
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLTambahStudio.fxml"));   
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
    private void hapusklik(ActionEvent event) {
        ModelStudio s= new ModelStudio();       
        s=tbvstudio.getSelectionModel().getSelectedItem();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
           if(FXMLDocumentController.dtstudio.delete(s.getId_studio())){
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
    private void keluarklik(ActionEvent event) {
        tbvstudio.getScene().getWindow().hide();
    }
    
    public void showdata(){
        ObservableList<ModelStudio> data=FXMLDocumentController.dtstudio.Load();
        if(data!=null){            
            show(data);
    }else {Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvstudio.getScene().getWindow().hide();
        }     
    }
    
    public void show(ObservableList<ModelStudio> data){
            tbvstudio.getColumns().clear();            
            tbvstudio.getItems().clear();
            TableColumn col = new TableColumn("Id Studio");
            col.setCellValueFactory(new PropertyValueFactory<ModelStudio, String>("id_studio"));
            tbvstudio.getColumns().addAll(col);
            
            col=new TableColumn("Nama Studio");
            col.setCellValueFactory(new PropertyValueFactory<ModelStudio, String>("nama_studio"));
            tbvstudio.getColumns().addAll(col);
            
            col=new TableColumn("Jumlah Kursi");
            col.setCellValueFactory(new PropertyValueFactory<ModelStudio, String>("jumlah_kursi"));
            tbvstudio.getColumns().addAll(col);
            
            tbvstudio.setItems(data);
    }
    
    public void firstclick(){
        tbvstudio.getSelectionModel().selectFirst();        
        tbvstudio.requestFocus();
    }
}
