package br.com.fiap.monitoramento_ambiental_api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.Data;

import java.time.LocalDateTime;

@Document(collection = "historico_agua")
@Data
public class HistoricoAgua {

    @Id
    private String id;

    private LocalDateTime dataHora;
    
    private String localizacao;
    
    private Double ph;
    
    private Double oxigenioDissolvido;
    
    private Double turbidez;
    
    private Double coliformesTotais;
    
    private Double fosforoTotal;
    
    @DBRef
    private MonitoramentoAgua monitoramentoAgua;
}
