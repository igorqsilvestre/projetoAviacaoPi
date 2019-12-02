/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.modelos;

/**
 *
 * @author johns
 */
public class EmissaoPassagem {
    private String cpfCliente;    
    
    public EmissaoPassagem() 
    {
        this.cpfCliente = "";        
    }
    
    public EmissaoPassagem( String cpfCliente )
    {
        if (cpfCliente.length() != 11){         
            System.out.println("CPF Invalido");
        }    
        this.cpfCliente = cpfCliente;
    }
    
    
}
