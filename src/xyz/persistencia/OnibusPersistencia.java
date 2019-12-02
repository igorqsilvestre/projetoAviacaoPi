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
import xyz.interfaces.IcrudOnibus;
import xyz.modelos.Onibus;
import xyz.modelos.Situacao;

/**
 *
 * @author Igor
 */
public class OnibusPersistencia implements IcrudOnibus {

    String arquivo = "onibus.txt";

    @Override
    public void incluir(Onibus objeto) throws Exception {
        try {
            if (consultaPlaca(objeto.getPlaca())) {
                FileWriter fw = new FileWriter(arquivo, true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(objeto.toString() + "\n");
                bw.close();
            }else{
                throw new Exception("Placa já existe insira outra");
            }

        } catch (Exception erro) {
            throw erro;
        }
    }

    @Override
    public boolean consultaPlaca(String placa) throws Exception {
        try {
            File file = new File(arquivo);
            if (file.exists()) {
                FileReader fr = new FileReader(arquivo);
                BufferedReader br = new BufferedReader(fr);
                String linha = "";
                while ((linha = br.readLine()) != null) {
                    String dados[] = linha.split(";");
                    if (dados[1].equals(placa)) {
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
    public ArrayList<Onibus> recuperar() throws Exception {
        try {
            File fl = new File(arquivo);
            ArrayList<Onibus> listaDeOnibus = new ArrayList<>();
            if (fl.exists()) {
                FileReader fr = new FileReader(arquivo);
                BufferedReader br = new BufferedReader(fr);
                String linha = "";
                while ((linha = br.readLine()) != null) {
                    Onibus onibus = new Onibus(linha);
                    listaDeOnibus.add(onibus);

                }
                br.close();
            }
            return listaDeOnibus;
        } catch (Exception erro) {
            throw erro;
        }
    }

    @Override
    public void excluir(int id) throws Exception {
        try {
            ArrayList<Onibus> listaArquivo = recuperar();
            FileWriter fw = new FileWriter(arquivo);
            BufferedWriter bw = new BufferedWriter(fw);

            if (listaArquivo != null) {
                for (int i = 0; i < listaArquivo.size(); i++) {
                    Onibus onibus = listaArquivo.get(i);
                    if (onibus.getId() != id) {
                        bw.write(onibus.toString() + "\n");
                    }
                }
            }

            bw.close();

        } catch (Exception e) {
            throw (e);
        }
    }

    @Override
    public int recuperaIDModeloPorOnibusSelecionado(int idOnibus) throws Exception {
        try {
            File fl = new File(arquivo);
            ArrayList<Onibus> listaDeOnibus = new ArrayList<>();
            int idModelo = 0;
            if (fl.exists()) {
                FileReader fr = new FileReader(arquivo);
                BufferedReader br = new BufferedReader(fr);
                String linha = "";
                while ((linha = br.readLine()) != null) {
                    String dadosOnibus[] = linha.split(";");
                    int id = Integer.parseInt(dadosOnibus[0]);
                    if (id == idOnibus) {
                        idModelo = Integer.parseInt(dadosOnibus[5]);
                    }

                }
                br.close();
            }
            return idModelo;
        } catch (Exception erro) {
            throw erro;
        }
    }

    @Override
    public void alterar(int idOnibus, String placa, int numeroPoltronas, int ano, Situacao situacaoOnibus, int idModelo) throws Exception {
        try {
            ArrayList<Onibus> listaArquivo = recuperar();
            FileWriter fw = new FileWriter(arquivo);
            BufferedWriter bw = new BufferedWriter(fw);

            Onibus onibuss = null;
            for (int i = 0; i < listaArquivo.size(); i++) {
                Onibus onibus = listaArquivo.get(i);

                if (onibus.getId() != idOnibus) {
                    bw.write(onibus.toString() + "\n");
                }
                if (onibus.getId() == idOnibus) {
                    onibuss = new Onibus(idOnibus, placa, numeroPoltronas, ano, situacaoOnibus, idModelo);
                    bw.write(onibuss + "\n");
                }
            }

            bw.close();

        } catch (Exception e) {
            throw (e);
        }
    }

    @Override
    public ArrayList<Onibus> recuperaOnibusAtivo() throws Exception {
        try {
            File fl = new File(arquivo);
            ArrayList<Onibus> listaDeOnibus = new ArrayList<>();
            if (fl.exists()) {
                FileReader fr = new FileReader(arquivo);
                BufferedReader br = new BufferedReader(fr);
                String linha = "";
                while ((linha = br.readLine()) != null) {
                    String[] dados = linha.split(";");
                    if (dados[4].equals("ativo")) {
                        listaDeOnibus.add(new Onibus(linha));
                    }

                }
                br.close();
            }
            return listaDeOnibus;
        } catch (Exception erro) {
            throw erro;
        }
    }

    @Override
    public Onibus recuperaOnibusPorID(int id) throws Exception {
        try {
            File fl = new File(arquivo);
            if (fl.exists()) {
                FileReader fr = new FileReader(arquivo);
                BufferedReader br = new BufferedReader(fr);
                String linha = "";
                while ((linha = br.readLine()) != null) {
                    String dados[] = linha.split(";");
                    int idOnibus = Integer.parseInt(dados[0]);
                    if (idOnibus == id) {
                        Onibus onibus = new Onibus(linha);
                        return onibus;
                    }

                }
                br.close();
            }
            return null;
        } catch (Exception erro) {
            throw erro;
        }
    }
    
     @Override
    public Onibus recuperaOnibusPorPlaca(String placa) throws Exception {
        try {
            File fl = new File(arquivo);
            if (fl.exists()) {
                FileReader fr = new FileReader(arquivo);
                BufferedReader br = new BufferedReader(fr);
                String linha = "";
                while ((linha = br.readLine()) != null) {
                    String dados[] = linha.split(";");
                    if (dados[1].equals(placa) ) {
                        Onibus onibus = new Onibus(linha);
                        return onibus;
                    }

                }
                br.close();
            }
            return null;
        } catch (Exception erro) {
            throw erro;
        }
    }
}
