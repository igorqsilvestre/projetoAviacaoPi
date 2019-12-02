/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import xyz.interfaces.IcrudEmissaoPassagem;
import xyz.modelos.EmissaoPassagem;

/**
 *
 * @author johns
 */
public class EmissaoPassagensPersistencia implements IcrudEmissaoPassagem {
    String arquivo = "emissaoPassagem.txt";
    
    @Override
    public ArrayList<EmissaoPassagem> recuperar() throws Exception {
         try {
            File fl = new File(arquivo);
            ArrayList<EmissaoPassagem> listaDeEmissaoDePassagens = new ArrayList<>();
            if (fl.exists()) {
                FileReader fr = new FileReader(arquivo);
                BufferedReader br = new BufferedReader(fr);
                String linha = "";
                while ((linha = br.readLine()) != null) {
                    EmissaoPassagem emissaopassagem = new EmissaoPassagem(linha);
                    listaDeEmissaoDePassagens.add(emissaopassagem);

                }
                br.close();
            }
            return listaDeEmissaoDePassagens;
        } catch (Exception erro) {
            throw erro;
        }
    }
}
