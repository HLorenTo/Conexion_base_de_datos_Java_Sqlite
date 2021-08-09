package Controlador;
import Modelo.Conexion;
import Modelo.Producto;
import Vista.Reto5;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Controlador implements ActionListener{
    Conexion conectar = new Conexion();
    Connection connect = conectar.getConexion();
    
    private Producto mod;
    private Reto5 vista;
    
    public Controlador (Producto mod, Reto5 vista){
        this.mod = mod;
        this.vista = vista;
        this.vista.btnguardar.addActionListener(this);
        this.vista.btnactualizar.addActionListener(this);
        this.vista.btneliminar.addActionListener(this);
        this.vista.btnconsultar.addActionListener(this);
        this.vista.combocategoria.addActionListener(this);
        this.vista.combocategoriaconsultar.addActionListener(this);
        this.vista.txtcantidadconsultar.addActionListener(this);
        this.vista.txtnombreconsultar.addActionListener(this);
        this.vista.txtprecioconsultar.addActionListener(this);
        
    }
    public void iniciar(){
        vista.setLocationRelativeTo(null); //centrar la ventana en medio de la pantalla
        vista.setTitle("MVC SUPERMERCADO BD");
        vista.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        DefaultTableModel model = (DefaultTableModel) vista.tabla.getModel();
        
        if(e.getSource()== vista.btnconsultar){
            try {
                model.setRowCount(0);
                ResultSet rs = null;
                PreparedStatement pst = connect.prepareStatement("SELECT id,nombre,categoria,cantidad,precio FROM productos"); //que vamos a declarar la query consultar
                rs = pst.executeQuery();
                
                while (rs.next()) {
                    model.addRow(new Object[]{rs.getInt("id"), rs.getString("nombre"), rs.getString("categoria"), rs.getString("cantidad"), rs.getInt("precio")});
                }
                
            } catch (Exception ee) {
                    JOptionPane.showMessageDialog(null, "Conectese a la DB.");
            }
        }   else if (e.getSource() == vista.btnguardar) {
            try {
                model.setRowCount(0);
                PreparedStatement pst = connect.prepareStatement("INSERT INTO productos (nombre,categoria,cantidad,precio) VALUES (?,?,?,?)");
                pst.setString(1, vista.txtnombre.getText());
                pst.setString(2, vista.combocategoria.getSelectedItem().toString());
                pst.setString(3, vista.txtcantidad.getText());
                pst.setDouble(4, Double.parseDouble(vista.txtprecio.getText()));
                pst.execute();
                JOptionPane.showMessageDialog(null, "Datos guardados");

            } catch (Exception ee) {
                JOptionPane.showMessageDialog(null, "Ingrese los datos a guardar.");
            }     
        } else if (e.getSource() == vista.btneliminar) {
            try {
                model.setRowCount(0);
                PreparedStatement pst = connect.prepareStatement("DELETE FROM productos WHERE id=?");
                pst.setInt(1, Integer.parseInt(vista.txtid.getText()));
                pst.execute();
                JOptionPane.showMessageDialog(null, "Datos eliminados.");
            } catch (Exception ee) {
                JOptionPane.showMessageDialog(null, "Ingrese el id a eliminar.");
            }
        } else if (e.getSource() == vista.btnactualizar) {
            try {
            model.setRowCount(0);
            PreparedStatement pst = connect.prepareStatement("UPDATE productos SET nombre=?, categoria=?, cantidad=?, precio=? WHERE id=?");
            pst.setString(1, vista.txtnombreconsultar.getText());
            pst.setString(2, vista.combocategoriaconsultar.getSelectedItem().toString());
            pst.setString(3, vista.txtcantidadconsultar.getText());
            pst.setDouble(4, Double.parseDouble(vista.txtprecioconsultar.getText()));
            pst.setInt(5, Integer.parseInt(vista.txtid.getText()));
            pst.execute();
            JOptionPane.showMessageDialog(null, "Datos actualizados.");
        } catch (Exception ee) {
            JOptionPane.showMessageDialog(null, "Ingrese los datos correctos");   
}
        }
    }
}