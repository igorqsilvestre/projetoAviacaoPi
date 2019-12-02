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
public class Cliente {

    private String nome;
    private String cpf;
    private String dataDeNascimento;
    private String telefone;
    private String cidade;
    private String estado;
    private String endereco;
    private String cep;

    public Cliente() {
        this.nome = "";
        this.cpf = "";
        this.dataDeNascimento = "";
        this.telefone = "";
        this.cidade = "";
        this.estado = "";
        this.endereco = "";
        this.cep = "";

    }

    public Cliente(String nome, String cpf, String dataDeNascimento, String telefone, String cidade, String estado, String endereco, String cep) throws Exception {
        setNome(nome);
        setCpf(cpf);
        setDataDeNascimento(dataDeNascimento);
        setTelefone(telefone);
        setCidade(cidade);
        setEstado(estado);
        setEndereco(endereco);
        setCep(cep);
    }

    public Cliente(String dados) throws Exception {
        try {
            String vetorDados[] = dados.split(";");
            this.nome = vetorDados[0];
            this.cpf = vetorDados[1];
            this.dataDeNascimento = vetorDados[2];
            this.telefone = vetorDados[3];
            this.cidade = vetorDados[4];
            this.estado = vetorDados[5];
            this.endereco = vetorDados[6];
            this.cep = vetorDados[7];

        } catch (Exception erro) {
            throw new Exception("Erro na montagem do objeto");
        }
    }

    @Override
    public String toString() {
        return "" + getNome() + ";" + getCpf() + ";" + getDataDeNascimento() + ";" + getTelefone() + ";" + getCidade() + ";" + getEstado() + ";" + getEndereco() + ";" + getCep();
    }
    
    

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     * @throws java.lang.Exception
     */
    public void setNome(String nome) throws Exception {
        if (nome.equals("") || nome.isEmpty()) {
            throw new Exception("Nome não pode estar vazio");
        } else {
            this.nome = nome;
        }

    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     * @throws java.lang.Exception
     */
    public void setCpf(String cpf) throws Exception {
        if (cpf.equals("") || cpf.isEmpty()||cpf.length()!=11) {
            throw new Exception("Cpf inválido!");
        } else {
            this.cpf = cpf;
        }

    }

    /**
     * @return the dataDeNascimento
     */
    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    /**
     * @param dataDeNascimento the dataDeNascimento to set
     * @throws java.lang.Exception
     */
    public void setDataDeNascimento(String dataDeNascimento) throws Exception {
        if (dataDeNascimento.equals("  /  /    ")|| dataDeNascimento.equals("")|| dataDeNascimento.isEmpty()) {
            throw new Exception("A data de nascimento não pode estar vazia");
        } else {
            this.dataDeNascimento = dataDeNascimento;
        }

    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     * @throws java.lang.Exception
     */
    public void setTelefone(String telefone) throws Exception {
         if (telefone.equals("")||telefone.isEmpty()) {
            throw new Exception("telefone inválido!");
        } else {
            this.telefone = telefone;
        }

    }

    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     * @throws java.lang.Exception
     */
    public void setCidade(String cidade) throws Exception {
        if (cidade.equals("") || cidade.isEmpty()) {
            throw new Exception("A cidade não pode estar vazia");
        } else {
            this.cidade = cidade;
        }

    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     * @throws java.lang.Exception
     */
    public void setEstado(String estado) throws Exception {
        if (estado.equals("") && estado.isEmpty()) {
            throw new Exception("O estado não pode estar vazio");
        } else {
            this.estado = estado;
        }

    }

    /**
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     * @throws java.lang.Exception
     */
    public void setEndereco(String endereco) throws Exception {
        if (endereco.equals("") || endereco.isEmpty()) {
            throw new Exception("O endereço não pode estar vazio");
        } else {
            this.endereco = endereco;
        }

    }

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     * @throws java.lang.Exception
     */
    public void setCep(String cep) throws Exception {
         if (cep.equals("")||cep.isEmpty()|| cep.length()!=8) {
            throw new Exception("cep inválido!");
        } else {
            this.cep = cep;
        }

    }

}
