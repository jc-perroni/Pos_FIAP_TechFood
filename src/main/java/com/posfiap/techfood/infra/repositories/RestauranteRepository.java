package com.posfiap.techfood.infra.repositories;

import com.posfiap.techfood.infra.models.Endereco;
import com.posfiap.techfood.infra.models.Restaurante;
import com.posfiap.techfood.core.domain.enums.TipoEndereco;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
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
        String sql = """
        SELECT r.ID AS RESTAURANTE_ID, r.NOME, r.TELEFONE, r.ID_PROPRIETARIO,
               e.ID AS ENDERECO_ID, e.RUA, e.CEP, e.CIDADE, e.BAIRRO, e.COMPLEMENTO, e.NUMERO
        FROM RESTAURANTES r
        LEFT JOIN ENDERECOS e ON r.ID = e.ID_RESTAURANTE AND e.TIPO = 'RESTAURANTE'
        WHERE r.ID = :id
    """;
        return jdbcClient.sql(sql)
                .param("id", id)
                .query((rs, rowNum) -> {
                    Restaurante restaurante = new Restaurante();
                    Field idField = null;
                    try {
                        idField = Restaurante.class.getDeclaredField("id");
                    } catch (NoSuchFieldException e) {
                        throw new RuntimeException(e);
                    }
                    idField.setAccessible(true);
                    try {
                        idField.set(restaurante, rs.getLong("RESTAURANTE_ID"));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    Field idPropField = null;
                    try {
                        idPropField = Restaurante.class.getDeclaredField("idProprietario");
                    } catch (NoSuchFieldException e) {
                        throw new RuntimeException(e);
                    }
                    idPropField.setAccessible(true);
                    try {
                        idPropField.set(restaurante, rs.getLong("ID_PROPRIETARIO"));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    restaurante.setNome(rs.getString("NOME"));
                    restaurante.setTelefone(rs.getString("TELEFONE"));
                    long enderecoId = rs.getLong("ENDERECO_ID");
                    if (enderecoId != 0 && !rs.wasNull()) {
                        restaurante.setEndereco(
                                new Endereco(
                                        enderecoId,
                                        rs.getLong("RESTAURANTE_ID"),
                                        TipoEndereco.RESTAURANTE,
                                        rs.getString("RUA"),
                                        rs.getString("CEP"),
                                        rs.getString("CIDADE"),
                                        rs.getString("BAIRRO"),
                                        rs.getString("COMPLEMENTO"),
                                        rs.getString("NUMERO")
                                )
                        );
                    }
                    return restaurante;
                })
                .optional();
    }

    @Override
    public List<Restaurante> findAll(int size, int offset) {
        String sql = """
        SELECT r.ID AS RESTAURANTE_ID, r.NOME, r.TELEFONE, r.ID_PROPRIETARIO,
               e.ID AS ENDERECO_ID, e.RUA, e.CEP, e.CIDADE, e.BAIRRO, e.COMPLEMENTO, e.NUMERO
        FROM RESTAURANTES r
        LEFT JOIN ENDERECOS e ON r.ID = e.ID_RESTAURANTE AND e.TIPO = 'RESTAURANTE'
        ORDER BY r.ID, e.ID
        LIMIT :size OFFSET :offset
    """;
        return jdbcClient.sql(sql)
                .param("size", size)
                .param("offset", offset)
                .query((rs, rowNum) -> {
                    Restaurante restaurante = new Restaurante();
                    Field idField = null;
                    try {
                        idField = Restaurante.class.getDeclaredField("id");
                    } catch (NoSuchFieldException e) {
                        throw new RuntimeException(e);
                    }
                    idField.setAccessible(true);
                    try {
                        idField.set(restaurante, rs.getLong("RESTAURANTE_ID"));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    Field idPropField = null;
                    try {
                        idPropField = Restaurante.class.getDeclaredField("idProprietario");
                    } catch (NoSuchFieldException e) {
                        throw new RuntimeException(e);
                    }
                    idPropField.setAccessible(true);
                    try {
                        idPropField.set(restaurante, rs.getLong("ID_PROPRIETARIO"));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    restaurante.setNome(rs.getString("NOME"));
                    restaurante.setTelefone(rs.getString("TELEFONE"));
                    long enderecoId = rs.getLong("ENDERECO_ID");
                    if (enderecoId != 0 && !rs.wasNull()) {
                        restaurante.setEndereco(
                                new Endereco(
                                        enderecoId,
                                        rs.getLong("RESTAURANTE_ID"),
                                        TipoEndereco.RESTAURANTE,
                                        rs.getString("RUA"),
                                        rs.getString("CEP"),
                                        rs.getString("CIDADE"),
                                        rs.getString("BAIRRO"),
                                        rs.getString("COMPLEMENTO"),
                                        rs.getString("NUMERO")
                                )
                        );
                    }
                    return restaurante;
                })
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
                        INSERT INTO RESTAURANTES (NOME, TELEFONE, ID_PROPRIETARIO)
                        VALUES (:nome, :telefone, :idProprietario)
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
                        DELETE FROM RESTAURANTES
                        WHERE ID = :id
                        """
                )
                .param("id", id)
                .update();
    }
}