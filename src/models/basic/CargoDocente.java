package models.basic;

//Importacion libreria de persistencia
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity //Decorador para indicar que es una entidad a persistir
@Table(name = "cargosdocente")  //Decorador para indicar el nombre de la tabla a crear
public class CargoDocente {

    @Id //Decorador para indicar que PRIVATE INT NUMERO es una clave primaria
    private int numero;
    private int dedicacionHoras;
    private Instituto instituto;

    public CargoDocente(){}

    public CargoDocente(int numero) {
        super();
        this.numero = numero;
    }

    public CargoDocente(int numero, int dedicacionHoras, Instituto instituto){
        this.numero = numero;
        this.dedicacionHoras = dedicacionHoras;
        this.instituto = instituto;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getDedicacionHoras() {
        return dedicacionHoras;
    }

    public void setDedicacionHoras(int dedicacionHoras) {
        this.dedicacionHoras = dedicacionHoras;
    }

    public Instituto getInstituto() {
        return instituto;
    }

    public void setInstituto(Instituto instituto) {
        this.instituto = instituto;
    }

    public String toString(){
        return getNumero() + " - " + getDedicacionHoras() + " - " + getInstituto();
    }

}
