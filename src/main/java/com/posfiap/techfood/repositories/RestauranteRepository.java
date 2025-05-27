package com.posfiap.techfood.repositories;

import com.posfiap.techfood.models.Restaurante;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RestauranteRepository implements CrudRepository<Restaurante> {
    private final JdbcClient jdbcClient;

    RestauranteRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Restaurante> findById(Long id) {
        return jdbcClient
                .sql(
                        """
                        SELECT * FROM RESTAURANTES
                        WHERE ID = :id
                        """
                )
                .param("id", id)
                .query(Restaurante.class)
                .optional();
    }

    @Override
    public List<Restaurante> findAll(int size, int offset) {
        return jdbcClient
                .sql(
                        """
                        SELECT * FROM RESTAURANTES
                        LIMIT :size
                        OFFSET :offset
                        """
                )
                .param("size", size)
                .param("offset", offset)
                .query(Restaurante.class)
                .list();
    }

    @Override
    public Integer update(Restaurante restaurante, long id) {
        return jdbcClient
                .sql(
                        """
                        UPDATE RESTAURANTES SET NOME = :nome, TELEFONE = :telefone,
                        ID_PROPRIETARIO = :idProprietario
                        WHERE ID = :id
                        """
                )
                .param("nome", restaurante.getNome())
                .param("telefone", restaurante.getTelefone())
                .param("id", id)
                .param("idProprietario", restaurante.getIdProprietario())
                .update();

    }

    @Override
    public Integer save(Restaurante restaurante) {
        return jdbcClient
                .sql(
                        """
                        INSERT INTO RESTAURANTES SET NOME = :nome, TELEFONE = :telefone,
                        ID_PROPRIETARIO = :idProprietario
                        """
                )
                .param("nome", restaurante.getNome())
                .param("telefone", restaurante.getTelefone())
                .param("idProprietario", restaurante.getIdProprietario())
                .update();
    }

    @Override
    public Integer delete(long id) {
        return jdbcClient
                .sql(
                        """
                        DELETE RESTAURANTES
                        WHERE ID = :id
                        """
                )
                .param("id", id)
                .update();
    }
}