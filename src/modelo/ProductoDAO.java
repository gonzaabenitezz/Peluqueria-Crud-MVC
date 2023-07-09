
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProductoDAO {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar(){
        List<Producto> datos = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Producto p = new Producto();
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setPrecio(rs.getString(3));
                p.setCantidad(rs.getString(4));
                datos.add(p);
            }
        } catch (Exception e) {
        }
        return datos;
    }
    
    public int agregar(Producto p){
        String sql = "INSERT INTO productos(Nombre,Precio,Cantidad)VALUES(?,?,?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getPrecio());
            ps.setString(3, p.getCantidad());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return 1;
    }
    
    public int Actualizar(Producto p){
        int r = 0;
        String sql = "update productos set Nombre=?, Precio=?, Cantidad=? where id=?";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getPrecio());
            ps.setString(3, p.getCantidad());
            ps.setInt(4, p.getId());
            r = ps.executeUpdate();
            if(r==1){
                return 1;
            }else {
                return 0;
            }
        } catch (Exception e) {
        }
        return r;
    }
    
    public void delete(int id){
        String sql = "delete from productos where id=" + id;
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
