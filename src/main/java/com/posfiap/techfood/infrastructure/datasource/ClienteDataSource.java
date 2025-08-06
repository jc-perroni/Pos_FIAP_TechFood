package com.posfiap.techfood.infrastructure.datasource;

import com.posfiap.techfood.core.application.dto.NovoUsuarioDTO;
import com.posfiap.techfood.core.application.dto.UsuarioDTO;
import com.posfiap.techfood.core.application.enums.PerfilUsuario;
import com.posfiap.techfood.core.application.interfaces.usuario.IUsuarioDataSource;
import com.posfiap.techfood.infrastructure.models.Usuario;
import com.posfiap.techfood.infrastructure.models.dto.cliente.ClienteDTO;
import com.posfiap.techfood.infrastructure.models.dto.cliente.ClienteUpdateDTO;
import com.posfiap.techfood.infrastructure.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClienteDataSource implements IUsuarioDataSource {

    private final ClienteService clienteService;

    @Override
    public Optional<UsuarioDTO> findById(Long id) {
        return Optional.of(
                usuarioToUsuarioDto(clienteService.findClienteById(id)));
    }

    @Override
    public Optional<UsuarioDTO> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<UsuarioDTO> findByUserame(String username) {
        return Optional.empty();
    }

    @Override
    public List<UsuarioDTO> findAll(int page, int size) {
        List<Usuario> usuarioList = clienteService.findAllClientes(page, size).getContent();
        return usuarioList.stream().map(ClienteDataSource::usuarioToUsuarioDto).toList();
    }

    @Override
    public UsuarioDTO update(UsuarioDTO usuarioDto, long id) {
        ClienteUpdateDTO clienteDto = usuarioDtoToClienteDto(usuarioDto);
        clienteService.updateCliente(clienteDto, id);
        return usuarioDto;
    }

    @Override
    public UsuarioDTO save(NovoUsuarioDTO novoUsuarioDTO) {
        ClienteDTO clienteDTO = novoUsuarioDtoToClienteDto(novoUsuarioDTO);
        clienteService.insertCliente(clienteDTO);
        return clienteDtoToUsuarioDto(clienteDTO);
    }

    @Override
    public Integer delete(long id) {
        clienteService.deleteCliente(id);
        return 1;
    }

    @Override
    public Boolean verificarSenha(String senhaInformada, String senhaUsuario) {
        return null;
    }

    private static UsuarioDTO clienteDtoToUsuarioDto(ClienteDTO clienteDTO) {
        return new UsuarioDTO(
                clienteDTO.telefone(),
                null,
                clienteDTO.nome(),
                clienteDTO.cpf(),
                clienteDTO.email(),
                clienteDTO.username(),
                clienteDTO.password(),
                clienteDTO.dataCriacaoConta(),
                clienteDTO.dataAlteracaoConta(),
                clienteDTO.dataAlteracaoSenha(),
                PerfilUsuario.CLIENTE
        );
    }

    private static UsuarioDTO usuarioToUsuarioDto(Usuario usuario) {
        return new UsuarioDTO(
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
                PerfilUsuario.valueOf(usuario.getPerfil().name())
        );
    }


    private static ClienteDTO novoUsuarioDtoToClienteDto(NovoUsuarioDTO novoUsuarioDTO) {
        return new ClienteDTO(
                novoUsuarioDTO.telefone(),
                novoUsuarioDTO.cpf(),
                novoUsuarioDTO.nome(),
                novoUsuarioDTO.email(),
                novoUsuarioDTO.username(),
                novoUsuarioDTO.password(),
                null,
                null,
                null
        );
    }

    private static ClienteUpdateDTO usuarioDtoToClienteDto(UsuarioDTO usuarioDTO) {
        return new ClienteUpdateDTO(
                usuarioDTO.nome(),
                usuarioDTO.cpf(),
                usuarioDTO.telefone(),
                usuarioDTO.email()
        );
    }
}
