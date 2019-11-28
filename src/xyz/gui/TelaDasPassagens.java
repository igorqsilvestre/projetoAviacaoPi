package xyz.gui;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import xyz.modelos.Marca;
import xyz.modelos.Modelo;
import xyz.modelos.Onibus;
import xyz.modelos.Rotas;
import xyz.persistencia.MarcaPersistencia;
import xyz.persistencia.ModeloPersistencia;
import xyz.persistencia.OnibusPersistencia;
import xyz.persistencia.RotasPersistencia;
import xyz.utilidades.GeradorDeIdentificadores;

/**
 *
 * @author eugeniojulio
 */
public class TelaDasPassagens extends javax.swing.JFrame {

    private ModeloPersistencia modeloPersistencia = new ModeloPersistencia();
    private MarcaPersistencia marcaPersistencia = new MarcaPersistencia();
    private OnibusPersistencia onibusPersistencia = new OnibusPersistencia();
    private RotasPersistencia rotasPersistencia = new RotasPersistencia();
//    private Situacao situacao;
//    private int idDoModelo;

    /**
     * Creates new form TelaDaMarca
     *
     * @throws java.lang.Exception
     */
    public TelaDasPassagens() throws Exception {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        jTextFieldIdPassagem.setEnabled(false);
        adicionaListaDeMarcasJComboBox(rotasPersistencia.recuperar());
        jTextFieldValorDaPassagem.setEnabled(false);
//        adicionaListaDeOnibusJComboBox(onibusPersistencia.recuperaOnibusAtivo());
//        this.iniciar();

        jButtonAlterar.setEnabled(false);

    }

//    private void mostrarDadosRotas(ArrayList<Rotas> listaDeRotas) throws Exception {
//        try {
//            DefaultTableModel model = (DefaultTableModel) jTablePassagem.getModel();
//
//            model.setNumRows(0);
//            for (int i = 0; i < listaDeRotas.size(); i++) {
//                String[] saida = new String[10];
//                Rotas aux = listaDeRotas.get(i);
//                saida[0] = "" + aux.getId();
//                saida[1] = aux.getCidadeOrigem();
//                saida[2] = "" + aux.getCidadeDestino();
//                saida[3] = "" + aux.getDataIda();
//                saida[4] = "" + aux.getDataChegada();
//                saida[5] = "" + aux.getHorarioIda();
//                saida[6] = "" + aux.getHorarioChegada();
//                Onibus onibus = onibusPersistencia.recuperaOnibusPorID(aux.getIdOnibus());
//                Modelo modelo = modeloPersistencia.recuperaModeloPorID(onibus.getIdModelo());
//                Marca marca = modeloPersistencia.recuperaMarcaPorDados(modelo.toString());
//                saida[7] = "" + onibus.getPlaca();
//                saida[8] = "" + modelo.getDescricao();
//                saida[9] = "" + marca.getDescricao();
//
//                model.addRow(saida);
//            }
//
//            jTablePassagem.setModel(model);
//        } catch (Exception erro) {
//            JOptionPane.showMessageDialog(null, erro.getMessage());
//        }
//
//    }

//    private void iniciar() {
//        try {
//            mostrarDadosRotas(rotasPersistencia.recuperar());
//
//        } catch (Exception e) {
//            DefaultTableModel model = (DefaultTableModel) jTablePassagem.getModel();
//            //Limpa a tabela 
//            model.setNumRows(0);
//            String[] saida = new String[10];
//            saida[0] = "Arquivo";
//            saida[1] = "Sem dados para mostrar";
//            saida[2] = "Sem dados para mostrar";
//            saida[3] = "Sem dados para mostrar";
//            saida[4] = "Sem dados para mostrar";
//            saida[5] = "Sem dados para mostrar";
//            saida[6] = "Sem dados para mostrar";
//            saida[7] = "Sem dados para mostrar";
//            saida[8] = "Sem dados para mostrar";
//            saida[9] = "Sem dados para mostrar";
//
//            //Incluir nova linha na Tabela
//            model.addRow(saida);
//        }
//    }

