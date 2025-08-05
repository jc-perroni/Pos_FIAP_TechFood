package com.posfiap.techfood.core.domain.usecases.usuario;

import com.posfiap.techfood.core.application.dto.NovoUsuarioDTO;
import com.posfiap.techfood.core.application.interfaces.usuario.IUsuarioGateway;
import com.posfiap.techfood.core.domain.entities.Usuario;
import com.posfiap.techfood.core.domain.exceptions.UsuarioJaExistenteException;
import com.posfiap.techfood.models.enums.PerfilUsuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InsertUsuarioUsecaseTest {

    @Mock
    private IUsuarioGateway usuarioGateway;

    private InsertUsuarioUsecase insertUsuarioUsecase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        insertUsuarioUsecase = InsertUsuarioUsecase.create(usuarioGateway);
    }

    @Test
    void testRun_UsuarioNaoExistente_Sucesso() {
        NovoUsuarioDTO novoUsuarioDTO = new NovoUsuarioDTO(
                PerfilUsuario.CLIENTE.toString(),
                "Teste",
                "teste@example.com",
                "11999999999",
                "12345678900",
                "testuser",
                "password123"
        );

        when(usuarioGateway.findByEmail(novoUsuarioDTO.email())).thenReturn(Optional.empty());
        when(usuarioGateway.findByUserame(novoUsuarioDTO.username())).thenReturn(Optional.empty());
        when(usuarioGateway.save(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Usuario usuarioSalvo = insertUsuarioUsecase.run(novoUsuarioDTO);

        assertNotNull(usuarioSalvo);
        assertEquals(novoUsuarioDTO.email(), usuarioSalvo.getEmail());
        assertEquals(novoUsuarioDTO.username(), usuarioSalvo.getUsername());
        verify(usuarioGateway, times(1)).findByEmail(novoUsuarioDTO.email());
        verify(usuarioGateway, times(1)).findByUserame(novoUsuarioDTO.username());
        verify(usuarioGateway, times(1)).save(any(Usuario.class));
    }

    @Test
    void testRun_UsuarioExistentePorEmail_LancaExcecao() {
        NovoUsuarioDTO novoUsuarioDTO = new NovoUsuarioDTO(
                PerfilUsuario.CLIENTE.toString(),
                "Teste",
                "teste@example.com",
                "11999999999",
                "12345678900",
                "testuser",
                "password123"
        );

        when(usuarioGateway.findByEmail(novoUsuarioDTO.email())).thenReturn(Optional.of(new Usuario()));

        UsuarioJaExistenteException exception = assertThrows(UsuarioJaExistenteException.class, () -> insertUsuarioUsecase.run(novoUsuarioDTO));

        assertEquals("Usuario com email: " + novoUsuarioDTO.email() + " ja existe", exception.getMessage());
        verify(usuarioGateway, times(1)).findByEmail(novoUsuarioDTO.email());
        verify(usuarioGateway, never()).findByUserame(anyString());
        verify(usuarioGateway, never()).save(any(Usuario.class));
    }

    @Test
    void testRun_UsuarioExistentePorUsername_LancaExcecao() {
        NovoUsuarioDTO novoUsuarioDTO = new NovoUsuarioDTO(
                PerfilUsuario.CLIENTE.toString(),
                "Teste",
                "teste@example.com",
                "11999999999",
                "12345678900",
                "testuser",
                "password123"
        );

        when(usuarioGateway.findByEmail(novoUsuarioDTO.email())).thenReturn(Optional.empty());
        when(usuarioGateway.findByUserame(novoUsuarioDTO.username())).thenReturn(Optional.of(new Usuario()));

        UsuarioJaExistenteException exception = assertThrows(UsuarioJaExistenteException.class, () -> insertUsuarioUsecase.run(novoUsuarioDTO));

        assertEquals("Usuario com username: " + novoUsuarioDTO.username() + " ja existe", exception.getMessage());
        verify(usuarioGateway, times(1)).findByEmail(novoUsuarioDTO.email());
        verify(usuarioGateway, times(1)).findByUserame(novoUsuarioDTO.username());
        verify(usuarioGateway, never()).save(any(Usuario.class));
    }
}


