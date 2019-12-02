package xyz.gui;

import java.awt.event.ItemEvent;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import xyz.modelos.Cliente;
import xyz.modelos.Modelo;
import xyz.modelos.Onibus;
import xyz.modelos.Passagem;
import xyz.modelos.Rotas;
import xyz.persistencia.ClientePersistencia;
import xyz.persistencia.MarcaPersistencia;
import xyz.persistencia.ModeloPersistencia;
import xyz.persistencia.OnibusPersistencia;
import xyz.persistencia.PassagemPersistencia;
import xyz.persistencia.RotasPersistencia;
import xyz.utilidades.GeradorDeIdentificadores;
import xyz.utilidades.ThreadPopulaClientesNoJcomboBox;

/**
 *
 * @author eugeniojulio
 */
public class TelaDasPassagens extends javax.swing.JFrame {
    
    private ModeloPersistencia modeloPersistencia = new ModeloPersistencia();
    private MarcaPersistencia marcaPersistencia = new MarcaPersistencia();
    private OnibusPersistencia onibusPersistencia = new OnibusPersistencia();
    private RotasPersistencia rotasPersistencia = new RotasPersistencia();
    private ClientePersistencia clientePersistencia = new ClientePersistencia();
    private PassagemPersistencia passagemPersistencia = new PassagemPersistencia();

    /**
     * Creates new form TelaDaMarca
     *
     * @throws java.lang.Exception
     */
    public TelaDasPassagens() throws Exception {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        jTextFieldIdPassagem.setEnabled(false);
        adicionaListaDeRotasJComboBox(rotasPersistencia.recuperar());
        jTextFieldValorDaPassagem.setEnabled(false);
        adicionaListaDeClientesJComboBox();
        populaAssentojComboBox();
        this.iniciar();
        jButtonAlterar.setEnabled(false);
        
    }
    
    private void mostrarDadosPassagens(ArrayList<Passagem> listaDePassagens) throws Exception {
        try {
            DefaultTableModel model = (DefaultTableModel) jTablePassagem.getModel();
            
            model.setNumRows(0);
            for (int i = 0; i < listaDePassagens.size(); i++) {
                String[] saida = new String[15];
                
                Passagem aux = listaDePassagens.get(i);
                Cliente cliente = clientePersistencia.recuperaClientePorCPF(aux.getCpfCliente());
                Rotas rota = rotasPersistencia.recuperaRotaPorId(aux.getIdRotas());
                Onibus onibus = onibusPersistencia.recuperaOnibusPorID(rota.getIdOnibus());
                Modelo modelo = modeloPersistencia.recuperaModeloPorID(onibus.getIdModelo());
                saida[0] = "" + aux.getId();
                saida[1] = cliente.getNome();
                saida[2] = "" + cliente.getCpf();
                saida[3] = "" + rota.getCidadeOrigem();
                saida[4] = "" + rota.getCidadeDestino();
                saida[5] = "" + rota.getDataIda();
                saida[6] = "" + rota.getDataChegada();
                saida[7] = "" + rota.getHorarioIda();
                saida[8] = "" + rota.getHorarioChegada();
                saida[9] = "" + onibus.getPlaca();
                saida[10] = "" + modelo.getMarca().getDescricao();
                saida[11] = "" + modelo.getDescricao();
                saida[12] = "" + aux.getValorPassagem();
                saida[13] = "" + aux.getFormaDePagamento();
                saida[14] = "" + aux.getAssento();
                model.addRow(saida);
            }
            
            jTablePassagem.setModel(model);
        } catch (Exception erro) {
           throw erro;
        }
        
    }
    
