<%-- 
    Document   : listagem_mercadoria
    Created on : 12/10/2017, 10:43:05
    Author     : Usuario
--%>

<%@page import="entidade.Mercadoria"%>
<%@page import="dao.MercadoriaDAO"%>
<%@page import="java.util.ArrayList"%>
<!-- Page Header-->
<header class="page-header">
    <div class="container-fluid">
        <h2 class="no-margin-bottom">Listagem de Mercadorias</h2>
    </div>
</header>
<!-- Breadcrumb-->
<ul class="breadcrumb">
    <div class="container-fluid">
        <li class="breadcrumb-item"><a>Inicio</a></li>
        <li class="breadcrumb-item active">Listagem</li>
    </div>
</ul>
<section class="tables">   
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-close">
                        <!--<div class="dropdown">
                            <button type="button" id="closeCard" data-toggle="dropdown" aria-haspopup="true" 
                                    aria-expanded="false" class="dropdown-toggle"><i class="fa fa-ellipsis-v"></i></button>
                            <div aria-labelledby="closeCard" class="dropdown-menu has-shadow">
                        <!--<a href="#" class="dropdown-item remove"> <i class="fa fa-times"></i>Close</a>
                        <a href="#" class="dropdown-item edit"> <i class="fa fa-gear"></i>Edit</a>
                    </div>
                </div>-->
                    </div>
                    <div class="card-header d-flex align-items-center">
                        <h3 class="h4">Listagem</h3>
                    </div>
                    <div class="card-body">
                        <%    String confirma_exclusao = String.valueOf(request.getAttribute("confirma_exclusao"));
                            if (confirma_exclusao.equalsIgnoreCase("sucesso")) {
                        %>
                        <div class="alert alert-success" role="alert">
                            <strong>Sucesso!</strong> A mercadoria foi inativada.
                        </div>
                        <%
                        } else if (confirma_exclusao.equalsIgnoreCase("erro")) {
                        %>
                        <div class="alert alert-danger" role="alert">
                            <strong></strong> Ocorreu um erro ao inativar a marcadoria.
                        </div>
                        <%
                            }
                        %>
                        <table class="table table-striped table-sm" id="tab_merc">
                            <thead>
                                <tr>
                                    <th>Código</th>
                                    <th>Descricao</th>
                                    <th>Valor Unitario</th>
                                    <th>Quantidade</th>
                                    <th>Estoque</th>
                                    <th>Hora Atualização</th>
                                    <th>Tipo</th>
                                    <th>Situação</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    ArrayList<Object> MercadoriaDAO = new MercadoriaDAO().consultarTodos();

                                    for (int i = 0; i < MercadoriaDAO.size(); i++) {
                                        Mercadoria mercadoria = (Mercadoria) MercadoriaDAO.get(i);
                                %>
                                <tr>
                                    <td scope="row"><%= mercadoria.getCodmercadoria()%></td>
                                    <td><%= mercadoria.getDescricao()%></td>
                                    <td><%= mercadoria.getValorunitario()%></td>
                                    <td><%= mercadoria.getQuantidade()%></td>
                                    <td><%= mercadoria.getEstoque()%></td>
                                    <td><%= mercadoria.getHoraatualizacao()%></td>
                                    <td><%= mercadoria.getTipo()%></td>
                                    <td><%= mercadoria.getSituacao()%></td>
                                    <td><a href="acao?parametro=editar_mercadoria&id=<%= mercadoria.getCodmercadoria()%>">Editar</span></a></td>
                                    <td><a href="acao?parametro=excluir_mercadoria&id=<%= mercadoria.getCodmercadoria()%>">Excluir</span></a></td>
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
        <div class="col-lg-9">
            <button id="export" data-export="export" class="btn btn-primary" data-icon="l"> Gerar CSV com filtro</button>
            <!--<a href="acao?parametro=csv_usuario" style="text-decoration:none;"><button type="submit" class="btn btn-primary" data-icon="l"> Gerar CSV com Todos Usuários</button></a>-->
        </div>
    </div>
</section>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/csv.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    $(function () {
        $("#export").click(function () {
            $("#tab_merc").tableToCSV();
        });
    });
</script>    