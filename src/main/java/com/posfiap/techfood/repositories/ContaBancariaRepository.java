package com.posfiap.techfood.repositories;

import com.posfiap.techfood.models.ContaBancaria;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ContaBancariaRepository implements CrudRepository<ContaBancaria> {
    private final JdbcClient jdbcClient;

    ContaBancariaRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<ContaBancaria> findById(Long id) {
        return jdbcClient
                .sql(
                        """
                        SELECT * FROM CONTA_BANCARIA
                        WHERE ID = :id
                        """
                )
                .param("id", id)
                .query(ContaBancaria.class)
                .optional();
    }

    @Override
    public List<ContaBancaria> findAll(int size, int offset) {
        return jdbcClient
                .sql(
                        """
                        SELECT * FROM CONTA_BANCARIA
                        LIMIT :size
                        OFFSET :offset
                        """
                )
                .param("size", size)
                .param("offset", offset)
                .query(ContaBancaria.class)
                .list();
    }

    @Override
    public Integer update(ContaBancaria contaBancaria, long id) {
        return jdbcClient
                .sql(
                        """
                        UPDATE CONTA_BANCARIA SET NUMERO = :numero, BANCO = :banco, TELEFONE = :telefone, EMAIL = :email
                        WHERE ID = :id
                        """
                )
                .param("numero", contaBancaria.numeroConta())
                .param("banco", contaBancaria.banco())
                .param("id", id)
                .update();

    }

    @Override
    public Integer save(ContaBancaria contaBancaria) {
        return jdbcClient
                .sql(
                        """
                        INSERT INTO CONTA_BANCARIA (NUMERO, BANCO)
                        VALUES (:numero, :banco)
                        """
                )
                .param("numero", contaBancaria.numeroConta())
                .param("banco", contaBancaria.banco())
                .update();
    }

    @Override
    public Integer delete(long id) {
        return jdbcClient
                .sql(
                        """
                        DELETE CONTA_BANCARIA
                        WHERE ID = :id
                        """
                )
                .param("id", id)
                .update();
    }
}