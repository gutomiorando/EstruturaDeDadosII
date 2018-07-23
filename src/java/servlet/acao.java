/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import apoio.Encryption;
import apoio.Formatacao;
import com.sun.security.auth.module.NTSystem;
import controle.ControlaUsuario;
import controle.ControlaVendaProdutos;
import static controle.ControlaVendaProdutos.mercadoria;
import dao.CidadeDAO;
import dao.ClienteDAO;
import dao.ContasapagarDAO;
import dao.ContasareceberDAO;
import dao.FornecedorDAO;
import dao.FuncionarioDAO;
import dao.MercadoriaDAO;
import dao.RelatorioDAO;
import dao.UsuarioDAO;
import dao.VendaDAO;
import entidade.Cidade;
import entidade.Cliente;
import entidade.Contasapagar;
import entidade.Fornecedor;
import entidade.Funcionario;
import entidade.Mercadoria;
import entidade.Usuario;
import entidade.Venda;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

/**
 *
 * @author fabricio.pretto
 */
public class acao extends HttpServlet {

    public static String paginaModelo = "modelo.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet acao</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet acao at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        System.out.println("PASSEI no GET");
//        String dado = request.getParameter("cor");
//        System.out.println("Cor que veio: " + dado);
        String parametro = request.getParameter("parametro");
        HttpSession sessao = request.getSession();

