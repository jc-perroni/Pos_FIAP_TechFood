package com.posfiap.techfood.repositories;

import com.posfiap.techfood.models.Cliente;
import com.posfiap.techfood.models.Proprietario;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class ProprietarioRepository implements CrudRepository<Proprietario>{

    private final JdbcClient jdbcClient;

    ProprietarioRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Proprietario> findById(Long id) {
        return jdbcClient
                .sql(
            """
            SELECT p.ID, p.CPF, p.NOME, p.EMAIL, p.TELEFONE, p.USERNAME, p.DATA_CRIACAO_CONTA, p.DATA_ALTERACAO_CONTA, p.DATA_ALTERACAO_SENHA, e.*
            FROM PROPRIETARIOS p
            INNER JOIN USUARIOS u ON p.USERNAME = u.ID_PROPRIETARIO
            LEFT JOIN RESTAURANTES r ON p.USERNAME = r.ID_PROPRIETARIO
            WHERE p.ID = :id
            """
        )
                .param("id", id)
                .query((queryResult, row) -> new Proprietario(
                        queryResult.getString("TELEFONE"),
                        queryResult.getString("CPF"),
                        queryResult.getString("NOME"),
                        queryResult.getString("EMAIL"),
                        queryResult.getString("USERNAME"),
                        "CONFIDENCIAL",
                        queryResult.getDate("DATA_CRIACAO_CONTA") != null ? queryResult.getDate("DATA_CRIACAO_CONTA").toLocalDate() : null,
                        queryResult.getDate("DATA_ALTERACAO_CONTA") != null ? queryResult.getDate("DATA_ALTERACAO_CONTA").toLocalDate() : null,
                        queryResult.getDate("DATA_ALTERACAO_SENHA") != null ? queryResult.getDate("DATA_ALTERACAO_SENHA").toLocalDate() : null))
                .optional();
    }

    @Override
    public List<Proprietario> findAll(int size, int offset) {
        return jdbcClient
                .sql(
                        """
                        SELECT p.*, u.*, r.*
                        FROM PROPRIETARIOS p
                        INNER JOIN USUARIOS u ON p.USERNAME = u.USERNAME
                        LEFT JOIN RESTAURANTES r ON p.USERNAME = r.ID_PROPRIETARIO
                        LIMIT :size
                        OFFSET :offset
                        """
                )
                .param("size", size)
                .param("offset", offset)
                .query((queryResult, row) -> new Proprietario(
                        queryResult.getString("TELEFONE"),
                        queryResult.getString("CPF"),
                        queryResult.getString("NOME"),
                        queryResult.getString("EMAIL"),
                        queryResult.getString("USERNAME"),
                        "CONFIDENCIAL",
                        queryResult.getDate("DATA_CRIACAO_CONTA") != null ? queryResult.getDate("DATA_CRIACAO_CONTA").toLocalDate() : null,
                        queryResult.getDate("DATA_ALTERACAO_CONTA") != null ? queryResult.getDate("DATA_ALTERACAO_CONTA").toLocalDate() : null,
                        queryResult.getDate("DATA_ALTERACAO_SENHA") != null ? queryResult.getDate("DATA_ALTERACAO_SENHA").toLocalDate() : null))
                .list();
    }

    @Override
    public Integer update(Proprietario proprietario, long id) {
        return jdbcClient
                .sql(
                        """
                        UPDATE PROPRIETARIOS SET NOME = :nome, CPF = :cpf, TELEFONE = :telefone, EMAIL = :email
                        WHERE ID = :id
                        """
                )
                .param("nome", proprietario.getNome())
                .param("cpf", proprietario.getCPF())
                .param("telefone", proprietario.getTelefone())
                .param("email", proprietario.getEmail())
                .param("id", id)
                .update();
    }

    @Transactional
    @Override
    public Integer save(Proprietario proprietario) {
        return jdbcClient
                .sql(
                        """
                        INSERT INTO USUARIOS (USERNAME, PASSWORD, DATA_CRIACAO_CONTA)
                        VALUES (:username, :password, :dataCriacao);
                        INSERT INTO PROPRIETARIOS (NOME, CPF, TELEFONE, EMAIL, USERNAME)
                        VALUES (:nome, :cpf, :telefone, :email, :username);
                        """
                )
                .param("nome", proprietario.getNome())
                .param("cpf", proprietario.getCPF())
                .param("telefone", proprietario.getTelefone())
                .param("email", proprietario.getEmail())
                .param("username", proprietario.getUsername())
                .param("password", proprietario.getPassword())
                .param("dataCriacao", proprietario.getDataCriacaoConta())
                .update();
    }

    @Override
    public Integer delete(long id) {
        return jdbcClient
                .sql(
                        """
                        DELETE PROPRIETARIOS
                        WHERE ID = :id
                        """
                )
                .param("id", id)
                .update();
    }

    public Optional<Proprietario> findByUsername(String username) {
        return jdbcClient
                .sql(
                        """
                        SELECT p.*, u.*
                        FROM PROPRIETARIOS p
                        INNER JOIN USUARIOS u ON p.USERNAME = u.USERNAME
                        WHERE p.USERNAME = :username
                        """
                )
                .param("username", username)
                .query(Proprietario.class)
                .optional();
    }
}
