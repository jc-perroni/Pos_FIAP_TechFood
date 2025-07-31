package com.posfiap.techfood.infra.repositories;

import com.posfiap.techfood.infra.exceptions.ResourceNotFoundException;
import com.posfiap.techfood.infra.models.Endereco;
import com.posfiap.techfood.infra.models.enums.TipoEndereco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Repository
public class EnderecoRepository implements CrudRepository<Endereco> {
    private final JdbcClient jdbcClient;

    EnderecoRepository(JdbcClient jdbcClient) {
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
                        queryResult.getString("ID_CLIENTE") == null ? queryResult.getLong("ID_RESTAURANTE") : queryResult.getLong("ID_CLIENTE"),
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
                        queryResult.getString("ID_CLIENTE") == null ? queryResult.getLong("ID_RESTAURANTE") : queryResult.getLong("ID_CLIENTE"),
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
                                UPDATE ENDERECOS SET TIPO = :tipoEndereco ::tipo_Endereco,  RUA = :rua,
                                    CIDADE = :cidade, COMPLEMENTO = :complemento, BAIRRO = :bairro,
                                    NUMERO =:numero, CEP = :cep
                                WHERE ID = :id
                                """
                )
                .param("tipoEndereco", endereco.getTipoEndereco().toString())
                .param("rua", endereco.getRua())
                .param("cidade", endereco.getCidade())
                .param("complemento", endereco.getComplemento())
                .param("bairro", endereco.getBairro())
                .param("numero", endereco.getNumero())
                .param("cep", endereco.getCep())
                .param("id", id)
                .update();

    }

    @Override
    public Integer save(Endereco endereco) {
        // Tratativa para caso o Id do cliente ou do Restaurante não exista;
        try {
            if (Objects.equals(endereco.getTipoEndereco().toString(), TipoEndereco.CLIENTE.toString())) {
                return jdbcClient
                        .sql(
                                """
                                        INSERT INTO ENDERECOS (ID_CLIENTE, RUA, CIDADE, COMPLEMENTO,
                                        BAIRRO, NUMERO, CEP, TIPO)
                                        VALUES (:idEntidade, :rua, :cidade, :complemento, :bairro, :numero, :cep, :tipoEndereco::tipo_endereco)
                                        """
                        )
                        .param("idEntidade", endereco.getIdEntidade())
                        .param("rua", endereco.getRua())
                        .param("cidade", endereco.getCidade())
                        .param("complemento", endereco.getComplemento())
                        .param("bairro", endereco.getBairro())
                        .param("numero", endereco.getNumero())
                        .param("cep", endereco.getCep())
                        .param("tipoEndereco", endereco.getTipoEndereco().toString())
                        .update();
            }

            return jdbcClient
                    .sql(
                            """
                                    INSERT INTO ENDERECOS (ID_RESTAURANTE, RUA, CIDADE, COMPLEMENTO,
                                    BAIRRO, NUMERO, CEP, TIPO)
                                    VALUES (:idEntidade, :rua, :cidade, :complemento, :bairro, :numero, :cep, :tipoEndereco::tipo_endereco)
                                    """
                    )
                    .param("idEntidade", endereco.getIdEntidade())
                    .param("rua", endereco.getRua())
                    .param("cidade", endereco.getCidade())
                    .param("complemento", endereco.getComplemento())
                    .param("bairro", endereco.getBairro())
                    .param("numero", endereco.getNumero())
                    .param("cep", endereco.getCep())
                    .param("tipoEndereco", endereco.getTipoEndereco().toString())
                    .update();

        } catch (DataIntegrityViolationException ex) {
            Throwable rootCause = ex.getRootCause();

            if (rootCause instanceof SQLException sqlEx) {
                String message = sqlEx.getMessage();

                if (message.contains("foreign key") || message.contains("constraint") || message.contains("violates foreign key constraint")) {
                    // Mensagens de erro diferentes para cliente e para restaurante
                    if (endereco.getTipoEndereco() == TipoEndereco.CLIENTE) {
                        throw new ResourceNotFoundException("ID de cliente não existe para atrela-lo a este endereço");
                    } else {
                        throw new ResourceNotFoundException("ID de restaurante não existe para atrela-lo a este endereço");
                    }
                }
            }

            throw ex;
        }
    }

    @Override
    public Integer delete(long id) {
        return jdbcClient
                .sql(
                        """
                                DELETE FROM ENDERECOS
                                WHERE ID = :id
                                """
                )
                .param("id", id)
                .update();
    }
}