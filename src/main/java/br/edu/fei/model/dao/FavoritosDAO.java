/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.model.dao;

import br.edu.fei.model.Animes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author sabri
 */
public class FavoritosDAO {

    public boolean criarLista(int idUsuario, String nomeLista) {
        String sql = """
            INSERT INTO "ListasFavoritos" (nome, "idUsuario")
            VALUES (?, ?)
        """;

        try (Connection conn = new Conexao().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nomeLista);
            stmt.setInt(2, idUsuario);

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao criar lista de favoritos:");
            System.out.println(e.getMessage());
            return false;
        }
    }

    public int buscarIdListaDoUsuario(int idUsuario) {
        String sql = """
            SELECT "idLista"
            FROM "ListasFavoritos"
            WHERE "idUsuario" = ?
        """;

        try (Connection conn = new Conexao().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("idLista");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar lista do usuário:");
            System.out.println(e.getMessage());
        }

        return -1;
    }

    public String buscarNomeListaDoUsuario(int idUsuario) {
        String sql = """
            SELECT nome
            FROM "ListasFavoritos"
            WHERE "idUsuario" = ?
        """;

        try (Connection conn = new Conexao().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("nome");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar nome da lista:");
            System.out.println(e.getMessage());
        }

        return null;
    }

    public boolean editarNomeLista(int idUsuario, String novoNome) {
        String sql = """
            UPDATE "ListasFavoritos"
            SET nome = ?
            WHERE "idUsuario" = ?
        """;

        try (Connection conn = new Conexao().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novoNome);
            stmt.setInt(2, idUsuario);

            int linhasAfetadas = stmt.executeUpdate();

            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao editar nome da lista:");
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean excluirLista(int idUsuario) {
        String sql = """
            DELETE FROM "ListasFavoritos"
            WHERE "idUsuario" = ?
        """;

        try (Connection conn = new Conexao().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);

            int linhasAfetadas = stmt.executeUpdate();

            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir lista:");
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean adicionarAnimeNaLista(int idUsuario, int idAnime) {
        int idLista = buscarIdListaDoUsuario(idUsuario);

        if (idLista == -1) {
            System.out.println("Usuário ainda não possui lista de favoritos.");
            return false;
        }

        String sql = """
            INSERT INTO "ListaFavoritosAnimes" ("idLista", "idAnime")
            VALUES (?, ?)
        """;

        try (Connection conn = new Conexao().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idLista);
            stmt.setInt(2, idAnime);

            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar anime na lista:");
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean removerAnimeDaLista(int idUsuario, int idAnime) {
        int idLista = buscarIdListaDoUsuario(idUsuario);

        if (idLista == -1) {
            System.out.println("Usuário ainda não possui lista de favoritos.");
            return false;
        }

        String sql = """
            DELETE FROM "ListaFavoritosAnimes"
            WHERE "idLista" = ?
            AND "idAnime" = ?
        """;

        try (Connection conn = new Conexao().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idLista);
            stmt.setInt(2, idAnime);

            int linhasAfetadas = stmt.executeUpdate();

            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao remover anime da lista:");
            System.out.println(e.getMessage());
            return false;
        }
    }

    public ArrayList<Animes> listarAnimesFavoritos(int idUsuario) {
        ArrayList<Animes> lista = new ArrayList<>();

        String sql = """
            SELECT a."idAnime", a.titulo, a.descricao, a.duracao, a.genero
            FROM "ListaFavoritosAnimes" lfa
            JOIN "ListasFavoritos" lf ON lfa."idLista" = lf."idLista"
            JOIN "Animes" a ON lfa."idAnime" = a."idAnime"
            WHERE lf."idUsuario" = ?
            ORDER BY a.titulo
        """;

        try (Connection conn = new Conexao().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);

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
            System.out.println("Erro ao listar animes favoritos:");
            System.out.println(e.getMessage());
        }

        return lista;
    }
}
