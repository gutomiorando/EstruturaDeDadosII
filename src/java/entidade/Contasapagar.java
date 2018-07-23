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
public class Contasapagar {

    private int idconta;
    private int codfornecedor;
    private String descricao;
    private String datapagamento;
    private String dataprevista;
    private double valortotal;
    private char situacao;

    public Contasapagar() {
        idconta = 0;
        codfornecedor = 0;
        descricao = "";
        datapagamento = "";
        dataprevista = "";
        valortotal = 0;
        situacao = 'A';
    }

    /**
     * @return the idconta
     */
    public int getIdconta() {
        return idconta;
    }

    /**
     * @param idconta the idconta to set
     */
    public void setIdconta(int idconta) {
        this.idconta = idconta;
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
     * @return the valortotal
     */
    public double getValortotal() {
        return valortotal;
    }

    /**
     * @param valortotal the valortotal to set
     */
    public void setValortotal(double valortotal) {
        this.valortotal = valortotal;
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
     * @return the dataprevista
     */
    public String getDataprevista() {
        return dataprevista;
    }

    /**
     * @param dataprevista the dataprevista to set
     */
    public void setDataprevista(String dataprevista) {
        this.dataprevista = dataprevista;
    }

}
