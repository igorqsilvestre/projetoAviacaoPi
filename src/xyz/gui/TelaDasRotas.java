package xyz.gui;

import java.awt.event.ItemEvent;
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
import xyz.utilidades.GeradorDeCidades;
import xyz.utilidades.GeradorDeIdentificadores;

/**
 *
 * @author eugeniojulio
 */
public class TelaDasRotas extends javax.swing.JFrame {

    private ModeloPersistencia modeloPersistencia = new ModeloPersistencia();
    private MarcaPersistencia marcaPersistencia = new MarcaPersistencia();
    private OnibusPersistencia onibusPersistencia = new OnibusPersistencia();
    private RotasPersistencia rotasPersistencia = new RotasPersistencia();
    private GeradorDeCidades geradorCidades = new GeradorDeCidades();
//    private Situacao situacao;
//    private int idDoModelo;

    /**
     * Creates new form TelaDaMarca
     *
     * @throws java.lang.Exception
     */
    public TelaDasRotas() throws Exception {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        jTextFieldIdRotas.setEnabled(false);
        adicionaCidadesOrigemjComboBox();
        adicionaCidadesDestinojComboBox();
        adicionaListaDeOnibusJComboBox(onibusPersistencia.recuperaOnibusAtivo());
        this.iniciar();

        jButtonAlterar.setEnabled(false);

    }

    private void mostrarDadosRotas(ArrayList<Rotas> listaDeRotas) throws Exception {
        try {
            DefaultTableModel model = (DefaultTableModel) jTableRotas.getModel();

            model.setNumRows(0);
            for (int i = 0; i < listaDeRotas.size(); i++) {
                String[] saida = new String[10];
                Rotas aux = listaDeRotas.get(i);
                saida[0] = "" + aux.getId();
                saida[1] = aux.getCidadeOrigem();
                saida[2] = "" + aux.getCidadeDestino();
                saida[3] = "" + aux.getDataIda();
                saida[4] = "" + aux.getDataChegada();
                saida[5] = "" + aux.getHorarioIda();
                saida[6] = "" + aux.getHorarioChegada();
                Onibus onibus = onibusPersistencia.recuperaOnibusPorID(aux.getIdOnibus());
                Modelo modelo = modeloPersistencia.recuperaModeloPorID(onibus.getIdModelo());
                Marca marca = modeloPersistencia.recuperaMarcaPorDados(modelo.toString());
                saida[7] = "" + onibus.getPlaca();
                saida[8] = "" + modelo.getDescricao();
                saida[9] = "" + marca.getDescricao();

                model.addRow(saida);
            }

            jTableRotas.setModel(model);
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }

    }

    private void iniciar() {
        try {
            mostrarDadosRotas(rotasPersistencia.recuperar());

        } catch (Exception e) {
            DefaultTableModel model = (DefaultTableModel) jTableRotas.getModel();
            //Limpa a tabela 
            model.setNumRows(0);
            String[] saida = new String[10];
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

            //Incluir nova linha na Tabela
            model.addRow(saida);
        }
    }

