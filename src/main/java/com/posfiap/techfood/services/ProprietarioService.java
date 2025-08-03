package com.posfiap.techfood.services;

import com.posfiap.techfood.exceptions.ResourceNotFoundException;
import com.posfiap.techfood.models.Proprietario;
import com.posfiap.techfood.models.dto.proprietario.ProprietarioDTO;
import com.posfiap.techfood.models.dto.proprietario.ProprietarioUpdateDTO;
import com.posfiap.techfood.repositories.ProprietarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProprietarioService {

    private final ProprietarioRepository proprietarioRepository;
    private final UsuarioService usuarioService;

    public Page<Proprietario> findAllProprietarios(int page, int size){
        log.info("Acessado o endpoint de retornar todos os proprietarios");
        return proprietarioRepository.findAll(PageRequest.of(page, size));
    }

    public Proprietario findProprietarioById(Long id){
        log.info("Acessado o endpoint de de encontrar proprietario pelo ID");
        return proprietarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proprietario com esse ID não existe"));
    }

    public void updateProprietario(ProprietarioUpdateDTO proprietario, Long id){
        log.info("Acessado o endpoint de atualização de proprietario");
        Proprietario proprietarioExistente = proprietarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proprietario não encontrado"));

        proprietarioExistente.updateDataAlteracao();
        proprietarioExistente.setNome(proprietario.nome());
        proprietarioExistente.setCpf(proprietario.cpf());
        proprietarioExistente.setTelefone(proprietario.telefone());
        proprietarioExistente.setEmail(proprietario.email());

        proprietarioRepository.save(proprietarioExistente);
        log.info("Atualização realizada com sucesso.");
    }

    public void insertProprietario(ProprietarioDTO proprietario){
        log.info("Acessado o endpoint de criação de proprietario");
        Proprietario pr = Proprietario.fromDTO(proprietario);
        usuarioService.alterarSenha(proprietario.password() , pr);
        proprietarioRepository.save(pr);
    }

    public void deleteProprietario(Long id) {
        log.info("Acessado o endpoint de deleção de proprietario");

        proprietarioRepository.deleteById(id);
    }

}
