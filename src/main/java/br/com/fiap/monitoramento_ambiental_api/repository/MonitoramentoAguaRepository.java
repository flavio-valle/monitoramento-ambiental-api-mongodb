package br.com.fiap.monitoramento_ambiental_api.repository;

import br.com.fiap.monitoramento_ambiental_api.model.MonitoramentoAgua;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MonitoramentoAguaRepository extends MongoRepository<MonitoramentoAgua, String> {
}
