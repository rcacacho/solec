package com.solec.presupuesto.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author elfo_
 */
public class Conexion {
    Connection conn;
    private String host = "localhost";
    private String port = "3306";
    private String db = "solec";
    private String user = "root";
    private String password = "";
    
    public Conexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://" +host + ":" + port + "/" + db;
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion exitosa");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error en la conexion error: " + e.toString());
        }
    }
}
