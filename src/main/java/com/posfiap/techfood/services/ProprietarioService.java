package com.posfiap.techfood.services;

import com.posfiap.techfood.exceptions.ResourceNotFoundException;
import com.posfiap.techfood.models.Usuario;
import com.posfiap.techfood.models.dto.proprietario.ProprietarioDTO;
import com.posfiap.techfood.models.dto.proprietario.ProprietarioUpdateDTO;
import com.posfiap.techfood.models.enums.PerfilUsuario;
import com.posfiap.techfood.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProprietarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;
    private final List<PerfilUsuario> perfilProprietario = List.of(PerfilUsuario.PROPRIETARIO, PerfilUsuario.PROPRIETARIO_CLIENTE);


    public Page<Usuario> findAllProprietarios(int page, int size){
        log.info("Acessado o endpoint de retornar todos os proprietarios");
        return usuarioRepository.findAllByPerfilIn(perfilProprietario, PageRequest.of(page, size));
    }

    public Usuario findProprietarioById(Long id){
        log.info("Acessado o endpoint de de encontrar proprietario pelo ID");
        return retornarProprietario(id);
    }

    public void updateProprietario(ProprietarioUpdateDTO proprietario, Long id){
        log.info("Acessado o endpoint de atualização de proprietario");
        Usuario proprietarioExistente = retornarProprietario(id);
        proprietarioExistente.updateDataAlteracao();
        proprietarioExistente.setNome(proprietario.nome());
        proprietarioExistente.setCpf(proprietario.cpf());
        proprietarioExistente.setTelefone(proprietario.telefone());
        proprietarioExistente.setEmail(proprietario.email());

        usuarioRepository.save(proprietarioExistente);
        log.info("Atualização realizada com sucesso.");
    }

    public void insertProprietario(ProprietarioDTO proprietario){
        log.info("Acessado o endpoint de criação de proprietario");
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(proprietario.nome());
        novoUsuario.setCpf(proprietario.cpf());
        novoUsuario.setTelefone(proprietario.telefone());
        novoUsuario.setEmail(proprietario.email());
        novoUsuario.setUsername(proprietario.username());
        novoUsuario.setPerfil(PerfilUsuario.PROPRIETARIO);
        usuarioService.alterarSenha(proprietario.password() , novoUsuario);
        usuarioRepository.save(novoUsuario);
    }

    public void deleteProprietario(Long id) {
        log.info("Acessado o endpoint de deleção de proprietario");
        Usuario usuario = retornarProprietario(id);
        if(usuario.getPerfil().equals(PerfilUsuario.PROPRIETARIO)) {
            usuarioRepository.deleteById(usuario.getId());
        }
        else usuarioService.alterarPerfil(id, PerfilUsuario.CLIENTE);
    }

    public Usuario retornarProprietario(Long id){
        return usuarioRepository.findByIdAndPerfilIn(id, perfilProprietario)
                .orElseThrow(() -> new ResourceNotFoundException("Proprietario não encontrado"));
    }
}
