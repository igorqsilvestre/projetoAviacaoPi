/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.modelos;

/**
 *
 * @author eugeniojulio
 */
public class Marca {

    //Atributos

    private int id;
    private String descricao;
    private String imagem;

    //Metodos

    public Marca() {
        id = 0;
        descricao = "";
    }

    public Marca(int id, String descricao,String imagem) throws Exception {
        
        if(!descricao.isEmpty() || !descricao.equals("")){
            this.id = id;
            this.descricao = descricao;
            this.imagem = imagem;
        }else{
            throw new Exception("Campo não pode ser vazio!");
        }
       
    }

    public Marca(String dados) throws Exception {
        try {
            String vetorString[] = dados.split(";");
            this.id = Integer.parseInt(vetorString[0]);
            this.descricao = (vetorString[1]);
            this.imagem = (vetorString[2]);

        } catch (Exception erro) {
            throw new Exception("Erro na montagem do objeto");
        }
    }

    @Override
    public String toString() {
        return ("" + id + ";" + descricao+";"+imagem);
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
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) throws Exception {
        if(!descricao.isEmpty() || !descricao.equals("")){
            this.descricao = descricao;
        }else{
            throw new Exception("Campo não pode ser vazio!");
        }

        
    }

    /**
     * @return the imagem
     */
    public String getImagem() {
        return imagem;
    }

    /**
     * @param imagem the imagem to set
     */
    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
    
    

}