        if (parametro.equals("sair")) {
            sessao.invalidate();
            response.sendRedirect("index.jsp");
        }
        else if (parametro.equals("home")){
            encaminharPagina("tela_inicial.jsp", request, response);
        }
        else if (parametro.equals("venda_cadastro")) {
            encaminharPagina("venda_cadastro.jsp", request, response);
        }
        else if (parametro.equals("relatorio_clienteporcidade")) {
            encaminharPagina("relatorio_clienteporcidade.jsp", request, response);
        }
        else if (parametro.equals("relatorio_contasapagarporfornecedor")) {
            encaminharPagina("relatorio_contasapagarporfornecedor.jsp", request, response);
        }
        else if (parametro.equals("relatorio_vendasporperiodo")) {
            encaminharPagina("relatorio_vendasporperiodo.jsp", request, response);
        }
        else if (parametro.equals("vendas_realizadas")) {
            encaminharPagina("vendas_realizadas.jsp", request, response);
        }
        else if (parametro.equals("relatorio_contasareceberporperiodo")) {
            encaminharPagina("relatorio_contasareceberporperiodo.jsp", request, response);
        }
        else if (parametro.equals("relatorio_contasapagarporperiodo")) {
            encaminharPagina("relatorio_contasapagarporperiodo.jsp", request, response);
        }
        else if (parametro.equals("relatorio_funcionariosporadmissao")) {
            encaminharPagina("relatorio_funcionariosporadmissao.jsp", request, response);
        }
        else if (parametro.equals("cadastro_usuario")) {
            encaminharPagina("cadastro_usuario.jsp", request, response);
        }
        else if (parametro.equals("cadastro_cidade")) {
            encaminharPagina("cadastro_cidade.jsp", request, response);
        }
        else if (parametro.equals("cadastro_fornecedor")) {
            encaminharPagina("cadastro_fornecedor.jsp", request, response);
        }
        else if (parametro.equals("cadastro_funcionario")) {
            encaminharPagina("cadastro_funcionario.jsp", request, response);
        }
        else if (parametro.equals("cadastro_mercadoria")) {
            encaminharPagina("cadastro_mercadoria.jsp", request, response);
        }
        else if (parametro.equals("cadastro_contasapagar")) {
            encaminharPagina("cadastro_contasapagar.jsp", request, response);
        }
        else if (parametro.equals("cadastro_cliente")) {
            encaminharPagina("cadastro_cliente.jsp", request, response);
        }
        else if (parametro.equals("listagem_usuario")) {
            encaminharPagina("listagem_usuario.jsp", request, response);
        }
        else if (parametro.equals("listagem_cidade")) {
            encaminharPagina("listagem_cidade.jsp", request, response);
        }
        else if (parametro.equals("listagem_fornecedor")) {
            encaminharPagina("listagem_fornecedor.jsp", request, response);
        }
        else if (parametro.equals("listagem_mercadoria")) {
            encaminharPagina("listagem_mercadoria.jsp", request, response);
        }
        else if (parametro.equals("listagem_cliente")) {
            encaminharPagina("listagem_cliente.jsp", request, response);
        }
        else if (parametro.equals("listagem_contasapagar")) {
            encaminharPagina("listagem_contasapagar.jsp", request, response);
        }
        else if (parametro.equals("listagem_contasareceber")) {
            encaminharPagina("listagem_contasareceber.jsp", request, response);
        }
        else if (parametro.equals("relatorio_vendasporcliente")) {
            encaminharPagina("relatorio_vendasporcliente.jsp", request, response);
        }
        else if (parametro.equals("definir_cliente")){
            
            int id = Integer.parseInt(String.valueOf(request.getParameter("id")));
            
            ClienteDAO clienteDAO =  new ClienteDAO();
            Cliente cliente = (Cliente) clienteDAO.consultarId(id);
            ControlaVendaProdutos.cliente = cliente;
            request.setAttribute("recarregar", "sim");
            request.setAttribute("nomeCliente", cliente.getNome());
            request.setAttribute("cpfCnpj", cliente.getCpfCnpj());
            encaminharPagina("venda_cadastro.jsp", request, response);
        }
        else if (parametro.equals("remover_produto"))
        {
            String indiceProduto = request.getParameter("indice_produto");

            ControlaVendaProdutos controlaVendaProdutos = new ControlaVendaProdutos();
            controlaVendaProdutos.removeProdutoNaCompra(Integer.parseInt(indiceProduto));
            
            if(ControlaVendaProdutos.cliente.getNome() != null)
            {
                request.setAttribute("nomeCliente", ControlaVendaProdutos.cliente.getNome());
                request.setAttribute("cpfCnpj", ControlaVendaProdutos.cliente.getCpfCnpj());
            }
            
            request.setAttribute("recarregar", "sim");
            encaminharPagina("venda_cadastro.jsp", request, response);
        } 
        else if (parametro.equals("definir_mercadoria")){
            
            int id = Integer.parseInt(String.valueOf(request.getParameter("id")));
            
            MercadoriaDAO mercadoriaDAO =  new MercadoriaDAO();
            ControlaVendaProdutos.mercadoria.add ((Mercadoria) mercadoriaDAO.consultarId(id));
            request.setAttribute("recarregar", "sim");
            encaminharPagina("venda_cadastro.jsp", request, response);
        }
        else if (parametro.equals("editar_usuario")) 
        {
            int id = Integer.parseInt(String.valueOf(request.getParameter("id")));
            Usuario usuario = (Usuario) new UsuarioDAO().consultarId(id);

            request.setAttribute("usuario", usuario);
            encaminharPagina("cadastro_usuario.jsp", request, response);
        }
        else if (parametro.equals("venda_detalhes"))
        {
            int id = Integer.parseInt(String.valueOf(request.getParameter("id")));

            VendaDAO vendaDAO = new VendaDAO();
            Venda venda = (Venda) vendaDAO.consultarId(id);
            request.setAttribute("venda", venda);
            encaminharPagina("vendas_realizadas_detalhes.jsp", request, response);
        }
        else if (parametro.equals("SelUsuario")) 
        {
            int id = Integer.parseInt(String.valueOf(request.getParameter("id")));
            Usuario usuario = (Usuario) new UsuarioDAO().consultarId(id);

            request.setAttribute("usuario", usuario);
            encaminharPagina("venda_listausuario.jsp", request, response);
        }
        else if (parametro.equals("cancelar_venda"))
        {
            ControlaVendaProdutos controlaVendaProdutos = new ControlaVendaProdutos();
            controlaVendaProdutos.cancelarVenda();
            encaminharPagina("venda_cadastro.jsp", request, response);
        } 
        else if (parametro.equals("excluir_usuario")) 
        {
            int id = Integer.parseInt(String.valueOf(request.getParameter("id")));

            String retorno = new UsuarioDAO().excluir(id);

            request.setAttribute("origem", "listagem_usuario.jsp");

            if (retorno == null)
            {
                request.setAttribute("confirma_exclusao", "sucesso");
            }
            else
            {
                request.setAttribute("confirma_exclusao", "erro");
            }
                encaminharPagina("listagem_usuario.jsp", request, response);
        }
        else if (parametro.equals("quitar_conta")) 
        {
            int id = Integer.parseInt(String.valueOf(request.getParameter("id")));

            String retorno = new ContasapagarDAO().excluir(id);

            request.setAttribute("origem", "listagem_contasapagar.jsp");

            if (retorno == null)
            {
                request.setAttribute("confirma_exclusao", "sucesso");
            }
            else
            {
                request.setAttribute("confirma_exclusao", "erro");
            }
            encaminharPagina("listagem_contasapagar.jsp", request, response);
        }
        else if (parametro.equals("receber_conta")) 
        {
            int id = Integer.parseInt(String.valueOf(request.getParameter("id")));

            String retorno = new ContasareceberDAO().excluir(id);

            request.setAttribute("origem", "listagem_contasareceber.jsp");

            if (retorno == null)
            {
                request.setAttribute("confirma_exclusao", "sucesso");
            }
            else
            {
                request.setAttribute("confirma_exclusao", "erro");
            }
            encaminharPagina("listagem_contasareceber.jsp", request, response);
        }
        else if (parametro.equals("editar_cidade")) 
        {
            int id = Integer.parseInt(String.valueOf(request.getParameter("id")));
            Cidade cidade = (Cidade) new CidadeDAO().consultarId(id);

            request.setAttribute("cidade", cidade);
            encaminharPagina("cadastro_cidade.jsp", request, response);
        }
        else if (parametro.equals("excluir_cidade")) 
        {
            int id = Integer.parseInt(String.valueOf(request.getParameter("id")));

            String retorno = new CidadeDAO().excluir(id);

            request.setAttribute("origem", "listagem_cidade.jsp");

            if (retorno == null)
            {
                request.setAttribute("confirma_exclusao", "sucesso");
            }
            else
            {
                request.setAttribute("confirma_exclusao", "erro");
            }
            encaminharPagina("listagem_cidade.jsp", request, response);
        }
        else if (parametro.equals("editar_fornecedor")) 
        {
            int id = Integer.parseInt(String.valueOf(request.getParameter("id")));
            Fornecedor forn = (Fornecedor) new FornecedorDAO().consultarId(id);

            request.setAttribute("fornecedor", forn);
            encaminharPagina("cadastro_fornecedor.jsp", request, response);
        }
        else if (parametro.equals("excluir_fornecedor")) 
        {
            int id = Integer.parseInt(String.valueOf(request.getParameter("id")));

            String retorno = new FornecedorDAO().excluir(id);

            request.setAttribute("origem", "listagem_fornecedor.jsp");

            if (retorno == null)
            {
                request.setAttribute("confirma_exclusao", "sucesso");
            }
            else
            {
                request.setAttribute("confirma_exclusao", "erro");
            }
            encaminharPagina("listagem_fornecedor.jsp", request, response);
        }
        else if (parametro.equals("editar_mercadoria")) 
        {
            int id = Integer.parseInt(String.valueOf(request.getParameter("id")));
            Mercadoria merc = (Mercadoria) new MercadoriaDAO().consultarId(id);

            request.setAttribute("mercadoria", merc);
            encaminharPagina("cadastro_mercadoria.jsp", request, response);
        }
        else if (parametro.equals("excluir_mercadoria")) 
        {
            int id = Integer.parseInt(String.valueOf(request.getParameter("id")));

            String retorno = new MercadoriaDAO().excluir(id);

            request.setAttribute("origem", "listagem_mercadoria.jsp");

            if (retorno == null)
            {
                request.setAttribute("confirma_exclusao", "sucesso");
            }
            else
            {
                request.setAttribute("confirma_exclusao", "erro");
            }
            encaminharPagina("listagem_mercadoria.jsp", request, response);
        }
        else if (parametro.equals("editar_cliente")) 
        {
            int id = Integer.parseInt(String.valueOf(request.getParameter("id")));
            Cliente cli = (Cliente) new ClienteDAO().consultarId(id);

            request.setAttribute("cliente", cli);
            encaminharPagina("cadastro_cliente.jsp", request, response);
        }
        else if (parametro.equals("excluir_cliente")) 
        {
            int id = Integer.parseInt(String.valueOf(request.getParameter("id")));

            String retorno = new ClienteDAO().excluir(id);

            request.setAttribute("origem", "listagem_cliente.jsp");

            if (retorno == null)
            {
                request.setAttribute("confirma_exclusao", "sucesso");
            }
            else
            {
                request.setAttribute("confirma_exclusao", "erro");
            }
            encaminharPagina("listagem_cliente.jsp", request, response);
        }
        else if (parametro.equals("csv_usuario"))
        {
            ArrayList<Object> usuarioDAO = new UsuarioDAO().consultarTodos();
            String retorno = gerarCsvUsuario(usuarioDAO);
            if (retorno.equals("OK"))
            {
                request.setAttribute("csv_usuario_sucesso", "sucesso");
            } 
            else
            {
                request.setAttribute("csv_usuario_sucesso", "erro");
            }
            encaminharPagina("listagem_usuario.jsp", request, response);
        }
        /*else if (parametro.equals("relatorio_vendas_por_cliente"))
        {
            int cliente_id;
            cliente_id = Integer.parseInt(request.getParameter("id"));
            byte[] bytes = new RelatorioDAO().relatorio_vendas_por_cliente(cliente_id);
            //gerarRelatorio(response, bytes);
           response.setContentType("application/pdf");
           response.setContentLength(bytes.length);
           ServletOutputStream outStream = response.getOutputStream();
           outStream.write(bytes, 0, bytes.length);
           outStream.flush();
           outStream.close();
        }*/
        
