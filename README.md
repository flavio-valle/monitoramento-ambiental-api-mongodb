# Monitoramento Ambiental API — MongoDB

API REST **Spring Boot 3 + MongoDB** para monitorar a qualidade **da água** e **do ar**, manter histórico e disparar alertas.
Pronta para rodar **localmente**, em **Docker** ou em **Render.com** (via GitHub Actions).

---

## 📑 Índice

1. [Requisitos](#requisitos)
2. [Clonagem do repositório](#clonagem)
3. [Configuração de variáveis de ambiente](#variáveis)
4. [Executando em modo *dev* (Maven)](#exec-maven)
5. [Executando via Docker](#exec-docker)
6. [Rodando testes automatizados](#testes)

   * [6.1 Testes Java (JUnit)](#testes-java)
   * [6.2 Testes com Robot Framework](#testes-robot)
7. [Coleções/Índices do Mongo](#mongo)
8. [Rotas principais](#rotas)
9. [Deploy contínuo](#cicd)
10. [Troubleshooting](#troubleshooting)

---

<a name="requisitos"></a>

## 1 — Requisitos

| Ferramenta          | Versão mínima | Observação                                 |
| ------------------- | ------------- | ------------------------------------------ |
| **Java**            | 17            | Testado com Temurin 17                     |
| **Maven**           | 3.9           | Wrapper (`mvnw`) incluído                  |
| **Docker**          | 20.10         | Para execução containerizada               |
| **Docker Compose**  | 2.20          | Opcional: subir Mongo local rapidamente    |
| **Python**          | 3.8+          | Para executar testes do Robot Framework    |
| **Robot Framework** | 6.x           | Instalada via `pip install robotframework` |

---

<a name="clonagem"></a>

## 2 — Clonagem do repositório

```bash
git clone https://github.com/flavio-valle/monitoramento-ambiental-api-mongodb.git
cd monitoramento-ambiental-api-mongodb
```

---

<a name="variáveis"></a>

## 3 — Configuração de variáveis de ambiente

Crie um arquivo **`.env`** (ou exporte no shell):

```bash
# MongoDB
MONGO_URI=mongodb+srv://<USER>:<PASSWORD>@cluster.net/meu_banco?retryWrites=true&w=majority

# JWT
JWT_SECRET=algumaFraseSuperSecreta123

# Porta (opcional)
SERVER_PORT=8080
```

> **Importante:** nunca *versione* suas credenciais; elas são lidas pelo `application.properties`.

---

<a name="exec-maven"></a>

## 4 — Executando em modo *dev* (Maven)

### 4.1 Subir um Mongo local (opcional)

```bash
docker run -d --name mongo \
  -p 27017:27017 \
  -e MONGO_INITDB_DATABASE=meu_banco \
  mongo:7
export MONGO_URI=mongodb://localhost:27017/meu_banco
```

### 4.2 Build + run

```bash
./mvnw spring-boot:run          # hot‑reload
# ou
./mvnw clean package
java -jar target/monitoramento-ambiental-api-0.0.1-SNAPSHOT.jar
```

API disponível em `http://localhost:8080`.

---

<a name="exec-docker"></a>

## 5 — Executando via Docker

### 5.1 Build da imagem

```bash
docker build -t monitoramento-api:local .
```

### 5.2 Run

```bash
docker run -d -p 8080:8080 \
  -e MONGO_URI=$MONGO_URI \
  -e JWT_SECRET=$JWT_SECRET \
  --name mon-ambiental monitoramento-api:local
```

#### Healthcheck

```bash
curl http://localhost:8080/actuator/health
```

---

<a name="testes"></a>

## 6 — Rodando testes automatizados

<a name="testes-java"></a>

### 6.1 Testes Java (JUnit)

```bash
./mvnw test
```

*Inclui `JUnit 5`. Adicione testes unitários, de integração (Testcontainers) e segurança (`spring-security-test`).*

<a name="testes-robot"></a>

### 6.2 Testes com Robot Framework

Este projeto inclui uma suíte de testes de API desenvolvida em Robot Framework dentro da pasta `robot-tests`.

#### Pré-requisitos:

* Python 3.8+
* Robot Framework instalado (`pip install robotframework`)
* Dependências do projeto: `pip install -r robot-tests/requirements.txt`

#### Executando os cenários:

```bash
# Entre na pasta de testes
cd robot-tests

# Instale dependências (se ainda não instalou)
pip install -r requirements.txt

# Execute todos os cenários
env ROBOT_SYSLOG_FILE=../logs/robot.log robot tests
```

Você pode usar tags para executar cenários específicos:

* `regressivo`: Executa todos os cenários automatizados.
* `smoke`: Executa os principais cenários.

*Exemplo:* `robot --include smoke tests`

---

<a name="mongo"></a>

## 7 — Coleções e índices recomendados

| Coleção              | Índice sugerido                   | Uso                 |                             |
| -------------------- | --------------------------------- | ------------------- | --------------------------- |
| `monitoramento_agua` | `{ dataHora: 1, localizacao: 1 }` | Range + localização |                             |
| `monitoramento_ar`   | `{ dataHora: 1, localizacao: 1 }` | Idem                |                             |
| \`historico\_agua    | ar\`                              | `{ origemId: 1 }`   | Relacionar medição original |
| `usuario`            | `{ email: 1 }` (*unique*)         | Login exclusivo     |                             |

---

<a name="rotas"></a>

## 8 — Rotas principais

| Método | Endpoint                  | Descrição              | Protegido |
| ------ | ------------------------- | ---------------------- | --------- |
| POST   | `/auth/register`          | Cria usuário           | ❌         |
| POST   | `/auth/login`             | Gera **JWT**           | ❌         |
| GET    | `/monitoramento/agua`     | Lista medições de água | ✅         |
| GET    | `/monitoramento/ar`       | Lista medições de ar   | ✅         |
| POST   | `/monitoramento/agua\|ar` | Cria nova medição      | ✅         |
| GET    | `/historico/agua\|ar`     | Lista históricos       | ✅         |

Inclua o token no header:

```
Authorization: Bearer <JWT>
```

---

<a name="cicd"></a>

## 9 — Deploy contínuo (GitHub Actions ➜ Docker Hub ➜ Render)

1. Push em **`staging`** ou **`master`** → dispara workflow `ci-and-push.yml`.
2. O workflow:

   1. *Builda* a imagem Docker.
   2. Faz *login* no Docker Hub.
   3. *Pusha* `flavinn/monitoramento-ambiental-api-mongodb:staging|master`.
3. Render.com está configurado para acompanhar a tag e redeployar.

---

<a name="troubleshooting"></a>

## 10 — Troubleshooting

| Sintoma                                    | Possível causa / solução                                     |
| ------------------------------------------ | ------------------------------------------------------------ |
| `No such image` no GitHub Actions          | Use `${{ github.ref_name }}` em vez de SHA no push de imagem |
| `404 /actuator/health` dentro do container | Adicione `spring-boot-starter-actuator` no `pom.xml`         |
| `401 Unauthorized` mesmo com token         | Verifique Header `Authorization` e expiração do token        |
| `Timed out` ao conectar no Mongo           | Confirme `MONGO_URI`, whitelist de IP ou porta no compose    |

---

> Feito com ♥ por [@flavio-valle](https://github.com/flavio-valle) — contribuições são bem‑vindas!
