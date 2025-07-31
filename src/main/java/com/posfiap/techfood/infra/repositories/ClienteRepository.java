package com.posfiap.techfood.infra.repositories;

import com.posfiap.techfood.infra.models.enums.TipoEndereco;
import com.posfiap.techfood.infra.models.Cliente;
import com.posfiap.techfood.infra.models.Endereco;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.*;

@Repository
public class ClienteRepository implements CrudRepository<Cliente> {

    private final JdbcClient jdbcClient;

    ClienteRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        String sql = """
        SELECT
            c.ID AS CLIENTE_ID,
            c.NOME AS CLIENTE_NOME,
            c.CPF AS CLIENTE_CPF,
            c.TELEFONE AS CLIENTE_TELEFONE,
            c.EMAIL AS CLIENTE_EMAIL,
            c.USERNAME AS CLIENTE_USERNAME,
            u.DATA_CRIACAO_CONTA AS USUARIO_DATA_CRIACAO_CONTA,
            u.DATA_ALTERACAO_CONTA AS USUARIO_DATA_ALTERACAO_CONTA,
            u.DATA_ALTERACAO_SENHA AS USUARIO_DATA_ALTERACAO_SENHA,
            e.ID AS ENDERECO_ID,
            e.ID_CLIENTE AS ENDERECO_ID_CLIENTE,
            e.TIPO AS ENDERECO_TIPO,
            e.RUA AS ENDERECO_RUA,
            e.CEP AS ENDERECO_CEP,
            e.CIDADE AS ENDERECO_CIDADE,
            e.BAIRRO AS ENDERECO_BAIRRO,
            e.COMPLEMENTO AS ENDERECO_COMPLEMENTO,
            e.NUMERO AS ENDERECO_NUMERO
        FROM CLIENTES c
        INNER JOIN USUARIOS u ON c.USERNAME = u.USERNAME
        LEFT JOIN ENDERECOS e ON c.ID = e.ID_CLIENTE
        WHERE c.ID = :id
    """;

        Map<Long, Cliente> clienteMap = new LinkedHashMap<>();
        jdbcClient.sql(sql)
                .param("id", id)
                .query((rs, rowNum) -> {
                    Long clienteId = rs.getLong("CLIENTE_ID");
                    Cliente cliente = clienteMap.get(clienteId);
                    if (cliente == null) {
                        cliente = new Cliente(
                                rs.getString("CLIENTE_NOME"),
                                rs.getString("CLIENTE_EMAIL"),
                                rs.getString("CLIENTE_TELEFONE"),
                                rs.getString("CLIENTE_CPF"),
                                rs.getString("CLIENTE_USERNAME"),
                                "CONFIDENCIAL",
                                rs.getObject("USUARIO_DATA_CRIACAO_CONTA", LocalDate.class),
                                rs.getObject("USUARIO_DATA_ALTERACAO_CONTA", LocalDate.class),
                                rs.getObject("USUARIO_DATA_ALTERACAO_SENHA", LocalDate.class)
                        );
                        Field idField = null;
                        try {
                            idField = cliente.getClass().getSuperclass().getDeclaredField("id");
                        } catch (NoSuchFieldException e) {
                            throw new RuntimeException(e);
                        }
                        idField.setAccessible(true);
                        try {
                            idField.set(cliente, clienteId);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                        clienteMap.put(clienteId, cliente);
                    }
                    Long enderecoId = rs.getLong("ENDERECO_ID");
                    if (enderecoId != 0 && !rs.wasNull()) {
                        cliente.getEnderecos().add(
                                new Endereco(
                                        enderecoId,
                                        rs.getLong("ENDERECO_ID_CLIENTE"),
                                        TipoEndereco.valueOf(rs.getString("ENDERECO_TIPO")),
                                        rs.getString("ENDERECO_RUA"),
                                        rs.getString("ENDERECO_CEP"),
                                        rs.getString("ENDERECO_CIDADE"),
                                        rs.getString("ENDERECO_BAIRRO"),
                                        rs.getString("ENDERECO_COMPLEMENTO"),
                                        rs.getString("ENDERECO_NUMERO")
                                )
                        );
                    }
                    return cliente;
                })
                .list();
        return clienteMap.values().stream().findFirst();
    }

