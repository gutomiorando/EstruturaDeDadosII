/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import apoio.Formatacao;
import apoio.IDAO;
import entidade.Contasapagar;
import entidade.Contasareceber;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class ContasareceberDAO implements IDAO {

    @Override
    public String salvar(Object o) {
        
     Contasareceber cont = (Contasareceber) o;

        String datad = cont.getDatapagamento();

        if (Formatacao.removerFormatacao(datad.trim()).length() <= 2) {
            datad = null;
        } else {
            datad = "'" + datad + "'";
        }

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "INSERT INTO contasareceber VALUES ("
                    + "DEFAULT,"
                    + "'" + cont.getCodvenda() + "',"
                    + "'" + cont.getDatalancamento() + "',"
                    + "'" + cont.getDatavencimento() + "',"
                    + "" + datad + ","
                    + "" + cont.getValorprevisto() + ","
                    + "" + cont.getValorrecebido() + ","
                    + "'" + cont.getSituacao() + "'"
                    + ")";

            int resultado = st.executeUpdate(sql);

            //  System.out.println("SQL = " + sql);
            if (resultado != 0) {
                return null;
            }
            return "xxx";

        } catch (Exception e) {
            System.out.println("Erro ao salvar Contas a receber = " + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(Object o) {
       Contasareceber conta = (Contasareceber) o;
        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "update contasareceber "
                    // + "set codvenda = " + conta.getCodvenda() + ","
                    // + "datalancamento = '" + conta.getDatalancamento() + "',"
                    // + "datavencimento = '" + conta.getDatavencimento() + "',"
                    + " set datapagamento = '" + conta.getDatapagamento() + "',"
                    //+ "valorprevisto = " + conta.getValorprevisto() + ","
                    + "valorrecebido = " + conta.getValorrecebido() + ","
                    + "situacao = '" + conta.getSituacao() + "'"
                    + "where id = " + conta.getId();
            System.out.println("SQL =  " + sql);
            int resultado = st.executeUpdate(sql);
            // System.out.println("SQL =  " + sql);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar conta: " + e);
            return e.toString();
        }

        return null;
    }

    @Override
    public String excluir(int id) {
         try {

            ContasareceberDAO receberDAO = new ContasareceberDAO();
            //receberDAO.consultarId(id);
            Contasareceber cont = (Contasareceber) receberDAO.consultarId(id);
             
            String datad = (Formatacao.getDataAtual());
             
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "update contasareceber "
                    + "set datapagamento = '" + datad + "',"
                    + "valorrecebido = '" + cont.getValorprevisto() + "',"
                    + "situacao = 'Q' "
                    + "where id = " + id;

            int resultado = st.executeUpdate(sql);
            System.out.println("Conta Quitada!");
        } catch (Exception e) {
            System.out.println("Erro ao quitar Conta: " + e);
            return e.toString();
        }
        return null;
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        ArrayList<Object> contas = new ArrayList<>();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * from contasareceber";

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Contasareceber cont = new Contasareceber();
                cont.setId(resultado.getInt("id"));
                cont.setCodvenda(resultado.getInt("codvenda"));
                cont.setDatalancamento(Formatacao.ajustaDataDMA(resultado.getString("datalancamento")));
                cont.setDatavencimento(Formatacao.ajustaDataDMA(resultado.getString("datavencimento")));
                cont.setDatapagamento(Formatacao.ajustaDataDMA(resultado.getString("datapagamento")));
                cont.setValorprevisto(resultado.getDouble("valorprevisto"));
                cont.setValorrecebido(resultado.getDouble("valorrecebido"));
                cont.setSituacao(resultado.getString("situacao").charAt(0));

                contas.add(cont);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar Contas a receber: " + e);
        }

        return contas;
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

            String sql = "select * from contasareceber "
                    + "where id = " + id;

            ResultSet resultado = st.executeQuery(sql);

            if (resultado.next()) {
                Contasareceber cont = new Contasareceber();
                cont.setId(resultado.getInt("id"));
                cont.setCodvenda(resultado.getInt("codvenda"));
                cont.setDatalancamento(Formatacao.ajustaDataDMA(resultado.getString("datalancamento")));
                cont.setDatavencimento(Formatacao.ajustaDataDMA(resultado.getString("datavencimento")));
                cont.setDatapagamento(Formatacao.ajustaDataDMA(resultado.getString("datapagamento")));
                cont.setValorprevisto(resultado.getDouble("valorprevisto"));
                cont.setValorrecebido(resultado.getDouble("valorrecebido"));
                cont.setSituacao(resultado.getString("situacao").charAt(0));

                return cont;

            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Deu erro ao consultar ID Contas a receber: " + e);
            return e.toString();
        }
    }

    @Override
    public boolean consultar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ArrayList<Object> consultarComFiltro(String criterio)
    {
        ArrayList<Object> contas = new ArrayList<>();

        try
        {
//            Statement st = bilhetario.Bilhetario.conexao.createStatement();
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM contasareceber WHERE codvenda ILIKE '%" + criterio + "%'"
                    + "order by id";

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next())
            {
                Contasareceber cont = new Contasareceber();
                
                cont.setId(resultado.getInt("id"));
                cont.setCodvenda(resultado.getInt("codvenda"));
                cont.setDatalancamento(Formatacao.ajustaDataDMA(resultado.getString("datalancamento")));
                cont.setDatavencimento(Formatacao.ajustaDataDMA(resultado.getString("datavencimento")));
                cont.setDatapagamento(Formatacao.ajustaDataDMA(resultado.getString("datapagamento")));
                cont.setValorprevisto(resultado.getDouble("valorprevisto"));
                cont.setValorrecebido(resultado.getDouble("valorrecebido"));
                cont.setSituacao(resultado.getString("situacao").charAt(0));

                return contas;
            }

        }
        catch (SQLException e)
        {
            System.out.println("Erro ao consultar Contas a receber: " + e);
        }

        return contas;
    }
    
    
}
