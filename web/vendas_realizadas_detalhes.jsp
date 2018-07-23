<%-- 
    Document   : vendas_realizadas_detalhes
    Created on : 04/12/2017, 18:39:30
    Author     : Usuario
--%>

<%@page import="entidade.Mercadoria"%>
<%@page import="dao.MercadoriaDAO"%>
<%@page import="entidade.ItensVenda"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.ItensVendaDAO"%>
<%@page import="entidade.Venda"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Venda venda = (Venda) request.getAttribute("venda");
    ItensVendaDAO itvendaDAO = new ItensVendaDAO();
    ArrayList vendaProdutos = itvendaDAO.consultarVendaProdutos(venda.getCodvenda());
%>
<!-- Page Header-->
<header class="page-header">
    <div class="container-fluid">
        <h2 class="no-margin-bottom">Datelhes da Vendas</h2>
    </div>
</header>
<!-- Breadcrumb-->
<ul class="breadcrumb">
    <div class="container-fluid">
        <li class="breadcrumb-item"><a href="acao?parametro=home">Home</a></li>
        <li class="breadcrumb-item active">Datelhes da Vendas</li>
    </div>
</ul>
<section class="tables">   
    <div class="container-fluid">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-header d-flex align-items-center">
                        <h3 class="h4">Detalhes</h3>
                    </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-6">
                            <label class="form-control-label">Forma de Pagamento</label>
                            <input type="text" value="<%= venda.getForma_pagamento_String() %>" class="form-control col-md-10">
                        </div>
                        <div class="col-md-6">
                            <label class="form-control-label">Cliente</label>
                            <input type="text" value="<%= venda.getCliente().getNome() %>" class="form-control col-md-10">
                        </div>
                    </div>
                    <div class="row breadcrumb">
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label class="form-control-label">Valor Total</label>
                            <input type="text" value="<%= venda.getValortotal() %>" class="form-control col-md-10">
                        </div>
                    </div>
                    <div class="row breadcrumb">
                        <div class="col-md-6">
                            <label class="form-control-label">Situação</label>
                            <input type="text" value="<%= venda.getSituacaovenda()%>" class="form-control col-md-10">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label class="form-control-label">Data da Venda</label>
                            <input type="text" value="<%= venda.getDataemissao()%>" class="form-control col-md-10">
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-header d-flex align-items-center">
                        <h3 class="h4">Produtos da venda</h3>
                    </div>
                    <div class="card-body" id="tabela_venda_export">
                        <table class="table table-striped table-sm">
                            <thead>
                                <tr>
                                    <th>Código</th>
                                    <th>Categoria</th>
                                    <th>Nome</th>
                                    <th>Quantidade</th>
                                    <th>Valor Unitário</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (int i = 0; i < vendaProdutos.size(); i++)
                                    {
                                        ItensVenda vp = (ItensVenda) vendaProdutos.get(i);
                                        int quantidadeItem = (int) vp.getQuantidade();
                                        MercadoriaDAO pDAO = new MercadoriaDAO();
                                        Mercadoria produtoLista = (Mercadoria) pDAO.consultarId(vp.getMercadoria().getCodmercadoria());
                                %>
                                <tr>
                                    <td scope="row"><%= produtoLista.getCodmercadoria()%></td>
                                    <td><%= produtoLista.getTipo()%></td>
                                    <td><%= produtoLista.getDescricao()%></td>
                                    <td><%= quantidadeItem%></td>
                                    <td><%= produtoLista.getValorunitario()%></td>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- Inline Form-->
        <div class="col-lg-9">   
            <button id="export" data-export="export" class="btn btn-primary" data-icon="l"> Gerar CSV com filtro</button>
        </div>
    </div>
</section>
<script src="js/jquery.tabletoCSV.js"></script>
<script type="text/javascript">
    $(function () {
        $("#export").click(function () {
            $("#tabela_venda_export").tableToCSV();
        });
    });
</script>            
