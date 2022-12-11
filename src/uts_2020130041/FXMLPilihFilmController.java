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
public class FXMLPilihFilmController implements Initializable {

    
    private int hasil=0;
    private ModelFilm film = new ModelFilm();
    
    public int getHasil(){return(hasil);}
    public ModelFilm getModelFilm(){return(film);}
    
    @FXML
    private Label genre;
    @FXML
    private TextField txtcari;
    @FXML
    private Label namafilm;
    @FXML
    private Label idfilm;
    @FXML
    private TableView<ModelFilm> tbvfilm;
    @FXML
    private Button btnpilih;
    @FXML
    private Button btnbatal;

    /**
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showdata();
        firstclick();
    }    

    @FXML
    private void Cari(KeyEvent event) {
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
    private void PilihID(MouseEvent event) {
        idfilm.setText(tbvfilm.getSelectionModel().getSelectedItem().getId_film());
        namafilm.setText(tbvfilm.getSelectionModel().getSelectedItem().getJudul_film());
        genre.setText(String.valueOf(tbvfilm.getSelectionModel().getSelectedItem().getGenre()));
    }

    @FXML
    private void pilihklik(ActionEvent event) {
        hasil=1;
        film = tbvfilm.getSelectionModel().getSelectedItem();
        btnpilih.getScene().getWindow().hide();
    }

    @FXML
    private void batalklik(ActionEvent event) {
        hasil=0;
        btnbatal.getScene().getWindow().hide();
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
            col.setCellValueFactory(new PropertyValueFactory<ModelCustomer, String>("id_film"));
            tbvfilm.getColumns().addAll(col);
            
            col=new TableColumn("Nama Film");
            col.setCellValueFactory(new PropertyValueFactory<ModelCustomer, String>("judul_film"));
            tbvfilm.getColumns().addAll(col);
            
            col=new TableColumn("Genre");
            col.setCellValueFactory(new PropertyValueFactory<ModelCustomer, String>("genre"));
            tbvfilm.getColumns().addAll(col);
            
            tbvfilm.setItems(data);
    }
    
    public void firstclick(){
        tbvfilm.getSelectionModel().selectFirst();        
        tbvfilm.requestFocus();
    }
    
}
