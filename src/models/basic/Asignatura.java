package models.basic;

import java.util.List;

public class Asignatura {
    private int codigo;
    private String nombre;
    private String descripcion;
    private Docente docente;
    private Instituto instituto;
    private List<Carrera> carreras;
    
    public Asignatura() {
    }

    public Asignatura(int codigo, String nombre, String descripcion, Docente docente, Instituto instituto,
            List<Carrera> carreras) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.docente = docente;
        this.instituto = instituto;
        this.carreras = carreras;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Instituto getInstituto() {
        return instituto;
    }

    public void setInstituto(Instituto instituto) {
        this.instituto = instituto;
    }

    public List<Carrera> getCarreras() {
        return carreras;
    }

    public void setCarreras(List<Carrera> carreras) {
        this.carreras = carreras;
    }

    public String toString(){
        return getCodigo() + " - " + getNombre() + " - " + docente.getApellidos() + " " + docente.getNombres() + " - " + instituto.getDenominacion() ;
    }
}