        else if (parametro.equals("finalizar_venda"))
        {
            ControlaVendaProdutos controlaVendaProdutos = new ControlaVendaProdutos();
            controlaVendaProdutos.carregaVariaveis(request, response);
            String retorno = controlaVendaProdutos.finalizarVenda();
            if (retorno == null)
            {
                request.setAttribute("finalizou_venda", "sucesso");
            } 
            else
            {
                request.setAttribute("finalizou_venda", retorno);
            }
            encaminharPagina("venda_cadastro.jsp", request, response);
        }
//        if (parametro.equals("edUsuario")) {
//            int id = Integer.parseInt(String.valueOf(request.getParameter("id")));
//            Usuario usuario = (Usuario) new UsuarioDAO().consultarId(id);
//
//            request.setAttribute("usuario", usuario);
//            encaminharPagina("usuario.jsp", request, response);
//        }
//
//        if (parametro.equals("exUsuario")) {
//            int id = Integer.parseInt(String.valueOf(request.getParameter("id")));
//
//            String retorno = new UsuarioDAO().excluir(id);
//
//            request.setAttribute("origem", "usuario.jsp");
//            
//            if (retorno == null) {
//                encaminharPagina("sucesso.jsp", request, response);
//            } else {
//                encaminharPagina("erro.jsp", request, response);
//            }
//        }
//        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String parametro = request.getParameter("parametro");
        HttpSession sessao = request.getSession();

