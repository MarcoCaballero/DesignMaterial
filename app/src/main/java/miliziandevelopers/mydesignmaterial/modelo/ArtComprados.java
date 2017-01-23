package miliziandevelopers.mydesignmaterial.modelo;

/**
 * Created by Pedro on 19/01/2017.
 */

public class ArtComprados {

    String idArtComprados;
    String idProducto;
    Integer cantidad;
    String idCompra;

    public ArtComprados(String idArtComprados, String idProducto, Integer cantidad, String idCompra){
        this.idArtComprados = idArtComprados;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.idCompra = idCompra;
    }

    public String getIdArtComprados() {
        return idArtComprados;
    }

    public void setIdArtComprados(String idArtComprados) {
        this.idArtComprados = idArtComprados;
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

    public String getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(String idCompra) {
        this.idCompra = idCompra;
    }
}
