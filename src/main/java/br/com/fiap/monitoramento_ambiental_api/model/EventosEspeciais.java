package br.com.fiap.monitoramento_ambiental_api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import lombok.Data;

import java.time.LocalDateTime;

@Document(collection = "eventos_especiais")
@Data
public class EventosEspeciais {

    @Id
    private String id;

    private Double medicao;

    private LocalDateTime dataHora;

    private Integer idAtributo;

    @DBRef
    private HistoricoAr historicoAr;

    @DBRef
    private HistoricoAgua historicoAgua;
}
