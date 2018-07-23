<%-- 
    Document   : venda_cadastro_informacoes
    Created on : 12/11/2017, 14:23:43
    Author     : Usuario
--%>

<%@page import="controle.ControlaVendaProdutos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <input value="<%= ControlaVendaProdutos.valorTotal %>" type="text" placeholder="R$" id="valorTotal" readonly="true" class="form-control form-control-sm">
            <a href="acao?parametro=finalizar_venda" style="text-decoration:none;"> <button type="submit" class="btn btn-success" style="margin-top: 20px"> Finalizar</button></a>
        </div>
    </div>


</div>