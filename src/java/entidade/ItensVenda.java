/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import dao.MercadoriaDAO;

/**
 *
 * @author Usuario
 */
public class ItensVenda {

    private int coditen;
    private int codvenda;
    private Mercadoria mercadoria;
    private double quantidade;
    private double valorUnitario;

    /**
     * @return the coditen
     */
    public int getCoditen() {
        return coditen;
    }

    /**
     * @param coditen the coditen to set
     */
    public void setCoditen(int coditen) {
        this.coditen = coditen;
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
     * @return the mercadoria
     */
    public Mercadoria getMercadoria() {
        return mercadoria;
    }

    /**
     * @param mercadoria the mercadoria to set
     */
    public void setMercadoria(Mercadoria mercadoria) {
        this.mercadoria = mercadoria;
    }

    public void setMercadoriaId(int idMercadoria) {
        MercadoriaDAO mercadoriaDAO = new MercadoriaDAO();
        this.mercadoria = (Mercadoria) mercadoriaDAO.consultarId(idMercadoria);
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
     * @return the valorUnitario
     */
    public double getValorUnitario() {
        return valorUnitario;
    }

    /**
     * @param valorUnitario the valorUnitario to set
     */
    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

}
