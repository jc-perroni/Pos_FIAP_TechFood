package com.posfiap.techfood.services;

import com.posfiap.techfood.exceptions.ResourceNotFoundException;
import com.posfiap.techfood.models.Endereco;
import com.posfiap.techfood.models.Restaurante;
import com.posfiap.techfood.repositories.EnderecoRepository;
import com.posfiap.techfood.repositories.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final EnderecoRepository enderecoRepository;

    public List<Restaurante> findAllRestaurantes(int page, int size){
        int offset = (page - 1) * size;
        return restauranteRepository.findAll(size, offset);
    }

    public Optional<Restaurante> findRestauranteById(Long id){
        return Optional.ofNullable(restauranteRepository.findById(id))
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado"));
    }

    public void updateRestaurante(Restaurante restaurante, Long id){
        var update = restauranteRepository.update(restaurante, id);
        if(update ==0) {
            throw new RuntimeException("O restaurante de id " + id + " não está cadastrado e não pode ser atualizado");
        }
        log.info("Atualização realizada com sucesso.");
    }

    public void insertRestaurante(Restaurante restaurante){
        var insert = restauranteRepository.save(restaurante);
        Assert.state(insert ==1, "Erro ao tentar gravar o restaurante.");
    }
    @Transactional
    public void deleteRestaurante(Long id) {
        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurante não encontrado"));
        enderecoRepository.delete(restaurante.getEndereco().getId());
        var delete = restauranteRepository.delete(id);
        if (delete == 0){
            throw new RuntimeException("Não foi possível excluir o restaurante com ID: " + id);
        }
    }

}
