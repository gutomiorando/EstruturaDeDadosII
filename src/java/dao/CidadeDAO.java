/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import apoio.IDAO;
import entidade.Cidade;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author Usuario
 */
public class CidadeDAO implements IDAO {

    @Override
    public String salvar(Object o) {
        Cidade cid = (Cidade) o;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "INSERT INTO cidade VALUES ("
                    + "DEFAULT,"
                    + "'" + cid.getNome() + "',"
                    + "'" + cid.getSituacao() + "'"
                    + ")";

            int resultado = st.executeUpdate(sql);

            if (resultado != 0) {
                return null;
            }
            return "xxx";

        } catch (Exception e) {
            System.out.println("Erro salvar Cidade = " + e);
            return e.toString();
        }

    }

    @Override
    public String atualizar(Object o) {
        Cidade cid = (Cidade) o;

        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "update cidade "
                    + "set nome = '" + cid.getNome() + "',"
                    + "situacao = '" + cid.getSituacao() + "'"
                    + "where codcid = " + cid.getCodcid();

            int resultado = st.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar cidade: " + e);
            return e.toString();
        }

        return null;
    }

    @Override
    public String excluir(int id) {
        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "update cidade "
                    + "set situacao = 'I' "
                    + "where codcid = " + id;

            int resultado = st.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Deu erro ao excluir a cidade: " + e);
            return e.toString();
        }
        return null;
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        ArrayList<Object> cidades = new ArrayList<>();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * from cidade order by nome";

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Cidade cid = new Cidade();
                cid.setCodcid(resultado.getInt("codcid"));
                cid.setNome(resultado.getString("nome"));
                cid.setSituacao(resultado.getString("situacao").charAt(0));

                cidades.add(cid);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar todas cidades: " + e);
        }

        return cidades;
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

            String sql = "select * from cidade "
                    + "where codcid = " + id;

            ResultSet resultado = st.executeQuery(sql);

            if (resultado.next()) {
                Cidade cid = new Cidade();
                cid.setCodcid(resultado.getInt("codcid"));
                cid.setNome(resultado.getString("nome"));
                cid.setSituacao(resultado.getString("situacao").charAt(0));

                return cid;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Deu erro ao consultar cidade por ID: " + e);
            return e.toString();
        }
    }

    @Override
    public boolean consultar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean consultarcidade(String veio) {
        ArrayList<Object> cidades = new ArrayList<>();
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * from cidade";

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Cidade cid = new Cidade();
                cid.setCodcid(resultado.getInt("codcid"));
                cid.setNome(resultado.getString("nome"));
                cid.setSituacao(resultado.getString("situacao").charAt(0));
                cidades.add(cid);

                if (veio.equalsIgnoreCase(resultado.getString("nome"))) {
                    System.out.println(" é igual");
                    return false;
                }

            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar cidade: " + e);
        }

        return true;
    }
    
public ArrayList<Object> consultarCidadesAtivas() {
        ArrayList<Object> cidades = new ArrayList<>();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * from cidade "
                    + "where situacao = 'A' ";

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Cidade cid = new Cidade();
                cid.setCodcid(resultado.getInt("codcid"));
                cid.setNome(resultado.getString("nome"));
                cid.setSituacao(resultado.getString("situacao").charAt(0));

                cidades.add(cid);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar todas cidades: " + e);
        }

        return cidades;
    }    

    public byte[] gerarListaCidades(int id) {
        
        try {
            Connection conn = new ConexaoBD().getInstance().getConnection();
            
            JasperReport relatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream("/relatorios/clientes_por_cidade2.jrxml"));

            Map parameters = new HashMap();
            parameters.put("codcid_fp", id);
            
            byte[] bytes = JasperRunManager.runReportToPdf(relatorio, parameters, conn);
            
            return bytes;
        } catch (Exception e) {
            System.out.println("erro ao gerar relatorio: " + e);
        }
        return null;
    }
    

}
