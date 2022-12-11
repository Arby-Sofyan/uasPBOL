/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package uts_2020130041;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Arby Sofyan
 */
public class FXMLDocumentController implements Initializable {


    public static DBFilm dtfilm = new DBFilm();
    public static DBTransaksi dttrans = new DBTransaksi();
    public static DBKursi dtkursi = new DBKursi();
    public static DBCustomer dtcust = new DBCustomer();
    public static DBJadwal dtjadwal = new DBJadwal();
    public static DBStudio dtstudio = new DBStudio();
    public static DBDetilTransaksi dtdetil = new DBDetilTransaksi();
    public static String[] transaksi = new String[5];
    private ArrayList<String> ListKursi = new ArrayList<>();
    @FXML
    private ToggleButton tgla1;
    @FXML
    private ToggleButton tgla2;
    @FXML
    private ToggleButton tgla3;
    @FXML
    private ToggleButton tgla4;
    @FXML
    private ToggleButton tgla5;
    @FXML
    private ToggleButton tgla6;
    @FXML
    private ToggleButton tgla7;
    @FXML
    private ToggleButton tgla8;
    @FXML
    private ToggleButton tglb8;
    @FXML
    private ToggleButton tglb7;
    @FXML
    private ToggleButton tglb6;
    @FXML
    private ToggleButton tglb5;
    @FXML
    private ToggleButton tglb4;
    @FXML
    private ToggleButton tglb3;
    @FXML
    private ToggleButton tglb2;
    @FXML
    private ToggleButton tglb1;
    private ComboBox<String> ComboFilm;
    private ComboBox<String> CBJadwal;
    @FXML
    private Text lnama;
    @FXML
    private Text lnomor;
    @FXML
    private Text lemail;
    @FXML
    private Text lselesai;
    @FXML
    private Text lnamastudio;
    @FXML
    private Text lnamafilm;
    @FXML
    private Text ltanggal;
    @FXML
    private Text lmulai;
    @FXML
    private Text ljadwal;
    @FXML
    private Text lidcust;
    @FXML
    private Text jumlahtiket;
    @FXML
    private Text total;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO


    }
