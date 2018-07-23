<%-- 
    Document   : listagem_contasareceber_tabela
    Created on : 19/11/2017, 14:36:37
    Author     : Usuario
--%>

<%@page import="entidade.Contasareceber"%>
<%@page import="dao.ContasareceberDAO"%>
<%-- 
    Document   : listagem_contasapagar_tabela
    Created on : 19/11/2017, 14:07:37
    Author     : Usuario
--%>

<%@page import="entidade.Contasapagar"%>
<%@page import="dao.ContasapagarDAO"%>
<%@page import="entidade.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.UsuarioDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String origem = request.getParameter("origem");
    
    ContasareceberDAO contasDAO = new ContasareceberDAO();
    ArrayList<Object> contasLista = contasDAO.consultarTodos();
    String pesquisa = request.getParameter("pesquisa");
    if (pesquisa != null) {
        contasLista = contasDAO.consultarComFiltro(pesquisa);
    }
%>
<table class="table table-striped table-sm" id="tabela_usu">
    <thead>
        <tr>
            <th>Código</th>
            <th>Cód. Venda</th>
            <th>Data Lancamento</th>
            <th>Data Vencimento</th>
            <th>Data Pagamento</th>
            <th>Valor Previsto</th>
            <th>Valor Recebido</th>
            <th>Situação</th>
            <th></th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <%
            //                                   ArrayList<Object> usuarioDAO = new UsuarioDAO().consultarTodos();
            for (int i = 0; i < contasLista.size(); i++) {
                Contasareceber cont = (Contasareceber) contasLista.get(i);
        %>
        <tr>
            <td scope="row"><%= cont.getId()%></td>
            <td><%= cont.getCodvenda()%></td>
            <td><%= cont.getDatalancamento()%></td>
            <td><%= cont.getDatavencimento()%></td>
            <td><%= cont.getDatapagamento()%></td>
            <td><%= cont.getValorprevisto()%></td>
            <td><%= cont.getValorrecebido()%></td>
            <td><%= cont.getSituacao()%></td>
            <td><a href="acao?parametro=receber_conta&id=<%= cont.getId()%>">receber</span></a></td>
        </tr>
        <%
            }
        %>
    </tbody>
</table>
