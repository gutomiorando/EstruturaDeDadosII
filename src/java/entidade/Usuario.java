/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

/**
 *
 * @author Usuario
 */
public class Usuario {

    private int codusuario;
    private String nomeusuario;
    private String email;
    private String senha;
    private char situacao;

    public Usuario() {
        codusuario = 0;
        nomeusuario = "";
        email = "";
        senha = "";
        situacao = 'A';
    }

    /**
     * @return the codusuario
     */
    public int getCodusuario() {
        return codusuario;
    }

    /**
     * @param codusuario the codusuario to set
     */
    public void setCodusuario(int codusuario) {
        this.codusuario = codusuario;
    }

    /**
     * @return the nomeusuario
     */
    public String getNomeusuario() {
        return nomeusuario;
    }

    /**
     * @param nomeusuario the nomeusuario to set
     */
    public void setNomeusuario(String nomeusuario) {
        this.nomeusuario = nomeusuario;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the situacao
     */
    public char getSituacao() {
        return situacao;
    }

    /**
     * @param situacao the situacao to set
     */
    public void setSituacao(char situacao) {
        this.situacao = situacao;
    }

}
