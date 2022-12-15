package controller.fx;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.basic.Asignatura;
import model.basic.Carrera;
import model.basic.Docente;
import model.basic.Instituto;
import service.AsignaturaService;
import service.CarreraService;
import service.DocenteService;
import service.InstitutoService;

public class AsignaturaVistaController implements Initializable{

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("FacultadPersistance");
    EntityManager em = emf.createEntityManager();

    private AsignaturaService asignaturaService = new AsignaturaService();
    private InstitutoService institutoService = new InstitutoService();
    private DocenteService docenteService = new DocenteService();
    private CarreraService carreraService = new CarreraService();

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
    void removeAsignatura(ActionEvent event) {

    }

    @FXML
    void editAsignatura(ActionEvent event) {

    }

    @FXML
    void saveAsignatura(ActionEvent event) {
        Alert a = new Alert(AlertType.ERROR);

        if ((txt_asignatura_cod.getText() != "") &&
            (txt_asignatura_nombre.getText() != "") &&
            (txt_asignatura_descripcion.getText() != "") &&
            (cboxAsignaturaDocente.getSelectionModel().getSelectedIndex() >= 0) &&
            (cboxInstituto.getSelectionModel().getSelectedIndex() >= 0) &&
            (listViewAsignaturaCarreras.getSelectionModel().getSelectedIndices() != null)){
                
                List<Carrera> selectedCarreras;
                for (Carrera carrera : listViewAsignaturaCarreras.getSelectionModel().getSelectedItems()){
                    selectedCarreras.add(carrera);
                }

                Asignatura asignaturaToSave = new Asignatura(
                                                            Integer.parseInt(txt_asignatura_cod.getText()), 
                                                            txt_asignatura_nombre.getText(),
                                                            txt_asignatura_descripcion.getText(),
                                                            cboxAsignaturaDocente.getSelectionModel().getSelectedItem(),
                                                            cboxAsignaturaDocente.getSelectionModel().getSelectedItem(),);

            }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.updateTable();
        this.fillDocenteCBox();
        this.fillInstitutoCBox();
        this.fillCarreraListView();
        listViewAsignaturaCarreras.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
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

    public void fillCarreraListView(){
        List<Carrera> carrerasFromService = carreraService.getCarreras(em);
        ObservableList<Carrera> carrerasOL = FXCollections.observableArrayList();

        for (Carrera carrera : carrerasFromService){
            carrerasOL.add(carrera);
        }
        listViewAsignaturaCarreras.setItems(carrerasOL);
        System.out.println(carrerasFromService);
        System.out.println(carrerasOL);

        }
}


