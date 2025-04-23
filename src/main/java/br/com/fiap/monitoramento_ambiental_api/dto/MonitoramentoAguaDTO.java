package br.com.fiap.monitoramento_ambiental_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MonitoramentoAguaDTO {

    private String id;

    @NotNull
    private LocalDateTime dataHora;

    @NotBlank
    private String localizacao;

    private Double ph;
    private Double oxigenioDissolvido;
    private Double turbidez;
    private Double coliformesTotais;
    private Double fosforoTotal;
}

