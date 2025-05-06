*** Settings ***
Library    RequestsLibrary
Library    Collections
Resource   ../common.resource

*** Keywords ***
Quando ele acessa o endpoint de dados do usuário
    ${headers}=    Create Dictionary    Authorization=Bearer ${TOKEN}
    ${resp}=    GET On Session    minha_api    ${ENDPOINT_1}    headers=${headers}
    Set Test Variable    ${RESPOSTA}    ${resp}

Quando ele acessa o endpoint de dados financeiros
    ${headers}=    Create Dictionary    Authorization=Bearer ${TOKEN}
    ${resp}=    GET On Session    minha_api    ${ENDPOINT_2}    headers=${headers}
    Set Test Variable    ${RESPOSTA}    ${resp}

Quando ele acessa o endpoint de histórico
    ${headers}=    Create Dictionary    Authorization=Bearer ${TOKEN}
    ${resp}=    GET On Session    minha_api    ${ENDPOINT_3}    headers=${headers}
    Set Test Variable    ${RESPOSTA}    ${resp}

Então a resposta deve conter status 200
    Should Be Equal As Integers    ${RESPOSTA.status_code}    200