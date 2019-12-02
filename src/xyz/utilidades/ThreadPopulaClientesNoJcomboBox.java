/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.utilidades;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import xyz.modelos.Cliente;
import xyz.persistencia.ClientePersistencia;

/**
 *
 * @author Igor
 */
public class ThreadPopulaClientesNoJcomboBox extends Thread {

    private JComboBox jComboBoxCliente;
    private boolean controle;
    private ClientePersistencia clientePersistencia = new ClientePersistencia();

    public ThreadPopulaClientesNoJcomboBox(JComboBox jComboBoxCliente, boolean controle) {
        this.jComboBoxCliente = jComboBoxCliente;
        this.controle = controle;
    }

    @Override
    public void run() {
        try {
            ArrayList<Cliente> listaDeClientes = clientePersistencia.recuperar();
            while (true) {
                if (controle) {
                    String saida[] = new String[listaDeClientes.size()];
                    for (int i = 0; i < listaDeClientes.size(); i++) {
                        Cliente cliente = null;
                        String lista = String.valueOf(listaDeClientes.get(i));
                        String dadosClientes[] = lista.split(";");
                        for (int j = 0; j < dadosClientes.length; j++) {
                            cliente = new Cliente(lista);
                        }

                        saida[i] = "" + cliente.getNome() + ";" + cliente.getCpf() + "";

                        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(saida);
                        jComboBoxCliente.setModel(comboBoxModel);

                    }
                }

                Thread.sleep(5000);
                ArrayList<Cliente> listaDeClientesAtual = new ClientePersistencia().recuperar();
                
                
                if (listaDeClientes.size() != listaDeClientesAtual.size()) {
                    listaDeClientes = listaDeClientesAtual;
                    controle = true;
                }else{
                    controle = false; 
                }

            }
        } catch (Exception erro) {
            erro.getMessage();
        }

    }
}
