package miliziandevelopers.mydesignmaterial.modelo;

/**
 * Created by Pedro on 19/01/2017.
 */

public class ArtVendidos {

    String idArtVendidos;
    String idProducto;
    Integer cantidad;
    String idVenta;

    public ArtVendidos(String idArtVendidos, String idProducto, Integer cantidad, String idVenta){
        this.idArtVendidos = idArtVendidos;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.idVenta = idVenta;
    }

    public String getIdArtVendidos() {
        return idArtVendidos;
    }

    public void setIdArtVendidos(String idArtVendidos) {
        this.idArtVendidos = idArtVendidos;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
    }
}
