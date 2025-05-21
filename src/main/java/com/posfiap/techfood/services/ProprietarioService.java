package com.posfiap.techfood.services;

import com.posfiap.techfood.exceptions.ResourceNotFoundException;
import com.posfiap.techfood.models.Proprietario;
import com.posfiap.techfood.repositories.ProprietarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProprietarioService {

    private final ProprietarioRepository proprietarioRepository;
    private final UsuarioService usuarioService;

    public List<Proprietario> findAllProprietarios(int page, int size){
        int offset = (page - 1) * size;
        return proprietarioRepository.findAll(size, offset);
    }

    public Optional<Proprietario> findProprietarioById(Long id){
        return Optional.ofNullable(proprietarioRepository.findById(id))
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado"));
    }

    public void updateProprietario(Proprietario proprietario, Long id){
        var update = proprietarioRepository.update(proprietario, id);
        if(update ==0) {
            throw new RuntimeException("O proprietario de id " + id + " não está cadastrado e não pode ser atualizado");
        }
        log.info("Atualização realizada com sucesso.");
    }

    public void insertProprietario(Proprietario proprietario){
        usuarioService.alterarSenha(proprietario.getPassword(), proprietario);
        var insert = proprietarioRepository.save(proprietario);
        Assert.state(insert ==1, "Erro ao tentar gravar o proprietario.");
    }

    public void deleteProprietario(Long id) {
        var delete = proprietarioRepository.delete(id);
        if (delete == 0){
            throw new RuntimeException("Proprietario não encontrado com a ID: " + id);
        }
    }

}
