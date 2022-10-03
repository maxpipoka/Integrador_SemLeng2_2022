package models.basic;

import java.util.Calendar;

//Clase Docente extiende de Persona porque es clase hija
public class Docente extends Persona {
    private CargoDocente cargoDocente;

    public Docente(int legajo, int docUnico, String nombres, String apellidos, Calendar fechaNac, String direccionNotif,
            CargoDocente cargoDocente) {
        super(legajo, docUnico, nombres, apellidos, fechaNac, direccionNotif);
        this.cargoDocente = cargoDocente;
    } //con super(valores) se hace referencia a los atributos heredados

    public Docente(CargoDocente cargoDocente) {
        this.cargoDocente = cargoDocente;
    }

    public int getLegajo() {
        return super.getLegajo();
    }

    public void setLegajo(int legajo){
        super.setLegajo(legajo);
    }

    public int getDocUnico() {
        return super.getDocUnico();
    }

    public void setDocUnico(int docUnico){
        super.setDocUnico(docUnico);
    }

    public String getNombres() {
        return super.getNombres();
    }

    public void setNombres(String nombres){
        super.setNombres(nombres);
    }

    public String getApellidos() {
        return super.getApellidos();
    }

    public void setApellidos(String apellidos){
        super.setApellidos(apellidos);
    }
    
    public Calendar getFechaNac() {
        return super.getFechaNac();
    }

    public void setFechaNac(Calendar fechaNac){
        super.setFechaNac(fechaNac);
    }

    public String getDireccionNotif() {
        return super.getDireccionNotif();
    }

    public void setDireccionNotif(String direccionNotif){
        super.setDireccionNotif(direccionNotif);
    }

    public CargoDocente getCargoDocente() {
        return cargoDocente;
    }

    public void setCargoDocente(CargoDocente cargoDocente) {
        this.cargoDocente = cargoDocente;
    }

    public String toString(){
        return getApellidos() +  " " + getNombres() + " - Cargo: " + cargoDocente.getNumero();
    }
}
