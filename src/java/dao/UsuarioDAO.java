/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import apoio.IDAO;
import entidade.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class UsuarioDAO implements IDAO {

    @Override
    public String salvar(Object o) {
        Usuario u = (Usuario) o;

        try {
            String sql = "INSERT INTO usuario VALUES (DEFAULT,"
                    + "'" + u.getNomeusuario() + "', "
                    + "'" + u.getEmail() + "', "
                    + "'" + u.getSenha() + "', "
                    + "'A'"
                    + ")";

            int resultado = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Erro ao salvar usuário: " + e);
            return e.toString();
        }

        return null;
    }

    @Override
    public String atualizar(Object o) {
        Usuario u = (Usuario) o;

        try {
            String sql = "UPDATE usuario SET "
                    + "nomeusuario = '" + u.getNomeusuario() + "', "
                    + "email = '" + u.getEmail() + "', "
                    + "senha = '" + u.getSenha() + "', "
                    + "situacao = '" + u.getSituacao() + "' "
                    + "WHERE codusuario = " + u.getCodusuario() + "";

            int resultado = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao atualizar usuário: " + e);
            return e.toString();
        }

        return null;
    }

    @Override
    public String excluir(int id) {
        try {
            String sql = "UPDATE usuario SET "
                    + "situacao = 'I' "
                    + "WHERE codusuario = " + id + "";

            int resultado = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao inativar/excluir usuário: " + e);
            return e.toString();
        }

        return null;
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        ArrayList<Object> usuarios = new ArrayList<>();

        try {
            String sql = "SELECT * FROM USUARIO ORDER BY nomeusuario";

            ResultSet retorno = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (retorno.next()) {
                Usuario u = new Usuario();

                u.setCodusuario(retorno.getInt("codusuario"));
                u.setNomeusuario(retorno.getString("nomeusuario"));
                u.setEmail(retorno.getString("email"));
                u.setSituacao(retorno.getString("situacao").charAt(0));

                usuarios.add(u);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar usuários: " + e);
            return null;
        }

        return usuarios;
    }

    @Override
    public boolean verificarRegistroUnico(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object consultarId(int id) {
        try {
            String sql = "SELECT * FROM USUARIO WHERE codusuario = " + id;

            ResultSet retorno = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (retorno.next()) {
                Usuario u = new Usuario();

                u.setCodusuario(retorno.getInt("codusuario"));
                u.setNomeusuario(retorno.getString("nomeusuario"));
                u.setEmail(retorno.getString("email"));
                u.setSituacao(retorno.getString("situacao").charAt(0));

                return u;
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar usuário id: " + e);
            return null;
        }
        return null;
    }

    @Override
    public boolean consultar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean testaUsu(String login, String senha) throws Exception {

        Statement st = ConexaoBD.getInstance().getConnection().createStatement();
        boolean validate = false;

        String sql = "SELECT * FROM usuario "
                + "WHERE nomeusuario = '" + login + "' "
                + "AND senha = '" + senha + "' "
                + "AND situacao = 'A' ";

        ResultSet resultado = st.executeQuery(sql);

        if (resultado.next()) {
            validate = true;
        } else {
            validate = false;

        }
        return validate;
    }

    public Object consultarPorNome(String nome) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM USUARIO WHERE nomeusuario = '" + nome + "'";

            ResultSet retorno = st.executeQuery(sql);

            if (retorno.next()) {
                Usuario user = new Usuario();

                user.setCodusuario(retorno.getInt("codusuario"));
                user.setNomeusuario(retorno.getString("nomeusuario"));
                user.setEmail(retorno.getString("email"));
                user.setSituacao(retorno.getString("situacao").charAt(0));

                return user;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao consultar usuário pelo Login: " + e);
            return e.toString();
        }
    }

    public ArrayList<Object> consultarComFiltro(String criterio)
    {
        ArrayList<Object> usuario = new ArrayList<>();

        try
        {
//            Statement st = bilhetario.Bilhetario.conexao.createStatement();
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM usuario WHERE nomeusuario ILIKE '%" + criterio + "%'"
                    + "order by nomeusuario";

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next())
            {
                Usuario user = new Usuario();
                user.setCodusuario(resultado.getInt("codusuario"));
                user.setNomeusuario(resultado.getString("nomeusuario"));
                user.setEmail(resultado.getString("email"));
                user.setSituacao(resultado.getString("situacao").charAt(0));

                usuario.add(user);
            }

        }
        catch (SQLException e)
        {
            System.out.println("Erro ao consultar Usuários: " + e);
        }

        return usuario;
    }
    
}
