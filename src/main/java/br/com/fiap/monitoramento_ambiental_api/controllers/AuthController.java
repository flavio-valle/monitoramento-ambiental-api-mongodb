package br.com.fiap.monitoramento_ambiental_api.controllers;

import br.com.fiap.monitoramento_ambiental_api.config.TokenService;
import br.com.fiap.monitoramento_ambiental_api.dto.LoginDTO;
import br.com.fiap.monitoramento_ambiental_api.dto.TokenDTO;
import br.com.fiap.monitoramento_ambiental_api.dto.UsuarioCadastroDTO;
import br.com.fiap.monitoramento_ambiental_api.dto.UsuarioExibicaoDTO;
import br.com.fiap.monitoramento_ambiental_api.model.Usuario;
import br.com.fiap.monitoramento_ambiental_api.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginDTO usuarioDto) {
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(
                        usuarioDto.email(),
                        usuarioDto.senha()
                );

        Authentication auth = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(token));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDTO registrar(@RequestBody @Valid UsuarioCadastroDTO usuarioCadastroDTO) {
        return usuarioService.salvarUsuario(usuarioCadastroDTO);
    }

}
