/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.controller;


import br.edu.fei.model.Animes;
import br.edu.fei.model.Usuarios;
import br.edu.fei.model.dao.FavoritosDAO;
import br.edu.fei.view.JFrameMenu;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author sabri
 */
public class FavoritosController {

    private FavoritosDAO favoritosDAO;

    public FavoritosController() {
        this.favoritosDAO = new FavoritosDAO();
    }

    public void configurarTabela(JTable tabela) {
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Título");
        modelo.addColumn("Descrição");
        modelo.addColumn("Duração em minutos");
        modelo.addColumn("Gênero");

        tabela.setModel(modelo);
    }

    public void carregarLista(Usuarios usuarioLogado, JLabel lblNomeLista, JTable tabela) {
        int idLista = favoritosDAO.buscarIdListaDoUsuario(usuarioLogado.getIdUsuario());

        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setRowCount(0);

        if (idLista == -1) {
            lblNomeLista.setText("Você ainda não criou uma lista de favoritos.");
            return;
        }

        lblNomeLista.setText("Meus Favoritos");

        ArrayList<Animes> favoritos = favoritosDAO.listarAnimesFavoritos(usuarioLogado.getIdUsuario());

        for (Animes anime : favoritos) {
            modelo.addRow(new Object[]{
                anime.getIdAnimes(),
                anime.getTitulo(),
                anime.getDescricao(),
                anime.getDuracao(),
                anime.getGenero()
            });
        }
    }

    public void criarLista(Usuarios usuarioLogado, JLabel lblNomeLista, JTable tabela, JFrame telaAtual) {
        boolean criou = favoritosDAO.criarLista(usuarioLogado.getIdUsuario());

        if (criou) {
            JOptionPane.showMessageDialog(telaAtual, "Lista de favoritos criada com sucesso!");
            carregarLista(usuarioLogado, lblNomeLista, tabela);
        } else {
            JOptionPane.showMessageDialog(telaAtual, "Erro ao criar lista. Talvez você já tenha uma lista.");
        }
    }

    public void excluirLista(Usuarios usuarioLogado, JLabel lblNomeLista, JTable tabela, JFrame telaAtual) {
        int idLista = favoritosDAO.buscarIdListaDoUsuario(usuarioLogado.getIdUsuario());

        if (idLista == -1) {
            JOptionPane.showMessageDialog(telaAtual, "Você ainda não tem uma lista para excluir.");
            return;
        }

        int opcao = JOptionPane.showConfirmDialog(
                telaAtual,
                "Deseja realmente excluir sua lista de favoritos?",
                "Confirmar exclusão",
                JOptionPane.YES_NO_OPTION
        );

        if (opcao == JOptionPane.YES_OPTION) {
            boolean excluiu = favoritosDAO.excluirLista(usuarioLogado.getIdUsuario());

            if (excluiu) {
                JOptionPane.showMessageDialog(telaAtual, "Lista excluída com sucesso!");
                carregarLista(usuarioLogado, lblNomeLista, tabela);
            } else {
                JOptionPane.showMessageDialog(telaAtual, "Erro ao excluir lista.");
            }
        }
    }

    public void removerAnime(Usuarios usuarioLogado, JTable tabela, JLabel lblNomeLista, JFrame telaAtual) {
        int idAnime = pegarIdAnimeSelecionado(tabela);

        if (idAnime == -1) {
            JOptionPane.showMessageDialog(telaAtual, "Selecione um anime para remover.");
            return;
        }

        boolean removeu = favoritosDAO.removerAnimeDaLista(
                usuarioLogado.getIdUsuario(),
                idAnime
        );

        if (removeu) {
            JOptionPane.showMessageDialog(telaAtual, "Anime removido dos favoritos.");
            carregarLista(usuarioLogado, lblNomeLista, tabela);
        } else {
            JOptionPane.showMessageDialog(telaAtual, "Erro ao remover anime.");
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
