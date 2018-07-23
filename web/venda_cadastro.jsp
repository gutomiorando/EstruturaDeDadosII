<%-- 
    Document   : venda_cadastro
    Created on : 29/10/2017, 15:24:06
    Author     : Usuario
--%>

<%@page import="controle.ControlaVendaProdutos"%>
<%@page import="entidade.Mercadoria"%>
<%@page import="dao.MercadoriaDAO"%>
<%@page import="dao.ClienteDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Usuario"%>
<%@page import="dao.UsuarioDAO"%>
<%@page import="entidade.Cliente"%>
<script type="text/javascript">
            jQuery(function ($) {
                //$("#data").mask("99/99/9999");         // Máscara para DATA
                //$("#cnpj").mask("99.999.999/9999-99"); // Máscara para CNPJ
                $("#cpf_cnpj").mask("999.999.999-99");      // Máscara para CPF
                //$("#valorRecebido").maskMoney({decimal: '.', thousands: ',', precision: 2});
                //$("#desconto").maskMoney({decimal: '.', thousands: ',', precision: 2});
                //$("#valor_compra").maskMoney({decimal: '.', thousands: ',', precision: 2});
                //$("#valor_total").maskMoney({decimal: '.', thousands: ',', precision: 2});
            });
</script>


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

    function atualizaVariaveis()
    {
        cpf_cnpj = ((document.getElementById("cpf_cnpj") || {}).value) || "";
        nome = ((document.getElementById("nome") || {}).value) || "";
        codproduto = ((document.getElementById("codproduto") || {}).value) || "";
        quantidade = ((document.getElementById("quantidade") || {}).value) || "";
        //cpf_cnpj = ((document.getElementById("cpf_cnpj") || {}).value) || "";
        //valorRecebido = ((document.getElementById("valorRecebido") || {}).value) || "";
        //desconto = ((document.getElementById("desconto") || {}).value) || "";
        formaPagamento = ((document.getElementById("formaPagamento") || {}).value) || "";
    }
    function inserirProduto()
    {
        var http = new getXMLHttpRequestObject();

        http.onreadystatechange = function ()
        { //Handler function for call back on state change.
            if (http.readyState == 4)
            {
                document.getElementById("itens_venda").innerHTML = this.responseText;
            }
        }

        http.open("GET", "venda_cadastro_tabela.jsp?codproduto=" + codproduto + "&quantidade=" + quantidade, true);
        http.send();
    }
    function atualizaInformacoes()
    {
        var http = new getXMLHttpRequestObject();

        http.onreadystatechange = function ()
        { //Handler function for call back on state change.
            if (http.readyState == 4)
            {
                document.getElementById("informacoes").innerHTML = this.responseText;
            }
        }
        http.open("GET", "venda_cadastro_informacoes.jsp?formaPagamento=" + formaPagamento, true);
        http.send();
    }

    function enterProduto(event)
    {
        if (event.keyCode == 13)
        {
            atualizaVariaveis();
            inserirProduto();

            atualizaInformacoes();
            atualizaInformacoes();
            document.getElementById("quantidade").value = "1";
            document.getElementById("codproduto").value = "";
        }
    }
    function enterValorRecebido(event)
    {
        if (event.keyCode == 13)
        {
            atualizaVariaveis();
            atualizaInformacoes();
            atualizaInformacoes();
        }
    }
    function enterDesconto(event)
    {
        if (event.keyCode == 13)
        {
            atualizaVariaveis();
            atualizaInformacoes();
            atualizaInformacoes();
        }
    }
</script>

