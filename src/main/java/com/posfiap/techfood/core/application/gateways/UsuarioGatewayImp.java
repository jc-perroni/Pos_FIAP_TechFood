package com.posfiap.techfood.core.application.gateways;

import com.posfiap.techfood.core.application.dto.NovoUsuarioDTO;
import com.posfiap.techfood.core.application.dto.UsuarioDTO;
import com.posfiap.techfood.core.application.interfaces.IUsuarioDataSource;
import com.posfiap.techfood.core.application.interfaces.IUsuarioGateway;
import com.posfiap.techfood.core.domain.entities.Usuario;

import java.util.List;
import java.util.Optional;

public class UsuarioGatewayImp implements IUsuarioGateway {
    private final IUsuarioDataSource dataSource;

    private UsuarioGatewayImp (IUsuarioDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static UsuarioGatewayImp create(IUsuarioDataSource dataSource) {
        return new UsuarioGatewayImp(dataSource);
    }

    @Override
    public Optional findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional findByUserame(String username) {
        return Optional.empty();
    }

    @Override
    public List findAll(int size, int offset) {
        return List.of();
    }

    @Override
    public Usuario update(Usuario usuario, long id) {
        return null;
    }

    @Override
    public Usuario save(Usuario usuario) {
        final NovoUsuarioDTO novoUsuarioDTO = new NovoUsuarioDTO(
                usuario.getTelefone(),
                usuario.getNome(),
                usuario.getTipoUsuario(),
                usuario.getCpf(),
                usuario.getEmail(),
                usuario.getUsername(),
                usuario.getPassword()
        );

        UsuarioDTO usuarioCriado = this.dataSource.save(novoUsuarioDTO);
        return Usuario.create(
                usuarioCriado.id(),
                usuarioCriado.telefone(),
                usuarioCriado.nome(),
                usuarioCriado.tipoDeUsuario(),
                usuarioCriado.cpf(),
                usuarioCriado.email(),
                usuarioCriado.username(),
                usuarioCriado.password(),
                usuarioCriado.dataCriacaoConta(),
                usuarioCriado.dataAlteracaoConta(),
                usuarioCriado.dataAlteracaoSenha());
    }

    @Override
    public Integer delete(long id) {
        return 0;
    }
}
