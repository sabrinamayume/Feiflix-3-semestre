/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.controller;

import br.edu.fei.model.Usuarios;
import br.edu.fei.model.dao.UsuariosDAO;
import br.edu.fei.view.JFrameLogin;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 *
 * @author sabri
 */
public class CadastroController {
    
    private UsuariosDAO usuariosDAO;

    public CadastroController() {
        this.usuariosDAO = new UsuariosDAO();
    }

    public boolean cadastrarUsuario(String nome, String email, String senha, String sexo) {
        if (nome == null || nome.trim().isEmpty()) {
            return false;
        }

        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        if (senha == null || senha.trim().isEmpty()) {
            return false;
        }

        if (sexo == null || sexo.trim().isEmpty() || sexo.equalsIgnoreCase("Selecione")) {
            return false;
        }

        Usuarios usuario = new Usuarios(nome, email, senha, sexo);

        return usuariosDAO.cadastrar(usuario);
    }

    public void abrirLogin(JFrame telaAtual) {
        JFrameLogin login = new JFrameLogin();
        login.setLocationRelativeTo(null);
        login.setVisible(true);

        telaAtual.dispose();
    }

    public void exibirMensagemCamposVazios(JFrame telaAtual) {
        JOptionPane.showMessageDialog(telaAtual, "Preencha todos os campos.");
    }

    public void exibirMensagemCadastroSucesso(JFrame telaAtual) {
        JOptionPane.showMessageDialog(telaAtual, "Usuário cadastrado com sucesso!");
    }

    public void exibirMensagemCadastroErro(JFrame telaAtual) {
        JOptionPane.showMessageDialog(telaAtual, "Erro ao cadastrar usuário. Verifique se o email já existe.");
    }
}
