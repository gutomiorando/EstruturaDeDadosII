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
public class Funcionario {

    /**
     * @return the codfunc
     */
    private int codfunc;
    private int idempresa;
    private String nome;
    private String dataadmissao;
    private String datademissao;
    private String funcao;
    private double salario;
    private char situacao;

    public Funcionario() {
        codfunc = 0;
        idempresa = 1;
        nome = "";
        dataadmissao = "";
        datademissao = "";
        funcao = "";
        salario = 0;
        situacao = 'A';
    }

    public int getCodfunc() {
        return codfunc;
    }

    /**
     * @param codfunc the codfunc to set
     */
    public void setCodfunc(int codfunc) {
        this.codfunc = codfunc;
    }

    /**
     * @return the idempresa
     */
    public int getIdempresa() {
        return idempresa;
    }

    /**
     * @param idempresa the idempresa to set
     */
    public void setIdempresa(int idempresa) {
        this.idempresa = idempresa;
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
     * @return the dataadmissao
     */
    public String getDataadmissao() {
        return dataadmissao;
    }

    /**
     * @param dataadmissao the dataadmissao to set
     */
    public void setDataadmissao(String dataadmissao) {
        this.dataadmissao = dataadmissao;
    }

    /**
     * @return the datademissao
     */
    public String getDatademissao() {
        return datademissao;
    }

    /**
     * @param datademissao the datademissao to set
     */
    public void setDatademissao(String datademissao) {
        this.datademissao = datademissao;
    }

    /**
     * @return the funcao
     */
    public String getFuncao() {
        return funcao;
    }

    /**
     * @param funcao the funcao to set
     */
    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    /**
     * @return the salario
     */
    public double getSalario() {
        return salario;
    }

    /**
     * @param salario the salario to set
     */
    public void setSalario(double salario) {
        this.salario = salario;
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
