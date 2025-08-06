package com.posfiap.techfood.core.application.gateways;

import com.posfiap.techfood.core.application.dto.NovoUsuarioDTO;
import com.posfiap.techfood.core.application.dto.UsuarioDTO;
import com.posfiap.techfood.core.application.interfaces.usuario.IUsuarioDataSource;
import com.posfiap.techfood.core.domain.entities.Usuario;
import com.posfiap.techfood.infrastructure.models.enums.PerfilUsuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.posfiap.techfood.infrastructure.models.enums.PerfilUsuario;


class UsuarioGatewayImpTest {

    @Mock
    private IUsuarioDataSource dataSource;

    @InjectMocks
    private UsuarioGatewayImp usuarioGatewayImp;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuarioGatewayImp = UsuarioGatewayImp.create(dataSource);
    }

    @Test
    void testFindById_UsuarioEncontrado() {
        Long id = 1L;
        UsuarioDTO usuarioDTO = new UsuarioDTO(
                "11987654321",
                id,
                "Nome Teste",
                PerfilUsuario.CLIENTE.toString(),
                "12345678900",
                "teste@example.com",
                "testuser",
                "hashedpassword",
                LocalDate.now(),
                LocalDate.now(),
                LocalDate.now()
        );
        when(dataSource.findById(id)).thenReturn(Optional.of(usuarioDTO));

        Optional<Usuario> result = usuarioGatewayImp.findById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
        assertEquals(usuarioDTO.email(), result.get().getEmail());
        verify(dataSource, times(1)).findById(id);
    }

    @Test
    void testFindById_UsuarioNaoEncontrado() {
        Long id = 1L;
        when(dataSource.findById(id)).thenReturn(Optional.empty());

        Optional<Usuario> result = usuarioGatewayImp.findById(id);

        assertFalse(result.isPresent());
        verify(dataSource, times(1)).findById(id);
    }

    @Test
    void testFindByEmail_UsuarioEncontrado() {
        String email = "teste@example.com";
        UsuarioDTO usuarioDTO = new UsuarioDTO(
                "11987654321",
                1L,
                "Nome Teste",
                PerfilUsuario.CLIENTE.toString(),
                "12345678900",
                email,
                "testuser",
                "hashedpassword",
                LocalDate.now(),
                LocalDate.now(),
                LocalDate.now()
        );
        when(dataSource.findByEmail(email)).thenReturn(Optional.of(usuarioDTO));

        Optional<Usuario> result = usuarioGatewayImp.findByEmail(email);

        assertTrue(result.isPresent());
        assertEquals(email, result.get().getEmail());
        verify(dataSource, times(1)).findByEmail(email);
    }

    @Test
    void testFindByEmail_UsuarioNaoEncontrado() {
        String email = "naoexiste@example.com";
        when(dataSource.findByEmail(email)).thenReturn(Optional.empty());

        Optional<Usuario> result = usuarioGatewayImp.findByEmail(email);

        assertFalse(result.isPresent());
        verify(dataSource, times(1)).findByEmail(email);
    }

    @Test
    void testFindByUsername_UsuarioEncontrado() {
        String username = "testuser";
        UsuarioDTO usuarioDTO = new UsuarioDTO(
                "11987654321",
                1L,
                "Nome Teste",
                PerfilUsuario.CLIENTE.toString(),
                "12345678900",
                "teste@example.com",
                username,
                "hashedpassword",
                LocalDate.now(),
                LocalDate.now(),
                LocalDate.now()
        );
        when(dataSource.findByUserame(username)).thenReturn(Optional.of(usuarioDTO));

        Optional<Usuario> result = usuarioGatewayImp.findByUserame(username);

        assertTrue(result.isPresent());
        assertEquals(username, result.get().getUsername());
        verify(dataSource, times(1)).findByUserame(username);
    }

    @Test
    void testFindByUsername_UsuarioNaoEncontrado() {
        String username = "naoexiste";
        when(dataSource.findByUserame(username)).thenReturn(Optional.empty());

        Optional<Usuario> result = usuarioGatewayImp.findByUserame(username);

        assertFalse(result.isPresent());
        verify(dataSource, times(1)).findByUserame(username);
    }

    @Test
    void testFindAll() {
        int size = 2;
        int offset = 0;        UsuarioDTO usuarioDTO1 = new UsuarioDTO(
                "11111111111", 1L, "Nome1", PerfilUsuario.CLIENTE.toString(), "11111111111", "e1@example.com", "user1", "pass1", LocalDate.now(), LocalDate.now(), LocalDate.now());
        UsuarioDTO usuarioDTO2 = new UsuarioDTO(
                "22222222222", 2L, "Nome2", PerfilUsuario.PROPRIETARIO.toString(), "22222222222", "e2@example.com", "user2", "pass2", LocalDate.now(), LocalDate.now(), LocalDate.now());
        List<UsuarioDTO> dtoList = Arrays.asList(usuarioDTO1, usuarioDTO2);

        when(dataSource.findAll(size, offset)).thenReturn(dtoList);

        List<Usuario> result = usuarioGatewayImp.findAll(size, offset);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(usuarioDTO1.email(), result.get(0).getEmail());
        assertEquals(usuarioDTO2.email(), result.get(1).getEmail());
        verify(dataSource, times(1)).findAll(size, offset);
    }

    @Test
    void testUpdate() {
        // O método update na implementação atual retorna null, então o teste deve refletir isso.
        // Se a lógica de update for implementada no futuro, este teste precisará ser atualizado.
        Usuario usuario = new Usuario();
        long id = 1L;

        Usuario result = usuarioGatewayImp.update(usuario, id);

        assertNull(result);
        // Nenhuma interação com dataSource é esperada, pois o método retorna null diretamente.
        verifyNoInteractions(dataSource);
    }

    @Test
    void testSave() {
        Usuario usuario = Usuario.create(
                PerfilUsuario.CLIENTE.toString(),
                "Nome Teste",
                "teste@example.com",
                "11987654321",
                "12345678900",
                "testuser",
                "password123"
        );
        UsuarioDTO usuarioCriadoDTO = new UsuarioDTO(
                usuario.getTelefone(),
                1L,
                usuario.getNome(),
                usuario.getTipoUsuario().toString(),
                usuario.getCpf(),
                usuario.getEmail(),
                usuario.getUsername(),
                usuario.getPassword(),
                LocalDate.now(),
                LocalDate.now(),
                LocalDate.now()
        );

        when(dataSource.save(any(NovoUsuarioDTO.class))).thenReturn(usuarioCriadoDTO);

        Usuario result = usuarioGatewayImp.save(usuario);

        assertNotNull(result);
        assertEquals(usuarioCriadoDTO.id(), result.getId());
        assertEquals(usuarioCriadoDTO.email(), result.getEmail());
        verify(dataSource, times(1)).save(any(NovoUsuarioDTO.class));
    }

    @Test
    void testDelete() {
        long id = 1L;
        when(dataSource.delete(id)).thenReturn(1);

        Integer result = usuarioGatewayImp.delete(id);

        assertEquals(1, result);
        verify(dataSource, times(1)).delete(id);
    }

    @Test
    void testVerificarSenha() {
        String senhaInformada = "senha123";
        String senhaUsuario = "hashedsenha";
        when(dataSource.verificarSenha(senhaInformada, senhaUsuario)).thenReturn(true);

        Boolean result = usuarioGatewayImp.verificarSenha(senhaInformada, senhaUsuario);

        assertTrue(result);
        verify(dataSource, times(1)).verificarSenha(senhaInformada, senhaUsuario);
    }
}


