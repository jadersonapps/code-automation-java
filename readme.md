![Regressivo - Continuous Testing](https://github.com/jadersonapps/code-automation-java/actions/workflows/QaOps.yml/badge.svg?branch=main)

# Projeto de Testes Automatizados com Java e Cucumber

Este projeto utiliza **Java com Cucumber** para automação de testes. Ele abrange a automação de fluxos críticos da aplicação e visa garantir a estabilidade e qualidade do sistema ao longo do tempo.

## Estrutura do Projeto

A estrutura completa do projeto, incluindo a organização dos diretórios, scripts, configurações e documentação sobre a execução dos testes, pode ser encontrada no nosso espaço de documentação interna. Visite a página do Confluence no link abaixo:

🔗 [Documentação detalhada do projeto](https://)


---

## Preparação de Ambiente

Certifique-se de que todos os requisitos do projeto estão devidamente instalados e configurados conforme descrito na documentação do Confluence. Aqui estão os principais pré-requisitos:

---

### ✅ Instalar o Git 🐙

- Baixe e instale o Git no [site oficial](https://git-scm.com/download/win).
- Após a instalação, verifique:

```bash
git --version
```

---

### ✅ Instalar o Java ☕ (JDK 17 ou superior)

- Baixe o JDK no [site oficial da Oracle](https://www.oracle.com/java/technologies/javase-downloads.html) ou [OpenJDK](https://jdk.java.net/).
- Durante a instalação, **marque a opção de adicionar o Java ao PATH**.

Verifique a instalação:

```bash
java -version
```

---

### ✅ Instalar o Apache Maven 📦

1. Baixe o Maven no [site oficial](https://maven.apache.org/download.cgi).
2. Extraia o conteúdo em um diretório, por exemplo: `C:\Ferramentas\apache-maven-3.9.0`.

#### ⚙️ Configurar as variáveis de ambiente:

1. Abra o menu **Iniciar** e digite **"variáveis de ambiente"**.
2. Clique em **"Editar variáveis de ambiente do sistema"**.
3. Em **Variáveis do sistema**, clique em **Novo** e adicione:

    - **Nome da variável:** `MAVEN_HOME`
    - **Valor da variável:** `C:\Ferramentas\apache-maven-3.9.0`

4. Ainda em **Variáveis do sistema**, selecione a variável `Path`, clique em **Editar** e adicione:

   ```
   %MAVEN_HOME%\bin
   ```

5. Clique em OK em todas as janelas.

#### Verifique a instalação:

```bash
mvn -v
```

---

### ✅ Instalar IntelliJ IDEA 💡

- Baixe no [site oficial](https://www.jetbrains.com/idea/download/).
- Recomendamos a versão **Community** (gratuita).

#### 🔌 Instalar o plugin Maven no IntelliJ

1. Abra o IntelliJ.
2. Vá em **File > Settings > Plugins**.
3. Pesquise por `Maven` e verifique se o plugin **"Maven Integration"** está **instalado e habilitado**.
4. Se não estiver, clique em **Install** e reinicie o IntelliJ.

---

## Clonar o Projeto 🧬

```bash
c:
mkdir automacao

git clone https://github.com/jadersonapps/code-automation-java.git

cd code-automation-java
```

---

## Importar no IntelliJ IDEA 💻

1. Abra o IntelliJ e clique em **Open**.
2. Selecione a pasta do projeto clonado.
3. O IntelliJ reconhecerá o projeto como **Maven Project**.
4. Quando solicitado, clique em **Import Maven Projects**.
5. Caso não reconheça automaticamente:
    - Vá em **View > Tool Windows > Maven**.
    - Clique em **+ Add Maven Project** e selecione o arquivo `pom.xml`.

---

## Executando os Testes Localmente

1. Abra o terminal.
2. Navegue até a pasta raiz do projeto:

```bash
cd c:\automacao\code-automation-java
```

3. Execute os testes com Maven:

```bash
mvn test
```

4. Os relatórios podem ser encontrados em:

```
c:\automacao\code-automation-java\logs
```