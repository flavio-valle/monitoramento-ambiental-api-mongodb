package br.com.fiap.monitoramento_ambiental_api.repository;

import br.com.fiap.monitoramento_ambiental_api.model.HistoricoAgua;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HistoricoAguaRepository extends MongoRepository<HistoricoAgua, String> {
}
