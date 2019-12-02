package xyz.gui;

import java.awt.Image;
import javax.swing.JOptionPane;
import xyz.modelos.Marca;
import xyz.persistencia.MarcaPersistencia;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import xyz.modelos.Modelo;
import xyz.modelos.Onibus;
import xyz.modelos.Situacao;
import xyz.persistencia.ModeloPersistencia;
import xyz.persistencia.OnibusPersistencia;
import xyz.utilidades.GeradorDeIdentificadores;
import xyz.utilidades.JTableRenderer;
import xyz.utilidades.TeclasPermitidasNumeros;

/**
 *
 * @author eugeniojulio
 */
public class TelaDoOnibus extends javax.swing.JFrame {

    private ModeloPersistencia modeloPersistencia = new ModeloPersistencia();
    private OnibusPersistencia onibusPersistencia = new OnibusPersistencia();
    private Situacao situacao;
    private int idDoModelo;

    /**
     * Creates new form TelaDaMarca
     *
     * @throws java.lang.Exception
     */
    public TelaDoOnibus() throws Exception {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        jTextFieldIdOnibus.setEnabled(false);        
        adicionaSituacaojComboBox();
        this.adicionaListaDeModelosJComboBox(modeloPersistencia.recuperar());
        this.iniciar();
        jButtonAlterar.setEnabled(false);
        jTextFieldAnoDeFabricacao.setDocument(new TeclasPermitidasNumeros());
        jTextFieldNumeroDePoltronas.setDocument(new TeclasPermitidasNumeros());
    }

    private void mostrarDadosOnibus(ArrayList<Onibus> listaDeOnibus) throws Exception {
        try{
            
            TableColumnModel columModel = jTableOnibus.getColumnModel();
            JTableRenderer renderer = new JTableRenderer();
            jTableOnibus.setRowHeight(60);
            DefaultTableModel model = (DefaultTableModel) jTableOnibus.getModel();

            model.setNumRows(0);
            for (int i = 0; i < listaDeOnibus.size(); i++) {                
                Onibus aux = listaDeOnibus.get(i);
                Modelo modelo = modeloPersistencia.recuperaModeloPorID(aux.getIdModelo());
                ImageIcon img = new ImageIcon(modelo.getMarca().getImagem());
                Image image = img.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
                columModel.getColumn(7).setCellRenderer(renderer);
                
                model.addRow(new Object[]{aux.getId(), aux.getPlaca(), aux.getNumeroDePoltronas(),
                aux.getAnoDeFabricacao(), aux.getSituacao(), modelo.getDescricao(), modelo.getMarca().getDescricao(),
                new ImageIcon(image)});
            }

            
            
       }catch(Exception erro){
          throw erro;
        }
        

    }
    
    private void iniciar() {
        try {
            mostrarDadosOnibus(onibusPersistencia.recuperar());
            
        } catch (Exception e) {
            DefaultTableModel model = (DefaultTableModel) jTableOnibus.getModel();
            //Limpa a tabela 
            model.setNumRows(0);
            String[] saida = new String[7];
            saida[0] = "Arquivo";
            saida[1] = "Sem dados para mostrar";
            saida[2] = "Sem dados para mostrar";
            saida[3] = "Sem dados para mostrar";
            saida[4] = "Sem dados para mostrar";
            saida[5] = "Sem dados para mostrar";
            saida[6] = "Sem dados para mostrar";

            //Incluir nova linha na Tabela
            model.addRow(saida);
        }
    }

    private void adicionaSituacaojComboBox() {
        try {
            Situacao[] saida = new Situacao[2];
            saida[0] = Situacao.ativo;
            saida[1] = Situacao.inativo;
            
            if(situacao == saida[0]){
                saida[0] = situacao;
                saida[1] = Situacao.inativo;
            }else if(situacao == saida[1]){
                saida[0] = situacao;
                saida[1] = Situacao.ativo;
            }
            
           

            DefaultComboBoxModel modeloMarcas = new DefaultComboBoxModel(saida);
            jComboBoxSituacao.setModel(modeloMarcas);
        } catch (Exception erro) {
            erro.getMessage();
        }
    }

