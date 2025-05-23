package br.com.fiap.monitoramento_ambiental_api.controllers;

import br.com.fiap.monitoramento_ambiental_api.dto.UsuarioCadastroDTO;
import br.com.fiap.monitoramento_ambiental_api.dto.UsuarioExibicaoDTO;
import br.com.fiap.monitoramento_ambiental_api.model.Usuario;
import br.com.fiap.monitoramento_ambiental_api.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDTO salvar(@RequestBody @Valid UsuarioCadastroDTO usuario){
        return usuarioService.salvarUsuario(usuario);
    }

    @GetMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioExibicaoDTO> litarTodos(){
        return usuarioService.listarTodos();
    }

    @GetMapping("/usuarios/{usuarioId}")
    public ResponseEntity<UsuarioExibicaoDTO> buscarPorId(@PathVariable String usuarioId){
        return ResponseEntity.ok(usuarioService.buscarPorId(usuarioId));
    }

//    @RequestMapping(value = "/usuarios", params = "email")
//    @ResponseStatus(HttpStatus.OK)
//    public UsuarioExibicaoDTO buscarPorEmail(@RequestParam String email){
//        return usuarioService.buscarPorEmail(email);
//    }

    @DeleteMapping("/usuarios/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable String usuarioId){
        usuarioService.excluir(usuarioId);
    }

    @PutMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public Usuario atualizar(@RequestBody Usuario usuario){
        return usuarioService.atualizar(usuario);
    }


}
