package integrationTests.utils;

import com.posfiap.techfood.core.application.dto.EnderecoComIdDTO;
import com.posfiap.techfood.core.application.dto.NovoEnderecoDTO;
import com.posfiap.techfood.infrastructure.models.Cardapio;
import com.posfiap.techfood.infrastructure.models.Prato;
import com.posfiap.techfood.infrastructure.models.Restaurante;
import com.posfiap.techfood.infrastructure.models.Usuario;
import com.posfiap.techfood.infrastructure.models.dto.endereco.EnderecoDTO;
import com.posfiap.techfood.infrastructure.models.dto.prato.PratoDTO;
import com.posfiap.techfood.infrastructure.models.dto.restaurante.RestauranteDTO;
import com.posfiap.techfood.infrastructure.models.enums.PerfilUsuario;
import com.posfiap.techfood.infrastructure.models.enums.TipoCozinha;
import com.posfiap.techfood.infrastructure.models.enums.TipoEndereco;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataFactory {

    public List<Usuario> gerarClientes() {
        Usuario u1 = new Usuario();
        u1.setTelefone("6523999-0001");
        u1.setNome("Primeiro Aluno Silva");
        u1.setEmail("primeiro.aluno@GMAIL.COM");
        u1.setUsername("primeiro.aluno1");
        u1.alterarSenha("1234e");
        u1.setCpf("313.001.201-55");
        u1.setPerfil(PerfilUsuario.CLIENTE);

        Usuario u2 = new Usuario();
        u2.setTelefone("6523999-0002");
        u2.setNome("Segundo Aluno Souza");
        u2.setEmail("segundo.aluno@GMAIL.COM");
        u2.setUsername("segundo.aluno2");
        u2.alterarSenha("1234e");
        u2.setCpf("313.001.201-56");
        u2.setPerfil(PerfilUsuario.CLIENTE);

        Usuario u3 = new Usuario();
        u3.setTelefone("6523999-0003");
        u3.setNome("Terceiro Aluno Lima");
        u3.setEmail("terceiro.aluno@GMAIL.COM");
        u3.setUsername("terceiro.aluno3");
        u3.alterarSenha("1234e");
        u3.setCpf("313.001.201-57");
        u3.setPerfil(PerfilUsuario.CLIENTE);

        Usuario u4 = new Usuario();
        u4.setTelefone("6523999-0004");
        u4.setNome("Quarto Aluno Costa");
        u4.setEmail("quarto.aluno@GMAIL.COM");
        u4.setUsername("quarto.aluno4");
        u4.alterarSenha("1234e");
        u4.setCpf("313.001.201-58");
        u4.setPerfil(PerfilUsuario.CLIENTE);

        Usuario u5 = new Usuario();
        u5.setTelefone("6523999-0005");
        u5.setNome("Quinto Aluno Dias");
        u5.setEmail("quinto.aluno@GMAIL.COM");
        u5.setUsername("quinto.aluno5");
        u5.alterarSenha("1234e");
        u5.setCpf("313.001.201-59");
        u5.setPerfil(PerfilUsuario.CLIENTE);

        return Arrays.asList(u1, u2, u3, u4, u5);
    }

    public List<Usuario> gerarProprietarios() {
        Usuario p1 = new Usuario();
        p1.setTelefone("6523999-0010");
        p1.setNome("Primeiro Proprietario Silva");
        p1.setEmail("primeiro.proprietario@GMAIL.COM");
        p1.setUsername("primeiro.proprietario1");
        p1.alterarSenha("1234e");
        p1.setCpf("313.001.301-55");
        p1.setPerfil(PerfilUsuario.PROPRIETARIO);

        Usuario p2 = new Usuario();
        p2.setTelefone("6523999-0011");
        p2.setNome("Segundo Proprietario Souza");
        p2.setEmail("segundo.proprietario@GMAIL.COM");
        p2.setUsername("segundo.proprietario2");
        p2.alterarSenha("1234e");
        p2.setCpf("313.001.301-56");
        p2.setPerfil(PerfilUsuario.PROPRIETARIO);

        Usuario p3 = new Usuario();
        p3.setTelefone("6523999-0012");
        p3.setNome("Terceiro Proprietario Lima");
        p3.setEmail("terceiro.proprietario@GMAIL.COM");
        p3.setUsername("terceiro.proprietario3");
        p3.alterarSenha("1234e");
        p3.setCpf("313.001.301-57");
        p3.setPerfil(PerfilUsuario.PROPRIETARIO);

        Usuario p4 = new Usuario();
        p4.setTelefone("6523999-0013");
        p4.setNome("Quarto Proprietario Costa");
        p4.setEmail("quarto.proprietario@GMAIL.COM");
        p4.setUsername("quarto.proprietario4");
        p4.alterarSenha("1234e");
        p4.setCpf("313.001.301-58");
        p4.setPerfil(PerfilUsuario.PROPRIETARIO);

        Usuario p5 = new Usuario();
        p5.setTelefone("6523999-0014");
        p5.setNome("Quinto Proprietario Dias");
        p5.setEmail("quinto.proprietario@GMAIL.COM");
        p5.setUsername("quinto.proprietario5");
        p5.alterarSenha("1234e");
        p5.setCpf("313.001.301-59");
        p5.setPerfil(PerfilUsuario.PROPRIETARIO);

        return Arrays.asList(p1, p2, p3, p4, p5);
    }

    public List<RestauranteDTO> gerarRestaurantes(List<Usuario> proprietarios) {
        List<RestauranteDTO> restaurantes = new ArrayList<>();
        int max = Math.min(proprietarios.size(), 4);

        if (max > 0) restaurantes.add(new RestauranteDTO(
                "COMIDA BRASILEIRA",
                "653333-3333",
                proprietarios.get(0).getId(),
                TipoCozinha.COMIDA_MINEIRA,
                "08:00 às 22:00"
        ));
        if (max > 1) restaurantes.add(new RestauranteDTO(
                "PIZZARIA ITALIANA",
                "654444-4444",
                proprietarios.get(1).getId(),
                TipoCozinha.LANCHES_E_PASTEL,
                "18:00 às 23:30"
        ));
        if (max > 2) restaurantes.add(new RestauranteDTO(
                "SUSHI HOUSE",
                "655555-5555",
                proprietarios.get(2).getId(),
                TipoCozinha.COMIDA_JAPONESA,
                "11:00 às 23:00"
        ));
        if (max > 3) restaurantes.add(new RestauranteDTO(
                "BURGER GRILL",
                "656666-6666",
                proprietarios.get(3).getId(),
                TipoCozinha.FAST_FOOD,
                "12:00 às 00:00"
        ));

        return restaurantes;
    }

    public List<EnderecoDTO> gerarEnderecosClientes(List<Usuario> clientes) {
        List<EnderecoDTO> enderecos = new ArrayList<>();
        int max = Math.min(clientes.size(), 5);

        if (max > 0) enderecos.add(new EnderecoDTO(clientes.get(0).getId(), TipoEndereco.CLIENTE, "Rua Socrates", "82000-000", "Curitiba", "Alto da Glória", "Apto 312", "123"));
        if (max > 1) enderecos.add(new EnderecoDTO(clientes.get(1).getId(), TipoEndereco.CLIENTE, "Rua Platão", "82001-001", "Curitiba", "Centro", "Casa 2", "456"));
        if (max > 2) enderecos.add(new EnderecoDTO(clientes.get(2).getId(), TipoEndereco.CLIENTE, "Rua Aristóteles", "82002-002", "Curitiba", "Batel", null, "789"));
        if (max > 3) enderecos.add(new EnderecoDTO(clientes.get(3).getId(), TipoEndereco.CLIENTE, "Rua Epicuro", "82003-003", "Curitiba", "Cristo Rei", "Fundos", "101"));
        if (max > 4) enderecos.add(new EnderecoDTO(clientes.get(4).getId(), TipoEndereco.CLIENTE, "Rua Heráclito", "82004-004", "Curitiba", "Juvevê", null, "202"));

        return enderecos;
    }

    public List<EnderecoDTO> gerarEnderecosRestaurantes(List<RestauranteDTO> restaurantes) {
        List<EnderecoDTO> enderecos = new ArrayList<>();
        int max = Math.min(restaurantes.size(), 5);

        if (max > 0) enderecos.add(new EnderecoDTO(restaurantes.get(0).idProprietario(), TipoEndereco.RESTAURANTE, "Av. Brasil", "83000-000", "Curitiba", "Centro Cívico", "Loja 1", "10"));
        if (max > 1) enderecos.add(new EnderecoDTO(restaurantes.get(1).idProprietario(), TipoEndereco.RESTAURANTE, "Av. Paraná", "83001-001", "Curitiba", "Água Verde", null, "20"));
        if (max > 2) enderecos.add(new EnderecoDTO(restaurantes.get(2).idProprietario(), TipoEndereco.RESTAURANTE, "Rua Chile", "83002-002", "Curitiba", "Mercês", "Sala 5", "30"));
        if (max > 3) enderecos.add(new EnderecoDTO(restaurantes.get(3).idProprietario(), TipoEndereco.RESTAURANTE, "Rua México", "83003-003", "Curitiba", "Santa Felicidade", null, "40"));
        if (max > 4) enderecos.add(new EnderecoDTO(restaurantes.get(4).idProprietario(), TipoEndereco.RESTAURANTE, "Rua Canadá", "83004-004", "Curitiba", "Cabral", "Sobreloja", "50"));

        return enderecos;
    }

    public List<EnderecoDTO> gerarEnderecosRestaurantesPorIds(List<Long> ids) {
        List<EnderecoDTO> enderecos = new ArrayList<>();
        int max = Math.min(ids.size(), 5);

        if (max > 0) enderecos.add(new EnderecoDTO(ids.getFirst(), TipoEndereco.RESTAURANTE, "Av. Brasil", "83000-000", "Curitiba", "Centro Cívico", "Loja 1", "10"));
        if (max > 1) enderecos.add(new EnderecoDTO(ids.get(1), TipoEndereco.RESTAURANTE, "Av. Paraná", "83001-001", "Curitiba", "Água Verde", null, "20"));
        if (max > 2) enderecos.add(new EnderecoDTO(ids.get(2), TipoEndereco.RESTAURANTE, "Rua Chile", "83002-002", "Curitiba", "Mercês", "Sala 5", "30"));
        if (max > 3) enderecos.add(new EnderecoDTO(ids.get(3), TipoEndereco.RESTAURANTE, "Rua México", "83003-003", "Curitiba", "Santa Felicidade", null, "40"));
        if (max > 4) enderecos.add(new EnderecoDTO(ids.get(4), TipoEndereco.RESTAURANTE, "Rua Canadá", "83004-004", "Curitiba", "Cabral", "Sobreloja", "50"));

        return enderecos;
    }

    public NovoEnderecoDTO gerarNovoEnderecoDTOComAlteracao(EnderecoComIdDTO endereco) {
        return new NovoEnderecoDTO(
                endereco.idEntidade(),
                endereco.tipoEndereco(),
                "Rua Alterada",
                endereco.cep(),
                endereco.cidade(),
                "Bairro Novo",
                endereco.complemento(),
                "999");
    }

    public List<Cardapio> gerarCardapiosComPratos(List<Restaurante> restaurantes) {
        List<Cardapio> cardapios = new ArrayList<>();
        int max = Math.min(restaurantes.size(), 5);

        if (max > 0) {
            Cardapio c1 = new Cardapio();
            Prato p1a = new Prato();
            p1a.setNome("Feijoada Completa");
            p1a.setDescricao("Feijoada tradicional com acompanhamentos");
            p1a.setPreco(29.90);
            p1a.setApenasConsumoLocal(true);
            p1a.setLinkImagem("https://exemplo.com/feijoada.jpg");
            p1a.setCardapio(c1);

            Prato p1b = new Prato();
            p1b.setNome("Moqueca Baiana");
            p1b.setDescricao("Moqueca de peixe com dendê");
            p1b.setPreco(34.90);
            p1b.setApenasConsumoLocal(false);
            p1b.setLinkImagem("https://exemplo.com/moqueca.jpg");
            p1b.setCardapio(c1);

            c1.setPratos(Arrays.asList(p1a, p1b));
            c1.setRestaurante(restaurantes.getFirst());
            cardapios.add(c1);
        }
        if (max > 1) {
            Cardapio c2 = new Cardapio();
            Prato p2a = new Prato();
            p2a.setNome("Pizza Margherita");
            p2a.setDescricao("Pizza clássica italiana");
            p2a.setPreco(39.90);
            p2a.setApenasConsumoLocal(true);
            p2a.setLinkImagem("https://exemplo.com/margherita.jpg");
            p2a.setCardapio(c2);

            Prato p2b = new Prato();
            p2b.setNome("Pizza Calabresa");
            p2b.setDescricao("Pizza de calabresa com cebola");
            p2b.setPreco(42.90);
            p2b.setApenasConsumoLocal(false);
            p2b.setLinkImagem("https://exemplo.com/calabresa.jpg");
            p2b.setCardapio(c2);

            c2.setPratos(Arrays.asList(p2a, p2b));
            c2.setRestaurante(restaurantes.get(1));
            cardapios.add(c2);
        }
        if (max > 2) {
            Cardapio c3 = new Cardapio();
            Prato p3a = new Prato();
            p3a.setNome("Sushi Combo");
            p3a.setDescricao("Combo de sushis variados");
            p3a.setPreco(49.90);
            p3a.setApenasConsumoLocal(true);
            p3a.setLinkImagem("https://exemplo.com/sushi.jpg");
            p3a.setCardapio(c3);

            Prato p3b = new Prato();
            p3b.setNome("Yakissoba");
            p3b.setDescricao("Yakissoba de carne e legumes");
            p3b.setPreco(27.90);
            p3b.setApenasConsumoLocal(false);
            p3b.setLinkImagem("https://exemplo.com/yakissoba.jpg");
            p3b.setCardapio(c3);

            c3.setPratos(Arrays.asList(p3a, p3b));
            c3.setRestaurante(restaurantes.get(2));
            cardapios.add(c3);
        }
        if (max > 3) {
            Cardapio c4 = new Cardapio();
            Prato p4a = new Prato();
            p4a.setNome("Cheeseburger");
            p4a.setDescricao("Hambúrguer artesanal com queijo");
            p4a.setPreco(24.90);
            p4a.setApenasConsumoLocal(true);
            p4a.setLinkImagem("https://exemplo.com/cheeseburger.jpg");
            p4a.setCardapio(c4);

            Prato p4b = new Prato();
            p4b.setNome("Batata Frita");
            p4b.setDescricao("Porção de batata frita crocante");
            p4b.setPreco(14.90);
            p4b.setApenasConsumoLocal(false);
            p4b.setLinkImagem("https://exemplo.com/batata.jpg");
            p4b.setCardapio(c4);

            c4.setPratos(Arrays.asList(p4a, p4b));
            c4.setRestaurante(restaurantes.get(3));
            cardapios.add(c4);
        }
        if (max > 4) {
            Cardapio c5 = new Cardapio();
            Prato p5a = new Prato();
            p5a.setNome("Salada Vegana");
            p5a.setDescricao("Salada fresca com grão de bico");
            p5a.setPreco(22.90);
            p5a.setApenasConsumoLocal(true);
            p5a.setLinkImagem("https://exemplo.com/salada.jpg");
            p5a.setCardapio(c5);

            Prato p5b = new Prato();
            p5b.setNome("Quiche de Espinafre");
            p5b.setDescricao("Quiche vegetariana de espinafre");
            p5b.setPreco(18.90);
            p5b.setApenasConsumoLocal(false);
            p5b.setLinkImagem("https://exemplo.com/quiche.jpg");
            p5b.setCardapio(c5);

            c5.setPratos(Arrays.asList(p5a, p5b));
            c5.setRestaurante(restaurantes.get(4));
            cardapios.add(c5);
        }

        return cardapios;
    }

    public PratoDTO getPratoDTO() {
        return new PratoDTO(
                "Prato Teste",
                "Descrição do prato teste",
                19.99,
                true,
                "https://exemplo.com/prato_teste.jpg"
        );
    }
}
