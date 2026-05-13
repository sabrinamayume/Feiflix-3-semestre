/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.controller;

import br.edu.fei.model.Animes;
import br.edu.fei.model.Usuarios;
import br.edu.fei.model.dao.AnimesDAO;
import br.edu.fei.model.dao.AvaliacoesDAO;
import br.edu.fei.model.dao.FavoritosDAO;
import br.edu.fei.view.JFrameMenu;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sabri
 */
public class BuscarAnimeController {

    private AnimesDAO animesDAO;
    private AvaliacoesDAO avaliacoesDAO;
    private FavoritosDAO favoritosDAO;

    public BuscarAnimeController() {
        this.animesDAO = new AnimesDAO();
        this.avaliacoesDAO = new AvaliacoesDAO();
        this.favoritosDAO = new FavoritosDAO();
    }

    public void buscarAnime(JTextField txtBusca, JTable tabela, JFrame telaAtual) {
        String nomeBusca = txtBusca.getText();

        if (nomeBusca.trim().isEmpty()) {
            JOptionPane.showMessageDialog(telaAtual, "Digite o nome do anime.");
            return;
        }

        ArrayList<Animes> lista = animesDAO.buscarPorNome(nomeBusca);

        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setRowCount(0);

        for (Animes anime : lista) {
            modelo.addRow(new Object[]{
                anime.getIdAnimes(),
                anime.getTitulo(),
                anime.getDescricao(),
                anime.getDuracao(),
                anime.getGenero()
            });
        }

        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(telaAtual, "Nenhum anime encontrado.");
        }
    }

    public void curtirAnime(Usuarios usuarioLogado, JTable tabela, JFrame telaAtual) {
        int idAnime = pegarIdAnimeSelecionado(tabela);

        if (idAnime == -1) {
            JOptionPane.showMessageDialog(telaAtual, "Selecione um anime na tabela.");
            return;
        }

        boolean sucesso = avaliacoesDAO.avaliarAnime(
                usuarioLogado.getIdUsuario(),
                idAnime,
                "CURTIDO"
        );

        if (sucesso) {
            JOptionPane.showMessageDialog(telaAtual, "Anime curtido!");
        } else {
            JOptionPane.showMessageDialog(telaAtual, "Erro ao curtir anime.");
        }
    }

    public void descurtirAnime(Usuarios usuarioLogado, JTable tabela, JFrame telaAtual) {
        int idAnime = pegarIdAnimeSelecionado(tabela);

        if (idAnime == -1) {
            JOptionPane.showMessageDialog(telaAtual, "Selecione um anime na tabela.");
            return;
        }

        boolean sucesso = avaliacoesDAO.avaliarAnime(
                usuarioLogado.getIdUsuario(),
                idAnime,
                "DESCURTIDO"
        );

        if (sucesso) {
            JOptionPane.showMessageDialog(telaAtual, "Anime descurtido!");
        } else {
            JOptionPane.showMessageDialog(telaAtual, "Erro ao descurtir anime.");
        }
    }

    public void adicionarAosFavoritos(Usuarios usuarioLogado, JTable tabela, JFrame telaAtual) {
        int idAnime = pegarIdAnimeSelecionado(tabela);

        if (idAnime == -1) {
            JOptionPane.showMessageDialog(telaAtual, "Selecione um anime na tabela.");
            return;
        }

        int idLista = favoritosDAO.buscarIdListaDoUsuario(usuarioLogado.getIdUsuario());

        if (idLista == -1) {
            JOptionPane.showMessageDialog(telaAtual, "Você ainda não criou uma lista de favoritos.");
            return;
        }

        boolean adicionou = favoritosDAO.adicionarAnimeNaLista(
                usuarioLogado.getIdUsuario(),
                idAnime
        );

        if (adicionou) {
            JOptionPane.showMessageDialog(telaAtual, "Anime adicionado aos favoritos!");
        } else {
            JOptionPane.showMessageDialog(telaAtual, "Erro ao adicionar anime. Talvez ele já esteja na lista.");
        }
    }

    public void voltarParaMenu(JFrame telaAtual, Usuarios usuarioLogado) {
        JFrameMenu menu = new JFrameMenu(usuarioLogado);
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);

        telaAtual.dispose();
    }

    private int pegarIdAnimeSelecionado(JTable tabela) {
        int linha = tabela.getSelectedRow();

        if (linha == -1) {
            return -1;
        }

        int linhaModelo = tabela.convertRowIndexToModel(linha);
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();

        return Integer.parseInt(modelo.getValueAt(linhaModelo, 0).toString());
    }
}
