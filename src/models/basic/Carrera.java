package models.basic;

//Importacion libreria de persistencia
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity //Decorador para indicar que es una entidad a persistir en la DB
@Table(name = "carreras")   //Decorador para indicar el nombre de la tabla a crear
public class Carrera {

    @Id //Decorador para indicar que PRIVATE INT CODIGO es una clave primaria
    private int codigo;
    private String nombre;
    private Instituto instituto;

    public Carrera(){}

    public Carrera(int codigo) {
        this.codigo = codigo;
    }

    public Carrera(int codigo, String nombre, Instituto instituto) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.instituto = instituto;
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

    public Instituto getInstituto() {
        return instituto;
    }

    public void setInstituto(Instituto instituto) {
        this.instituto = instituto;
    }

    public String toString(){
        return getCodigo() + " - " + getNombre() + " - Instituto: " + instituto.toString(); 
    }
}
