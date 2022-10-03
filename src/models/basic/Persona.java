package models.basic;

import java.util.Calendar;

public abstract class Persona {
    private int legajo;
    private int docUnico;
    private String nombres;
    private String apellidos;
    private Calendar fechaNac;
    private String direccionNotif;

    public Persona(){}

    public Persona(int legajo, int docUnico, String nombres, String apellidos, Calendar fechaNac, String direccionNotif){
        this.legajo = legajo;
        this.docUnico = docUnico;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNac = fechaNac;
        this.direccionNotif = direccionNotif;
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

    public Calendar getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Calendar fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getDireccionNotif() {
        return direccionNotif;
    }

    public void setDireccionNotif(String direccionNotif) {
        this.direccionNotif = direccionNotif;
    }

    
}
