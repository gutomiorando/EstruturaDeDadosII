
<%@page import="entidade.Usuario"%>
<%
    Usuario user3 = (Usuario) request.getAttribute("usuario");

    if (user3 == null) {
        user3 = new Usuario();
    }

%>

<!-- Page Header-->
<header class="page-header">
    <div class="container-fluid">
        <h2 class="no-margin-bottom">Cadastro de Usuários</h2>
    </div>
</header>
<ul class="breadcrumb">
    <div class="container-fluid">
        <li class="breadcrumb-item"><a>Inicio</a></li>
        <li class="breadcrumb-item active">Cadastro de Usuários</li>
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
                        <%    String confirma_cadastro = String.valueOf(request.getAttribute("confirma_cadastro"));
                            if (confirma_cadastro.equalsIgnoreCase("sucesso")) {
                        %>
                        <div class="alert alert-success" role="alert">
                            <strong>Sucesso!</strong> Usuário cadastrado com sucesso.
                        </div>
                        <!--Limpa os campos-->
                        <script>
                            $('div.card-body').find('input').val('');
                        </script>
                        <%
                        } else if (confirma_cadastro.equalsIgnoreCase("erro")) {
                        %>
                        <div class="alert alert-danger" role="alert">
                            <strong>Opss!</strong> Ocorreu um erro ao cadastrar o usuário.
                        </div>
                        <%
                            }
                        %>

                        <!--MENSAGEM DE CONFIRMAÇÃO DE EDIÇÃO-->
                        <%
                            String confirma_edicao = String.valueOf(request.getAttribute("confirma_edicao"));
                            if (confirma_edicao.equalsIgnoreCase("sucesso")) {
                        %>
                        <div class="alert alert-success" role="alert">
                            <strong>Sucesso!</strong> As alterações foram salvas.
                        </div>
                        <!--Limpa os campos-->
                        <script>
                            $('div.card-body').find('input').val('');
                        </script>
                        <%
                        } else if (confirma_edicao.equalsIgnoreCase("erro")) {
                        %>
                        <div class="alert alert-danger" role="alert">
                            <strong>Opss!</strong> Ocorreu um erro ao salvar as edições do usuário!
                        </div>
                        <%
                            }
                        %>
                        <!--<p>Lorem ipsum dolor sit amet consectetur.</p>-->
                        <form class="form-horizontal" action="acao?parametro=cadastro_usuario" method="post">
                            <div class="form-group row">
                                <label class="col-sm-1 form-control-label">Código</label>
                                <div class="col-sm-4">
                                    <input id="codusuario" value="<%= user3.getCodusuario()%>" readonly="true" type="text" placeholder="Código do Usuário" name="codusuario" class="form-control form-control-success">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-1 form-control-label">Nome Usuário*</label>
                                <div class="col-sm-4">
                                    <input id="nomeusuario" value="<%= user3.getNomeusuario()%>" type="text" placeholder="Nome Usuário" name="nomeusuario" required="true" class="form-control form-control-warning">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-1 form-control-label">Email*</label>
                                <div class="col-sm-4">
                                    <input id="email" type="email" value="<%= user3.getEmail()%>" placeholder="Email" name="email" required="true" class="form-control form-control-warning">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-1 form-control-label">Senha*</label>
                                <div class="col-sm-4">
                                    <input id="senha" type="password" value="<%= user3.getSenha()%>" placeholder="Senha" name="senha" required="true" class="form-control form-control-warning">
                                </div>
                            </div>
                            <!--
                            <div class="form-group row">
                                <label class="col-sm-1 form-control-label">Tipo de Usuário *</label>
                                <div class="col-sm-4 select">
                                    <select name="tipo_usuario" class="form-control" required="true" >
                                        <option selected="true" value="" >Selecione</option>
                                        <option value="a">Adminsitrador</option>
                                        <option value="o">Operador</option>
                                        <option value="c">Cliente</option>
                                        <option value="f">Fornecedor</option>
                                    </select>
                                </div>
                            </div>
                            -->
                            <!--
                            <div class="row">
                                <label class="col-sm-1 form-control-label">Dados do Usuário</label>
                                <div class="col-sm-4">
                                    <div class="form-group-material">
                                        <input id="nome" type="text" name="nome" required="true" class="input-material">
                                        <label for="nome" class="label-material">Nome *</label>
                                    </div>
                                    <div class="form-group-material">
                                        <input id="email" type="email" name="email" class="input-material">
                                        <label for="email" class="label-material">Email Address</label>
                                    </div>
                                    <div class="form-group-material">
                                        <input id="telefone" type="tel" name="telefone"  class="input-material"><small>Exemplo: 51992789845</small>
                                        <label for="telefone" class="label-material">Telefone</label>
                                    </div>
                                    <div class="form-group-material">
                                        <input id="data_nascimento" type="date" name="data_nascimento" class="input-material" value="" >
                                        <label for="data_nascimento" class="label-material">Data de Nascimento</label>
                                    </div>
                                    <div class="form-group-material">
                                        <input id="cpf" type="text" name="cpf" class="input-material">
                                        <label for="cpf" class="label-material">CPF</label>
                                    </div>
                                    <div class="form-group-material">
                                        <input id="cnpj" type="text" name="cnpj" class="input-material">
                                        <label for="cnpj" class="label-material">CNPJ</label>
                                    </div>
                                    <div class="form-group-material">
                                        <input id="rg" type="text" name="rg" class="input-material">
                                        <label for="rg" class="label-material">RG</label>
                                    </div>
                                </div>
                            </div>
                            -->
                            <div class="form-group row">
                                <label class="col-sm-1 form-control-label">Situação*</label>
                                <div class="col-sm-4 select">
                                    <select id="situacao" name="situacao" class="form-control" required="true" >
                                        <%
                                            if (user3.getSituacao() == 'I') {
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
    $("#situacao").val("<%= user3.getSituacao()%>");
</script>