        System.out.println("OPAAAAAAAAAAAAAAAAA");

        // Valida Login
        if (parametro.equals("login")) {
            String login = String.valueOf(request.getParameter("login"));
            String senha = String.valueOf(request.getParameter("senha"));
            
            UsuarioDAO dao = new UsuarioDAO();

            try {
                String senhaEncriptada = Encryption.hash(senha);
                if (dao.testaUsu(login, senhaEncriptada)) {
                    Usuario usuario = (Usuario) new UsuarioDAO().consultarPorNome(login);

                    //Adicionar o usuário na sessão
                    sessao.setAttribute("usuarioLogado", usuario);

                    //************ CHAMAR TELA DO SISTEMA
                    //new TelaPrincipal(vendedor).setVisible( true );
                    //String destino = "tela_inicial.jsp";
                    //request.setAttribute("destino", destino);
                    encaminharPagina("venda_cadastro.jsp", request, response);
//                    response.sendRedirect("control.jsp");
                    //encaminharPagina("modelo.jsp", request, response);
                } else {
                    response.sendRedirect("index.jsp");
                }
            } catch (Exception e) {
                System.out.println("Erro ao consultar Fazer login: " + e);
            }
        }

        if (parametro.equals("cadastro_usuario")) {
            Usuario usuario = new Usuario();

            int codigo = 0;
            if (request.getParameter("codusuario") != null) {
                codigo = Integer.parseInt(request.getParameter("codusuario"));
            }

            usuario.setCodusuario(codigo);
            usuario.setNomeusuario(request.getParameter("nomeusuario"));
            usuario.setEmail(request.getParameter("email"));
            usuario.setSenha(Encryption.hash(request.getParameter("senha")));
            usuario.setSituacao(request.getParameter("situacao").charAt(0));

            String retorno = null;

            System.out.println("Código = " + codigo);
            if (codigo == 0) {
                retorno = new UsuarioDAO().salvar(usuario);
            } else {
                retorno = new UsuarioDAO().atualizar(usuario);
            }

            // Se código = 0 é um registro novo. Se não, é uma edição.
            if (codigo == 0) {
                if (retorno == null) {
                    request.setAttribute("confirma_cadastro", "sucesso");
                } else {
                    request.setAttribute("confirma_cadastro", "erro");
                }
            } else {
                if (retorno == null) {
                    request.setAttribute("confirma_edicao", "sucesso");
                } else {
                    request.setAttribute("confirma_edicao", "erro");
                }
            }

            encaminharPagina("cadastro_usuario.jsp", request, response);
        }

