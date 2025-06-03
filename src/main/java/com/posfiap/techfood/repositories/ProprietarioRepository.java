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
            SELECT p.*, u.*
            FROM PROPRIETARIOS p
            INNER JOIN USUARIOS u ON p.USERNAME = u.USERNAME
            WHERE ID = :id
            """
        )
                .param("id", id)
                .query(Proprietario.class)
                .optional();
    }

    @Override
    public List<Proprietario> findAll(int size, int offset) {
        return jdbcClient
                .sql(
                        """
                        SELECT p.*, u.*
                        FROM PROPRIETARIOS p
                        INNER JOIN USUARIOS u ON p.USERNAME = u.USERNAME
                        LIMIT :size
                        OFFSET :offset
                        """
                )
                .param("size", size)
                .param("offset", offset)
                .query(Proprietario.class)
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
                .param("cpf", proprietario.getCpf())
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
                .param("cpf", proprietario.getCpf())
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

    public Integer updatePassword(Proprietario proprietario) {
        return jdbcClient
                .sql(
                        """
                        UPDATE USUARIOS SET PASSWORD = :password, DATA_ALTERACAO_SENHA = :dataAlteracaoSenha
                        WHERE USERNAME = :username
                        """
                )
                .param("username", proprietario.getUsername())
                .param("password", proprietario.getPassword())
                .param("dataAlteracaoSenha", proprietario.getDataAlteracaoSenha())
                .update();
    }
}
