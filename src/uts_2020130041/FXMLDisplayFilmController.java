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
public class FXMLDisplayFilmController implements Initializable {

    @FXML
    private TextField txtcari;
    @FXML
    private TableView<ModelFilm> tbvfilm;

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
        ModelFilm s = new ModelFilm();
        String key = txtcari.getText();
        if(key!=""){
        ObservableList<ModelFilm> data=FXMLDocumentController.dtfilm.LookUp("judul_film", key);
        if(data!=null){            
            show(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvfilm.getScene().getWindow().hide();;
        }            
            } else{
               showdata();
            } 
    }

    @FXML
    private void tambahklik(ActionEvent event) {
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLTambahFilm.fxml"));   
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
        ModelFilm s= new ModelFilm();
        s=tbvfilm.getSelectionModel().getSelectedItem();
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLTambahFilm.fxml"));    
        Parent root = (Parent)loader.load();
        FXMLTambahFilmController isidt=(FXMLTambahFilmController)loader.getController();
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
        ModelFilm s= new ModelFilm();       
        s=tbvfilm.getSelectionModel().getSelectedItem();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
           if(FXMLDocumentController.dtfilm.delete(s.getId_film())){
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
        tbvfilm.getScene().getWindow().hide();
    }
    
    public void showdata(){
        ObservableList<ModelFilm> data=FXMLDocumentController.dtfilm.Load();
        if(data!=null){            
            show(data);
    }else {Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvfilm.getScene().getWindow().hide();
        }     
    }
    
    public void show(ObservableList<ModelFilm> data){
            tbvfilm.getColumns().clear();            
            tbvfilm.getItems().clear();
            TableColumn col = new TableColumn("Id Film");
            col.setCellValueFactory(new PropertyValueFactory<ModelFilm, String>("id_film"));
            tbvfilm.getColumns().addAll(col);
            
            col=new TableColumn("Judul Film");
            col.setCellValueFactory(new PropertyValueFactory<ModelFilm, String>("judul_film"));
            tbvfilm.getColumns().addAll(col);
            
            col=new TableColumn("Genre");
            col.setCellValueFactory(new PropertyValueFactory<ModelFilm, String>("genre"));
            tbvfilm.getColumns().addAll(col);
            tbvfilm.setItems(data);
    }
    
    public void firstclick(){
        tbvfilm.getSelectionModel().selectFirst();        
        tbvfilm.requestFocus();
    }
    
}
