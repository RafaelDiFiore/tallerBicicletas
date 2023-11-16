/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bd;

/**
 *
 * @author Rafael Diaz
 */

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Dante
 */
public class bd {
    

    public Connection obtenerConeccion() {
        Connection cnx = null;
        String url, usu, pass;

        url = "jdbc:mysql://localhost:3306/cliente";
        usu = "root";
        pass = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            cnx = DriverManager.getConnection(url, usu, pass);
            System.out.println("Conexión exitosa");
        } catch (Exception e) {
            System.out.println("NO se pudo conectar: " + e.getMessage());
        }
        return cnx;
    }
}
