CREATE TABLE tb_usuario (
                            USUARIO_ID NUMBER PRIMARY KEY,
                            NOME VARCHAR2(50) UNIQUE NOT NULL,
                            EMAIL VARCHAR2(100) NOT NULL,
                            SENHA VARCHAR2(20) NOT NULL
);

CREATE SEQUENCE seq_usuario
    START WITH 1
    INCREMENT BY 1
    NOCACHE;