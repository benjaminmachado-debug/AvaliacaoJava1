# Sistema de Avaliação

Aplicação desktop em Java com interface Swing para registrar alunos, calcular médias e verificar a situação acadêmica com base em notas e frequência.

## Funcionalidades

- validação de notas entre 0 e 10;
- validação de frequência entre 0 e 100;
- cálculo da média de duas notas;
- verificação de aprovação, recuperação ou reprovação;
- controle de alunos em uma estrutura de lista;
- interface gráfica para entrada de dados.

## Tecnologias

- Java 17
- Maven
- Swing
- JUnit 5

## Regras de negócio

A lógica principal está na classe `Avaliacao`:

- notas válidas: entre 0 e 10;
- frequência válida: entre 0 e 100;
- média calculada: `(nota1 + nota2) / 2`;
- frequência mínima exigida: 75%;
- situação:
  - se a frequência for menor que 75% → `REPROVADO POR FREQUÊNCIA`;
  - se a média for maior ou igual a 7 → `APROVADO`;
  - se a média estiver entre 5 e 6.9 → `RECUPERAÇÃO`;
  - se a média for menor que 5 → `REPROVADO`.

A classe `SistemaAvaliacao` organiza os alunos em uma lista e expõe operações básicas de cadastro e consulta.

## Estrutura do projeto

```text
SistemaAvaliacao/
├── pom.xml
├── mvnw
├── mvnw.cmd
├── readme.md
├── .gitignore
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── main/
│   │       │   └── Main.java
│   │       ├── model/
│   │       │   ├── Aluno.java
│   │       │   └── Avaliacao.java
│   │       ├── service/
│   │       │   └── SistemaAvaliacao.java
│   │       └── view/
│   │           └── TelaAvaliacao.java
│   └── test/
│       └── java/
└── target/
```

## Descrição das classes

### `model.Aluno`
Representa o aluno com os atributos:

- nome;
- nota1;
- nota2;
- frequencia.

### `model.Avaliacao`
Responsável pela lógica da avaliação:

- validação de notas e frequência;
- cálculo da média;
- verificação da situação final.

### `service.SistemaAvaliacao`
Responsável por armazenar os alunos em uma lista e gerenciar operações como:

- adicionar aluno;
- listar alunos;
- contar a quantidade de alunos.

### `view.TelaAvaliacao`
Interface gráfica Swing que coleta os dados e exibe o resultado da avaliação.

### `main.Main`
Ponto de entrada da aplicação, responsável por abrir a tela principal.

## Como executar

### Pré-requisitos

- JDK 17 ou superior;
- Maven instalado ou uso do wrapper do projeto.

### 1. Clonar o repositório

```bash
git clone <url-do-repositorio>
cd SistemaAvaliacao
```

### 2. Compilar o projeto

Windows:

```bash
mvnw.cmd clean compile
```

Linux/macOS:

```bash
./mvnw clean compile
```

### 3. Executar a aplicação

```bash
java -cp target/classes main.Main
```

A janela principal da aplicação será aberta para a inserção dos dados.

## Autor

Ester
