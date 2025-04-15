package br.com.fiap.monitoramento_ambiental_api.dto;



import br.com.fiap.monitoramento_ambiental_api.model.Usuario;

public record UsuarioExibicaoDTO(
        String usuarioId,
        String nome,
        String email) {

    public UsuarioExibicaoDTO(Usuario usuario) {
        this(
                usuario.getUsuarioId(),
                usuario.getNome(),
                usuario.getEmail());
    }

    public UsuarioExibicaoDTO() {
        this(null, null, null);
    }

    @Override
    public String toString() {
        return "UsuarioExibicaoDTO [usuarioId=" + usuarioId + ", nome=" + nome + ", email=" + email + "]";
    }
}
