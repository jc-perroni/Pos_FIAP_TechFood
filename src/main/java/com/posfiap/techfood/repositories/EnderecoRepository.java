package com.posfiap.techfood.repositories;

import com.posfiap.techfood.models.Endereco;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EnderecoRepository {
    private final JdbcClient jdbcClient;

    EnderecoRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }
    public Optional<Endereco> findByIdforUser(Long id) {
        return jdbcClient
                .sql(
                        """
                        SELECT * FROM ENDERECOS
                        WHERE ID = :id AND TIPO_ENDERECO = CLIENTE
                        """
                )
                .param("id", id)
                .query(Endereco.class)
                .optional();
    }

    public Optional<Endereco> findByIdForRestaurant(Long id) {
        return jdbcClient
                .sql(
                        """
                        SELECT * FROM ENDERECOS
                        WHERE ID = :id AND TIPO_ENDERECO = RESTAURANTE
                        """
                )
                .param("id", id)
                .query(Endereco.class)
                .optional();
    }

    public List<Endereco> findAllForUsers(int size, int offset) {
        return jdbcClient
                .sql(
                        """
                        SELECT * FROM ENDERECOS WHERE TIPO_ENDERECO= CLIENTE
                        LIMIT :size
                        OFFSET :offset
                        """
                )
                .param("size", size)
                .param("offset", offset)
                .query(Endereco.class)
                .list();
    }

    public List<Endereco> findAllForRestaurants(int size, int offset) {
        return jdbcClient
                .sql(
                        """
                        SELECT * FROM ENDERECOS WHERE TIPO_ENDERECO= RESTAURANTE
                        LIMIT :size
                        OFFSET :offset
                        """
                )
                .param("size", size)
                .param("offset", offset)
                .query(Endereco.class)
                .list();
    }

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

    public Integer saveForClient(Endereco endereco) {
        return jdbcClient
                .sql(
                        """
                                INSERT INTO ENDERECOS (ID_CLIENTE, RUA, CIDADE, COMPLEMENTO, BAIRRO, NUMERO, CEP, TIPO_ENDERECO)
                                VALUES (:idCliente, :rua, :cidade, :complemento, :bairro, :numero, :cep, "CLIENTE")
                                """
                )
                .param("idCliente", endereco.idFonte())
                .param("rua", endereco.rua())
                .param("cidade", endereco.cidade())
                .param("complemento", endereco.complemento())
                .param("bairro", endereco.bairro())
                .param("numero", endereco.numero())
                .param("cep", endereco.cep())
                .update();
    }

    public Integer saveForRestaurant(Endereco endereco) {
        return jdbcClient
                .sql(
                        """
                                INSERT INTO ENDERECOS (ID_CLIENTE, RUA, CIDADE, COMPLEMENTO, BAIRRO, NUMERO, CEP, TIPO_ENDERECO)
                                VALUES (:idCliente, :rua, :cidade, :complemento, :bairro, :numero, :cep, "RESTAURANTE")
                                """
                )
                .param("idRestaurante", endereco.idFonte())
                .param("rua", endereco.rua())
                .param("cidade", endereco.cidade())
                .param("complemento", endereco.complemento())
                .param("bairro", endereco.bairro())
                .param("numero", endereco.numero())
                .param("cep", endereco.cep())
                .update();
    }

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