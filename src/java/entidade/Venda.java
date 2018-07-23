/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import dao.ClienteDAO;
import dao.UsuarioDAO;

/**
 *
 * @author Usuario
 */
public class Venda {
    
    private int codvenda;
    private Cliente cliente;
    private Usuario usuario;
    private Empresa empresa;
    private String motorista;
    private String placa;
    private double quantidade;
    private double litros;
    private double valortotal;
    private char formapagamento;
    private String dataemissao;
    private char situacaovenda;

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
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public void setCodcli(int cliente) {
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente clienteObjeto = (Cliente) clienteDAO.consultarId(cliente);
        this.cliente = clienteObjeto;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the motorista
     */
    public String getMotorista() {
        return motorista;
    }

    /**
     * @param motorista the motorista to set
     */
    public void setMotorista(String motorista) {
        this.motorista = motorista;
    }

    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
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

    public char getForma_pagamento()
    {
        return formapagamento;
    }

    public String getForma_pagamento_String()
    {
        switch (this.formapagamento)
        {
            case 'D':
                return "Dinheiro";
            case 'C':
                return "Credito";
            case 'E':
                return "Debito";
            default:
                return "Dinheiro";
        }
    }

    public void setForma_pagamento(char forma_pagamento)
    {
        this.formapagamento = forma_pagamento;
    }

    public void setForma_pagamento(int forma_pagamento)
    {
        switch (forma_pagamento)
        {
            case 1:
                this.formapagamento = 'D';
                break;
            case 2:
                this.formapagamento = 'C';
                break;
            case 3:
                this.formapagamento = 'E';
                break;
            default:
                break;
        }
    }

    /**
     * @return the dataemissao
     */
    public String getDataemissao() {
        return dataemissao;
    }

    /**
     * @param dataemissao the dataemissao to set
     */
    public void setDataemissao(String dataemissao) {
        this.dataemissao = dataemissao;
    }

    /**
     * @return the situacaovenda
     */
    public char getSituacaovenda() {
        return situacaovenda;
    }

    /**
     * @param situacaovenda the situacaovenda to set
     */
    public void setSituacaovenda(char situacaovenda) {
        this.situacaovenda = situacaovenda;
    }

    /**
     * @return the empresa
     */
    public Empresa getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    /**
     * @return the litros
     */
    public double getLitros() {
        return litros;
    }

    /**
     * @param litros the litros to set
     */
    public void setLitros(double litros) {
        this.litros = litros;
    }
    
    public String getCliente_Nome(Cliente cliente1)
    {
        ClienteDAO clDAO = new ClienteDAO();
        Cliente cliente2 = (Cliente) clDAO.consultarId(Integer.parseInt(String.valueOf(cliente1)));
        return cliente2.getNome();
    }
    
}
