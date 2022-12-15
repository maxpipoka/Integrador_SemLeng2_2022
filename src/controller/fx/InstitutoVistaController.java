package controller.fx;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.basic.Instituto;
import service.InstitutoService;

public class InstitutoVistaController implements Initializable{

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("FacultadPersistance");
    EntityManager em = emf.createEntityManager();

    private InstitutoService institutoService = new InstitutoService();

    @FXML
    private TableView<Instituto> table_institutos;

    @FXML
    private TableColumn<Instituto, String> columnDenominacion;

    @FXML
    private TableColumn<Instituto, String> columnCodigo;

    @FXML
    private Button btn_instituto_guardar;

    @FXML
    private Button btn_instituto_editar;

    @FXML
    private Button btn_instituto_eliminar;

    @FXML
    private TextField txt_instituto_cod;

    @FXML
    private TextField txt_instituto_denominacion;

    @FXML
    void deleteInstituto(ActionEvent event) {
        Instituto selectedInstituto = table_institutos.getSelectionModel().getSelectedItem();
        if (selectedInstituto != null){
            institutoService.removeInstituto(em, selectedInstituto.getCodigo());
            updateTable();
        }
    }

    @FXML
    void editInstituto(ActionEvent event) {
        Instituto selectedInstituto = table_institutos.getSelectionModel().getSelectedItem();
        if (selectedInstituto != null){
            txt_instituto_cod.setText(Integer.toString(selectedInstituto.getCodigo()));
            txt_instituto_denominacion.setText(selectedInstituto.getDenominacion());
            this.deleteInstituto(event);
            this.updateTable();
            btn_instituto_eliminar.setDisable(true);
            btn_instituto_editar.setDisable(true);
            txt_instituto_cod.setDisable(true);
            table_institutos.setDisable(true);
        }
    }

    @FXML
    void saveInstituto(ActionEvent event) {

        Instituto institutoToSave = new Instituto(Integer.parseInt(txt_instituto_cod.getText()), txt_instituto_denominacion.getText());

        try{
            institutoService.saveInstituto(em, institutoToSave);
            txt_instituto_cod.clear();
            txt_instituto_denominacion.clear();
            this.updateTable();
        } catch (Exception e){
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText("No se puede guardar el Instituto");
            a.show();
        }
        

        btn_instituto_eliminar.setDisable(false);
        btn_instituto_editar.setDisable(false);
        txt_instituto_cod.setDisable(false);
        table_institutos.setDisable(false);
        

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        columnDenominacion.setCellValueFactory(new PropertyValueFactory<>("denominacion"));
        this.updateTable();
    }


    public void updateTable(){
        List<Instituto> institutosFromService = institutoService.getInstitutos(em);
        table_institutos.getItems().clear();
        table_institutos.getItems().addAll(institutosFromService);
    }
}
