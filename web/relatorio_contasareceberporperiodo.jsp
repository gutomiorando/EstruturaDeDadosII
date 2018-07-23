<%-- 
    Document   : relatorio_contasareceberporperiodo
    Created on : 12/11/2017, 11:15:47
    Author     : Usuario
--%>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sistema Ipiranga</title>
</head>

<!-- Page Header-->
<header class="page-header">
    <div class="container-fluid">
        <h2 class="no-margin-bottom">Relatório de Contas a receber por Período</h2>
    </div>
</header>
<ul class="breadcrumb">
    <div class="container-fluid">
        <li class="breadcrumb-item"><a>Inicio</a></li>
        <li class="breadcrumb-item active">Relatório de Contas a receber por Período</li>
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
                        <h3 class="h4">Selecione o período de pagamento</h3>
                    </div>
                    <div class="card-body">
                        <!--<p>Lorem ipsum dolor sit amet consectetur.</p>-->
                        <form class="form-horizontal" action="acao?parametro=relatorio_contasareceberporperiodo" method="post">
                            <div class="form-group row">
                                <div class="col-sm-4 select">

                            <div class="form-group row">
                                <label class="col-sm-4 form-control-label">Data Inicial*</label>
                                <div class="col-sm-8">
                                    <input id="inicio" value="" type="date" placeholder="Data Inicial" name="inicio" required="true" class="form-control form-control-warning">
                                </div>
                            </div>
                                    <div class="form-group row">
                                <label class="col-sm-4 form-control-label">Data Final*</label>
                                <div class="col-sm-8">
                                    <input id="fim" value="" type="date" placeholder="Data Final" name="fim" required="true" class="form-control form-control-warning">
                                </div>
                            </div>
                                </div>
                            </div>     
                            <div class="form-group row-1">       
                                <input type="submit" value="Pesquisar Contas" href="acao?parametro=relatorio_contasareceberporperiodo" class="btn btn-primary">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>