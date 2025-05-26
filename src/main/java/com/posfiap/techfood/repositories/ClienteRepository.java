package com.posfiap.techfood.repositories;

import com.posfiap.techfood.models.Cliente;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepository implements CrudRepository<Cliente> {

    private final JdbcClient jdbcClient;

    ClienteRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return jdbcClient
                .sql(
                        """
                        SELECT c.*, u.*
                        FROM CLIENTES c
                        INNER JOIN USUARIOS u ON c.USERNAME = u.USERNAME
                        WHERE ID = :id
                        """
        )
                .param("id", id)
                .query(Cliente.class)
                .optional();
    }

    @Override
    public List<Cliente> findAll(int size, int offset) {
        return jdbcClient
                .sql(
                        """
                        SELECT c.*, u.*
                        FROM CLIENTES c
                        INNER JOIN USUARIOS u ON c.USERNAME = u.USERNAME
                        LIMIT :size
                        OFFSET :offset
                        """
                )
                .param("size", size)
                .param("offset", offset)
                .query(Cliente.class)
                .list();
    }

    @Override
    public Integer update(Cliente cliente, long id) {
        return jdbcClient
                .sql(
                        """
                        UPDATE CLIENTES SET NOME = :nome, CPF = :cpf, TELEFONE = :telefone, EMAIL = :email
                        WHERE ID = :id
                        """
                )
                .param("nome", cliente.getNome())
                .param("cpf", cliente.getCPF())
                .param("telefone", cliente.getTelefone())
                .param("email", cliente.getEmail())
                .param("id", id)
                .update();

    }
    @Transactional
    @Override
    public Integer save(Cliente cliente) {
        return jdbcClient
                .sql(
                        """
                        INSERT INTO USUARIOS (USERNAME, PASSWORD, DATA_CRIACAO_CONTA)
                        VALUES (:username, :password, :dataCriacao);
                        INSERT INTO CLIENTES (NOME, CPF, TELEFONE, EMAIL, USERNAME)
                        VALUES (:nome, :cpf, :telefone, :email, :username)
                        """
                )
                .param("nome", cliente.getNome())
                .param("cpf", cliente.getCPF())
                .param("telefone", cliente.getTelefone())
                .param("email", cliente.getEmail())
                .param("username", cliente.getUsername())
                .param("password", cliente.getPassword())
                .param("dataCriacao", cliente.getDataCriacaoConta())
                .update();
    }

    @Override
    public Integer delete(long id) {
        return jdbcClient
                .sql(
                        """
                        DELETE CLIENTES
                        WHERE ID = :id
                        """
                )
                .param("id", id)
                .update();
    }

    public Optional<Cliente> findByUsername(String username) {
        return jdbcClient
                .sql(
                        """
                        SELECT c.*, u.*
                        FROM CLIENTES c
                        INNER JOIN USUARIOS u ON c.USERNAME = u.USERNAME
                        WHERE u.USERNAME = :username
                        """
                )
                .param("username", username)
                .query(Cliente.class)
                .optional();
    }
}
