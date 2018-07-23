
<%@page import="apoio.Formatacao"%>
<%@page import="entidade.Mercadoria"%>
<%
    Mercadoria merc3 = (Mercadoria) request.getAttribute("mercadoria");

    if (merc3 == null) {
        merc3 = new Mercadoria();
    }
    
%>

<script type="text/javascript">
    jQuery(function ($) 
    {
        $("#valorunitario").maskMoney({ decimal: '.', thousands: ',', precision: 2 }); 
    });
</script>

<!-- Page Header-->
<header class="page-header">
    <div class="container-fluid">
        <h2 class="no-margin-bottom">Cadastro de Mercadorias</h2>
    </div>
</header>
<ul class="breadcrumb">
    <div class="container-fluid">
        <li class="breadcrumb-item"><a>Inicio</a></li>
        <li class="breadcrumb-item active">Cadastro de Mercadorias</li>
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
                            <strong>Mercadoria cadastrada com sucesso!</strong> 
                        </div>
                        <%
                        } else if (confirma_cadastro.equalsIgnoreCase("erro")) {
                        %>
                        <div class="alert alert-danger" role="alert">
                            <strong>Atenção!</strong> Ocorreu um erro ao cadastrar Mercadoria!
                        </div>
                        <%
                            }
                        %>
                        <!--<p>Lorem ipsum dolor sit amet consectetur.</p>-->
                        <form class="form-horizontal" action="acao?parametro=cadastro_mercadoria" method="post">
                            <div class="form-group row">
                                <label class="col-sm-2 form-control-label">Código</label>
                                <div class="col-sm-4">
                                    <input id="codmercadoria" value="<%=merc3.getCodmercadoria()%>" readonly="true" type="text" placeholder="Código da Mercadoria" name="codmercadoria" class="form-control form-control-success">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 form-control-label">Descrição*</label>
                                <div class="col-sm-4">
                                    <input id="descricao" value="<%=merc3.getDescricao()%>" type="text" placeholder="Descrição" name="descricao" required="true" class="form-control form-control-warning">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 form-control-label">Valor Unitário*</label>
                                <div class="col-sm-4">
                                    <input id="valorunitario" value="<%=merc3.getValorunitario()%>" type="text" placeholder="Valor Unitário" name="valorunitario" required="true" class="form-control form-control-warning"  autocomplete="off">
                                </div>
                            </div>    
                            <div class="form-group row">
                                <label class="col-sm-2 form-control-label">Quantidade*</label>
                                <div class="col-sm-4">
                                    <input id="quantidade" value="<%=merc3.getQuantidade()%>" type="number" placeholder="Quantidade" name="quantidade" required="true" class="form-control form-control-warning">
                                </div>
                            </div>    
                            <div class="form-group row">
                                <label class="col-sm-2 form-control-label">Estoque Atual</label>
                                <div class="col-sm-4">
                                    <input id="estoque" value="<%=merc3.getEstoque()%>" readonly="true" type="number" placeholder="Estoque" name="estoque" class="form-control form-control-warning">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 form-control-label">Ultima Atual.</label>
                                <div class="col-sm-4">
                                    <input id="horaatualizacao" value="<%=Formatacao.ajustaDataAMD(merc3.getHoraatualizacao()) %>" readonly="true" type="date" placeholder="Hora da Atualizacao" name="horaatualizacao" required="true" class="form-control form-control-warning">
                                </div>
                            </div>    
                            <div class="form-group row">
                                <label class="col-sm-2 form-control-label">Tipo*</label>
                                <div class="col-sm-4 select">
                                    <select name="tipo" id="tipo" class="form-control" required="true" >
                                        <%
                                            if (merc3.getTipo() == 'C') {
                                        %>
                                        <option id="C" value="C" selected>Combustivel</option>
                                        <option id="B" value="B">Bebida</option>
                                        <option id="M" value="M">Comida</option>
                                        <option id="O" value="O">Outros</option>
                                        <%
                                        } else if (merc3.getTipo() == 'B') {
                                        %>
                                        <option id="C" value="C">Combustivel</option>
                                        <option id="B" value="B"selected>Bebida</option>
                                        <option id="M" value="M">Comida</option>
                                        <option id="O" value="O">Outros</option>
                                        <%
                                        } else if (merc3.getTipo() == 'M') {
                                        %>
                                        <option id="C" value="C">Combustivel</option>
                                        <option id="B" value="B">Bebida</option>
                                        <option id="M" value="M"selected>Comida</option>
                                        <option id="O" value="O">Outros</option>
                                        <%
                                        } else if (merc3.getTipo() == 'O') {
                                        %>
                                        <option id="C" value="C">Combustivel</option>
                                        <option id="B" value="B">Bebida</option>
                                        <option id="M" value="M">Comida</option>
                                        <option id="O" value="O"selected>Outros</option>
                                        <%}
                                        %>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 form-control-label">Situação*</label>
                                <div class="col-sm-4 select">
                                    <select name="situacao" id="situacao" class="form-control" required="true" >
                                        <%
                                            if (merc3.getSituacao() == 'I') {
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
    $("#situacao").val("<%= merc3.getSituacao()%>");
</script>

