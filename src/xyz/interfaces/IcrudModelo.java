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
    void excluir(int id)throws Exception;
    void alterar(int id, String descricao, Marca marca)throws Exception;
}
