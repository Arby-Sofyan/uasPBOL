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
public class FXMLDisplayJadwalController implements Initializable {

    @FXML
    private TextField txtcari;
    @FXML
    private TableView<ModelJadwal> tbvjadwal;

    /**
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
        firstclick();
    }    

    @FXML
    private void search(KeyEvent event) {
        ModelJadwal s = new ModelJadwal();
        String key = txtcari.getText();
        if(key!=""){
        ObservableList<ModelJadwal> data=FXMLDocumentController.dtjadwal.SearchJadwal(key);
        if(data!=null){            
            show(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvjadwal.getScene().getWindow().hide();;
        }            
            } else{
               showdata();
            } 
    }

    @FXML
    private void tambahklik(ActionEvent event) {
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLTambahJadwal.fxml"));   
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
    private void editklik(ActionEvent event) {
        ModelJadwal s= new ModelJadwal();
        s=tbvjadwal.getSelectionModel().getSelectedItem();
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLTambahJadwal.fxml"));    
        Parent root = (Parent)loader.load();
        FXMLTambahJadwalController isidt=(FXMLTambahJadwalController)loader.getController();
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
    private void hapusklik(ActionEvent event) {
        ModelJadwal s= new ModelJadwal();       
        s=tbvjadwal.getSelectionModel().getSelectedItem();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
           if(FXMLDocumentController.dtjadwal.delete(s.getId_jadwal())){
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
        tbvjadwal.getScene().getWindow().hide();
    }
    
    public void showdata(){
        ObservableList<ModelJadwal> data=FXMLDocumentController.dtjadwal.Load();
        if(data!=null){            
            show(data);
    }else {Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvjadwal.getScene().getWindow().hide();
        }     
    }
    
    public void show(ObservableList<ModelJadwal> data){
            tbvjadwal.getColumns().clear();            
            tbvjadwal.getItems().clear();
            TableColumn col = new TableColumn("Id Jadwal");
            col.setCellValueFactory(new PropertyValueFactory<ModelJadwal, String>("id_jadwal"));
            tbvjadwal.getColumns().addAll(col);
            
            col=new TableColumn("Nama Studio");
            col.setCellValueFactory(new PropertyValueFactory<ModelJadwal, String>("nama_studio"));
            tbvjadwal.getColumns().addAll(col);
            
            col=new TableColumn("Judul Film");
            col.setCellValueFactory(new PropertyValueFactory<ModelJadwal, String>("judul_film"));
            tbvjadwal.getColumns().addAll(col);
            
            col=new TableColumn("Tanggal");
            col.setCellValueFactory(new PropertyValueFactory<ModelJadwal, String>("tanggal"));
            tbvjadwal.getColumns().addAll(col);
            
            col=new TableColumn("Waktu Mulai");
            col.setCellValueFactory(new PropertyValueFactory<ModelJadwal, String>("waktu_mulai"));
            tbvjadwal.getColumns().addAll(col);
            
            col=new TableColumn("Waktu Selesai");
            col.setCellValueFactory(new PropertyValueFactory<ModelJadwal, String>("waktu_selesai"));
            tbvjadwal.getColumns().addAll(col);
            tbvjadwal.setItems(data);
    }
    
    public void firstclick(){
        tbvjadwal.getSelectionModel().selectFirst();        
        tbvjadwal.requestFocus();
    }
}