//    daftar Id
//            customer 1
//            transaksi 2
//            kursi 3
//            jadwal 4
    //        studio 5
    //        film 6
    boolean editdata = false;
    public static int harga;
    private void batalklik(ActionEvent event) {
        
    }
    @FXML
    private void pilihcust(ActionEvent event) {
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLPilihCustomer.fxml"));    
        Parent root = (Parent)loader.load();
        FXMLPilihCustomerController isidt=(FXMLPilihCustomerController)loader.getController();
        Scene scene = new Scene(root);
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);
        stg.setIconified(false);
        stg.setScene(scene);
        stg.showAndWait();
        if(isidt.getHasil()==1){
            ModelCustomer d = isidt.getModelCustomer();
            lidcust.setText(d.getId_customer());
            lnama.setText(d.getNama());
            lnomor.setText(d.getNohp());
            lemail.setText(d.getEmail());
            
        }
        } catch (IOException e){   e.printStackTrace();   }
    }

    @FXML
    private void pilihjadwal(ActionEvent event) {
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLPilihJadwal.fxml"));    
        Parent root = (Parent)loader.load();
        FXMLPilihJadwalController isidt=(FXMLPilihJadwalController)loader.getController();
        Scene scene = new Scene(root);
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);
        stg.setIconified(false);
        stg.setScene(scene);
        stg.showAndWait();
        if(isidt.getHasil()==1){
            ModelJadwal d = isidt.getModelJadwal();
            ljadwal.setText(d.getId_jadwal());
            lnamastudio.setText(d.getNama_studio());
            lnamafilm.setText(d.getJudul_film());
            ltanggal.setText(String.valueOf(d.getTanggal()));
            lmulai.setText(String.valueOf(d.getWaktu_mulai()));
            lselesai.setText(String.valueOf(d.getWaktu_selesai()));
            UbahKursi();
        }
        } catch (IOException e){   e.printStackTrace();   }
    }


    
    private void UbahKursi(){
    
        ListKursi = dtkursi.ListKursi(ljadwal.getText());

        if (ListKursi.get(0).equals("true")) {tgla1.setDisable(true);} 
        else if (ListKursi.get(0).equals("false")) {tgla1.setDisable(false);}

        if (ListKursi.get(1).equals("true")) {tgla2.setDisable(true);} 
        else if (ListKursi.get(0).equals("false")) {tgla2.setDisable(false);}

        if (ListKursi.get(2).equals("true")) {tgla3.setDisable(true);} 
        else if (ListKursi.get(0).equals("false")) {tgla3.setDisable(false);}
        
        if (ListKursi.get(3).equals("true")) {tgla4.setDisable(true);} 
        else if (ListKursi.get(0).equals("false")) {tgla4.setDisable(false);}
        
        if (ListKursi.get(4).equals("true")) {tgla5.setDisable(true);} 
        else if (ListKursi.get(0).equals("false")) {tgla5.setDisable(false);}
        
        if (ListKursi.get(5).equals("true")) {tgla6.setDisable(true);} 
        else if (ListKursi.get(0).equals("false")) {tgla6.setDisable(false);}
        
        if (ListKursi.get(6).equals("true")) {tgla7.setDisable(true);} 
        else if (ListKursi.get(0).equals("false")) {tgla7.setDisable(false);}
        
        if (ListKursi.get(7).equals("true")) {tgla8.setDisable(true);} 
        else if (ListKursi.get(0).equals("false")) {tgla8.setDisable(false);}
        
        if (ListKursi.get(8).equals("true")) {tglb1.setDisable(true);} 
        else if (ListKursi.get(0).equals("false")) {tglb1.setDisable(false);}
        
        if (ListKursi.get(9).equals("true")) {tglb2.setDisable(true);} 
        else if (ListKursi.get(0).equals("false")) {tglb2.setDisable(false);}
        
        if (ListKursi.get(10).equals("true")) {tglb3.setDisable(true);} 
        else if (ListKursi.get(0).equals("false")) {tglb3.setDisable(false);}
        
        if (ListKursi.get(11).equals("true")) {tglb4.setDisable(true);} 
        else if (ListKursi.get(0).equals("false")) {tglb4.setDisable(false);}
        
        if (ListKursi.get(12).equals("true")) {tglb5.setDisable(true);} 
        else if (ListKursi.get(0).equals("false")) {tglb5.setDisable(false);}
        
        if (ListKursi.get(13).equals("true")) {tglb6.setDisable(true);} 
        else if (ListKursi.get(0).equals("false")) {tglb6.setDisable(false);}
        
        if (ListKursi.get(14).equals("true")) {tglb7.setDisable(true);} 
        else if (ListKursi.get(0).equals("false")) {tglb7.setDisable(false);}
        
        if (ListKursi.get(15).equals("true")) {tglb8.setDisable(true);} 
        else if (ListKursi.get(0).equals("false")) {tglb8.setDisable(false);}

    }

    private int HitungTiket() {
        int Jumlah = 0;
        if (tgla1.isSelected()) {Jumlah += 1;}
        if (tgla2.isSelected()) {Jumlah += 1;}
        if (tgla3.isSelected()) {Jumlah += 1;}
        if (tgla4.isSelected()) {Jumlah += 1;}
        if (tgla5.isSelected()) {Jumlah += 1;}
        if (tgla6.isSelected()) {Jumlah += 1;}
        if (tgla7.isSelected()) {Jumlah += 1;}
        if (tgla8.isSelected()) {Jumlah += 1;}
        if (tglb1.isSelected()) {Jumlah += 1;}
        if (tglb2.isSelected()) {Jumlah += 1;}
        if (tglb3.isSelected()) {Jumlah += 1;}
        if (tglb4.isSelected()) {Jumlah += 1;}
        if (tglb5.isSelected()) {Jumlah += 1;}
        if (tglb6.isSelected()) {Jumlah += 1;}
        if (tglb7.isSelected()) {Jumlah += 1;}
        if (tglb8.isSelected()) {Jumlah += 1;}
        return Jumlah;
    }
    
    
    
    private void SaveKursi() {
        ArrayList<String> list = new ArrayList<>();

        if (!tgla1.isDisabled()) {
            if (tgla1.isSelected()) {list.add("3"+ljadwal.getText()+"A1");}
        }
        
        if (!tgla2.isDisabled()) {
            if (tgla2.isSelected()) {list.add("3"+ljadwal.getText()+"A2");}
        }
        
        if (!tgla3.isDisabled()) {
            if (tgla3.isSelected()) {list.add("3"+ljadwal.getText()+"A3");}
        }
        
        if (!tgla4.isDisabled()) {
            if (tgla4.isSelected()) {list.add("3"+ljadwal.getText()+"A4");}
        }

        if (!tgla5.isDisabled()) {
            if (tgla5.isSelected()) {list.add("3"+ljadwal.getText()+"A5");}
        }
        
        if (!tgla6.isDisabled()) {
            if (tgla6.isSelected()) {list.add("3"+ljadwal.getText()+"A6");}
        }

        if (!tgla7.isDisabled()) {
            if (tgla7.isSelected()) {list.add("3"+ljadwal.getText()+"A7");}
        }
        
        if (!tgla8.isDisabled()) {
            if (tgla8.isSelected()) {list.add("3"+ljadwal.getText()+"A8");}
        }

        if (!tglb1.isDisabled()) {
            if (tglb1.isSelected()) {list.add("3"+ljadwal.getText()+"B1");}
        }
        
        if (!tglb2.isDisabled()) {
            if (tglb2.isSelected()) {list.add("3"+ljadwal.getText()+"B2");}
        }

        if (!tglb3.isDisabled()) {
            if (tglb3.isSelected()) {list.add("3"+ljadwal.getText()+"B3");}
        }
        
        if (!tglb4.isDisabled()) {
            if (tglb4.isSelected()) {list.add("3"+ljadwal.getText()+"B4");}
        }

        if (!tglb5.isDisabled()) {
            if (tglb5.isSelected()) {list.add("3"+ljadwal.getText()+"B5");}
        }
        
        if (!tglb6.isDisabled()) {
            if (tglb6.isSelected()) {list.add("3"+ljadwal.getText()+"B6");}
        }

        if (!tglb7.isDisabled()) {
            if (tglb7.isSelected()) {list.add("3"+ljadwal.getText()+"B7");}
        }
        
        if (!tglb8.isDisabled()) {
            if (tglb8.isSelected()) {list.add("3"+ljadwal.getText()+"B8");}
        }

        ModelDetilTransaksi dt = new ModelDetilTransaksi();
        dt.setId_transaksi(dttrans.getModelTransaksi().getId_transaksi());
        for (int i = 0; i < list.size(); i++) {
            dtkursi.belikursi(list.get(i));
            dt.setId_kursi(list.get(i));
            dtdetil.setModelDetilTransaksi(dt);
            if(dtdetil.insert()){}
        }
    }

    
    @FXML
    private void HitungKlik(ActionEvent event) {
        jumlahtiket.setText(String.valueOf(HitungTiket()));
        harga = HitungTiket() * 30000;
        
       total.setText(String.valueOf(harga));
    }


    @FXML
    private void Pesan(ActionEvent event) {
        ModelTransaksi n = new ModelTransaksi();
        
        if(lidcust.getText()!="" &&  ljadwal.getText()!="" && jumlahtiket.getText()!="0"){
        n.setId_jadwal(ljadwal.getText());
        n.setId_customer(lidcust.getText());
        n.setJumlah_tiket(Integer.valueOf(jumlahtiket.getText()));
        n.setTotal_bayar(Integer.valueOf(total.getText()));
        
        ArrayList<String> List =FXMLDocumentController.dttrans.ListID();
        String urutan;
        if(!List.isEmpty()){
           int urut = Integer.valueOf(List.get(List.size()-1).substring(1)) + 1;
           if(urut<100){urutan = "00"+String.valueOf(urut);}
           else if(urut<100){urutan = "0"+String.valueOf(urut);}
           else {urutan = String.valueOf(urut);}
       } else {
           urutan ="001";
       }
        n.setId_transaksi("2"+urutan);
        }
        
        if(FXMLDocumentController.dttrans.validasi(n.getId_transaksi())<=0){
            FXMLDocumentController.dttrans.setModelTransaksi(n);
            if(FXMLDocumentController.dttrans.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Transaksi Berhasil Disimpan",ButtonType.OK);
               SaveKursi();
               a.showAndWait();
               transaksi[0] = lnama.getText();
               transaksi[1] = lnamafilm.getText();
               transaksi[2] = lnamastudio.getText();
               transaksi[3] = jumlahtiket.getText();
               transaksi[4] = total.getText();
               try{  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLDisplayTiket.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){   e.printStackTrace();   }
               batal(event);
               
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
        }
    }

    @FXML
    private void batal(ActionEvent event) {
        lidcust.setText("");
        lnama.setText("");
        lnomor.setText("");
        lemail.setText("");
        
        ljadwal.setText("");
        
        lnamastudio.setText("");
        lnamafilm.setText("");
        ltanggal.setText("");
        lmulai.setText("");
        lselesai.setText("");
        
        jumlahtiket.setText("");
        total.setText("");
        
        tgla1.setDisable(false);
        tgla2.setDisable(false);
        tgla3.setDisable(false);
        tgla4.setDisable(false);
        tgla5.setDisable(false);
        tgla6.setDisable(false);
        tgla7.setDisable(false);
        tgla8.setDisable(false);
        
        tglb1.setDisable(false);
        tglb2.setDisable(false);
        tglb3.setDisable(false);
        tglb4.setDisable(false);
        tglb5.setDisable(false);
        tglb6.setDisable(false);
        tglb7.setDisable(false);
        tglb8.setDisable(false);
        
        tgla1.setSelected(false);
        tgla2.setSelected(false);
        tgla3.setSelected(false);
        tgla4.setSelected(false);
        tgla5.setSelected(false);
        tgla6.setSelected(false);
        tgla7.setSelected(false);
        tgla8.setSelected(false);
        
        tglb1.setSelected(false);
        tglb2.setSelected(false);
        tglb3.setSelected(false);
        tglb4.setSelected(false);
        tglb5.setSelected(false);
        tglb6.setSelected(false);
        tglb7.setSelected(false);
        tglb8.setSelected(false);
        
    }

    
}
