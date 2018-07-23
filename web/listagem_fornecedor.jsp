<%-- 
    Document   : listagem_fornecedor
    Created on : 12/10/2017, 10:01:56
    Author     : Usuario
--%>



<%@page import="entidade.Fornecedor"%>
<%@page import="dao.FornecedorDAO"%>
<%@page import="java.util.ArrayList"%>
<!-- Page Header-->
<header class="page-header">
    <div class="container-fluid">
        <h2 class="no-margin-bottom">Listagem de Fornecedores</h2>
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
                        <!--MENSAGEM DE CONFIRMAÇÃO PARA NOVOS CADASTROS-->
                        <%    String confirma_exclusao = String.valueOf(request.getAttribute("confirma_exclusao"));
                            if (confirma_exclusao.equalsIgnoreCase("sucesso")) {
                        %>
                        <div class="alert alert-success" role="alert">
                            <strong>Sucesso!</strong> O Fornecedor foi inativado.
                        </div>
                        <%
                        } else if (confirma_exclusao.equalsIgnoreCase("erro")) {
                        %>
                        <div class="alert alert-danger" role="alert">
                            <strong></strong> Ocorreu um erro ao inativar o fornecedor.
                        </div>
                        <%
                            }
                        %>
                        <table class="table table-striped table-sm">
                            <thead>
                                <tr>
                                    <th>Código</th>
                                    <th>Nome</th>
                                    <th>Data Cadastro</th>
                                    <th>Situação</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    ArrayList<Object> FornecedorDAO = new FornecedorDAO().consultarTodos();

                                    for (int i = 0; i < FornecedorDAO.size(); i++) {
                                        Fornecedor forne = (Fornecedor) FornecedorDAO.get(i);
                                %>
                                <tr>
                                    <th scope="row"><%= forne.getCodfornecedor()%></th>
                                    <td><%= forne.getNome()%></td>
                                    <td><%= forne.getDatacadastro()%></td>
                                    <td><%= forne.getSituacao()%></td>
                                    <td><a href="acao?parametro=editar_fornecedor&id=<%= forne.getCodfornecedor()%>">Editar</span></a></td>
                                    <td><a href="acao?parametro=excluir_fornecedor&id=<%= forne.getCodfornecedor()%>">Excluir</span></a></td>
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
    </div>
</section>
