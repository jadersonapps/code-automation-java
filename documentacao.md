
# 🚀 Documentação

Este projeto é uma automação de testes utilizando **Java**, **JUnit 4**, **Cucumber** e o framework de relatórios **Extent Report**. A arquitetura está organizada em pacotes e camadas para garantir modularidade, manutenibilidade e clareza.

---

## 📂 Estrutura do Projeto

O pacote principal do projeto é:

```
br.com.amazonecommerce
```

A estrutura detalhada fica assim:

```
br.com.amazonecommerce/
├── config/                ⚙️ Configurações e gerenciamento dos testes
│   ├── DriverFactory.java    🚗 Gerencia o status do driver do navegador
│   ├── Hooks.java            🔄 Gerencia o ciclo de vida dos testes
│   ├── Listener.java         🎧 Captura eventos do Cucumber
│   ├── Logger.java           📝 Captura logs dos steps
│   └── Report.java           📊 Gerencia a instância do relatório HTML
│
├── page/                  🖥️ Gerenciamento dos elementos e funções das páginas
│   └── [Classes de página]    📍 Métodos e locators dos elementos UI
│
├── step/                  👣 Gerenciamento dos passos (steps) do Cucumber
│   └── [Classes de steps]     📋 Implementação dos passos de testes
│
├── util/                  🔧 Funções utilitárias para suporte geral no projeto
│   └── [Classes utilitárias]
│
├── runner/                ▶️ Execução dos testes
│   └── RunAutomation.java     ⚡ Classe runner que inicia os testes Cucumber + JUnit
│
└── resource/
    └── config.properties      🗝️ Arquivo de configuração com dados sensíveis e parâmetros

src/
├── java/
│   └── br/com/amazonecommerce/ (estrutura acima)
│
└── test/
    └── resources/
        └── features/          📄 Arquivos `.feature` do Cucumber (cenários de teste escritos em linguagem natural)

```

---

## 📌 Camadas e Responsabilidades

### ⚙️ Config

- **DriverFactory**: gerencia a criação, status e encerramento do driver do navegador.
- **Hooks**: controla a inicialização e finalização dos testes.
- **Listener**: escuta os eventos do Cucumber para customizações.
- **Logger**: registra logs de execução dos steps.
- **Report**: gerencia a geração do relatório HTML com Extent Report.

### 🖥️ Page

- Gerencia locators e funções para interação com a interface das páginas da aplicação.

### 👣 Step

- Contém as implementações dos passos usados nos testes Cucumber.

### 🔧 Util

- Funções auxiliares reutilizáveis para diversas finalidades no projeto.

### ▶️ Runner

- Classe que inicia a execução dos testes (RunAutomation).

### 🗝️ Config Properties

- Contém configurações, URLs, credenciais e outras variáveis sensíveis.

### 📄 Features

- Cenários escritos em linguagem natural no formato `.feature` dentro da pasta `features`.

---

## 📋 Padrões adotados

- Pastas iniciam com letra **minúscula**.
- Arquivos iniciam com letra **maiúscula**.
- Variáveis usam **CamelCase**.
- Código em **inglês** (classes, métodos, variáveis).
- Steps e features escritos em **Português (pt-BR)**.
- Dados para testes são informados diretamente nos arquivos `.feature`.
- Dados sensíveis e configurações ficam no `config.properties`.
- Classes e métodos são documentados.

---

## 🏷️ Convenções para Features

- Cada cenário e feature deve conter as seguintes **tags** para organização:

```
@TC000           → ID do cenário (ex: @TC001)
@nomeFuncionalidade → Nome da funcionalidade (ex: @autocomplete)
@estrategia      → Estratégia do teste (ex: @regressivo)
```

---

## 📂 Logs e Relatórios de Execução

- Logs são armazenados na pasta:

```
logs/
```

- Para cada execução, cria-se uma pasta com timestamp:

```
dd-MM-yyyy HHmmss/
```

- Dentro dela:

    - 📸 Pasta `screenshot/` com imagens da execução.
    - 📑 Arquivo `report.html` com o relatório completo.

### 📊 Relatório HTML

- Menu lateral esquerdo com lista dos cenários executados.
- Ao clicar em um cenário:
    - Detalhes e resumo dos steps com status (✔️ passou, ❌ falhou, ⏭️ pulado).
- Menu analítico para análise detalhada da execução.

---

## 📬 Contato

Para dúvidas ou contribuições, entre em contato com o time de automação.

---

✨ Este projeto segue boas práticas para garantir código claro, reutilizável e fácil de manter.
