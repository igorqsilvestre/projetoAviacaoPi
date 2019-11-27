/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import xyz.interfaces.IcrudCliente;
import xyz.modelos.Cliente;

/**
 *
 * @author Igor
 */
public class ClientePersistencia implements IcrudCliente{

    String arquivo = "cliente.txt";

    @Override
    public void incluir(Cliente objeto) throws Exception {
        try {
            if (consultaCPF(objeto.getCpf())) {
                FileWriter fw = new FileWriter(arquivo, true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(objeto.toString() + "\n");
                bw.close();
            }else{
                throw new Exception("O cpf já existe, insira outro!");
            }

        } catch (Exception erro) {
            throw erro;
        }
    }

    @Override
    public boolean consultaCPF(String cpf) throws Exception {
        try {
            File file = new File(arquivo);
            if (file.exists()) {
                FileReader fr = new FileReader(arquivo);
                BufferedReader br = new BufferedReader(fr);
                String linha = "";
                while ((linha = br.readLine()) != null) {
                    String dados[] = linha.split(";");
                    if (dados[1].equals(cpf)) {
                        return false;
                    }

                }
                br.close();

            }

            return true;

        } catch (Exception erro) {
            throw erro;
        }
    }

    @Override
    public ArrayList<Cliente> recuperar() throws Exception {
        try {
            File file = new File(arquivo);
            ArrayList<Cliente> listaCliente = new ArrayList<>();
            if (file.exists()) {
                FileReader fr = new FileReader(arquivo);
                BufferedReader br = new BufferedReader(fr);
                String linha = "";
                while ((linha = br.readLine()) != null) {
                    Cliente objeto = new Cliente(linha);
                    listaCliente.add(objeto);
                }
                br.close();

            }

            return listaCliente;

        } catch (Exception erro) {
            throw erro;
        }
    }

    @Override
    public void excluir(String cpf)throws Exception {
         try {
            ArrayList<Cliente> listaArquivo = recuperar();
            FileWriter fw = new FileWriter(arquivo);
            BufferedWriter bw = new BufferedWriter(fw);

            if (listaArquivo != null) {
                for (int i = 0; i < listaArquivo.size(); i++) {
                    Cliente cliente = listaArquivo.get(i);
                    if (!cliente.getCpf().equals(cpf)) {
                        bw.write(cliente.toString() + "\n");
                    }
                }
            }

            bw.close();

        } catch (Exception e) {
            throw (e);
        }
    }
    
    

}
