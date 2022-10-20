package models.basic;

public class Instituto {
    private int codigo;
    private String denominacion;

    public Instituto(){}

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
        return this.getCodigo() + " " + this.getDenominacion();
    }
}
