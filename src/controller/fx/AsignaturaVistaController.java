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
import service.AsignaturaService;
import service.DocenteService;
import service.InstitutoService;

public class AsignaturaVistaController implements Initializable{

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("FacultadPersistance");
    EntityManager em = emf.createEntityManager();

    private AsignaturaService asignaturaService = new AsignaturaService();
    private InstitutoService institutoService = new InstitutoService();
    private DocenteService docenteService = new DocenteService();

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
        this.updateTable();
        this.fillDocenteCBox();
        this.fillInstitutoCBox();
        
    }

    public void updateTable(){
        List<Asignatura> asignaturasFromService = asignaturaService.getAsignaturas(em);
        table_asignaturas.getItems().clear();
        table_asignaturas.getItems().addAll(asignaturasFromService);
    }

    public void fillInstitutoCBox(){
        List<Instituto> institutosFromService = institutoService.getInstitutos(em);
        cboxInstituto.getItems().addAll(institutosFromService);
    }
    
    public void fillDocenteCBox(){
        List<Docente> docenteFromService = docenteService.getDocentes(em);
        cboxAsignaturaDocente.getItems().addAll(docenteFromService);
    }

}
