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
public class Mercadoria {

    private int codmercadoria;
    private String descricao;
    private double valorunitario;
    private double quantidade;
    private double estoque;
    private String horaatualizacao;
    private char tipo;
    private char situacao;

    public Mercadoria() {
        codmercadoria = 0;
        descricao = "";
        valorunitario = 0;
        quantidade = 0;
        estoque = 0;
        horaatualizacao = "";
        tipo = 'C';
        situacao = 'A';
    }

    /**
     * @return the codmercadoria
     */
    public int getCodmercadoria() {
        return codmercadoria;
    }

    /**
     * @param codmercadoria the codmercadoria to set
     */
    public void setCodmercadoria(int codmercadoria) {
        this.codmercadoria = codmercadoria;
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
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the valorunitario
     */
    public double getValorunitario() {
        return valorunitario;
    }

    /**
     * @param valorunitario the valorunitario to set
     */
    public void setValorunitario(double valorunitario) {
        this.valorunitario = valorunitario;
    }

    /**
     * @return the quantidade
     */
    public double getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the estoque
     */
    public double getEstoque() {
        return estoque;
    }

    /**
     * @param estoque the estoque to set
     */
    public void setEstoque(double estoque) {
        this.estoque = estoque;
    }

    /**
     * @return the horaatualizacao
     */
    public String getHoraatualizacao() {
        return horaatualizacao;
    }

    /**
     * @param horaatualizacao the horaatualizacao to set
     */
    public void setHoraatualizacao(String horaatualizacao) {
        this.horaatualizacao = horaatualizacao;
    }

    /**
     * @return the tipo
     */
    public char getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(char tipo) {
        this.tipo = tipo;
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
