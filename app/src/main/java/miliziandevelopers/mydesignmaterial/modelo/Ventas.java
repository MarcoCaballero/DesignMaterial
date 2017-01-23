package miliziandevelopers.mydesignmaterial.modelo;

/**
 * Created by Pedro on 19/01/2017.
 */

public class Ventas {

    String idVenta;
    Integer numeroProductos;
    Float precio;
    String fecha;

    public Ventas(String idVenta, Integer numeroProductos, Float precio, String fecha){
        this.idVenta = idVenta;
        this.numeroProductos = numeroProductos;
        this.precio = precio;
        this.fecha = fecha;
    }

    public String getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
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
