package controller.fx;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.basic.CargoDocente;
import model.basic.Instituto;
import service.CargoDocenteService;
import service.InstitutoService;

public class CargoDocenteVistaController implements Initializable{

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("FacultadPersistance");
    EntityManager em = emf.createEntityManager();

    private CargoDocenteService cargoDocenteService = new CargoDocenteService();
    private InstitutoService institutoService = new InstitutoService();

    @FXML
    private TableView<CargoDocente> table_cargos;

    @FXML
    private TableColumn<CargoDocente, String> columnNumero;

    @FXML
    private TableColumn<CargoDocente, String> columnDedicacion;

    @FXML
    private TableColumn<CargoDocente, Instituto> columnInstituto;

    @FXML
    private Button btn_cargos_guardar;

    @FXML
    private Button btn_cargos_editar;

    @FXML
    private Button btn_cargos_eliminar;

    @FXML
    private TextField txt_cargos_numero;

    @FXML
    private TextField txt_cargos_dedicacion;

    @FXML
    private ChoiceBox<Instituto> choiseInstituto;

    @FXML
    void editCargo(ActionEvent event) {
        CargoDocente selectedCargoDocente = table_cargos.getSelectionModel().getSelectedItem();

        if (selectedCargoDocente != null){
            txt_cargos_numero.setText(Integer.toString(selectedCargoDocente.getNumero()));
            txt_cargos_dedicacion.setText(Integer.toString(selectedCargoDocente.getDedicacionHoras()));
            choiseInstituto.setValue(selectedCargoDocente.getInstituto());
            this.removeCargo(event);
            this.updateTable();
            txt_cargos_numero.setDisable(true);
            btn_cargos_editar.setDisable(true);
            btn_cargos_eliminar.setDisable(true);
        }
    }

    @FXML
    void removeCargo(ActionEvent event) {
        CargoDocente selectedCargoDocente = table_cargos.getSelectionModel().getSelectedItem();
        if (selectedCargoDocente != null){
            cargoDocenteService.removeCargoDocente(em, selectedCargoDocente.getNumero());
            updateTable();
        }

    }

    @FXML
    void saveCargo(ActionEvent event) {

        Alert a = new Alert(AlertType.ERROR);

        if ((txt_cargos_numero.getText() != "") &&
            (txt_cargos_dedicacion.getText() != "") &&
            choiseInstituto.getSelectionModel().getSelectedIndex() >= 0){

                CargoDocente cargoDocenteToSave = new CargoDocente(Integer.parseInt(txt_cargos_numero.getText()), Integer.parseInt(txt_cargos_dedicacion.getText()), choiseInstituto.getSelectionModel().getSelectedItem());

                try {
                    cargoDocenteService.saveCargoDocente(em, cargoDocenteToSave);
                    txt_cargos_numero.clear();
                    txt_cargos_dedicacion.clear();
                    choiseInstituto.getSelectionModel().clearSelection();
                    this.updateTable();
                } catch (Exception e){
                    a.setContentText("No se puede guardar el Cargo Docente");
                    a.show();
                }
        } else {
            a.setContentText("Deben completarse todos los campos");
            a.show();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        columnDedicacion.setCellValueFactory(new PropertyValueFactory<>("dedicacionHoras"));
        columnInstituto.setCellValueFactory(new PropertyValueFactory<>("instituto"));
        updateTable();
        fillInstitutoCBox();
        
    }

    public void updateTable(){
        List<CargoDocente> cargosDocenteFromService = cargoDocenteService.getCargosDocente(em);
        table_cargos.getItems().clear();
        table_cargos.getItems().addAll(cargosDocenteFromService);
    }

    public void fillInstitutoCBox(){
        List<Instituto> institutosFromService = institutoService.getInstitutos(em);
        choiseInstituto.getItems().addAll(institutosFromService);
    }

}
