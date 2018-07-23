
<%@page import="entidade.Fornecedor"%>
<%@page import="dao.FornecedorDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Contasapagar"%>
<%
    Contasapagar cont3 = (Contasapagar) request.getAttribute("contasapagar");

    if (cont3 == null) {
        cont3 = new Contasapagar();
    }

%>
<!-- Page Header-->
<header class="page-header">
    <div class="container-fluid">
        <h2 class="no-margin-bottom">Cadastro de Contas a pagar</h2>
    </div>
</header>
<ul class="breadcrumb">
    <div class="container-fluid">
        <li class="breadcrumb-item"><a>Inicio</a></li>
        <li class="breadcrumb-item active">Cadastro de Contas a pagar</li>
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
                            <strong>Conta cadastrada com sucesso!</strong> 
                        </div>
                        <%
                        } else if (confirma_cadastro.equalsIgnoreCase("erro")) {
                        %>
                        <div class="alert alert-danger" role="alert">
                            <strong>Atenção!</strong> Ocorreu um erro ao cadastrar uma Conta!
                        </div>
                        <%
                            }
                        %>
                        <!--<p>Lorem ipsum dolor sit amet consectetur.</p>-->
                        <form class="form-horizontal" action="acao?parametro=cadastro_contasapagar" method="post">
                            <div class="form-group row">
                                <label class="col-sm-2 form-control-label">Código</label>
                                <div class="col-sm-4">
                                    <input id="idconta" value="<%=cont3.getIdconta()%>" readonly="true" type="text" placeholder="Código da Conta" name="idconta" class="form-control form-control-success">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 form-control-label">Fornecedor*</label>
                                <div class="col-sm-4 select">

                                    <select name="codfornecedor" id="codfornecedor" class="form-control" required="true" >
                                        <%
                                            ArrayList<Object> fornecedor4 = new FornecedorDAO().consultarFornecedorAtivo();

                                            for (int i = 0; i < fornecedor4.size(); i++) {
                                                Fornecedor f2 = (Fornecedor) fornecedor4.get(i);
                                        %>
                                        <option id="<%= f2.getCodfornecedor()%>" value="<%= f2.getCodfornecedor()%>"><%= f2.getNome()%></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                            </div>  
                            <div class="form-group row">
                                <label class="col-sm-2 form-control-label">Descrição*</label>
                                <div class="col-sm-4">
                                    <input id="descricao" value="<%=cont3.getDescricao()%>" type="text" placeholder="Descrição" name="descricao" required="true" class="form-control form-control-warning">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 form-control-label">Data Prevista*</label>
                                <div class="col-sm-4">
                                    <input id="dataprevista" value="<%=cont3.getDataprevista()%>" type="date" placeholder="Data Prevista" name="dataprevista" required="true" class="form-control form-control-warning">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 form-control-label">Data de Pagamento</label>
                                <div class="col-sm-4">
                                    <input id="datapagamento" value="<%=cont3.getDatapagamento()%>" type="date" placeholder="Data Pagamento" name="datapagamento" class="form-control form-control-warning">
                                </div>
                            </div>    
                            <div class="form-group row">
                                <label class="col-sm-2 form-control-label">Valor Total*</label>
                                <div class="col-sm-4">
                                    <input id="valortotal" value="<%=cont3.getValortotal()%>" type="number" placeholder="Valor Total" name="valortotal" required="true" class="form-control form-control-warning">
                                </div>
                            </div>      
                            <div class="form-group row">
                                <label class="col-sm-2 form-control-label">Situação*</label>
                                <div class="col-sm-4 select">
                                    <select name="situacao" id="situacao" class="form-control" required="true" >
                                        <%
                                            if (cont3.getSituacao() == 'I') {
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
    $("#situacao").val("<%= cont3.getSituacao()%>");
</script>

