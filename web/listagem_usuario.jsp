<%-- 
    Document   : listagem_usuario
    Created on : 17/09/2017, 21:35:41
    Author     : Usuario
--%>

<%@page import="entidade.Usuario"%>
<%@page import="dao.UsuarioDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        origem = "usuario";

        http.onreadystatechange = function ()
        { //Handler function for call back on state change.
            if (http.readyState === 4)
            {
                document.getElementById("tabela_usuario").innerHTML = this.responseText;
            }
        }

        http.open("GET", "listagem_usuario_tabela.jsp?pesquisa=" + pesquisa + "&origem=" + origem, true);
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
    ArrayList<Object> usuarioDAO = new UsuarioDAO().consultarTodos();

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
    swal('Oops...', 'Ocorreu um erro ao gerar o relatório CSV de usuários.<br>Entre em contato com o adminsitrador do sistema.', 'error')
</script>
<%
        }
    }
%>

<!-- Page Header-->
<header class="page-header">
    <div class="container-fluid">
        <h2 class="no-margin-bottom">Listagem de Usuários</h2>
    </div>
</header>
<!-- Breadcrumb-->
<ul class="breadcrumb">
    <div class="container-fluid">
        <li class="breadcrumb-item"><a>Inicio</a></li>
        <li class="breadcrumb-item active">Listagem</li>
    </div>
</ul>
<section class="tables">   
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-close">
                        <!--<div class="dropdown">
                            <button type="button" id="closeCard" data-toggle="dropdown" aria-haspopup="true" 
                                    aria-expanded="false" class="dropdown-toggle"><i class="fa fa-ellipsis-v"></i></button>
                            <div aria-labelledby="closeCard" class="dropdown-menu has-shadow">
                        <!--<a href="#" class="dropdown-item remove"> <i class="fa fa-times"></i>Close</a>
                        <a href="#" class="dropdown-item edit"> <i class="fa fa-gear"></i>Edit</a>
                    </div>
                </div>-->
                    </div>
                    <div class="card-header d-flex align-items-center">
                        <h3 class="h4">Listagem</h3>
                    </div>
                    <div class="card-body">
                        <!--MENSAGEM DE CONFIRMAÇÃO PARA NOVOS CADASTROS-->
                        <%    String confirma_exclusao = String.valueOf(request.getAttribute("confirma_exclusao"));
                            if (confirma_exclusao.equalsIgnoreCase("sucesso")) {
                        %>
                        <div class="alert alert-success" role="alert">
                            <strong>Sucesso!</strong> O usuário foi inativado.
                        </div>
                        <%
                        } else if (confirma_exclusao.equalsIgnoreCase("erro")) {
                        %>
                        <div class="alert alert-danger" role="alert">
                            <strong>Opss!</strong> Ocorreu um erro ao excluir o usuário.
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
                            <table class="table table-striped table-sm"  id="tabela_usu">
                                <thead>
                                    <tr>
                                        <th>Código</th>
                                        <th>Nome</th>
                                        <th>Email</th>
                                        <th>Situação</th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                </thead>

                                <tbody>
                                    <%
                                        //                                   ArrayList<Object> usuarioDAO = new UsuarioDAO().consultarTodos();
                                        for (int i = 0; i < usuarioDAO.size(); i++) {
                                            Usuario usuario = (Usuario) usuarioDAO.get(i);
                                    %>
                                    <tr>
                                        <td scope="row"><%= usuario.getCodusuario()%></td>
                                        <td><%= usuario.getNomeusuario()%></td>
                                        <td><%= usuario.getEmail()%></td>
                                        <td><%= usuario.getSituacao()%></td>
                                        <td><a href="acao?parametro=editar_usuario&id=<%= usuario.getCodusuario()%>">Editar</span></a></td>
                                        <td><a href="acao?parametro=excluir_usuario&id=<%= usuario.getCodusuario()%>">Excluir</span></a></td>
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
            <button id="export" data-export="export" class="btn btn-primary" data-icon="l"> Gerar CSV </button>
            <!--<a href="acao?parametro=csv_usuario" style="text-decoration:none;"><button type="submit" class="btn btn-primary" data-icon="l"> Gerar CSV com Todos Usuários</button></a>-->
        </div>                            
    </div>
</section>
<script src="js/csv.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    $(function () {
        $("#export").click(function () {
            $("#tabela_usu").tableToCSV();
        });
    });
</script>                                
