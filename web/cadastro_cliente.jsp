
<%@page import="apoio.Formatacao"%>
<%@page import="entidade.Cidade"%>
<%@page import="dao.CidadeDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Cliente"%>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sistema Ipiranga</title>
</head>

<script type="text/javascript">
    jQuery(function ($) {
        //$("#data").mask("99/99/9999");         // Máscara para DATA
        $("#cnpj").mask("99.999.999/9999-99"); // Máscara para CNPJ
        $("#cpf_cnpj").mask("999.999.999-99");      // Máscara para CPF
    });
</script>   


<%
    Cliente cli3 = (Cliente) request.getAttribute("cliente");

    if (cli3 == null) {
        cli3 = new Cliente();
    }

%>

<!-- Page Header-->
<header class="page-header">
    <div class="container-fluid">
        <h2 class="no-margin-bottom">Cadastro de Clientes</h2>
    </div>
</header>
<ul class="breadcrumb">
    <div class="container-fluid">
        <li class="breadcrumb-item"><a>Inicio</a></li>
        <li class="breadcrumb-item active">Cadastro de Clientes</li>
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
                            <strong>Cliente cadastrado com sucesso!</strong> 
                        </div>
                        <%
                        } else if (confirma_cadastro.equalsIgnoreCase("erro")) {
                        %>
                        <div class="alert alert-danger" role="alert">
                            <strong>Atenção!</strong> Ocorreu um erro ao cadastrar Cliente!
                        </div>
                        <%
                            }
                        %>
                        <!--<p>Lorem ipsum dolor sit amet consectetur.</p>-->
                        <form class="form-horizontal" action="acao?parametro=cadastro_cliente" method="post">
                            <div class="form-group row">
                                <label class="col-sm-1 form-control-label">Código</label>
                                <div class="col-sm-4">
                                    <input id="codcli" value="<%=cli3.getCodcli()%>" readonly="true" type="text" placeholder="Código do Cliente" name="codcli" class="form-control form-control-success">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-1 form-control-label">Cidade*</label>
                                <div class="col-sm-4 select">

                                    <select name="codcid" id="codcid" class="form-control" required="true" >
                                        <!--<option id="F" value="F">Pessoa Física</option>
                                        <option id="J" value="J">Pessoa Jurídica</option>-->
                                        <%
                                            ArrayList<Object> cidades4 = new CidadeDAO().consultarCidadesAtivas();

                                            for (int i = 0; i < cidades4.size(); i++) {
                                                Cidade u2 = (Cidade) cidades4.get(i);
                                        %>
                                        <option id="<%= u2.getCodcid()%>" value="<%= u2.getCodcid()%>"><%= u2.getNome()%></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                            </div>   
                            <div class="form-group row">
                                <label class="col-sm-1 form-control-label">Tipo de Cadastro*</label>
                                <div class="col-sm-4 select">
                                    <select name="tipocadastro" id="tipocadastro" class="form-control" required="true" >
                                        <option id="F" value="F">Pessoa Física</option>
                                        <option id="J" value="J">Pessoa Jurídica</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-1 form-control-label">Nome*</label>
                                <div class="col-sm-4">
                                    <input id="nome" value="<%=cli3.getNome()%>" type="text" placeholder="Nome do Cliente" name="nome" required="true" class="form-control form-control-warning">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-1 form-control-label">Cpf ou Cnpj*</label>
                                <div class="col-sm-4">
                                    <input id="cpf_cnpj" value="<%=cli3.getCpfCnpj()%>" type="text" placeholder="Cpf ou Cnpj" name="cpf_cnpj" required="true" class="form-control form-control-warning">
                                </div>
                            </div>   
                            <div class="form-group row">
                                <label class="col-sm-1 form-control-label">Rua*</label>
                                <div class="col-sm-4">
                                    <input id="rua" value="<%=cli3.getRua()%>" type="text" placeholder="Rua" name="rua" required="true" class="form-control form-control-warning">
                                </div>
                            </div>   
                            <div class="form-group row">
                                <label class="col-sm-1 form-control-label">Numero*</label>
                                <div class="col-sm-4">
                                    <input id="numero" value="<%=cli3.getNumero()%>" type="number" placeholder="numero" name="numero" required="true" class="form-control form-control-warning">
                                </div>
                            </div>     
                            <div class="form-group row">
                                <label class="col-sm-1 form-control-label">Data Cadastro*</label>
                                <div class="col-sm-4">
                                    <input id="datacadastro" value="<%=Formatacao.ajustaDataAMD(cli3.getDatacadastro())%>" type="date" placeholder="Data do Cadastro" name="datacadastro" required="true" class="form-control form-control-warning">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-1 form-control-label">Situação*</label>
                                <div class="col-sm-4 select">
                                    <select name="situacao" id="situacao" class="form-control" required="true" >
                                        <%
                                            if (cli3.getSituacao() == 'I') {
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
    $("#situacao").val("<%= cli3.getSituacao()%>");
</script>

