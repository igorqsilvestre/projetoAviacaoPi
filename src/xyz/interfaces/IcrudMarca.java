/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.interfaces;
import xyz.modelos.Marca;
import java.util.ArrayList;
/**
 *
 * @author eugeniojulio
 */
public interface IcrudMarca {
    void incluir(Marca objeto)throws Exception;
    ArrayList<Marca> recuperar() throws Exception;
    void excluir(int id)throws Exception;
    void alterar(int id,String descricao, String imagem)throws Exception;
}