    @Override
    public List<Cliente> findAll(int size, int offset) {
        String sql = """
        SELECT
            c.ID AS CLIENTE_ID,
            c.NOME AS CLIENTE_NOME,
            c.CPF AS CLIENTE_CPF,
            c.TELEFONE AS CLIENTE_TELEFONE,
            c.EMAIL AS CLIENTE_EMAIL,
            c.USERNAME AS CLIENTE_USERNAME,
            u.DATA_CRIACAO_CONTA AS USUARIO_DATA_CRIACAO_CONTA,
            u.DATA_ALTERACAO_CONTA AS USUARIO_DATA_ALTERACAO_CONTA,
            u.DATA_ALTERACAO_SENHA AS USUARIO_DATA_ALTERACAO_SENHA,
            e.ID AS ENDERECO_ID,
            e.ID_CLIENTE AS ENDERECO_ID_CLIENTE,
            e.TIPO AS ENDERECO_TIPO,
            e.RUA AS ENDERECO_RUA,
            e.CEP AS ENDERECO_CEP,
            e.CIDADE AS ENDERECO_CIDADE,
            e.BAIRRO AS ENDERECO_BAIRRO,
            e.COMPLEMENTO AS ENDERECO_COMPLEMENTO,
            e.NUMERO AS ENDERECO_NUMERO
        FROM CLIENTES c
        INNER JOIN USUARIOS u ON c.USERNAME = u.USERNAME
        LEFT JOIN ENDERECOS e ON c.ID = e.ID_CLIENTE
        ORDER BY c.ID, e.ID
        LIMIT :size OFFSET :offset
    """;

        Map<Long, Cliente> clienteMap = new LinkedHashMap<>();
        jdbcClient.sql(sql)
                .param("size", size)
                .param("offset", offset)
                .query((rs, rowNum) -> {
                    Long clienteId = rs.getLong("CLIENTE_ID");
                    Cliente cliente = clienteMap.get(clienteId);
                    if (cliente == null) {
                        cliente = new Cliente(
                                rs.getString("CLIENTE_NOME"),
                                rs.getString("CLIENTE_EMAIL"),
                                rs.getString("CLIENTE_TELEFONE"),
                                rs.getString("CLIENTE_CPF"),
                                rs.getString("CLIENTE_USERNAME"),
                                "CONFIDENCIAL",
                                rs.getObject("USUARIO_DATA_CRIACAO_CONTA", LocalDate.class),
                                rs.getObject("USUARIO_DATA_ALTERACAO_CONTA", LocalDate.class),
                                rs.getObject("USUARIO_DATA_ALTERACAO_SENHA", LocalDate.class)
                        );
                        Field idField = null;
                        try {
                            idField = cliente.getClass().getSuperclass().getDeclaredField("id");
                        } catch (NoSuchFieldException e) {
                            throw new RuntimeException(e);
                        }
                        idField.setAccessible(true);
                        try {
                            idField.set(cliente, clienteId);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                        clienteMap.put(clienteId, cliente);
                    }
                    Long enderecoId = rs.getLong("ENDERECO_ID");
                    if (enderecoId != 0 && !rs.wasNull()) {
                        cliente.getEnderecos().add(
                                new Endereco(
                                        enderecoId,
                                        rs.getLong("ENDERECO_ID_CLIENTE"),
                                        TipoEndereco.valueOf(rs.getString("ENDERECO_TIPO")),
                                        rs.getString("ENDERECO_RUA"),
                                        rs.getString("ENDERECO_CEP"),
                                        rs.getString("ENDERECO_CIDADE"),
                                        rs.getString("ENDERECO_BAIRRO"),
                                        rs.getString("ENDERECO_COMPLEMENTO"),
                                        rs.getString("ENDERECO_NUMERO")
                                )
                        );
                    }
                    return cliente;
                })
                .list();

        return new ArrayList<>(clienteMap.values());
    }

    @Override
    public Integer update(Cliente cliente, long id) {
        return jdbcClient
                .sql(
                        """
                        UPDATE CLIENTES SET NOME = :nome, CPF = :cpf, TELEFONE = :telefone, EMAIL = :email
                        WHERE ID = :id;
                        UPDATE USUARIOS SET DATA_ALTERACAO_CONTA = :dataAltarecaoConta
                        WHERE USERNAME = :username;
                        """
                )
                .param("nome", cliente.getNome())
                .param("cpf", cliente.getCpf())
                .param("telefone", cliente.getTelefone())
                .param("email", cliente.getEmail())
                .param("username", cliente.getUsername())
                .param("dataAltarecaoConta", cliente.getDataAlteracaoConta())
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
                .param("cpf", cliente.getCpf())
                .param("telefone", cliente.getTelefone())
                .param("email", cliente.getEmail())
                .param("username", cliente.getUsername())
                .param("password", cliente.getPassword())
                .param("dataCriacao", cliente.getDataCriacaoConta())
                .update();
    }

    @Transactional
    @Override
    public Integer delete(long id) {
        return jdbcClient
                .sql(
                        """
                        DELETE FROM CLIENTES
                        WHERE ID = :id;
                        """
                )
                .param("id", id)
                .update();
    }

    @Transactional
    public Integer deleteUsuario(String username) {
        return jdbcClient
                .sql(
                        """
                        DELETE FROM USUARIOS
                        WHERE USERNAME = :username;
                        """
                )
                .param("username", username)
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

    public Integer updatePassword(Cliente cliente) {
        return jdbcClient
                .sql(
                        """
                        UPDATE USUARIOS SET PASSWORD = :password, DATA_ALTERACAO_SENHA = :dataAlteracaoSenha
                        WHERE USERNAME = :username
                        """
                )
                .param("username", cliente.getUsername())
                .param("password", cliente.getPassword())
                .param("dataAlteracaoSenha", cliente.getDataAlteracaoSenha())
                .update();
    }
}
