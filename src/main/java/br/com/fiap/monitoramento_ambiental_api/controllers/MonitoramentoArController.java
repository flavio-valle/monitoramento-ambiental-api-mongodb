package br.com.fiap.monitoramento_ambiental_api.controllers;

import br.com.fiap.monitoramento_ambiental_api.dto.MonitoramentoArDTO;
import br.com.fiap.monitoramento_ambiental_api.exceptions.EntidadeNaoEncontradaException;
import br.com.fiap.monitoramento_ambiental_api.model.MonitoramentoAr;
import br.com.fiap.monitoramento_ambiental_api.services.MonitoramentoArService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/monitoramento-ar")
public class MonitoramentoArController {

    @Autowired
    private MonitoramentoArService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<MonitoramentoArDTO> listarTodos() {
        List<MonitoramentoAr> lista = service.listarTodos();
        return lista.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public MonitoramentoArDTO buscarPorId(@PathVariable String id) {
        MonitoramentoAr monitoramentoAr = service.buscarPorId(id);
        if (monitoramentoAr != null) {
            return convertToDTO(monitoramentoAr);
        } else {
            throw new EntidadeNaoEncontradaException("Monitoramento de ar não encontrado com o ID: " + id);
        }
    }

    @PostMapping
    public MonitoramentoArDTO criar(@RequestBody @Valid MonitoramentoArDTO monitoramentoArDTO) {
        MonitoramentoAr monitoramentoAr = convertToEntity(monitoramentoArDTO);
        MonitoramentoAr criado = service.salvar(monitoramentoAr);
        return convertToDTO(criado);
    }

    @PutMapping("/{id}")
    public MonitoramentoArDTO atualizar(@PathVariable String id, @RequestBody @Valid MonitoramentoArDTO monitoramentoArDTO) {
        MonitoramentoAr monitoramentoAr = convertToEntity(monitoramentoArDTO);
        monitoramentoAr.setId(id);
        MonitoramentoAr atualizado = service.salvar(monitoramentoAr);
        return convertToDTO(atualizado);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deletar(id);
    }

    // Métodos utilitários para conversão entre Entidade e DTO
    private MonitoramentoArDTO convertToDTO(MonitoramentoAr monitoramentoAr) {
        return modelMapper.map(monitoramentoAr, MonitoramentoArDTO.class);
    }

    private MonitoramentoAr convertToEntity(MonitoramentoArDTO dto) {
        return modelMapper.map(dto, MonitoramentoAr.class);
    }
}
