/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.modelos;

/**
 *
 * @author Igor
 */
public class Modelo {

    private int id;
    private String descricao;
    private Marca marca;

    public Modelo() {
        id = 0;
        descricao = "";
        marca = null;
    }

    public Modelo(int id, String descricao, Marca marca) throws Exception {

        if (!descricao.isEmpty() || !descricao.equals("")) {
            this.id = id;
            this.descricao = descricao;
            this.marca = marca;
        } else {
            throw new Exception("O campo da descrição não pode ser vazio!");
        }
        
        if(marca == null){
            throw new Exception("Não existe marca cadastrada disponível para o modelo!");
        }

    }

    public Modelo(String dados, Marca marca) throws Exception {
        try {
            String vetorDados[] = dados.split(";");
            this.id = Integer.parseInt(vetorDados[0]);
            this.descricao = vetorDados[1];
            this.marca = marca;

        } catch (Exception erro) {
            throw new Exception("Erro na montagem do objeto");
        }
    }

    @Override
    public String toString() {
        return ("" + id + ";" + descricao + ";" + marca.getId());
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) throws Exception {
        if (!descricao.isEmpty() || !descricao.equals("")) {
            this.descricao = descricao;
        } else {
            throw new Exception("O campo da descrição não pode ser vazio!");
        }

    }

    /**
     * @return the marca
     */
    public Marca getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(Marca marca) {
        this.marca = marca;
    }

}