<%
    if (request.getAttribute("recarregar") != null) {
        if (request.getAttribute("recarregar").equals("sim")) {
%>
<script type="text/javascript">atualizaVariaveis();</script>
<script type="text/javascript">atualizaInformacoes();</script>
<script type="text/javascript">inserirProduto();</script>


<%
        }
    }
    ArrayList<Object> MercadoriaDAO = new MercadoriaDAO().consultarTodos();
    ArrayList<Object> ClienteDAO = new ClienteDAO().consultarTodosAtivos();
    if (request.getAttribute("pesquisa") != null) {
        ClienteDAO = (ArrayList) request.getAttribute("pesquisa");
        MercadoriaDAO = (ArrayList) request.getAttribute("pesquisa");
    }

    String finalizou_venda = String.valueOf(request.getAttribute("finalizou_venda"));
    if (finalizou_venda.equalsIgnoreCase("sucesso")) {
%>
<script type="text/javascript">
    swal({position: 'center', type: 'success', title: 'Venda Finalizada com sucesso!', showConfirmButton: false, timer: 1500})
</script>
<%
} else if (!finalizou_venda.equalsIgnoreCase("null")) {
%>
<script type="text/javascript">
    swal('Oops...', '<%= finalizou_venda%>', 'error')
</script>
<%
    }
    String nomeCliente = "";
    String cpfCnpj = "";

if (request.getAttribute("nomeCliente") != null) {
    nomeCliente = (String) request.getAttribute("nomeCliente");
}

if (request.getAttribute("cpfCnpj") != null) {
    cpfCnpj = (String) request.getAttribute("cpfCnpj");
}

%>

<!-- Page Header-->
<header class="page-header">
    <div class="container-fluid">
        <h2 class="no-margin-bottom">Venda</h2>
    </div>
</header>

<section class="forms"> 
    <div class="container-fluid">
        <div class="row">
            <!-- Inline Form-->
            <div class="col-lg-9">                           
                <div class="card">
                    <div class="card-body">
                        <div class="form-group row">
                            <label class="col-sm-2 form-control-label">Cliente</label>
                            <div class="col-sm-5">
                                <input id="nome" value="<%= nomeCliente %>" type="text" readonly="true" name="nome" required="true" class="form-control form-control-warning">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-2 form-control-label">Cpf ou Cnpj</label>
                            <div class="col-sm-5">
                                <input id="cpf_cnpj" value="<%= cpfCnpj %>" type="text" readonly="true" name="cpf_cnpj" required="true" class="form-control form-control-warning">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="card">
                    <div class="card-body">
                        <form class="form-inline">
                            <!--<div class="form-group">
                                <label for="inlineFormInput" >Quantidade:</label>
                                <input id="quantidade" type="text" class="form-control col-lg-10" value="1" >
                            </div> -->
                            <div class="form-group row">
                                <label class="col-sm-3 form-control-label">Quant.</label>
                                <div class="col-sm-2">
                                    <input id="quantidade" value="1" type="text" name="quantidade"  class="form-control form-control-warning">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-4 form-control-label">Cód.</label>
                                <div class="col-sm-1">
                                    <input id="codproduto" value="" type="text" onkeypress="enterProduto(event)" name="codproduto" class="form-control form-control-warning">
                                </div>
                            </div>
                            <!--<button type="submit" class="btn btn-info"> Cliente</button>-->


                        </form>
                    </div>
                </div>

                <div class="card">
                    <div class="card-header d-flex align-items-center">
                        <h3 class="h4">Produtos da Venda</h3>
                    </div>
                    <div class="card-body">
                        <div id="itens_venda">
                            <table class="table table-striped table-sm">
                                <thead>
                                    <tr>
                                        <th>Código</th>
                                        <th>Descrição</th>
                                        <th>Quantidade</th>
                                        <th>Valor Unitário</th>
                                        <th>Tipo</th>
                                    </tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                        </div>
                        <!--   Tabela de produtos inseridos na venda -->
                    </div>
                </div>
            </div>
            <div class="col-lg-3">
                <div class="card">
                    <div class="card-header d-flex align-items-center">
                        <h3 class="h4">Detalhes da Venda</h3>
                    </div>
                    <div class="card-body" id="informacoes">
                        <div class="form-group row">
                            <div class="col-sm-12">
                                <div class="form-group">
                                    <label for="inlineFormInputGroup" class="h6">Forma de Pagamento:</label>
                                    <select name="formaPagamento" id="formaPagamento" class="form-control">
                                        <option value="1">Dinheiro</option>
                                        <option value="2">Cartão de Crédito</option>
                                        <option value="3">Cartão de Débito</option>
                                    </select>
                                </div>
                                <form>
                                </form>
                                <div class="form-group">
                                    <label for="inlineFormInputGroup" class="h6">Valor Total:</label>
                                    <input type="text" placeholder="R$" id="valorTotal" readonly="true" class="form-control form-control-sm">
                                    <a href="acao?parametro=finalizar_venda" style="text-decoration:none;"> <button type="submit" class="btn btn-success" style="margin-top: 20px"> Finalizar</button></a>
                                </div>
                            </div>


                        </div>
                        <!-- Inforações -->
                    </div>
                </div>
            </div>

            <!-- Inline Form-->
            <div class="col-lg-9">   
                <!--<button type="submit" class="btn btn-danger"> Cancelar</button>-->
                <a href="acao?parametro=cancelar_venda"><button type="submit" class="btn btn-danger" data-icon="l"> Cancelar</button></a>
                <button type="buttom" class="btn btn-info" data-toggle="modal" data-target="#seletorCliente">Cliente</button>
            </div>

        </div>

    </div>
