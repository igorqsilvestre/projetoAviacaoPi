/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.utilidades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Igor Queiroz
 */
public class GeradorDeIdentificadores {
    private int identificador = 0;
    String nomeDoArquivo ="DadosId.txt";
    
    public GeradorDeIdentificadores() throws FileNotFoundException, IOException{
        FileReader fr = new FileReader(nomeDoArquivo);
        BufferedReader br  = new BufferedReader(fr);
        String linha=br.readLine();
        identificador = Integer.parseInt(linha);
        br.close();
    }
    public int getIdentificador(){
        return ++identificador;
    }
    public void finalizar() throws IOException{
         //cria o arquivo
         FileWriter fw = new FileWriter(nomeDoArquivo,false);
         //Criar o buffer do arquivo
         BufferedWriter bw =new BufferedWriter(fw);
         //Escreve no arquivo
         String saida = identificador+"";
         bw.write(saida);
         //fecha o arquivo
         bw.close();		
    }
}