    private void adicionaListaDeMarcasJComboBox(ArrayList<Rotas> listaDeRotas) {
        try {
            String saida[] = new String[listaDeRotas.size()];
            for (int i = 0; i < listaDeRotas.size(); i++) {
                Rotas rotas = null;
                String lista = String.valueOf(listaDeRotas.get(i));
                String dadosOnibus[] = lista.split(";");
                for (int j = 0; j < dadosOnibus.length; j++) {
                    rotas = new Rotas(lista);
                }
     
                saida[i] = "" + rotas.getId() + ";" + rotas.getCidadeOrigem() + ";" + rotas.getCidadeDestino()+ ";" 
                               +rotas.getDataIda()+ ";"+rotas.getDataChegada()+";"+rotas.getHorarioIda()+";"+rotas.getHorarioChegada();
            }

            DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(saida);
            jComboBoxRotas.setModel(comboBoxModel);

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
        jTextFieldIdPassagem = new javax.swing.JTextField();
        jButtonIncluir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePassagem = new javax.swing.JTable();
        jButtonAlterar = new javax.swing.JButton();
        jButtonBuscar = new javax.swing.JButton();
        jLabelIDMarca = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        idModelo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButtonExluir = new javax.swing.JButton();
        jComboBoxRotas = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jComboBoxFormasDePagamento = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxCliente = new javax.swing.JComboBox<>();
        jButtonCadastrarCliente = new javax.swing.JButton();
        jTextFieldValorDaPassagem = new javax.swing.JTextField();
        jButtonCalcularPassagem = new javax.swing.JButton();

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
        jLabel1.setText("CADASTRO DAS PASSAGENS");
        jLabel1.setMaximumSize(new java.awt.Dimension(298, 32));

        jLabel2.setText("IDENTIFICADOR");

        jButtonIncluir.setText("INCLUIR");
        jButtonIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIncluirActionPerformed(evt);
            }
        });

        jTablePassagem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDENTIFICADOR", "CLIENTE", "CPF", "CIDADE ORIGEM", "CIDADE DESTINO", "DATA DE IDA", "DATA DE CHEGADA", "HORARIO DE IDA", "HORARIO DE CHEGADA", "ONIBUS", "MARCA", "MODELO", "VALOR DA PASSAGEM", "FORMA DE PAGAMENTO"
            }
        ));
        jTablePassagem.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jTablePassagem);

        jButtonAlterar.setText("ALTERAR");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });

        jButtonBuscar.setText("BUSCAR");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        jLabel8.setText("ROTAS");

        jLabel3.setText("VALOR DA PASSAGEM");

        jButtonExluir.setText("EXCLUIR");
        jButtonExluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExluirActionPerformed(evt);
            }
        });

        jLabel11.setText("FORMA DE PAGAMENTO");

        jComboBoxFormasDePagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dinheiro", "Cartão de crédito", "Cartão de débito" }));

        jLabel4.setText("CLIENTE");

        jButtonCadastrarCliente.setText("CADASTRAR CLIENTE");

        jButtonCalcularPassagem.setText("CALCULAR PASSAGEM");
        jButtonCalcularPassagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCalcularPassagemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(486, 486, 486)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel4))
                                .addGap(94, 94, 94)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jComboBoxRotas, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldIdPassagem)
                                    .addComponent(jComboBoxFormasDePagamento, 0, 277, Short.MAX_VALUE)
                                    .addComponent(jComboBoxCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldValorDaPassagem))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(idModelo))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButtonCadastrarCliente)
                                            .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButtonCalcularPassagem))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonExluir, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(260, 260, 260))
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(1491, 1491, 1491)
                .addComponent(jLabelIDMarca)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 153, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(idModelo)
                        .addGap(194, 194, 194))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldIdPassagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonBuscar)
                            .addComponent(jLabel2))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jComboBoxRotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextFieldValorDaPassagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonCalcularPassagem))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jComboBoxFormasDePagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jButtonCadastrarCliente))
                        .addGap(53, 53, 53)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelIDMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonIncluir)
                            .addComponent(jButtonAlterar)
                            .addComponent(jButtonExluir))
                        .addGap(36, 36, 36)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIncluirActionPerformed

        try {

            GeradorDeIdentificadores gerarID = new GeradorDeIdentificadores();
            int id = gerarID.getIdentificador();
            
            
//
//            String cidadeOrigem = (String.valueOf(jComboBoxCidadesOrigem.getSelectedItem()));
//            String cidadeDestino = String.valueOf(jComboBoxCidadesDestino.getSelectedItem());
//
//            String dataIda = jFormatteDataIda.getText();
//            String dataChegada = jFormattedDataChegada.getText();
//
//            String horasIda = String.valueOf(jComboBoxHorasIda.getSelectedItem());
//            String minutosIda = String.valueOf(ComboBoxMinutosIda.getSelectedItem());
//            String horarioIda = "" + horasIda + ":" + minutosIda + "";
//            String horasChegada = String.valueOf(jComboBoxHorasChegada.getSelectedItem());
//           String minutosChegada = String.valueOf(ComboBoxMinutosChegada.getSelectedItem());
//            String horarioChegada = "" + horasChegada + ":" + minutosChegada + "";
//
//            String onibus = String.valueOf(jComboBoxOnibus.getSelectedItem());
//            int idOnibus = recuperaIDOnibusPorDadoSelecionadoJcomboBox(onibus);
//
//            Rotas rotas = new Rotas(id, cidadeOrigem, cidadeDestino, dataIda, dataChegada, horarioIda, horarioChegada, idOnibus);
//
//            rotasPersistencia.incluir(rotas);
//
//            iniciar();
//            limparCampos();
//            gerarID.finalizar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }//GEN-LAST:event_jButtonIncluirActionPerformed


    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
//          try{
//        int idOnibus = Integer.parseInt(jTextFieldIdRotas.getText());
//        String placa = jTextFieldPlacaOnibus.getText();
//        int numeroPoltronas = Integer.parseInt(jTextFieldNumeroDePoltronas.getText());
//        int ano = Integer.parseInt(jTextFieldAnoDeFabricacao.getText());
//        
//        Situacao situacaoOnibus = (Situacao)jComboBoxSituacao.getSelectedItem();
//        
//
//        onibusPersistencia.alterar(idOnibus, placa, numeroPoltronas,ano,situacaoOnibus,idDoModelo);
//        mostrarDadosOnibus(onibusPersistencia.recuperar());
//        limparCampos();
//        jButtonAlterar.setEnabled(false);
//        jButtonIncluir.setEnabled(true);
//        }catch(Exception e){
//            JOptionPane.showMessageDialog(null, e.getMessage());
//        }

    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
// try {
//            int indice = jTableRotas.getSelectedRow();
//            if (indice != -1) {
//                String idOnibusString = String.valueOf(jTableRotas.getValueAt(indice, 0));
//                String placa = String.valueOf(jTableRotas.getValueAt(indice, 1));
//                String nPoltronas = String.valueOf(jTableRotas.getValueAt(indice,2));
//                String anoFabricacao = String.valueOf(jTableRotas.getValueAt(indice,3));
//                String s = String.valueOf(jTableRotas.getValueAt(indice,4));
//                String modelo = String.valueOf(jTableRotas.getValueAt(indice,5));
//                String marca = String.valueOf(jTableRotas.getValueAt(indice,6));
//                
//                int idOnibus = Integer.parseInt(idOnibusString);
//                int numeroPoltronas = Integer.parseInt(nPoltronas);               
//                int ano = Integer.parseInt(anoFabricacao);
//                situacao = Situacao.valueOf(s);
//                
//                jTextFieldIdRotas.setText(""+idOnibus);
//                jTextFieldPlacaOnibus.setText(placa);
//                jTextFieldNumeroDePoltronas.setText(""+numeroPoltronas);
//                jTextFieldAnoDeFabricacao.setText(""+ano);
//               
//                adicionaSituacaojComboBox();
//                
//                int idModelo = onibusPersistencia.recuperaIDModeloPorOnibusSelecionado(idOnibus);
//                idDoModelo = idModelo;
//                ArrayList<Modelo> listaDeModelos = modeloPersistencia.recuperaModelosPeloIDSelecionado(idModelo);
//                adicionaListaDeModelosJComboBox(listaDeModelos);
//                
//                jButtonAlterar.setEnabled(true);
//                jButtonIncluir.setEnabled(false);
//               
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    
    private void jButtonExluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExluirActionPerformed
//        try {
//            int indice = jTablePassagem.getSelectedRow();
//            if (indice != -1) {
//                int opcao = JOptionPane.showConfirmDialog(null, "Você realmente deseja excluir ?", "Alerta", JOptionPane.WARNING_MESSAGE);
//                if (opcao == 0) {
//                    String idEmString = String.valueOf(jTablePassagem.getValueAt(indice, 0));
//                    int id = Integer.parseInt(idEmString);
//                    rotasPersistencia.excluir(id);
//                    mostrarDadosRotas(rotasPersistencia.recuperar());
//                    limparCampos();
//
//                }
//
//            }
//
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
//        }
    }//GEN-LAST:event_jButtonExluirActionPerformed

    private void jButtonCalcularPassagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCalcularPassagemActionPerformed
       
    }//GEN-LAST:event_jButtonCalcularPassagemActionPerformed

