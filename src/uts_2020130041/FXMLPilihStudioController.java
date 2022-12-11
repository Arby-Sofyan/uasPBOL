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
public class FXMLPilihStudioController implements Initializable {

    private int hasil=0;
    private ModelStudio studio = new ModelStudio();
    
    public int getHasil(){return(hasil);}
    public ModelStudio getModelStudio(){return(studio);}
    
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnpilih;
    @FXML
    private TableView<ModelStudio> tbvstudio;
    @FXML
    private Label idstudio;
    @FXML
    private Label namastudio;
    @FXML
    private TextField txtcari;
    @FXML
    private Label jumlahkursi;

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
        studio = tbvstudio.getSelectionModel().getSelectedItem();
        btnpilih.getScene().getWindow().hide();
    }

    @FXML
    private void PilihID(MouseEvent event) {
        idstudio.setText(tbvstudio.getSelectionModel().getSelectedItem().getId_studio());
        namastudio.setText(tbvstudio.getSelectionModel().getSelectedItem().getNama_studio());
        jumlahkursi.setText(String.valueOf(tbvstudio.getSelectionModel().getSelectedItem().getJumlah_kursi()));
    }

    @FXML
    private void Cari(KeyEvent event) {
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
            col.setCellValueFactory(new PropertyValueFactory<ModelCustomer, String>("id_studio"));
            tbvstudio.getColumns().addAll(col);
            
            col=new TableColumn("Nama Studio");
            col.setCellValueFactory(new PropertyValueFactory<ModelCustomer, String>("nama_studio"));
            tbvstudio.getColumns().addAll(col);
            
            col=new TableColumn("Jumlah Kursi");
            col.setCellValueFactory(new PropertyValueFactory<ModelCustomer, String>("jumlah_kursi"));
            tbvstudio.getColumns().addAll(col);
            
            tbvstudio.setItems(data);
    }
    
    public void firstclick(){
        tbvstudio.getSelectionModel().selectFirst();        
        tbvstudio.requestFocus();
    }
}
