package model.basic;

import java.time.LocalDate;

//Importacion libreria de persistencia
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity //Decorador para indicar que es entidad a persistir en DB
@Table(name="docentes") //Decorador para indicar el nombre de la tabla a crear
public class Docente {

    @Id //Decorador para indicar que el PRIVATE INT LEGAJO es clave primaria
    private int legajo;
    private int docUnico;
    private String nombres;
    private String apellidos;
    private LocalDate fechaNac;
    private String direccionNotif;
    private CargoDocente cargoDocente;

    public Docente(){}

    public Docente(int legajo) {
        super();
        this.legajo = legajo;
    }

    public Docente(int legajo, int docUnico, String nombres, String apellidos, LocalDate fechaNac, String direccionNotif, CargoDocente cargoDocente){
        this.legajo = legajo;
        this.docUnico = docUnico;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNac = fechaNac;
        this.direccionNotif = direccionNotif;
        this.cargoDocente = cargoDocente;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public int getDocUnico() {
        return docUnico;
    }

    public void setDocUnico(int docUnico) {
        this.docUnico = docUnico;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getDireccionNotif() {
        return direccionNotif;
    }

    public void setDireccionNotif(String direccionNotif) {
        this.direccionNotif = direccionNotif;
    }

    public CargoDocente getCargoDocente() {
        return cargoDocente;
    }

    public void setCargoDocente(CargoDocente cargoDocente) {
        this.cargoDocente = cargoDocente;
    }

    public String toString(){
        return "Legajo: " + getLegajo() + "Nombre: " + getApellidos() + " " + getNombres();
    }
}
