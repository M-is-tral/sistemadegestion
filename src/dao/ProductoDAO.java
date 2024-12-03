/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import modelo.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductoDAO {

    public boolean registrarProducto(Producto producto) {
    String sql = "INSERT INTO Productos (nombre, marca, categoria, precio, cantidadDisponible) VALUES (?, ?, ?, ?, ?)";
    try (Connection conn = Conexion.getConexion();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, producto.getNombre());
        ps.setString(2, producto.getMarca());
        ps.setString(3, producto.getCategoria());
        ps.setDouble(4, producto.getPrecio());
        ps.setInt(5, producto.getCantidadDisponible());

        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        System.err.println("Error al registrar el producto: " + e.getMessage());
        return false;
    }
} 
public boolean actualizarProducto(Producto producto) {
    String sql = "UPDATE Productos SET nombre = ?, marca = ?, categoria = ?, precio = ?, cantidadDisponible = ? WHERE id = ?";
    try (Connection conn = Conexion.getConexion();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, producto.getNombre());
        ps.setString(2, producto.getMarca());
        ps.setString(3, producto.getCategoria());
        ps.setDouble(4, producto.getPrecio());
        ps.setInt(5, producto.getCantidadDisponible());
        ps.setInt(6, producto.getId());

        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        System.err.println("Error al actualizar el producto: " + e.getMessage());
        return false;
    }
}

    public List<Producto> obtenerProductos() {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM Productos";
        try (Connection con = Conexion.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                Producto producto = new Producto(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("marca"),
                    rs.getString("categoria"),
                    rs.getDouble("precio"),
                    rs.getInt("cantidadDisponible")
                );
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    public void eliminarProducto(int id) {
        String query = "DELETE FROM Productos WHERE id = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


