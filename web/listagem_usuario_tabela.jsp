<%-- 
    Document   : listagem_usuario_tabela
    Created on : 12/11/2017, 17:36:31
    Author     : Usuario
--%>

<%@page import="entidade.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.UsuarioDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String origem = request.getParameter("origem");
    
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    ArrayList<Object> usuarioLista = usuarioDAO.consultarTodos();
    String pesquisa = request.getParameter("pesquisa");
    if (pesquisa != null) {
        usuarioLista = usuarioDAO.consultarComFiltro(pesquisa);
    }
%>
<table class="table table-striped table-sm" id="tabela_usu">
    <thead>
        <tr>
            <th>Código</th>
            <th>Nome</th>
            <th>Email</th>
            <th>Situação</th>
            <th></th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <%
            //                                   ArrayList<Object> usuarioDAO = new UsuarioDAO().consultarTodos();
            for (int i = 0; i < usuarioLista.size(); i++) {
                Usuario usuario = (Usuario) usuarioLista.get(i);
        %>
        <tr>
            <td scope="row"><%= usuario.getCodusuario()%></td>
            <td><%= usuario.getNomeusuario()%></td>
            <td><%= usuario.getEmail()%></td>
            <td><%= usuario.getSituacao()%></td>
            <td><a href="acao?parametro=editar_usuario&id=<%= usuario.getCodusuario()%>">Editar</span></a></td>
            <td><a href="acao?parametro=excluir_usuario&id=<%= usuario.getCodusuario()%>">Excluir</span></a></td>
        </tr>
        <%
            }
        %>
    </tbody>
</table>
