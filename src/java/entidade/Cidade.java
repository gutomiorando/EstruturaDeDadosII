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
public class Cidade {

    private int codcid;
    private String nome;
    private char situacao;

    public Cidade() {
        codcid = 0;
        nome = "";
        situacao = 'A';
    }

    /**
     * @return the codcid
     */
    public int getCodcid() {
        return codcid;
    }

    /**
     * @param codcid the codcid to set
     */
    public void setCodcid(int codcid) {
        this.codcid = codcid;
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
