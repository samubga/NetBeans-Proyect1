/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package proyectounidad1;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.text.AbstractDocument;

/**
 *
 * @author samuel
 */
public class PanelAnadirPedido extends javax.swing.JPanel {

    /**
     * Creates new form PanelAnadirPedido
     */
    
    
    private int codigoCliente;
    private String nombreCompania = "";
    //Crear referencia al jFrame VentanaPrincipal
    private VentanaPrincipal principal;
    
    
    public PanelAnadirPedido(VentanaPrincipal principal) {
        initComponents();
        this.principal = principal;
        principal.rellenarListasVentanasAnadirModificar(comboCiudades, listaPaises, listaEmpleados);
        
        //Cuando le das con el click izquierdo al jTextField de cliente, se abrirá la ventana para elegir un cliente
        clienteTxt.setEditable(false); 
        clienteTxt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) { // Verificar clic izquierdo
                    VentanaElegirCliente vc = new VentanaElegirCliente(principal, true, principal.con);
                    vc.setVisible(true);
                    nombreCompania =  vc.nombreCompania();
                    codigoCliente = vc.codigoCliente();
                    clienteTxt.setText(nombreCompania);
                }
            }
        });
        
        //Convertir fechas al formato YYYY-MM-DD
        ((AbstractDocument) fpedidoTxt.getDocument()).setDocumentFilter(new DateDocumentFilter());
        ((AbstractDocument) fenvioTxt.getDocument()).setDocumentFilter(new DateDocumentFilter());
        ((AbstractDocument) fentregaTxt.getDocument()).setDocumentFilter(new DateDocumentFilter());
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new javax.swing.JPanel();
        codPostalLabel = new javax.swing.JLabel();
        codPostalTxt = new javax.swing.JTextField();
        cargoLabel = new javax.swing.JLabel();
        cargoTxt = new javax.swing.JTextField();
        fpedidoLabel = new javax.swing.JLabel();
        fenvioLabel = new javax.swing.JLabel();
        fpedidoTxt = new javax.swing.JTextField();
        fenvioTxt = new javax.swing.JTextField();
        direccionLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        direccionTxtA = new javax.swing.JTextArea();
        fentregaLabel = new javax.swing.JLabel();
        fentregaTxt = new javax.swing.JTextField();
        clienteLabel = new javax.swing.JLabel();
        clienteTxt = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        listaEmpleados = new javax.swing.JList<>();
        empleadoLabel = new javax.swing.JLabel();
        panel2 = new javax.swing.JPanel();
        ciudadLabel = new javax.swing.JLabel();
        comboCiudades = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        listaPaises = new javax.swing.JList<>();
        paisLabel = new javax.swing.JLabel();
        panelBotones = new javax.swing.JPanel();
        botonAnadir = new javax.swing.JButton();
        botonReiniciar = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1080, 400));

        codPostalLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        codPostalLabel.setText("Código postal");

        cargoLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cargoLabel.setText("Cargo (total€)");

        fpedidoLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        fpedidoLabel.setText("Fecha pedido");

        fenvioLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        fenvioLabel.setText("Fecha envio");

        fpedidoTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fpedidoTxtActionPerformed(evt);
            }
        });

        direccionLabel.setText("Dirección");

        direccionTxtA.setColumns(20);
        direccionTxtA.setRows(5);
        jScrollPane1.setViewportView(direccionTxtA);

        fentregaLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        fentregaLabel.setText("Fecha entrega");

        clienteLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        clienteLabel.setText("Cliente");

        clienteTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clienteTxtActionPerformed(evt);
            }
        });

        listaEmpleados.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane5.setViewportView(listaEmpleados);

        empleadoLabel.setText("Empleado");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panel1Layout.createSequentialGroup()
                            .addGap(41, 41, 41)
                            .addComponent(direccionLabel))
                        .addComponent(codPostalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(clienteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cargoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(clienteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codPostalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cargoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(fpedidoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fpedidoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(fentregaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fenvioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(empleadoLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(fentregaTxt, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                            .addComponent(fenvioTxt))))
                .addGap(94, 94, 94))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(direccionLabel)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fpedidoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fpedidoLabel))
                        .addGap(25, 25, 25)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fenvioLabel)
                            .addComponent(fenvioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fentregaLabel)
                            .addComponent(fentregaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cargoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cargoLabel))
                                .addGap(30, 30, 30)
                                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(codPostalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(codPostalLabel))
                                .addGap(35, 35, 35)
                                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(clienteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(clienteLabel)))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(empleadoLabel)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        ciudadLabel.setText("Ciudad");

        comboCiudades.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        listaPaises.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(listaPaises);

        paisLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        paisLabel.setText("País");

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(ciudadLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboCiudades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(paisLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(paisLabel)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboCiudades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ciudadLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        botonAnadir.setText("Añadir");
        botonAnadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAnadirActionPerformed(evt);
            }
        });

        botonReiniciar.setText("Reiniciar");
        botonReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonReiniciarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBotonesLayout = new javax.swing.GroupLayout(panelBotones);
        panelBotones.setLayout(panelBotonesLayout);
        panelBotonesLayout.setHorizontalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBotonesLayout.createSequentialGroup()
                .addContainerGap(348, Short.MAX_VALUE)
                .addComponent(botonAnadir, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(botonReiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(137, 137, 137))
        );
        panelBotonesLayout.setVerticalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesLayout.createSequentialGroup()
                .addGroup(panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAnadir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonReiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void botonAnadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAnadirActionPerformed
        try {                                            
            String queryInsertPedido = "INSERT INTO PEDIDOS (IDCLIENTE, CODCLIENTE, CODEMPLEADO, FECHAPEDIDO, FECHAENTREGA, FECHAENVIO, CARGO, DIRECCION, CODCIUDAD, CODPOSTAL, CODEMPREENVIO) "
                    + "VALUES (?,?,?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            //Obtener tipos de valores correctos para añadir a la base de datos
            String nombreEmpleado = listaEmpleados.getSelectedValue();
            int codeEmpleado = obtenerCodeEmpleadoDesdeNombre(nombreEmpleado);
            String codeEmpleadoString = String.valueOf(codeEmpleado);
            
            String nombreCiudad = comboCiudades.getSelectedItem().toString();
            int codCiudad = obtenerCodCiudadDesdeNombre(nombreCiudad);
            
            String cargoTextValue = cargoTxt.getText().trim(); // Eliminar espacios en blanco al principio y al final
            
            //Compropbar que esten rellenados todos los campos
            if(codeEmpleadoString.equals("")||
                nombreCiudad.equals("Este país no tiene ciudades") ||
                nombreCiudad.equals("Selecciona un país") ||
                nombreEmpleado == null ||
                nombreCiudad == null 
                ||cargoTextValue.equals("")
                || codPostalTxt.getText().equals("")
                || direccionTxtA.getText().equals("")
                || fpedidoTxt.getText().equals("")
                || fentregaTxt.getText().equals("")
                || fentregaTxt.getText().equals(""))
            {
                 JOptionPane.showMessageDialog(null, "Rellena todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                //Comprobar si el cargo es un número para evitar errores
                if(esNumero(cargoTextValue)){
                    
                    //Obtener tipos de valores correctos para añadir a la base de datos, sobre todo las fechas
                    double cargoValue = Double.parseDouble(cargoTextValue);
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
                    java.util.Date fechaPedidoUtil = dateFormat.parse(fpedidoTxt.getText());
                    java.util.Date fechaEntregaUtil = dateFormat.parse(fentregaTxt.getText());
                    java.util.Date fechaEnvioUtil = dateFormat.parse(fenvioTxt.getText());
                    Date fechaPedidoSql = new Date(fechaPedidoUtil.getTime());
                    Date fechaEntregaSql = new Date(fechaEntregaUtil.getTime());
                    Date fechaEnvioSql = new Date(fechaEnvioUtil.getTime());

                    try (PreparedStatement preparedStatement = principal.con.prepareStatement(queryInsertPedido)) {
                        // Establecer valores para los parámetros
                        preparedStatement.setString(1, "");
                        preparedStatement.setInt(2, codigoCliente);
                        preparedStatement.setInt(3, codeEmpleado);
                        preparedStatement.setDate(4, fechaPedidoSql);
                        preparedStatement.setDate(5, fechaEntregaSql);
                        preparedStatement.setDate(6, fechaEnvioSql);
                        preparedStatement.setDouble(7, cargoValue);
                        preparedStatement.setString(8, direccionTxtA.getText());
                        preparedStatement.setInt(9, codCiudad); 
                        preparedStatement.setString(10, codPostalTxt.getText());
                        preparedStatement.setInt(11, 2);

                        // Ejecutar la consulta
                        preparedStatement.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Pedido añadido correctamente al final de la tabla", "Información", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    principal.cargarTablaAnadir();
                    
                } else{
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese solo números en Cargo", "Error", JOptionPane.ERROR_MESSAGE);
                }            
           }
        } catch (ParseException ex) {
             JOptionPane.showMessageDialog(null, "Ingresa la fecha completa", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botonAnadirActionPerformed
    
    
    
    private int obtenerCodeEmpleadoDesdeNombre(String nombreEmpleado) {
        int codeEmpleado = 0; // Valor predeterminado en caso de no encontrar coincidencia

        //Obtener el codigo del empleado donde su nombre sea el que nos pasen por parámetro
        try (PreparedStatement preparedStatement = principal.con.prepareStatement("SELECT CODIGO FROM EMPLEADOS WHERE NOMBRE = ?")) {
            preparedStatement.setString(1, nombreEmpleado);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    codeEmpleado = resultSet.getInt("CODIGO");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return codeEmpleado;
    }

    
    
    
    private int obtenerCodCiudadDesdeNombre(String nombreCiudad) {
        int codCiudad = 0; // Valor predeterminado en caso de no encontrar coincidencia

        // Suponiendo que tienes una conexión a la base de datos llamada 'con'
        try (PreparedStatement preparedStatement = principal.con.prepareStatement("SELECT CODIGO FROM CIUDADES WHERE NOMBRE = ?")) {
            preparedStatement.setString(1, nombreCiudad);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    codCiudad = resultSet.getInt("CODIGO");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return codCiudad;
    }
    
    
    
    public boolean esNumero(String texto) {
        try {
            Integer.parseInt(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    
    
    private void clienteTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clienteTxtActionPerformed
        
    }//GEN-LAST:event_clienteTxtActionPerformed

    private void botonReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonReiniciarActionPerformed
        cargoTxt.setText("");
        clienteTxt.setText("");
        codPostalTxt.setText("");
        direccionTxtA.setText("");
        fentregaTxt.setText("");
        fenvioTxt.setText("");
        fpedidoTxt.setText("");
        listaEmpleados.clearSelection();
        listaPaises.clearSelection();
        comboCiudades.setSelectedIndex(0);
        DefaultComboBoxModel<String> modeloComboCiudades = new DefaultComboBoxModel<>();
        modeloComboCiudades.addElement("Selecciona un país");
        comboCiudades.setModel(modeloComboCiudades);
        principal.cargarTablaAnadir();
    }//GEN-LAST:event_botonReiniciarActionPerformed

    private void fpedidoTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fpedidoTxtActionPerformed
        
    }//GEN-LAST:event_fpedidoTxtActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAnadir;
    private javax.swing.JButton botonReiniciar;
    private javax.swing.JLabel cargoLabel;
    private javax.swing.JTextField cargoTxt;
    private javax.swing.JLabel ciudadLabel;
    private javax.swing.JLabel clienteLabel;
    private javax.swing.JTextField clienteTxt;
    private javax.swing.JLabel codPostalLabel;
    private javax.swing.JTextField codPostalTxt;
    private javax.swing.JComboBox<String> comboCiudades;
    private javax.swing.JLabel direccionLabel;
    private javax.swing.JTextArea direccionTxtA;
    private javax.swing.JLabel empleadoLabel;
    private javax.swing.JLabel fentregaLabel;
    private javax.swing.JTextField fentregaTxt;
    private javax.swing.JLabel fenvioLabel;
    private javax.swing.JTextField fenvioTxt;
    private javax.swing.JLabel fpedidoLabel;
    private javax.swing.JTextField fpedidoTxt;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JList<String> listaEmpleados;
    private javax.swing.JList<String> listaPaises;
    private javax.swing.JLabel paisLabel;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private javax.swing.JPanel panelBotones;
    // End of variables declaration//GEN-END:variables
}
