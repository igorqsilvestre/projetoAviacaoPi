/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.utilidades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import xyz.modelos.Onibus;

/**
 *
 * @author Igor
 */
public class GeradorDeCidades {
    
    String arquivo = "cidades.txt";

    public void gerar()throws Exception{
        try {
            FileWriter fw = new FileWriter(arquivo, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Goiânia\nUberlândia\nUberaba\nSão Paulo");
            bw.close();

        } catch (Exception erro) {
            throw erro;
        }
    }
    
    public ArrayList<String>recuperar()throws Exception{
        try {
            File fl = new File(arquivo);
            ArrayList<String> listaDeCidades = new ArrayList<>();
            if (fl.exists()) {
                FileReader fr = new FileReader(arquivo);
                BufferedReader br = new BufferedReader(fr);
                String linha = "";
                while ((linha = br.readLine()) != null) {
                    listaDeCidades.add(linha);

                }
                br.close();
            }
            return listaDeCidades;
        } catch (Exception erro) {
            throw erro;
        }
        
    }

    
}
