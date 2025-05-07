*** Settings ***
Documentation  Arquivo responsavel pelo compartilhamentos de resources
Library        RequestsLibrary
Library        String
Library        Collections
Resource       config.resource
Resource       ../resources/requests/auth.resource
Resource       ../resources/requests/endpoints.resource