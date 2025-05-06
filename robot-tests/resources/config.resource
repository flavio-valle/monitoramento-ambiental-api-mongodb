*** Settings ***
Documentation     Configuração de ambiente e variáveis globais
Library           OperatingSystem
Resource          common.resource
Variables         config/config.yaml

*** Keywords ***
Set Execution Environment
    ${execution_environment}=    Get Environment Variable    EXECUTION_ENVIRONMENT    default=${CONFIG}[DEFAULT][EXECUTION_ENVIRONMENT]
    Set Global Variable    ${EXECUTION_ENVIRONMENT}    ${execution_environment}

Set Base URL
    ${base_url}=    Get Environment Variable    BASE_URL    default=${CONFIG}[${EXECUTION_ENVIRONMENT}][BASE_URL]
    Set Global Variable    ${BASE_URL}    ${base_url}

Setup Test Environment
    Set Execution Environment
    Set Base URL
    Criar Sessão Autenticada

Desconectar a api
    Delete All Sessions

*** Variables ***
${LOGIN_ENDPOINT}     /auth/login
${ENDPOINT_1}         /monitoramento-agua
${ENDPOINT_2}         /monitoramento-ar
${ENDPOINT_3}         /historico-agua
${USUARIO}            kaique@kaique.com
${SENHA}              kaique123