        if (parametro.equals("cadastro_cidade")) {
            Cidade cid = new Cidade();

            int codigo = 0;
            if (request.getParameter("codcid") != null) {
                codigo = Integer.parseInt(request.getParameter("codcid"));
            }

            cid.setCodcid(codigo);
            cid.setNome(request.getParameter("nome"));
            cid.setSituacao(request.getParameter("situacao").charAt(0));

            String retorno = null;

            if (codigo == 0) {
                retorno = new CidadeDAO().salvar(cid);
            } else {
                retorno = new CidadeDAO().atualizar(cid);
            }

            // Se código = 0 é um registro novo. Se não, é uma edição.
            if (codigo == 0) {
                if (retorno == null) {
                    request.setAttribute("confirma_cadastro", "sucesso");
                } else {
                    request.setAttribute("confirma_cadastro", "erro");
                }
            } else {
                if (retorno == null) {
                    request.setAttribute("confirma_edicao", "sucesso");
                } else {
                    request.setAttribute("confirma_edicao", "erro");
                }
            }

            encaminharPagina("cadastro_cidade.jsp", request, response);
        }

        if (parametro.equals("cadastro_fornecedor")) {
            Fornecedor forn = new Fornecedor();

            int codigo = 0;
            if (request.getParameter("codfornecedor") != null) {
                codigo = Integer.parseInt(request.getParameter("codfornecedor"));
            }

            forn.setCodfornecedor(codigo);
            forn.setNome(request.getParameter("nome"));
            forn.setDatacadastro(request.getParameter("datacadastro"));
            forn.setSituacao(request.getParameter("situacao").charAt(0));

            String retorno = null;

            if (codigo == 0) {
                retorno = new FornecedorDAO().salvar(forn);
            } else {
                retorno = new FornecedorDAO().atualizar(forn);
            }

            // Se código = 0 é um registro novo. Se não, é uma edição.
            if (codigo == 0) {
                if (retorno == null) {
                    request.setAttribute("confirma_cadastro", "sucesso");
                } else {
                    request.setAttribute("confirma_cadastro", "erro");
                }
            } else {
                if (retorno == null) {
                    request.setAttribute("confirma_edicao", "sucesso");
                } else {
                    request.setAttribute("confirma_edicao", "erro");
                }
            }

            encaminharPagina("cadastro_fornecedor.jsp", request, response);
        }
        
        if (parametro.equals("cadastro_funcionario")) {
            Funcionario funcionario = new Funcionario();

            int codigo = 0;
            if (request.getParameter("codfunc") != null) {
                codigo = Integer.parseInt(request.getParameter("codfunc"));
            }

            funcionario.setCodfunc(codigo);
            funcionario.setIdempresa(1);
            funcionario.setNome(request.getParameter("nome"));
            funcionario.setDataadmissao(request.getParameter("dataadmissao"));
            funcionario.setDatademissao(request.getParameter("datademissao"));
            funcionario.setFuncao(request.getParameter("funcao"));
            funcionario.setSalario(Double.parseDouble(request.getParameter("salario")));
            funcionario.setSituacao(request.getParameter("situacao").charAt(0));

            String retorno = null;

            //System.out.println("Código = " + codigo);
            if (codigo == 0) {
                retorno = new FuncionarioDAO().salvar(funcionario);
            } else {
                retorno = new FuncionarioDAO().atualizar(funcionario);
            }

            // Se código = 0 é um registro novo. Se não, é uma edição.
            if (codigo == 0) {
                if (retorno == null) {
                    request.setAttribute("confirma_cadastro", "sucesso");
                } else {
                    request.setAttribute("confirma_cadastro", "erro");
                }
            } else {
                if (retorno == null) {
                    request.setAttribute("confirma_edicao", "sucesso");
                } else {
                    request.setAttribute("confirma_edicao", "erro");
                }
            }

            encaminharPagina("cadastro_funcionario.jsp", request, response);
        }
        
