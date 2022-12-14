package controller.fx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class MainVistaController implements Initializable{

    @FXML
    private Button btnInstitutos;

    @FXML
    private Button btnCarreras;

    @FXML
    private Button btnAsignaturas;

    @FXML
    private Button btnDocentes;

    @FXML
    private Button btnCargosDocente;

    @FXML
    private Pane container_content;

    FXMLLoader loaderFXMLContent;

    Parent root;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Pane loadedPane = FXMLLoader.load(getClass().getResource("/views/InstitutoVista.fxml"));
            container_content.getChildren().setAll(loadedPane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void setVistaAsignaturas(ActionEvent event) {
        Pane loadedPane;
        try {
            loadedPane = FXMLLoader.load(getClass().getResource("/views/AsignaturaVista.fxml"));
            container_content.getChildren().clear();
            container_content.getChildren().setAll(loadedPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void setVistaCargosDocente(ActionEvent event) {
        Pane loadedPane;
        try {
            loadedPane = FXMLLoader.load(getClass().getResource("/views/CargoDocenteVista.fxml"));
            container_content.getChildren().clear();
            container_content.getChildren().setAll(loadedPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void setVistaCarreras(ActionEvent event) {
        Pane loadedPane;
        try {
            loadedPane = FXMLLoader.load(getClass().getResource("/views/CarreraVista.fxml"));
            container_content.getChildren().clear();
            container_content.getChildren().setAll(loadedPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void setVistaDocentes(ActionEvent event) {
        Pane loadedPane;
        try {
            loadedPane = FXMLLoader.load(getClass().getResource("/views/DocenteVista.fxml"));
            container_content.getChildren().clear();
            container_content.getChildren().setAll(loadedPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void setVistaInstituto(ActionEvent event) {
        Pane loadedPane;
        try {
            loadedPane = FXMLLoader.load(getClass().getResource("/views/InstitutoVista.fxml"));
            container_content.getChildren().clear();
            container_content.getChildren().setAll(loadedPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    

}
