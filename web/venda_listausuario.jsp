<%-- 
    Document   : venda_listausuario
    Created on : 29/10/2017, 15:52:18
    Author     : Usuario
--%>

<%@page import="entidade.Cliente"%>
<%@page import="dao.ClienteDAO"%>
<%@page import="entidade.Usuario"%>
<%@page import="dao.UsuarioDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema Ipiranga/title>
    </head>

    <%//@include file="menu.jsp" %>

    <body>
        <h1>Listagem de usuários</h1>


        <%            
            String criterio = request.getParameter("criterio");

            if (criterio == null) {
                criterio = "";
            }
        %>
        
        <div class="table-responsive">
            <div class="row col-md-6">

                <table class="table table-condensed table-hover">
                    <thead class="thead-default">
                        <tr>
                            <th>Id</th>
                            <th>Tipo de Cadastro</th>
                            <th>Nome</th>
                            <th>Cpf/Cnpj</th>
                            <th>Situação</th>
                            <th>Selecionar</th>
                        </tr>
                    </thead>
                    <%
                        ArrayList<Object> clientes = new ClienteDAO().consultar(criterio);

                        for (int i = 0; i < clientes.size(); i++) {
                            Cliente c = (Cliente) clientes.get(i);
                    %>
                    <tr>
                        <td><%= c.getCodcli()%></td>
                        <td><%= c.getTipocadastro()%></td>
                        <td><%= c.getNome()%></td>
                        <td><%= c.getCpfCnpj()%></td>
                        <td><%= c.getSituacao()%></td>
                        <td><a href="acao?parametro=SelUsuario&id=<%= c.getCodcli()%>"><span class="pull-left glyphicon glyphicon-pencil"></span></a></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </div>
        </div>
    </body>
</html>
