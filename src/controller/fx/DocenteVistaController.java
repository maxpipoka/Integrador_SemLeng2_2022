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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.basic.CargoDocente;
import model.basic.Docente;
import service.CargoDocenteService;
import service.DocenteService;

public class DocenteVistaController implements Initializable{

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("FacultadPersistance");
    EntityManager em = emf.createEntityManager();

    private DocenteService docenteService = new DocenteService();
    private CargoDocenteService cargoDocenteService = new CargoDocenteService();

    @FXML
    private TableView<Docente> table_docentes;

    @FXML
    private TableColumn<Docente, String> columnLegajo;

    @FXML
    private TableColumn<Docente, CargoDocente> columnCargo;

    @FXML
    private TableColumn<Docente, String> columnDni;

    @FXML
    private TableColumn<Docente, String> columnApellidos;

    @FXML
    private TableColumn<Docente, String> columnNombres;

    @FXML
    private Button btn_docente_guardar;

    @FXML
    private Button btn_docente_editar;

    @FXML
    private Button btn_docente_eliminar;

    @FXML
    private TextField txt_docente_legajo;

    @FXML
    private TextField txt_docente_dni;

    @FXML
    private DatePicker pickerNacimiento;

    @FXML
    private TextField txt_docente_apellidos;

    @FXML
    private TextField txt_docente_nombres;

    @FXML
    private TextField txt_docente_direccion;

    @FXML
    private ChoiceBox<CargoDocente> choiseCargo;

    @FXML
    void removeDocente(ActionEvent event) {
        Docente selectedDocente = table_docentes.getSelectionModel().getSelectedItem();
        if (selectedDocente != null){
            docenteService.removeDocente(em, selectedDocente.getLegajo());
            updateTable();
        }
    }

    @FXML
    void editDocente(ActionEvent event) {
        Docente selectedDocente = table_docentes.getSelectionModel().getSelectedItem();

        if (selectedDocente != null){
            txt_docente_legajo.setText(Integer.toString(selectedDocente.getLegajo()));
            txt_docente_dni.setText(Integer.toString(selectedDocente.getDocUnico()));
            pickerNacimiento.setValue(selectedDocente.getFechaNac());
            txt_docente_apellidos.setText(selectedDocente.getApellidos());
            txt_docente_nombres.setText(selectedDocente.getNombres());
            txt_docente_direccion.setText(selectedDocente.getDireccionNotif());
            choiseCargo.setValue(selectedDocente.getCargoDocente());
            this.removeDocente(event);
            this.updateTable();
            txt_docente_legajo.setDisable(true);
            btn_docente_editar.setDisable(true);
            btn_docente_eliminar.setDisable(true);
            table_docentes.setDisable(true);
        }
    }


    @FXML
    void saveDocente(ActionEvent event) {
        Alert a = new Alert(AlertType.ERROR);

        if ((txt_docente_legajo.getText() != "") && 
            (txt_docente_dni.getText() != "") && 
            (pickerNacimiento.getValue() != null) &&
            (txt_docente_apellidos.getText() != "") &&
            (txt_docente_nombres.getText() != "") &&
            (txt_docente_direccion.getText() != "") &&
            (choiseCargo.getSelectionModel().getSelectedIndex() >= 0)){
                Docente docenteToSave = new Docente(
                                                    Integer.parseInt(txt_docente_legajo.getText()),
                                                    Integer.parseInt(txt_docente_dni.getText()),
                                                    txt_docente_nombres.getText(),
                                                    txt_docente_apellidos.getText(),
                                                    pickerNacimiento.getValue(),
                                                    txt_docente_direccion.getText(),
                                                    choiseCargo.getSelectionModel().getSelectedItem());
                try {
                    docenteService.saveDocente(em, docenteToSave);
                    txt_docente_legajo.clear();
                    txt_docente_dni.clear();
                    txt_docente_nombres.clear();
                    txt_docente_apellidos.clear();
                    txt_docente_direccion.clear();
                    choiseCargo.getSelectionModel().clearSelection();
                    this.updateTable();
                    txt_docente_legajo.setDisable(false);
                    btn_docente_editar.setDisable(false);
                    btn_docente_eliminar.setDisable(false);
                    table_docentes.setDisable(false);
                } catch (Exception e){
                    a.setContentText("No se puede guardar el Docente");
                    a.show();
                }
        } else{
            a.setContentText("Deben completarse todos los campos");
            a.show();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnLegajo.setCellValueFactory(new PropertyValueFactory<>("legajo"));
        columnCargo.setCellValueFactory(new PropertyValueFactory<>("cargoDocente"));
        columnDni.setCellValueFactory(new PropertyValueFactory<>("docUnico"));
        columnNombres.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        columnApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        this.updateTable();
        this.fillCargo();

    }

    public void updateTable(){
        List<Docente> docentesFromService = docenteService.getDocentes(em);
        table_docentes.getItems().clear();
        table_docentes.getItems().addAll(docentesFromService);
    }

    public void fillCargo(){
        List<CargoDocente> cargosDocenteFromService = cargoDocenteService.getCargosDocente(em);
        choiseCargo.getItems().addAll(cargosDocenteFromService);

    }

}