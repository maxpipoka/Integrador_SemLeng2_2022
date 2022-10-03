package models.basic;

public class CargoDocente {
    private int numero;
    private int dedicacionHoras;
    private Instituto instituto;

    public CargoDocente(){}

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
