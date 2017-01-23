package miliziandevelopers.mydesignmaterial.modelo;

/**
 * Created by Pedro on 19/01/2017.
 */

public class Compras {

    String idCompras;
    Integer numeroProductos;
    Float precio;
    String fecha;

    public Compras(String idCompras, Integer numeroProductos, Float precio, String fecha){
        this.idCompras = idCompras;
        this.numeroProductos = numeroProductos;
        this.precio = precio;
        this.fecha = fecha;
    }

    public String getIdCompras() {
        return idCompras;
    }

    public void setIdCompras(String idCompras) {
        this.idCompras = idCompras;
    }

    public Integer getNumeroProductos() {
        return numeroProductos;
    }

    public void setNumeroProductos(Integer numeroProductos) {
        this.numeroProductos = numeroProductos;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
