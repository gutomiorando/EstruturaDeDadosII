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
    
    ContasapagarDAO contasDAO = new ContasapagarDAO();
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
            <th>Fornecedor</th>
            <th>Descricao</th>
            <th>Data Prevista</th>
            <th>Data Pagamento</th>
            <th>Valor</th>
            <th>Situação</th>
            <th></th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <%
            //                                   ArrayList<Object> usuarioDAO = new UsuarioDAO().consultarTodos();
            for (int i = 0; i < contasLista.size(); i++) {
                Contasapagar cont = (Contasapagar) contasLista.get(i);
        %>
        <tr>
            <td scope="row"><%= cont.getIdconta()%></td>
            <td><%= cont.getCodfornecedor()%></td>
            <td><%= cont.getDescricao()%></td>
            <td><%= cont.getDataprevista()%></td>
            <td><%= cont.getDatapagamento()%></td>
            <td><%= cont.getValortotal()%></td>
            <td><%= cont.getSituacao()%></td>
            <td><a href="acao?parametro=quitar_conta&id=<%= cont.getIdconta()%>">quitar</span></a></td>
        </tr>
        <%
            }
        %>
    </tbody>
</table>
