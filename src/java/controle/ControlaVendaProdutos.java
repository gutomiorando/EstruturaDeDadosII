/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import apoio.Formatacao;
import apoio.Validacao;
import dao.ClienteDAO;
import dao.ContasareceberDAO;
import dao.EmpresaDAO;
import dao.ItensVendaDAO;
import dao.MercadoriaDAO;
import dao.UsuarioDAO;
import dao.VendaDAO;
import entidade.Cliente;
import entidade.Contasareceber;
import entidade.Empresa;
import entidade.ItensVenda;
import entidade.Mercadoria;
import entidade.Usuario;
import entidade.Venda;
import java.awt.HeadlessException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Usuario
 */
public class ControlaVendaProdutos {

    public static ArrayList<String> codProduto = new ArrayList();
    public static ArrayList<Mercadoria> mercadoria = new ArrayList();
    public static ArrayList<ItensVenda> itensVenda = new ArrayList();
    public static int itens = 0;
    //public static BigDecimal valorRecebido = BigDecimal.ZERO;
    //public static BigDecimal desconto = BigDecimal.ZERO;
    public static BigDecimal valorTotal = BigDecimal.ZERO;
    //public static BigDecimal valorTotalComDesconto = BigDecimal.ZERO;
    //public static BigDecimal diferenca = BigDecimal.ZERO;
    //public static BigDecimal aPagar = BigDecimal.ZERO;
    public static int formaPagamento = 1;
    public static int codVenda = 0;

    public static Usuario usuario;
    public static Cliente cliente;

    int idcontasareceber = 0;
    //public static BigDecimal troco = BigDecimal.ZERO;

    public void carregaVariaveis(HttpServletRequest request, HttpServletResponse response) {
        HttpSession sessao = request.getSession();
        //sessao.setAttribute("codigoBarrasProdutosVenda", codigoBarrasProdutosVenda);
        sessao.setAttribute("mercadoria", mercadoria);
        sessao.setAttribute("itens", itens);
        sessao.setAttribute("cliente", cliente);
        //sessao.setAttribute("valorRecebido", valorRecebido);
        //sessao.setAttribute("desconto", desconto);
        sessao.setAttribute("valorTotal", valorTotal);
        //sessao.setAttribute("valorTotalComDesconto", valorTotalComDesconto);
        //sessao.setAttribute("diferenca", diferenca);
        sessao.setAttribute("formaPagamento", formaPagamento);
        sessao.setAttribute("codProduto", codProduto);
        sessao.setAttribute("codVenda", codVenda);
        //sessao.setAttribute("aPagar", aPagar);

        atualizarValoresDaCompra();
    }

    public void carregaInformacoes(String valorRecebido, String desconto, String formaPagamento) {
        //if (!Validacao.validarNumero(valorRecebido) && !Validacao.validarNumero(desconto))
        //{
        //    return;
        //}
        //if (valorRecebido.compareTo("") != 0)
        // {
        //     ControlaVendaProdutos.valorTotal = new BigDecimal(valorTotal);
        // }

        //if (desconto.compareTo("") != 0)
        //{
        //    ControlaVendaProdutos.desconto = new BigDecimal(desconto);
        //}
        if (formaPagamento.compareTo("") != 0) {
            ControlaVendaProdutos.formaPagamento = Integer.valueOf(formaPagamento);
        }

        atualizarValoresDaCompra();
    }

    public void diminuirestoque(int idprod, String quantidade) {
        String retorno = null;
        String retorno10 = null;
        MercadoriaDAO mercDAO = new MercadoriaDAO();
        Mercadoria merca = (Mercadoria) new MercadoriaDAO().consultarId(idprod);

        merca.setCodmercadoria(idprod);
        merca.setEstoque(merca.getEstoque() - (Double.parseDouble(quantidade)));
        System.out.println("Estoque = " + merca.getEstoque());
        retorno = mercDAO.atualizar(merca);
        if (merca.getEstoque() == 0) {
            retorno10 = mercDAO.excluir(merca.getCodmercadoria());
        }
    }

    public void iniciarVenda(String id, String quantidade) {
        boolean numeroValidoQuantidade = Validacao.validarNumero(quantidade);
        boolean numeroValidoCodigoProduto = Validacao.validarNumero(id);

        if (!id.equalsIgnoreCase("")) {
            int idProduto = Integer.parseInt(id);
            Mercadoria mercadoria2;
            MercadoriaDAO mercadoriaDAO = new MercadoriaDAO();

            mercadoria2 = (Mercadoria) mercadoriaDAO.consultarId(idProduto);

            if (numeroValidoQuantidade && numeroValidoCodigoProduto && mercadoria2 != null) {
                adicionaProdutoNaCompra(mercadoria2, Integer.parseInt(quantidade));
                atualizarValoresDaCompra();
                diminuirestoque(idProduto, quantidade);
            } else {
                System.out.println("Digite um número válido na quantidade");
            }
        }
    }