    private void adicionaListaDeModelosJComboBox(ArrayList<Modelo> listaDeModelos) {
        try {
            String saida[] = new String[listaDeModelos.size()];

            for (int i = 0; i < listaDeModelos.size(); i++) {
                String dados = String.valueOf(listaDeModelos.get(i));
                String dadosModelo[] = dados.split(";");
                Marca marca = null;
                for (int j = 0; j < dadosModelo.length; j++) {
                    marca = new MarcaPersistencia().recuperarMarcaPorID(Integer.parseInt(dadosModelo[2]));
                }

                Modelo modelo = new Modelo(dados, marca);
                saida[i] = "" + modelo.getId() + ";" + modelo.getDescricao() + ";" + modelo.getMarca().getDescricao();

            }

            DefaultComboBoxModel modeloMarcas = new DefaultComboBoxModel(saida);
            jComboBoxModelos.setModel(modeloMarcas);
        } catch (Exception erro) {
            erro.getMessage();
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

        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldIdOnibus = new javax.swing.JTextField();
        jTextFieldPlacaOnibus = new javax.swing.JTextField();
        jButtonIncluir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableOnibus = new javax.swing.JTable();
        jButtonAlterar = new javax.swing.JButton();
        jComboBoxModelos = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jButtonBuscar = new javax.swing.JButton();
        jLabelIDMarca = new javax.swing.JLabel();
        jTextFieldNumeroDePoltronas = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldAnoDeFabricacao = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxSituacao = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        idModelo = new javax.swing.JLabel();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        jCheckBox1.setText("jCheckBox1");

        jPasswordField1.setText("jPasswordField1");

        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CADASTRO");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 3, 24)); // NOI18N
        jLabel1.setText("CADASTRO DOS ÔNIBUS");
        jLabel1.setMaximumSize(new java.awt.Dimension(298, 32));

        jLabel2.setText("IDENTIFICADOR");

        jLabel3.setText("PLACA");

        jButtonIncluir.setText("INCLUIR");
        jButtonIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIncluirActionPerformed(evt);
            }
        });

        jTableOnibus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDENTIFICADOR", "PLACA", "NUMERO DE POLTRONAS", "ANO DE FABRICAÇÃO", "SITUAÇÃO", "MODELO", "MARCA", "LOGOTIPO"
            }
        ));
        jScrollPane1.setViewportView(jTableOnibus);

        jButtonAlterar.setText("ALTERAR");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });

        jLabel4.setText("NUMERO DE POLTRONAS");

        jButtonBuscar.setText("BUSCAR");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        jTextFieldNumeroDePoltronas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNumeroDePoltronasActionPerformed(evt);
            }
        });

        jLabel6.setText("ANO DE FABRICAÇÃO");

        jLabel7.setText("SITUAÇÃO");

        jLabel8.setText("MODELO");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabelIDMarca))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(486, 486, 486)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jComboBoxModelos, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(idModelo))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jTextFieldIdOnibus, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jButtonBuscar))
                                                .addComponent(jTextFieldPlacaOnibus, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addComponent(jTextFieldAnoDeFabricacao)
                                                    .addGap(301, 301, 301)))
                                            .addComponent(jTextFieldNumeroDePoltronas, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jComboBoxSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 450, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(260, 260, 260))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldIdOnibus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscar))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldPlacaOnibus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNumeroDePoltronas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldAnoDeFabricacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBoxSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxModelos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(idModelo))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonIncluir)
                    .addComponent(jButtonAlterar))
                .addGap(8, 8, 8)
                .addComponent(jLabelIDMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIncluirActionPerformed

        try {

            GeradorDeIdentificadores gerarID = new GeradorDeIdentificadores();
            int id = gerarID.getIdentificador();

            String placaOnibus = jTextFieldPlacaOnibus.getText();
            int numeroPoltronas = Integer.parseInt(jTextFieldNumeroDePoltronas.getText());
            int ano = Integer.parseInt(jTextFieldAnoDeFabricacao.getText());

            Situacao situacaoOnibus = (Situacao) jComboBoxSituacao.getSelectedItem();
            String modelo = String.valueOf(jComboBoxModelos.getSelectedItem());
            int idModelo = recuperaIDModeloPorDadoSelecionadoJcomboBox(modelo);

            Onibus onibus = new Onibus(id, placaOnibus,numeroPoltronas,ano,situacaoOnibus,idModelo);
            onibusPersistencia.incluir(onibus);

            iniciar();
            limparCampos();
            gerarID.finalizar();
            
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }//GEN-LAST:event_jButtonIncluirActionPerformed


    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
          try{
        int idOnibus = Integer.parseInt(jTextFieldIdOnibus.getText());
        String placa = jTextFieldPlacaOnibus.getText();
        int numeroPoltronas = Integer.parseInt(jTextFieldNumeroDePoltronas.getText());
        int ano = Integer.parseInt(jTextFieldAnoDeFabricacao.getText());
        
        Situacao situacaoOnibus = (Situacao)jComboBoxSituacao.getSelectedItem();
        

        onibusPersistencia.alterar(idOnibus, placa, numeroPoltronas,ano,situacaoOnibus,idDoModelo);
        mostrarDadosOnibus(onibusPersistencia.recuperar());
        limparCampos();
        jButtonAlterar.setEnabled(false);
        jButtonIncluir.setEnabled(true);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
 try {
            int indice = jTableOnibus.getSelectedRow();
            if (indice != -1) {
                String idOnibusString = String.valueOf(jTableOnibus.getValueAt(indice, 0));
                String placa = String.valueOf(jTableOnibus.getValueAt(indice, 1));
                String nPoltronas = String.valueOf(jTableOnibus.getValueAt(indice,2));
                String anoFabricacao = String.valueOf(jTableOnibus.getValueAt(indice,3));
                String s = String.valueOf(jTableOnibus.getValueAt(indice,4));
                String modelo = String.valueOf(jTableOnibus.getValueAt(indice,5));
                String marca = String.valueOf(jTableOnibus.getValueAt(indice,6));
                
                int idOnibus = Integer.parseInt(idOnibusString);
                int numeroPoltronas = Integer.parseInt(nPoltronas);               
                int ano = Integer.parseInt(anoFabricacao);
                situacao = Situacao.valueOf(s);
                
                jTextFieldIdOnibus.setText(""+idOnibus);
                jTextFieldPlacaOnibus.setText(placa);
                jTextFieldNumeroDePoltronas.setText(""+numeroPoltronas);
                jTextFieldAnoDeFabricacao.setText(""+ano);
               
                adicionaSituacaojComboBox();
                
                int idModelo = onibusPersistencia.recuperaIDModeloPorOnibusSelecionado(idOnibus);
                idDoModelo = idModelo;
                ArrayList<Modelo> listaDeModelos = modeloPersistencia.recuperaModelosPeloIDSelecionado(idModelo);
                adicionaListaDeModelosJComboBox(listaDeModelos);
                
                jButtonAlterar.setEnabled(true);
                jButtonIncluir.setEnabled(false);
               
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jTextFieldNumeroDePoltronasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNumeroDePoltronasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNumeroDePoltronasActionPerformed

    private int recuperaIDModeloPorDadoSelecionadoJcomboBox(String dados) {
        String dadoModelo[] = dados.split(";");
        int idModelo = 0;
        for (int i = 0; i < dadoModelo.length; i++) {
            idModelo = Integer.parseInt(dadoModelo[0]);
        }
        return idModelo;
    }

    private void limparCampos() {
        jTextFieldIdOnibus.setText("");
        jTextFieldPlacaOnibus.setText("");
        jTextFieldAnoDeFabricacao.setText("");
        jTextFieldNumeroDePoltronas.setText("");
        idDoModelo = 0;
        
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
            java.util.logging.Logger.getLogger(TelaDoOnibus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaDoOnibus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaDoOnibus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaDoOnibus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TelaDoOnibus().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(TelaDoOnibus.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel idModelo;
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonIncluir;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBoxModelos;
    private javax.swing.JComboBox jComboBoxSituacao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelIDMarca;
    private javax.swing.JList jList1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableOnibus;
    private javax.swing.JTextField jTextFieldAnoDeFabricacao;
    private javax.swing.JTextField jTextFieldIdOnibus;
    private javax.swing.JTextField jTextFieldNumeroDePoltronas;
    private javax.swing.JTextField jTextFieldPlacaOnibus;
    // End of variables declaration//GEN-END:variables
}
