package controller.fx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.basic.Asignatura;
import model.basic.Carrera;
import model.basic.Docente;
import model.basic.Instituto;

public class AsignaturaVistaController implements Initializable{

    @FXML
    private Button btn_asignaturas_editar;

    @FXML
    private Button btn_asignaturas_eliminar;

    @FXML
    private Button btn_asignaturas_guardar;

    @FXML
    private ChoiceBox<Docente> cboxAsignaturaDocente;

    @FXML
    private ChoiceBox<Instituto> cboxInstituto;

    @FXML
    private TableColumn<Asignatura, String> columnCodigo;

    @FXML
    private TableColumn<Asignatura, Docente> columnDocente;

    @FXML
    private TableColumn<Asignatura, Instituto> columnInstituto;

    @FXML
    private TableColumn<Asignatura, String> columnNombre;

    @FXML
    private ListView<Carrera> listViewAsignaturaCarreras;

    @FXML
    private TableView<Asignatura> table_asignaturas;

    @FXML
    private TextField txt_asignatura_cod;

    @FXML
    private TextField txt_asignatura_descripcion;

    @FXML
    private TextField txt_asignatura_nombre;

    @FXML
    void editAsignatura(ActionEvent event) {

    }

    @FXML
    void removeAsignatura(ActionEvent event) {

    }

    @FXML
    void saveAsignatura(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    }

}