        if (parametro.equals("cadastro_mercadoria")) {
            Mercadoria mercadoria = new Mercadoria();

            int codigo = 0;
            if (request.getParameter("codmercadoria") != null) {
                codigo = Integer.parseInt(request.getParameter("codmercadoria"));
            }

            mercadoria.setCodmercadoria(codigo);
            mercadoria.setDescricao(request.getParameter("descricao"));
            mercadoria.setValorunitario(Double.parseDouble(request.getParameter("valorunitario")));
            mercadoria.setQuantidade(Double.parseDouble(request.getParameter("quantidade")));
            mercadoria.setEstoque(Double.parseDouble(request.getParameter("estoque")));
            mercadoria.setHoraatualizacao(Formatacao.getDataAtual());
            mercadoria.setTipo(request.getParameter("tipo").charAt(0));
            mercadoria.setSituacao(request.getParameter("situacao").charAt(0));

            String retorno = null;
        /*
        MercadoriaDAO mDAO = new MercadoriaDAO();
        Mercadoria m = (Mercadoria) new MercadoriaDAO().consultarId(cmerc);
        m.setCodmercadoria(cmerc);
        m.setEstoque(m.getEstoque() + (cquantmerc));
        System.out.println("Estoque aumentou para = " + m.getEstoque());

            */
            //System.out.println("Código = " + codigo);
            if (codigo == 0) {
                retorno = new MercadoriaDAO().salvar(mercadoria);
            } else {
                retorno = new MercadoriaDAO().atualizar(mercadoria);
            }

            // Se código = 0 é um registro novo. Se não, é uma edição.
            if (codigo == 0) {
                if (retorno == null) {
                    request.setAttribute("confirma_cadastro", "sucesso");
                } else {
                    request.setAttribute("confirma_cadastro", "erro");
                }
            } else {
                if (retorno == null) {
                    request.setAttribute("confirma_edicao", "sucesso");
                } else {
                    request.setAttribute("confirma_edicao", "erro");
                }
            }

            encaminharPagina("cadastro_mercadoria.jsp", request, response);
        }
        
        if (parametro.equals("cadastro_contasapagar")) {
            Contasapagar cpagar = new Contasapagar();

            int codigo = 0;
            if (request.getParameter("idconta") != null) {
                codigo = Integer.parseInt(request.getParameter("idconta"));
            }

            cpagar.setIdconta(codigo);
            cpagar.setCodfornecedor(Integer.parseInt(request.getParameter("codfornecedor")));
            cpagar.setDescricao(request.getParameter("descricao"));
            cpagar.setDataprevista(request.getParameter("dataprevista"));
            cpagar.setDatapagamento(request.getParameter("datapagamento"));
            cpagar.setValortotal(Double.parseDouble(request.getParameter("valortotal")));
            cpagar.setSituacao(request.getParameter("situacao").charAt(0));

            String retorno = null;

            //System.out.println("Código = " + codigo);
            if (codigo == 0) {
                retorno = new ContasapagarDAO().salvar(cpagar);
            } else {
                retorno = new ContasapagarDAO().atualizar(cpagar);
            }

            // Se código = 0 é um registro novo. Se não, é uma edição.
            if (codigo == 0) {
                if (retorno == null) {
                    request.setAttribute("confirma_cadastro", "sucesso");
                } else {
                    request.setAttribute("confirma_cadastro", "erro");
                }
            } else {
                if (retorno == null) {
                    request.setAttribute("confirma_edicao", "sucesso");
                } else {
                    request.setAttribute("confirma_edicao", "erro");
                }
            }

            encaminharPagina("cadastro_contasapagar.jsp", request, response);
        }
        
