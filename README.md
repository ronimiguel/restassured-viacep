# Automação de Testes ViaCEP com RestAssured


## Sobre o Teste

O objetivo deste teste automatizado é  realizar testes automatizados na API ViaCEP e cobrir cenários para CEP válido e CEP inválido.

### Ferramentas Utilizadas

Os testes foram desenvolvidos utilizando as seguintes ferramentas:

- RestAssured: Para fazer requisições HTTP e validar respostas de APIs.
- Junit 5: Para gerenciar os testes.
- Extent Report: Para gerar relatórios de execução dos testes.

### Executando os Testes

Antes de começar, certifique-se de ter o seguinte configurado em sua máquina:

- Java 17 instalado.

Para executar os testes, siga os passos abaixo:

1. Abra o terminal ou prompt de comando.
2. Navegue até a pasta raiz do projeto.

#### Opção 1: Usando o Gradle Wrapper

No Windows:
```bash
.\gradlew.bat clean test

```

No Linux e sistemas similares:
```bash
./gradlew clean test
```

#### Opção 2: Usando sua IDE

1. Clone este repositório para a sua máquina.
2. Abra sua IDE favorita (Eclipse, IntelliJ, etc.).
3. Importe o projeto.
4. Localize e execute a classe `APITest.java` como um projeto JUnit.

Ao final da execução, um arquivo chamado `RelatorioDeExecucao.html` será gerado na raiz do projeto. Esse arquivo conterá informações detalhadas sobre a execução dos testes.


