/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.interfaces;
import java.util.ArrayList;
import xyz.modelos.Onibus;
import xyz.modelos.Situacao;
/**
 *
 * @author eugeniojulio
 */
public interface IcrudOnibus {
    void incluir(Onibus objeto)throws Exception;
    ArrayList<Onibus> recuperar() throws Exception;
    void excluir(int id)throws Exception;
    int recuperaIDModeloPorOnibusSelecionado(int idOnibus) throws Exception;
    void alterar(int idOnibus, String placa, int numeroPoltronas, int ano, Situacao situacaoOnibus, int idModelo) throws Exception ;
    ArrayList<Onibus> recuperaOnibusAtivo()throws Exception;
    Onibus recuperaOnibusPorID(int id) throws Exception;
}
