package com.posfiap.techfood.services;

import com.posfiap.techfood.exceptions.ResourceNotFoundException;
import com.posfiap.techfood.models.Cliente;
import com.posfiap.techfood.models.Endereco;
import com.posfiap.techfood.models.Restaurante;
import com.posfiap.techfood.models.dto.endereco.EnderecoDTO;
import com.posfiap.techfood.repositories.EnderecoRepository;
import com.posfiap.techfood.repositories.ClienteRepository;
import com.posfiap.techfood.repositories.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final ClienteRepository clienteRepository;
    private final RestauranteRepository restauranteRepository;



    public Page<Endereco> findAllEnderecos(int page, int size){
        log.info("Acessado o endpoint de retornar todos os endereços");
        return enderecoRepository.findAll(PageRequest.of(page, size));
    }

    public Endereco findEnderecoById(Long id){
        log.info("Acessado o endpoint de de encontrar endereço pelo ID");
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado"));
    }

    @Transactional
    public Endereco updateEndereco(Endereco endereco, Long id){
        log.info("Acessado o endpoint de atualização de endereço");
        var enderecoAlterado = findEnderecoById(id);

        if (endereco.getBairro() != null) enderecoAlterado.setBairro(endereco.getBairro());
        if (endereco.getCidade() != null) enderecoAlterado.setCidade(endereco.getCidade());
        if (endereco.getCep() != null) enderecoAlterado.setCep(endereco.getCep());
        if (endereco.getRua() != null) enderecoAlterado.setRua(endereco.getRua());
        if (endereco.getNumero() != null) enderecoAlterado.setNumero(endereco.getNumero());
        if (endereco.getComplemento() != null) enderecoAlterado.setComplemento(endereco.getComplemento());

        enderecoRepository.save(enderecoAlterado);
        log.info("Atualização realizada com sucesso.");
        return enderecoAlterado;
    }

    public void insertEndereco(EnderecoDTO endereco){
        log.info("Acessado o endpoint de salvamento de endereço");
        switch(endereco.tipoEndereco()){
            case CLIENTE -> {
                    Cliente cliente = clienteRepository.findById(endereco.idEntidade())
                    .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado para cadastro do endereço"));
                enderecoRepository.save(Endereco.fromDTO(endereco, cliente));
            }

            case RESTAURANTE -> {
                Restaurante restaurante =  restauranteRepository.findById(endereco.idEntidade())
                .orElseThrow(() -> new ResourceNotFoundException("Restaurante não encontraddo para cadastro do endereço"));
                enderecoRepository.save(Endereco.fromDTO(endereco, restaurante));
            }

            default -> throw new IllegalStateException("Não foi possível identificar o tipo do endereço");
    }
    }

    public void deleteEndereco(Long id) {
        log.info("Acessado o endpoint de deleção de endereço");
        enderecoRepository.deleteById(id);
    }
}
