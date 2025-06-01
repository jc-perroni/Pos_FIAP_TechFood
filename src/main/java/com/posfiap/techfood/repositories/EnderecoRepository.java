package com.posfiap.techfood.repositories;

import com.posfiap.techfood.models.Endereco;
import com.posfiap.techfood.models.enums.TipoEndereco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
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
                .query((queryResult, row) -> new Endereco(
                        queryResult.getLong("ID"),
                        queryResult.getString("ID_CLIENTE") == null ? queryResult.getString("ID_RESTAURANTE") : queryResult.getString("ID_CLIENTE"),
                        queryResult.getString("TIPO").equals(TipoEndereco.CLIENTE.toString()) ? TipoEndereco.CLIENTE : TipoEndereco.RESTAURANTE,
                        queryResult.getString("RUA"),
                        queryResult.getString("CEP"),
                        queryResult.getString("CIDADE"),
                        queryResult.getString("BAIRRO"),
                        queryResult.getString("COMPLEMENTO"),
                        queryResult.getString("NUMERO")))
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
                .query((queryResult, row) -> new Endereco(
                        queryResult.getLong("ID"),
                        queryResult.getString("ID_CLIENTE") == null ? queryResult.getString("ID_RESTAURANTE") : queryResult.getString("ID_CLIENTE"),
                        queryResult.getString("TIPO").equals(TipoEndereco.CLIENTE.toString()) ? TipoEndereco.CLIENTE : TipoEndereco.RESTAURANTE,
                        queryResult.getString("RUA"),
                        queryResult.getString("CEP"),
                        queryResult.getString("CIDADE"),
                        queryResult.getString("BAIRRO"),
                        queryResult.getString("COMPLEMENTO"),
                        queryResult.getString("NUMERO")))
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
        log.info("body: " + String.valueOf(endereco));
        if (endereco.tipoEndereco() == TipoEndereco.CLIENTE) {
            return jdbcClient
                    .sql(
                            """
                            INSERT INTO ENDERECOS (ID_CLIENTE, RUA, CIDADE, COMPLEMENTO,
                            BAIRRO, NUMERO, CEP, TIPO)
                            VALUES (:idEntidade, :rua, :cidade, :complemento, :bairro, :numero, :cep, :tipoEndereco)
                            """
                    )
                    .param("idEntidade", endereco.idEntidade())
                    .param("rua", endereco.rua())
                    .param("cidade", endereco.cidade())
                    .param("complemento", endereco.complemento())
                    .param("bairro", endereco.bairro())
                    .param("numero", endereco.numero())
                    .param("cep", endereco.cep())
                    .param("tipoEndereco", TipoEndereco.CLIENTE.toString())
                    .update();
        }

        return jdbcClient
                .sql(
                        """
                                INSERT INTO ENDERECOS (ID_RESTAURANTE, RUA, CIDADE, COMPLEMENTO,
                                BAIRRO, NUMERO, CEP, TIPO)
                                VALUES (:idEntidade, :rua, :cidade, :complemento, :bairro, :numero, :cep, :tipoEndereco)
                                """
                )
                .param("idEntidade", endereco.idEntidade())
                .param("rua", endereco.rua())
                .param("cidade", endereco.cidade())
                .param("complemento", endereco.complemento())
                .param("bairro", endereco.bairro())
                .param("numero", endereco.numero())
                .param("cep", endereco.cep())
                .param("tipoEndereco", TipoEndereco.RESTAURANTE.toString())
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