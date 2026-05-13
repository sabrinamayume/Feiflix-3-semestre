/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.controller;

import br.edu.fei.model.Animes;
import br.edu.fei.model.Usuarios;
import br.edu.fei.model.dao.AvaliacoesDAO;
import br.edu.fei.view.JFrameMenu;

import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sabri
 */
public class AvaliacoesController {

    private AvaliacoesDAO avaliacoesDAO;

    public AvaliacoesController() {
        this.avaliacoesDAO = new AvaliacoesDAO();
    }

    public void buscarAvaliacoes(Usuarios usuarioLogado, JComboBox<String> comboBox, JTable tabela, JFrame telaAtual) {
        String opcaoSelecionada = comboBox.getSelectedItem().toString();

        String tipo;

        if (opcaoSelecionada.equalsIgnoreCase("Curtidos")) {
            tipo = "CURTIDO";
        } else if (opcaoSelecionada.equalsIgnoreCase("Descurtidos")) {
            tipo = "DESCURTIDO";
        } else {
            tipo = opcaoSelecionada;
        }

        ArrayList<Animes> lista = avaliacoesDAO.listarPorTipo(
                usuarioLogado.getIdUsuario(),
                tipo
        );

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
            JOptionPane.showMessageDialog(telaAtual, "Nenhuma avaliação encontrada.");
        }
    }

    public void voltarParaMenu(JFrame telaAtual, Usuarios usuarioLogado) {
        JFrameMenu menu = new JFrameMenu(usuarioLogado);
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);

        telaAtual.dispose();
    }
}
