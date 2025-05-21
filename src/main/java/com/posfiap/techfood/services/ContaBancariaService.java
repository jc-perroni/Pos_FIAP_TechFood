package com.posfiap.techfood.services;

import com.posfiap.techfood.exceptions.ResourceNotFoundException;
import com.posfiap.techfood.models.ContaBancaria;
import com.posfiap.techfood.repositories.ContaBancariaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ContaBancariaService {

    private final ContaBancariaRepository contaBancariaRepository;

    public List<ContaBancaria> findAllContaBancarias(int page, int size){
        int offset = (page - 1) * size;
        return contaBancariaRepository.findAll(size, offset);
    }

    public Optional<ContaBancaria> findContaBancariaById(Long id){
        return Optional.ofNullable(contaBancariaRepository.findById(id))
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado"));
    }

    public void updateContaBancaria(ContaBancaria contaBancaria, Long id){
        var update = contaBancariaRepository.update(contaBancaria, id);
        if(update ==0) {
            throw new RuntimeException("O contaBancaria de id " + id + " não está cadastrado e não pode ser atualizado");
        }
        log.info("Atualização realizada com sucesso.");
    }

    public void insertContaBancaria(ContaBancaria contaBancaria){
        var insert = contaBancariaRepository.save(contaBancaria);
        Assert.state(insert ==1, "Erro ao tentar gravar o contaBancaria.");
    }

    public void deleteContaBancaria(Long id) {
        var delete = contaBancariaRepository.delete(id);
        if (delete == 0){
            throw new RuntimeException("ContaBancaria não encontrado com a ID: " + id);
        }
    }

}
