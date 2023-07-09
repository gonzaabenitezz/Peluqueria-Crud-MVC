
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Producto;
import modelo.ProductoDAO;
import vista.Vista;

public class Controlador implements ActionListener{

    ProductoDAO dao = new ProductoDAO();
    Producto p  = new Producto();
    Vista vista = new Vista();
    DefaultTableModel modelo = new DefaultTableModel();

    
    public Controlador(Vista v){
        this.vista = v;
        this.vista.btnListar.addActionListener(this);
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnNuevo.addActionListener(this);
        listar(vista.tabla);
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnListar) {
            limpiarTabla();
            listar(vista.tabla);
            nuevo();
        }
        
        if(e.getSource() == vista.btnGuardar){
            guardar();
            limpiarTabla();
            listar(vista.tabla);
            nuevo();
        }
            
        if(e.getSource() == vista.btnEditar){
            int  fila = vista.tabla.getSelectedRow();
            if(fila == 1){
                JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
            }else{
                int id = Integer.parseInt((String)vista.tabla.getValueAt(fila,0).toString());
                String nom = (String)vista.tabla.getValueAt(fila, 1);
                String precio = (String)vista.tabla.getValueAt(fila, 2);
                String cant = (String)vista.tabla.getValueAt(fila, 3);
                vista.txtId.setText(""+id);
                vista.txtNombre.setText(nom);
                vista.txtPrecio.setText(precio);
                vista.txtCantidad.setText(cant);
            }
        }

        if(e.getSource() == vista.btnActualizar){
            Actualizar();
            limpiarTabla();
            listar(vista.tabla);
            nuevo();
        }
        
        if(e.getSource() == vista.btnEliminar){
            delete();
            limpiarTabla();
            listar(vista.tabla);
            nuevo();
        }
        
        if(e.getSource() == vista.btnNuevo){
            nuevo();
        }
    }
    
        public void nuevo() {
        vista.txtId.setText("");
        vista.txtNombre.setText("");
        vista.txtPrecio.setText("");
        vista.txtCantidad.setText("");
        vista.txtNombre.requestFocus();
        JOptionPane.showMessageDialog(vista, "campo de texto limpiados con exito");
    }
    
    public void delete(){
        int fila = vista.tabla.getSelectedRow();            
            
        if(fila == -1){
            JOptionPane.showMessageDialog(vista, "Debe seleccionar un usuario");
        }else{
            int id = Integer.parseInt((String)vista.tabla.getValueAt(fila, 0).toString());
            dao.delete(id);
            JOptionPane.showMessageDialog(vista,"Usuario eliminado");
        }
    }
    
    void limpiarTabla(){
        for (int i = 0; i < vista.tabla.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }
    
    public void Actualizar(){
        int id = Integer.parseInt(vista.txtId.getText());
        String nom = vista.txtNombre.getText();
        String precio = vista.txtPrecio.getText();
        String cant = vista.txtCantidad.getText();
        p.setId(id);
        p.setNombre(nom);
        p.setPrecio(precio);
        p.setCantidad(cant);
        int r = dao.Actualizar(p);
        if(r==1){
            JOptionPane.showMessageDialog(vista, "Usuario actualizado");
        }else {
            JOptionPane.showMessageDialog(vista, "Error al actualizar");
        }
    }
    
    public void guardar(){
        String nom = vista.txtNombre.getText();
        String precio = vista.txtPrecio.getText();
        String cant = vista.txtCantidad.getText();
        p.setNombre(nom);
        p.setPrecio(precio);
        p.setCantidad(cant);
        int r = dao.agregar(p);
        if(r==1){
            JOptionPane.showMessageDialog(vista, "Se agrego un producto con exito");
        }else {
            JOptionPane.showMessageDialog(vista, "Error al agregar un producto");
        }
    }
    
    public void listar(JTable tabla){
        modelo = (DefaultTableModel)tabla.getModel();
        List<Producto> lista = dao.listar();
        Object[] object = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNombre();
            object[2] = lista.get(i).getPrecio();
            object[3] = lista.get(i).getCantidad();
            modelo.addRow(object);
        }
        vista.tabla.setModel(modelo);
    }
    
}