    public void carregaCliente(Cliente cliente) {
//        tfdCliente.setText(cliente.getNome());
//        this.cliente = cliente;
    }

    public void atualizarValoresDaCompra() {
        //valorTotalComDesconto = valorTotal.subtract(desconto);

        //if (valorRecebido.compareTo(valorTotalComDesconto) >= 0)
        //{
        //    diferenca = valorRecebido.subtract(valorTotalComDesconto);
        //    troco = diferenca;
        //    aPagar = BigDecimal.ZERO;
        //} 
        //else
        // {
        //     diferenca = valorTotalComDesconto.subtract(valorRecebido);
        //    troco = BigDecimal.ZERO;
        //    aPagar = diferenca;
        // }
    }

    public void limparValoresDaCompra() {
//        nomeCliente.clear();
        mercadoria.clear();
        itensVenda.clear();
//        cpfoucnpj.clear();
        codProduto.clear();

        itens = 0;
        //valorRecebido = BigDecimal.ZERO;
        //desconto = BigDecimal.ZERO;
        valorTotal = BigDecimal.ZERO;
        //valorTotalComDesconto = BigDecimal.ZERO;
        //diferenca = BigDecimal.ZERO;
        //aPagar = BigDecimal.ZERO;

        ClienteDAO clienteDAO = new ClienteDAO();
        //this.cliente = (Usuario) usuarioDAO.consultarLogin("anonimo");
    }

    public void adicionaProdutoNaCompra(Mercadoria produto, int quantidade) {
        this.codProduto.add(String.valueOf(produto.getCodmercadoria()));
        mercadoria.add(produto);

        ItensVenda itemVenda = new ItensVenda();
        itemVenda.setCoditen(produto.getCodmercadoria());
        itemVenda.setQuantidade(quantidade);
        itemVenda.setValorUnitario(produto.getValorunitario());

        itensVenda.add(itemVenda);

        // Para poder buscar o nome da categoria de produto
        //CategoriaProdutoDAO cpDAO = new CategoriaProdutoDAO();
        //CategoriaProduto categoriaProduto;
        //categoriaProduto = (CategoriaProduto) cpDAO.consultarId(produto.getCategoria_produto_id());
        // ###################################################
        // ###       Campos da tabela da direita           ###
        // ###################################################
        // Adiciona 1 item no carrinho
        itens++;

        // Valor do produto * quantidade
        BigDecimal valorProdutoQuantidade = (BigDecimal.valueOf(produto.getValorunitario()).multiply(BigDecimal.valueOf(quantidade)));
        valorTotal = valorTotal.add(valorProdutoQuantidade);

        atualizarValoresDaCompra();
    }

    public void removeProdutoNaCompra(int indiceProduto) {
        if (cliente == null)
        {
            ClienteDAO clienteDAO = new ClienteDAO();
            cliente = (Cliente) clienteDAO.consultarId(1);
        }
        if (mercadoria.size() != 1) {
            BigDecimal valorProdutoQuantidade = (BigDecimal.valueOf((mercadoria.get(indiceProduto).getValorunitario())).multiply(BigDecimal.valueOf(itensVenda.get(indiceProduto).getQuantidade())));
            this.codProduto.remove(indiceProduto);
            mercadoria.remove(indiceProduto);
            itensVenda.remove(indiceProduto);

            valorTotal = valorTotal.subtract(valorProdutoQuantidade);
        } else {
            this.codProduto.clear();
            mercadoria.clear();
            itensVenda.clear();
            valorTotal = BigDecimal.ZERO;
        }
        itens--;
        //atualizarValoresDaCompra();
    }

    public void cancelarVenda() {
        limparValoresDaCompra();
        atualizarValoresDaCompra();
    }

