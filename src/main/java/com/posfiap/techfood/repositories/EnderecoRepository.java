package com.posfiap.techfood.repositories;

import com.posfiap.techfood.models.Endereco;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EnderecoRepository implements CrudRepository<Endereco> {
    private final JdbcClient jdbcClient;

    EnderecoRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Endereco> findById(Long id) {
        return jdbcClient
                .sql(
                        """
                        SELECT * FROM ENDERECOS
                        WHERE ID = :id
                        """
                )
                .param("id", id)
                .query(Endereco.class)
                .optional();
    }

    @Override
    public List<Endereco> findAll(int size, int offset) {
        return jdbcClient
                .sql(
                        """
                        SELECT * FROM ENDERECOS
                        LIMIT :size
                        OFFSET :offset
                        """
                )
                .param("size", size)
                .param("offset", offset)
                .query(Endereco.class)
                .list();
    }

    @Override
    public Integer update(Endereco endereco, long id) {
        return jdbcClient
                .sql(
                        """
                        UPDATE ENDERECOS SET RUA = :rua, CIDADE = :cidade, COMPLEMENTO = :complemento, BAIRRO = :bairro,
                            NUMERO =:numero, CEP = :cep
                        WHERE ID = :id
                        """
                )
                .param("rua", endereco.rua())
                .param("cidade", endereco.cidade())
                .param("complemento", endereco.complemento())
                .param("bairro", endereco.bairro())
                .param("numero", endereco.numero())
                .param("cep", endereco.cep())
                .param("id", id)
                .update();

    }

    @Override
    public Integer save(Endereco endereco) {
        return jdbcClient
                .sql(
                        """
                                INSERT INTO ENDERECOS (RUA, CIDADE, COMPLEMENTO, BAIRRO, NUMERO, CEP)
                                VALUES (:rua, :cidade, :complemento, :bairro, :numero, :cep)
                                """
                )
                .param("rua", endereco.rua())
                .param("cidade", endereco.cidade())
                .param("complemento", endereco.complemento())
                .param("bairro", endereco.bairro())
                .param("numero", endereco.numero())
                .param("cep", endereco.cep())
                .update();
    }

    @Override
    public Integer delete(long id) {
        return jdbcClient
                .sql(
                        """
                        DELETE ENDERECOS
                        WHERE ID = :id
                        """
                )
                .param("id", id)
                .update();
    }
}