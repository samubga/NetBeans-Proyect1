/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package proyectounidad1;
import java.awt.Color;
import java.net.URL;
import java.sql.*;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
/**
 *
 * @author samuel
 */
public class VentanaDetallesCliente extends javax.swing.JDialog {

    /**
     * Creates new form VentanaDetalles
     */
    
    
    private VentanaPrincipal principal;
    private int codigoCliente;
    
    
    public VentanaDetallesCliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
     public VentanaDetallesCliente(java.awt.Frame parent, boolean modal, int codigoCliente, VentanaPrincipal principal) {
        super(parent, modal);
        initComponents();
        this.codigoCliente = codigoCliente;
        this.principal = principal;
        infoLabel.setText("Información detallada acerca del cliente numero " + codigoCliente);
        cargarTablaDetallada();
        
    }
     
    public void cargarTablaDetallada(){
            
        //------------Tabla con detalles de los pedidos que ha realizado el cliente---------------

        String queryDetalles = "SELECT P.FECHAPEDIDO AS FECHA_PEDIDO, P.FECHAENTREGA AS FECHA_ENTREGA, P.FECHAENVIO AS FECHA_ENVIO,"
                + " P.CARGO AS CARGO_PEDIDO, CE.NOMBRE AS NOMBRE_COMP_ENVIO, E.NOMBRE AS NOMBRE_EMPLEADO FROM PUBLIC.PEDIDOS P "
                + "LEFT JOIN PUBLIC.COMPENVIOS CE ON P.CODEMPREENVIO = CE.CODIGO "
                + "LEFT JOIN PUBLIC.EMPLEADOS E ON P.CODEMPLEADO = E.CODIGO WHERE P.CODCLIENTE = '" + codigoCliente + "'";
        try {
            Statement stmtDetalles = VentanaPrincipal.con.createStatement();
            ResultSet rsDetalles = stmtDetalles.executeQuery(queryDetalles);

            DefaultTableModel detallesModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hace que todas las celdas no sean editables
            }
            };
            pedidosEncTabla.setRowSelectionAllowed(false);
            pedidosEncTabla.setCellSelectionEnabled(true);
            // Nombres de las columnas para los detalles
            String[] detallesColumnNames = {"Fecha Pedido", "Fecha Envío", "Fecha Entrega", "Cargo (€)", "Empresa de envío", "Empleado"};

            // Asigna los nombres de las columnas al modelo de tabla de detalles
            detallesModel.setColumnIdentifiers(detallesColumnNames);

            // Recorre el resultado de la consulta de detalles y agrega las filas al modelo de detalles
            while (rsDetalles.next()) {
                Object[] detallesRowData = {
                    rsDetalles.getDate("FECHAPEDIDO"),
                    rsDetalles.getDate("FECHA_ENVIO"),
                    rsDetalles.getDate("FECHA_ENTREGA"),
                    rsDetalles.getDouble("CARGO_PEDIDO"),
                    rsDetalles.getString("NOMBRE_COMP_ENVIO"),
                    rsDetalles.getString("NOMBRE_EMPLEADO"),
                };
                detallesModel.addRow(detallesRowData);
            }
            
            
            
           

            pedidosEncTabla.setModel(detallesModel);
            
            //Ajustar celdas
            pedidosEncTabla.setRowHeight(50);
            /*TableColumnModel columnModelDetallada = jTableDetalles.getColumnModel();
            columnModelDetallada.getColumn(0).setPreferredWidth(150);
            columnModelDetallada.getColumn(1).setPreferredWidth(20);
            columnModelDetallada.getColumn(2).setPreferredWidth(20);*/


            // Cierra los recursos para los detalles
            rsDetalles.close();
            stmtDetalles.close();


            //----------Tabla con detalles del cliente--------------
                
            String query = "SELECT C.CODIGO AS CODIGOCLIENTE, C.NOMCOMPANIA AS NOMCOMPANIA, C.NOMCONTACTO AS NOMCONTACTO,"
                + " CA.NOMBRE AS NOMBRE_CARGO, C.DIRECCION AS DIRECCION, CI.NOMBRE AS NOMBRE_CIUDAD, C.CODIGOPOSTAL AS CODIGOPOSTAL, "
                + "C.TELEFONO AS TELEFONO FROM PUBLIC.CLIENTES C LEFT JOIN PUBLIC.CARGOS CA ON C.CODCARGO = CA.CODIGO LEFT JOIN PUBLIC.CIUDADES CI ON C.CODCIUDAD = CI.CODIGO "
                +  "WHERE C.CODIGO = '" + codigoCliente + "'";
            
                Statement stmt = VentanaPrincipal.con.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                // Hace que no sean editables las celdas
                DefaultTableModel model = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Hace que todas las celdas no sean editables
                }
                };
                
                infoDetalladaTabla.setRowSelectionAllowed(false);
                infoDetalladaTabla.setCellSelectionEnabled(true);
                infoDetalladaTabla.setModel(model);
                 
                // Limpia el contenido actual de la JTable
                model.setRowCount(0);
                String[] columnNames = {
                    "Compañia", "Contacto", "Cargo",
                    "Dirección", "Ciudad", "Código postal", "Teléfono",
                };

                // Asigna los nombres de las columnas al modelo de tabla
                model.setColumnIdentifiers(columnNames);
                
                // Recorre el resultado de la consulta y agrega las filas a la JTable
                while (rs.next()) {
                    Object[] rowData = {
                        rs.getString("NOMCOMPANIA"),
                        rs.getString("NOMCONTACTO"),
                        rs.getString("NOMBRE_CARGO"),
                        rs.getString("DIRECCION"),
                        rs.getString("NOMBRE_CIUDAD"),
                        rs.getString("CODIGOPOSTAL"),
                        rs.getString("TELEFONO"),
                    };
                    model.addRow(rowData);
                }
                TableColumnModel columnModel = infoDetalladaTabla.getColumnModel();

                // Tamaño preferido para todas las columnas
                int preferredWidth = 70;

                // Itera sobre todas las columnas y establece el mismo tamaño preferido
                for (int i = 0; i < columnModel.getColumnCount(); i++) {
                    columnModel.getColumn(i).setPreferredWidth(preferredWidth);
                }
                //Quitar borde del jPanel
                jScrollPane1.setBorder(null);
                infoDetalladaTabla.setRowHeight(50);
                // Cierra los recursos
                rs.close();
                stmt.close();
                
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        infoDetalladaTabla = new javax.swing.JTable();
        infoLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        pedidosEncTabla = new javax.swing.JTable();
        label2 = new javax.swing.JLabel();
        imagenLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Detalles cliente");

        infoDetalladaTabla.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(infoDetalladaTabla);

        infoLabel.setText("Información detallada acerca del cliente");

        pedidosEncTabla.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(pedidosEncTabla);

        label2.setText("Pedidos encargados por el cliente");

        imagenLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/clientesEscalado.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(infoLabel)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(256, 256, 256))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label2)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(imagenLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(infoLabel)
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(label2)
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(imagenLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(VentanaDetallesCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaDetallesCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaDetallesCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaDetallesCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VentanaDetallesCliente dialog = new VentanaDetallesCliente(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel imagenLabel;
    private javax.swing.JTable infoDetalladaTabla;
    private javax.swing.JLabel infoLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel label2;
    private javax.swing.JTable pedidosEncTabla;
    // End of variables declaration//GEN-END:variables
}
