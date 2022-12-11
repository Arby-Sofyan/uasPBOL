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
public class FXMLPilihJadwalController implements Initializable {

    private int hasil=0;
    private ModelJadwal jadwal = new ModelJadwal();
    
    public int getHasil(){return(hasil);}
    public ModelJadwal getModelJadwal(){return(jadwal);}
    
    
    @FXML
    private TextField txtcari;
    @FXML
    private Label namafilm;
    @FXML
    private Label idjadwal;
    @FXML
    private TableView<ModelJadwal> tbvjadwal;
    @FXML
    private Button btnpilih;
    @FXML
    private Button btnbatal;
    @FXML
    private Label namastudio;
    @FXML
    private Label tanggal;
    @FXML
    private Label waktumulai;
    @FXML
    private Label waktuselesai;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showdata();
    }    

    @FXML
    private void Cari(KeyEvent event) {
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
    private void PilihID(MouseEvent event) {
        idjadwal.setText(tbvjadwal.getSelectionModel().getSelectedItem().getId_jadwal());
        namastudio.setText(tbvjadwal.getSelectionModel().getSelectedItem().getNama_studio());
        namafilm.setText(tbvjadwal.getSelectionModel().getSelectedItem().getJudul_film());
        tanggal.setText(String.valueOf(tbvjadwal.getSelectionModel().getSelectedItem().getTanggal()));
        waktumulai.setText(String.valueOf(tbvjadwal.getSelectionModel().getSelectedItem().getWaktu_mulai()));
        waktuselesai.setText(String.valueOf(tbvjadwal.getSelectionModel().getSelectedItem().getWaktu_selesai()));
                
    }

    @FXML
    private void pilihklik(ActionEvent event) {
        hasil=1;
        int pilihan=tbvjadwal.getSelectionModel().getSelectedCells().get(0).getRow();
        jadwal = tbvjadwal.getSelectionModel().getSelectedItem();
        btnpilih.getScene().getWindow().hide();
    }

    @FXML
    private void batalklik(ActionEvent event) {
        hasil=0;
        btnbatal.getScene().getWindow().hide();
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
