/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.controller;

import br.edu.fei.model.Usuarios;
import br.edu.fei.view.JFrameAvaliacoes;
import br.edu.fei.view.JFrameBuscarAnime;
import br.edu.fei.view.JFrameFavoritos;
import javax.swing.JFrame;
/**
 *
 * @author sabri
 */
public class MenuController {

    public String gerarMensagemBoasVindas(Usuarios usuarioLogado) {
        return "Olá, " + usuarioLogado.getNome();
    }

    public void abrirTelaBuscarAnime(JFrame telaAtual, Usuarios usuarioLogado) {
        JFrameBuscarAnime tela = new JFrameBuscarAnime(usuarioLogado);
        tela.setLocationRelativeTo(null);
        tela.setVisible(true);

        telaAtual.dispose();
    }

    public void abrirTelaFavoritos(JFrame telaAtual, Usuarios usuarioLogado) {
        JFrameFavoritos tela = new JFrameFavoritos(usuarioLogado);
        tela.setLocationRelativeTo(null);
        tela.setVisible(true);

        telaAtual.dispose();
    }

    public void abrirTelaAvaliacoes(JFrame telaAtual, Usuarios usuarioLogado) {
        JFrameAvaliacoes tela = new JFrameAvaliacoes(usuarioLogado);
        tela.setLocationRelativeTo(null);
        tela.setVisible(true);

        telaAtual.dispose();
    }

    public void sairDoSistema() {
        System.out.println("Saindo do programa...");
        System.exit(0);
    }
}
