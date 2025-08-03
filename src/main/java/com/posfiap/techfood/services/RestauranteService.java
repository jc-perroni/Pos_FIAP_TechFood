package com.posfiap.techfood.services;

import com.posfiap.techfood.exceptions.ResourceNotFoundException;
import com.posfiap.techfood.models.Proprietario;
import com.posfiap.techfood.models.Restaurante;
import com.posfiap.techfood.models.dto.restaurante.RestauranteDTO;
import com.posfiap.techfood.repositories.EnderecoRepository;
import com.posfiap.techfood.repositories.ProprietarioRepository;
import com.posfiap.techfood.repositories.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final ProprietarioRepository proprietarioRepository;
    private final EnderecoRepository enderecoRepository;

    public Page<Restaurante> findAllRestaurantes(int page, int size){
        return restauranteRepository.findAll(PageRequest.of(page, size));
    }

    public Optional<Restaurante> findRestauranteById(Long id){
        return Optional.ofNullable(restauranteRepository.findById(id))
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado"));
    }
    @Transactional
    public void updateRestaurante(Restaurante restaurante, Long id){
        Restaurante restauranteAlterado = findRestauranteById(id).orElseThrow(
                () -> new ResourceNotFoundException("Restaurante não encontrado"));
        restauranteRepository.save(restaurante);
        log.info("Atualização realizada com sucesso.");
    }
    @Transactional
    public void insertRestaurante(RestauranteDTO dto){
        Restaurante restaurante = new Restaurante();
        restaurante.setNome(dto.nome());
        restaurante.setTelefone(dto.telefone());
        Proprietario proprietario = proprietarioRepository.findById(dto.idProprietario())
                .orElseThrow(() -> new ResourceNotFoundException("Proprietário não encontrado para cadastro no estabelecimento"));
        restaurante.setProprietario(proprietario);
        restauranteRepository.save(restaurante);
    }
    @Transactional
    public void deleteRestaurante(Long id) {
        restauranteRepository.deleteById(id);
    }
}
