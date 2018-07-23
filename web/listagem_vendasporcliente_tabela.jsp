<%-- 
    Document   : Listagem_vendasporcliente_tabela
    Created on : 12/11/2017, 22:59:42
    Author     : Usuario
--%>

<%@page import="dao.VendaDAO"%>
<%@page import="entidade.Venda"%>
<%@page import="entidade.Cliente"%>
<%@page import="dao.ClienteDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String origem = request.getParameter("origem");

    ArrayList<Object> vendaDAO = new VendaDAO().consultarTodos();
    if (request.getAttribute("pesquisa") != null)
    {
        vendaDAO = (ArrayList) request.getAttribute("pesquisa");
    }
    
    ClienteDAO clienteDAO = new ClienteDAO();
    ArrayList<Object> clienteLista = clienteDAO.consultarTodos();
    String pesquisa = request.getParameter("pesquisa");
    if (pesquisa != null) {
        clienteLista = clienteDAO.consultarComFiltro(pesquisa);
    }
%>
<!DOCTYPE html>
<table class="table table-striped table-sm" id="tabela_usu">
    <thead>
        <tr>
            <th>Código</th>
            <th>Valor Total</th>
            <th>Data</th>
            <th>Situação</th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <%
            for (int i = 0; i < vendaDAO.size(); i++) {
                Venda venda = (Venda) vendaDAO.get(i);
                ClienteDAO clienteDAO2 = new ClienteDAO();
                Cliente cliente = (Cliente) clienteDAO2.consultarId(venda.getCliente().getCodcli());
        %>
        <tr>
            <td scope="row"><b><%=venda.getCodvenda()%></b></td>
            <td><%=venda.getCliente().getNome()%></td>
            <td><%=venda.getDataemissao()%></td>
            <td><%=venda.getValortotal()%></td>
            <td><%=venda.getSituacaovenda()%></td>
            <td><a href="acao?parametro=venda_detalhes&id=<%= venda.getCodvenda()%>">Detalhes</a></td>
        </tr>
        <%
            }
        %>
    </tbody>
</table>
