package br.com.fiap.monitoramento_ambiental_api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import java.time.LocalDateTime;

@Document(collection = "monitoramento_agua")
@Data
public class MonitoramentoAgua {

    @Id
    private String id;

    private LocalDateTime dataHora;

    private String localizacao;

    private Double ph;

    private Double oxigenioDissolvido;

    private Double turbidez;

    private Double coliformesTotais;

    private Double fosforoTotal;
}
