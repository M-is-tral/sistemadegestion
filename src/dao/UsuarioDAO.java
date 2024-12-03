/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import modelo.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public boolean verificarUsuario(String nombreUsuario, String contrasena) {
        String sql = "SELECT * FROM Usuarios WHERE nombreUsuario = ? AND contrasena = ?";
        try (Connection conn = Conexion.getConexion();  
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombreUsuario);
            ps.setString(2, contrasena);
            ResultSet rs = ps.executeQuery();

            return rs.next();  
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM Usuarios";

        try (Connection conn = Conexion.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombreUsuario(rs.getString("nombreUsuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setCorreoElectronico(rs.getString("correoElectronico"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public boolean registrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuarios (nombreUsuario, nombre, apellido, telefono, correoElectronico, contrasena) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = Conexion.getConexion();  
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getApellido());
            ps.setString(4, usuario.getTelefono());
            ps.setString(5, usuario.getCorreoElectronico());
            ps.setString(6, usuario.getContrasena());
            
            int filasAfectadas = ps.executeUpdate();
            
            // Si filasAfectadas es mayor a 0, significa que la inserción fue exitosa
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}