/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.interfaces;

import java.util.ArrayList;
import xyz.modelos.Marca;
import xyz.modelos.Modelo;

/**
 *
 * @author Igor
 */
public interface IcrudModelo {
    void incluir(Modelo objeto)throws Exception;
    ArrayList<Modelo> recuperar()throws Exception;
    Marca recuperaMarcaPorDados(String dados) throws Exception;
    void excluir(int id)throws Exception;
    void alterar(int id, String descricao, Marca marca)throws Exception;
    ArrayList<Marca> recuperaMarcasPeloIDSelecionado(int idModelo) throws Exception;
    Modelo recuperaIDModeloPorDados(String dados) throws Exception;
    Modelo recuperaModeloPorID(int id)throws Exception;
    ArrayList<Modelo> recuperaModelosPeloIDSelecionado(int idModelo) throws Exception;
}
