package br.com.fiap.monitoramento_ambiental_api.controllers;

import br.com.fiap.monitoramento_ambiental_api.dto.MonitoramentoAguaDTO;
import br.com.fiap.monitoramento_ambiental_api.model.MonitoramentoAgua;
import br.com.fiap.monitoramento_ambiental_api.services.MonitoramentoAguaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/monitoramento-agua")
public class MonitoramentoAguaController {

    @Autowired
    private MonitoramentoAguaService service;

    @GetMapping
    public List<MonitoramentoAguaDTO> listarTodos() {
        List<MonitoramentoAgua> lista = service.listarTodos();
        return lista.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private MonitoramentoAguaDTO convertToDTO(MonitoramentoAgua monitoramentoAgua) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(monitoramentoAgua, MonitoramentoAguaDTO.class);
    }

    private MonitoramentoAgua convertToEntity(MonitoramentoAguaDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, MonitoramentoAgua.class);
    }

    @GetMapping("/{id}")
    public MonitoramentoAgua buscarPorId(@PathVariable String id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public MonitoramentoAgua criar(@RequestBody MonitoramentoAgua monitoramentoAgua) {
        return service.salvar(monitoramentoAgua);
    }

    @PutMapping("/{id}")
    public MonitoramentoAgua atualizar(@PathVariable String id, @RequestBody MonitoramentoAgua monitoramentoAgua) {
        monitoramentoAgua.setId(id);
        return service.salvar(monitoramentoAgua);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable String id) {
        service.deletar(id);
    }
}
