

<%@page import="entidade.Cliente"%>
<%@page import="dao.ClienteDAO"%>
<%@page import="dao.UsuarioDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Usuario"%>
<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<script type="text/javascript">
    function getXMLHttpRequestObject()
    {
        var objetoAjax;

        if (window.XMLHttpRequest)
        { // tenta carregar componente Mozilla/safari
            objetoAjax = new XMLHttpRequest();
        } else if (window.ActiveXObject)
        { // se falhar, tenta carregar o ActiveX do IE :-(
            try
            {
                objetoAjax = new ActiveXObject("Msxml2.XMLHTTP");
            } catch (e)
            {
                try
                {
                    objetoAjax = new ActiveXObject("Microsoft.XMLHTTP");
                } catch (ex)
                {
                    objetoAjax = false;
                }
            }
        }
        return objetoAjax;
    }

    function filtrarUsuarioModelo()
    {
        var http = new getXMLHttpRequestObject();

        campo_pesquisa = document.getElementById("campo_pesquisa").value;

        http.onreadystatechange = function ()
        { //Handler function for call back on state change.
            if (http.readyState == 4)
            {
                document.getElementById("tabela_usuario_modelo").innerHTML = this.responseText;
            }
        }

        http.open("GET", "listagem_vendasporcliente_tabela.jsp?pesquisa=" + campo_pesquisa + "&origem=relatorio", true);
        http.send();
        event.preventDefault();
    }
    function enterPesquisaCliente(event)
    {
        if (event.keyCode == 13)
        {
            filtrarUsuarioModelo();
        }
    }
    function enterPesquisaUsuario(event)
    {
        if (event.keyCode == 13)
        {
            filtrarUsuarioModelo();
        }
    }
