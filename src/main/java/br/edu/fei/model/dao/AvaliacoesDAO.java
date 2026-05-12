/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import br.edu.fei.model.Animes;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    
    public ArrayList<Animes> listarPorTipo(int idUsuario, String tipo) {
        ArrayList<Animes> lista = new ArrayList<>();

        String sql = """
            SELECT a."idAnime", a.titulo, a.descricao, a.duracao, a.genero
            FROM "Avaliacoes" av
            JOIN "Animes" a ON av."idAnime" = a."idAnime"
            WHERE av."idUsuario" = ?
            AND av.tipo = ?
            ORDER BY a.titulo
        """;

        try (Connection conn = new Conexao().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            stmt.setString(2, tipo);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Animes anime = new Animes();

                anime.setIdAnimes(rs.getInt("idAnime"));
                anime.setTitulo(rs.getString("titulo"));
                anime.setDescricao(rs.getString("descricao"));
                anime.setDuracao(rs.getInt("duracao"));
                anime.setGenero(rs.getString("genero"));

                lista.add(anime);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar avaliações:");
            System.out.println(e.getMessage());
        }

        return lista;
    }
    
}
    