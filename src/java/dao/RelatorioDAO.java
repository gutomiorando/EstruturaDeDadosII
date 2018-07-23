/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author Usuario
 */
public class RelatorioDAO {
    /*public byte[] relatorio_vendas_por_cliente(int id) 
    {
        try 
        {
            Connection conn = new ConexaoBD().getInstance().getConnection();
            
            JasperReport relatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream("/relatorios/vendasporcliente.jrxml"));

            Map parametros = new HashMap();
            parametros.put("cliente_id", id);
            
            byte[] bytes = JasperRunManager.runReportToPdf(relatorio, parametros, conn);

            return bytes;
        } 
        catch (Exception e) 
        {
            System.out.println("Erro ao gerar relatorio: " + e);
        }
        return null;
    }*/
    
        public byte[] gerarContasapagarnoperiodo(Timestamp inicio, Timestamp fim) {
        
        try {
            Connection conn = new ConexaoBD().getInstance().getConnection();
            
            JasperReport relatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream("/relatorios/contasapagarporperiodo.jrxml"));
            
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
    
        public byte[] gerarListaFornecedores(int id) {
        
        try {
            Connection conn = new ConexaoBD().getInstance().getConnection();
            
            JasperReport relatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream("/relatorios/contasapagarporfornecedor.jrxml"));

            Map parameters = new HashMap();
            parameters.put("codfornecedor_fp", id);
            
            byte[] bytes = JasperRunManager.runReportToPdf(relatorio, parameters, conn);
            
            return bytes;
        } catch (Exception e) {
            System.out.println("erro ao gerar relatorio: " + e);
        }
        return null;
    }
        
    public byte[] gerarContasarecebernoperiodo(Timestamp inicio, Timestamp fim) {
        
        try {
            Connection conn = new ConexaoBD().getInstance().getConnection();
            
            JasperReport relatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream("/relatorios/contasareceberporperiodo.jrxml"));
            
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
        
        
}
