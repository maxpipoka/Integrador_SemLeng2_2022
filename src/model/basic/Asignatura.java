package model.basic;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity //Decorador para indicar que es una entidad a persistir
@Table(name = "asignaturas")    //Decorador para indicar el nombre de la tabla a crear
public class Asignatura {

    @Id //Decorador para indicar que PRIVATE INT CODIGO es clave primaria.
    private int codigo;
    private String nombre;
    private String descripcion;
    private Docente docente;
    private Instituto instituto;
    private Set<Carrera> carreras;
    
    public Asignatura() {
    }
    
    public Asignatura(int codigo) {
        super();
        this.codigo = codigo;
    }


    public Asignatura(int codigo, String nombre, String descripcion, Docente docente, Instituto instituto,
            Set<Carrera> carreras) {
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

    public Set<Carrera> getCarreras() {
        return carreras;
    }

    public void setCarreras(Set<Carrera> carreras) {
        this.carreras = carreras;
    }

    public String toString(){
        return "Código: " + getCodigo() + " - Nombre: " + getNombre() + " - Instituto: " + instituto.getDenominacion() ;
    }
}