    private void adicionaCidadesOrigemjComboBox() {
        try {
            geradorCidades.gerar();
            ArrayList<String> cidades = geradorCidades.recuperar();
            DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(cidades.toArray());
            jComboBoxCidadesOrigem.setModel(comboBoxModel);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void adicionaCidadesDestinojComboBox() {
        try {

            geradorCidades.gerar();
            ArrayList<String> cidades = geradorCidades.recuperar();
            ArrayList<String> cidadesDestino = new ArrayList<>();
            for (int i = 0; i < cidades.size(); i++) {
                String cidadeOrigem = String.valueOf(jComboBoxCidadesOrigem.getSelectedItem());
                if (!cidadeOrigem.equals(cidades.get(i))) {
                    cidadesDestino.add(cidades.get(i));
                }
            }
            DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(cidadesDestino.toArray());
            jComboBoxCidadesDestino.setModel(comboBoxModel);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void adicionaListaDeOnibusJComboBox(ArrayList<Onibus> listaDeOnibus) {
        try {
            String saida[] = new String[listaDeOnibus.size()];
            for (int i = 0; i < listaDeOnibus.size(); i++) {
                Onibus onibus = null;
                String lista = String.valueOf(listaDeOnibus.get(i));
                String dadosOnibus[] = lista.split(";");
                for (int j = 0; j < dadosOnibus.length; j++) {
                    onibus = new Onibus(lista);
                }

                Modelo modelo = modeloPersistencia.recuperaModeloPorID(onibus.getIdModelo());
                Marca marca = modeloPersistencia.recuperaMarcaPorDados(modelo.toString());
                saida[i] = "" + onibus.getId() + ";" + onibus.getPlaca() + ";" + modelo.getDescricao() + ";" + marca.getDescricao() + "";
            }

            DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(saida);
            jComboBoxOnibus.setModel(comboBoxModel);

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
        jTextFieldIdRotas = new javax.swing.JTextField();
        jButtonIncluir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableRotas = new javax.swing.JTable();
        jButtonAlterar = new javax.swing.JButton();
        jComboBoxCidadesOrigem = new javax.swing.JComboBox();
        jButtonBuscar = new javax.swing.JButton();
        jLabelIDMarca = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        idModelo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxCidadesDestino = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxOnibus = new javax.swing.JComboBox();
        jButtonExluir = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jComboBoxHorasIda = new javax.swing.JComboBox<>();
        ComboBoxMinutosIda = new javax.swing.JComboBox<>();
        jComboBoxHorasChegada = new javax.swing.JComboBox<>();
        ComboBoxMinutosChegada = new javax.swing.JComboBox<>();
        jFormatteDataIda = new javax.swing.JFormattedTextField();
        jFormattedDataChegada = new javax.swing.JFormattedTextField();

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
        jLabel1.setText("CADASTRO DAS ROTAS");
        jLabel1.setMaximumSize(new java.awt.Dimension(298, 32));

        jLabel2.setText("IDENTIFICADOR");

        jButtonIncluir.setText("INCLUIR");
        jButtonIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIncluirActionPerformed(evt);
            }
        });

        jTableRotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDENTIFICADOR", "CIDADE ORIGEM", "CIDADE DESTINO", "DATA DE IDA", "DATA DE CHEGADA", "HORÁRIO DE IDA", "HORÁRIO DE CHEGADA", "ONIBUS", "MARCA", "MODELO"
            }
        ));
        jScrollPane1.setViewportView(jTableRotas);

