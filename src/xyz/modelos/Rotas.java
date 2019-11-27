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
public class Rotas {

    private int id;
    private String cidadeOrigem;
    private String cidadeDestino;
    private String dataIda;
    private String dataVolta;
    private String horarioIda;
    private String horarioChegada;
    private int idOnibus;

    public Rotas() {
        this.id = 0;
        this.cidadeOrigem = "";
        this.cidadeDestino = "";
        this.dataIda = "";
        this.dataVolta = "";
        this.horarioIda = "";
        this.horarioChegada = "";
        this.idOnibus = 0;

    }

    public Rotas(int id, String cidadeOrigem, String cidadeDestino, String dataIda, String dataVolta, String horarioIda, String horarioChegada, int idOnibus) {
        this.id = id;
        this.cidadeOrigem = cidadeOrigem;
        this.cidadeDestino = cidadeDestino;
        this.dataIda = dataIda;
        this.dataVolta = dataVolta;
        this.horarioIda = horarioIda;
        this.horarioChegada = horarioChegada;
        this.idOnibus = idOnibus;
    }

    public Rotas(String dados) throws Exception {
        try {
            String vetorDados[] = dados.split(";");
            this.id = Integer.parseInt(vetorDados[0]);
            this.cidadeOrigem = vetorDados[1];
            this.cidadeDestino = vetorDados[2];
            this.dataIda = vetorDados[3];
            this.dataVolta = vetorDados[4];
            this.horarioIda = vetorDados[5];
            this.horarioChegada = vetorDados[6];
            this.idOnibus = Integer.parseInt(vetorDados[7]);

        } catch (Exception erro) {
            throw new Exception("Erro na montagem do objeto");
        }
    }

    @Override
    public String toString() {
        return ""+getId()+";"+getCidadeOrigem()+";"+getCidadeDestino()+";"+getDataIda()+";"+getDataVolta()+";"+getHorarioIda()+";"+getHorarioChegada()+";"+getIdOnibus()+"";
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
     * @return the dataIda
     */
    public String getDataIda() {
        return dataIda;
    }

    /**
     * @param dataIda the dataIda to set
     */
    public void setDataIda(String dataIda) {
        this.dataIda = dataIda;
    }

    /**
     * @return the dataVolta
     */
    public String getDataVolta() {
        return dataVolta;
    }

    /**
     * @param dataVolta the dataVolta to set
     */
    public void setDataVolta(String dataVolta) {
        this.dataVolta = dataVolta;
    }

    /**
     * @return the idOnibus
     */
    public int getIdOnibus() {
        return idOnibus;
    }

    /**
     * @param idOnibus the idOnibus to set
     */
    public void setIdOnibus(int idOnibus) {
        this.idOnibus = idOnibus;
    }

    /**
     * @return the cidadeOrigem
     */
    public String getCidadeOrigem() {
        return cidadeOrigem;
    }

    /**
     * @param cidadeOrigem the cidadeOrigem to set
     */
    public void setCidadeOrigem(String cidadeOrigem) {
        this.cidadeOrigem = cidadeOrigem;
    }

    /**
     * @return the cidadeDestino
     */
    public String getCidadeDestino() {
        return cidadeDestino;
    }

    /**
     * @param cidadeDestino the cidadeDestino to set
     */
    public void setCidadeDestino(String cidadeDestino) {
        this.cidadeDestino = cidadeDestino;
    }

    /**
     * @return the horarioIda
     */
    public String getHorarioIda() {
        return horarioIda;
    }

    /**
     * @param horarioIda the horarioIda to set
     */
    public void setHorarioIda(String horarioIda) {
        this.horarioIda = horarioIda;
    }

    /**
     * @return the horarioChegada
     */
    public String getHorarioChegada() {
        return horarioChegada;
    }

    /**
     * @param horarioChegada the horarioChegada to set
     */
    public void setHorarioChegada(String horarioChegada) {
        this.horarioChegada = horarioChegada;
    }

}
