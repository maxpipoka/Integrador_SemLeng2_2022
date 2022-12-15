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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.basic.Carrera;
import model.basic.Instituto;
import service.CarreraService;
import service.InstitutoService;

public class CarreraVistaController implements Initializable{

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("FacultadPersistance");
    EntityManager em = emf.createEntityManager();

    private CarreraService carreraService = new CarreraService();
    private InstitutoService institutoService = new InstitutoService();

    @FXML
    private Button btn_carrera_editar;

    @FXML
    private Button btn_carrera_eliminar;

    @FXML
    private Button btn_carrera_guardar;

    @FXML
    private ChoiceBox<Instituto> cbox_carreras_institutos;

    @FXML
    private TableView<Carrera> table_carreras;

    @FXML
    private TableColumn<Carrera, String> columnCod;

    @FXML
    private TableColumn<Instituto, Instituto> columnInstituto;

    @FXML
    private TableColumn<Carrera, String> columnNombre;

    @FXML
    private TextField txt_carrera_cod;

    @FXML
    private TextField txt_carrera_nombre;

    @FXML
    void editCarrera(ActionEvent event) {
        Carrera selectedCarrera = table_carreras.getSelectionModel().getSelectedItem();
        
        if(selectedCarrera != null){
            txt_carrera_cod.setText(Integer.toString(selectedCarrera.getCodigo()));
            txt_carrera_nombre.setText(selectedCarrera.getNombre());
            cbox_carreras_institutos.setValue(selectedCarrera.getInstituto());
            this.removeCarrera(event);
            this.updateTable();
            txt_carrera_cod.setDisable(true);
            btn_carrera_editar.setDisable(true);
            btn_carrera_eliminar.setDisable(true);
            table_carreras.setDisable(true);
        }
    }

    @FXML
    void removeCarrera(ActionEvent event) {
        Carrera selectedCarrera = table_carreras.getSelectionModel().getSelectedItem();

        if(selectedCarrera != null){
            carreraService.removeCarrera(em, selectedCarrera.getCodigo());
            updateTable();
        }

    }

    @FXML
    void saveCarrera(ActionEvent event) {
        
        Alert a = new Alert(AlertType.ERROR);
        

        if ((txt_carrera_cod.getText() != null) && (txt_carrera_nombre.getText() != null) && (cbox_carreras_institutos.getSelectionModel().getSelectedIndex() >= 0)){
            
            Carrera carreraToSave = new Carrera(
                                                Integer.parseInt(txt_carrera_cod.getText()), 
                                                txt_carrera_nombre.getText(), 
                                                cbox_carreras_institutos.getSelectionModel().getSelectedItem());
            try{
                carreraService.saveCarrera(em, carreraToSave);
                txt_carrera_cod.clear();
                txt_carrera_nombre.clear();
                this.updateTable();
            } catch (Exception e){
                a.setContentText("No se puede guardar la Carrera");
                a.show();
            }
        } else{
            a.setContentText("Deben completarse todos los campos.");
            a.show();
        }

        txt_carrera_cod.setDisable(false);
        btn_carrera_editar.setDisable(false);
        btn_carrera_eliminar.setDisable(false);
        table_carreras.setDisable(false);

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        columnCod.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnInstituto.setCellValueFactory(new PropertyValueFactory<>("instituto"));
        updateTable();
        fillInstitutoCBox();
    }

    public void updateTable(){
        List<Carrera> carrerasFromService = carreraService.getCarreras(em);
        table_carreras.getItems().clear();
        table_carreras.getItems().addAll(carrerasFromService);
    }

    public void fillInstitutoCBox(){
        List<Instituto> institutosFromService = institutoService.getInstitutos(em);
        cbox_carreras_institutos.getItems().addAll(institutosFromService);
    }

}
