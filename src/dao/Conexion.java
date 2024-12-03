/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

   private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=SistemaAlmacen;encrypt=true;trustServerCertificate=true";
    private static final String USER = "Belphegor";
    private static final String PASSWORD = "MoslerMT900";

    public static Connection getConexion() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa");
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
        return connection;
    }
public static Connection conectar() {
        return getConexion();
}
    
    public static void main(String[] args) {
        Connection conn = Conexion.getConexion();
        if (conn != null) {
            System.out.println("Conexión exitosa.");
        } else {
            System.out.println("Conexión fallida.");
        }
    }
}
