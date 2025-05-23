-- V2__create_sequences.sql
-- Migration para criação das sequências necessárias para cada tabela

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
    START WITH 1
    INCREMENT BY 1
    NOCACHE;