    private void btnRemoverProdutoActionPerformed(java.awt.event.ActionEvent evt) {
//        DefaultTableModel dtm = (DefaultTableModel) tblVendaProdutos.getModel();
//        if (tblVendaProdutos.getSelectedRow() >= 0)
//        {
//            Produto produto = new ProdutoDAO().consultarCodigoBarras(
//                    (String) tblVendaProdutos.getValueAt(tblVendaProdutos.getSelectedRow(), 5));
//
//            codigoBarrasProdutosVenda.remove(produto.getCodigo_barras());
//            produtosVenda.remove(tblVendaProdutos.getSelectedRow());
//
//            int quantidadeDoProduto = (int) tblVendaProdutos.getValueAt(tblVendaProdutos.getSelectedRow(), 3);
//
//            dtm.removeRow(tblVendaProdutos.getSelectedRow());
//            tblVendaProdutos.setModel(dtm);
//
//            itens--;
//
//            // Valor do produto * quantidade
//            BigDecimal valorProdutoQuantidade = (produto.getValor_venda().multiply(BigDecimal.valueOf(quantidadeDoProduto)));
//            valorTotal = valorTotal.subtract(valorProdutoQuantidade);
//            atualizarValoresDaCompra();
//        } else
//        {
//            JOptionPane.showMessageDialog(this, "Nenhum produto selecionado!");
//        }
    }

    private void btnSelecionarClienteActionPerformed(java.awt.event.ActionEvent evt) {
//        DlgSelecionaUsuario dlgClientes = new DlgSelecionaUsuario(null, true, this);
//        dlgClientes.setVisible(true);
    }

    private void tfdValorRecebidoActionPerformed(java.awt.event.ActionEvent evt) {
//        if (tfdValorRecebido.getText().equals(""))
//        {
//            tfdValorRecebido.setText("0");
//        }
//
//        tfdValorRecebido.setText(tfdValorRecebido.getText().replaceAll(",", "."));
//
//        atualizarValoresDaCompra();
//        tfdDesconto.grabFocus();
    }

    private void tfdDescontoActionPerformed(java.awt.event.ActionEvent evt) {
//        if (tfdDesconto.getText().equals(""))
//        {
//            tfdDesconto.setText("0");
//        }
//
//        tfdDesconto.setText(tfdDesconto.getText().replaceAll(",", "."));
//
//        atualizarValoresDaCompra();
    }

