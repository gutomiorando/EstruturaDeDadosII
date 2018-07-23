/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import apoio.Formatacao;
import apoio.IDAO;
import entidade.Mercadoria;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author Usuario
 */
public class MercadoriaDAO implements IDAO {

    @Override
    public String salvar(Object o) {
        Mercadoria merc = (Mercadoria) o;

        double quant = 0;
        
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "INSERT INTO mercadoria VALUES ("
                    + "DEFAULT,"
                    + "'" + merc.getDescricao() + "',"
                    + "" + merc.getValorunitario() + ","
                    + "" + quant + ","
                    + "" + merc.getQuantidade() + ","
                    + "'" + merc.getHoraatualizacao() + "',"
                    + "'" + merc.getTipo() + "',"
                    + "'" + merc.getSituacao() + "'"
                    + ")";

            //System.out.println("SQL: " + sql);
            int resultado = st.executeUpdate(sql);

            if (resultado != 0) {
                return null;
            }
            return "xxx";

        } catch (Exception e) {
            System.out.println("Erro ao salvar Mercadoria = " + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(Object o) {
        Mercadoria merc = (Mercadoria) o;

        double novoestoque = merc.getQuantidade() + merc.getEstoque();
        
        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "update mercadoria "
                    + "set descricao = '" + merc.getDescricao() + "',"
                    + "valorunitario = " + merc.getValorunitario() + ","
                    + "quantidade = " + merc.getQuantidade() + ","
                    + "estoque = " + novoestoque + ","
                    + "horaatualizacao = '" + merc.getHoraatualizacao() + "',"
                    + "tipo = '" + merc.getTipo() + "',"
                    + "situacao = '" + merc.getSituacao() + "'"
                    + "where codmercadoria = " + merc.getCodmercadoria();

            int resultado = st.executeUpdate(sql);
            //System.out.println("SQL =  " + sql);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar mercadoria: " + e);
            return e.toString();
        }

        return null;
    }

    @Override
    public String excluir(int id) {
        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "update mercadoria "
                    + "set situacao = 'I' "
                    + "where codmercadoria = " + id;

            int resultado = st.executeUpdate(sql);
            //System.out.println(" Produto Excluido!");
        } catch (Exception e) {
            System.out.println("Erro ao excluir Mercadoria: " + e);
            return e.toString();
        }
        return null;
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        ArrayList<Object> mercadoria = new ArrayList<>();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * from mercadoria";

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Mercadoria merc = new Mercadoria();
                merc.setCodmercadoria(resultado.getInt("codmercadoria"));
                merc.setDescricao(resultado.getString("descricao"));
                merc.setValorunitario(resultado.getDouble("valorunitario"));
                merc.setQuantidade(resultado.getDouble("quantidade"));
                merc.setEstoque(resultado.getDouble("estoque"));
                merc.setHoraatualizacao(Formatacao.ajustaDataDMA(resultado.getString("horaatualizacao")));
                merc.setTipo(resultado.getString("tipo").charAt(0));
                merc.setSituacao(resultado.getString("situacao").charAt(0));

                mercadoria.add(merc);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar todas mercadorias: " + e);
        }

        return mercadoria;
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

            String sql = "select * from mercadoria "
                    + "where codmercadoria = " + id;

            ResultSet resultado = st.executeQuery(sql);

            if (resultado.next()) {
                Mercadoria merc = new Mercadoria();
                merc.setCodmercadoria(resultado.getInt("codmercadoria"));
                merc.setDescricao(resultado.getString("descricao"));
                merc.setValorunitario(resultado.getDouble("valorunitario"));
                merc.setQuantidade(resultado.getDouble("quantidade"));
                merc.setEstoque(resultado.getDouble("estoque"));
                merc.setHoraatualizacao(Formatacao.ajustaDataDMA(resultado.getString("horaatualizacao")));
                merc.setTipo(resultado.getString("tipo").charAt(0));
                merc.setSituacao(resultado.getString("situacao").charAt(0));

                return merc;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Deu erro ao consultar ID mercadoria: " + e);
            return e.toString();
        }
    }

    @Override
    public boolean consultar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public byte[] gerarListaMercadorias() {

        try {
            Connection conn = new ConexaoBD().getInstance().getConnection();

            JasperReport relatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream("/relatorios/lista_mercadorias2.jrxml"));

            Map parameters = new HashMap();

            byte[] bytes = JasperRunManager.runReportToPdf(relatorio, parameters, conn);

            return bytes;
        } catch (Exception e) {
            System.out.println("erro ao gerar relatorio: " + e);
        }
        return null;
    }

    public ArrayList<Object> consultarComFiltro(String criterio) {
        ArrayList<Object> mercadoria = new ArrayList<>();

        try {
//            Statement st = bilhetario.Bilhetario.conexao.createStatement();
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM mercadoria WHERE descricao ILIKE '%" + criterio + "%'"
                    + "order by nome";

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Mercadoria user = new Mercadoria();
                user.setCodmercadoria(resultado.getInt("codmercadoria"));
                user.setDescricao(resultado.getString("descricao"));
                user.setValorunitario(resultado.getDouble("valorunitario"));
                user.setEstoque(resultado.getInt("estoque"));
                user.setTipo(resultado.getString("tipo").charAt(0));
                user.setSituacao(resultado.getString("situacao").charAt(0));

                mercadoria.add(user);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao consultar Mercadorias: " + e);
        }

        return mercadoria;
    }

    public ArrayList<Object> consultarTodosAtivos() {
        ArrayList<Object> mercadoria = new ArrayList<>();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * from mercadoria"
                       + "AND situacao = 'A'";

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Mercadoria merc = new Mercadoria();
                merc.setCodmercadoria(resultado.getInt("codmercadoria"));
                merc.setDescricao(resultado.getString("descricao"));
                merc.setValorunitario(resultado.getDouble("valorunitario"));
                merc.setQuantidade(resultado.getDouble("quantidade"));
                merc.setEstoque(resultado.getDouble("estoque"));
                merc.setHoraatualizacao(Formatacao.ajustaDataDMA(resultado.getString("horaatualizacao")));
                merc.setTipo(resultado.getString("tipo").charAt(0));
                merc.setSituacao(resultado.getString("situacao").charAt(0));

                mercadoria.add(merc);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar todas mercadorias: " + e);
        }

        return mercadoria;
    }

}
