package miliziandevelopers.mydesignmaterial.base_de_datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.provider.BaseColumns;

import miliziandevelopers.mydesignmaterial.base_de_datos.Bbdd.ArtComprado;
import miliziandevelopers.mydesignmaterial.base_de_datos.Bbdd.ArtVendido;
import miliziandevelopers.mydesignmaterial.base_de_datos.Bbdd.Compra;
import miliziandevelopers.mydesignmaterial.base_de_datos.Bbdd.Producto;
import miliziandevelopers.mydesignmaterial.base_de_datos.Bbdd.Proveedor;
import miliziandevelopers.mydesignmaterial.base_de_datos.Bbdd.Usuario;
import miliziandevelopers.mydesignmaterial.base_de_datos.Bbdd.Venta;

/**
 * Created by Pedro on 19/01/2017.
 * Esta clase administra la conexión a la BBDD y su administración
 */

public class Helper extends SQLiteOpenHelper {

    private static final String NOMBRE_BASE_DATOS = "pyministratordb.db";
    private static final int VERSION_ACTUAL = 1;
    private final Context context;

    interface Tablas {
        String USUARIOS = "usuarios";
        String PRODUCTOS = "productos";
        String PROVEEDORES = "proveedores";
        String VENTAS = "ventas";
        String COMPRAS = "compras";
        String ARTVENDIDOS = "articulos_vendidos";
        String ARTCOMPRADOS = "articulos_comprados";
    }

    // Enlazar las foreign keys
    interface Referencias {
        String ID_PRODUCTO = String.format("REFERENCES %s(%s)", Tablas.PRODUCTOS, Producto.ID);
        String ID_PROVEEDOR = String.format("REFERENCES %s(%s)", Tablas.PROVEEDORES, Proveedor.ID);
        String ID_VENTA = String.format("REFERENCES %s(%s)", Tablas.VENTAS, Venta.ID);
        String ID_COMPRA = String.format("REFERENCES %s(%s)", Tablas.COMPRAS, Compra.ID);
    }

    public Helper(Context context) {
        super(context, NOMBRE_BASE_DATOS, null, VERSION_ACTUAL);
        this.context = context;
    }

    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.execSQL("PRAGMA foreign_keys=ON");
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear la tabla de usuarios
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                "%s TEXT UNIQUE NOT NULL,%s TEXT UNIQUE NOT NULL,%s TEXT NOT NULL,%s TEXT)",
                Tablas.USUARIOS, BaseColumns._ID, Usuario.ID, Usuario.NOMBRE, Usuario.PASSWORD, Usuario.AVATAR));

        // Crear la tabla de productos
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT UNIQUE NOT NULL,%s TEXT UNIQUE NOT NULL,%s TEXT,%s INTEGER NOT NULL," +
                        "%s INTEGER NOT NULL CHECK (%s>0),%s INTEGER NOT NULL CHECK (%s>0),%s TEXT,%s TEXT NOT NULL %s)",
                Tablas.PRODUCTOS, BaseColumns._ID, Producto.ID, Producto.NOMBRE, Producto.DESCRIPCION,
                Producto.CANTIDAD, Producto.PRECIOVENTA, Producto.PRECIOVENTA, Producto.PRECIOCOMPRA,
                Producto.PRECIOCOMPRA, Producto.FOTO, Producto.NUM_PROVEEDOR, Referencias.ID_PROVEEDOR));
        // Crear la tabla de proveedores
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT UNIQUE NOT NULL,%s TEXT UNIQUE NOT NULL,%s TEXT UNIQUE,%s TEXT)", Tablas.PROVEEDORES,
                BaseColumns._ID, Proveedor.ID, Proveedor.NOMBRE_EMPRESA, Proveedor.NUM_TELFNO, Proveedor.DIRECCION));
        // Crear la tabla de ventas
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT UNIQUE NOT NULL,%s INTEGER NOT NULL CHECK (%s>0),%s REAL NOT NULL,%s DATETIME NOT NULL)",
                Tablas.VENTAS, BaseColumns._ID, Venta.ID, Venta.NUM_PRODUCTOS, Venta.NUM_PRODUCTOS,
                Venta.PRECIO, Venta.FECHA));
        // Crear la tabla de compras
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT UNIQUE NOT NULL,%s INTEGER NOT NULL CHECK (%s>0),%s REAL NOT NULL,%s DATETIME NOT NULL)",
                Tablas.COMPRAS, BaseColumns._ID, Compra.ID, Compra.NUM_RODUCTOS, Compra.NUM_RODUCTOS,
                Compra.PRECIO, Compra.FECHA));
        // Crear la tabla de artículos vendidos
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT UNIQUE NOT NULL,%s TEXT NOT NULL %s,%s INTEGER NOT NULL CHECK (%s>0),%s TEXT UNIQUE %s)",
                Tablas.ARTVENDIDOS, BaseColumns._ID, ArtVendido.ID, ArtVendido.ID_PRODUCTO, Referencias.ID_PRODUCTO,
                ArtVendido.CANTIDAD, ArtVendido.CANTIDAD, ArtVendido.ID_VENTA, Referencias.ID_VENTA));
        // Crear la tabla de artículos comprados
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT UNIQUE NOT NULL,%s TEXT NOT NULL %s,%s INTEGER NOT NULL CHECK (%s>0),%s TEXT UNIQUE %s)",
                Tablas.ARTCOMPRADOS, BaseColumns._ID, ArtComprado.ID, ArtComprado.ID_PRODUCTO, Referencias.ID_PRODUCTO,
                ArtComprado.CANTIDAD, ArtComprado.CANTIDAD, ArtComprado.ID_COMPRA, Referencias.ID_COMPRA));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP DATABASE IF EXISTS " + Tablas.USUARIOS);
        db.execSQL("DROP DATABASE IF EXISTS " + Tablas.PRODUCTOS);
        db.execSQL("DROP DATABASE IF EXISTS " + Tablas.PROVEEDORES);
        db.execSQL("DROP DATABASE IF EXISTS " + Tablas.VENTAS);
        db.execSQL("DROP DATABASE IF EXISTS " + Tablas.COMPRAS);
        db.execSQL("DROP DATABASE IF EXISTS " + Tablas.ARTVENDIDOS);
        db.execSQL("DROP DATABASE IF EXISTS " + Tablas.ARTCOMPRADOS);

        onCreate(db);
    }
}
