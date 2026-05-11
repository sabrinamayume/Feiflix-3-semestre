/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author sabri
 */
//Esse método serve tanto para curtir quanto para descurtir.
//Se o usuário ainda não avaliou o anime, ele cria uma avaliação nova.
//Se o usuário já avaliou, ele atualiza de CURTIDO para DESCURTIDO, ou o contrário.

public class AvaliacoesDAO {

    public boolean avaliarAnime(int idUsuario, int idAnime, String tipo) {
        String sql = """
            INSERT INTO "Avaliacoes" ("idUsuario", "idAnime", tipo)
            VALUES (?, ?, ?)
            ON CONFLICT ("idUsuario", "idAnime")
            DO UPDATE SET tipo = EXCLUDED.tipo
        """;

        try (Connection conn = new Conexao().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idAnime);
            stmt.setString(3, tipo);

            stmt.executeUpdate();

            System.out.println("Avaliação registrada com sucesso!");
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao avaliar anime:");
            System.out.println(e.getMessage());
            return false;
        }
    }
}