package xyz.gui;

import java.awt.Image;
import javax.swing.JOptionPane;
import xyz.modelos.Marca;
import xyz.persistencia.MarcaPersistencia;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import xyz.modelos.Modelo;
import xyz.persistencia.ModeloPersistencia;
import xyz.utilidades.GeradorDeIdentificadores;
import xyz.utilidades.JTableRenderer;
import xyz.utilidades.TeclasPermitidasLetras;

/**
 *
 * @author eugeniojulio
 */
public class TelaDoModelo extends javax.swing.JFrame {
    
    private ModeloPersistencia modeloPersistencia = new ModeloPersistencia();
    private MarcaPersistencia marcaPersistencia = new MarcaPersistencia();
    private String caminhoImagem;

    /**
     * Creates new form TelaDaMarca
     * @throws java.lang.Exception
     */
    public TelaDoModelo() throws Exception {
        initComponents();
        jTextFieldIdModelo.setEnabled(false);
        this.adicionaListaDeMarcasJComboBox(marcaPersistencia.recuperar());
        this.iniciar();
        jTextFieldDescricaoModelo.setDocument(new TeclasPermitidasLetras());
        jButtonAlterar.setEnabled(false);
    }
    
    private void mostrarDadosModelo(ArrayList<Modelo> listaDeModelos) {
        try{
            
            TableColumnModel columModel = jTableModelos.getColumnModel();
            JTableRenderer renderer = new JTableRenderer();
            jTableModelos.setRowHeight(60);
            DefaultTableModel modelo = (DefaultTableModel) jTableModelos.getModel();

            modelo.setNumRows(0);
            for (int i = 0; i < listaDeModelos.size(); i++) {
                String[] saida = new String[3];
                Modelo aux = listaDeModelos.get(i);                
                
                ImageIcon img = new ImageIcon(aux.getMarca().getImagem());
                Image image = img.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
                columModel.getColumn(3).setCellRenderer(renderer);

                modelo.addRow(new Object[]{aux.getId(), aux.getDescricao(), aux.getMarca().getDescricao(),
                new ImageIcon(image)});
            }
            
        
        } catch (Exception erro) {
            erro.getMessage();
        }
    }
    
    private void iniciar() {
        try {
            mostrarDadosModelo(modeloPersistencia.recuperar());
            
        } catch (Exception e) {
            DefaultTableModel model = (DefaultTableModel) jTableModelos.getModel();
            //Limpa a tabela 
            model.setNumRows(0);
            String[] saida = new String[3];
            saida[0] = "Arquivo";
            saida[1] = "Sem dados para mostrar";
            saida[2] = "Sem dados para mostrar";

            //Incluir nova linha na Tabela
            model.addRow(saida);
        }
    }
    