    private void iniciar() {
        try {
            mostrarDadosPassagens(passagemPersistencia.recuperar());
            
        } catch (Exception e) {
            DefaultTableModel model = (DefaultTableModel) jTablePassagem.getModel();
            //Limpa a tabela 
            model.setNumRows(0);
            String[] saida = new String[15];
            saida[0] = "Arquivo";
            saida[1] = "Sem dados para mostrar";
            saida[2] = "Sem dados para mostrar";
            saida[3] = "Sem dados para mostrar";
            saida[4] = "Sem dados para mostrar";
            saida[5] = "Sem dados para mostrar";
            saida[6] = "Sem dados para mostrar";
            saida[7] = "Sem dados para mostrar";
            saida[8] = "Sem dados para mostrar";
            saida[9] = "Sem dados para mostrar";
            saida[10] = "Sem dados para mostrar";
            saida[11] = "Sem dados para mostrar";
            saida[12] = "Sem dados para mostrar";
            saida[13] = "Sem dados para mostrar";
            saida[14] = "Sem dados para mostrar";

            //Incluir nova linha na Tabela
            model.addRow(saida);
        }
    }
    
    private void adicionaListaDeRotasJComboBox(ArrayList<Rotas> listaDeRotas) {
        try {
            String saida[] = new String[listaDeRotas.size()];
            for (int i = 0; i < listaDeRotas.size(); i++) {
                Rotas rotas = null;
                String lista = String.valueOf(listaDeRotas.get(i));
                String dadosOnibus[] = lista.split(";");
                for (int j = 0; j < dadosOnibus.length; j++) {
                    rotas = new Rotas(lista);
                }
                
                saida[i] = "" + rotas.getId() + ";" + rotas.getCidadeOrigem() + ";" + rotas.getCidadeDestino() + ";"
                        + rotas.getDataIda() + ";" + rotas.getDataChegada() + ";" + rotas.getHorarioIda() + ";"
                        + rotas.getHorarioChegada() + ";" + rotas.getIdOnibus();
            }
            
            DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(saida);
            jComboBoxRotas.setModel(comboBoxModel);
            
        } catch (Exception erro) {
            erro.getMessage();
        }
    }
    
    private void adicionaListaDeClientesJComboBox() {
        try {
            ThreadPopulaClientesNoJcomboBox buscarClientes = new ThreadPopulaClientesNoJcomboBox(jComboBoxCliente, true);
            buscarClientes.start();
        } catch (Exception erro) {
            erro.getMessage();
            
        }
    }
    