    public String finalizarVenda() {
        String retorno = null;
        try {
            atualizarValoresDaCompra();

            // TESTA SE TEM CLIENTE NA VENDA
            //if(cliente.getNome().equalsIgnoreCase("")){
            // Tentando validar pra ver se o valor total deu um valor > 0
            if (valorTotal.compareTo(BigDecimal.ZERO) > 0) {

                Venda venda = new Venda();
                Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());

                if (cliente == null) {
                    int codc = 1;
                    Cliente clinull = (Cliente) new ClienteDAO().consultarId(codc);
                    venda.setCliente(clinull);
                } else {
                    venda.setCliente(cliente);
                }

                //setar a empresa
                int codemp = 1;
                Empresa emp = (Empresa) new EmpresaDAO().consultarId(codemp);
                venda.setEmpresa(emp);

                //setar o usuario
                int codusu = 1;
                Usuario usu = (Usuario) new UsuarioDAO().consultarId(codusu);
                venda.setUsuario(usu);

                venda.setDataemissao(Formatacao.getDataAtual());
                venda.setValortotal(Double.parseDouble(String.valueOf(valorTotal)));
                venda.setQuantidade(itensVenda.size());

                // Pegar o combobox Tipo Usuário
                //ComboItens FormaPagamento = (ComboItens) FormaPagamento.getSelectedItem();
                venda.setForma_pagamento(formaPagamento);

                if (formaPagamento == 1) {
                    venda.setForma_pagamento('D');
                }
                if (formaPagamento == 2) {
                    venda.setForma_pagamento('C');
                }
                if (formaPagamento == 3) {
                    venda.setForma_pagamento('E');
                }

                venda.setSituacaovenda('F');

                /*
                int z = 0;
                while (z < mercadoria.size()) {

                    //Venda venda = (Venda) vendaDAO.get(i);
                    //ClienteDAO clienteDAO2 = new ClienteDAO();
                    //Cliente cliente = (Cliente) clienteDAO2.consultarId(venda.getCliente().getCodcli());
                    
                    //String codvend = String.valueOf(venda.getCodvenda());
                    //ArrayList<Object> vendaDAO2 = new VendaDAO().consultarComFiltro(codvend); 

                    MercadoriaDAO mcDAO = new MercadoriaDAO();
                    int cod4 = Integer.parseInt(String.valueOf(mercadoria.get(z)));
                    int codm = mcDAO.consultarId(cod4);
                    Mercadoria codmer2 = mercadoria.get(z);
                    int codmer3 = new MercadoriaDAO().consultarId(Integer.parseInt(String.valueOf(codmer2)));

                    z++;
                }
                 */
                VendaDAO vendaDAO = new VendaDAO();

                // Se conseguiu salvar a venda sem erro
                if (vendaDAO.salvar(venda) == null) {
                    int i = 0;
                    while (i < mercadoria.size()) {
                        ItensVenda itensVenda = this.itensVenda.get(i);
                        itensVenda.setCodvenda(codVenda);
                        itensVenda.setMercadoria(mercadoria.get(i));

                        ItensVendaDAO ItensVendaDAO = new ItensVendaDAO();
                        // Salva aqui!!!!
                        ItensVendaDAO.salvar(itensVenda);

                        i++;
                    }
                    // ADICIONA NO CONTAS A RECEBER
                    Contasareceber careceber = new Contasareceber();
                    //careceber.setId(idcontasareceber); //tem que setar zero no final.
                    careceber.setCodvenda(codVenda);
                    careceber.setDatalancamento(Formatacao.getDataAtual());
                    //if (venda.getFormapagamento('C')) {
                    //if(formapagamento = "C") {
                    Date data = new Date();
                    data.setDate(data.getDate());
                    Calendar c = Calendar.getInstance();
                    c.setTime(data);
                    c.add(Calendar.DATE, 30);
                    // Obtemos a data alterada
                    data = c.getTime();
                    String dataFormatada = Formatacao.ajustaDataDMAJCalendar(data);
                    careceber.setDatavencimento(dataFormatada);

                    // se não aqui dá nullPointer porque ficaria aberto na tabela
                    careceber.setDatapagamento("");
                    //} else {

                    careceber.setDatapagamento(Formatacao.getDataAtual());
                    careceber.setDatavencimento(Formatacao.getDataAtual());
                    // }
                    careceber.setValorprevisto(Double.parseDouble(String.valueOf(valorTotal)));
                    if (formaPagamento == 1) {
                        careceber.setSituacao('Q');
                    }
                    if (formaPagamento == 2) {
                        careceber.setSituacao('Q');
                    }
                    if (formaPagamento == 3) {
                        careceber.setSituacao('A');
                    }
                    //careceber.setSituacao('A');

                    ContasareceberDAO receberDAO = new ContasareceberDAO();
                    receberDAO.salvar(careceber);

                }
                limparValoresDaCompra();
                //UsuarioDAO usuarioDAO = new UsuarioDAO();
                //cliente = (Usuario) usuarioDAO.consultarLogin("anonimo");

                System.out.println("Venda Finalizada!");
            } else {
                System.out.println("Nenhum produto adicionado");
                retorno = "Nenhum produto adicionado";
                return retorno;
            }

            //} else {
            //    System.out.println("Nenhum Cliente adicionado");
            //     retorno = "Nenhum Cliente adicionado";
            //     return retorno;
            // }
        } catch (HeadlessException | NumberFormatException e) {
            System.out.println("Erro ao finalizar venda!");
            System.out.println(e);
            retorno = "Erro ao finalizar venda!";
            return retorno;
        }
        return retorno;
    }

    private void tfdQuantidadeActionPerformed(java.awt.event.ActionEvent evt) {
//        tfdCodigoBarras.grabFocus();
    }

    private void btnAdicionarProdutoActionPerformed(java.awt.event.ActionEvent evt) {
//        DlgSelecionaProduto dlgSelecionaProduto = new DlgSelecionaProduto(null, true, this);
//        dlgSelecionaProduto.setVisible(true);
    }

    private void btnVendasConcluidasActionPerformed(java.awt.event.ActionEvent evt) {
//        DlgVendasConcluidas dlgVendasConcluidas = new DlgVendasConcluidas(null, true, this);
//        dlgVendasConcluidas.setVisible(true);
    }

    private void cbFormaPagamentoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void tfdValorRecebidoFocusLost(java.awt.event.FocusEvent evt) {
//        if (tfdValorRecebido.getText().equals(""))
//        {
//            tfdValorRecebido.setText("0");
//        }
//
//        tfdValorRecebido.setText(tfdValorRecebido.getText().replaceAll(",", "."));
//
//        atualizarValoresDaCompra();
//        tfdDesconto.grabFocus();
    }

    private void tfdDescontoFocusLost(java.awt.event.FocusEvent evt) {
//        if (tfdDesconto.getText().equals(""))
//        {
//            tfdDesconto.setText("0");
//        }
//
//        tfdDesconto.setText(tfdDesconto.getText().replaceAll(",", "."));
//
//        atualizarValoresDaCompra();
    }

    public ArrayList<Mercadoria> obterProdutosVenda() {
        ArrayList<Mercadoria> produtosVenda = null;
        return produtosVenda;
    }

    public void adicionarProdutoLista(Mercadoria produto) {
//        produtosVenda.add(produto);
    }

}
