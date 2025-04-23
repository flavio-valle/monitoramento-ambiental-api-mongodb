package br.com.fiap.monitoramento_ambiental_api.services;

import br.com.fiap.monitoramento_ambiental_api.exceptions.EntidadeNaoEncontradaException;
import br.com.fiap.monitoramento_ambiental_api.model.HistoricoAgua;
import br.com.fiap.monitoramento_ambiental_api.model.MonitoramentoAgua;
import br.com.fiap.monitoramento_ambiental_api.repository.HistoricoAguaRepository;
import br.com.fiap.monitoramento_ambiental_api.repository.MonitoramentoAguaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricoAguaService {

    @Autowired
    private HistoricoAguaRepository repository;

    @Autowired
    private MonitoramentoAguaRepository monitoramentoAguaRepository;

    public List<HistoricoAgua> listarTodos() {
        return repository.findAll();
    }

    public HistoricoAgua buscarPorId(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Histórico de água não encontrado com o ID: " + id));
    }

    public HistoricoAgua salvar(HistoricoAgua historicoAgua) {
        return repository.save(historicoAgua);
    }

    public void deletar(String id) {
        if (!repository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Histórico de água não encontrado com o ID: " + id);
        }
        repository.deleteById(id);
    }

    // Método para associar o MonitoramentoAgua ao HistoricoAgua
    public MonitoramentoAgua obterMonitoramentoAguaPorId(String id) {
        return monitoramentoAguaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Monitoramento de água não encontrado com o ID: " + id));
    }
}
