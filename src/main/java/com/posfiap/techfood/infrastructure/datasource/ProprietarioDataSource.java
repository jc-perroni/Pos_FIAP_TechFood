package com.posfiap.techfood.infrastructure.datasource;

import com.posfiap.techfood.core.application.dto.NovoUsuarioDTO;
import com.posfiap.techfood.core.application.dto.UsuarioDTO;
import com.posfiap.techfood.core.application.interfaces.usuario.IUsuarioDataSource;
import com.posfiap.techfood.infrastructure.models.Usuario;
import com.posfiap.techfood.infrastructure.models.dto.proprietario.ProprietarioDTO;
import com.posfiap.techfood.infrastructure.models.dto.proprietario.ProprietarioUpdateDTO;
import com.posfiap.techfood.infrastructure.services.ProprietarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProprietarioDataSource implements IUsuarioDataSource {

    private final ProprietarioService proprietarioService;

    @Override
    public Optional<UsuarioDTO> findById(Long id) {
        return Optional.of(
                usuarioToUsuarioDto(proprietarioService.findProprietarioById(id)));
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
        List <Usuario> usuarioList = proprietarioService.findAllProprietarios(page, size).getContent();
        return usuarioList.stream().map(ProprietarioDataSource::usuarioToUsuarioDto).toList();
    }

    @Override
    public UsuarioDTO update(UsuarioDTO usuarioDto, long id) {
        ProprietarioUpdateDTO proprietarioDto = usuarioDtoToProprietarioDto(usuarioDto);
        proprietarioService.updateProprietario(proprietarioDto, id);
        return usuarioDto;
    }

    @Override
    public UsuarioDTO save(NovoUsuarioDTO novoUsuarioDTO) {
        ProprietarioDTO proprietarioDTO = novoUsuarioDtoToProprietarioDto(novoUsuarioDTO);
        proprietarioService.insertProprietario(proprietarioDTO);
        return proprietarioDtoToUsuarioDto(proprietarioDTO);
    }

    @Override
    public Integer delete(long id) {
        proprietarioService.deleteProprietario(id);
        return 1;
    }

    @Override
    public Boolean verificarSenha(String senhaInformada, String senhaUsuario) {
        return null;
    }

    private static UsuarioDTO proprietarioDtoToUsuarioDto(ProprietarioDTO proprietarioDTO) {
        return new UsuarioDTO(
                proprietarioDTO.telefone(),
                null,
                proprietarioDTO.nome(),
                proprietarioDTO.cpf(),
                proprietarioDTO.email(),
                proprietarioDTO.username(),
                proprietarioDTO.password(),
                proprietarioDTO.dataCriacaoConta(),
                proprietarioDTO.dataAlteracaoConta(),
                proprietarioDTO.dataAlteracaoSenha()
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
                usuario.getDataAlteracaoSenha()
        );
    }


    private static ProprietarioDTO novoUsuarioDtoToProprietarioDto(NovoUsuarioDTO novoUsuarioDTO) {
        return new ProprietarioDTO(
                novoUsuarioDTO.telefone(),
                novoUsuarioDTO.nome(),
                novoUsuarioDTO.cpf(),
                novoUsuarioDTO.email(),
                novoUsuarioDTO.username(),
                novoUsuarioDTO.password(),
                null,
                null,
                null
        );
    }

    private static ProprietarioUpdateDTO usuarioDtoToProprietarioDto(UsuarioDTO usuarioDTO) {
        return new ProprietarioUpdateDTO(
                usuarioDTO.nome(),
                usuarioDTO.cpf(),
                usuarioDTO.telefone(),
                usuarioDTO.email()
        );
    }
}