</section>


<!-- Modal -->
<div class="modal fade" style="margin-top: 50px" id="seletorCliente" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Selecione o cliente</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <!--                <form id="pesquisa" class="form-horizontal" action="acao?parametro=pesquisa_usuario" 
                                      method="post" onsubmit="document.pesquisa.reset();">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <input type="text" id="pesquisa" name="pesquisa" class="form-control">
                                            <span class="input-group-btn">
                                                <button type="submit" class="btn btn-primary">Pesquisar</button>
                                            </span>
                                        </div>
                                    </div>
                                </form>-->
                <table class="table table-striped table-sm">
                    <thead>
                        <tr>
                            <th>Código</th>
                            <th>Tipo Cadastro</th>
                            <th>Nome</th>
                            <th>Cpf/Cnpj</th>
                            <th>Data Cadastro</th>
                            <th>Situação</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%//                                    ArrayList<Object> usuarios = new UsuarioDAO().consultarComFiltro(criterio);
                            for (int i = 0; i < ClienteDAO.size(); i++) {
                                Cliente cliente = (Cliente) ClienteDAO.get(i);
                        %>
                        <tr>
                            <th scope="row"><%= cliente.getCodcli()%></th>
                            <td><%= cliente.getTipocadastro()%></td>
                            <td><%= cliente.getNome()%></td>
                            <td><%= cliente.getCpfCnpj()%></td>
                            <td><%= cliente.getDatacadastro()%></td>
                            <td><%= cliente.getSituacao()%></td>
                            <td><a href="acao?parametro=definir_cliente&id=<%= cliente.getCodcli()%>">Selecionar</span></a></td>
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


<!-- Modal dos Produtos-->
<div class="modal fade" style="margin-top: 50px" id="seletorProduto" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Selecione o produto</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <!--                <form id="pesquisa" class="form-horizontal" action="acao?parametro=pesquisa_usuario" 
                                      method="post" onsubmit="document.pesquisa.reset();">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <input type="text" id="pesquisa" name="pesquisa" class="form-control">
                                            <span class="input-group-btn">
                                                <button type="submit" class="btn btn-primary">Pesquisar</button>
                                            </span>
                                        </div>
                                    </div>
                                </form>-->
                <table class="table table-striped table-sm">
                    <thead>
                        <tr>
                            <th>Código</th>
                            <th>Descrição</th>
                            <th>Valor Unitário</th>
                            <th>Estoque</th>
                            <th>Tipo</th>
                            <th>Situação</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            //ArrayList<Object> mercadoria = new MercadoriaDAO().consultarComFiltro(criterio);
                            for (int i = 0; i < MercadoriaDAO.size(); i++) {
                                Mercadoria merc = (Mercadoria) MercadoriaDAO.get(i);
                        %>
                        <tr>
                            <th scope="row" id="coddamercadoria"><%= merc.getCodmercadoria()%></th>
                            <td><%= merc.getDescricao()%></td>
                            <td><%= merc.getValorunitario()%></td>
                            <td><%= merc.getEstoque()%></td>
                            <td><%= merc.getTipo()%></td>
                            <td><%= merc.getSituacao()%></td>
                            <td><input type="button" onclick="enterProduto(event)" value="selecionar"/></td>
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