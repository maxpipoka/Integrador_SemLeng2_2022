package models.basic;

public class Instituto {
    private int codigo;
    private String denominacion;
    private Facultad facultad;

    public Instituto(){}

    public Instituto(int codigo, String denominacion, Facultad facultad){
        this.codigo = codigo;
        this.denominacion = denominacion;
        this.facultad = facultad;
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

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public String toString(){
        return this.getCodigo() + " " + this.getDenominacion();
    }
}
