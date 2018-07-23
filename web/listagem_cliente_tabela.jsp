<%-- 
    Document   : listagem_cliente_tabela
    Created on : 14/11/2017, 20:42:48
    Author     : Usuario
--%>

<%@page import="entidade.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.ClienteDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String origem = request.getParameter("origem");

    ClienteDAO cliDAO = new ClienteDAO();
    ArrayList<Object> clienteLista = cliDAO.consultarTodos();
    String pesquisa = request.getParameter("pesquisa");
    if (pesquisa != null) {
        clienteLista = cliDAO.consultarComFiltro(pesquisa);
    }
%>
<table class="table table-striped table-sm" id="tabela_cli">
    <thead>
        <tr>
            <th>Código</th>
            <th>Tipo</th>
            <th>Nome</th>
            <th>Cpf ou Cnpj</th>
            <th>Rua</th>
            <th>Numero</th>
            <th>Data do Cadatro</th>
            <th>Situação</th>
            <th></th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <%
            //                                   ArrayList<Object> usuarioDAO = new UsuarioDAO().consultarTodos();
            for (int i = 0; i < clienteLista.size(); i++) {
                Cliente cliente = (Cliente) clienteLista.get(i);
        %>
        <tr>
            <td scope="row"><%= cliente.getCodcli()%></td>
            <td><%= cliente.getTipocadastro()%></td>
            <td><%= cliente.getNome()%></td>
            <td><%= cliente.getCpfCnpj()%></td>
            <td><%= cliente.getRua()%></td>
            <td><%= cliente.getNumero()%></td>
            <td><%= cliente.getDatacadastro()%></td>
            <td><%= cliente.getSituacao()%></td>
            <td><a href="acao?parametro=editar_cliente&id=<%= cliente.getCodcli()%>">Editar</span></a></td>
            <td><a href="acao?parametro=excluir_cliente&id=<%= cliente.getCodcli()%>">Excluir</span></a></td>
        </tr>
        <%
            }
        %>
    </tbody>
</table>
