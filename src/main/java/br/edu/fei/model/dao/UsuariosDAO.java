/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.model.dao;

import br.edu.fei.model.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author sabri
 */
public class UsuariosDAO {

    public boolean cadastrar(Usuarios usuario) {
        String sql = "INSERT INTO \"Usuarios\" (nome, email, senha, sexo) VALUES (?, ?, ?, ?)";

        try (Connection conn = new Conexao().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getSexo());

            stmt.executeUpdate();

            System.out.println("Usuário cadastrado com sucesso!");
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar usuário:");
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Usuarios login(String email, String senha) {
        String sql = "SELECT * FROM \"Usuarios\" WHERE email = ? AND senha = ?";

        try (Connection conn = new Conexao().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuarios usuario = new Usuarios();

                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setSexo(rs.getString("sexo"));

                return usuario;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao fazer login:");
            System.out.println(e.getMessage());
        }

        return null;
    }
}