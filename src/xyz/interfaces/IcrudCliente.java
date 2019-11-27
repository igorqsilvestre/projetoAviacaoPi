/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.interfaces;

import java.util.ArrayList;
import xyz.modelos.Cliente;

/**
 *
 * @author Igor
 */
public interface IcrudCliente {
     void incluir(Cliente cliente)throws Exception;
     boolean consultaCPF(String cpf) throws Exception;
     ArrayList<Cliente> recuperar() throws Exception; 
     void excluir(String cpf) throws Exception;
}