    private void adicionaListaDeMarcasJComboBox(ArrayList<Marca>listaDeMarcas){
            try{
            String saida[] = new String[listaDeMarcas.size()];
            
            for (int i = 0; i < listaDeMarcas.size(); i++) {
                String dados = String.valueOf(listaDeMarcas.get(i));
                Marca marca = new Marca(dados);
                saida[i] = "" + marca.getId() + ";" + marca.getDescricao();
                
            }
            
            DefaultComboBoxModel modeloMarcas = new DefaultComboBoxModel(saida);
            jComboBoxMarcas.setModel(modeloMarcas);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldIdModelo = new javax.swing.JTextField();
        jTextFieldDescricaoModelo = new javax.swing.JTextField();
        jButtonIncluir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableModelos = new javax.swing.JTable();
        jButtonExcluir = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jComboBoxMarcas = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jButtonBuscar = new javax.swing.JButton();
        jLabelIDMarca = new javax.swing.JLabel();
        jButtonCadastrarMarca = new javax.swing.JButton();
        jButtonMenu = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CADASTRO");
        setResizable(false);

        jLabel2.setText("IDENTIFICADOR");

        jLabel3.setText("DESCRICAO");

        jButtonIncluir.setText("INCLUIR");
        jButtonIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIncluirActionPerformed(evt);
            }
        });

        jTableModelos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDENTIFICADOR", "DESCRICAO", "MARCA", "LOGOTIPO"
            }
        ));
        jScrollPane1.setViewportView(jTableModelos);

        jButtonExcluir.setText("EXCLUIR");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        jButtonAlterar.setText("ALTERAR");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });

        jLabel4.setText("MARCA");

        jButtonBuscar.setText("BUSCAR");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        jButtonCadastrarMarca.setText("CADASTRAR MARCA");
        jButtonCadastrarMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarMarcaActionPerformed(evt);
            }
        });

        jButtonMenu.setText("IR PARA O MENU");
        jButtonMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMenuActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Sans", 3, 24)); // NOI18N
        jLabel1.setText("CADASTRO DO MODELO");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(526, 526, 526)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextFieldIdModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonBuscar))
                            .addComponent(jTextFieldDescricaoModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jComboBoxMarcas, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonCadastrarMarca)))))
                .addContainerGap(551, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(306, 306, 306)
                .addComponent(jLabelIDMarca)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButtonIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonMenu))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelIDMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(134, 134, 134)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldIdModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscar))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldDescricaoModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxMarcas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCadastrarMarca))
                .addGap(171, 171, 171)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIncluirActionPerformed
        
        try {
            
            GeradorDeIdentificadores gerarID = new GeradorDeIdentificadores();
            int id = gerarID.getIdentificador();
            
            String descricao = jTextFieldDescricaoModelo.getText();
            
            String dadosJComboBox = String.valueOf(jComboBoxMarcas.getSelectedItem());
            String dados = dadosJComboBox + ";" + caminhoImagem;
            Marca marca = new Marca(dados);
            Modelo modelo = new Modelo(id, descricao, marca);
            
            modeloPersistencia.incluir(modelo);
            mostrarDadosModelo(modeloPersistencia.recuperar());
            limparCampos();
            gerarID.finalizar();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }//GEN-LAST:event_jButtonIncluirActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        try {
            int indice = jTableModelos.getSelectedRow();
            if (indice != -1) {
                int opcao = JOptionPane.showConfirmDialog(null, "Você realmente deseja excluir ?", "Alerta", JOptionPane.WARNING_MESSAGE);
                if (opcao == 0) {
                    String idEmString = String.valueOf(jTableModelos.getValueAt(indice, 0));
                    int id = Integer.parseInt(idEmString);
                    modeloPersistencia.excluir(id);
                    mostrarDadosModelo(modeloPersistencia.recuperar());
                    limparCampos();
                    
                }
                
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }//GEN-LAST:event_jButtonExcluirActionPerformed
    

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        try{
        int idModelo = Integer.parseInt(jTextFieldIdModelo.getText());
        String descricaoModelo = jTextFieldDescricaoModelo.getText();
        String dadosJComboBox = (String)jComboBoxMarcas.getSelectedItem();
        String dados = dadosJComboBox + ";" + caminhoImagem;
        Marca marca = new Marca(dados);
        modeloPersistencia.alterar(idModelo, descricaoModelo, marca);
        mostrarDadosModelo(modeloPersistencia.recuperar());
        limparCampos();
        jButtonAlterar.setEnabled(false);
        jButtonIncluir.setEnabled(true);
        jButtonExcluir.setEnabled(true);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        try {
            int indice = jTableModelos.getSelectedRow();
            if (indice != -1) {
                String idModeloString = String.valueOf(jTableModelos.getValueAt(indice, 0));
                int idModelo = Integer.parseInt(idModeloString);
                String descricaoModelo = String.valueOf(jTableModelos.getValueAt(indice, 1));
                ArrayList<Marca> listaDeMarcas = modeloPersistencia.recuperaMarcasPeloIDSelecionado(idModelo);
                adicionaListaDeMarcasJComboBox(listaDeMarcas);
               
                jTextFieldIdModelo.setText(""+idModelo);
                jTextFieldDescricaoModelo.setText(descricaoModelo);
                
                jButtonAlterar.setEnabled(true);
                jButtonIncluir.setEnabled(false);
                jButtonExcluir.setEnabled(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jButtonCadastrarMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarMarcaActionPerformed
        new TelaDaMarca().setVisible(true);
    }//GEN-LAST:event_jButtonCadastrarMarcaActionPerformed

    private void jButtonMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMenuActionPerformed
        new Principal().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonMenuActionPerformed
    
    private void limparCampos() {
        jTextFieldIdModelo.setText("");
        jTextFieldDescricaoModelo.setText("");
        caminhoImagem = null;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonCadastrarMarca;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonIncluir;
    private javax.swing.JButton jButtonMenu;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBoxMarcas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelIDMarca;
    private javax.swing.JList jList1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableModelos;
    private javax.swing.JTextField jTextFieldDescricaoModelo;
    private javax.swing.JTextField jTextFieldIdModelo;
    // End of variables declaration//GEN-END:variables
}
