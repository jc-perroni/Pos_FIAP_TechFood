package com.posfiap.techfood.infrastructure.services;

import com.posfiap.techfood.infrastructure.exceptions.InvalidPasswordException;
import com.posfiap.techfood.infrastructure.exceptions.ResourceNotFoundException;
import com.posfiap.techfood.infrastructure.models.Usuario;
import com.posfiap.techfood.infrastructure.repositories.UsuarioRepository;
import com.posfiap.techfood.models.enums.PerfilUsuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static com.posfiap.techfood.infrastructure.services.ValidaSenhaService.autenticarSenha;
import static com.posfiap.techfood.infrastructure.utils.HashPassword.hashPassword;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAlterarSenhaComNovaSenha() {
        Usuario usuario = new Usuario();
        String novaSenha = "novaSenha123";

        usuarioService.alterarSenha(novaSenha, usuario);

        assertNotNull(usuario.getPassword());
        // Não é possível verificar a senha diretamente, pois ela é hashed
        // Poderíamos mockar HashPassword.hashPassword para retornar um valor conhecido para teste
    }

    @Test
    void testAlterarSenhaComSenhaAntigaESenhaNova_Sucesso() {
        Usuario usuario = new Usuario();
        String senhaAntiga = "senhaAntiga123";
        String senhaNova = "senhaNova123";

        // Mockando o comportamento de autenticarSenha e hashPassword
        try (var mockedValidaSenhaService = mockStatic(ValidaSenhaService.class)) {
            mockedValidaSenhaService.when(() -> ValidaSenhaService.autenticarSenha(senhaAntiga, usuario)).thenReturn(true);
            try (var mockedHashPassword = mockStatic(com.posfiap.techfood.infrastructure.utils.HashPassword.class)) {
                mockedHashPassword.when(() -> com.posfiap.techfood.infrastructure.utils.HashPassword.hashPassword(senhaNova)).thenReturn("hashedNovaSenha");

                usuarioService.alterarSenha(senhaAntiga, senhaNova, usuario);

                assertEquals("hashedNovaSenha", usuario.getPassword());
            }
        }
    }

    @Test
    void testAlterarSenhaComSenhaAntigaESenhaNova_FalhaSenhaInvalida() {
        Usuario usuario = new Usuario();
        String senhaAntiga = "senhaAntigaInvalida";
        String senhaNova = "senhaNova123";

        // Mockando o comportamento de autenticarSenha
        try (var mockedValidaSenhaService = mockStatic(ValidaSenhaService.class)) {
            mockedValidaSenhaService.when(() -> ValidaSenhaService.autenticarSenha(senhaAntiga, usuario)).thenReturn(false);

            assertThrows(InvalidPasswordException.class, () -> usuarioService.alterarSenha(senhaAntiga, senhaNova, usuario));
        }
    }

    @Test
    void testAlterarPerfil_Sucesso() {
        Long id = 1L;
        PerfilUsuario novoPerfil = PerfilUsuario.PROPRIETARIO;
        Usuario usuario = new Usuario();

        usuario.setPerfil(PerfilUsuario.CLIENTE);

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        usuarioService.alterarPerfil(id, novoPerfil);

        assertEquals(novoPerfil, usuario.getPerfil());
        verify(usuarioRepository, times(1)).findById(id);
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void testAlterarPerfil_UsuarioNaoEncontrado() {
        Long id = 1L;
        PerfilUsuario novoPerfil = PerfilUsuario.PROPRIETARIO;

        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> usuarioService.alterarPerfil(id, novoPerfil));
        verify(usuarioRepository, times(1)).findById(id);
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }
}


