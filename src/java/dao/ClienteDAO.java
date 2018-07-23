/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import apoio.Formatacao;
import apoio.IDAO;
import entidade.Cidade;
import entidade.Cliente;
import entidade.Usuario;
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
public class ClienteDAO implements IDAO {

    @Override
    public String salvar(Object o) {
        Cliente cli = (Cliente) o;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "INSERT INTO cliente VALUES ("
                    + "DEFAULT,"
                    + "" + cli.getCidade().getCodcid() + ","
                    + "'" + cli.getTipocadastro() + "',"
                    + "'" + cli.getNome() + "',"
                    + "'" + cli.getCpfCnpj() + "',"
                    + "'" + cli.getRua() + "',"
                    + "" + cli.getNumero() + ","
                    + "'" + cli.getDatacadastro() + "',"
                    + "'" + cli.getSituacao() + "'"
                    + ")";

            System.out.println("SQL: " + sql);

            int resultado = st.executeUpdate(sql);

            if (resultado != 0) {
                return null;
            }
            return "xxx";

        } catch (Exception e) {
            System.out.println("Erro salvar Cliente no banco = " + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(Object o) {
        Cliente cli = (Cliente) o;

        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "update cliente "
                    + "set codcid = " + cli.getCidade().getCodcid() + ","
                    + "tipocadastro = '" + cli.getTipocadastro() + "',"
                    + "nome = '" + cli.getNome() + "',"
                    + "cpf_cnpj = '" + cli.getCpfCnpj() + "',"
                    + "rua = '" + cli.getRua() + "',"
                    + "numero = " + cli.getNumero() + ","
                    //+ "datacadastro = '" + cli.getDatacadastro() + "',"
                    + "situacao = '" + cli.getSituacao() + "'"
                    + "where codcli = " + cli.getCodcli();

            int resultado = st.executeUpdate(sql);
            System.out.println("sql" + sql);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar cliente: " + e);
            return e.toString();
        }

        return null;
    }

    @Override
    public String excluir(int id) {
        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "update cliente "
                    + "set situacao = 'I' "
                    + "where codcli = " + id;

            int resultado = st.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao excluir Cliente: " + e);
            return e.toString();
        }
        return null;
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        ArrayList<Object> clientes = new ArrayList<>();

        try {
//            Statement st = bilhetario.Bilhetario.conexao.createStatement();
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * from  cliente   where nome ilike  '%'order by nome";

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Cliente cli = new Cliente();
                Cidade cid = new Cidade();
                cli.setCodcli(resultado.getInt("codcli"));
                cid.setCodcid(resultado.getInt("codcid"));
                cli.setTipocadastro(resultado.getString("tipocadastro").charAt(0));
                cli.setNome(resultado.getString("nome"));
                cli.setCpfCnpj(resultado.getString("cpf_cnpj"));
                cli.setRua(resultado.getString("rua"));
                cli.setNumero(resultado.getInt("numero"));
                cli.setDatacadastro(Formatacao.ajustaDataDMA(resultado.getString("datacadastro")));
                cli.setSituacao(resultado.getString("situacao").charAt(0));

                clientes.add(cli);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar todos clientes: " + e);
        }

        return clientes;
    }

