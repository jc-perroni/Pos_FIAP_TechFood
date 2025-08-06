package com.posfiap.techfood.core.application.gateways;

import com.posfiap.techfood.core.application.dto.NovoUsuarioDTO;
import com.posfiap.techfood.core.application.dto.UsuarioDTO;
import com.posfiap.techfood.core.application.interfaces.usuario.IUsuarioDataSource;
import com.posfiap.techfood.core.application.interfaces.usuario.IUsuarioGateway;
import com.posfiap.techfood.core.domain.entities.Usuario;

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

        UsuarioDTO usuarioDTO = usuarioCriado.get();

        return Optional.of(Usuario.create(
                usuarioDTO.id(),
                usuarioDTO.nome(),
                usuarioDTO.email(),
                usuarioDTO.telefone(),
                usuarioDTO.cpf(),
                usuarioDTO.username(),
                usuarioDTO.password(),
                usuarioDTO.dataCriacaoConta(),
                usuarioDTO.dataAlteracaoConta(),
                usuarioDTO.dataAlteracaoSenha(),
                usuarioDTO.perfil()));
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
    public List<Usuario> findAll(int page, int size) {
        List<UsuarioDTO> usuarioDTOList = this.dataSource.findAll(page, size);
        List<Usuario> usuarioList = new ArrayList<>();

        usuarioDTOList.forEach(
                usuarioDTO -> {
                    var usario = Usuario.create(
                            usuarioDTO.id(),
                            usuarioDTO.nome(),
                            usuarioDTO.email(),
                            usuarioDTO.telefone(),
                            usuarioDTO.cpf(),
                            usuarioDTO.username(),
                            usuarioDTO.password(),
                            usuarioDTO.dataCriacaoConta(),
                            usuarioDTO.dataAlteracaoConta(),
                            usuarioDTO.dataAlteracaoSenha(),
                            usuarioDTO.perfil());

                    usuarioList.add(usario);
                });

        return usuarioList;
    }

    @Override
    public Usuario update(Usuario usuario, long id) {
        UsuarioDTO usuarioDto = new UsuarioDTO(
                usuario.getTelefone(),
                usuario.getId(),
                usuario.getNome(),
                usuario.getCpf(),
                usuario.getEmail(),
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getDataCriacaoConta(),
                usuario.getDataAlteracaoConta(),
                usuario.getDataAlteracaoSenha(),
                usuario.getPerfil()
        );
        UsuarioDTO usuarioCriado = this.dataSource.update(usuarioDto, id);
        return Usuario.create(
                usuarioCriado.id(),
                usuarioCriado.telefone(),
                usuarioCriado.nome(),
                usuarioCriado.cpf(),
                usuarioCriado.email(),
                usuarioCriado.username(),
                usuarioCriado.password(),
                usuarioCriado.dataCriacaoConta(),
                usuarioCriado.dataAlteracaoConta(),
                usuarioCriado.dataAlteracaoSenha(),
                usuarioCriado.perfil());
    }

    @Override
    public Usuario save(Usuario usuario) {
        final NovoUsuarioDTO novoUsuarioDTO = new NovoUsuarioDTO(
                usuario.getTelefone(),
                usuario.getNome(),
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
                usuarioCriado.cpf(),
                usuarioCriado.email(),
                usuarioCriado.username(),
                usuarioCriado.password(),
                usuarioCriado.dataCriacaoConta(),
                usuarioCriado.dataAlteracaoConta(),
                usuarioCriado.dataAlteracaoSenha(),
                usuarioCriado.perfil());
    }

    @Override
    public Integer delete(long id) {
        return this.dataSource.delete(id);
    }

    @Override
    public Boolean verificarSenha(String senhaInformada, String senhaUsuario) {
        return this.dataSource.verificarSenha(senhaInformada, senhaUsuario);
    }
}
