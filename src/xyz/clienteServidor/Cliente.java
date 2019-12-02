/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.clienteServidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import xyz.modelos.Passagem;

/**
 *
 * @author johns
 */
public class Cliente {
    
    public static void main(String[] args)
    {
        try {
            //Estabelecer Conexão com o Servidor
            //Trocar mensagens com o Servidor

            //Cria conexão entre Cliente e Servidor
            Socket socket = new Socket( "localhost", 5555 );
            
            //Criação dos Streams (Entrada e Saida)
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            
            System.out.println("Enviando Mensagem...");
            String passagensEmitidas = "Hello";
            output.writeUTF(passagensEmitidas);
            output.flush(); //liberar o buffer para envio
            
            passagensEmitidas = input.readUTF();
            System.out.println("Resposta: "+passagensEmitidas);
            
            input.close();
            output.close();
            socket.close();
            
        } catch (IOException ex) {
            System.out.println("Erro no Cliente "+ex);
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String passagensEmitidas ( ArrayList<Passagem> listaPassagem )
    {
        //Recebe o Array List das passagens 
        return "";
    }
}
