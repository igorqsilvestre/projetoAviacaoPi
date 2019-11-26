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
import xyz.interfaces.IcrudRotas;
import xyz.modelos.Onibus;
import xyz.modelos.Rotas;

/**
 *
 * @author Igor
 */
public class RotasPersistencia implements IcrudRotas{
 
    String arquivo = "rotas.txt";

    @Override
    public void incluir(Rotas objeto) throws Exception {
         try {
            FileWriter fw = new FileWriter(arquivo, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(objeto.toString() + "\n");
            bw.close();

        } catch (Exception erro) {
            throw erro;
        }
    }

    public ArrayList<Rotas> recuperar()throws Exception {
         try {
            File fl = new File(arquivo);
            ArrayList<Rotas> listaDeRotas = new ArrayList<>();
            if (fl.exists()) {
                FileReader fr = new FileReader(arquivo);
                BufferedReader br = new BufferedReader(fr);
                String linha = "";
                while ((linha = br.readLine()) != null) {
                    Rotas rotas = new Rotas(linha);
                    listaDeRotas.add(rotas);

                }
                br.close();
            }
            return listaDeRotas;
        } catch (Exception erro) {
            throw erro;
        }
    }
    
}
