<%-- 
    Document   : relatorio_vendasporcliente
    Created on : 31/10/2017, 10:32:07
    Author     : Usuario
--%>

<%@page import="entidade.Venda"%>
<%@page import="dao.VendaDAO"%>
<%@page import="dao.ClienteDAO"%>
<%@page import="dao.MercadoriaDAO"%>
<%@page import="apoio.Formatacao"%>
<%@page import="entidade.Cidade"%>
<%@page import="dao.CidadeDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Cliente"%>

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

    function filtrarUsuario()
    {
        var http = new getXMLHttpRequestObject();

        pesquisa = document.getElementById("pesquisa").value;
        origem = "cliente";

        http.onreadystatechange = function ()
        { //Handler function for call back on state change.
            if (http.readyState === 4)
            {
                document.getElementById("tabela_usuario").innerHTML = this.responseText;
            }
        }

        http.open("GET", "listagem_vendasporcliente_tabela.jsp?pesquisa=" + pesquisa + "&origem=" + origem, true);
        http.send();
        event.preventDefault();
    }
    function enterPesquisa(event)
    {
        if (event.keyCode === 13)
        {
            filtrarUsuario();
        }
    }
</script>

<%
    ArrayList<Object> vendaDAO = new VendaDAO().consultarTodos();
    ArrayList<Object> clienteDAO = new ClienteDAO().consultarTodos();

    if (request.getAttribute("csv_usuario_sucesso") != null) {
        if (request.getAttribute("csv_usuario_sucesso").equals("sucesso")) {
%>
<script type="text/javascript">
    swal("Relatório gerado com sucesso!", "O arquivo foi salvo na área de trabalho!", "success")
</script>
<%
} else {
%>
<script type="text/javascript">
    swal('Oops...', 'Ocorreu um erro ao gerar o relatório CSV de Vendas.<br>Entre em contato com o adminsitrador do sistema.', 'error')
</script>
<%
        }
    }
%>



<%
//    ArrayList<Object> clienteDAO = new ClienteDAO().consultarTodos();
//    ArrayList<Object> vendaDAO = new VendaDAO().consultarTodos();
//    if (request.getAttribute("pesquisa") != null)
//    {
//        vendaDAO = (ArrayList) request.getAttribute("pesquisa");
//    }
%>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sistema Ipiranga</title>
</head>

<!-- Page Header-->
<header class="page-header">
    <div class="container-fluid">
        <h2 class="no-margin-bottom">Relatório Vendas</h2>
    </div>
</header>
<ul class="breadcrumb">
    <div class="container-fluid">
        <li class="breadcrumb-item"><a>Inicio</a></li>
        <li class="breadcrumb-item active">Relatório Vendas</li>
    </div>
</ul>
<!-- Forms Section-->

<section class="tables">   
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-header d-flex align-items-center">
                        <h3 class="h4">Listagem de Vendas</h3>
                    </div>
                    <div class="card-body">
                        <!--MENSAGEM DE CONFIRMAÇÃO PARA NOVOS CADASTROS-->
                        <%    String confirma_exclusao = String.valueOf(request.getAttribute("confirma_exclusao"));
                            if (confirma_exclusao.equalsIgnoreCase("sucesso")) {
                        %>
                        <div class="alert alert-success" role="alert">
                            <strong>Sucesso!</strong> A venda foi inativada.
                        </div>
                        <%
                        } else if (confirma_exclusao.equalsIgnoreCase("erro")) {
                        %>
                        <div class="alert alert-danger" role="alert">
                            <strong>Opss!</strong> Ocorreu um erro ao inativar a venda.
                        </div>
                        <%
                            }
                        %>
                        <form id="pesquisa_usuario" action="" onsubmit="filtrarUsuario()" name="pesquisa_usuario" class="form-horizontal">
                            <div class="form-group">
                                <div class="input-group">
                                    <input type="text" id="pesquisa" onkeypress="enterPesquisa(event)" name="pesquisa" class="form-control">
                                    <span class="input-group-btn">
                                        <button type="submit" class="btn btn-primary">Pesquisar</button>
                                    </span>
                                </div>
                            </div>
                        </form>
                        <div id="tabela_usuario">
                            <table class="table table-striped table-sm" id="tabela_usu">
                                <thead>
                                    <tr>
                                        <th>Código</th>
                                        <th>Valor Total</th>
                                        <th>Data</th>
                                        <th>Situação</th>
                                        <th></th>
                                    </tr>
                                </thead>
                          <tbody>
                                <%
                                    for (int i = 0; i < vendaDAO.size(); i++)
                                    {
                                        Venda venda = (Venda) vendaDAO.get(i);
                                        ClienteDAO clienteDAO2 = new ClienteDAO();
                                        Cliente cliente = (Cliente) clienteDAO2.consultarId(venda.getCliente().getCodcli());
                                %>
                                <tr>
                                    <td scope="row"><b><%=venda.getCodvenda()%></b></td>
                                    <td><%=venda.getCliente().getNome() %></td>
                                    <td><%=venda.getDataemissao()%></td>
                                    <td><%=venda.getValortotal()%></td>
                                    <td><%=venda.getSituacaovenda()%></td>
                                    <td><a href="acao?parametro=venda_detalhes&id=<%= venda.getCodvenda()%>">Detalhes</a></td>
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
        <!-- Inline Form-->
        <div class="col-lg-9">   
            <button id="export" data-export="export" class="btn btn-primary" data-icon="l"> Gerar CSV com filtro</button>
        </div>
    </div>
</section>
<script src="js/jquery.tabletoCSV.js"></script>
<script type="text/javascript">
                                        $(function () {
                                            $("#export").click(function () {
                                                $("#tabela_usu").tableToCSV();
                                            });
                                        });
</script>            
