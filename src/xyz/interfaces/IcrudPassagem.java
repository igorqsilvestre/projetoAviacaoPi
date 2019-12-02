/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.interfaces;

import java.util.ArrayList;
import xyz.modelos.Passagem;

/**
 *
 * @author Igor
 */
public interface IcrudPassagem {
     void incluir(Passagem objeto) throws Exception;
     ArrayList<Passagem>recuperar()throws Exception;
     void excluir(int id)throws Exception;
     Passagem recuperarPassagemPorID(int id) throws Exception;
}