        jButtonAlterar.setText("ALTERAR");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });

        jComboBoxCidadesOrigem.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxCidadesOrigemItemStateChanged(evt);
            }
        });

        jButtonBuscar.setText("BUSCAR");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        jLabel8.setText("CIDADE ORIGEM");

        jLabel3.setText("CIDADE DESTINO");

        jLabel4.setText("DATA DE IDA");

        jLabel6.setText("DATA DE CHEGADA");

        jLabel7.setText("ÔNIBUS");

        jButtonExluir.setText("EXCLUIR");
        jButtonExluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExluirActionPerformed(evt);
            }
        });

        jLabel9.setText("HORÁRIO DE IDA");

        jLabel10.setText("HORÁRIO DE CHEGADA");

        jComboBoxHorasIda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", " " }));

        ComboBoxMinutosIda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        jComboBoxHorasChegada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", " " }));

        ComboBoxMinutosChegada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        try {
            jFormatteDataIda.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            jFormattedDataChegada.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

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
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(idModelo))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButtonIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButtonExluir, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButtonAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel8)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel9)
                                                .addComponent(jLabel6)
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel2))
                                        .addGap(85, 85, 85)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jComboBoxHorasIda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(ComboBoxMinutosIda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jComboBoxHorasChegada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(ComboBoxMinutosChegada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(jFormattedDataChegada, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jComboBoxCidadesOrigem, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jComboBoxCidadesDestino, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jTextFieldIdRotas, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jComboBoxOnibus, javax.swing.GroupLayout.Alignment.LEADING, 0, 198, Short.MAX_VALUE)
                                                    .addComponent(jFormatteDataIda, javax.swing.GroupLayout.Alignment.LEADING))
                                                .addGap(18, 18, 18)
                                                .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldIdRotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonBuscar)
                            .addComponent(jLabel2))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxCidadesOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxCidadesDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jFormatteDataIda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedDataChegada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(ComboBoxMinutosIda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxHorasIda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jComboBoxHorasChegada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboBoxMinutosChegada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxOnibus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(idModelo)
                        .addGap(194, 194, 194)))
                .addComponent(jLabelIDMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonIncluir)
                    .addComponent(jButtonAlterar)
                    .addComponent(jButtonExluir))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIncluirActionPerformed

        try {

            GeradorDeIdentificadores gerarID = new GeradorDeIdentificadores();
            int id = gerarID.getIdentificador();

            String cidadeOrigem = (String.valueOf(jComboBoxCidadesOrigem.getSelectedItem()));
            String cidadeDestino = String.valueOf(jComboBoxCidadesDestino.getSelectedItem());

            String dataIda = jFormatteDataIda.getText();
            String dataChegada = jFormattedDataChegada.getText();

            String horasIda = String.valueOf(jComboBoxHorasIda.getSelectedItem());
            String minutosIda = String.valueOf(ComboBoxMinutosIda.getSelectedItem());
            String horarioIda =""+horasIda+":"+minutosIda+"";
            String horasChegada = String.valueOf(jComboBoxHorasChegada.getSelectedItem());
            String minutosChegada = String.valueOf(ComboBoxMinutosChegada.getSelectedItem());
            String horarioChegada = ""+horasChegada+":"+minutosChegada+"";

            String onibus = String.valueOf(jComboBoxOnibus.getSelectedItem());
            int idOnibus = recuperaIDOnibusPorDadoSelecionadoJcomboBox(onibus);

            Rotas rotas = new Rotas(id, cidadeOrigem, cidadeDestino, dataIda, dataChegada, horarioIda, horarioChegada, idOnibus);

            rotasPersistencia.incluir(rotas);

            iniciar();
            limparCampos();
            gerarID.finalizar();
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

    private void jComboBoxCidadesOrigemItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxCidadesOrigemItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            adicionaCidadesDestinojComboBox();
        }
    }//GEN-LAST:event_jComboBoxCidadesOrigemItemStateChanged

    private void jButtonExluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExluirActionPerformed
       try {
            int indice = jTableRotas.getSelectedRow();
            if (indice != -1) {
                int opcao = JOptionPane.showConfirmDialog(null, "Você realmente deseja excluir ?", "Alerta", JOptionPane.WARNING_MESSAGE);
                if (opcao == 0) {
                    String idEmString = String.valueOf(jTableRotas.getValueAt(indice, 0));
                    int id = Integer.parseInt(idEmString);
                    rotasPersistencia.excluir(id);
                    mostrarDadosRotas(rotasPersistencia.recuperar());
                    limparCampos();
                    
                }
                
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jButtonExluirActionPerformed

    private int recuperaIDOnibusPorDadoSelecionadoJcomboBox(String dados) {
        String dadoOnibus[] = dados.split(";");
        int idOnibus = 0;
        for (int i = 0; i < dadoOnibus.length; i++) {
            idOnibus = Integer.parseInt(dadoOnibus[0]);
        }
        return idOnibus;
    }
    
    
    private void limparCampos() {
        jTextFieldIdRotas.setText("");
        jFormatteDataIda.setText("");
        jFormattedDataChegada.setText("");
        jComboBoxHorasIda.setSelectedIndex(0);
        ComboBoxMinutosIda.setSelectedIndex(0);
        jComboBoxHorasChegada.setSelectedIndex(0);
        ComboBoxMinutosChegada.setSelectedIndex(0);
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
            java.util.logging.Logger.getLogger(TelaDasRotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaDasRotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaDasRotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaDasRotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                    new TelaDasRotas().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(TelaDasRotas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxMinutosChegada;
    private javax.swing.JComboBox<String> ComboBoxMinutosIda;
    private javax.swing.JLabel idModelo;
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonExluir;
    private javax.swing.JButton jButtonIncluir;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBoxCidadesDestino;
    private javax.swing.JComboBox jComboBoxCidadesOrigem;
    private javax.swing.JComboBox<String> jComboBoxHorasChegada;
    private javax.swing.JComboBox<String> jComboBoxHorasIda;
    private javax.swing.JComboBox jComboBoxOnibus;
    private javax.swing.JFormattedTextField jFormatteDataIda;
    private javax.swing.JFormattedTextField jFormattedDataChegada;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelIDMarca;
    private javax.swing.JList jList1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableRotas;
    private javax.swing.JTextField jTextFieldIdRotas;
    // End of variables declaration//GEN-END:variables
}