        if (parametro.equals("cadastro_cliente")) {
            Cliente cli = new Cliente();

            int codigo = 0;
            if (request.getParameter("codcli") != null) {
                codigo = Integer.parseInt(request.getParameter("codcli"));
            }

            cli.setCodcli(codigo);
            Cidade cid2 = (Cidade) new CidadeDAO().consultarId(Integer.parseInt(request.getParameter("codcid")));
            cli.setCidade(cid2);
            cli.setTipocadastro(request.getParameter("tipocadastro").charAt(0));
            cli.setNome(request.getParameter("nome"));
            cli.setCpfCnpj(request.getParameter("cpf_cnpj"));
            cli.setRua(request.getParameter("rua"));
            cli.setNumero(Integer.parseInt(request.getParameter("numero")));
            cli.setDatacadastro(request.getParameter("datacadastro"));
            cli.setSituacao(request.getParameter("situacao").charAt(0));

            String retorno = null;

            //System.out.println("Código = " + codigo);
            if (codigo == 0) {
                retorno = new ClienteDAO().salvar(cli);
            } else {
                retorno = new ClienteDAO().atualizar(cli);
            }

            // Se código = 0 é um registro novo. Se não, é uma edição.
            if (codigo == 0) {
                if (retorno == null) {
                    request.setAttribute("confirma_cadastro", "sucesso");
                } else {
                    request.setAttribute("confirma_cadastro", "erro");
                }
            } else {
                if (retorno == null) {
                    request.setAttribute("confirma_edicao", "sucesso");
                } else {
                    request.setAttribute("confirma_edicao", "erro");
                }
            }

            encaminharPagina("cadastro_cliente.jsp", request, response);
        }
        
        if (parametro.equals("relatorio_clienteporcidade")) {
         
           int codig = 0;
           codig = Integer.parseInt(request.getParameter("codcid"));
           byte[] bytes = new CidadeDAO().gerarListaCidades(codig);

        
           response.setContentType("application/pdf");
           response.setContentLength(bytes.length);
           ServletOutputStream outStream = response.getOutputStream();
           outStream.write(bytes, 0, bytes.length);
           outStream.flush();
           outStream.close();
        }
        
        if (parametro.equals("relatorio_vendasporperiodo")) {
         
           String inicio = request.getParameter("inicio");
           String fim = request.getParameter("fim");
            
           Timestamp tInicio = Timestamp.valueOf(inicio+" 00:00:00");
           Timestamp tFim = Timestamp.valueOf(fim+" 23:59:59");
            
           
           byte[] bytes = new VendaDAO().gerarListaVendasPeriodo(tInicio, tFim);

        
           response.setContentType("application/pdf");
           response.setContentLength(bytes.length);
           ServletOutputStream outStream = response.getOutputStream();
           outStream.write(bytes, 0, bytes.length);
           outStream.flush();
           outStream.close();
        }      
        /*
        if (parametro.equals("relatorio_vendasporcliente")) {
         
           int codcli = 0; 
           codcli = Integer.parseInt(request.getParameter("codcli"));
            
           byte[] bytes = new RelatorioDAO().relatorio_vendas_por_cliente(codcli);
           
           response.setContentType("application/pdf");
           response.setContentLength(bytes.length);
           ServletOutputStream outStream = response.getOutputStream();
           outStream.write(bytes, 0, bytes.length);
           outStream.flush();
           outStream.close();
        }*/      
        
        
        if (parametro.equals("relatorio_contasapagarporperiodo")) {
         
           String inicio = request.getParameter("inicio");
           String fim = request.getParameter("fim");
            
           Timestamp tInicio = Timestamp.valueOf(inicio+" 00:00:00");
           Timestamp tFim = Timestamp.valueOf(fim+" 23:59:59");
            
           
           byte[] bytes = new RelatorioDAO().gerarContasapagarnoperiodo(tInicio, tFim);

        
           response.setContentType("application/pdf");
           response.setContentLength(bytes.length);
           ServletOutputStream outStream = response.getOutputStream();
           outStream.write(bytes, 0, bytes.length);
           outStream.flush();
           outStream.close();
        }
        
