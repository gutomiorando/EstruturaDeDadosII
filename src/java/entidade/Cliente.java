/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

/**
 *
 * @author Usuario
 */
public class Cliente {

    private int codcli;
    //private int codcid;
    private Cidade Cidade;
    private String nome;
    private char tipocadastro;
    private String cpfCnpj;
    private String rua;
    private int numero;
    private String datacadastro;
    private char situacao;

    public Cliente(Cidade Cidade) {
        this.Cidade = Cidade;
    }

    public Cliente() {
        codcli = 0;
        this.Cidade = Cidade;
        nome = "";
        tipocadastro = 'F';
        cpfCnpj = "";
        rua = "";
        numero = 0;
        datacadastro = "";
        situacao = 'A';
    }

    /**
     * @return the codcli
     */
    public int getCodcli() {
        return codcli;
    }

    /**
     * @param codcli the codcli to set
     */
    public void setCodcli(int codcli) {
        this.codcli = codcli;
    }

    /* public int getCodcid() {
        return codcid;
    }

    public void setCodcid(int codcid) {
        this.codcid = codcid;
    }
     */
    public Cidade getCidade() {
        return Cidade;
    }

    public void setCidade(Cidade Cidade) {
        this.Cidade = Cidade;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the cpfCnpj
     */
    public String getCpfCnpj() {
        return cpfCnpj;
    }

    /**
     * @param cpfCnpj the cpfCnpj to set
     */
    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    /**
     * @return the rua
     */
    public String getRua() {
        return rua;
    }

    /**
     * @param rua the rua to set
     */
    public void setRua(String rua) {
        this.rua = rua;
    }

    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * @return the datacadastro
     */
    public String getDatacadastro() {
        return datacadastro;
    }

    /**
     * @param datacadastro the datacadastro to set
     */
    public void setDatacadastro(String datacadastro) {
        this.datacadastro = datacadastro;
    }

    /**
     * @return the situacao
     */
    public char getSituacao() {
        return situacao;
    }

    /**
     * @param situacao the situacao to set
     */
    public void setSituacao(char situacao) {
        this.situacao = situacao;
    }

    /**
     * @return the tipocadastro
     */
    public char getTipocadastro() {
        return tipocadastro;
    }

    /**
     * @param tipocadastro the tipocadastro to set
     */
    public void setTipocadastro(char tipocadastro) {
        this.tipocadastro = tipocadastro;
    }
}
