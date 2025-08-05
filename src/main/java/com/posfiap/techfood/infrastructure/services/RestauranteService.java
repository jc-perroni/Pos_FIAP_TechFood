package com.posfiap.techfood.infrastructure.services;

import com.posfiap.techfood.infrastructure.exceptions.ResourceNotFoundException;
import com.posfiap.techfood.infrastructure.models.Restaurante;
import com.posfiap.techfood.infrastructure.models.Usuario;
import com.posfiap.techfood.infrastructure.models.dto.proprietario.ProprietarioInfoBasicaDTO;
import com.posfiap.techfood.infrastructure.models.dto.restaurante.RestauranteDTO;
import com.posfiap.techfood.infrastructure.models.dto.restaurante.RestauranteResponseDTO;
import com.posfiap.techfood.infrastructure.repositories.RestauranteRepository;
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
    private final ProprietarioService proprietarioService;

    public Page<RestauranteResponseDTO> findAllRestaurantes(int page, int size) {
        Page<Restaurante> restaurantes = restauranteRepository.findAll(PageRequest.of(page, size));
        return restaurantes.map(restaurante -> {
            ProprietarioInfoBasicaDTO proprietarioInfo = new ProprietarioInfoBasicaDTO(
                    restaurante.getUsuario().getNome(),
                    restaurante.getUsuario().getEmail(),
                    restaurante.getUsuario().getPerfil()
            );
            return new RestauranteResponseDTO(
                    restaurante.getNome(),
                    restaurante.getTelefone(),
                    restaurante.getTipoCozinha(),
                    restaurante.getHorarioFuncionamento(),
                    proprietarioInfo,
                    restaurante.getEnderecos(),
                    restaurante.getCardapio()
            );
        });
    }

    public Optional<Restaurante> findRestauranteById(Long id){
        return Optional.of(restauranteRepository.findById(id))
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
        restaurante.setTipoCozinha(dto.tipoCozinha());
        restaurante.setHorarioFuncionamento(dto.horarioFuncionamento());

        Usuario proprietario = proprietarioService.retornarProprietario(dto.idProprietario());
        restaurante.setUsuario(proprietario);
        restauranteRepository.save(restaurante);
    }
    @Transactional
    public void deleteRestaurante(Long id) {
        restauranteRepository.deleteById(id);
    }
}