    private void populaJComboBoxRotaAPartirDaBusca(int id) {
        ArrayList<String> listanovaRotas = new ArrayList<>();
        ArrayList<String> listaVelhaRotas = new ArrayList<>();
        for (int i = 0; i < jComboBoxRotas.getItemCount(); i++) {
            String rotas = jComboBoxRotas.getItemAt(i);
            String dados[] = rotas.split(";");
            int idRota = Integer.parseInt(dados[0]);
            if (idRota == id) {
                listanovaRotas.add(rotas);
            } else {
                listaVelhaRotas.add(rotas);
            }
        }
        
        jComboBoxRotas.removeAllItems();
        listanovaRotas.addAll(listaVelhaRotas);
        DefaultComboBoxModel model = new DefaultComboBoxModel(listanovaRotas.toArray());
        jComboBoxRotas.setModel(model);
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
        jComboBoxRotas = new javax.swing.JComboBox<String>();
        jLabel11 = new javax.swing.JLabel();
        jComboBoxFormasDePagamento = new javax.swing.JComboBox<String>();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxCliente = new javax.swing.JComboBox<String>();
        jButtonCadastrarCliente = new javax.swing.JButton();
        jTextFieldValorDaPassagem = new javax.swing.JTextField();
        jButtonCalcularPassagem = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxAssento = new javax.swing.JComboBox<String>();

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
                "IDENTIFICADOR", "CLIENTE", "CPF", "CIDADE ORIGEM", "CIDADE DESTINO", "DATA DE IDA", "DATA DE CHEGADA", "HORARIO DE IDA", "HORARIO DE CHEGADA", "ONIBUS", "MARCA", "MODELO", "VALOR DA PASSAGEM", "FORMA DE PAGAMENTO", "ASSENTO"
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

        jComboBoxRotas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxRotasItemStateChanged(evt);
            }
        });

        jLabel11.setText("FORMA DE PAGAMENTO");

        jComboBoxFormasDePagamento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dinheiro", "Cartão de crédito", "Cartão de débito" }));

        jLabel4.setText("CLIENTE");

        jButtonCadastrarCliente.setText("CADASTRAR CLIENTE");
        jButtonCadastrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarClienteActionPerformed(evt);
            }
        });

        jButtonCalcularPassagem.setText("CALCULAR PASSAGEM");
        jButtonCalcularPassagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCalcularPassagemActionPerformed(evt);
            }
        });

        jToggleButton1.setText("Mostrar Passagens Emitidas");

        jLabel6.setText("SELECIONAR ASSENTO:");

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
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6))
                                .addGap(94, 94, 94)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
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
                                                    .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jButtonCalcularPassagem)
                                                    .addComponent(jButtonCadastrarCliente))
                                                .addGap(0, 0, Short.MAX_VALUE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jComboBoxAssento, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonExluir, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(101, 101, 101)
                .addComponent(jToggleButton1)
                .addGap(54, 54, 54))
            .addGroup(layout.createSequentialGroup()
                .addGap(1491, 1491, 1491)
                .addComponent(jLabelIDMarca)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 144, Short.MAX_VALUE)
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
                            .addComponent(jButtonCadastrarCliente)
                            .addComponent(jToggleButton1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jComboBoxAssento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelIDMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(296, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonIncluir)
                            .addComponent(jButtonAlterar)
                            .addComponent(jButtonExluir))
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIncluirActionPerformed
        
        try {
            GeradorDeIdentificadores gerarID = new GeradorDeIdentificadores();
            int id = gerarID.getIdentificador();
            String rotas = String.valueOf(jComboBoxRotas.getSelectedItem());
            String valorPassagem = jTextFieldValorDaPassagem.getText();
            String formaDePagamento = String.valueOf(jComboBoxFormasDePagamento.getSelectedItem());
            String cliente = String.valueOf(jComboBoxCliente.getSelectedItem());
            int assento = Integer.parseInt(String.valueOf(jComboBoxAssento.getSelectedItem()));
            
            int idRotas = recuperaIDRotasJComboBox(rotas);
            String cpfCliente = recuperaCPFClientesJComboBox(cliente);
            
            verificarAssentoDisponivel(idRotas, cpfCliente);
            Passagem passagem = new Passagem(id, idRotas, valorPassagem, formaDePagamento, cpfCliente, assento);
            passagemPersistencia.incluir(passagem);
            
            iniciar();
            limparCampos();
            gerarID.finalizar();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }//GEN-LAST:event_jButtonIncluirActionPerformed
    
    private void verificarAssentoDisponivel(int id, String cpf) throws Exception {
        ArrayList<Passagem> listaDePassagens = passagemPersistencia.recuperar();
        for (int i = 0; i < listaDePassagens.size(); i++) {
            Passagem passagem = listaDePassagens.get(i);
            if (passagem.getIdRotas() == id && passagem.getCpfCliente().equals(cpf) && passagem.getAssento() != 0) {
                throw new Exception("Cliente já possui assento para esta rota!");
            }
        }
        
    }
    

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        try {
            int idPassagem = Integer.parseInt(jTextFieldIdPassagem.getText());
            String rotas = String.valueOf(jComboBoxRotas.getSelectedItem());
            String valorPassagem = jTextFieldValorDaPassagem.getText();
            String formaDePagamento = String.valueOf(jComboBoxFormasDePagamento.getSelectedItem());
            String cliente = String.valueOf(jComboBoxCliente.getSelectedItem());
            int assento = Integer.parseInt(String.valueOf(jComboBoxAssento.getSelectedItem()));
            int idRotas = recuperaIDRotasJComboBox(rotas);
            String cpfCliente = recuperaCPFClientesJComboBox(cliente);
            
            passagemPersistencia.alterar(idPassagem,valorPassagem,formaDePagamento,assento,idRotas,cpfCliente);
            iniciar();
            limparCampos();
            jButtonAlterar.setEnabled(false);
            jButtonIncluir.setEnabled(true);
            jButtonExluir.setEnabled(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        try {
            int indice = jTablePassagem.getSelectedRow();
            if (indice != -1) {
                int idPassagem = Integer.parseInt(String.valueOf(jTablePassagem.getValueAt(indice, 0)));
                
                Passagem passagem = passagemPersistencia.recuperarPassagemPorID(idPassagem);
                passagem.getIdRotas();
                
                jTextFieldIdPassagem.setText("" + idPassagem);
                populaJComboBoxRotaAPartirDaBusca(passagem.getIdRotas());
                populaFormaDePagamentoAPartirDaBusca(passagem.getFormaDePagamento());
                populaClientesAPartirDaBusca(passagem.getCpfCliente());
                populaAssentoAPartirDaBusca(passagem.getAssento());
                
                jButtonAlterar.setEnabled(true);
                jButtonIncluir.setEnabled(false);
                jButtonExluir.setEnabled(false);
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButtonBuscarActionPerformed
    
    private void populaAssentoAPartirDaBusca(int assento) {
        ArrayList<Integer> listaAssentos = new ArrayList<>();
        for (int i = 0; i < jComboBoxAssento.getItemCount(); i++) {
            int assentos = Integer.parseInt(String.valueOf(jComboBoxAssento.getItemAt(i)));
            if (assentos == assento) {
                listaAssentos.add(0, assentos);
            } else {
                listaAssentos.add(assentos);
            }
        }
        jComboBoxAssento.removeAllItems();
        DefaultComboBoxModel model = new DefaultComboBoxModel(listaAssentos.toArray());
        jComboBoxAssento.setModel(model);
    }
    
    private void populaClientesAPartirDaBusca(String cpf) {
        ArrayList<String> listaClientes = new ArrayList<>();
        for (int i = 0; i < jComboBoxCliente.getItemCount(); i++) {
            String tiposCliente = String.valueOf(jComboBoxCliente.getItemAt(i));
            String dadosCliente[] = tiposCliente.split(";");
            if (dadosCliente[1].equals(cpf)) {
                listaClientes.add(0, tiposCliente);
            } else {
                listaClientes.add(tiposCliente);
            }
        }
        jComboBoxCliente.removeAllItems();
        DefaultComboBoxModel model = new DefaultComboBoxModel(listaClientes.toArray());
        jComboBoxCliente.setModel(model);
        
    }
    
    private void populaFormaDePagamentoAPartirDaBusca(String formaDePagamento) {
        ArrayList<String> listaFormasPagamento = new ArrayList<>();
        for (int i = 0; i < jComboBoxFormasDePagamento.getItemCount(); i++) {
            String tiposPagamento = String.valueOf(jComboBoxFormasDePagamento.getItemAt(i));
            if (tiposPagamento.equals(formaDePagamento)) {
                listaFormasPagamento.add(0, tiposPagamento);
            } else {
                listaFormasPagamento.add(tiposPagamento);
            }
        }
        
        jComboBoxFormasDePagamento.removeAllItems();
        DefaultComboBoxModel model = new DefaultComboBoxModel(listaFormasPagamento.toArray());
        jComboBoxFormasDePagamento.setModel(model);
        
    }

    private void jButtonExluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExluirActionPerformed
        try {
            int indice = jTablePassagem.getSelectedRow();
            if (indice != -1) {
                int opcao = JOptionPane.showConfirmDialog(null, "Você realmente deseja excluir ?", "Alerta", JOptionPane.WARNING_MESSAGE);
                if (opcao == 0) {
                    String idEmString = String.valueOf(jTablePassagem.getValueAt(indice, 0));
                    int id = Integer.parseInt(idEmString);
                    passagemPersistencia.excluir(id);
                    mostrarDadosPassagens(passagemPersistencia.recuperar());
                    limparCampos();
                    
                }
                
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jButtonExluirActionPerformed

    private void jButtonCalcularPassagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCalcularPassagemActionPerformed
        String rotas = String.valueOf(jComboBoxRotas.getSelectedItem());
        
        String[] dados = rotas.split(";");
        
        String horaIda = dados[5];
        String horariosIda[] = horaIda.split(":");
        int horarioIda = Integer.parseInt(horariosIda[0]);
        int minutosIda = Integer.parseInt(horariosIda[0]);
        
        String horaChegada = dados[6];
        
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
        
        LocalTime horario = LocalTime.of(horarioIda, minutosIda);
        LocalTime desejada = LocalTime.parse(horaChegada, formatoHora);
        
        LocalTime tempoGasto = desejada.minusHours(horario.getHour()).minusMinutes(horario.getMinute());
        
        String diferenca[] = String.valueOf(tempoGasto).split(":");
        int horaGasta = Integer.parseInt(diferenca[0]);
        int minutosGasto = Integer.parseInt(diferenca[1]);
        int hora = 0;
        for (int i = 1; i < horaGasta; i++) {
            hora = hora + 20;
        }
        double minuto = 0;
        for (int i = 0; i < minutosGasto; i++) {
            minuto = minuto + 0.33;
        }
        double total = hora + minuto;
        DecimalFormat formatador = new DecimalFormat("R$ #,##0.00");
        jTextFieldValorDaPassagem.setText(formatador.format(total));
        

    }//GEN-LAST:event_jButtonCalcularPassagemActionPerformed

    private void jButtonCadastrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarClienteActionPerformed
        try {
            new TelaDoCliente().setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(TelaDasPassagens.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonCadastrarClienteActionPerformed
    
    private void populaAssentojComboBox() throws Exception {
        try {
            String item = String.valueOf(jComboBoxRotas.getSelectedItem());
            String assento[] = item.split(";");
            int assentoOnibus = Integer.parseInt(assento[7]);
            Onibus onibus = onibusPersistencia.recuperaOnibusPorID(assentoOnibus);
            int numeroPoltronas = onibus.getNumeroDePoltronas();
            
            String onibusNumeroPoltronas[] = new String[numeroPoltronas];
            for (int i = 1; i < onibusNumeroPoltronas.length + 1; i++) {
                onibusNumeroPoltronas[i - 1] = "" + i;
            }
            
            DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(onibusNumeroPoltronas);
            jComboBoxAssento.setModel(comboBoxModel);
        } catch (Exception erro) {
            throw erro;
        }
    }
    private void jComboBoxRotasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxRotasItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            try {
                populaAssentojComboBox();
            } catch (Exception erro) {
                //Trata exceção
                JOptionPane.showMessageDialog(rootPane, erro.getMessage());
            }
        }
    }//GEN-LAST:event_jComboBoxRotasItemStateChanged
    
    private int recuperaIDRotasJComboBox(String dados) {
        String dadoRotas[] = dados.split(";");
        int idRotas = 0;
        for (int i = 0; i < dadoRotas.length; i++) {
            idRotas = Integer.parseInt(dadoRotas[0]);
        }
        return idRotas;
    }
    
    private String recuperaCPFClientesJComboBox(String dados) {
        String dadosCliente[] = dados.split(";");
        String cpfCliente = "";
        for (int i = 0; i < dadosCliente.length; i++) {
            cpfCliente = dadosCliente[1];
        }
        return cpfCliente;
    }
    
    private void limparCampos() {
        jTextFieldIdPassagem.setText("");
        jTextFieldValorDaPassagem.setText("");
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
        try {
            new TelaDasPassagens().setVisible(true);
            
        } catch (Exception erro) {
            Logger.getLogger(TelaDoCliente.class.getName()).log(Level.SEVERE, null, erro);
        }
        
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
    private javax.swing.JComboBox<String> jComboBoxAssento;
    private javax.swing.JComboBox<String> jComboBoxCliente;
    private javax.swing.JComboBox<String> jComboBoxFormasDePagamento;
    private javax.swing.JComboBox<String> jComboBoxRotas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelIDMarca;
    private javax.swing.JList jList1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTablePassagem;
    private javax.swing.JTextField jTextFieldIdPassagem;
    private javax.swing.JTextField jTextFieldValorDaPassagem;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables

}