    @Override
    public boolean verificarRegistroUnico(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> consultar(String criterio) {
     ArrayList<Object> cliente = new ArrayList<>();

        try {
            String sql = "SELECT * "
                    + "FROM CLIENTE "
                    + "WHERE nome ilike '%" + criterio + "%' "
                    + "ORDER BY nome";

            ResultSet retorno = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (retorno.next()) {
                Cliente cli = new Cliente();
                Cidade cid = new Cidade();
                cli.setCodcli(retorno.getInt("codcli"));
                cid.setCodcid(retorno.getInt("codcid"));
                cli.setTipocadastro(retorno.getString("tipocadastro").charAt(0));
                cli.setNome(retorno.getString("nome"));
                cli.setCpfCnpj(retorno.getString("cpf_cnpj"));
                cli.setRua(retorno.getString("rua"));
                cli.setNumero(retorno.getInt("numero"));
                cli.setDatacadastro(Formatacao.ajustaDataDMA(retorno.getString("datacadastro")));
                cli.setSituacao(retorno.getString("situacao").charAt(0));

                cliente.add(cli);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar clientes por criterio: " + e);
            return null;
        }

        return cliente;
    }


    @Override
    public Object consultarId(int id) {
        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * from cliente "
                    + "where codcli = " + id;

            ResultSet resultado = st.executeQuery(sql);

            if (resultado.next()) {
                Cliente cli = new Cliente();
                Cidade cid = new Cidade();
                cli.setCodcli(resultado.getInt("codcli"));
                cid.setCodcid(resultado.getInt("codcid"));
                cli.setTipocadastro(resultado.getString("tipocadastro").charAt(0));
                cli.setNome(resultado.getString("nome"));
                cli.setCpfCnpj(resultado.getString("cpf_cnpj"));
                cli.setRua(resultado.getString("rua"));
                cli.setNumero(resultado.getInt("numero"));
                cli.setDatacadastro(Formatacao.ajustaDataDMA(resultado.getString("datacadastro")));
                cli.setSituacao(resultado.getString("situacao").charAt(0));

                return cli;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Deu erro: " + e);
            return e.toString();
        }
    }

    @Override
    public boolean consultar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean consultarcliente(String veio) {
        ArrayList<Object> clientes = new ArrayList<>();
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * from cliente";

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Cliente cli = new Cliente();
                Cidade cid = new Cidade();
                cli.setCodcli(resultado.getInt("codcli"));
                cid.setCodcid(resultado.getInt("codcid"));
                cli.setTipocadastro(resultado.getString("tipocadastro").charAt(0));
                cli.setNome(resultado.getString("nome"));
                cli.setCpfCnpj(resultado.getString("cpf_cnpj"));
                cli.setRua(resultado.getString("rua"));
                cli.setNumero(resultado.getInt("numero"));
                cli.setDatacadastro(Formatacao.ajustaDataDMA(resultado.getString("datacadastro")));
                cli.setSituacao(resultado.getString("situacao").charAt(0));
                clientes.add(cli);

                //if (id > 0) {
                if (veio.equalsIgnoreCase(resultado.getString("cpf_cnpj"))) {
                    System.out.println(" é igual");
                    return false;
                }
                // } else{
                //      System.out.println("É só atualizar, não testou cpf");
                // }
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar cliente: " + e);
        }

        return true;

    }
    
     public ArrayList<Object> consultarporSituacao(String situacao) {
     ArrayList<Object> cliente = new ArrayList<>();

        try {
            String sql = "SELECT * "
                    + "FROM CLIENTE "
                    + "WHERE situacao = '" + situacao + "' ORDER BY nome";

            ResultSet retorno = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (retorno.next()) {
                Cliente cli = new Cliente();
                Cidade cid = new Cidade();
                cli.setCodcli(retorno.getInt("codcli"));
                cid.setCodcid(retorno.getInt("codcid"));
                cli.setTipocadastro(retorno.getString("tipocadastro").charAt(0));
                cli.setNome(retorno.getString("nome"));
                cli.setCpfCnpj(retorno.getString("cpf_cnpj"));
                cli.setRua(retorno.getString("rua"));
                cli.setNumero(retorno.getInt("numero"));
                cli.setDatacadastro(Formatacao.ajustaDataDMA(retorno.getString("datacadastro")));
                cli.setSituacao(retorno.getString("situacao").charAt(0));

                cliente.add(cli);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar clientes por situacao: " + e);
            return null;
        }

        return cliente;
    }
     
     
    public byte[] gerarListaClientes(int id) {
        
        try {
            Connection conn = new ConexaoBD().getInstance().getConnection();
            
            JasperReport relatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream("/relatorios/vendasporcliente.jrxml"));

            Map parameters = new HashMap();
            parameters.put("codcli_fp", conn);
            
            byte[] bytes = JasperRunManager.runReportToPdf(relatorio, parameters, conn);
            
            return bytes;
        } catch (Exception e) {
            System.out.println("erro ao gerar relatorio: " + e);
        }
        return null;
    }
 
        public ArrayList<Object> consultarTodosAtivos() {
        ArrayList<Object> clientes = new ArrayList<>();

        try {
//            Statement st = bilhetario.Bilhetario.conexao.createStatement();
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            //String sql = "select * from  cliente"
            //        + "WHERE situacao = 'A'";
            String sql = "SELECT * "
                    + "FROM CLIENTE "
                    + "WHERE situacao = 'A' ORDER BY nome";

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Cliente cli = new Cliente();
                Cidade cid = new Cidade();
                cli.setCodcli(resultado.getInt("codcli"));
                cid.setCodcid(resultado.getInt("codcid"));
                cli.setTipocadastro(resultado.getString("tipocadastro").charAt(0));
                cli.setNome(resultado.getString("nome"));
                cli.setCpfCnpj(resultado.getString("cpf_cnpj"));
                cli.setRua(resultado.getString("rua"));
                cli.setNumero(resultado.getInt("numero"));
                cli.setDatacadastro(Formatacao.ajustaDataDMA(resultado.getString("datacadastro")));
                cli.setSituacao(resultado.getString("situacao").charAt(0));

                clientes.add(cli);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar todos clientes: " + e);
        }

        return clientes;
    }
        
         public ArrayList<Object> consultarComFiltro(String criterio)
    {
        ArrayList<Object> cliente = new ArrayList<>();

        try
        {
//            Statement st = bilhetario.Bilhetario.conexao.createStatement();
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM cliente WHERE nome ILIKE '%" + criterio + "%'"
                    + "order by nome";

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next())
            {
                Cliente cli = new Cliente();
                Cidade cid = new Cidade();
                
                cli.setCodcli(resultado.getInt("codcli"));
                cid.setCodcid(resultado.getInt("codcid"));
                cli.setTipocadastro(resultado.getString("tipocadastro").charAt(0));
                cli.setNome(resultado.getString("nome"));
                cli.setCpfCnpj(resultado.getString("cpf_cnpj"));
                cli.setRua(resultado.getString("rua"));
                cli.setNumero(resultado.getInt("numero"));
                cli.setDatacadastro(Formatacao.ajustaDataDMA(resultado.getString("datacadastro")));
                cli.setSituacao(resultado.getString("situacao").charAt(0));

                cliente.add(cli);
            }

        }
        catch (SQLException e)
        {
            System.out.println("Erro ao consultar Cliente com filtro: " + e);
        }

        return cliente;
    }

    public Cliente consultarId(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
