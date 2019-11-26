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
import xyz.interfaces.IcrudModelo;
import xyz.modelos.Marca;
import xyz.modelos.Modelo;

/**
 *
 * @author Igor
 */
public class ModeloPersistencia implements IcrudModelo {

    String arquivo = "modelo.txt";

    @Override
    public void incluir(Modelo objeto) throws Exception {
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
    public ArrayList<Modelo> recuperar() throws Exception {
        try {
            File fl = new File(arquivo);
            ArrayList<Modelo> listaDeModelos = new ArrayList<>();
            if (fl.exists()) {
                FileReader fr = new FileReader(arquivo);
                BufferedReader br = new BufferedReader(fr);
                String linha = "";
                while ((linha = br.readLine()) != null) {
                    Marca marca = recuperaMarcaPorDados(linha);
                    Modelo modelo = new Modelo(linha, marca);
                    listaDeModelos.add(modelo);

                }
                br.close();
            }
            return listaDeModelos;
        } catch (Exception erro) {
            throw erro;
        }
    }
    
    @Override
    public Marca recuperaMarcaPorDados(String dados) throws Exception {
        try {
            String[] modelo = dados.split(";");
            int id = Integer.parseInt(modelo[2]);

            MarcaPersistencia persistencia = new MarcaPersistencia();
            ArrayList<Marca> listaDeMarcas = persistencia.recuperar();
            Marca marca = null;

            for (int i = 0; i < listaDeMarcas.size(); i++) {
                if (listaDeMarcas.get(i).getId() == id) {
                    marca = listaDeMarcas.get(i);
                    break;
                }
            }

            return marca;

        } catch (Exception erro) {
            throw erro;
        }
    }

    @Override
    public void excluir(int id) throws Exception {
        try {
            ArrayList<Modelo> listaArquivo = recuperar();
            FileWriter fw = new FileWriter(arquivo);
            BufferedWriter bw = new BufferedWriter(fw);

            if (listaArquivo != null) {
                for (int i = 0; i < listaArquivo.size(); i++) {
                    Modelo modelo = listaArquivo.get(i);
                    if (modelo.getId() != id) {
                        bw.write(modelo.toString() + "\n");
                    }
                }
            }

            bw.close();

        } catch (Exception e) {
            throw (e);
        }
    }

    @Override
    public void alterar(int id, String descricao, Marca marca) throws Exception {
        try {
            ArrayList<Modelo> listaArquivo = recuperar();
            boolean controle = false;

                Modelo modeloo=null;
            for (int i = 0; i < listaArquivo.size(); i++) {
                Modelo modelo = listaArquivo.get(i);
                if (modelo.getId() == id) {
                        modeloo = new Modelo(id, descricao, marca);
                        controle = true;
                        excluir(id);
                    

                }
            }

            if (controle) {
                incluir(modeloo);
            }

        } catch (Exception e) {
            throw (e);
        }
    }

    @Override
    public ArrayList<Marca> recuperaMarcasPeloIDSelecionado(int idModelo) throws Exception {
        FileReader fl = new FileReader(arquivo);
        BufferedReader br = new BufferedReader(fl);
        String linha = "";
        int idMarca = 0;
        ArrayList<Marca> novaListaDeMarcas = new ArrayList<>();
        ArrayList<Marca> listaVelhaDeMarcas = new ArrayList<>();

        //Recupera id da marca pelo id do modelo selecionado
        while ((linha = br.readLine()) != null) {
            String dadosModelo[] = linha.split(";");
            int id = Integer.parseInt(dadosModelo[0]);
            if (id == idModelo) {
                idMarca = Integer.parseInt(dadosModelo[2]);

            }
        }

        //Recupera uma lista de Marcas com a marca do modelo selecionado em primeiro na lista
        MarcaPersistencia persistencia = new MarcaPersistencia();
        ArrayList<Marca> listaDeMarcas = persistencia.recuperar();

        for (int y = 0; y < listaDeMarcas.size(); y++) {
            if (listaDeMarcas.get(y).getId() == idMarca) {
                novaListaDeMarcas.add(listaDeMarcas.get(y));
            } else {
                listaVelhaDeMarcas.add(listaDeMarcas.get(y));
            }
        }

        novaListaDeMarcas.addAll(listaVelhaDeMarcas);
        return novaListaDeMarcas;
    }

    @Override
    public Modelo recuperaIDModeloPorDados(String dados) throws Exception {
        try {
            File fl = new File(arquivo);
            ArrayList<Modelo> listaDeModelos = new ArrayList<>();
            if (fl.exists()) {
                FileReader fr = new FileReader(arquivo);
                BufferedReader br = new BufferedReader(fr);
                String linha = "";
                while ((linha = br.readLine()) != null) {
                    String dadosArquivo[] = {linha};
                    
                    Marca marca = recuperaMarcaPorDados(linha);
                    Modelo modelo = new Modelo(linha, marca);
                    listaDeModelos.add(modelo);

                }
                br.close();
            }
            return null;
        } catch (Exception erro) {
            throw erro;
        }
    }

    @Override
    public Modelo recuperaModeloPorID(int id)throws Exception{
        try {
            File fl = new File(arquivo);
            
            if (fl.exists()) {
                FileReader fr = new FileReader(arquivo);
                BufferedReader br = new BufferedReader(fr);
                String linha = "";
                while ((linha = br.readLine()) != null) {
                    Marca marca = recuperaMarcaPorDados(linha);
                    Modelo modelo = new Modelo(linha, marca);
                    if(modelo.getId() == id){
                        return modelo;
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
    public ArrayList<Modelo> recuperaModelosPeloIDSelecionado(int idModelo) throws Exception{
        FileReader fl = new FileReader(arquivo);
        BufferedReader br = new BufferedReader(fl);
        MarcaPersistencia marcaPersistencia = new MarcaPersistencia();
        String linha = "";
        int idMarca = 0;
        ArrayList<Modelo> novaListaDeModelos = new ArrayList<>();
        ArrayList<Modelo> listaVelhaDeModelos = new ArrayList<>();
        Modelo modelo=null;

        //Recupera a marca do modelo pelo id do modelo selecionado
        while ((linha = br.readLine()) != null) {
            String dadosModelo[] = linha.split(";");
            int id = Integer.parseInt(dadosModelo[0]);
            if (id == idModelo) {
                String descricao = dadosModelo[1];
                idMarca = Integer.parseInt(dadosModelo[2]);
                Marca marca = marcaPersistencia.recuperarMarcaPorID(idMarca);
                modelo = new Modelo(id,descricao,marca);
            }
        }
        
        ArrayList<Modelo>listaDeModelos = new ModeloPersistencia().recuperar();
        
        //Recupera uma lista com o modelo selecionado em primeiro na lista
        for (int i = 0; i < listaDeModelos.size(); i++) {
            if(listaDeModelos.get(i).getId() == modelo.getId()){
                novaListaDeModelos.add(modelo);
            }else{
                listaVelhaDeModelos.add(listaDeModelos.get(i));
            }
        }
        novaListaDeModelos.addAll(listaVelhaDeModelos);
        return novaListaDeModelos;
    }
}
