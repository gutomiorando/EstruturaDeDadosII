/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import apoio.Formatacao;
import apoio.IDAO;
import entidade.Fornecedor;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class FornecedorDAO implements IDAO {

    @Override
    public String salvar(Object o) {
        Fornecedor forn = (Fornecedor) o;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "INSERT INTO fornecedor VALUES ("
                    + "DEFAULT,"
                    + "'" + forn.getNome() + "',"
                    + "'" + forn.getDatacadastro() + "',"
                    + "'" + forn.getSituacao() + "'"
                    + ")";

            int resultado = st.executeUpdate(sql);

            if (resultado != 0) {
                return null;
            }
            return "xxx";

        } catch (Exception e) {
            System.out.println("Erro ao salvar Fornecedor = " + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(Object o) {
        Fornecedor forne = (Fornecedor) o;

        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "update fornecedor "
                    + "set nome = '" + forne.getNome() + "',"
                    + "datacadastro = '" + forne.getDatacadastro() + "',"
                    + "situacao = '" + forne.getSituacao() + "'"
                    + "where codfornecedor = " + forne.getCodfornecedor();

            int resultado = st.executeUpdate(sql);
            //System.out.println("SQL =  " + sql);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar Fornecedor: " + e);
            return e.toString();
        }

        return null;
    }

    @Override
    public String excluir(int id) {
        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "update fornecedor "
                    + "set situacao = 'I' "
                    + "where codfornecedor = " + id;

            int resultado = st.executeUpdate(sql);
            System.out.println("Fornecedor Excluido!");
        } catch (Exception e) {
            System.out.println("Erro ao excluir Fornecedor: " + e);
            return e.toString();
        }
        return null;
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        ArrayList<Object> fornecedor = new ArrayList<>();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * from fornecedor";

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Fornecedor forne = new Fornecedor();
                forne.setCodfornecedor(resultado.getInt("codfornecedor"));
                forne.setNome(resultado.getString("nome"));
                forne.setDatacadastro(Formatacao.ajustaDataDMA(resultado.getString("datacadastro")));
                forne.setSituacao(resultado.getString("situacao").charAt(0));

                fornecedor.add(forne);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar fornecedores: " + e);
        }

        return fornecedor;
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

            String sql = "select * from fornecedor "
                    + "where codfornecedor = " + id;

            ResultSet resultado = st.executeQuery(sql);

            if (resultado.next()) {
                Fornecedor forne = new Fornecedor();
                forne.setCodfornecedor(resultado.getInt("codfornecedor"));
                forne.setNome(resultado.getString("nome"));
                forne.setDatacadastro(Formatacao.ajustaDataDMA(resultado.getString("datacadastro")));
                forne.setSituacao(resultado.getString("situacao").charAt(0));

                return forne;

            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Deu erro ao consultar ID fornecedor: " + e);
            return e.toString();
        }
    }

    @Override
    public boolean consultar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    public boolean consultarfornecedor(String veio) {
        ArrayList<Object> fornecedor = new ArrayList<>();
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * from fornecedor";

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Fornecedor f = new Fornecedor();
                f.setCodfornecedor(resultado.getInt("codfornecedor"));
                f.setNome(resultado.getString("nome"));
                f.setDatacadastro(Formatacao.ajustaDataDMA(resultado.getString("datacadastro")));
                f.setSituacao(resultado.getString("situacao").charAt(0));
                fornecedor.add(f);

                if (veio.equalsIgnoreCase(resultado.getString("nome"))) {
                    System.out.println(" é igual");
                    return false;
                }

            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar fornecedor: " + e);
        }

        return true;
    }
    
    public ArrayList<Object> consultarFornecedorAtivo() {
        ArrayList<Object> cidades = new ArrayList<>();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * from fornecedor "
                    + "where situacao = 'A' ";

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Fornecedor fornec = new Fornecedor();
                fornec.setCodfornecedor(resultado.getInt("codfornecedor"));
                fornec.setNome(resultado.getString("nome"));
                fornec.setDatacadastro(Formatacao.ajustaDataDMA(resultado.getString("datacadastro")));
                fornec.setSituacao(resultado.getString("situacao").charAt(0));

                cidades.add(fornec);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar fornecedores Ativos: " + e);
        }

        return cidades;
    }    

}
