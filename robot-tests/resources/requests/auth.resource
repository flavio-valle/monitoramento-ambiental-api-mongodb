*** Settings ***
Library    RequestsLibrary
Library    Collections
Resource   ../common.resource

*** Keywords ***
Criar Sessão Autenticada
    Create Session    minha_api    ${BASE_URL}

Obter Token De Acesso
    ${body}=    Create Dictionary    email=${USUARIO}    senha=${SENHA}
    ${resp}=    POST On Session    minha_api    ${LOGIN_ENDPOINT}    json=${body}
    Should Be Equal As Integers    ${resp.status_code}    200
    ${json}=    Evaluate    json.loads("""${resp.content}""")    modules=json
    Set Suite Variable    ${TOKEN}    ${json['token']}

Dado que o usuário possui credenciais válidas
    No Operation

Quando ele realiza o login
    Obter Token De Acesso

Então um token JWT é retornado com sucesso
    Should Not Be Empty    ${TOKEN}

Dado que o usuário está autenticado
    Dado que o usuário possui credenciais válidas
    Quando ele realiza o login
    Então um token JWT é retornado com sucesso

