/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts_2020130041;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Arby Sofyan
 */
public class FXMLTambahJadwalController implements Initializable {

    public boolean editdata= false;
    
    @FXML
    private Text lidstudio;
    @FXML
    private Text lnamastudio;
    @FXML
    private Text lgenre;
    @FXML
    private Text ljudulfilm;
    @FXML
    private Text lidfilm;
    @FXML
    private DatePicker tanggal;
    @FXML
    private TextField mulai;
    @FXML
    private TextField selesai;

    /**
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void tambah(ActionEvent event) {
        ModelJadwal n = new ModelJadwal();
        if(lidstudio.getText()!="" &&  lidfilm.getText()!=""){
        n.setId_studio(lidstudio.getText());
        n.setNama_studio(lnamastudio.getText());
        n.setId_film(lidfilm.getText());
        n.setJudul_film(ljudulfilm.getText());
        n.setTanggal(Date.valueOf(tanggal.getValue()));
        n.setWaktu_mulai(Time.valueOf(mulai.getText()));
        n.setWaktu_selesai(Time.valueOf(selesai.getText()));
        ArrayList<String> List =FXMLDocumentController.dtjadwal.ListID();
        String urutan;
        if(!List.isEmpty()){
           int urut = Integer.valueOf(List.get(List.size()-1).substring(1)) + 1;
           if(urut<100){urutan = "00"+String.valueOf(urut);}
           else if(urut<100){urutan = "0"+String.valueOf(urut);}
           else {urutan = String.valueOf(urut);}
       } else {
           urutan ="001";
       }
        n.setId_jadwal("4"+urutan);
        }
        
        
        if(editdata){
            n.setId_jadwal(FXMLDocumentController.dtjadwal.getModelJadwal().getId_jadwal());
            FXMLDocumentController.dtjadwal.setModelJadwal(n);
            if(FXMLDocumentController.dtjadwal.update()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();   
               mulai.getScene().getWindow().hide();
            } else {               
                Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
                a.showAndWait(); 
            }
        }   else if(FXMLDocumentController.dtjadwal.validasi(n.getId_jadwal())<=0){
            
            FXMLDocumentController.dtjadwal.setModelJadwal(n);
            if(FXMLDocumentController.dtjadwal.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan",ButtonType.OK);
               a.showAndWait();            
               batal(event);
                    ModelKursi k = new ModelKursi();
                    k.setId_jadwal(n.getId_jadwal());
                    k.setId_studio(n.getId_studio());
                    k.setStatus_kursi("false");
               for(int i=1; i<=8; i++){
                    k.setNomor_kursi("A"+i);
                    k.setId_kursi("3"+k.getId_jadwal()+k.getNomor_kursi());
                    FXMLDocumentController.dtkursi.setModelKursi(k);
                    if(FXMLDocumentController.dtkursi.insert()){}
                    k.setNomor_kursi("B"+i);
                    k.setId_kursi("3"+k.getId_jadwal()+k.getNomor_kursi());
                    FXMLDocumentController.dtkursi.setModelKursi(k);
                    if(FXMLDocumentController.dtkursi.insert()){}
        }
               
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
        }
    }

    @FXML
    private void batal(ActionEvent event) {
        lidfilm.setText("");
        lidstudio.setText("");
        ljudulfilm.setText("");
        lnamastudio.setText("");
        lgenre.setText("");
        mulai.setText("");
        selesai.setText("");
        
    }

    @FXML
    private void Studio(ActionEvent event) {
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLPilihStudio.fxml"));    
        Parent root = (Parent)loader.load();
        FXMLPilihStudioController isidt=(FXMLPilihStudioController)loader.getController();
        Scene scene = new Scene(root);
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);
        stg.setIconified(false);
        stg.setScene(scene);
        stg.showAndWait();
        if(isidt.getHasil()==1){
            ModelStudio d = isidt.getModelStudio();
            lidstudio.setText(d.getId_studio());
            lnamastudio.setText(d.getNama_studio());
        }
        } catch (IOException e){   e.printStackTrace();   }
        
    }

    @FXML
    private void Film(ActionEvent event) {
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLPilihFilm.fxml"));    
        Parent root = (Parent)loader.load();
        FXMLPilihFilmController isidt=(FXMLPilihFilmController)loader.getController();
        Scene scene = new Scene(root);
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);
        stg.setIconified(false);
        stg.setScene(scene);
        stg.showAndWait();
        if(isidt.getHasil()==1){
            ModelFilm d = isidt.getModelFilm();
            lidfilm.setText(d.getId_film());
            ljudulfilm.setText(d.getJudul_film());
            lgenre.setText(d.getGenre());
        }
        } catch (IOException e){   e.printStackTrace();   }
        
    }
    
    public void execute(ModelJadwal d){
        if(!d.getId_jadwal().isEmpty()){
        editdata= true;
        lidfilm.setText(d.getId_film());
        lidstudio.setText(d.getId_studio());
        ljudulfilm.setText(d.getJudul_film());
        lnamastudio.setText(d.getNama_studio());
        lgenre.setText(d.getGenre());
        tanggal.setValue(d.getTanggal().toLocalDate());
        mulai.setText(String.valueOf(d.getWaktu_mulai()));
        selesai.setText(String.valueOf(d.getWaktu_selesai()));
        FXMLDocumentController.dtjadwal.setModelJadwal(d);
        }
    }
    
}
