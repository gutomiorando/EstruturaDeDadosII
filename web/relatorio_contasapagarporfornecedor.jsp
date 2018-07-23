<%-- 
    Document   : relatorio_contasapagarporfornecedor
    Created on : 12/11/2017, 10:28:00
    Author     : Usuario
--%>

<%@page import="entidade.Fornecedor"%>
<%@page import="dao.FornecedorDAO"%>
<%@page import="java.util.ArrayList"%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sistema Ipiranga</title>
</head>

<!-- Page Header-->
<header class="page-header">
    <div class="container-fluid">
        <h2 class="no-margin-bottom">Relatório de Contas a pagar por Fornecedor</h2>
    </div>
</header>
<ul class="breadcrumb">
    <div class="container-fluid">
        <li class="breadcrumb-item"><a>Inicio</a></li>
        <li class="breadcrumb-item active">Relatório de Contas a pagar por Fornecedor</li>
    </div>
</ul>
<!-- Forms Section-->
<section class="forms"> 
    <div class="container-fluid"> 
        <div class="row">
            <!-- Basic Form-->
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-close">
                        <!--<div class="dropdown">
                            <button type="button" id="closeCard" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-ellipsis-v"></i></button>
                            <div aria-labelledby="closeCard" class="dropdown-menu has-shadow"><a href="#" class="dropdown-item remove"> <i class="fa fa-times"></i>Close</a><a href="#" class="dropdown-item edit"> <i class="fa fa-gear"></i>Edit</a></div>
                        </div>
                        -->
                    </div>
                    <div class="card-header d-flex align-items-center">
                        <h3 class="h4">Selecione o Fornecedor</h3>
                    </div>
                    <div class="card-body">
                        <!--<p>Lorem ipsum dolor sit amet consectetur.</p>-->
                        <form class="form-horizontal" action="acao?parametro=relatorio_contasapagarporfornecedor" method="post">
                            <div class="form-group row">
                                <label class="col-sm-1 form-control-label">Fornecedor</label>
                                <div class="col-sm-4 select">

                                    <select name="codfornecedor" id="codfornecedor" class="form-control" required="true" >
                                        <!--<option id="F" value="F">Pessoa Física</option>
                                        <option id="J" value="J">Pessoa Jurídica</option>-->
                                        <%
                                            ArrayList<Object> for5 = new FornecedorDAO().consultarFornecedorAtivo();

                                            for (int i = 0; i < for5.size(); i++) {
                                                Fornecedor u2 = (Fornecedor) for5.get(i);
                                        %>
                                        <option id="<%= u2.getCodfornecedor()%>" value="<%= u2.getCodfornecedor()%>"><%= u2.getNome()%></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                            </div>     
                            <div class="form-group row-1">       
                                <input type="submit" value="Pesquisar Contas" href="acao?parametro=relatorio_contasapagarporfornecedor&codfornecedor" class="btn btn-primary">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>


