package br.com.fiap.monitoramento_ambiental_api.repository;

import br.com.fiap.monitoramento_ambiental_api.model.MonitoramentoAr;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MonitoramentoArRepository extends MongoRepository<MonitoramentoAr, String> {
}
