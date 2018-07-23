/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import apoio.Formatacao;
import apoio.IDAO;
import entidade.Funcionario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
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
public class FuncionarioDAO implements IDAO {

    @Override
    public String salvar(Object o) {
        Funcionario func = (Funcionario) o;

        String datad = func.getDatademissao();

        if (Formatacao.removerFormatacao(datad.trim()).length() <= 2) {
            datad = null;
        } else {
            datad = "'" + datad + "'";
        }

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "INSERT INTO funcionario VALUES ("
                    + "DEFAULT,"
                    + "" + func.getIdempresa() + ","
                    + "'" + func.getNome() + "',"
                    + "'" + func.getDataadmissao() + "',"
                    + "" + datad + ","
                    + "'" + func.getFuncao() + "',"
                    + "" + func.getSalario() + ","
                    + "'" + func.getSituacao() + "'"
                    + ")";

            //System.out.println("SQL: " + sql);
            int resultado = st.executeUpdate(sql);

            if (resultado != 0) {
                return null;
            }
            return "xxx";

        } catch (Exception e) {
            System.out.println("Erro ao salvar Funcionario = " + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(Object o) {
        Funcionario func = (Funcionario) o;

        //int codigofuncatuliza = func.getCodfunc();
        String datad = func.getDatademissao();

        if (Formatacao.removerFormatacao(datad.trim()).length() <= 2) {
            datad = null;
        } else {
            datad = "'" + datad + "'";
        }

        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "update funcionario "
                    + "set idempresa = " + func.getIdempresa() + ","
                    + "nome = '" + func.getNome() + "',"
                    + "dataadmissao = '" + func.getDataadmissao() + "',"
                    + "datademissao = " + datad + ","
                    + "funcao = '" + func.getFuncao() + "',"
                    + "salario = " + func.getSalario() + ","
                    + "situacao = '" + func.getSituacao() + "'"
                    + "where codfunc = " + func.getCodfunc();

            int resultado = st.executeUpdate(sql);
            // System.out.println(" atualizou: " + sql);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar funcionario: " + e);
            return e.toString();
        }

        return null;
    }

    @Override
    public String excluir(int id) {
        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "update funcionario "
                    + "set situacao = 'I' "
                    + "where codfunc = " + id;

            int resultado = st.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao excluir Funcionario: " + e);
            return e.toString();
        }
        return null;
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        ArrayList<Object> funcionario = new ArrayList<>();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * from funcionario";

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Funcionario func = new Funcionario();
                func.setCodfunc(resultado.getInt("codfunc"));
                func.setIdempresa(resultado.getInt("idempresa"));
                func.setNome(resultado.getString("nome"));
                func.setDataadmissao(Formatacao.ajustaDataDMA(resultado.getString("dataadmissao")));
                //  func.setDataadmissao(resultado.getString("dataadmissao"));
                func.setDatademissao(resultado.getString("datademissao"));
                func.setFuncao(resultado.getString("funcao"));
                func.setSalario(resultado.getDouble("salario"));
                func.setSituacao(resultado.getString("situacao").charAt(0));

                funcionario.add(func);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar Todos Funcionarios: " + e);
        }

        return funcionario;
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

            String sql = "select * from funcionario "
                    + "where codfunc = " + id;

            ResultSet resultado = st.executeQuery(sql);

            if (resultado.next()) {
                Funcionario func = new Funcionario();
                func.setCodfunc(resultado.getInt("codfunc"));
                func.setIdempresa(resultado.getInt("idempresa"));
                func.setNome(resultado.getString("nome"));
                func.setDataadmissao(resultado.getString("dataadmissao"));
                func.setDatademissao(resultado.getString("datademissao"));
                func.setFuncao(resultado.getString("funcao"));
                func.setSalario(resultado.getDouble("salario"));
                func.setSituacao(resultado.getString("situacao").charAt(0));

                return func;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Deu erro ao consultar funcionario por ID: " + e);
            return e.toString();
        }
    }

    @Override
    public boolean consultar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean consultarFuncionario(String nome, String data, String funcao, String salario) {
        ArrayList<Object> func = new ArrayList<>();
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * from funcionario";

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Funcionario fu = new Funcionario();
                fu.setCodfunc(resultado.getInt("codfunc"));
                fu.setIdempresa(resultado.getInt("idempresa"));
                fu.setNome(resultado.getString("nome"));
                fu.setDataadmissao(resultado.getString("dataadmissao"));
                fu.setDatademissao(resultado.getString("datademissao"));
                fu.setFuncao(resultado.getString("funcao"));
                fu.setSalario(resultado.getDouble("salario"));
                fu.setSituacao(resultado.getString("situacao").charAt(0));
                func.add(fu);

                String dt = Formatacao.ajustaDataDMA(resultado.getString("dataadmissao"));
                String sl = salario.replace(',', '.');

                if (nome.equalsIgnoreCase(resultado.getString("nome"))) {
                    if (data.equalsIgnoreCase(dt)) {
                        if (funcao.equalsIgnoreCase(resultado.getString("funcao"))) {
                            if (salario.equalsIgnoreCase(sl)) {
                                return false;
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar Funcionario com criterios: " + e);
        }

        return true;
    }

    public byte[] gerarFuncionariosporAdmissao(Timestamp inicio) {
        
        try {
            Connection conn = new ConexaoBD().getInstance().getConnection();
            
            JasperReport relatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream("/relatorios/funcionarios_por_admissao.jrxml"));
            
            Map parameters = new HashMap();
            parameters.put("dataadmissao_fp", inicio);
            
            byte[] bytes = JasperRunManager.runReportToPdf(relatorio, parameters, conn);
            
            return bytes;
        } catch (Exception e) {
            System.out.println("erro ao gerar relatorio: " + e);
        }
        return null;
    }
    
}
