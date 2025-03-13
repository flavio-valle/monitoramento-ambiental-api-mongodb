CREATE TABLE tb_eventos_especiais (
                                      id_evento         INTEGER NOT NULL,
                                      medicao           NUMBER NOT NULL,
                                      dt_hora           DATE NOT NULL,
                                      id_atributo       INTEGER NOT NULL,
                                      id_historico_ar   INTEGER,
                                      id_historico_agua INTEGER
);



ALTER TABLE tb_eventos_especiais ADD CONSTRAINT eventosespeciaisar_pk PRIMARY KEY (id_evento);

CREATE TABLE tb_historico_agua (
                                   id_monitoramento_agua  INTEGER,
                                   dt_hora                DATE NOT NULL,
                                   lc_localizacao         VARCHAR2(30) NOT NULL,
                                   qt_ph                  NUMBER,
                                   qt_oxigenio_dissolvido NUMBER,
                                   qt_turbidez            NUMBER,
                                   qt_coliformes_totais   NUMBER,
                                   qt_fosforo_total       NUMBER,
                                   id_historico_agua      INTEGER NOT NULL
);

ALTER TABLE tb_historico_agua ADD CONSTRAINT historicoagua_pk PRIMARY KEY (id_historico_agua);

CREATE TABLE tb_historico_ar (
                                 id_monitoramento_ar   INTEGER,
                                 dt_hora               DATE NOT NULL,
                                 lc_localizacao        VARCHAR2(30) NOT NULL,
                                 qt_monoxido_carbono   NUMBER,
                                 qt_ozonio             NUMBER,
                                 qt_dioxido_nitrogenio NUMBER,
                                 qt_dioxido_enxofre    NUMBER,
                                 id_historico_ar       INTEGER NOT NULL
);

ALTER TABLE tb_historico_ar ADD CONSTRAINT historicoar_pk PRIMARY KEY (id_historico_ar);

CREATE TABLE tb_monitoramento_agua (
                                       id_monitoramento_agua  INTEGER NOT NULL,
                                       dt_hora                DATE NOT NULL,
                                       lc_localizacao         VARCHAR2(30) NOT NULL,
                                       qt_ph                  NUMBER,
                                       qt_oxigenio_dissolvido NUMBER,
                                       qt_turbidez            NUMBER,
                                       qt_coliformes_totais   NUMBER,
                                       qt_fosforo_total       NUMBER
);

ALTER TABLE tb_monitoramento_agua ADD CONSTRAINT monitoramentoagua_pk PRIMARY KEY (id_monitoramento_agua);

CREATE TABLE tb_monitoramento_ar (
                                     id_monitoramento_ar   INTEGER NOT NULL,
                                     dt_hora               DATE NOT NULL,
                                     lc_localizacao        VARCHAR2(30) NOT NULL,
                                     qt_monoxido_carbono   NUMBER,
                                     qt_ozonio             NUMBER,
                                     qt_dioxido_nitrogenio NUMBER,
                                     qt_dioxido_enxofre    NUMBER
);

ALTER TABLE tb_monitoramento_ar ADD CONSTRAINT monitoramentoar_pk PRIMARY KEY (id_monitoramento_ar);

ALTER TABLE tb_eventos_especiais
    ADD CONSTRAINT eventos_historico_agua_fk FOREIGN KEY (id_historico_agua)
        REFERENCES tb_historico_agua (id_historico_agua);

ALTER TABLE tb_eventos_especiais
    ADD CONSTRAINT eventos_historico_ar_fk FOREIGN KEY (id_historico_ar)
        REFERENCES tb_historico_ar (id_historico_ar);

ALTER TABLE tb_historico_agua
    ADD CONSTRAINT historico_monitor_agua_fk FOREIGN KEY (id_monitoramento_agua)
        REFERENCES tb_monitoramento_agua (id_monitoramento_agua);

ALTER TABLE tb_historico_ar
    ADD CONSTRAINT historico_monitor_ar_fk FOREIGN KEY (id_monitoramento_ar)
        REFERENCES tb_monitoramento_ar (id_monitoramento_ar);


CREATE OR REPLACE TRIGGER TRG_MONITORAMENTO_AR_ALERTA
AFTER INSERT OR UPDATE ON tb_monitoramento_ar
                           FOR EACH ROW
BEGIN
    IF :NEW.qt_monoxido_carbono > 9 THEN
        INSERT INTO tb_eventos_especiais
        (id_evento, medicao, dt_hora, id_historico_ar, id_atributo)
        VALUES (SEQ_EVENTOS_ESPECIAIS.NEXTVAL, :NEW.qt_monoxido_carbono, :NEW.dt_hora, :NEW.id_monitoramento_ar, 4);
