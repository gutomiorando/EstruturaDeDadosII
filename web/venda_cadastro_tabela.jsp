<%-- 
    Document   : venda_cadastro_tabela
    Created on : 07/11/2017, 21:01:28
    Author     : Usuario
--%>

<%@page import="entidade.Mercadoria"%>
<%@page import="dao.MercadoriaDAO"%>
<%@page import="entidade.ItensVenda"%>
<%@page import="controle.ControlaVendaProdutos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String codproduto = request.getParameter("codproduto");

    if (codproduto == null) {
        codproduto = "0";
    }

    String quantidade = request.getParameter("quantidade");

    if (quantidade == null) {
        quantidade = "0";
    }

    ControlaVendaProdutos controlaVendaProdutos = new ControlaVendaProdutos();
    controlaVendaProdutos.iniciarVenda(codproduto, quantidade);
//    controlaVendaProdutos.carregaVariaveis(request, response);

//    acao.encaminharPagina("venda_produtos_informacoes", request, response);
%>
<table class="table table-striped table-sm">
    <thead>
        <tr>
            <th>Código</th>
            <th>Descrição</th>
            <th>Quantidade</th>
            <th>Valor Unitário</th>
            <th>Tipo</th>
        </tr>
    </thead>
    <tbody>
        <%            for (int i = 0; i < ControlaVendaProdutos.itensVenda.size(); i++) {
                ItensVenda vp = ControlaVendaProdutos.itensVenda.get(i);
                double quantidadeItem = vp.getQuantidade();
                MercadoriaDAO pDAO = new MercadoriaDAO();
//                pDAO.consultarId(vp.getProduto_id());
                Mercadoria produtoLista = (Mercadoria) pDAO.consultarId(vp.getCoditen());
        %>
        <tr>
            <th scope="row"><%= produtoLista.getCodmercadoria()%></th>
            <td><%= produtoLista.getDescricao()%></td>
            <td><%= quantidadeItem%></td>
            <td><%= produtoLista.getValorunitario()%></td>
            <td><%= produtoLista.getTipo()%></td>
            <td><a href="acao?parametro=remover_produto&indice_produto=<%= i %>">Remover</a></td>
        </tr>
        <%
            }
        %>
    </tbody>
</table>
