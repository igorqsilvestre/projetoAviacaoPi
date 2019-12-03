/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.modelos;

import java.util.Calendar;

/**
 *
 * @author Igor
 */
public class Onibus {

    private int id;
    private String placa;
    private int numeroDePoltronas;
    private int anoDeFabricacao;
    private Situacao situacao;
    private int idModelo;

    public Onibus() {
        id = 0;
        placa = "";
        numeroDePoltronas = 0;
        anoDeFabricacao = 0;
        situacao = Situacao.inativo;
        idModelo = 0;

    }

    public Onibus(int id, String placa, int numeroDePoltronas, int anoDeFabricacao, Situacao situacao, int idModelo) throws Exception {
        if (placa.length() != 7) {
            throw new Exception("O tamanho do número da placa não pode ser diferente de 7!");
        }
        
        if(placa.isEmpty()){
            throw new Exception("O campo da placa não pode estar vazio!");
        }
        
        
        if(numeroDePoltronas > 50 || numeroDePoltronas <=0){
            throw new Exception("O o número de poltronas não pode ser maior que 50 ou menor do igual a 0!");
        }

        if (anoDeFabricacao <=0) {
            throw new Exception("O campo não pode ser menor ou igual 0");
        }
        
        if(anoDeFabricacao < 1998 || anoDeFabricacao > Calendar.getInstance().get(Calendar.YEAR)){
            throw new Exception("O ano de fabricação não pode ser menor do que 1998 ou maior do que o ano atual!");
        }
        
        
        
        this.id = id;
        this.placa = placa;
        this.numeroDePoltronas = numeroDePoltronas;
        this.anoDeFabricacao = anoDeFabricacao;
        this.situacao = situacao;
        this.idModelo = idModelo;
    }

    public Onibus(String dados) throws Exception {
        try {
            String vetorDados[] = dados.split(";");
            this.id = Integer.parseInt(vetorDados[0]);
            this.placa = vetorDados[1];
            this.numeroDePoltronas = Integer.parseInt(vetorDados[2]);
            this.anoDeFabricacao = Integer.parseInt(vetorDados[3]);
            this.situacao = Situacao.valueOf(vetorDados[4]);
            this.idModelo = Integer.parseInt(vetorDados[5]);

        } catch (Exception erro) {
            throw new Exception("Erro na montagem do objeto");
        }
    }

    @Override
    public String toString() {
        return "" + getId() + ";" + getPlaca() + ";" + getNumeroDePoltronas() + ";" + getAnoDeFabricacao() + ";" + getSituacao() + ";" + getIdModelo();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) throws Exception {
        if (placa.length() != 7) {
             throw new Exception("O tamanho do número da placa não pode ser maior ou menor que 7!");
        }
        if(placa.isEmpty()){
            throw new Exception("O campo da placa não pode estar vazio!");
        }
        
        this.placa = placa;
        
    }

    /**
     * @return the numeroDePoltronas
     */
    public int getNumeroDePoltronas() {
        return numeroDePoltronas;
    }

    /**
     * @param numeroDePoltronas the numeroDePoltronas to set
     */
    public void setNumeroDePoltronas(int numeroDePoltronas) throws Exception {
        if (numeroDePoltronas <= 0 || numeroDePoltronas > 50) {
            throw new Exception("O numero de poltronas não pode ser menor ou igual a 0 ou maior que 50!");
        } else {
            this.numeroDePoltronas = numeroDePoltronas;
        }

    }

    /**
     * @return the anoDeFabricacao
     */
    public int getAnoDeFabricacao() {
        return anoDeFabricacao;
    }

    /**
     * @param anoDeFabricacao the anoDeFabricacao to set
     */
    public void setAnoDeFabricacao(int anoDeFabricacao) throws Exception {
        if(anoDeFabricacao < 1998 || anoDeFabricacao > Calendar.getInstance().get(Calendar.YEAR)){
              throw new Exception("O ano não pode ser menor do que 1998 ou maior do que o ano atual!");
        }else{
            this.anoDeFabricacao = anoDeFabricacao;
        }
        
    }

    /**
     * @return the situacao
     */
    public Situacao getSituacao() {
        return situacao;
    }

    /**
     * @param situacao the situacao to set
     */
    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    /**
     * @return the idModelo
     */
    public int getIdModelo() {
        return idModelo;
    }

    /**
     * @param idModelo the idModelo to set
     */
    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

}
