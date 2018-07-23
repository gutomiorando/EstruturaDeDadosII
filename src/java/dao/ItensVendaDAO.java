/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import apoio.IDAO;
import entidade.ItensVenda;
import entidade.Mercadoria;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class ItensVendaDAO implements IDAO {

    @Override
    public String salvar(Object o) {
        ItensVenda itens = (ItensVenda) o;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "INSERT INTO itensvenda VALUES ("
                    + "DEFAULT,"
                    + "'" + itens.getCodvenda() + "',"
                    + "'" + itens.getMercadoria().getCodmercadoria() + "',"
                    + "" + itens.getQuantidade() + ","
                    + "" + itens.getValorUnitario() + ""
                    + ")";

            System.out.println("SQL: " + sql);

            int resultado = st.executeUpdate(sql);

            if (resultado != 0) {
                return null;
            }
            return "xxx";

        } catch (Exception e) {
            System.out.println("Erro salvar Itens de venda = " + e);
            return e.toString();
        }

    }

    @Override
    public String atualizar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String excluir(int id) {
        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "delete from itensvenda "
                    + "where itensvenda = " + id;

            System.out.println("Chamou o excluir Registro.");
            int resultado = st.executeUpdate(sql);
            System.out.println("SQL = " + sql);
        } catch (Exception e) {
            System.out.println("Erro ao excluir Iten: " + e);
            return e.toString();
        }
        return null;
    }

    @Override
    public ArrayList<Object> consultarTodos() {
      ArrayList<Object> itens = new ArrayList<>();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * from itensvenda";

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                ItensVenda cid = new ItensVenda();
                Mercadoria merc = new Mercadoria();
                cid.setCoditen(resultado.getInt("coditen"));
                cid.setCodvenda(resultado.getInt("codvenda"));
                merc.setCodmercadoria(resultado.getInt("codmercadoria"));
                cid.setQuantidade(resultado.getDouble("quantidade"));
                cid.setValorUnitario(resultado.getDouble("valorunitario"));

                itens.add(cid);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar itens de venda: " + e);
        }

        return itens;
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

            String sql = "select * from itensvenda "
                    + "where coditen = " + id;

            ResultSet resultado = st.executeQuery(sql);

            if (resultado.next()) {
                ItensVenda cid = new ItensVenda();
                Mercadoria merc = new Mercadoria();
                cid.setCoditen(resultado.getInt("coditen"));
                cid.setCodvenda(resultado.getInt("codvenda"));
                merc.setCodmercadoria(resultado.getInt("codmercadoria"));
                cid.setQuantidade(resultado.getDouble("quantidade"));
                cid.setValorUnitario(resultado.getDouble("valorunitario"));

                return cid;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Deu erro ao consultar itens: " + e);
            return e.toString();
        }
    }

    @Override
    public boolean consultar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     public String excluiriten(int codvenda, int codmerc) {
        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "delete from itensvenda "
                    //+ "where itensvenda = " + id
                    + "where codvenda = " + codvenda
                    + "and codmercadoria = " + codmerc;

            System.out.println("Chamou o excluir Registro.");
            int resultado = st.executeUpdate(sql);
            System.out.println(" SQL DO EXCLUIR = " + sql);

        } catch (Exception e) {
            System.out.println("Erro ao excluir Iten: " + e);
            return e.toString();
        }
        return null;
    }
    
     
    public ArrayList<Object> consultarVendaProdutos(int id)
    {
        ArrayList<Object> listaVendaProdutos = new ArrayList<>();
        try
        {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * from itensvenda "
                    + "where codvenda = " + id;

            ResultSet resultado = st.executeQuery(sql);

            while (resultado.next())
            {
                ItensVenda vp = new ItensVenda();
                Mercadoria merc = new Mercadoria();
                
                vp.setCoditen(resultado.getInt("coditen"));
                vp.setCodvenda(resultado.getInt("codvenda"));
                vp.setMercadoriaId(resultado.getInt("codmercadoria"));
                vp.setQuantidade(resultado.getInt("quantidade"));
                vp.setValorUnitario(resultado.getDouble("valorunitario"));

                listaVendaProdutos.add(vp);
            }

        }
        catch (SQLException e)
        {
            System.out.println("Erro ao consultar itens venda pelo ID da venda: " + e);
        }

        return listaVendaProdutos;
    }
     
}
