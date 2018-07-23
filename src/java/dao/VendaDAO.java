/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import apoio.Formatacao;
import apoio.IDAO;
import controle.ControlaVendaProdutos;
import entidade.Cliente;
import entidade.Empresa;
import entidade.ItensVenda;
import entidade.Usuario;
import entidade.Venda;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author Usuario
 */
public class VendaDAO implements IDAO {

    @Override
    public String salvar(Object o) {
        Venda ven = (Venda) o;
        ItensVenda itens = new ItensVenda();
        String datad = ven.getDataemissao();

        if (Formatacao.removerFormatacao(datad.trim()).length() <= 4) {
            datad = (Formatacao.getDataAtual());
        } else {
            datad = "'" + datad + "'";
        }

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "INSERT INTO venda VALUES ("
                    + "DEFAULT,"
                    + "" + ven.getCliente().getCodcli() + ","
                    + "" + ven.getUsuario().getCodusuario() + ","
                    + "" + ven.getEmpresa().getIdempresa() + ","
                    + "'" + ven.getMotorista() + "',"
                    + "'" + ven.getPlaca() + "',"
                    + "" + ven.getQuantidade() + ","
                    + "" + ven.getLitros() + ","
                    + "" + ven.getValortotal() + ","
                    + "'" + ven.getForma_pagamento()+ "',"
                    + "" + datad + ","
                    + "'" + ven.getSituacaovenda() + "'"
                    + ")";

            System.out.println("SQL: " + sql);

            int resultado = st.executeUpdate(sql);

            sql = "select max(codvenda) from venda ";

            ResultSet resulSelect = st.executeQuery(sql);
            resulSelect.next();
            //System.out.println("Posição Salva = " + resulSelect.getString("max"));
            ControlaVendaProdutos.codVenda = (Integer.parseInt(resulSelect.getString("max")));
//            System.out.println("Código da venda = " + codvenda);
//            itens.setCodvenda(codvenda);

            if (resultado != 0) {
                return null;
            }
            return "xxx";

        } catch (Exception e) {
            System.out.println("Erro salvar Venda no banco = " + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(Object o) {
        Venda ven = (Venda) o;

        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "update venda "
                    //+ "set codcli = " + ven.getCliente() + ","
                    //+ "motorista = '" + ven.getMotorista() + "',"
                    //+ "placa = '" + ven.getPlaca() + "',"
                    + "set quantidade = " + ven.getQuantidade() + ","
                    + "litros = " + ven.getLitros() + ","
                    + "valortotal = " + ven.getValortotal() + ","
                    //+ "formapagamento = '" + ven.getFormapagamento() + "',"
                    + "situacao = '" + ven.getSituacaovenda() + "'"
                    + "where codvenda = " + ven.getCodvenda();

            int resultado = st.executeUpdate(sql);
            System.out.println("SQL do atuzlizar venda = " + sql);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar a venda: " + e);
            return e.toString();
        }

        return null;
    }

    @Override
    public String excluir(int id) {
        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "update venda "
                    + "set situacao = 'I' "
                    + "where codvenda = " + id;

            int resultado = st.executeUpdate(sql);
            System.out.println("SQL = " + sql);
        } catch (Exception e) {
            System.out.println("Erro ao excluir a venda: " + e);
            return e.toString();
        }
        return null;
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        ArrayList<Object> vendas = new ArrayList<>();

        try {
//            Statement st = bilhetario.Bilhetario.conexao.createStatement();
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            //String sql = "select * from  venda  order by dataemissao";
            
            String sql = "SELECT * "
                    + "FROM venda v "
                    + "INNER JOIN cliente c "
                    + "ON c.codcli = v.codcli "
                    + "ORDER BY dataemissao";
            

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Venda v = new Venda();
                Cliente c = new Cliente();
                v.setCodvenda(resultado.getInt("codvenda"));
                v.setCodcli(resultado.getInt("codcli"));
                v.setMotorista(resultado.getString("motorista"));
                v.setPlaca(resultado.getString("placa"));
                v.setQuantidade(resultado.getDouble("quantidade"));
                v.setLitros(resultado.getDouble("litros"));
                v.setValortotal(resultado.getDouble("valortotal"));
                v.setForma_pagamento(resultado.getString("formapagamento").charAt(0));
                v.setDataemissao(resultado.getString("dataemissao"));
                v.setSituacaovenda(resultado.getString("situacao").charAt(0));

                vendas.add(v);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar Vendas: " + e);
        }

        return vendas;
    }

