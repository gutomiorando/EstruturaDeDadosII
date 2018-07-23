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
public class Fornecedor {

    private int codfornecedor;
    private String nome;
    private String datacadastro;
    private char situacao;

    public Fornecedor() {
        codfornecedor = 0;
        nome = "";
        datacadastro = "";
        situacao = 'A';
    }

    /**
     * @return the codfornecedor
     */
    public int getCodfornecedor() {
        return codfornecedor;
    }

    /**
     * @param codfornecedor the codfornecedor to set
     */
    public void setCodfornecedor(int codfornecedor) {
        this.codfornecedor = codfornecedor;
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
}