END IF;

    IF :NEW.qt_ozonio > 0.07 THEN
        INSERT INTO tb_eventos_especiais
        (id_evento, medicao, dt_hora, id_historico_ar, id_atributo)
        VALUES (SEQ_EVENTOS_ESPECIAIS.NEXTVAL, :NEW.qt_ozonio, :NEW.dt_hora, :NEW.id_monitoramento_ar, 5);
END IF;

    IF :NEW.qt_dioxido_nitrogenio > 0.1 THEN
        INSERT INTO tb_eventos_especiais
        (id_evento, medicao, dt_hora, id_historico_ar, id_atributo)
        VALUES (SEQ_EVENTOS_ESPECIAIS.NEXTVAL, :NEW.qt_dioxido_nitrogenio, :NEW.dt_hora, :NEW.id_monitoramento_ar, 6);
END IF;

    IF :NEW.qt_dioxido_enxofre > 0.075 THEN
        INSERT INTO tb_eventos_especiais
        (id_evento, medicao, dt_hora, id_historico_ar, id_atributo)
        VALUES (SEQ_EVENTOS_ESPECIAIS.NEXTVAL, :NEW.qt_dioxido_enxofre, :NEW.dt_hora, :NEW.id_monitoramento_ar, 7);
END IF;
END;

CREATE OR REPLACE TRIGGER TRG_MONITORAMENTO_AGUA_ALERTA
AFTER INSERT OR UPDATE ON tb_monitoramento_agua
                           FOR EACH ROW
BEGIN
    IF :NEW.qt_ph < 6.5 OR :NEW.qt_ph > 8.5 THEN
        INSERT INTO tb_eventos_especiais
        (id_evento, medicao, dt_hora, id_historico_agua, id_atributo)
        VALUES (SEQ_EVENTOS_ESPECIAIS.NEXTVAL, :NEW.qt_ph, :NEW.dt_hora, :NEW.id_monitoramento_agua, 1);
END IF;

    IF :NEW.qt_oxigenio_dissolvido < 4 THEN
        INSERT INTO tb_eventos_especiais
        (id_evento, medicao, dt_hora, id_historico_agua, id_atributo)
        VALUES (SEQ_EVENTOS_ESPECIAIS.NEXTVAL, :NEW.qt_oxigenio_dissolvido, :NEW.dt_hora, :NEW.id_monitoramento_agua, 2);
END IF;

    IF :NEW.qt_coliformes_totais > 1000 THEN
        INSERT INTO tb_eventos_especiais
        (id_evento, medicao, dt_hora, id_historico_agua, id_atributo)
        VALUES (SEQ_EVENTOS_ESPECIAIS.NEXTVAL, :NEW.qt_coliformes_totais, :NEW.dt_hora, :NEW.id_monitoramento_agua, 3);
END IF;
END;

CREATE OR REPLACE TRIGGER trg_monitoramento_agua
BEFORE INSERT ON tb_monitoramento_agua
FOR EACH ROW
DECLARE
v_count INTEGER;
BEGIN
SELECT COUNT(*) INTO v_count FROM tb_monitoramento_agua;
IF v_count >= 10 THEN
DELETE FROM tb_monitoramento_agua
WHERE id_monitoramento_agua = (SELECT MIN(id_monitoramento_agua) FROM tb_monitoramento_agua);
END IF;
END;

CREATE OR REPLACE TRIGGER trg_monitoramento_ar
BEFORE INSERT ON tb_monitoramento_ar
FOR EACH ROW
DECLARE
v_count INTEGER;
BEGIN
SELECT COUNT(*) INTO v_count FROM tb_monitoramento_ar;
IF v_count >= 10 THEN
DELETE FROM tb_monitoramento_ar
WHERE id_monitoramento_ar = (SELECT MIN(id_monitoramento_ar) FROM tb_monitoramento_ar);
END IF;
END;

CREATE SEQUENCE SEQ_EVENTOS_ESPECIAIS
    START WITH 1
    INCREMENT BY 1
    NOCACHE;


CREATE SEQUENCE SEQ_HISTORICO_AGUA
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

CREATE SEQUENCE SEQ_HISTORICO_AR
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

CREATE SEQUENCE SEQ_MONITORAMENTO_AGUA
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

CREATE SEQUENCE SEQ_MONITORAMENTO_AR
    START WITH
        1
    INCREMENT BY 1
    NOCACHE;

