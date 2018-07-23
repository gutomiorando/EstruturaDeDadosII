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
import entidade.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class ContasapagarDAO implements IDAO {

    @Override
    public String salvar(Object o) {
        Contasapagar cont = (Contasapagar) o;

        String datad = cont.getDatapagamento();

        if (Formatacao.removerFormatacao(datad.trim()).length() <= 2) {
            datad = null;
        } else {
            datad = "'" + datad + "'";
        }

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "INSERT INTO contasapagar VALUES ("
                    + "DEFAULT,"
                    + "'" + cont.getCodfornecedor() + "',"
                    + "'" + cont.getDescricao() + "',"
                    + "'" + cont.getDataprevista() + "',"
                    + "" + datad + ","
                    + "" + cont.getValortotal() + ","
                    + "'" + cont.getSituacao() + "'"
                    + ")";

            int resultado = st.executeUpdate(sql);
            //System.out.println("sql = " + sql);

            /* sql = "select max(idconta) from contasapagar ";

            ResultSet resulSelect = st.executeQuery(sql);
            resulSelect.next();
            //System.out.println("Posição Salva = " + resulSelect.getString("max"));
            codconta = (Integer.parseInt(resulSelect.getString("max")));
            //System.out.println("Código da venda = " + codvenda);
            cont.setIdconta(codconta);*/
            if (resultado != 0) {
                return null;
            }
            return "xxx";

        } catch (Exception e) {
            System.out.println("Erro ao salvar Contas a Pagar = " + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(Object o) {
        Contasapagar conta = (Contasapagar) o;
        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "update contasapagar "
                    + "set codfornecedor = " + conta.getCodfornecedor() + ","
                    + "descricao = '" + conta.getDescricao() + "',"
                    //+ "dataprevista = '" + conta.getDataprevista() + "',"
                    + "datapagamento = '" + conta.getDatapagamento() + "',"
                    + "valortotal = " + conta.getValortotal() + ","
                    + "situacao = '" + conta.getSituacao() + "'"
                    + "where idconta = " + conta.getIdconta();

            int resultado = st.executeUpdate(sql);
            System.out.println("SQL =  " + sql);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar conta: " + e);
            return e.toString();
        }

        return null;
    }

    @Override
    public String excluir(int id) {
        try {

            String datad = (Formatacao.getDataAtual());
            
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "update contasapagar "
                    + "set datapagamento = '" + datad + "',"
                    + "situacao = 'P' "
                    + "where idconta = " + id;

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

            String sql = "select * from contasapagar";

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Contasapagar cont = new Contasapagar();
                cont.setIdconta(resultado.getInt("idconta"));
                cont.setCodfornecedor(resultado.getInt("codfornecedor"));
                cont.setDescricao(resultado.getString("descricao"));
                cont.setDataprevista(Formatacao.ajustaDataDMA(resultado.getString("dataprevista")));
                cont.setDatapagamento(Formatacao.ajustaDataDMA(resultado.getString("datapagamento")));
                cont.setValortotal(resultado.getDouble("valortotal"));
                cont.setSituacao(resultado.getString("situacao").charAt(0));

                contas.add(cont);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar Contas a pagar: " + e);
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

            String sql = "select * from contasapagar "
                    + "where idconta = " + id;

            ResultSet resultado = st.executeQuery(sql);

            if (resultado.next()) {
                Contasapagar cont = new Contasapagar();
                cont.setIdconta(resultado.getInt("idconta"));
                cont.setCodfornecedor(resultado.getInt("codfornecedor"));
                cont.setDescricao(resultado.getString("descricao"));
                cont.setDataprevista(Formatacao.ajustaDataDMA(resultado.getString("dataprevista")));
                cont.setDatapagamento(Formatacao.ajustaDataDMA(resultado.getString("datapagamento")));
                cont.setValortotal(resultado.getDouble("valortotal"));
                cont.setSituacao(resultado.getString("situacao").charAt(0));

                return cont;

            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Deu erro ao consultar ID Contas a pagar: " + e);
            return e.toString();
        }
    }

    @Override
    public boolean consultar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean consultarConta(String cod, String valor, String descr) {
        ArrayList<Object> contas = new ArrayList<>();
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * from contasapagar";

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Contasapagar cont = new Contasapagar();
                cont.setIdconta(resultado.getInt("idconta"));
                cont.setCodfornecedor(resultado.getInt("codfornecedor"));
                cont.setDescricao(resultado.getString("descricao"));
                cont.setDataprevista(Formatacao.ajustaDataDMA(resultado.getString("dataprevista")));
                cont.setDatapagamento(Formatacao.ajustaDataDMA(resultado.getString("datapagamento")));
                cont.setValortotal(resultado.getDouble("valortotal"));
                cont.setSituacao(resultado.getString("situacao").charAt(0));
                contas.add(cont);

                String dt = Formatacao.ajustaDataDMA(resultado.getString("datapagamento"));

                if (cod.equalsIgnoreCase(resultado.getString("codfornecedor"))) {
                    // if (data.equalsIgnoreCase(dt)) {
                    if (descr.equalsIgnoreCase(resultado.getString("descricao"))) {
                        if (valor.equalsIgnoreCase(resultado.getString("valortotal"))) {
                            // System.out.println(" tudo igual");
                            return false;
                        }
                        //  }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar Contas: " + e);
        }

        return true;
    }

    public ArrayList<Object> consultarComFiltro(String criterio)
    {
        ArrayList<Object> contas = new ArrayList<>();

        try
        {
//            Statement st = bilhetario.Bilhetario.conexao.createStatement();
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM contasapagar WHERE descricao ILIKE '%" + criterio + "%'"
                    + "order by idconta";

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next())
            {
                Contasapagar cont2 = new Contasapagar();
                cont2.setIdconta(resultado.getInt("idconta"));
                cont2.setCodfornecedor(resultado.getInt("codfornecedor"));
                cont2.setDescricao(resultado.getString("descricao"));
                cont2.setDataprevista(Formatacao.ajustaDataDMA(resultado.getString("dataprevista")));
                cont2.setDatapagamento(Formatacao.ajustaDataDMA(resultado.getString("datapagamento")));
                cont2.setValortotal(resultado.getDouble("valortotal"));
                cont2.setSituacao(resultado.getString("situacao").charAt(0));
                
                contas.add(cont2);
            }

        }
        catch (SQLException e)
        {
            System.out.println("Erro ao consultar Usuários: " + e);
        }

        return contas;
    }
    
}
