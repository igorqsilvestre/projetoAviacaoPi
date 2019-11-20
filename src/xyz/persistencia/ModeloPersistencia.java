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
        File fl = new File(arquivo);
        ArrayList<Modelo> listaDeModelos = new ArrayList<>();
        if (fl.exists()) {
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);
            String linha = "";
            while ((linha = br.readLine()) != null) {
                Marca marca = recuperaMarcaPorID(linha);
                Modelo modelo = new Modelo(linha, marca);
                listaDeModelos.add(modelo);
                
            }
            br.close();
        }
        return listaDeModelos;
    }
    
    public Marca recuperaMarcaPorID(String dados) throws Exception {
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
            
            for (int i = 0; i < listaArquivo.size(); i++) {
                Modelo modelo = listaArquivo.get(i);
                if (marca.getId() == id) {
                    if (!descricao.equals("") || !descricao.isEmpty()) {
                        controle = true;
                        excluir(id);
                    } else {
                        throw new Exception("O campo da descrição não pode estar vazio!");
                    }
                    
                }
            }
            
            if (controle) {
                Modelo modelo = new Modelo(id, descricao, marca);
                incluir(modelo);
            }
            
        } catch (Exception e) {
            throw (e);
        }
    }
    
    public Marca recuperaMarcaPorDescricao(String descricao) throws Exception {
        try {            
            ArrayList<Marca> listaDeMarcas = new MarcaPersistencia().recuperar();
            Marca marca=null;
            for (int i = 0; i < listaDeMarcas.size(); i++) {
                if(listaDeMarcas.get(i).getDescricao().equals(descricao)){
                    marca = listaDeMarcas.get(i);
                    break;
                }
            }
            
            return marca;
            
        } catch (Exception e) {
            throw e;
        }
    }
    
}
