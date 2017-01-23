package miliziandevelopers.mydesignmaterial.base_de_datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import miliziandevelopers.mydesignmaterial.base_de_datos.Bbdd.Usuario;
import miliziandevelopers.mydesignmaterial.base_de_datos.Bbdd.Producto;
import miliziandevelopers.mydesignmaterial.base_de_datos.Bbdd.Proveedor;
import miliziandevelopers.mydesignmaterial.base_de_datos.Helper.Tablas;
import miliziandevelopers.mydesignmaterial.modelo.Productos;
import miliziandevelopers.mydesignmaterial.modelo.Proveedores;
import miliziandevelopers.mydesignmaterial.modelo.Usuarios;

/**
 * Created by Pedro on 19/01/2017.
 */

public class Operaciones {

    private static Helper dataBase;

    private static Operaciones instancia = new Operaciones();

    private Operaciones(){

    }

    public static Operaciones obtenerInstancia(Context context){
        if(dataBase == null){
            dataBase = new Helper(context);
        }
        return instancia;
    }

    // Operaciones Usuarios
    public String insertarUsuario(Usuarios user){
        SQLiteDatabase db = dataBase.getWritableDatabase();

        String idUsuario = Usuario.generarIdUsuarios();

        ContentValues valores = new ContentValues();
        valores.put(Usuario.ID, user.getId());
        valores.put(Usuario.NOMBRE, user.getNombre());
        valores.put(Usuario.PASSWORD, user.getPassword());
        valores.put(Usuario.AVATAR, user.getAvatar());

        db.insertOrThrow(Tablas.USUARIOS, null, valores);

        return idUsuario;
    }
    public boolean actualizarUsuario(Usuarios newUser){
        SQLiteDatabase db = dataBase.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Usuario.NOMBRE, newUser.getNombre());
        valores.put(Usuario.PASSWORD, newUser.getPassword());
        valores.put(Usuario.AVATAR, newUser.getAvatar());

        String whereClause = String.format("%s=?", Usuario.ID);
        String[] whereArgs = {newUser.getId()};

        int result = db.update(Tablas.USUARIOS, valores, whereClause, whereArgs);

