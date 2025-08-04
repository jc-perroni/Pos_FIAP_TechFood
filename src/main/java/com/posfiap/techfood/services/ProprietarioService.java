package com.posfiap.techfood.services;

import com.posfiap.techfood.exceptions.ResourceNotFoundException;
import com.posfiap.techfood.models.Proprietario;
import com.posfiap.techfood.models.dto.ProprietarioDTO;
import com.posfiap.techfood.models.dto.ProprietarioUpdateDTO;
import com.posfiap.techfood.repositories.ProprietarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProprietarioService {

    private final ProprietarioRepository proprietarioRepository;
    private final UsuarioService usuarioService;

    public List<Proprietario> findAllProprietarios(int page, int size){
        log.info("Acessado o endpoint de retornar todos os proprietarios");
        int offset = (page - 1) * size;
        return proprietarioRepository.findAll(size, offset);
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

        // Atualiza a entidade no banco
        int update = proprietarioRepository.update(proprietarioExistente, id);
        if(update ==0) {
            throw new ResourceNotFoundException("O proprietario de id " + id + " não está cadastrado e não pode ser atualizado");
        }
        log.info("Atualização realizada com sucesso.");
    }

    public void insertProprietario(ProprietarioDTO proprietario){
        log.info("Acessado o endpoint de criação de proprietario");
        Proprietario pr = new Proprietario(proprietario);
        usuarioService.alterarSenha(proprietario.password() , pr);
        var insert = proprietarioRepository.save(pr);
    }

    public void deleteProprietario(Long id) {
        log.info("Acessado o endpoint de deleção de proprietario");

        Proprietario pr = proprietarioRepository.findById(id).orElseThrow();
        var delete = proprietarioRepository.delete(id);
        var deleteUser = proprietarioRepository.deleteUsuario(pr.getUsername());
        if (delete == 0){
            throw new ResourceNotFoundException("Proprietario não encontrado com a ID: " + id);
        }
    }

}
