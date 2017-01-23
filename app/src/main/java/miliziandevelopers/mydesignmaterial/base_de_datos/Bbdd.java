package miliziandevelopers.mydesignmaterial.base_de_datos;

import java.util.UUID;

/**
 * Created by Pedro on 19/01/2017.
 * En esta clase se define la estructura de la BBDD
 */

public class Bbdd {
    interface ColumnasUsuarios {
        String ID = "id";
        String NOMBRE = "nombre";
        String PASSWORD = "password";
        String AVATAR = "avatar";
    }

    interface ColumnasProductos {
        String ID = "id";
        String NOMBRE = "nombre";
        String DESCRIPCION = "descripcion";
        String CANTIDAD = "cantidad";
        String PRECIOVENTA = "precio_venta";
        String PRECIOCOMPRA = "precio_compra";
        String FOTO = "foto";
        String NUM_PROVEEDOR = "numero_proveedor";
    }

    interface ColumnasProveedores {
        String ID = "id";
        String NOMBRE_EMPRESA = "nombre_empresa";
        String NUM_TELFNO = "numero_telefono";
        String DIRECCION = "direccion";
    }

    interface ColumnasVentas {
        String ID = "id";
        String NUM_PRODUCTOS = "numero_productos";
        String PRECIO = "total";
        String FECHA = "fecha";
    }

    interface ColumnasCompras {
        String ID = "id";
        String NUM_RODUCTOS = "numero_productos";
        String PRECIO = "total";
        String FECHA = "fecha";
    }

    interface ColumnasArtVendidos {
        String ID = "id";
        String ID_PRODUCTO = "id_producto";
        String CANTIDAD = "cantidad";
        String ID_VENTA = "id_venta";
    }

    interface ColumnasArtComprados {
        String ID = "id";
        String ID_PRODUCTO = "id_producto";
        String CANTIDAD = "cantidad";
        String ID_COMPRA = "id_compra";
    }

    // Generar las ID
    public static class Usuario implements ColumnasUsuarios {
        public static String generarIdUsuarios() {
            return "USUARIO - " + UUID.randomUUID().toString();
        }
    }

    public static class Producto implements ColumnasProductos {
        public static String generarIdProductos() {
            return "PRODUCTO - " + UUID.randomUUID().toString();
        }
    }

    public static class Proveedor implements ColumnasProveedores {
        public static String generarIdProveedores() {
            return "PROVEEDOR - " + UUID.randomUUID().toString();
        }
    }

    public static class Venta implements ColumnasVentas {
        public static String generarIdVentas() {
            return "VENTA - " + UUID.randomUUID().toString();
        }
    }

    public static class Compra implements ColumnasCompras {
        public static String generarIdCompras() {
            return "COMPRA - " + UUID.randomUUID().toString();
        }
    }

    public static class ArtVendido implements ColumnasArtVendidos {
        public static String generarIdArtVendidos() {
            return "ARTICULO_VENDIDO - " + UUID.randomUUID().toString();
        }
    }

    public static class ArtComprado implements ColumnasArtComprados {
        public static String generarIdArtComprados() {
            return "ARTICULO_COMPRADO - " + UUID.randomUUID().toString();
        }
    }

    private void Bbdd() {

    }
}
