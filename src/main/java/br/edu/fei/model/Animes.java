/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.model;

/**
 *
 * @author sabri
 */
public class Animes {
    private int idAnimes;
    private String titulo;
    private String descricao;
    private int duracao;
    private String genero;

    public Animes() {
    }

    public Animes(int idAnime, String titulo, String descricao, int duracao, String genero) {
        this.idAnimes = idAnime;
        this.titulo = titulo;
        this.descricao = descricao;
        this.duracao = duracao;
        this.genero = genero;
    }
    
    /**
     * @return the id
     */
    public int getIdAnimes() {
        return idAnimes;
    }

    /**
     * @param idAnimes the id to set
     */
    public void setIdAnimes(int idAnimes) {
        this.idAnimes = idAnimes;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the duracao
     */
    public int getDuracao() {
        return duracao;
    }

    /**
     * @param duracao the duracao to set
     */
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    /**
     * @return the genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }
    
}
