*** Settings ***
Documentation    Testes de autenticação e endpoints com token
Resource         ../resources/common.resource
Suite Setup      Setup Test Environment
Suite Teardown   Desconectar a api

*** Test Cases ***
Cenário 01: Realizar login com credenciais válidas
    [Documentation]    Esse teste realiza a autenticação
    [Tags]    @auth    regressivo
    Dado que o usuário possui credenciais válidas
    Quando ele realiza o login
    Então um token JWT é retornado com sucesso

Cenário 02: Acessar endpoint de monitoramento de agua
    [Documentation]    Esse teste realiza o acesso ao endpoint de monitoramento de agua
    [Tags]    @monitoramento-agua    regressivo
    Dado que o usuário está autenticado
    Quando ele acessa o endpoint de dados do usuário
    Então a resposta deve conter status 200

Cenário 03: Acessar endpoint de monitoramento de ar
    [Documentation]    Esse teste realiza o acesso ao endpoint de monitoramento de ar
    [Tags]    @monitoramento-ar    regressivo
    Dado que o usuário está autenticado
    Quando ele acessa o endpoint de dados financeiros
    Então a resposta deve conter status 200

Cenário 04: Acessar endpoint de historico da agua
    [Documentation]    Esse teste realiza o acesso ao endpoint de historico da agua
    [Tags]    @historico-agua    regressivo
    Dado que o usuário está autenticado
    Quando ele acessa o endpoint de histórico
    Então a resposta deve conter status 200