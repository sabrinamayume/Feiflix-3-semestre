package br.edu.fei.controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import br.edu.fei.model.Usuarios;
import br.edu.fei.model.dao.UsuariosDAO;
import br.edu.fei.view.JFrameMenu;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import br.edu.fei.view.JFrameCadastro;

/**
 *
 * @author sabri
 */
public class LoginController {
    
    private UsuariosDAO usuariosDAO;

    public LoginController() {
        this.usuariosDAO = new UsuariosDAO();
    }

    public Usuarios autenticar(String email, String senha) {
        if (email == null || email.trim().isEmpty()) {
            return null;
        }

        if (senha == null || senha.trim().isEmpty()) {
            return null;
        }

        return usuariosDAO.login(email, senha);
    }

    public void abrirMenu(JFrame telaAtual, Usuarios usuarioLogado) {
        JFrameMenu menu = new JFrameMenu(usuarioLogado);
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);

        telaAtual.dispose();
    }

    public void exibirMensagemCamposVazios(JFrame telaAtual) {
        JOptionPane.showMessageDialog(telaAtual, "Preencha email e senha.");
    }

    public void exibirMensagemLoginInvalido(JFrame telaAtual) {
        JOptionPane.showMessageDialog(telaAtual, "Email ou senha inválidos.");
    }

    public void exibirMensagemBoasVindas(JFrame telaAtual, Usuarios usuarioLogado) {
        JOptionPane.showMessageDialog(telaAtual, "Bem-vindo(a), " + usuarioLogado.getNome());
    }
    
    public void abrirCadastro(JFrame telaAtual) {
        JFrameCadastro cadastro = new JFrameCadastro();
        cadastro.setLocationRelativeTo(null);
        cadastro.setVisible(true);

        telaAtual.dispose();
    }
}
