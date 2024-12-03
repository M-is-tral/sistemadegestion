/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import modelo.Producto;
import dao.ProductoDAO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FormularioProducto extends JFrame {
    private JTextField txtNombre, txtMarca, txtCategoria, txtPrecio, txtCantidad;
    private JButton btnGuardar, btnCancelar;
    private Producto producto;

    public FormularioProducto(Producto producto) {
        this.producto = producto;
        setTitle(producto == null ? "Nuevo Producto" : "Editar Producto");
        setLayout(new GridLayout(6, 2));
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        
        add(new JLabel("Nombre:"));
        txtNombre = new JTextField(producto != null ? producto.getNombre() : "");
        add(txtNombre);

        add(new JLabel("Marca:"));
        txtMarca = new JTextField(producto != null ? producto.getMarca() : "");
        add(txtMarca);

        add(new JLabel("Categoría:"));
        txtCategoria = new JTextField(producto != null ? producto.getCategoria() : "");
        add(txtCategoria);

        add(new JLabel("Precio:"));
        txtPrecio = new JTextField(producto != null ? String.valueOf(producto.getPrecio()) : "");
        add(txtPrecio);

        add(new JLabel("Cantidad Disponible:"));
        txtCantidad = new JTextField(producto != null ? String.valueOf(producto.getCantidadDisponible()) : "");
        add(txtCantidad);

        
        btnGuardar = new JButton(producto == null ? "Guardar" : "Actualizar");
        btnCancelar = new JButton("Cancelar");

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarProducto();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        add(btnGuardar);
        add(btnCancelar);

        setLocationRelativeTo(null);
        setVisible(true);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
private void guardarProducto() {
        try {
            String nombre = txtNombre.getText();
            String marca = txtMarca.getText();
            String categoria = txtCategoria.getText();
            double precio = Double.parseDouble(txtPrecio.getText());
            int cantidad = Integer.parseInt(txtCantidad.getText());

            Producto producto = new Producto(this.producto != null ? this.producto.getId() : 0, nombre, marca, categoria, precio, cantidad);
            ProductoDAO productoDAO = new ProductoDAO();

            if (this.producto == null) {
                productoDAO.registrarProducto(producto);
            } else {
                productoDAO.actualizarProducto(producto);
            }
            JOptionPane.showMessageDialog(this, "Producto guardado con éxito");
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el producto: " + e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormularioProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        // Cuando necesitas pasar un Producto específico:
Producto producto = new Producto(1, "Nombre Producto", "Marca", "Categoria", 100.0, 10);
java.awt.EventQueue.invokeLater(new Runnable() {
    public void run() {
        new FormularioProducto(producto).setVisible(true);
    }
});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
