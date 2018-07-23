
<%@page import="entidade.Funcionario"%>
<%
    Funcionario func3 = (Funcionario) request.getAttribute("funcionario");

    if (func3 == null) {
        func3 = new Funcionario();
    }

%>

<!-- Page Header-->
<header class="page-header">
    <div class="container-fluid">
        <h2 class="no-margin-bottom">Cadastro de Funcion�rios</h2>
    </div>
</header>
<ul class="breadcrumb">
    <div class="container-fluid">
        <li class="breadcrumb-item"><a>Inicio</a></li>
        <li class="breadcrumb-item active">Cadastro de Funcion�rios</li>
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
                        <h3 class="h4">Formul�rio de Cadastro</h3>
                    </div>
                    <div class="card-body">
                        <%    String confirma_cadastro = String.valueOf(request.getAttribute("confirma_cadastro"));
                            if (confirma_cadastro.equalsIgnoreCase("sucesso")) {
                        %>
                        <div class="alert alert-success" role="alert">
                            <strong>Sucesso!</strong> Funcion�rio cadastrado com sucesso.
                        </div>
                        <!--Limpa os campos-->
                        <script>
                            $('div.card-body').find('input').val('');
                        </script>
                        <%
                        } else if (confirma_cadastro.equalsIgnoreCase("erro")) {
                        %>
                        <div class="alert alert-danger" role="alert">
                            <strong>Opss!</strong> Ocorreu um erro ao cadastrar o Funcion�rio.
                        </div>
                        <%
                            }
                        %>

                        <!--MENSAGEM DE CONFIRMA��O DE EDI��O-->
                        <%
                            String confirma_edicao = String.valueOf(request.getAttribute("confirma_edicao"));
                            if (confirma_edicao.equalsIgnoreCase("sucesso")) {
                        %>
                        <div class="alert alert-success" role="alert">
                            <strong>Sucesso!</strong> As altera��es foram salvas.
                        </div>
                        <!--Limpa os campos-->
                        <script>
                            $('div.card-body').find('input').val('');
                        </script>
                        <%
                        } else if (confirma_edicao.equalsIgnoreCase("erro")) {
                        %>
                        <div class="alert alert-danger" role="alert">
                            <strong>Opss!</strong> Ocorreu um erro ao salvar as edi��es do Funcionario!
                        </div>
                        <%
                            }
                        %>
                        <!--<p>Lorem ipsum dolor sit amet consectetur.</p>-->
                        <form class="form-horizontal" action="acao?parametro=cadastro_funcionario" method="post">
                            <div class="form-group row">
                                <label class="col-sm-2 form-control-label">C�digo</label>
                                <div class="col-sm-4">
                                    <input id="codfunc" value="<%= func3.getCodfunc()%>" readonly="true" type="text" placeholder="C�digo do Funcion�rio" name="codfunc" class="form-control form-control-success">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 form-control-label">Nome Funcion�rio*</label>
                                <div class="col-sm-4">
                                    <input id="nome" value="<%= func3.getNome()%>" type="text" placeholder="Nome do Funcion�rio" name="nome" required="true" class="form-control form-control-warning">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 form-control-label">Data Admiss�o*</label>
                                <div class="col-sm-4">
                                    <input id="dataadmissao" value="<%=func3.getDataadmissao()%>" type="date" placeholder="Data Admiss�o" name="dataadmissao" required="true" class="form-control form-control-warning">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 form-control-label">Data Demiss�o</label>
                                <div class="col-sm-4">
                                    <input id="datademissao" value="<%=func3.getDatademissao()%>" type="date" placeholder="Data Demiss�o" name="datademissao" class="form-control form-control-warning">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 form-control-label">Funcao*</label>
                                <div class="col-sm-4">
                                    <input id="funcao" type="text" value="<%= func3.getFuncao()%>" placeholder="Funcao" name="funcao" required="true" class="form-control form-control-warning">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 form-control-label">Sal�rio*</label>
                                <div class="col-sm-4">
                                    <input id="salario" type="number" value="<%= func3.getSalario()%>" placeholder="Sal�rio" name="salario" required="true" class="form-control form-control-warning">
                                </div>
                            </div>    
                            <div class="form-group row">
                                <label class="col-sm-2 form-control-label">Situa��o*</label>
                                <div class="col-sm-4 select">
                                    <select id="situacao" name="situacao" class="form-control" required="true" >
                                        <%
                                            if (func3.getSituacao() == 'I') {
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
                                <label class="col-sm-4 form-control-label">Campos com * s�o obrigat�rios</label>
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
    $("#situacao").val("<%= func3.getSituacao()%>");
</script>

