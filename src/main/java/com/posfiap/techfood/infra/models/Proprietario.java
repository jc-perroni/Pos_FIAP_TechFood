package com.posfiap.techfood.infra.models;

import com.posfiap.techfood.infra.models.dto.ProprietarioDTO;

import java.time.LocalDate;
import java.util.List;

public class Proprietario extends Usuario{

    private List<Restaurante> managerBusiness;

    public Proprietario() {
        super();
    }

    public Proprietario(String telefone,
                        String nome,
                        String cpf,
                        String email,
                        String username,
                        String password,
                        LocalDate dataCriacaoConta,
                        LocalDate dataAlteracaoConta,
                        LocalDate dataAlteracaoSenha) {
        super(nome, cpf, telefone, email, username, password, dataCriacaoConta, dataAlteracaoConta, dataAlteracaoSenha);
    }

    public Proprietario(ProprietarioDTO proprietarioDTO) {
        super(proprietarioDTO.nome(), proprietarioDTO.email(), proprietarioDTO.telefone(), proprietarioDTO.cpf(),
                proprietarioDTO.username(), proprietarioDTO.password(), proprietarioDTO.dataCriacaoConta(),
                proprietarioDTO.dataAlteracaoConta(), proprietarioDTO.dataAlteracaoSenha());
    }
}
