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
import xyz.interfaces.IcrudPassagem;
import xyz.modelos.Marca;
import xyz.modelos.Onibus;
import xyz.modelos.Passagem;

/**
 *
 * @author Igor
 */
public class PassagemPersistencia implements IcrudPassagem {

    String arquivo = "passagem.txt";

    @Override
    public void incluir(Passagem objeto) throws Exception {
        try {
            FileWriter fw = new FileWriter(arquivo, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(objeto.toString() + "\n");
            bw.close();

        } catch (Exception erro) {
            throw erro;
        }
    }

    @Override
    public ArrayList<Passagem> recuperar() throws Exception {
        try {
            File fl = new File(arquivo);
            ArrayList<Passagem> listaDePassagens = new ArrayList<>();
            if (fl.exists()) {
                FileReader fr = new FileReader(arquivo);
                BufferedReader br = new BufferedReader(fr);
                String linha = "";
                while ((linha = br.readLine()) != null) {
                    Passagem passagem = new Passagem(linha);
                    listaDePassagens.add(passagem);

                }
                br.close();
            }
            return listaDePassagens;
        } catch (Exception erro) {
            throw erro;
        }
    }

    @Override
    public void excluir(int id) throws Exception {
        try {
            ArrayList<Passagem> listaArquivo = recuperar();
            FileWriter fw = new FileWriter(arquivo);
            BufferedWriter bw = new BufferedWriter(fw);

            if (listaArquivo != null) {
                for (int i = 0; i < listaArquivo.size(); i++) {
                    Passagem passagem = listaArquivo.get(i);
                    if (passagem.getId() != id) {
                        bw.write(passagem.toString() + "\n");
                    }
                }
            }

            bw.close();

        } catch (Exception e) {
            throw (e);
        }
    }

    @Override
    public Passagem recuperarPassagemPorID(int id) throws Exception {
        try {
            File file = new File(arquivo);
            Passagem passagem = null;
            if (file.exists()) {
                FileReader fr = new FileReader(arquivo);
                BufferedReader br = new BufferedReader(fr);
                String linha = "";
                while ((linha = br.readLine()) != null) {
                    String dados[] = linha.split(";");
                    int idPassagem = Integer.parseInt(dados[0]);
                    if (idPassagem == id) {
                        passagem = new Passagem(linha);
                    }

                }
                br.close();

            }

            return passagem;

        } catch (Exception erro) {
            throw erro;
        }

    }
}
