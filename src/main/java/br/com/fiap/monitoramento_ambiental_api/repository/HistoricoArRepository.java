package br.com.fiap.monitoramento_ambiental_api.repository;

import br.com.fiap.monitoramento_ambiental_api.model.HistoricoAr;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HistoricoArRepository extends MongoRepository<HistoricoAr, String> {
}
