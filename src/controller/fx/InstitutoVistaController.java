package controller.fx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class InstitutoVistaController {

    @FXML
    private Button btn_instituto_editar;

    @FXML
    private Button btn_instituto_eliminar;

    @FXML
    private Button btn_instituto_guardar;

    @FXML
    private TableView<?> table_institutos;

    @FXML
    private TextField txt_institutos_cod;

    @FXML
    private TextField txt_institutos_denominacion;

}
