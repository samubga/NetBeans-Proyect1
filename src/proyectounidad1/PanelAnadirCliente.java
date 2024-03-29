/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package proyectounidad1;


import java.sql.*;
import javax.swing.ButtonModel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.text.AbstractDocument;

/**
 *
 * @author samuel
 */
public class PanelAnadirCliente extends javax.swing.JPanel {

    /**
     * Creates new form PanelAnadirPedido
     */
    
    
    //Crear referencia al jFrame VentanaPrincipal
    private VentanaPrincipal principal;
    
    
    public PanelAnadirCliente(VentanaPrincipal principal) {
        initComponents();
        this.principal = principal;
         principal.rellenarSeleccionesBuscarCliente(comboPaises, listaCiudades);
        
        String consultaCargos = "SELECT NOMBRE FROM PUBLIC.CARGOS";
        try (PreparedStatement statement = principal.con.prepareStatement(consultaCargos);
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
        
        ((AbstractDocument) telfTxt.getDocument()).setDocumentFilter(new NumeroTelefonoDocumentFilter());
        

    }
             
    
    
    private int obtenerCodCiudadDesdeNombre(String nombreCiudad) {
        int codCiudad = 0; // Valor predeterminado en caso de no encontrar coincidencia

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
    
    private int obtenerCodCargoDesdeNombre(String nombreCargo) {
        int codCiudad = 0; // Valor predeterminado en caso de no encontrar coincidencia

        try (PreparedStatement preparedStatement = principal.con.prepareStatement("SELECT CODIGO FROM CARGOS WHERE NOMBRE = ?")) {
            preparedStatement.setString(1, nombreCargo);

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
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelFormularioAnadir = new javax.swing.JPanel();
        companiaLabel = new javax.swing.JLabel();
        companiaTxt = new javax.swing.JTextField();
        codPostalLabel = new javax.swing.JLabel();
        contactLabel = new javax.swing.JLabel();
        contactTxt = new javax.swing.JTextField();
        codPostalTxt = new javax.swing.JTextField();
        paisLabel = new javax.swing.JLabel();
        comboPaises = new javax.swing.JComboBox<>();
        ciudadLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaCiudades = new javax.swing.JList<>();
        telfLabel = new javax.swing.JLabel();
        telfTxt = new javax.swing.JTextField();
        direccionLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        direccionTxtA = new javax.swing.JTextArea();
        panelCargos = new javax.swing.JPanel();
        panelBotones = new javax.swing.JPanel();
        anadirBoton = new javax.swing.JButton();
        reiniciarBoton = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1080, 400));

        panelFormularioAnadir.setPreferredSize(new java.awt.Dimension(1080, 220));

        companiaLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        companiaLabel.setText("Compañía");

        codPostalLabel.setText("Código postal");

        contactLabel.setText("Contacto");

        codPostalTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codPostalTxtActionPerformed(evt);
            }
        });

        paisLabel.setText("País");

        comboPaises.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        ciudadLabel.setText("Ciudad");

        listaCiudades.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listaCiudades);

        telfLabel.setText("Teléfono");

        direccionLabel.setText("Dirección");

        direccionTxtA.setColumns(20);
        direccionTxtA.setRows(5);
        jScrollPane2.setViewportView(direccionTxtA);

        javax.swing.GroupLayout panelFormularioAnadirLayout = new javax.swing.GroupLayout(panelFormularioAnadir);
        panelFormularioAnadir.setLayout(panelFormularioAnadirLayout);
        panelFormularioAnadirLayout.setHorizontalGroup(
            panelFormularioAnadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormularioAnadirLayout.createSequentialGroup()
                .addGroup(panelFormularioAnadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(telfLabel)
                    .addComponent(ciudadLabel)
                    .addGroup(panelFormularioAnadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelFormularioAnadirLayout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addGroup(panelFormularioAnadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(companiaLabel)
                                .addComponent(contactLabel)
                                .addComponent(codPostalLabel)))
                        .addComponent(paisLabel, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormularioAnadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(comboPaises, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelFormularioAnadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(contactTxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                        .addComponent(companiaTxt, javax.swing.GroupLayout.Alignment.LEADING))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelFormularioAnadirLayout.createSequentialGroup()
                        .addGroup(panelFormularioAnadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(codPostalTxt, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(telfTxt, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(panelFormularioAnadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(direccionLabel)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        panelFormularioAnadirLayout.setVerticalGroup(
            panelFormularioAnadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormularioAnadirLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(panelFormularioAnadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(companiaLabel)
                    .addComponent(companiaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelFormularioAnadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contactTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(contactLabel))
                .addGap(18, 18, 18)
                .addGroup(panelFormularioAnadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codPostalLabel)
                    .addComponent(codPostalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelFormularioAnadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(telfTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(telfLabel))
                .addGap(28, 28, 28)
                .addGroup(panelFormularioAnadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboPaises, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paisLabel))
                .addGap(30, 30, 30)
                .addGroup(panelFormularioAnadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ciudadLabel)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelFormularioAnadirLayout.createSequentialGroup()
                        .addComponent(direccionLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelCargos.setBorder(javax.swing.BorderFactory.createTitledBorder("Cargo"));
        panelCargos.setLayout(new java.awt.GridLayout(5, 3));

        anadirBoton.setText("Añadir");
        anadirBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anadirBotonActionPerformed(evt);
            }
        });

        reiniciarBoton.setText("Reiniciar");
        reiniciarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reiniciarBotonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBotonesLayout = new javax.swing.GroupLayout(panelBotones);
        panelBotones.setLayout(panelBotonesLayout);
        panelBotonesLayout.setHorizontalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesLayout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(anadirBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(reiniciarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(253, Short.MAX_VALUE))
        );
        panelBotonesLayout.setVerticalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBotonesLayout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(anadirBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reiniciarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelFormularioAnadir, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(panelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(panelCargos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCargos, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelFormularioAnadir, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void codPostalTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codPostalTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codPostalTxtActionPerformed

    private void anadirBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anadirBotonActionPerformed
        try {                                            
            String queryInsertPedido = "INSERT INTO CLIENTES (IDCLIENTE, NOMCOMPANIA, NOMCONTACTO, CODCARGO, DIRECCION, CODCIUDAD, CODIGOPOSTAL, TELEFONO)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            
            ButtonModel selectedButton = buttonGroup1.getSelection();
            String cargoSeleccionado;
            
                
                int codCiudad = obtenerCodCiudadDesdeNombre(listaCiudades.getSelectedValue());
                String codeCiudadString = String.valueOf(codCiudad);
                System.out.println("codigo"+ codCiudad);
                String nombreCom = companiaTxt.getText();
                String nombreContact = contactTxt.getText();
                String direccionTexto = direccionTxtA.getText();
                String codigoPostalTexto = codPostalTxt.getText();
                String telefonoTexto = telfTxt.getText();
            if (selectedButton == null
                || companiaTxt.getText().equals("")
                || contactTxt.getText().equals("")
                || direccionTxtA.getText().equals("")
                || codPostalTxt.getText().equals("")    
                || telfTxt.getText().equals("")    
                || codeCiudadString.equals("0")
                    )
            {
                JOptionPane.showMessageDialog(null, "Rellena todos los campos para añadir un cliente", "Información", JOptionPane.ERROR_MESSAGE);

            } else {
                cargoSeleccionado = selectedButton.getActionCommand();
                int codCargo = obtenerCodCargoDesdeNombre(cargoSeleccionado);
                try (PreparedStatement preparedStatement = principal.con.prepareStatement(queryInsertPedido)) {
                    // Establecer valores para los parámetros
                    preparedStatement.setString(1, "");
                    preparedStatement.setString(2, nombreCom);
                    preparedStatement.setString(3, nombreContact);
                    preparedStatement.setInt(4, codCargo);
                    preparedStatement.setString(5, direccionTexto);
                    preparedStatement.setInt(6, codCiudad);
                    preparedStatement.setString(7, codigoPostalTexto);
                    preparedStatement.setString(8, telefonoTexto);

                    // Ejecutar la consulta
                    preparedStatement.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Cliente añadido correctamente al final de la tabla", "Información", JOptionPane.INFORMATION_MESSAGE);
                    
                } catch (SQLException ex) {
                     ex.printStackTrace();
                }
                
                    principal.cargarTablaAnadirCliente();
            }
                    
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_anadirBotonActionPerformed

    
    
    
    
    private void reiniciarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reiniciarBotonActionPerformed
        buttonGroup1.clearSelection();
        comboPaises.setSelectedIndex(0);
        companiaTxt.setText("");
        contactTxt.setText("");
        codPostalTxt.setText("");
        telfTxt.setText(" ");
        direccionTxtA.setText("");
        principal.cargarTablaAnadirCliente();
    }//GEN-LAST:event_reiniciarBotonActionPerformed

    
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton anadirBoton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel ciudadLabel;
    private javax.swing.JLabel codPostalLabel;
    private javax.swing.JTextField codPostalTxt;
    private javax.swing.JComboBox<String> comboPaises;
    private javax.swing.JLabel companiaLabel;
    private javax.swing.JTextField companiaTxt;
    private javax.swing.JLabel contactLabel;
    private javax.swing.JTextField contactTxt;
    private javax.swing.JLabel direccionLabel;
    private javax.swing.JTextArea direccionTxtA;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listaCiudades;
    private javax.swing.JLabel paisLabel;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelCargos;
    private javax.swing.JPanel panelFormularioAnadir;
    private javax.swing.JButton reiniciarBoton;
    private javax.swing.JLabel telfLabel;
    private javax.swing.JTextField telfTxt;
    // End of variables declaration//GEN-END:variables
}
