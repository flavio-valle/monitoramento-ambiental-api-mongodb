package br.com.fiap.monitoramento_ambiental_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MonitoramentoArDTO {

    private String id;

    @NotNull(message = "A data e hora são obrigatórias.")
    private LocalDateTime dataHora;

    @NotBlank(message = "A localização é obrigatória.")
    private String localizacao;

    private Double monoxidoCarbono;
    private Double ozonio;
    private Double dioxidoNitrogenio;
    private Double dioxidoEnxofre;
}
