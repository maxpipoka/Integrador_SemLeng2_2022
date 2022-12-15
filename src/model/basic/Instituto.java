package model.basic;

//Importacion de la libreria de persistencia
import jakarta.persistence.Entity; 
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity  //Decorador que indica que va a ser una entidad a persistir
@Table(name = "institutos") //Decorador para especificar el nombre de la tabla a crear en la DB para guardar estos registros. OJO! no vale comilla simple
public class Instituto {

    @Id //Decorador para establecer el PRIVATE INT CODIGO como clave primaria.
    private int codigo;
    private String denominacion;

    public Instituto(){}

    public Instituto(int codigo) {
        super();
        this.codigo = codigo;
    }

    public Instituto(int codigo, String denominacion){
        this.codigo = codigo;
        this.denominacion = denominacion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String toString(){
        return this.getCodigo() + " - " + this.getDenominacion();
    }
}
