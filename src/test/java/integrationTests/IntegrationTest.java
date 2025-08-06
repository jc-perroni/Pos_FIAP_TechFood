package integrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.posfiap.techfood.infrastructure.models.*;
import com.posfiap.techfood.infrastructure.models.dto.cliente.ClienteDTO;
import com.posfiap.techfood.infrastructure.models.dto.cliente.ClienteLoginDTO;
import com.posfiap.techfood.infrastructure.models.dto.cliente.ClienteUpdateDTO;
import com.posfiap.techfood.infrastructure.models.dto.prato.PratoUpdateDTO;
import com.posfiap.techfood.infrastructure.models.dto.proprietario.ProprietarioDTO;
import com.posfiap.techfood.infrastructure.models.dto.proprietario.ProprietarioUpdateDTO;
import com.posfiap.techfood.infrastructure.models.dto.restaurante.RestauranteDTO;
import com.posfiap.techfood.infrastructure.models.dto.restaurante.RestauranteResponseDTO;
import com.posfiap.techfood.infrastructure.models.enums.PerfilUsuario;
import integrationTests.utils.DataFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = com.posfiap.techfood.TechfoodApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());
    private final DataFactory dataFactory = new DataFactory();

    @Test
    @Order(1)
    void deveAdicionarClientes() throws Exception {
        List<Usuario> usuarios = dataFactory.gerarClientes();
        List<ClienteDTO> clientesDTO = usuarios.stream()
                .map(u -> new ClienteDTO(
                        u.getTelefone(),
                        u.getCpf(),
                        u.getNome(),
                        u.getEmail(),
                        u.getUsername(),
                        "1234e",
                        null,
                        null,
                        null
                )).toList();
        for (ClienteDTO cliente : clientesDTO) {
            mockMvc.perform(post("/v1/clientes")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(cliente)))
                    .andExpect(status().isCreated());
        }

        MvcResult result = mockMvc.perform(get("/v1/clientes")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andReturn();

        List<Usuario> clientesPersistidos = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Usuario.class)
        );

        assertEquals(5, clientesPersistidos.size());
        assertTrue(clientesPersistidos.stream()
                .allMatch(c -> ((Usuario) c).getPerfil() == PerfilUsuario.CLIENTE));
        assertEquals(usuarios.get(0).getNome(), (clientesPersistidos.get(0)).getNome());
        assertEquals(usuarios.get(4).getNome(), (clientesPersistidos.get(4)).getNome());

        MvcResult byId = mockMvc.perform(get("/v1/clientes/1"))
                .andExpect(status().isOk())
                .andReturn();

        Usuario clienteBuscado = objectMapper.readValue(byId.getResponse().getContentAsString(), Usuario.class);
        assertEquals(usuarios.getFirst().getNome(), clienteBuscado.getNome());
        assertNotNull(clienteBuscado.getDataCriacaoConta());
    }

    @Test
    @Order(2)
    void deveAlterarClientePersistido() throws Exception {

        MvcResult byId = mockMvc.perform(get("/v1/clientes/1"))
                .andExpect(status().isOk())
                .andReturn();

        Usuario cliente = objectMapper.readValue(byId.getResponse().getContentAsString(), Usuario.class);

        var updateDTO = new ClienteUpdateDTO(
                "Nome Alterado",
                cliente.getCpf(),
                cliente.getTelefone(),
                "novoemail@email.com"
        );

        mockMvc.perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put("/v1/clientes/" + 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDTO))
        ).andExpect(status().isNoContent());

        byId = mockMvc.perform(get("/v1/clientes/1"))
                .andExpect(status().isOk())
                .andReturn();

        Usuario clienteAtualizado = objectMapper.readValue(byId.getResponse().getContentAsString(), Usuario.class);
        assertEquals("Nome Alterado", clienteAtualizado.getNome());
        assertEquals("novoemail@email.com", clienteAtualizado.getEmail());
    }
    @Test
    @Order(3)
    void deveDeletarCliente() throws Exception {

        ResultActions deleteCliente = mockMvc.perform((delete("/v1/clientes/1")))
                .andExpect((status().isOk()));
        MvcResult result = mockMvc.perform(get("/v1/clientes").param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andReturn();

        List<?> clientesPersistidos = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Usuario.class)
        );
        assertEquals(4, clientesPersistidos.size());
    }

    @Test
    @Order(4)
    void deveAdicionarProprietarios() throws Exception {
        List<Usuario> proprietarios = dataFactory.gerarProprietarios();
        var proprietariosDTO = proprietarios.stream()
                .map(p -> new ProprietarioDTO(
                        p.getTelefone(),
                        p.getNome(),
                        p.getCpf(),
                        p.getEmail(),
                        p.getUsername(),
                        "1234e",
                        null, null, null
                )).toList();

        for (var dto : proprietariosDTO) {
            mockMvc.perform(post("/v1/proprietarios")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dto)))
                    .andExpect(status().isCreated());
        }

        MvcResult result = mockMvc.perform(get("/v1/proprietarios")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andReturn();

        List<Usuario> proprietariosPersistidos = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Usuario.class)
        );

        assertEquals(5, proprietariosPersistidos.size());
        assertTrue(proprietariosPersistidos.stream()
                .allMatch(p -> p.getPerfil() == PerfilUsuario.PROPRIETARIO));
        assertEquals(proprietarios.get(0).getNome(), proprietariosPersistidos.get(0).getNome());
        assertEquals(proprietarios.get(4).getNome(), proprietariosPersistidos.get(4).getNome());
    }

    @Test
    @Order(5)
    void deveAlterarProprietarioPersistido() throws Exception {
        MvcResult byId = mockMvc.perform(get("/v1/proprietarios/6"))
                .andExpect(status().isOk())
                .andReturn();

        Usuario proprietario = objectMapper.readValue(byId.getResponse().getContentAsString(), Usuario.class);

        var updateDTO = new ProprietarioUpdateDTO(
                "Nome Proprietario Alterado",
                proprietario.getCpf(),
                proprietario.getTelefone(),
                "novoemailproprietario@email.com"
        );

        mockMvc.perform(put("/v1/proprietarios/6")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isNoContent());

        byId = mockMvc.perform(get("/v1/proprietarios/6"))
                .andExpect(status().isOk())
                .andReturn();

        Usuario proprietarioAtualizado = objectMapper.readValue(byId.getResponse().getContentAsString(), Usuario.class);
        assertEquals("Nome Proprietario Alterado", proprietarioAtualizado.getNome());
        assertEquals("novoemailproprietario@email.com", proprietarioAtualizado.getEmail());
    }

    @Test
    @Order(6)
    void deveDeletarProprietario() throws Exception {
        mockMvc.perform(delete("/v1/proprietarios/6"))
                .andExpect(status().isOk());

        MvcResult result = mockMvc.perform(get("/v1/proprietarios")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andReturn();

        List<Usuario> proprietariosPersistidos = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Usuario.class)
        );
        assertEquals(4, proprietariosPersistidos.size());
    }

    @Test
    @Order(7)
    void deveAdicionarRestaurantes() throws Exception {

        MvcResult result = mockMvc.perform(get("/v1/proprietarios")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andReturn();

        List<Usuario> proprietariosPersistidos = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Usuario.class)
        );

        List<RestauranteDTO> restaurantesDTO = dataFactory.gerarRestaurantes(proprietariosPersistidos);

        for (var dto : restaurantesDTO.subList(0, 4)) {
            mockMvc.perform(post("/v1/restaurantes")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dto)))
                    .andExpect(status().isCreated());
        }

        result = mockMvc.perform(get("/v1/restaurantes")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andReturn();

        List<RestauranteResponseDTO> restaurantesPersistidos = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, RestauranteResponseDTO.class)
        );

        assertEquals(4, restaurantesPersistidos.size());
        assertEquals("COMIDA BRASILEIRA", (restaurantesPersistidos.get(0)).nome());
        assertEquals("BURGER GRILL", (restaurantesPersistidos.get(3)).nome());
    }

    @Test
    @Order(8)
    void deveAlterarRestaurantePersistido() throws Exception {
        long restauranteId = 1L;

        MvcResult byId = mockMvc.perform(get("/v1/restaurantes/" + restauranteId))
                .andExpect(status().isOk())
                .andReturn();

        com.fasterxml.jackson.databind.JsonNode restauranteJson = objectMapper.readTree(byId.getResponse().getContentAsString());
        Restaurante restaurante = objectMapper.treeToValue(restauranteJson, Restaurante.class);

        restaurante.setNome("Restaurante Alterado");
        restaurante.setTelefone("9999-9999");
        restaurante.setHorarioFuncionamento("09:00 às 21:00");

        mockMvc.perform(put("/v1/restaurantes/" + restauranteId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(restaurante)))
                .andExpect(status().isNoContent());

        byId = mockMvc.perform(get("/v1/restaurantes/" + restauranteId))
                .andExpect(status().isOk())
                .andReturn();

        restauranteJson = objectMapper.readTree(byId.getResponse().getContentAsString());
        restaurante = objectMapper.treeToValue(restauranteJson, Restaurante.class);

        assertEquals("Restaurante Alterado", restaurante.getNome());
        assertEquals("9999-9999", restaurante.getTelefone());
        assertEquals("09:00 às 21:00", restaurante.getHorarioFuncionamento());
    }

    @Test
    @Order(9)
    void deveDeletarRestaurante() throws Exception {
        mockMvc.perform(delete("/v1/restaurantes/1"))
                .andExpect(status().isOk());

        MvcResult result = mockMvc.perform(get("/v1/restaurantes")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andReturn();

        List<RestauranteResponseDTO> restaurantesPersistidos = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, RestauranteResponseDTO.class)
        );
        assertEquals(3, restaurantesPersistidos.size());
    }

    @Test
    @Order(10)
    void deveAdicionarEnderecos() throws Exception {
        MvcResult clientesResult = mockMvc.perform(get("/v1/clientes")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andReturn();
        List<Usuario> clientesPersistidos = objectMapper.readValue(
                clientesResult.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Usuario.class)
        );

        MvcResult restaurantesResult = mockMvc.perform(get("/v1/restaurantes")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andReturn();
        List<RestauranteResponseDTO> restaurantesPersistidos = objectMapper.readValue(
                restaurantesResult.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, RestauranteResponseDTO.class)
        );

        var enderecosClientes = dataFactory.gerarEnderecosClientes(clientesPersistidos);
        var enderecosRestaurantes = dataFactory.gerarEnderecosRestaurantesPorIds(List.of(2L, 3L, 4L));

        for (var dto : enderecosClientes) {
            mockMvc.perform(post("/v1/enderecos")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dto)))
                    .andExpect(status().isCreated());
        }
        for (var dto : enderecosRestaurantes) {
            mockMvc.perform(post("/v1/enderecos")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dto)))
                    .andExpect(status().isCreated());
        }

        MvcResult result = mockMvc.perform(get("/v1/enderecos")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andReturn();

        List<?> enderecosPersistidos = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Endereco.class)
        );
        assertEquals(7, enderecosPersistidos.size());
    }

    @Test
    @Order(11)
    void deveAlterarEnderecoPersistido() throws Exception {
        MvcResult byId = mockMvc.perform(get("/v1/enderecos/1"))
                .andExpect(status().isOk())
                .andReturn();

        Endereco endereco = objectMapper.readValue(
                byId.getResponse().getContentAsString(),
                Endereco.class
        );

        endereco.setRua("Rua Alterada");
        endereco.setNumero("999");
        endereco.setBairro("Bairro Novo");

        mockMvc.perform(put("/v1/enderecos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(endereco)))
                .andExpect(status().isNoContent());

        byId = mockMvc.perform(get("/v1/enderecos/1"))
                .andExpect(status().isOk())
                .andReturn();

        Endereco enderecoAtualizado = objectMapper.readValue(
                byId.getResponse().getContentAsString(),
                Endereco.class
        );
        assertEquals("Rua Alterada", enderecoAtualizado.getRua());
        assertEquals("999", enderecoAtualizado.getNumero());
        assertEquals("Bairro Novo", enderecoAtualizado.getBairro());
    }

    @Test
    @Order(12)
    void deveDeletarEndereco() throws Exception {
        mockMvc.perform(delete("/v1/enderecos/1"))
                .andExpect(status().isOk());

        MvcResult result = mockMvc.perform(get("/v1/enderecos")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andReturn();

        List<?> enderecosPersistidos = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Endereco.class)
        );
        assertEquals(6, enderecosPersistidos.size());
    }
    @Test
    @Order(13)
    void deveAutenticarClienteComSucesso() throws Exception {
        var loginSucesso = new ClienteLoginDTO();
        loginSucesso.setUsuario("quarto.aluno4");
        loginSucesso.setSenha("1234e");

        mockMvc.perform(post("/v1/login/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginSucesso)))
                .andExpect(status().isOk());
    }
    @Test
    @Order(14)
    void naoDeveAutenticarClienteSenhaErrada() throws Exception {
        var loginFalha = new ClienteLoginDTO();
        loginFalha.setUsuario("quarto.aluno4");
        loginFalha.setSenha("senhaErrada");

        mockMvc.perform(post("/v1/login/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginFalha)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @Order(15)
    void deveAlterarSenhaClienteEValidarLogin() throws Exception {
        var alterarSenhaJson = objectMapper.createObjectNode();
        alterarSenhaJson.put("username", "quarto.aluno4");
        alterarSenhaJson.put("senhaAntiga", "1234e");
        alterarSenhaJson.put("senhaNova", "novaSenha123");

        mockMvc.perform(post("/v1/alterar-senha/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(alterarSenhaJson)))
                .andExpect(status().isNoContent());

        var loginAntigo = new ClienteLoginDTO();
        loginAntigo.setUsuario("quarto.aluno4");
        loginAntigo.setSenha("1234e");

        mockMvc.perform(post("/v1/login/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginAntigo)))
                .andExpect(status().isUnauthorized());

        var loginNovo = new ClienteLoginDTO();
        loginNovo.setUsuario("quarto.aluno4");
        loginNovo.setSenha("novaSenha123");

        mockMvc.perform(post("/v1/login/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginNovo)))
                .andExpect(status().isOk());
    }

    @Test
    @Order(16)
    void deveAdicionarCardapio() throws Exception {
        List<Restaurante> listaRestaurantes = new ArrayList<>();

        MvcResult restauranteResult = mockMvc.perform(get("/v1/restaurantes")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andReturn();

        List<RestauranteResponseDTO> restaurantes = objectMapper.readValue(
                restauranteResult.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, RestauranteResponseDTO.class)
        );

        for (RestauranteResponseDTO restaurante : restaurantes) {
            MvcResult resultado = mockMvc.perform(get("/v1/restaurantes/" + restaurante.id())).andExpect(status().isOk()).andReturn();
            listaRestaurantes.add(objectMapper.readValue(resultado.getResponse().getContentAsString(), Restaurante.class));
        }
        Cardapio cardapio = dataFactory.gerarCardapiosComPratos(listaRestaurantes).getFirst();
        mockMvc.perform(post("/v1/cardapio")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cardapio)))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(17)
    void deveBuscarCardapios() throws Exception {
        MvcResult result = mockMvc.perform(get("/v1/cardapio")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andReturn();

        List<?> cardapios = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Cardapio.class)
        );
        assertFalse(cardapios.isEmpty());
    }

    @Test
    @Order(18)
    void deveBuscarCardapioPorId() throws Exception {
        MvcResult result = mockMvc.perform(get("/v1/cardapio")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andReturn();

        List<Cardapio> cardapios = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Cardapio.class)
        );

        Long id = cardapios.getFirst().getId();

        mockMvc.perform(get("/v1/cardapio/" + id))
                .andExpect(status().isOk());
    }

    @Test
    @Order(19)
    void deveAtualizarCardapio() throws Exception {
        MvcResult cardapiosResult = mockMvc.perform(get("/v1/cardapio")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andReturn();

        List<Cardapio> cardapios = objectMapper.readValue(
                cardapiosResult.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Cardapio.class)
        );
        assertFalse(cardapios.isEmpty(), "Nenhum cardápio encontrado para teste");

        Cardapio cardapio = cardapios.getFirst();

        MvcResult cardapioResult = mockMvc.perform(get("/v1/cardapio/" + cardapio.getId()))
                .andExpect(status().isOk())
                .andReturn();

        Cardapio cardapioBuscado = objectMapper.readValue(
                cardapioResult.getResponse().getContentAsString(),
                Cardapio.class
        );

        assertFalse(cardapioBuscado.getPratos().isEmpty(), "Cardápio não possui pratos");
        String nomeAntigo = cardapioBuscado.getPratos().getFirst().getNome();
        cardapioBuscado.getPratos().getFirst().setNome("Prato Alterado");

        mockMvc.perform(put("/v1/cardapio/" + cardapioBuscado.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cardapioBuscado)))
                .andExpect(status().isNoContent());

        MvcResult cardapioAtualizadoResult = mockMvc.perform(get("/v1/cardapio/" + cardapioBuscado.getId()))
                .andExpect(status().isOk())
                .andReturn();

        Cardapio cardapioAtualizado = objectMapper.readValue(
                cardapioAtualizadoResult.getResponse().getContentAsString(),
                Cardapio.class
        );

        assertEquals("Prato Alterado", cardapioAtualizado.getPratos().getFirst().getNome());
        assertNotEquals(nomeAntigo, cardapioAtualizado.getPratos().getFirst().getNome());
    }

    @Test
    @Order(26)
    void deveDeletarCardapio() throws Exception {
        MvcResult result = mockMvc.perform(get("/v1/cardapio")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andReturn();

        List<Cardapio> cardapios = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Cardapio.class)
        );

        Long id = cardapios.getFirst().getId();

        mockMvc.perform(delete("/v1/cardapio/" + id))
                .andExpect(status().isOk());
    }

    @Test
    @Order(21)
    void deveAdicionarPrato() throws Exception {
        MvcResult cardapiosResult = mockMvc.perform(get("/v1/cardapio")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andReturn();

        List<Cardapio> cardapios = objectMapper.readValue(
                cardapiosResult.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Cardapio.class)
        );
        assertFalse(cardapios.isEmpty());
        Long cardapioId = cardapios.getFirst().getId();

        var pratoDTO = dataFactory.getPratoDTO();

        mockMvc.perform(post("/v1/prato")
                        .param("cardapioId", cardapioId.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pratoDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(22)
    void deveBuscarPratos() throws Exception {
        MvcResult result = mockMvc.perform(get("/v1/prato")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andReturn();

        List<Prato> pratos = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Prato.class)
        );
        assertFalse(pratos.isEmpty());
    }

    @Test
    @Order(23)
    void deveBuscarPratoPorId() throws Exception {
        MvcResult result = mockMvc.perform(get("/v1/prato")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andReturn();

        List<Prato> pratos = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Prato.class)
        );
        assertFalse(pratos.isEmpty());
        Long id = pratos.getFirst().getId();

        mockMvc.perform(get("/v1/prato/" + id))
                .andExpect(status().isOk());
    }

    @Test
    @Order(24)
    void deveAtualizarPrato() throws Exception {
        MvcResult result = mockMvc.perform(get("/v1/prato")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andReturn();

        List<Prato> pratos = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Prato.class)
        );
        assertFalse(pratos.isEmpty());
        Prato prato = pratos.getFirst();

        var updateDTO = new PratoUpdateDTO(
                "Nome Prato Atualizado",
                prato.getDescricao(),
                prato.getPreco() + 1.0,
                prato.getApenasConsumoLocal(),
                prato.getLinkImagem()
        );

        mockMvc.perform(put("/v1/prato/" + prato.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isNoContent());

        MvcResult byId = mockMvc.perform(get("/v1/prato/" + prato.getId()))
                .andExpect(status().isOk())
                .andReturn();

        Prato pratoAtualizado = objectMapper.readValue(
                byId.getResponse().getContentAsString(),
                Prato.class
        );
        assertEquals("Nome Prato Atualizado", pratoAtualizado.getNome());
        assertEquals(prato.getPreco() + 1.0, pratoAtualizado.getPreco());
    }

    @Test
    @Order(25)
    void deveDeletarPrato() throws Exception {
        // Buscar um prato existente
        MvcResult result = mockMvc.perform(get("/v1/prato")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andReturn();

        List<Prato> pratos = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Prato.class)
        );
        assertFalse(pratos.isEmpty());
        Long id = pratos.getFirst().getId();

        mockMvc.perform(delete("/v1/prato/" + id))
                .andExpect(status().isOk());
    }

    @Test
    @Order(27)
    void deveAlterarPerfilUsuario() throws Exception {
        MvcResult clientesResult = mockMvc.perform(get("/v1/clientes")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andReturn();
        List<Usuario> clientes = objectMapper.readValue(
                clientesResult.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Usuario.class)
        );
        assertFalse(clientes.isEmpty());
        Usuario cliente = clientes.getFirst();

        var alterarPerfilCliente = objectMapper.createObjectNode();
        alterarPerfilCliente.put("idUsuario", cliente.getId());
        alterarPerfilCliente.put("novoPerfil", "PROPRIETARIO");

        mockMvc.perform(post("/v1/usuarios/alterar-perfil")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(alterarPerfilCliente)))
                .andExpect(status().isNoContent());

        MvcResult clienteAlteradoResult = mockMvc.perform(get("/v1/proprietarios/" + cliente.getId()))
                .andExpect(status().isOk())
                .andReturn();
        Usuario clienteAlterado = objectMapper.readValue(
                clienteAlteradoResult.getResponse().getContentAsString(),
                Usuario.class
        );
        assertEquals(PerfilUsuario.PROPRIETARIO, clienteAlterado.getPerfil());

        MvcResult proprietariosResult = mockMvc.perform(get("/v1/proprietarios")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andReturn();
        List<Usuario> proprietarios = objectMapper.readValue(
                proprietariosResult.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Usuario.class)
        );
        assertFalse(proprietarios.isEmpty());
        Usuario proprietario = proprietarios.getFirst();

        var alterarPerfilProprietario = objectMapper.createObjectNode();
        alterarPerfilProprietario.put("idUsuario", proprietario.getId());
        alterarPerfilProprietario.put("novoPerfil", "CLIENTE");

        mockMvc.perform(post("/v1/usuarios/alterar-perfil")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(alterarPerfilProprietario)))
                .andExpect(status().isNoContent());

        MvcResult proprietarioAlteradoResult = mockMvc.perform(get("/v1/clientes/" + proprietario.getId()))
                .andExpect(status().isOk())
                .andReturn();

        mockMvc.perform(get("/v1/proprietarios/" + proprietario.getId()))
                .andExpect(status().isNotFound());

        Usuario proprietarioAlterado = objectMapper.readValue(
                proprietarioAlteradoResult.getResponse().getContentAsString(),
                Usuario.class
        );
        assertEquals(PerfilUsuario.CLIENTE, proprietarioAlterado.getPerfil());
    }



}