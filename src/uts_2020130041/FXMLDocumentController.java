/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package uts_2020130041;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Arby Sofyan
 */
public class FXMLDocumentController implements Initializable {

    String[] ListJudul = {"Pamali", "Miracle in Cell No.7", "End Game"};
    String[] ListJadwal = {"401", "402", "403"};

    public static DBFilm dtfilm = new DBFilm();
    public static DBTransaksi dttrans = new DBTransaksi();
    public static DBKursi dtkursi = new DBKursi();

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
    @FXML
    private TextField txtnama;
    @FXML
    private TextField txthp;
    @FXML
    private TextField txtemail;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnpesan;
    @FXML
    private TextField txtjumlahtiket;
    @FXML
    private Button btnlihatmaster;
    @FXML
    private TextField txtJamTayang;
    @FXML
    private TextField txtJamSelesai;
    @FXML
    private ComboBox<String> ComboFilm;
    @FXML
    private ComboBox<String> CBJadwal;
    @FXML
    private TextField txtharga;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        dtfilm.Load();
        ComboFilm.getItems().addAll(ListJudul);
        CBJadwal.getItems().addAll(ListJadwal);

    }
//    diaftar Id
//            customer 1
//            transaksi 2
//            kursi 3
//            jadwal 4
    boolean editdata = false;
    public static int harga;
