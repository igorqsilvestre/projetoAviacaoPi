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
            bw.write(objeto.toString()+"\n");
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
                if(listaDeMarcas.get(i).getId() == id){
                    marca =  listaDeMarcas.get(i);
                    break;
                }
            }
            
            return marca;

        } catch (Exception erro) {
            throw erro;
        }
    }

}
