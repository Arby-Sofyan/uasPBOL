/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Arby Sofyan
 */
public class FXML_DisplayTransaksiController implements Initializable {

    @FXML
    private TableView<TransaksiModel> tbvtrans;
    @FXML
    private Button btnawal;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btnsesudah;
    @FXML
    private Button btnakhir;
    @FXML
    private Button btnkeluar;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btntambah;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showdata();
    }    

    private void showdata() {
        ObservableList<TransaksiModel> data = FXMLDocumentController.dttrans.Load();
        
        if (data != null) {
            tbvtrans.getColumns().clear();
            tbvtrans.getItems().clear();
            TableColumn col = new TableColumn("Id Transaksi");
            col.setCellValueFactory(new PropertyValueFactory<TransaksiModel, String>("Id_Transaksi"));
            tbvtrans.getColumns().addAll(col);

            col = new TableColumn("Id Customer");
            col.setCellValueFactory(new PropertyValueFactory<TransaksiModel, String>("Id_Customer"));
            tbvtrans.getColumns().addAll(col);

            col = new TableColumn("Judul Film");
            col.setCellValueFactory(new PropertyValueFactory<TransaksiModel, String>("Judul_Film"));
            tbvtrans.getColumns().addAll(col);
            
            col = new TableColumn("Jam Mulai");
            col.setCellValueFactory(new PropertyValueFactory<TransaksiModel, String>("Jam_Mulai"));
            tbvtrans.getColumns().addAll(col);

            col = new TableColumn("Jam Selesai");
            col.setCellValueFactory(new PropertyValueFactory<TransaksiModel, String>("Jam_Selesai"));
            tbvtrans.getColumns().addAll(col);
            
            col = new TableColumn("Jumlah Tiket");
            col.setCellValueFactory(new PropertyValueFactory<TransaksiModel, String>("Jumlah_Tiket"));
            tbvtrans.getColumns().addAll(col);

//            col = new TableColumn("Harga Bayar");
//            col.setCellValueFactory(new PropertyValueFactory<TransaksiModel, String>("Harga_Bayar"));
//            tbvtrans.getColumns().addAll(col);
            tbvtrans.setItems(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbvtrans.getScene().getWindow().hide();;
        }
    }

    @FXML
    private void awalklik(ActionEvent event) {
        tbvtrans.getSelectionModel().selectFirst();
        tbvtrans.requestFocus();
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
        tbvtrans.getSelectionModel().selectAboveCell();
        tbvtrans.requestFocus();
    }

    @FXML
    private void sesudahklik(ActionEvent event) {
        tbvtrans.getSelectionModel().selectBelowCell();
        tbvtrans.requestFocus();
    }

    @FXML
    private void akhirklik(ActionEvent event) {
        tbvtrans.getSelectionModel().selectLast();
        tbvtrans.requestFocus();
    }

    @FXML
    private void keluarklik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }


    @FXML
    private void hapusklik(ActionEvent event) {
        TransaksiModel s = new TransaksiModel();
        s = tbvtrans.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Mau dihapus?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            if (FXMLDocumentController.dttrans.delete(s.getId_Transaksi())) {
                Alert b = new Alert(Alert.AlertType.INFORMATION, "Data berhasil dihapus", ButtonType.OK);
                b.showAndWait();
            } else {
                Alert b = new Alert(Alert.AlertType.ERROR, "Data gagal dihapus", ButtonType.OK);
                b.showAndWait();
            }
            showdata();
            awalklik(event);
        }
    }

    @FXML
    private void tambahklik(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
            btnkeluar.getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showdata();
        awalklik(event);

    }
    
    
}
