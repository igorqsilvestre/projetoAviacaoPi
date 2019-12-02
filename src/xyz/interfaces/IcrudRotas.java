/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.interfaces;

import java.util.ArrayList;
import xyz.modelos.Rotas;

/**
 *
 * @author Igor
 */
public interface IcrudRotas {
    void incluir(Rotas objeto)throws Exception;
    ArrayList<Rotas> recuperar()throws Exception;
     void excluir(int id)throws Exception;
     Rotas recuperaRotaPorId(int id)throws Exception;
     ArrayList<String> recuperaListaOrdemSelecionadaCidadeOrigem(String cidadeOrigem,ArrayList<String>cidades) throws Exception ;
     ArrayList<String> recuperaListaOrdemSelecionadaCidadeDestino(String cidadeDestino,ArrayList<String>cidades) throws Exception;
     void alterar(int idRotas, String cidadeOrigem, String cidadeDestino, String dataIda, String dataChegada, String saidaHorarioIda, String saidaHorarioChegada, int idOnibus) throws Exception;
}
