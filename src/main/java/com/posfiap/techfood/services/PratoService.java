package com.posfiap.techfood.services;

import com.posfiap.techfood.exceptions.ResourceNotFoundException;
import com.posfiap.techfood.models.Cardapio;
import com.posfiap.techfood.models.Prato;
import com.posfiap.techfood.models.dto.prato.PratoDTO;
import com.posfiap.techfood.models.dto.prato.PratoUpdateDTO;
import com.posfiap.techfood.repositories.CardapioRepository;
import com.posfiap.techfood.repositories.PratoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PratoService {

    private final PratoRepository pratoRepository;
    private final CardapioRepository cardapioRepository;

    public Page<Prato> findAllPratos(int page, int size) {
        log.info("Listando todos os pratos");
        return pratoRepository.findAll(PageRequest.of(page, size));
    }

    public Prato findPratoById(Long id) {
        log.info("Buscando prato por ID");
        return pratoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prato não encontrado"));
    }

    public void insertPrato(PratoDTO pratoDTO, Long cardapioId) {
        log.info("Inserindo novo prato");
        Cardapio cardapio = cardapioRepository.findById(cardapioId)
                .orElseThrow(() -> new ResourceNotFoundException("Cardápio não encontrado"));
        Prato prato = new Prato();
        prato.setNome(pratoDTO.nome());
        prato.setDescricao(pratoDTO.descricao());
        prato.setPreco(pratoDTO.preco());
        prato.setApenasConsumoLocal(pratoDTO.apenasConsumoLocal());
        prato.setLinkImagem(pratoDTO.linkImagem());
        prato.setCardapio(cardapio);
        pratoRepository.save(prato);
    }

    public void updatePrato(PratoUpdateDTO pratoDTO, Long id) {
        log.info("Atualizando prato");
        Prato prato = findPratoById(id);
        prato.setNome(pratoDTO.nome());
        prato.setDescricao(pratoDTO.descricao());
        prato.setPreco(pratoDTO.preco());
        prato.setApenasConsumoLocal(pratoDTO.disponibilidadeConsumoLocal());
        prato.setLinkImagem(pratoDTO.linkImagem());
        pratoRepository.save(prato);
    }

    public void deletePrato(Long id) {
        log.info("Deletando prato");
        Prato prato = findPratoById(id);
        pratoRepository.delete(prato);
    }
}