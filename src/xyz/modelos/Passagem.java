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
public class Passagem {

    private int id;
    private int idRotas;
    private String valorPassagem;
    private String formaDePagamento;
    private String cpfCliente;
    private int assento;
    
    public Passagem() {
        this.id = 0;
        this.idRotas = 0;
        this.valorPassagem = "";
        this.formaDePagamento = "";
        this.cpfCliente = "";
        this.assento = 0;
    }

    public Passagem(int id, int idRotas, String valorPassagem, String formaDePagamento, 
            String cpfCliente, int assento) throws Exception {
        if(valorPassagem.equals("") || valorPassagem.isEmpty()){
            throw new Exception("Clique no botão para calcular passagem!");
        }
        this.id = id;
        this.idRotas = idRotas;
        this.valorPassagem = valorPassagem;
        this.formaDePagamento = formaDePagamento;
        this.cpfCliente = cpfCliente;
        this.assento = assento;
    }

    public Passagem(String dados) throws Exception {
        try {
            String vetorDados[] = dados.split(";");
            this.id = Integer.parseInt(vetorDados[0]);
            this.cpfCliente = vetorDados[1];
            this.idRotas = Integer.parseInt(vetorDados[2]);
            this.valorPassagem = vetorDados[3];
            this.formaDePagamento = vetorDados[4];
            this.assento = Integer.parseInt(vetorDados[5]);

        } catch (Exception erro) {
            throw new Exception("Erro na montagem do objeto");
        }
    }

    @Override
    public String toString() {
        return "" + getId() + ";" + getCpfCliente() + ";" + getIdRotas() + ";" 
                + getValorPassagem() + ";" + getFormaDePagamento() + ";" + getAssento() + "";
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
     * @return the idRotas
     */
    public int getIdRotas() {
        return idRotas;
    }

    /**
     * @param idRotas the idRotas to set
     */
    public void setIdRotas(int idRotas) {
        this.idRotas = idRotas;
    }

    /**
     * @return the valorPassagem
     */
    public String getValorPassagem() {
        return valorPassagem;
    }

    /**
     * @param valorPassagem the valorPassagem to set
     */
    public void setValorPassagem(String valorPassagem) {
        this.valorPassagem = valorPassagem;
    }

    /**
     * @return the formaDePagamento
     */
    public String getFormaDePagamento() {
        return formaDePagamento;
    }

    /**
     * @param formaDePagamento the formaDePagamento to set
     */
    public void setFormaDePagamento(String formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    /**
     * @return the cpfCliente
     */
    public String getCpfCliente() {
        return cpfCliente;
    }

    /**
     * @param cpfCliente the cpfCliente to set
     */
    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    /**
     * @return the assento
     */
    public int getAssento() {
        return assento;
    }

    /**
     * @param assento the assento to set
     */
    public void setAssento(int assento) {
        this.assento = assento;
    }

}