        return result > 0;
    }
    public boolean eliminarUsuario(String id){
        SQLiteDatabase db = dataBase.getWritableDatabase();

        String whereClause = String.format("%s=?", Usuario.ID);
        String[] whereArgs = {id};

        int result = db.delete(Tablas.USUARIOS, whereClause, whereArgs);

        return result > 0;
    }
    public Cursor obtenerUsuarios(){
        SQLiteDatabase db = dataBase.getWritableDatabase();

        String[] columnas = new String[] {Usuario.ID, Usuario.NOMBRE, Usuario.PASSWORD, Usuario.AVATAR};

        Cursor cursor = db.query(Tablas.USUARIOS, columnas, null, null, null, null, null);

        return cursor;
    }
    // Para el LogIn
    public boolean comprobarUsuario(String nombre){
        boolean existe = true;
        SQLiteDatabase db = dataBase.getReadableDatabase();

        String sql = String.format("SELECT %s FROM %s WHERE %s=?", Usuario.PASSWORD, Tablas.USUARIOS, Usuario.NOMBRE);

        String[] columnas = new String[] {Usuario.PASSWORD};

        String[] selectionArgs = {nombre};

        //Cursor cursor = db.rawQuery(sql, selectionArgs);

        Cursor cursor = db.query(Tablas.USUARIOS, columnas, sql, selectionArgs, null, null, null);

        if(cursor.getCount() <= 0){
            existe = false;
        }else{
            existe = true;
        }

        return existe;
    }

    // Operaciones Productos
    public String insertarProducto(Productos product){
        SQLiteDatabase db = dataBase.getWritableDatabase();

        String idProducto = Producto.generarIdProductos();

        ContentValues valores = new ContentValues();
        valores.put(Producto.ID, product.getIdProducto());
        valores.put(Producto.NOMBRE, product.getNombre());
        valores.put(Producto.DESCRIPCION, product.getDescripcion());
        valores.put(Producto.CANTIDAD, product.getCantidad());
        valores.put(Producto.PRECIOVENTA, product.getPrecioVenta());
        valores.put(Producto.PRECIOCOMPRA, product.getPrecioCompra());
        valores.put(Producto.FOTO, product.getFoto());
        valores.put(Producto.NUM_PROVEEDOR, product.getNumProveedor());

        db.insertOrThrow(Tablas.PRODUCTOS, null, valores);

        return idProducto;
    }
    public boolean actualizarProducto(Productos newProduct){
        SQLiteDatabase db = dataBase.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Producto.ID, newProduct.getIdProducto());
        valores.put(Producto.NOMBRE, newProduct.getNombre());
        valores.put(Producto.DESCRIPCION, newProduct.getDescripcion());
        valores.put(Producto.CANTIDAD, newProduct.getCantidad());
        valores.put(Producto.PRECIOVENTA, newProduct.getPrecioVenta());
        valores.put(Producto.PRECIOCOMPRA, newProduct.getPrecioCompra());
        valores.put(Producto.FOTO, newProduct.getFoto());
        valores.put(Producto.NUM_PROVEEDOR, newProduct.getNumProveedor());

        String whereClause = String.format("%s=?", Producto.ID);
        String[] whereArgs = {newProduct.getIdProducto()};

        int result = db.update(Tablas.PRODUCTOS, valores, whereClause, whereArgs);

        return result > 0;
    }
    public boolean eliminarProducto(String id){
        SQLiteDatabase db = dataBase.getWritableDatabase();

        String whereClause = String.format("%s=?", Producto.ID);
        String[] whereArgs = {id};

        int result = db.delete(Tablas.PRODUCTOS, whereClause, whereArgs);

        return result > 0;
    }
    public Cursor obtenerProductos(){
        SQLiteDatabase db = dataBase.getWritableDatabase();

        String[] columnas = new String[] {Producto.NOMBRE, Producto.DESCRIPCION, Producto.CANTIDAD,
        Producto.PRECIOVENTA, Producto.PRECIOCOMPRA, Producto.FOTO, Producto.NUM_PROVEEDOR};

        Cursor cursor = db.query(Tablas.PRODUCTOS, columnas, null, null, null, null, null);

        return cursor;
    }

    // Operaciones Proveedores
    public String insertarProveedor(Proveedores prov){
        SQLiteDatabase db = dataBase.getWritableDatabase();

        String idProveedor = Bbdd.Proveedor.generarIdProveedores();

        ContentValues valores = new ContentValues();
        valores.put(Proveedor.ID, prov.getIdProveedor());
        valores.put(Proveedor.NOMBRE_EMPRESA, prov.getNombreEmpresa());
        valores.put(Proveedor.NUM_TELFNO, prov.getTelefono());
        valores.put(Proveedor.DIRECCION, prov.getDireccion());

        db.insertOrThrow(Tablas.PROVEEDORES, null, valores);

        return idProveedor;
    }
    public boolean actualizarProveedor(Proveedores newProv){
        SQLiteDatabase db = dataBase.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Proveedor.ID, newProv.getIdProveedor());
        valores.put(Proveedor.NOMBRE_EMPRESA, newProv.getNombreEmpresa());
        valores.put(Proveedor.NUM_TELFNO, newProv.getTelefono());
        valores.put(Proveedor.DIRECCION, newProv.getDireccion());

        String whereClause = String.format("%s=?", Proveedor.ID);
        String[] whereArgs = {newProv.getIdProveedor()};

        int result = db.update(Tablas.PROVEEDORES, valores, whereClause, whereArgs);

        return result > 0;
    }
    public boolean eliminarProveedor(String id){
        SQLiteDatabase db = dataBase.getWritableDatabase();

        String whereClause = String.format("%s=?", Proveedor.ID);
        String[] whereArgs = {id};

        int result = db.delete(Tablas.PROVEEDORES, whereClause, whereArgs);

        return result > 0;
    }
    public Cursor obtenerProveedores(){
        SQLiteDatabase db = dataBase.getWritableDatabase();

        // cambiar las columnas!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        String[] columnas = new String[] {Proveedor.ID, Proveedor.NOMBRE_EMPRESA, Proveedor.NUM_TELFNO,
                Proveedor.DIRECCION};

        Cursor cursor = db.query(Tablas.PROVEEDORES, columnas, null, null, null, null, null);

        return cursor;
    }

    // Llenar cursores
    public List<Usuarios> getUsersList(){
        List<Usuarios> list = new ArrayList<>();

        Cursor cursor = obtenerUsuarios();

        while(cursor.moveToNext()){
            Usuarios user = new Usuarios();

            user.setId(cursor.getString(0));
            user.setNombre(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            user.setAvatar(cursor.getString(3));

            list.add(user);
        }
        return list;
    }

    public Productos[] getProductosList(){
        List<Productos> list = new ArrayList<>();

        Cursor cursor = obtenerProductos();

        while(cursor.moveToNext()){
            Productos prod = new Productos();

            Cursor c = obtenerProductos();

            prod.setIdProducto(c.getString(0));
            prod.setNombre(c.getString(1));
            prod.setDescripcion(c.getString(2));
            prod.setCantidad(c.getInt(3));
            prod.setPrecioVenta(c.getInt(4));
            prod.setPrecioCompra(c.getInt(5));
            prod.setFoto(c.getString(6));
            prod.setNumProveedor(c.getString(7));

            list.add(prod);
        }
        return list.toArray(new Productos[list.size()]);
    }

    public List<Proveedores> getProvList(){
        List<Proveedores> list = new ArrayList<>();

        Cursor cursor = obtenerProveedores();

        while(cursor.moveToNext()){
            Proveedores prov = new Proveedores();

            prov.setIdProveedor(cursor.getString(0));
            prov.setNombreEmpresa(cursor.getString(1));
            prov.setTelefono(cursor.getString(2));
            prov.setDireccion(cursor.getString(3));

            list.add(prov);
        }
        return list;
    }

    // Al final
    public SQLiteDatabase getDB(){
        return dataBase.getWritableDatabase();
    }

}
