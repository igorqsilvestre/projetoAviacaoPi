/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.persistencia;

import xyz.interfaces.IcrudMarca;
import xyz.modelos.Marca;
import java.io.*;
import java.util.ArrayList;
import xyz.modelos.Cliente;

/**
 *
 * @author eugeniojulio
 */
public class MarcaPersistencia implements IcrudMarca {

    String nomeDoArquivo = "marca.txt";

    @Override
    public void incluir(Marca objeto) throws Exception {
        try {
            //cria o arquivo
            FileWriter fw = new FileWriter(nomeDoArquivo, true);
            //Criar o buffer do arquivo
            BufferedWriter bw = new BufferedWriter(fw);
            //Escreve no arquivo
            bw.write(objeto.toString() + "\n");
            //fecha o arquivo
            bw.close();

        } catch (Exception erro) {
            throw erro;
        }
    }

    @Override
    public ArrayList<Marca> recuperar() throws Exception {
        try {
            File file = new File(nomeDoArquivo);
            ArrayList<Marca> listaMarca = new ArrayList<>();
            if (file.exists()) {
                FileReader fr = new FileReader(nomeDoArquivo);
                BufferedReader br = new BufferedReader(fr);
                String linha = "";
                while ((linha = br.readLine()) != null) {
                    Marca objeto = new Marca(linha);
                    listaMarca.add(objeto);
                }
                br.close();

            }

            return listaMarca;

        } catch (Exception erro) {
            throw erro;
        }
    }

    @Override
    public void excluir(int id) throws Exception {
        try {
            ArrayList<Marca> listaArquivo = recuperar();
            FileWriter fw = new FileWriter(nomeDoArquivo);
            BufferedWriter bw = new BufferedWriter(fw);

            if (listaArquivo != null) {
                for (int i = 0; i < listaArquivo.size(); i++) {
                    Marca marca = listaArquivo.get(i);
                    if (marca.getId() != id) {
                        bw.write(marca.toString() + "\n");
                    }
                }
            }

            bw.close();

        } catch (Exception e) {
            throw (e);
        }
    }

    @Override
    public void alterar(int id, String descricao, String imagem) throws Exception {
         try {
            ArrayList<Marca> listaArquivo = recuperar();
            ArrayList<Marca> listaNovaArquivo = new ArrayList<>();
            
            for (int i = 0; i < listaArquivo.size(); i++) {
                Marca marca = listaArquivo.get(i);
                if(marca.getId() == id){
                 listaNovaArquivo.add(i, new Marca(id,descricao,imagem));
                }else{
                 listaNovaArquivo.add(marca);
                }
            }
                FileWriter fw = new FileWriter(nomeDoArquivo);
                BufferedWriter bw = new BufferedWriter(fw);
            
            for (int i = 0; i < listaNovaArquivo.size(); i++) {
                Marca marcaa = listaNovaArquivo.get(i);
                bw.write(marcaa.toString() + "\n");
            }
            
            bw.close();
                
            
        } catch (Exception erro) {
            throw erro;
        }

    }

    @Override
    public Marca recuperarMarcaPorID(int id) throws Exception {
        try {
            File file = new File(nomeDoArquivo);
            if (file.exists()) {
                FileReader fr = new FileReader(nomeDoArquivo);
                BufferedReader br = new BufferedReader(fr);
                String linha = "";
                while ((linha = br.readLine()) != null) {
                    Marca marca = new Marca(linha);
                    if (marca.getId() == id) {
                        return marca;
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
