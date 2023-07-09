
package modelo;


public class Producto {
    int id;
    String Nombre;
    String Precio;
    String Cantidad;

    public Producto() {
    }

    public Producto(int id, String Nombre, String Precio, String Cantidad) {
        this.id = id;
        this.Nombre = Nombre;
        this.Precio = Precio;
        this.Cantidad = Cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String Precio) {
        this.Precio = Precio;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String Cantidad) {
        this.Cantidad = Cantidad;
    }    
    
          
}
