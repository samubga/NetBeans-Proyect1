/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package proyectounidad1;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.ButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 *
 * @author samuel
 */
public class VentanaElegirCliente extends javax.swing.JDialog {

    /**
     * Creates new form VentanaElegirCliente
     */
    
    
    private String nombreCompania = "";
    private int codigoCliente;
    Connection con = null;
    
    
    
     public VentanaElegirCliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
     
     
    public VentanaElegirCliente(java.awt.Frame parent, boolean modal, Connection con) {
        super(parent, modal);
        initComponents();
        this.con = con;
        cargarTabla();
        
        String consultaCargos = "SELECT NOMBRE FROM PUBLIC.CARGOS";
        try (PreparedStatement statement = con.prepareStatement(consultaCargos);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String nombreCargo = resultSet.getString("NOMBRE");

                // Crear un JRadioButton para cada cargo
                JRadioButton radioButton = new JRadioButton(nombreCargo);

                // Agregar el JRadioButton al buttonGroup1
                String seleccion = resultSet.getString("NOMBRE");
                if(seleccion == null){
                    seleccion = "";
                }else{
                    radioButton.setActionCommand(seleccion);
                }
                
                buttonGroup1.add(radioButton);
                

                // Agregar el JRadioButton al panelBotones
                panelCargos.add(radioButton);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Rellenar combobox de ciudades de los pedidos
        ArrayList<String> listaCiudadesPedidos = new ArrayList<>();
        String queryListaCiudadesPedidos = "SELECT nombre FROM Ciudades";
        try (Statement stmt2 = con.createStatement();
            ResultSet resultSet = stmt2.executeQuery(queryListaCiudadesPedidos)) {
            listaCiudadesPedidos.add("Elige una opción");
            while (resultSet.next()) {
               listaCiudadesPedidos.add(resultSet.getString("nombre"));
           }

           DefaultComboBoxModel<String> modeloComboCiudadesPedidos = new DefaultComboBoxModel<>(listaCiudadesPedidos.toArray(new String[0]));

           comboCiudad.setModel(modeloComboCiudadesPedidos);

           } catch (SQLException ex) {
               ex.printStackTrace();
           }
           tablaClientes.setRowSelectionAllowed(true);
           tablaClientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = tablaClientes.getSelectedRow();
                if (filaSeleccionada != -1 && e.getClickCount() == 1) {
                    Object valorNombreCompania = tablaClientes.getValueAt(filaSeleccionada, 0);
                    nombreCompania = (String) valorNombreCompania;

                    String queryCodigoCliente = "SELECT codigo FROM Clientes WHERE TRIM(nomcompania) = ?";
                    try (PreparedStatement preparedStatement = con.prepareStatement(queryCodigoCliente)) {
                        preparedStatement.setString(1, nombreCompania.trim());

                        try (ResultSet resultSet = preparedStatement.executeQuery()) {
                            while (resultSet.next()) {
                                // Obtén el código del cliente y haz algo con él
                                codigoCliente = resultSet.getInt("codigo");
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
           
    }

    private void cargarTabla() {
        String consultaDatosCliente = "SELECT c.NOMCOMPANIA AS Nombredelacompaniadelcliente,"
                                    + " c.NOMCONTACTO AS Nombredecontactodelcliente, "
                                    + "ci.NOMBRE AS Ciudad, "
                                    + "cg.NOMBRE AS Cargo "
                                    + "FROM PUBLIC.CLIENTES c JOIN PUBLIC.CIUDADES ci ON c.CODCIUDAD = ci.CODIGO LEFT JOIN PUBLIC.CARGOS cg ON c.CODCARGO = cg.CODIGO";

        // Crear el modelo de la tabla
        DefaultTableModel modeloTabla = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Hace que todas las celdas no sean editables
                }
            };
            
        tablaClientes.setModel(modeloTabla);
        try (PreparedStatement statement = con.prepareStatement(consultaDatosCliente);
            ResultSet resultSet = statement.executeQuery()) {

           // Obtener metadatos de columnas
           int columnCount = resultSet.getMetaData().getColumnCount();

           // Agregar nombres de columnas personalizados al modelo de la tabla
           String[] columnNames = {"Compañía del cliente", "Contacto del cliente", "Ciudad", "Cargo"};
           modeloTabla.setColumnIdentifiers(columnNames);

           // Agregar las filas al modelo de la tabla
           while (resultSet.next()) {
               Object[] fila = new Object[columnCount];
               for (int i = 1; i <= columnCount; i++) {
                   fila[i - 1] = resultSet.getObject(i);
               }
               modeloTabla.addRow(fila);
            }
            tablaClientes.setRowHeight(26);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        panelFormulario = new javax.swing.JPanel();
        companiaLabel = new javax.swing.JLabel();
        contactLabel = new javax.swing.JLabel();
        ciudadLabel = new javax.swing.JLabel();
        companiaTxt = new javax.swing.JTextField();
        contactTxt = new javax.swing.JTextField();
        comboCiudad = new javax.swing.JComboBox<>();
        panelCargos = new javax.swing.JPanel();
        panelBotones = new javax.swing.JPanel();
        buscarBoton = new javax.swing.JButton();
        reiniciarBoton = new javax.swing.JButton();
        botonElegirCliente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Elegir clientes");

        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaClientes);

        companiaLabel.setText("Nombre de la compañia");

        contactLabel.setText("Nombre de contacto");

        ciudadLabel.setText("Ciudad");

        comboCiudad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        panelCargos.setBorder(javax.swing.BorderFactory.createTitledBorder("Cargo"));
        panelCargos.setLayout(new java.awt.GridLayout(5, 3, 50, 0));

        javax.swing.GroupLayout panelFormularioLayout = new javax.swing.GroupLayout(panelFormulario);
        panelFormulario.setLayout(panelFormularioLayout);
        panelFormularioLayout.setHorizontalGroup(
            panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormularioLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ciudadLabel)
                    .addComponent(contactLabel)
                    .addComponent(companiaLabel))
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFormularioLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(companiaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelFormularioLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(contactTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(87, 87, 87)
                .addComponent(panelCargos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelFormularioLayout.setVerticalGroup(
            panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormularioLayout.createSequentialGroup()
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFormularioLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(companiaLabel)
                            .addComponent(companiaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(contactLabel)
                            .addComponent(contactTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ciudadLabel)
                            .addComponent(comboCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelFormularioLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(panelCargos, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        buscarBoton.setText("Buscar");
        buscarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarBotonActionPerformed(evt);
            }
        });

        reiniciarBoton.setText("Reiniciar");
        reiniciarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reiniciarBotonActionPerformed(evt);
            }
        });

        botonElegirCliente.setText("Elegir cliente");
        botonElegirCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonElegirClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBotonesLayout = new javax.swing.GroupLayout(panelBotones);
        panelBotones.setLayout(panelBotonesLayout);
        panelBotonesLayout.setHorizontalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesLayout.createSequentialGroup()
                .addGap(355, 355, 355)
                .addComponent(buscarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(reiniciarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(botonElegirCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(383, Short.MAX_VALUE))
        );
        panelBotonesLayout.setVerticalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buscarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reiniciarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonElegirCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelFormulario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 11, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buscarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarBotonActionPerformed
        String nombreCompania = companiaTxt.getText();
        String nombreContacto = contactTxt.getText();
        String ciudad = (String) comboCiudad.getSelectedItem();
        ButtonModel selectedButton = buttonGroup1.getSelection();
        String cargoSeleccionado;
        if(selectedButton == null){
            cargoSeleccionado = "";
        }
        else{
            cargoSeleccionado = selectedButton.getActionCommand();
        }
        
        String consultaDatosCliente = "SELECT c.NOMCOMPANIA AS Nombredelacompaniadelcliente,"
                                    + " c.NOMCONTACTO AS Nombredecontactodelcliente, "
                                    + "ci.NOMBRE AS Ciudad, "
                                    + "cg.NOMBRE AS Cargo "
                                    + "FROM PUBLIC.CLIENTES c JOIN PUBLIC.CIUDADES ci ON c.CODCIUDAD = ci.CODIGO LEFT JOIN PUBLIC.CARGOS cg ON c.CODCARGO = cg.CODIGO WHERE 1=1"; 
        
        if (!nombreCompania.isEmpty()) {
            consultaDatosCliente += " AND LOWER(c.NOMCOMPANIA) LIKE '%" + nombreCompania.toLowerCase() + "%' ";
        }
         if (!nombreContacto.isEmpty()) {
            consultaDatosCliente += " AND LOWER(c.NOMCONTACTO) LIKE '%" + nombreContacto.toLowerCase() + "%' ";
        }
        if (!"Elige una opción".equals(ciudad)) {
            consultaDatosCliente += " AND ci.NOMBRE = '" + ciudad + "' ";
        }
        if(selectedButton != null){
            consultaDatosCliente += " AND LOWER(cg.NOMBRE) = '" + cargoSeleccionado.toLowerCase() + "' ";
        }
         
         try (PreparedStatement statement = con.prepareStatement(consultaDatosCliente);
            ResultSet resultSet = statement.executeQuery()) {

           // Obtener metadatos de columnas
           int columnCount = resultSet.getMetaData().getColumnCount();
           DefaultTableModel modeloTabla = new DefaultTableModel();
            tablaClientes.setModel(modeloTabla);
           // Agregar nombres de columnas personalizados al modelo de la tabla
           String[] columnNames = {"Compañía del cliente", "Contacto del cliente", "Ciudad", "Cargo"};
           modeloTabla.setColumnIdentifiers(columnNames);

           // Agregar las filas al modelo de la tabla mediante un array de objetos
           while (resultSet.next()) {
               Object[] fila = new Object[columnCount];
               for (int i = 1; i <= columnCount; i++) {
                   fila[i - 1] = resultSet.getObject(i);
               }
               modeloTabla.addRow(fila);
            }
            tablaClientes.setRowHeight(26);

        } catch (SQLException e) {
            e.printStackTrace();
        }
         
    }//GEN-LAST:event_buscarBotonActionPerformed

    public String nombreCompania(){
       
        return nombreCompania;
    }
    
    public int codigoCliente(){
        return codigoCliente;
    }
    
    private void botonElegirClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonElegirClienteActionPerformed
        if(nombreCompania.equals("")){
                JOptionPane.showMessageDialog(null, "No se ha podido elegir el cliente" , "Error", JOptionPane.ERROR_MESSAGE);  
        }
         else{
              JOptionPane.showMessageDialog(null, "Cliente" + nombreCompania + " elegido exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
              dispose();
         }
    }//GEN-LAST:event_botonElegirClienteActionPerformed

    private void reiniciarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reiniciarBotonActionPerformed
        companiaTxt.setText("");
        contactTxt.setText("");
        comboCiudad.setSelectedIndex(0);
        buttonGroup1.clearSelection();
        cargarTabla();
    }//GEN-LAST:event_reiniciarBotonActionPerformed
    
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
            java.util.logging.Logger.getLogger(VentanaElegirCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaElegirCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaElegirCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaElegirCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VentanaElegirCliente dialog = new VentanaElegirCliente(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonElegirCliente;
    private javax.swing.JButton buscarBoton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel ciudadLabel;
    private javax.swing.JComboBox<String> comboCiudad;
    private javax.swing.JLabel companiaLabel;
    private javax.swing.JTextField companiaTxt;
    private javax.swing.JLabel contactLabel;
    private javax.swing.JTextField contactTxt;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelCargos;
    private javax.swing.JPanel panelFormulario;
    private javax.swing.JButton reiniciarBoton;
    private javax.swing.JTable tablaClientes;
    // End of variables declaration//GEN-END:variables
}