</script>
<html>
    <head>
        <meta charset="utf-8"> <!--<meta charset="utf-8">-->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Sistema Ipiranga</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="robots" content="all,follow">
        <!-- Bootstrap CSS-->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <!-- Google fonts - Roboto -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins:300,400,700">
        <!-- theme stylesheet-->
        <link rel="stylesheet" href="css/style.default.css" id="theme-stylesheet">
        <!-- Custom stylesheet - for your changes-->
        <link rel="stylesheet" href="css/custom.css">
        <!-- Favicon-->
        <link rel="shortcut icon" href="img/favicon.ico">
        <!-- Font Awesome CDN-->
        <!-- you can replace it by local Font Awesome-->
        <script src="https://use.fontawesome.com/99347ac47f.js"></script>
        <script src="js/99347ac47f.js"></script>
        <script src="js/jquery2.1.min.js"></script>
        <!-- Font Icons CSS-->
        <link rel="stylesheet" href="css/icons2.css">
        <link href="css/icons1.css" rel="stylesheet">
        <script src="js/jquery1.1.min.js"></script>
        <script src="js/tether.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.cookie.js"></script>
        <script src="js/jquery.validate.min.js"></script>
        <script src="js/Chart.min.js"></script>
        <script src="js/charts-home.js"></script>
        <script src="js/front.js"></script>
        <script src="js/jquery.maskedinput.js"></script>
        <script src="js/sweetalert2.all.min.js"></script>
        <script src="js/jquery.maskMoney.min.js"></script>
        <!--<link rel="stylesheet" href="https://file.myfontastic.com/da58YPMQ7U5HY8Rb6UxkNf/icons.css">-->
        
        <script type="text/javascript">
            jQuery(function ($) {
                //$("#data").mask("99/99/9999");         // Máscara para DATA
                $("#cnpj").mask("99.999.999/9999-99"); // Máscara para CNPJ
                $("#cpf").mask("999.999.999-99");      // Máscara para CPF
                //$("#valorRecebido").maskMoney({decimal: '.', thousands: ',', precision: 2});
                //$("#desconto").maskMoney({decimal: '.', thousands: ',', precision: 2});
                //$("#valor_compra").maskMoney({decimal: '.', thousands: ',', precision: 2});
                $("#valor_total").maskMoney({decimal: '.', thousands: ',', precision: 2});
            });
        </script>

    </head>
    <body>
        <%
            Usuario user = new Usuario();

            String pagina = String.valueOf(request.getAttribute("destino"));
            System.out.println("Página que estou querendo acessar: " + pagina);

            //Controle de sessão
            HttpSession sessao = ((HttpServletRequest) request).getSession();
            if (sessao.getAttribute("usuarioLogado") == null) {
                ((HttpServletResponse) response).sendRedirect("index.jsp");
            }


        %>

        <div class="page home-page">
            <!-- Main Navbar-->
            <header class="header">
                <nav class="navbar">
                    <!-- Search Box-->
                    <div class="search-box">
                        <button class="dismiss"><i class="icon-close"></i></button>
                        <form id="searchForm" action="#" role="search">
                            <input type="search" placeholder="What are you looking for..." class="form-control">
                        </form>
                    </div>
                    <div class="container-fluid">
                        <div class="navbar-holder d-flex align-items-center justify-content-between">
                            <!-- Navbar Header-->
                            <div class="navbar-header">
                                <!-- Navbar Brand --><a href="acao?parametro=home" class="navbar-brand">
                                    <div class="brand-text brand-big hidden-lg-down"><span>Sistema </span><strong>Ipiranga</strong></div>
                                    <div class="brand-text brand-small"><strong>SI</strong></div></a>
                                <!-- Toggle Button--><a id="toggle-btn" href="acao?parametro=home" class="menu-btn active"><span></span><span></span><span></span></a>
                            </div>
                            <!-- Navbar Menu -->
                            <ul class="nav-menu list-unstyled d-flex flex-md-row align-items-md-center">
                                <!-- Search-->
                                <!--<li class="nav-item d-flex align-items-center"><a id="search" href="#"><i class="icon-search"></i></a></li>-->
                                <!-- Notifications-->
                                <!--<li class="nav-item dropdown"> <a id="notifications" rel="nofollow" data-target="#" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link"><i class="fa fa-bell-o"></i><span class="badge bg-red">12</span></a>
                                    <ul aria-labelledby="notifications" class="dropdown-menu">
                                        <li><a rel="nofollow" href="#" class="dropdown-item"> 
                                                <div class="notification">
                                                    <div class="notification-content"><i class="fa fa-envelope bg-green"></i>You have 6 new messages </div>
                                                    <div class="notification-time"><small>4 minutes ago</small></div>
                                                </div></a></li>
                                        <li><a rel="nofollow" href="#" class="dropdown-item"> 
                                                <div class="notification">
                                                    <div class="notification-content"><i class="fa fa-twitter bg-blue"></i>You have 2 followers</div>
                                                    <div class="notification-time"><small>4 minutes ago</small></div>
                                                </div></a></li>
                                        <li><a rel="nofollow" href="#" class="dropdown-item"> 
                                                <div class="notification">
                                                    <div class="notification-content"><i class="fa fa-upload bg-orange"></i>Server Rebooted</div>
                                                    <div class="notification-time"><small>4 minutes ago</small></div>
                                                </div></a></li>
                                        <li><a rel="nofollow" href="#" class="dropdown-item"> 
                                                <div class="notification">
                                                    <div class="notification-content"><i class="fa fa-twitter bg-blue"></i>You have 2 followers</div>
                                                    <div class="notification-time"><small>10 minutes ago</small></div>
                                                </div></a></li>
                                        <li><a rel="nofollow" href="#" class="dropdown-item all-notifications text-center"> <strong>view all notifications                                            </strong></a></li>
                                    </ul>
                                </li>-->
                                <!-- Messages                        -->
                                <!--<li class="nav-item dropdown"> <a id="messages" rel="nofollow" data-target="#" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link"><i class="fa fa-envelope-o"></i><span class="badge bg-orange">10</span></a>
                                    <ul aria-labelledby="notifications" class="dropdown-menu">
                                        <li><a rel="nofollow" href="#" class="dropdown-item d-flex"> 
                                                <div class="msg-profile"> <img src="img/avatar-1.jpg" alt="..." class="img-fluid rounded-circle"></div>
                                                <div class="msg-body">
                                                    <h3 class="h5">Jason Doe</h3><span>Sent You Message</span>
                                                </div></a></li>
                                        <li><a rel="nofollow" href="#" class="dropdown-item d-flex"> 
                                                <div class="msg-profile"> <img src="img/avatar-2.jpg" alt="..." class="img-fluid rounded-circle"></div>
                                                <div class="msg-body">
                                                    <h3 class="h5">Frank Williams</h3><span>Sent You Message</span>
                                                </div></a></li>
                                        <li><a rel="nofollow" href="#" class="dropdown-item d-flex"> 
                                                <div class="msg-profile"> <img src="img/avatar-3.jpg" alt="..." class="img-fluid rounded-circle"></div>
                                                <div class="msg-body">
                                                    <h3 class="h5">Ashley Wood</h3><span>Sent You Message</span>
                                                </div></a></li>
                                        <li><a rel="nofollow" href="#" class="dropdown-item all-notifications text-center"> <strong>Read all messages    </strong></a></li>
                                    </ul>
                                </li>-->
                                <!-- Logout    -->
                                <li class="nav-item"><a href="/SistemaIpiranga/acao?parametro=sair" class="nav-link logout">Sair<i class="fa fa-sign-out"></i></a></li>
                            </ul>
                        </div>
                    </div>
                </nav>
            </header>
            <div class="page-content d-flex align-items-stretch">
                <!-- Side Navbar -->
                <nav class="side-navbar">
                    <!-- Sidebar Header-->
                    <div class="sidebar-header d-flex align-items-center">
                        <!--<div class="avatar"><img src="img/avatar-1.jpg" alt="..." class="img-fluid rounded-circle"></div>-->
                        <div class="title">
                            <h1 class="h4">Administrador</h1>
                            <!--<p>Web Designer</p>-->
                        </div>
                    </div>
                    <!-- Sidebar Navidation Menus--><span class="heading">Menu</span>
                    <ul class="list-unstyled">
                        <li class="active"> <a href="acao?parametro=venda_cadastro"><i class="icon-home"></i>Inicio</a></li>
                        <li><a href="#vendas" aria-expanded="false" data-toggle="collapse"> <i class="icon-interface-windows"></i>Vendas</a>
                            <ul id="vendas" class="collapse list-unstyled">
                                <li><a href="acao?parametro=venda_cadastro">Inserir Vendas</a></li>
                                <!--<li><a href="acao?parametro=relatorio_vendasporcliente">Vendas por Cliente</a></li>-->
                                <li><a href="acao?parametro=relatorio_vendasporperiodo" target="_blank">Vendas por Periodo</a></li>
                                <li><a href="acao?parametro=vendas_realizadas" target="_blank">Vendas Realizadas</a></li>
                            </ul>
                        </li>
                        <li><a href="#dashvariants" aria-expanded="false" data-toggle="collapse"> <i class="icon-interface-windows"></i>Cadastros</a>
                            <ul id="dashvariants" class="collapse list-unstyled">
                                <li><a href="acao?parametro=cadastro_usuario">Usuários</a></li>
                                <li><a href="acao?parametro=cadastro_cidade">Cidades</a></li>
                                <li><a href="acao?parametro=cadastro_cliente">Clientes</a></li>
                                <li><a href="acao?parametro=cadastro_contasapagar">Contas a Pagar</a></li>
                                <li><a href="acao?parametro=cadastro_fornecedor">Fornecedores</a></li>
                                <li><a href="acao?parametro=cadastro_funcionario">Funcionários</a></li>
                                <li><a href="acao?parametro=cadastro_mercadoria">Mercadorias</a></li>
                            </ul>
                        </li>
                        <li><a href="#listas" aria-expanded="false" data-toggle="collapse"> <i class="icon-list"></i>Listagens</a>
                            <ul id="listas" class="collapse list-unstyled">
                                <li><a href="acao?parametro=listagem_usuario">Lista de Usuários</a></li>
                                <li><a href="acao?parametro=listagem_cidade">Lista de Cidades</a></li>
                                <li><a href="acao?parametro=listagem_fornecedor">Lista de Fornecedores</a></li>
                                <li><a href="acao?parametro=listagem_mercadoria">Lista de Mercadorias</a></li>
                                <li><a href="acao?parametro=listagem_cliente">Lista de Clientes</a></li>
                                <li><a href="acao?parametro=listagem_contasapagar">Lista de Contas a Pagar</a></li>
                                <li><a href="acao?parametro=listagem_contasareceber">Lista de Contas a Receber</a></li>
                            </ul>
                        </li>
                        <!--<li> <a href="tables.html"> <i class="icon-grid"></i>Tables </a></li>-->
                        <!--<li> <a href="charts.html"> <i class="fa fa-bar-chart"></i>Charts </a></li>-->
                        <li><a href="#relatorios" aria-expanded="false" data-toggle="collapse"> <i class="icon-list"></i>Relatorios</a>
                            <ul id="relatorios" class="collapse list-unstyled">
                                <li><a href="listagem_mercadorias2.jsp" target="_blank">Relatorio de Mercadorias</a></li>
                                <li><a href="acao?parametro=relatorio_clienteporcidade" target="_blank" >Clientes por Cidade</a></li>
                                <li><a href="acao?parametro=relatorio_funcionariosporadmissao" target="_blank">Funcionarios por Admissao</a></li>
                                <li><a href="acao?parametro=relatorio_contasapagarporperiodo" target="_blank">Contas a pagar por periodo</a></li>
                                <li><a href="acao?parametro=relatorio_contasapagarporfornecedor" target="_blank">Contas a pagar por Fornecedor</a></li>
                                <li><a href="acao?parametro=relatorio_contasareceberporperiodo" target="_blank">Contas a receber por Periodo</a></li>
                            </ul>
                        </li>
                        <!--<li> <a href="login.html"> <i class="icon-interface-windows"></i>Login Page</a></li>--> 
                    </ul>
                    <!--<span class="heading">Extras</span>
                    <ul class="list-unstyled">
                        <li> <a href="#"> <i class="icon-flask"></i>Demo </a></li>
                        <li> <a href="#"> <i class="icon-screen"></i>Demo </a></li>
                        <li> <a href="#"> <i class="icon-mail"></i>Demo </a></li>
                        <li> <a href="#"> <i class="icon-picture"></i>Demo </a></li>
                    </ul>
                    -->
                </nav>

                <div class="content-inner">
                    <%-- AQUIIIIIIII COMEÇA O CONTEÚDO --%>

                    <jsp:include page="<%= pagina%>" ></jsp:include>
                    <%-- AQUI ACABA --%>
                    <!-- Page Footer-->
                    <!--                    <footer class="main-footer">
                                            <div class="container-fluid">
                                                <div class="row">
                                                    <div class="col-sm-6">
                                                        <p>Your company &copy; 2017-2019</p>
                                                    </div>
                                                    <div class="col-sm-6 text-right">
                                                        <p>Desenvolvido por <a>Gustavo JM</a></p>
                                                         Please do not remove the backlink to us unless you support further theme's development at https://bootstrapious.com/donate. It is part of the license conditions. Thank you for understanding :)
                                                    </div>
                                                </div>
                                            </div>
                                        </footer>-->
                </div>
            </div>
        </div>
        <!-- Javascript files-->
<!--        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js" type="text/javascript" charset="utf-8"></script>
        <script src="js/tether.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.cookie.js"></script>
        <script src="js/jquery.validate.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
        <script src="js/charts-home.js"></script>
        <script src="js/front.js"></script>
        <script src="js/csv.js" type="text/javascript" charset="utf-8"></script>
    </body>
</html>