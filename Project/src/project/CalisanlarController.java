    package project;


    import java.util.Collection;
    import javafx.scene.control.ChoiceBox;
    import project.CalisanlarController;
    import java.sql.*;
    import project.DBConnection;
    import java.sql.Connection;
    import java.util.Map;
    import java.net.URL;
    import java.sql.PreparedStatement;
    import java.util.ResourceBundle;
    import javafx.scene.control.TextField;
    import java.util.logging.Level;
    import java.util.logging.Logger;
    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.fxml.Initializable;
    import javafx.scene.control.Alert;
    import javafx.scene.control.Button;
    import javafx.scene.control.TableColumn;
    import javafx.scene.control.TableView;
    import javafx.scene.input.MouseEvent;
    import javafx.scene.layout.AnchorPane;




    public class CalisanlarController implements Initializable {
    ObservableList<String> levelList = FXCollections.observableArrayList("Level 1", "Level 2", "Level 3" );

      @FXML
      private ChoiceBox<String> level;

      @FXML
      private AnchorPane personel;


        Connection conn;
        @FXML
        private TextField txt_id;
        @FXML
        private TextField txt_ad;
        @FXML
        private TextField txt_soyad;
        @FXML
        private Button kaydetb;
        @FXML
        private Button yenib;
        @FXML
        private Button duzenleb;
        @FXML
        private Button silb;
        @FXML
        private TableView<Calisanlar> tablo_personel;


        private FXMLLoader loader;
        private String query,id, firstname, lastname, seviye;
        DataAccesObject dao;
        private DBConnection database;
        private Connection connect;
        private static boolean EDIT=false, ADD=true;

        @FXML
        private TableColumn<Calisanlar,String> idsutun;
        @FXML
        private TableColumn<Calisanlar, String> adsutun;
        @FXML
        private TableColumn<Calisanlar,String> soyadsutun;
        @FXML
        private TableColumn<Calisanlar,String> seviyesutun;



      public void initialize(URL url, ResourceBundle rb) {


            dao = new DataAccesObject(); 
        loadData();


                    duzenleb.setOnAction(e->{
                            ADD = false;
                            EDIT = true;
                            editAccount();
                             refreshTable();
                    });


                    silb.setOnAction(e->{
                            deleteAccount();
                    });
                 //   initTable();
       refreshTable();
      }

      private void loadData() {
        this.levelList.removeAll((Collection)this.levelList);
        String a = "Level 1";
        String b = "Level 2";
        String c = "Level 3";
        this.levelList.addAll( a, b, c );
        this.level.getItems().addAll((Collection)this.levelList);
      }

     private void initTable() {
                    idsutun.setCellValueFactory(cell->cell.getValue().getpID());
                    adsutun.setCellValueFactory(cell->cell.getValue().getpname());
                    soyadsutun.setCellValueFactory(cell->cell.getValue().getpLastname());
                    seviyesutun.setCellValueFactory(cell->cell.getValue().getpSeviye());
                    } 
    private void refreshTable() {
                    initTable();
                    query = "SELECT * FROM personel";
                    tablo_personel.setItems(dao.getAccountsData(query));
                    } 

    private void deleteAccount() {
                    Calisanlar selected = tablo_personel.getSelectionModel().getSelectedItem();
                    id = selected.getpID().get();
                    query = "DELETE FROM personel WHERE id="+id+"";
                    dao.saveData(query);
                    refreshTable();
            }

            private void editAccount() { 
                    Calisanlar selected = tablo_personel.getSelectionModel().getSelectedItem();
                    //id = selected.getpID().get();
                   txt_id.setText(selected.getpID().get());
                    txt_ad.setText(selected.getpname().get());
                    txt_soyad.setText(selected.getpLastname().get());
                    level.getSelectionModel().select(selected.getpSeviye().get());
            }



            PreparedStatement ps;
    @FXML
        private void setOnAction(MouseEvent event) throws SQLException {

            ResultSet rs=null;
            String name=txt_ad.getText();
            String nach=txt_soyad.getText();
            String id=txt_id.getText();
            String seviye=level.getValue().toString();
            if (txt_id.getText().isEmpty() || txt_ad.getText().isEmpty() || txt_soyad.getText().isEmpty() || level.getValue().toString().isEmpty()) 
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Lütfen tüm alanları doldurunuz");
                alert.showAndWait();
                return;
            }

            String query="INSERT INTO personel(id, Adı, Soyadı, Seviye) VALUES(?,?,?,?)";
         try 
                    {
            ps=database.connect.prepareStatement(query);
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, nach);
            ps.setString(4, seviye);
            ps.executeUpdate();
            refreshTable();
            //rs=ps.executeQuery();

            if(rs!=null)
                            {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Personel Ekleme İşlemi");
                alert.setContentText("Ekleme Başarıyla sonuçlandı :)");
                alert.showAndWait();
                            }
            } 
                    catch (Exception e) 
                    {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Personel Ekleme İşlemi Başarısız :(");
                alert.setContentText("Ama üzülmeyin, tekrar deneyebilirsiniz :) Herkes ikinci bir şansı hakeder...");
                alert.showAndWait();
                            e.printStackTrace();
                    }

    }
        @FXML
        private void update(MouseEvent event) {
            String ad=txt_ad.getText();
            String id=txt_id.getText();
            String soyad=txt_soyad.getText();
            String seviye=level.getValue();

            String query="UPDATE personel SET Adı=?,Soyadı=?,Seviye=?  WHERE id=?";


            try {
                ps=database.connect.prepareStatement(query);
                ps.setString(1, ad);
                ps.setString(2, soyad);
                ps.setString(3, seviye);
                ps.setString(4, id);
                ps.executeUpdate();
                   refreshTable();

            } catch (SQLException ex) {
                Logger.getLogger(CalisanlarController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            }