    @Override
    public boolean verificarRegistroUnico(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object consultarId(int id) {
        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            //String sql = "select * from venda "
            //        + "where codvenda = " + id;
            
            String sql = "SELECT * "
                    + "FROM venda v "
                    + "INNER JOIN cliente c "
                    + "ON c.codcli = v.codcli "
                    + "WHERE v.codvenda = " + id;
            

            ResultSet resultado = st.executeQuery(sql);

            if (resultado.next()) {
                Venda v = new Venda();
                Cliente c = new Cliente();
                v.setCodvenda(resultado.getInt("codvenda"));
                v.setCodcli(resultado.getInt("codcli"));
                v.setMotorista(resultado.getString("motorista"));
                v.setPlaca(resultado.getString("placa"));
                v.setQuantidade(resultado.getDouble("quantidade"));
                v.setLitros(resultado.getDouble("litros"));
                v.setValortotal(resultado.getDouble("valortotal"));
                v.setForma_pagamento(resultado.getString("formapagamento").charAt(0));
                v.setDataemissao(resultado.getString("dataemissao"));
                v.setSituacaovenda(resultado.getString("situacao").charAt(0));;

                return v;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Deu erro ao consultar venda por ID: " + e);
            return e.toString();
        }
    }

    @Override
    public boolean consultar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public byte[] gerarListaVendasPeriodo(Timestamp inicio, Timestamp fim) {
        
        try {
            Connection conn = new ConexaoBD().getInstance().getConnection();
            
            JasperReport relatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream("/relatorios/vendasporperiodo.jrxml"));
            
            Map parameters = new HashMap();
            parameters.put("inicial_fp", inicio);
            parameters.put("final_fp", fim);
            
            byte[] bytes = JasperRunManager.runReportToPdf(relatorio, parameters, conn);
            
            return bytes;
        } catch (Exception e) {
            System.out.println("erro ao gerar relatorio: " + e);
        }
        return null;
    }
    
    
  public ArrayList<Object> consultarComFiltro(String criterio)
    {
        ArrayList<Object> venda = new ArrayList<>();

        try
        {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * "
                    + "FROM venda v "
                    + "INNER JOIN cliente c "
                    + "ON c.codcli = v.codcli "
                    + "WHERE c.nome ILIKE '%" + criterio + "%' "
                    + "ORDER BY v.codvenda";

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next())
            {
                Venda v = new Venda();
                Cliente cli = new Cliente();
                Usuario usu = new Usuario();
                Empresa emp = new Empresa();
                v.setCodvenda(resultado.getInt("codvenda"));
                v.setCodcli(resultado.getInt("codcli"));
//                usu.setCodusuario(resultado.getInt("codusuario"));
//                emp.setIdempresa(resultado.getInt("idempresa"));
                v.setMotorista(resultado.getString("motorista"));
                v.setPlaca(resultado.getString("placa"));
                v.setQuantidade(resultado.getDouble("quantidade"));
                v.setLitros(resultado.getDouble("litros"));
                v.setValortotal(resultado.getDouble("valortotal"));
                v.setForma_pagamento(resultado.getString("formapagamento").charAt(0));
                v.setDataemissao(resultado.getString("dataemissao"));
                v.setSituacaovenda(resultado.getString("situacao").charAt(0));;

                venda.add(v);
            }

        }
        catch (SQLException e)
        {
            System.out.println("Erro ao consultar Vendas com filtro: " + e);
        }

        return venda;
    }  
    
    
}
