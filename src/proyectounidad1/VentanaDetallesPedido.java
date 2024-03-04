/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package proyectounidad1;
import java.awt.Color;
import java.sql.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
/**
 *
 * @author samuel
 */
public class VentanaDetallesPedido extends javax.swing.JDialog {

    /**
     * Creates new form VentanaDetallesPedido
     */
    
    
    private VentanaPrincipal principal;
    private int codigoPedido;
    
    
    public VentanaDetallesPedido(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
     public VentanaDetallesPedido(java.awt.Frame parent, boolean modal, int codigoPedido, VentanaPrincipal principal) {
        super(parent, modal);
        initComponents();
        this.codigoPedido = codigoPedido;
        this.principal = principal;
        codPedidoLabel.setText("Información detallada acerca del pedido numero " + codigoPedido);
        cargarTablaDetallada();
        
    }
     
    public void cargarTablaDetallada(){
            
        //------------Tabla con detalles del producto del pedido---------------

        String queryDetalles = "SELECT PR.NOMBRE AS NOMBRE_PRODUCTO, DP.PRECIOUNIDAD, DP.CANTIDAD " +
                   "FROM PUBLIC.PEDIDOS P " +
                   "JOIN PUBLIC.DETALLEPEDIDOS DP ON P.CODIGO = DP.CODPEDIDO " +
                   "JOIN PUBLIC.PRODUCTOS PR ON DP.CODPRODUCTO = PR.CODIGO " +
                   "WHERE P.CODIGO = '" + codigoPedido + "'";
        try {
            Statement stmtDetalles = VentanaPrincipal.con.createStatement();
            ResultSet rsDetalles = stmtDetalles.executeQuery(queryDetalles);

            // Obtiene el modelo para la nueva JTable (para los detalles)
            DefaultTableModel detallesModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hace que todas las celdas no sean editables
            }
            };
            productosTabla.setRowSelectionAllowed(false);
            productosTabla.setCellSelectionEnabled(true);
            // Nombres de las columnas para los detalles
            String[] detallesColumnNames = {"Nombre Producto", "Precio Unidad", "Cantidad"};

            // Asigna los nombres de las columnas al modelo de tabla de detalles
            detallesModel.setColumnIdentifiers(detallesColumnNames);

            // Recorre el resultado de la consulta de detalles y agrega las filas al modelo de detalles
            while (rsDetalles.next()) {
                Object[] detallesRowData = {
                    rsDetalles.getString("NOMBRE_PRODUCTO"),
                    rsDetalles.getDouble("PRECIOUNIDAD"),
                    rsDetalles.getInt("CANTIDAD")
                };
                detallesModel.addRow(detallesRowData);
            }
            
            
            
            //Quitar borde de la Tabla con los productos 

            productosTabla.setModel(detallesModel);
            
            //Ajustar celdas
            productosTabla.setRowHeight(50);
            TableColumnModel columnModelDetallada = productosTabla.getColumnModel();
            columnModelDetallada.getColumn(0).setPreferredWidth(150);
            columnModelDetallada.getColumn(1).setPreferredWidth(20);
            columnModelDetallada.getColumn(2).setPreferredWidth(20);


            // Cierra los recursos para los detalles
            rsDetalles.close();
            stmtDetalles.close();


            //----------Tabla con detalles de pedido--------------
                
            String query = "SELECT P.CODCLIENTE, C.NOMCOMPANIA AS NOMBRE_CLIENTE, C.NOMCONTACTO AS NOMBRE_CONTACTO, "
                    + "E.NOMBRE AS NOMBRE_EMPLEADO, P.FECHAPEDIDO, P.FECHAENTREGA, P.FECHAENVIO, P.CARGO, P.DIRECCION, "
                    + "CI.NOMBRE AS NOMBRE_CIUDAD, P.CODPOSTAL, CE.NOMBRE AS NOMBRE_EMPRESA_ENVIO " +
                    "FROM PUBLIC.PEDIDOS P " +
                    "JOIN PUBLIC.CLIENTES C ON P.CODCLIENTE = C.CODIGO " +
                    "JOIN PUBLIC.EMPLEADOS E ON P.CODEMPLEADO = E.CODIGO " +
                    "JOIN PUBLIC.CIUDADES CI ON P.CODCIUDAD = CI.CODIGO " +
                    "LEFT JOIN PUBLIC.COMPENVIOS CE ON P.CODEMPREENVIO = CE.CODIGO " +
                    "WHERE P.CODIGO = '" + codigoPedido + "'";
            
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
                    "Nombre Cliente", "Nombre Contacto", "Nombre Empleado",
                    "Fecha Pedido", "Fecha Entrega", "Fecha Envío", "Cargo", "Dirección",
                    "Nombre Ciudad", "Código Postal", "Nombre Empresa Envío",
                };

                // Asigna los nombres de las columnas al modelo de tabla
                model.setColumnIdentifiers(columnNames);
                
                // Recorre el resultado de la consulta y agrega las filas a la JTable
                while (rs.next()) {
                    Object[] rowData = {
                        rs.getString("NOMBRE_CLIENTE"),
                        rs.getString("NOMBRE_CONTACTO"),
                        rs.getString("NOMBRE_EMPLEADO"),
                        rs.getDate("FECHAPEDIDO"),
                        rs.getDate("FECHAENTREGA"),
                        rs.getDate("FECHAENVIO"),
                        rs.getDouble("CARGO"),
                        rs.getString("DIRECCION"),
                        rs.getString("NOMBRE_CIUDAD"),
                        rs.getString("CODPOSTAL"),
                        rs.getString("NOMBRE_EMPRESA_ENVIO"),
                    };
                    model.addRow(rowData);
                }
                TableColumnModel columnModel = infoDetalladaTabla.getColumnModel();

                // Tamaño preferido para todas las columnas
                int preferredWidth = 100;

                // Itera sobre todas las columnas y establece el mismo tamaño preferido
                for (int i = 0; i < columnModel.getColumnCount(); i++) {
                    columnModel.getColumn(i).setPreferredWidth(preferredWidth);
                }
                columnModel.getColumn(0).setPreferredWidth(150);
                columnModel.getColumn(6).setPreferredWidth(50);
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
        jScrollPane2 = new javax.swing.JScrollPane();
        productosTabla = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        infoDetalladaTabla = new javax.swing.JTable();
        codPedidoLabel = new javax.swing.JLabel();
        prodcLabel = new javax.swing.JLabel();
        imagenLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Detalles");

        productosTabla.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(productosTabla);

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

        codPedidoLabel.setText("Información detallada acerca del pedido");

        prodcLabel.setText("Productos, precios y cantidades del pedido");

        imagenLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/pedidoEscalado.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(prodcLabel)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(imagenLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 769, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(codPedidoLabel)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1461, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(115, 115, 115))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(codPedidoLabel)
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(prodcLabel)
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(imagenLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1487, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
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
            java.util.logging.Logger.getLogger(VentanaDetallesPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaDetallesPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaDetallesPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaDetallesPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VentanaDetallesPedido dialog = new VentanaDetallesPedido(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel codPedidoLabel;
    private javax.swing.JLabel imagenLabel;
    private javax.swing.JTable infoDetalladaTabla;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel prodcLabel;
    private javax.swing.JTable productosTabla;
    // End of variables declaration//GEN-END:variables
}
