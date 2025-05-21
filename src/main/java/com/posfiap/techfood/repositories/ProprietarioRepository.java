package com.posfiap.techfood.repositories;

import com.posfiap.techfood.models.Proprietario;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

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
            SELECT * FROM PROPRIETARIOS
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
                        SELECT * FROM PROPRIETARIOS
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
                .param("cpf", proprietario.getCPF())
                .param("telefone", proprietario.getTelefone())
                .param("email", proprietario.getEmail())
                .param("id", id)
                .update();

    }

    @Override
    public Integer save(Proprietario proprietario) {
        return jdbcClient
                .sql(
                        """
                        INSERT INTO PROPRIETARIOS (NOME, CPF, TELEFONE, EMAIL, LOGIN, PASSWORD)
                        VALUES (:nome, :cpf, :telefone, :email, :login, :password)
                        """
                )
                .param("nome", proprietario.getNome())
                .param("cpf", proprietario.getCPF())
                .param("telefone", proprietario.getTelefone())
                .param("email", proprietario.getEmail())
                .param("login", proprietario.getLogin())
                .param("password", proprietario.getPassword())
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
                        SELECT * FROM PROPRIETARIOS
                        WHERE LOGIN = :username
                        """
                )
                .param("username", username)
                .query(Proprietario.class)
                .optional();
    }

}
