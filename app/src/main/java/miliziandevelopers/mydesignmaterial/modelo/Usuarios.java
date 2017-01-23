package miliziandevelopers.mydesignmaterial.modelo;

/**
 * Created by Pedro on 19/01/2017.
 */

public class Usuarios {

    private String id;
    private String nombre;
    private String password;
    private String avatar;

    public Usuarios(){

    }

    public Usuarios(String id, String nombre, String password, String avatar){
        this.id = id;
        this.nombre = nombre;
        this.password = password;
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
