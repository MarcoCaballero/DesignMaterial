package miliziandevelopers.mydesignmaterial.modelo;

/**
 * Created by Pedro on 19/01/2017.
 */

public class Productos {

    private String idProducto;
    private String nombre;
    private String descripcion;
    private Integer cantidad;
    private Integer precioVenta;
    private Integer precioCompra;
    private String foto;
    private String numProveedor;

    public Productos() {
    }

    public Productos(String idProducto, String nombre, String descripcion, Integer cantidad, Integer precioVenta,
                     Integer precioCompra, String foto, String numProveedor){
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra;
        this.foto = foto;
        this.numProveedor = numProveedor;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Integer precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Integer precioCompra) {
        this.precioCompra = precioCompra;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNumProveedor() {
        return numProveedor;
    }

    public void setNumProveedor(String numProveedor) {
        this.numProveedor = numProveedor;
    }
}
