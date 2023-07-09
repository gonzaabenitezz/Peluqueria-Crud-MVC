
package modelo;

import java.sql.DriverManager;
import java.sql.Connection;


public class Conexion {
    Connection con;
    public Connection getConnection(){
        String url="jdbc:mysql://localhost:3306/bd_peluqueria";
        String user="root";
        String pass="";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,user,pass);
        } catch (Exception e) {
        }
        return con;
    }
}
