package project;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author busra
 */
public class MusterilerController implements Initializable {

    @FXML
    private TableView<?> musteri_tablo;
    @FXML
    private TextField musteri_id;
    @FXML
    private TextField firmaadı;
    @FXML
    private TextField il;
    @FXML
    private TextField ilce;
    @FXML
    private Button kaydetbt;
    @FXML
    private Button yenibt;
    @FXML
    private Button duzenlebt;
    @FXML
    private Button silbt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
