<%-- 
    Document   : cadastro_fornecedor
    Created on : 30/09/2017, 11:41:54
    Author     : Usuario
--%>

<%@page import="apoio.Formatacao"%>
<%@page import="entidade.Fornecedor"%>
<%
    Fornecedor for3 = (Fornecedor) request.getAttribute("fornecedor");

    if (for3 == null) {
        for3 = new Fornecedor();
    }

%>
<!-- Page Header-->
<header class="page-header">
    <div class="container-fluid">
        <h2 class="no-margin-bottom">Cadastro de Fornecedores</h2>
    </div>
</header>
<ul class="breadcrumb">
    <div class="container-fluid">
        <li class="breadcrumb-item"><a>Inicio</a></li>
        <li class="breadcrumb-item active">Cadastro de Fornecedores</li>
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
                        <h3 class="h4">Formulário de Cadastro</h3>
                    </div>
                    <div class="card-body">
                        <%  String confirma_cadastro = String.valueOf(request.getAttribute("confirma_cadastro"));
                            if (confirma_cadastro.equalsIgnoreCase("sucesso")) {
                        %>
                        <div class="alert alert-success" role="alert">
                            <strong>Fornecedor cadastrado com sucesso!</strong> 
                        </div>
                        <%
                        } else if (confirma_cadastro.equalsIgnoreCase("erro")) {
                        %>
                        <div class="alert alert-danger" role="alert">
                            <strong>Atenção!</strong> Ocorreu um erro ao salvar Fornecedor!
                        </div>
                        <%
                            }
                        %>
                        <!--<p>Lorem ipsum dolor sit amet consectetur.</p>-->
                        <form class="form-horizontal" action="acao?parametro=cadastro_fornecedor" method="post">
                            <div class="form-group row">
                                <label class="col-sm-1 form-control-label">Código</label>
                                <div class="col-sm-4">
                                    <input id="codfornecedor" value="<%=for3.getCodfornecedor()%>" readonly="true" type="text" placeholder="Código do Fornecedor" name="codfornecedor" class="form-control form-control-success">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-1 form-control-label">Nome*</label>
                                <div class="col-sm-4">
                                    <input id="nome" value="<%=for3.getNome()%>" type="text" placeholder="Nome do Fornecedor" name="nome" required="true" class="form-control form-control-warning">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-1 form-control-label">Data Cadastro*</label>
                                <div class="col-sm-4">
                                    <input id="datacadastro" value="<%=Formatacao.ajustaDataAMD(for3.getDatacadastro()) %>" type="date" placeholder="Data do Cadastro" name="datacadastro" required="true" class="form-control form-control-warning">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-1 form-control-label">Situação*</label>
                                <div class="col-sm-4 select">
                                    <select name="situacao" id="situacao" class="form-control" required="true" >
                                        <%
                                            if (for3.getSituacao() == 'I') {
                                        %>
                                        <option id="A" value="A">Ativo</option>
                                        <option id="I" value="I" selected>Inativo</option>
                                        <%
                                        } else {
                                        %>
                                        <option id="A" value="A" selected>Ativo</option>
                                        <option id="I" value="I">Inativo</option>
                                        <%}
                                        %>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-4 form-control-label">Campos com * são obrigatórios</label>
                            </div>
                            <div class="form-group row">       
                                <div class="col-sm-2 offset-sm-1">
                                    <input type="submit" value="Cadastrar" class="btn btn-primary">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<script>
    $("#situacao").val("<%= for3.getSituacao()%>");
</script>

