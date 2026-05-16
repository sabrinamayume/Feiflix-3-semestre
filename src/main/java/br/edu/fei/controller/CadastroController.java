/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.controller;

import br.edu.fei.model.Usuarios;
import br.edu.fei.model.dao.UsuariosDAO;
import br.edu.fei.view.JFrameLogin;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
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
        public void cadastrar(
            JFrame telaAtual,
            JTextField txtNome,
            JTextField txtEmail,
            JPasswordField txtSenha,
            JComboBox<String> cbSexo
    ) {
        String nome = txtNome.getText();
        String email = txtEmail.getText();
        String senha = new String(txtSenha.getPassword());
        String sexo = cbSexo.getSelectedItem().toString();

        if (nome.trim().isEmpty()
                || email.trim().isEmpty()
                || senha.trim().isEmpty()
                || sexo.equalsIgnoreCase("Selecione")) {

            JOptionPane.showMessageDialog(telaAtual, "Preencha todos os campos.");
            return;
        }

        Usuarios usuario = new Usuarios(nome, email, senha, sexo);

        boolean cadastrou = usuariosDAO.cadastrar(usuario);

        if (cadastrou) {
            JOptionPane.showMessageDialog(telaAtual, "Usuário cadastrado com sucesso!");
            abrirLogin(telaAtual);
        } else {
            JOptionPane.showMessageDialog(telaAtual, "Erro ao cadastrar usuário. Verifique se o email já existe.");
        }
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
