/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.model;

/**
 *
 * @author sabri
 */
public class Usuarios {
    private int idUsuario;
    private String nome;
    private String email;
    private String senha;
    private String sexo;
 
    /**
     * construtor
    */
    public Usuarios() {
    }
    
    /**
     * usado antes de salvar no banco
    */
    public Usuarios(String nome, String email, String senha, String sexo) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.sexo = sexo;
    }
    
    /**
     * usado depois de buscar no banco
    */
    public Usuarios(int idUsuario, String nome, String email, String senha, String sexo) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.sexo = sexo;
    }
    
    
    /**
     * @return the idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the id to set
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
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
     * @return the sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
}
