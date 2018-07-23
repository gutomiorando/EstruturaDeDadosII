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
public class Contasareceber {
    
    private int id;
    private int codvenda;
    private String datalancamento;
    private String datavencimento;
    private String datapagamento;
    private double valorprevisto;
    private double valorrecebido;
    private char situacao;

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
     * @return the codvenda
     */
    public int getCodvenda() {
        return codvenda;
    }

    /**
     * @param codvenda the codvenda to set
     */
    public void setCodvenda(int codvenda) {
        this.codvenda = codvenda;
    }

    /**
     * @return the datalancamento
     */
    public String getDatalancamento() {
        return datalancamento;
    }

    /**
     * @param datalancamento the datalancamento to set
     */
    public void setDatalancamento(String datalancamento) {
        this.datalancamento = datalancamento;
    }

    /**
     * @return the datavencimento
     */
    public String getDatavencimento() {
        return datavencimento;
    }

    /**
     * @param datavencimento the datavencimento to set
     */
    public void setDatavencimento(String datavencimento) {
        this.datavencimento = datavencimento;
    }

    /**
     * @return the datapagamento
     */
    public String getDatapagamento() {
        return datapagamento;
    }

    /**
     * @param datapagamento the datapagamento to set
     */
    public void setDatapagamento(String datapagamento) {
        this.datapagamento = datapagamento;
    }

    /**
     * @return the valorprevisto
     */
    public double getValorprevisto() {
        return valorprevisto;
    }

    /**
     * @param valorprevisto the valorprevisto to set
     */
    public void setValorprevisto(double valorprevisto) {
        this.valorprevisto = valorprevisto;
    }

    /**
     * @return the valorrecebido
     */
    public double getValorrecebido() {
        return valorrecebido;
    }

    /**
     * @param valorrecebido the valorrecebido to set
     */
    public void setValorrecebido(double valorrecebido) {
        this.valorrecebido = valorrecebido;
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