//    private int recuperaIDOnibusPorDadoSelecionadoJcomboBox(String dados) {
//        String dadoOnibus[] = dados.split(";");
//        int idOnibus = 0;
//        for (int i = 0; i < dadoOnibus.length; i++) {
//            idOnibus = Integer.parseInt(dadoOnibus[0]);
//        }
//        return idOnibus;
//    }
//
//    private void limparCampos() {
//        jTextFieldIdPassagem.setText("");
//        jFormatteDataIda.setText("");
//        jFormattedDataChegada.setText("");
//        jComboBoxHorasIda.setSelectedIndex(0);
//        ComboBoxMinutosIda.setSelectedIndex(0);
//        jComboBoxHorasChegada.setSelectedIndex(0);
//        ComboBoxMinutosChegada.setSelectedIndex(0);
//    }

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
            java.util.logging.Logger.getLogger(TelaDasPassagens.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaDasPassagens.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaDasPassagens.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaDasPassagens.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TelaDasPassagens().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(TelaDasPassagens.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel idModelo;
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonCadastrarCliente;
    private javax.swing.JButton jButtonCalcularPassagem;
    private javax.swing.JButton jButtonExluir;
    private javax.swing.JButton jButtonIncluir;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox<String> jComboBoxCliente;
    private javax.swing.JComboBox<String> jComboBoxFormasDePagamento;
    private javax.swing.JComboBox<String> jComboBoxRotas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelIDMarca;
    private javax.swing.JList jList1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTablePassagem;
    private javax.swing.JTextField jTextFieldIdPassagem;
    private javax.swing.JTextField jTextFieldValorDaPassagem;
    // End of variables declaration//GEN-END:variables
}
