package br.com.fiap.monitoramento_ambiental_api.services;

import br.com.fiap.monitoramento_ambiental_api.model.MonitoramentoAgua;
import br.com.fiap.monitoramento_ambiental_api.repository.MonitoramentoAguaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MonitoramentoAguaService {

    @Autowired
    private MonitoramentoAguaRepository repository;

    public List<MonitoramentoAgua> listarTodos() {
        return repository.findAll();
    }

    public MonitoramentoAgua buscarPorId(String id) {
        return repository.findById(id).orElse(null);
    }

    public MonitoramentoAgua salvar(MonitoramentoAgua monitoramentoAgua) {
        return repository.save(monitoramentoAgua);
    }

    public void deletar(String id) {
        repository.deleteById(id);
    }
}
