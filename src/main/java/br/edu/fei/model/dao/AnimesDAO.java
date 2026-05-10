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
public class AnimesDAO {
    
    public ArrayList<Animes> buscarPorNome(String nome) {
        ArrayList<Animes> lista = new ArrayList<>();

        String sql = "SELECT * FROM \"Animes\" WHERE LOWER(titulo) LIKE LOWER(?) ORDER BY titulo";

        try (Connection conn = new Conexao().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");

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
            System.out.println("Erro ao buscar anime:");
            System.out.println(e.getMessage());
        }

        return lista;
    }
}