        if (parametro.equals("relatorio_contasareceberporperiodo")) {
         
           String inicio = request.getParameter("inicio");
           String fim = request.getParameter("fim");
            
           Timestamp tInicio = Timestamp.valueOf(inicio+" 00:00:00");
           Timestamp tFim = Timestamp.valueOf(fim+" 23:59:59");
            
           
           byte[] bytes = new RelatorioDAO().gerarContasarecebernoperiodo(tInicio, tFim);

        
           response.setContentType("application/pdf");
           response.setContentLength(bytes.length);
           ServletOutputStream outStream = response.getOutputStream();
           outStream.write(bytes, 0, bytes.length);
           outStream.flush();
           outStream.close();
        }
        
        if (parametro.equals("relatorio_contasapagarporfornecedor")) {
         
           int codig = 0;
           codig = Integer.parseInt(request.getParameter("codfornecedor"));
           byte[] bytes = new RelatorioDAO().gerarListaFornecedores(codig);

        
           response.setContentType("application/pdf");
           response.setContentLength(bytes.length);
           ServletOutputStream outStream = response.getOutputStream();
           outStream.write(bytes, 0, bytes.length);
           outStream.flush();
           outStream.close();
        }
        
        if (parametro.equals("relatorio_funcionariosporadmissao")) {
         
           String inicio = request.getParameter("inicio");
            
           Timestamp tInicio = Timestamp.valueOf(inicio+" 00:00:00");
            
           
           byte[] bytes = new FuncionarioDAO().gerarFuncionariosporAdmissao(tInicio);

        
           response.setContentType("application/pdf");
           response.setContentLength(bytes.length);
           ServletOutputStream outStream = response.getOutputStream();
           outStream.write(bytes, 0, bytes.length);
           outStream.flush();
           outStream.close();
        }
        
        if (parametro.equals("pesquisa_venda_cliente"))
        {
            String pesquisa = request.getParameter("pesquisa");
            ArrayList<Object> venda = new VendaDAO().consultarComFiltro(pesquisa);
            request.setAttribute("pesquisa", venda);
            encaminharPagina("vendas_realizadas.jsp", request, response);
        }
        
        if (parametro.equals("logout")) {
            //System.out.println("LOGOUTTTTTT");
            sessao.invalidate();
            response.sendRedirect("login.jsp");
        }
        
        if (parametro.equals("pesquisa_usuario"))
        {
            ArrayList<Object> usuarioDAO = new UsuarioDAO().consultarComFiltro(request.getParameter("pesquisa"));
            request.setAttribute("pesquisa", usuarioDAO);
            encaminharPagina("listagem_usuario.jsp", request, response);
        } 
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void encaminharPagina(String pagina, HttpServletRequest request, HttpServletResponse response) {
        {
            try {
                request.setAttribute("destino", pagina);
                RequestDispatcher rd = request.getRequestDispatcher("modelo.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                System.out.println("Erro ao encaminhar página: " + e);
            }
        }
    }
    
        private String gerarCsvUsuario(ArrayList<Object> usuarioDAO) throws IOException
    {
        String retorno = null;
        try
        {
            NTSystem infoSystem = new NTSystem(); 
            FileWriter writer = new FileWriter("C:\\Users\\"+infoSystem.getName()+"\\Desktop\\listagem_usuario.csv");

            writer.append("CÓDIGO");
            writer.append(';');
            writer.append("NOME");
            writer.append(';');
            writer.append("EMAIL");
            writer.append(';');
            writer.append("SITUAÇÃO");
            writer.append('\n');
            
            for (int i = 0; i < usuarioDAO.size(); i++)
            {
                Usuario usuario = (Usuario) usuarioDAO.get(i);

                writer.append(String.valueOf(usuario.getCodusuario()));
                writer.append(';');
                writer.append(usuario.getNomeusuario());
                writer.append(';');
                writer.append(usuario.getEmail());
                writer.append(';');
                writer.append(usuario.getSituacao());
                writer.append('\n');
            }
            //generate whatever data you want
            writer.flush();
            writer.close();

            return "OK";
        } 
        catch (IOException e)
        {
            System.out.println("Erro ao gerar CSV: \n" + e);
            return "Erro ao gerar CSV: " +e;
        }
    }
    
}
