package models.basic;

public class Carrera {
    private int codigo;
    private String nombre;
    private Instituto instituto;

    public Carrera(){}

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
