/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

 /*
 * @author sabri
 */
package br.edu.fei.feiflix;

import br.edu.fei.view.JFrameLogin;



public class Feiflix {

    public static void main(String[] args) {
        System.out.println("Abrindo programa..."); 
        JFrameLogin login = new JFrameLogin(); //chama a tela de login
        login.setLocationRelativeTo(null); // centraliza a janela na tela
        login.setVisible(true); // mostra a janela
      
    }
        
        
        
//TESTE DE CRIAÇÃO DE LISTA DE FAVORITOS
//        import br.edu.fei.model.dao.FavoritosDAO;
//
//        
//        FavoritosDAO favoritosDAO = new FavoritosDAO();
//
//            boolean criou = favoritosDAO.criarLista(1, "Meus Favoritos");
//
//            if (criou) {
//                System.out.println("Lista criada com sucesso!");
//            } else {
//                System.out.println("Erro ao criar lista.");
//            }
//    }
     

// TESTE DE LOGIN
//import br.edu.fei.model.Usuarios;
//import br.edu.fei.model.dao.UsuariosDAO;
//
//        UsuariosDAO usuarioDAO = new UsuariosDAO();
//
//    Usuarios usuarioLogado = usuarioDAO.login("sabrina2@email.com", "123A");
//
//    if (usuarioLogado != null) {
//        System.out.println("Login realizado com sucesso!");
//        System.out.println("Bem-vinda, " + usuarioLogado.getNome());
//    } else {
//        System.out.println("Email ou senha inválidos.");
//    }
    

//  TESTE DE CADASTRO
//import br.edu.fei.model.Usuarios;
//import br.edu.fei.model.dao.UsuariosDAO;
//import br.edu.fei.model.dao.Conexao;
//import java.sql.Connection;
//import java.sql.SQLException;
//
//        UsuariosDAO usuarioDAO = new UsuariosDAO();
//
//        Usuarios novoUsuario = new Usuarios(
//                "Sabrina",
//                "sabrina2@email.com",
//                "123",
//                "Feminino"
//        );
//
//        boolean cadastrou = usuarioDAO.cadastrar(novoUsuario);
//
//        if (cadastrou) {
//            System.out.println("Cadastro testado com sucesso!");
//        } else {
//            System.out.println("Cadastro falhou.");
//        }
//
//        Usuarios usuarioLogado = usuarioDAO.login("sabrina2@email.com", "123");
//
//        if (usuarioLogado != null) {
//            System.out.println("Login realizado com sucesso!");
//            System.out.println("Bem-vinda, " + usuarioLogado.getNome());
//        } else {
//            System.out.println("Email ou senha inválidos.");
//        }


//  *CÓDIGO PARA VERIFICAR SE A CONEXÃO COM O BANCO FOI BEM SUCEDIDA
//	
//	try {
//            Conexao conexaoBanco = new Conexao();
//
//            Connection conexao = conexaoBanco.getConnection();
//
//            System.out.println("Teste de conexão finalizado com sucesso!");
//
//            conexao.close();
//
//        } catch (SQLException e) {
//            System.out.println("Erro ao conectar com o banco:");
//            System.out.println(e.getMessage());
//        }
    
}