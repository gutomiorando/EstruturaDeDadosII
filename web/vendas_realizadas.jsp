<%-- 
    Document   : vendas_realizadas
    Created on : 04/12/2017, 17:41:25
    Author     : Usuario
--%>

<%@page import="entidade.Cliente"%>
<%@page import="dao.ClienteDAO"%>
<%@page import="entidade.Venda"%>
<%@page import="dao.VendaDAO"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<Object> vendaDAO = new VendaDAO().consultarTodos();
    if (request.getAttribute("pesquisa") != null)
    {
        vendaDAO = (ArrayList) request.getAttribute("pesquisa");
    }

    String confirma_exclusao = String.valueOf(request.getAttribute("confirma_exclusao"));
    if (confirma_exclusao.equalsIgnoreCase("sucesso"))
    {
%>
<script type="text/javascript">
    swal({position: 'center', type: 'success', title: 'Venda excluída com sucesso', showConfirmButton: false, timer: 1500});
</script>
<%
} else if (!confirma_exclusao.equalsIgnoreCase("null"))
{
%>
<script type="text/javascript">
    swal('Oops...', '<%= confirma_exclusao%>', 'error');
</script>
<%
    }
%>
<!-- Page Header-->
<header class="page-header">
    <div class="container-fluid">
        <h2 class="no-margin-bottom">Listagem de Vendas</h2>
    </div>
</header>
<!-- Breadcrumb-->
<ul class="breadcrumb">
    <div class="container-fluid">
        <li class="breadcrumb-item active">Vendas/Vendas Realizadas</li>
    </div>
</ul>
<section class="tables">   
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-header d-flex align-items-center">
                        <h3 class="h4">Listagem</h3>
                    </div>
                    <div class="card-body">
                        <form id="pesquisa" class="form-group" style="margin-bottom: 20px;" action="acao?parametro=pesquisa_venda_cliente" 
                              method="post" onsubmit="document.pesquisa.reset();">
                           <div class="form-group">
                                <div class="input-group">
                                    <input type="text" id="pesquisa" name="pesquisa" class="form-control">
                                    <span class="input-group-btn">
                                        <button type="submit" class="btn btn-primary">Pesquisar</button>
                                    </span>
                                </div>
                            </div>
                        </form>

                        <table class="table table-striped table-sm" id="tabela_venda_export">
                            <thead>
                                <tr>
                                    <th>Código</th>
                                    <th>Cliente</th>
                                    <th>Data</th>
                                    <th>Valor Total</th>
                                    <th>Situação</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (int i = 0; i < vendaDAO.size(); i++)
                                    {
                                        Venda venda = (Venda) vendaDAO.get(i);
                                        ClienteDAO clienteDAO2 = new ClienteDAO();
                                        Cliente cliente = (Cliente) clienteDAO2.consultarId(venda.getCliente().getCodcli());
                                %>
                                <tr>
                                    <td scope="row"><b><%=venda.getCodvenda()%></b></td>
                                    <td><%=venda.getCliente().getNome() %></td>
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
