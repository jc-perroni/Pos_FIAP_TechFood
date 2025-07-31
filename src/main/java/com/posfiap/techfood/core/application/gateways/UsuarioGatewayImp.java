package com.posfiap.techfood.core.application.gateways;

import com.posfiap.techfood.core.application.dto.NovoUsuarioDTO;
import com.posfiap.techfood.core.application.dto.UsuarioDTO;
import com.posfiap.techfood.core.application.interfaces.IUsuarioDataSource;
import com.posfiap.techfood.core.application.interfaces.IUsuarioGateway;
import com.posfiap.techfood.core.domain.entities.Usuario;
import com.posfiap.techfood.core.domain.exceptions.UsuarioNaoEncontradoException;

import java.util.ArrayList;
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
    public Optional<Usuario> findById(Long id) {
        Optional<UsuarioDTO> usuarioCriado = this.dataSource.findById(id);

        if (usuarioCriado.isEmpty()) {
            return Optional.empty();
        }

        UsuarioDTO usuario = usuarioCriado.get();

        return Optional.of(Usuario.create(
                usuario.id(),
                usuario.telefone(),
                usuario.nome(),
                usuario.tipoDeUsuario(),
                usuario.cpf(),
                usuario.email(),
                usuario.username(),
                usuario.password(),
                usuario.dataCriacaoConta(),
                usuario.dataAlteracaoConta(),
                usuario.dataAlteracaoSenha()));
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
    public List<Usuario> findAll(int size, int offset) {
        List<UsuarioDTO> usuarioDTOList = this.dataSource.findAll(size, offset);
        List<Usuario> usuarioList = new ArrayList<>();

        usuarioDTOList.forEach(
                usuarioDTO -> {
                    var usario = Usuario.create(
                            usuarioDTO.id(),
                            usuarioDTO.telefone(),
                            usuarioDTO.nome(),
                            usuarioDTO.tipoDeUsuario(),
                            usuarioDTO.cpf(),
                            usuarioDTO.email(),
                            usuarioDTO.username(),
                            usuarioDTO.password(),
                            usuarioDTO.dataCriacaoConta(),
                            usuarioDTO.dataAlteracaoConta(),
                            usuarioDTO.dataAlteracaoSenha());

                    usuarioList.add(usario);
                });

        return usuarioList;
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
        return this.dataSource.delete(id);
    }
}
