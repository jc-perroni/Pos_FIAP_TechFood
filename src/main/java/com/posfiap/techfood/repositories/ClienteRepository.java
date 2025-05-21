package com.posfiap.techfood.repositories;

import com.posfiap.techfood.models.Cliente;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

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
            SELECT * FROM CLIENTES
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
                        SELECT * FROM CLIENTES
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

    @Override
    public Integer save(Cliente cliente) {
        return jdbcClient
                .sql(
                        """
                        INSERT INTO CLIENTES (NOME, CPF, TELEFONE, EMAIL, LOGIN, PASSWORD)
                        VALUES (:nome, :cpf, :telefone, :email, :login, :password)
                        """
                )
                .param("nome", cliente.getNome())
                .param("cpf", cliente.getCPF())
                .param("telefone", cliente.getTelefone())
                .param("email", cliente.getEmail())
                .param("login", cliente.getLogin())
                .param("password", cliente.getPassword())
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
                        SELECT * FROM CLIENTES
                        WHERE LOGIN = :username
                        """
                )
                .param("username", username)
                .query(Cliente.class)
                .optional();
    }
}
