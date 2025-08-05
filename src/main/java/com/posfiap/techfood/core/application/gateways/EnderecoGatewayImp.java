package com.posfiap.techfood.core.application.gateways;

import com.posfiap.techfood.core.application.dto.EnderecoDTO;
import com.posfiap.techfood.core.application.interfaces.endereco.IEnderecoDataSource;
import com.posfiap.techfood.core.application.interfaces.endereco.IEnderecoGateway;
import com.posfiap.techfood.core.domain.entities.Endereco;

import java.util.List;
import java.util.Optional;

public class EnderecoGatewayImp implements IEnderecoGateway {
    private final IEnderecoDataSource dataSource;

    private EnderecoGatewayImp (IEnderecoDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static EnderecoGatewayImp create(IEnderecoDataSource dataSource) {
        return new EnderecoGatewayImp(dataSource);
    }


    @Override
    public Optional<Endereco> findById(Long id) {
       Optional<EnderecoDTO> enderecoCriado = this.dataSource.findById(id);

       if(enderecoCriado.isEmpty()) {
           return Optional.empty();
       }

       EnderecoDTO endereco = enderecoCriado.get();

       return Optional.of(Endereco.create(
               endereco.id(),
               endereco.idEntidade(),
               endereco.tipoEndereco(),
               endereco.rua(),
               endereco.cep(),
               endereco.cidade(),
               endereco.bairro(),
               endereco.complemento(),
               endereco.numero()
       ));
    }

    @Override
    public List<Endereco> findAll(int size, int offset) {
        return List.of();
    }

    @Override
    public Endereco update(Endereco endereco, long id) {
        return null;
    }

    @Override
    public Endereco save(Endereco endereco) {
        return null;
    }

    @Override
    public Integer delete(long id) {
        return 0;
    }
}
