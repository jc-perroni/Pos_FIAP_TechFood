package com.posfiap.techfood.infrastructure.services;

import com.posfiap.techfood.core.domain.exceptions.ResourceNotFoundException;
import com.posfiap.techfood.infrastructure.models.Cardapio;
import com.posfiap.techfood.infrastructure.repositories.CardapioRepository;
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
public class CardapioService {

    private final CardapioRepository cardapioRepository;

    public Page<Cardapio> findAllCardapios(int page, int size) {
        return cardapioRepository.findAll(PageRequest.of(page, size));
    }

    public Optional<Cardapio> findCardapioById(Long id) {
        return Optional.of(cardapioRepository.findById(id))
                .orElseThrow(() -> new ResourceNotFoundException("Cardápio não existente"));
    }

    @Transactional
    public void updateCardapio(Cardapio cardapio, Long id){
        Cardapio cardapioAlterado = findCardapioById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cardápio não existente"));
        cardapioRepository.save(cardapio);
        log.info("Atualização de cardápio realizada com sucesso.");
    }
    @Transactional
    public void insertCardapio(Cardapio cardapio){
        cardapioRepository.save(cardapio);
    }

    @Transactional
    public void deleteCardapio(Long id) {
        Cardapio cardapio = cardapioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cardápio não localizado."));
        cardapioRepository.delete(cardapio);
    }
}
