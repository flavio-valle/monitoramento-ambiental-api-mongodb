package br.com.fiap.monitoramento_ambiental_api.repository;

import br.com.fiap.monitoramento_ambiental_api.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Optional<Usuario> findByEmail(String email);
    
    @Override
    Optional<Usuario> findById(String id);
}
