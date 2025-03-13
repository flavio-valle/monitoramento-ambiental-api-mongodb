package br.com.fiap.monitoramento_ambiental_api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import java.time.LocalDateTime;

@Document(collection = "historico_ar")
@Data
public class HistoricoAr {

    @Id
    private String id;

    private LocalDateTime dataHora;

    private String localizacao;

    private Double monoxidoCarbono;

    private Double ozonio;

    private Double dioxidoNitrogenio;

    private Double dioxidoEnxofre;

    @DBRef
    private MonitoramentoAr monitoramentoAr;
}