//    public void execute(Jadwal_FilmModel d) {
//        if (!d.getNPM().isEmpty()) {
//            editdata = true;
//            txtnpm.setText(d.getNPM());
//            txtnama.setText(d.getNama());
//            txtalamat.setText(d.getAlamat());
//            txtnpm.setEditable(false);
//            txtnama.requestFocus();
//        }
//    }

    @FXML
    private void batalklik(ActionEvent event) {
    }

    @FXML
    private void pesanklik(ActionEvent event) {
        TransaksiModel s = new TransaksiModel();
        s.setId_Customer(txtnama.getText());
        s.setJudul_Film(ComboFilm.getSelectionModel().getSelectedItem());
        int jumlah = HitungTiket();
        s.setJam_Mulai(null);
        s.setJam_Selesai(null);
        s.setJumlah_Tiket(Integer.valueOf(jumlah));
        s.setHarga_Bayar(0);
        s.setId_Jadwal(CBJadwal.getSelectionModel().getSelectedItem());
        ArrayList<String> List = dttrans.CheckKode();
        String urutan;
        if (!List.isEmpty()) {
            int urut = Integer.valueOf(List.get(List.size() - 1).substring(1)) + 1;
            if (urut < 10) {
                urutan = "0" + String.valueOf(urut);
            } else {
                urutan = String.valueOf(urut);
            }
        } else {
            urutan = "01";
        }
        s.setId_Transaksi("2" + urutan);

        dttrans.setTransaksiModel(s);

        if (dttrans.validasi(s.getId_Transaksi()) <= 0) {
            if (dttrans.insert()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil disimpan", ButtonType.OK);
                a.showAndWait();
                batalklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal disimpan", ButtonType.OK);
                a.showAndWait();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data sudah ada", ButtonType.OK);
            a.showAndWait();
            txtnama.requestFocus();
        }

        SaveKursi();

    }

    @FXML
    private void lihatmasterklik(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_DisplayTransaksi.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();
            CBJadwal.getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void UbahKursi(ActionEvent event) {
        ListKursi = dtkursi.CheckKursi(CBJadwal.getSelectionModel().getSelectedItem());

        if (ListKursi.get(0).equals("true")) {
            tgla1.setDisable(true);
        } else if (ListKursi.get(0).equals("false")) {
            tgla1.setDisable(false);
        }

        if (ListKursi.get(1).equals("true")) {
            tgla2.setDisable(true);
        } else if (ListKursi.get(0).equals("false")) {
            tgla2.setDisable(false);
        }

        if (ListKursi.get(2).equals("true")) {
            tgla3.setDisable(true);
        } else if (ListKursi.get(0).equals("false")) {
            tgla3.setDisable(false);
        }
        if (ListKursi.get(3).equals("true")) {
            tgla4.setDisable(true);
        } else if (ListKursi.get(0).equals("false")) {
            tgla4.setDisable(false);
        }
        if (ListKursi.get(4).equals("true")) {
            tgla5.setDisable(true);
        } else if (ListKursi.get(0).equals("false")) {
            tgla5.setDisable(false);
        }
        if (ListKursi.get(5).equals("true")) {
            tgla6.setDisable(true);
        } else if (ListKursi.get(0).equals("false")) {
            tgla6.setDisable(false);
        }
        if (ListKursi.get(6).equals("true")) {
            tgla7.setDisable(true);
        } else if (ListKursi.get(0).equals("false")) {
            tgla7.setDisable(false);
        }
        if (ListKursi.get(7).equals("true")) {
            tgla8.setDisable(true);
        } else if (ListKursi.get(0).equals("false")) {
            tgla8.setDisable(false);
        }
        if (ListKursi.get(8).equals("true")) {
            tglb1.setDisable(true);
        } else if (ListKursi.get(0).equals("false")) {
            tglb1.setDisable(false);
        }
        if (ListKursi.get(9).equals("true")) {
            tglb2.setDisable(true);
        } else if (ListKursi.get(0).equals("false")) {
            tglb2.setDisable(false);
        }
        if (ListKursi.get(10).equals("true")) {
            tglb3.setDisable(true);
        } else if (ListKursi.get(0).equals("false")) {
            tglb3.setDisable(false);
        }
        if (ListKursi.get(11).equals("true")) {
            tglb4.setDisable(true);
        } else if (ListKursi.get(0).equals("false")) {
            tglb4.setDisable(false);
        }
        if (ListKursi.get(12).equals("true")) {
            tglb5.setDisable(true);
        } else if (ListKursi.get(0).equals("false")) {
            tglb5.setDisable(false);
        }
        if (ListKursi.get(13).equals("true")) {
            tglb6.setDisable(true);
        } else if (ListKursi.get(0).equals("false")) {
            tglb6.setDisable(false);
        }
        if (ListKursi.get(14).equals("true")) {
            tglb7.setDisable(true);
        } else if (ListKursi.get(0).equals("false")) {
            tglb7.setDisable(false);
        }
        if (ListKursi.get(15).equals("true")) {
            tglb8.setDisable(true);
        } else if (ListKursi.get(0).equals("false")) {
            tglb8.setDisable(false);
        }

    }

    private int HitungTiket() {
        int Jumlah = 0;
        if (tgla1.isSelected()) {
            Jumlah += 1;
        }
        if (tgla2.isSelected()) {
            Jumlah += 1;
        }
        if (tgla3.isSelected()) {
            Jumlah += 1;
        }
        if (tgla4.isSelected()) {
            Jumlah += 1;
        }
        if (tgla5.isSelected()) {
            Jumlah += 1;
        }
        if (tgla6.isSelected()) {
            Jumlah += 1;
        }
        if (tgla7.isSelected()) {
            Jumlah += 1;
        }
        if (tgla8.isSelected()) {
            Jumlah += 1;
        }
        if (tglb1.isSelected()) {
            Jumlah += 1;
        }
        if (tglb2.isSelected()) {
            Jumlah += 1;
        }
        if (tglb3.isSelected()) {
            Jumlah += 1;
        }
        if (tglb4.isSelected()) {
            Jumlah += 1;
        }
        if (tglb5.isSelected()) {
            Jumlah += 1;
        }
        if (tglb6.isSelected()) {
            Jumlah += 1;
        }
        if (tglb7.isSelected()) {
            Jumlah += 1;
        }
        if (tglb8.isSelected()) {
            Jumlah += 1;
        }
        return Jumlah;
    }

    private void SaveKursi() {
        ArrayList<Detail_KursiModel> list = new ArrayList<>();

        if (!tgla1.isDisabled()) {
            if (tgla1.isSelected()) {
                Detail_KursiModel s = new Detail_KursiModel();
                s.setId_Jadwal(CBJadwal.getSelectionModel().getSelectedItem());
                s.setId_Kursi("301");
                s.setStatus("true");
                list.add(s);
            }
        }
        if (!tgla2.isDisabled()) {
            if (tgla2.isSelected()) {
                Detail_KursiModel s = new Detail_KursiModel();
                s.setId_Jadwal(CBJadwal.getSelectionModel().getSelectedItem());
                s.setId_Kursi("302");
                s.setStatus("true");
                list.add(s);
            }
        }

        if (!tgla3.isDisabled()) {
            if (tgla3.isSelected()) {
                Detail_KursiModel s = new Detail_KursiModel();
                s.setId_Jadwal(CBJadwal.getSelectionModel().getSelectedItem());
                s.setId_Kursi("303");
                s.setStatus("true");
                list.add(s);
            }
        }
        if (!tgla4.isDisabled()) {
            if (tgla4.isSelected()) {
                Detail_KursiModel s = new Detail_KursiModel();
                s.setId_Jadwal(CBJadwal.getSelectionModel().getSelectedItem());
                s.setId_Kursi("304");
                s.setStatus("true");
                list.add(s);
            }
        }

        if (!tgla5.isDisabled()) {
            if (tgla5.isSelected()) {
                Detail_KursiModel s = new Detail_KursiModel();
                s.setId_Jadwal(CBJadwal.getSelectionModel().getSelectedItem());
                s.setId_Kursi("305");
                s.setStatus("true");
                list.add(s);
            }
        }
        if (!tgla6.isDisabled()) {
            if (tgla6.isSelected()) {
                Detail_KursiModel s = new Detail_KursiModel();
                s.setId_Jadwal(CBJadwal.getSelectionModel().getSelectedItem());
                s.setId_Kursi("306");
                s.setStatus("true");
                list.add(s);
            }
        }

        if (!tgla7.isDisabled()) {
            if (tgla7.isSelected()) {
                Detail_KursiModel s = new Detail_KursiModel();
                s.setId_Jadwal(CBJadwal.getSelectionModel().getSelectedItem());
                s.setId_Kursi("307");
                s.setStatus("true");
                list.add(s);
            }
        }
        if (!tgla8.isDisabled()) {
            if (tgla8.isSelected()) {
                Detail_KursiModel s = new Detail_KursiModel();
                s.setId_Jadwal(CBJadwal.getSelectionModel().getSelectedItem());
                s.setId_Kursi("308");
                s.setStatus("true");
                list.add(s);
            }
        }

        if (!tglb1.isDisabled()) {
            if (tglb1.isSelected()) {
                Detail_KursiModel s = new Detail_KursiModel();
                s.setId_Jadwal(CBJadwal.getSelectionModel().getSelectedItem());
                s.setId_Kursi("309");
                s.setStatus("true");
                list.add(s);
            }
        }
        if (!tglb2.isDisabled()) {
            if (tglb2.isSelected()) {
                Detail_KursiModel s = new Detail_KursiModel();
                s.setId_Jadwal(CBJadwal.getSelectionModel().getSelectedItem());
                s.setId_Kursi("310");
                s.setStatus("true");
                list.add(s);
            }
        }

        if (!tglb3.isDisabled()) {
            if (tglb3.isSelected()) {
                Detail_KursiModel s = new Detail_KursiModel();
                s.setId_Jadwal(CBJadwal.getSelectionModel().getSelectedItem());
                s.setId_Kursi("311");
                s.setStatus("true");
                list.add(s);
            }
        }
        if (!tglb4.isDisabled()) {
            if (tglb4.isSelected()) {
                Detail_KursiModel s = new Detail_KursiModel();
                s.setId_Jadwal(CBJadwal.getSelectionModel().getSelectedItem());
                s.setId_Kursi("312");
                s.setStatus("true");
                list.add(s);
            }
        }

        if (!tglb5.isDisabled()) {
            if (tglb5.isSelected()) {
                Detail_KursiModel s = new Detail_KursiModel();
                s.setId_Jadwal(CBJadwal.getSelectionModel().getSelectedItem());
                s.setId_Kursi("313");
                s.setStatus("true");
                list.add(s);
            }
        }
        if (!tglb6.isDisabled()) {
            if (tglb6.isSelected()) {
                Detail_KursiModel s = new Detail_KursiModel();
                s.setId_Jadwal(CBJadwal.getSelectionModel().getSelectedItem());
                s.setId_Kursi("314");
                s.setStatus("true");
                list.add(s);
            }
        }

        if (!tglb7.isDisabled()) {
            if (tglb7.isSelected()) {
                Detail_KursiModel s = new Detail_KursiModel();
                s.setId_Jadwal(CBJadwal.getSelectionModel().getSelectedItem());
                s.setId_Kursi("315");
                s.setStatus("true");
                list.add(s);
            }
        }
        if (!tglb8.isDisabled()) {
            if (tglb8.isSelected()) {
                Detail_KursiModel s = new Detail_KursiModel();
                s.setId_Jadwal(CBJadwal.getSelectionModel().getSelectedItem());
                s.setId_Kursi("316");
                s.setStatus("true");
                list.add(s);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            dtkursi.setDetail_KursiModel(list.get(i));
            if (dtkursi.update()) {
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal diubah", ButtonType.OK);
                a.showAndWait();
            }
        }

    }

    @FXML
    private void HitungKlik(ActionEvent event) {
        txtjumlahtiket.setText(String.valueOf(HitungTiket()));
        harga = HitungTiket() * 30000;
        
       txtharga.setText(String.valueOf(harga));
    }

}
