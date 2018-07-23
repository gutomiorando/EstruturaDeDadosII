/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.UsuarioDAO;
import entidade.Usuario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Usuario
 */
public class ControlaUsuario {

    public boolean autenticarUsuario(HttpServletRequest request, HttpServletResponse response) {
        Usuario u = new Usuario();
        
        u.setNomeusuario(String.valueOf(request.getParameter("nomeusuario")));
        u.setEmail(String.valueOf(request.getParameter("email")));
        u.setSenha(String.valueOf(request.getParameter("senha")));
        u.setSituacao(request.getParameter("situacao").charAt(0));
        

        if (new UsuarioDAO().consultar(u)) {
            HttpSession sessao = request.getSession();
            sessao.setAttribute("usuarioLogado", u);

            return true;
        } else {
            return false;
        }
    }

    public boolean cadastrarUsuario(HttpServletRequest request, HttpServletResponse response) {
        Usuario u = new Usuario();

        int id = Integer.parseInt(String.valueOf((request.getParameter("codusuario"))));

        u.setCodusuario(id);
        u.setNomeusuario(request.getParameter("nomeusuario"));
        u.setEmail(request.getParameter("email"));
        u.setSenha(request.getParameter("senha"));
        u.setSituacao(request.getParameter("situacao").charAt(0));

        String retorno = null;

        if (id == 0) {
            retorno = new UsuarioDAO().salvar(u);
        } else {
            retorno = new UsuarioDAO().atualizar(u);
        }

        if (retorno == null) {
            return true;
        } else {
            return false;
        }
    }

}
