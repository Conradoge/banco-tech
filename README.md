# BancoTech — Versão do Professor

Projeto didático de sistema bancário em console, organizado por pacotes e camadas.

## Requisitos
- JDK 17 ou superior
- Maven (opcional para compilar pela linha de comando)

## Organização
- `exception`: exceções de negócio verificadas
- `model`: Cliente e hierarquia de Conta
- `operation`: histórico de operações
- `repository`: armazenamento em memória
- `service`: regras de negócio
- `view`: menu e leitura do console
- `Main.java`: ponto de entrada

## Executar com Maven
Na raiz do projeto:
```bash
mvn clean package
java -cp target/classes br.com.bancotech.Main
```
O projeto usa armazenamento em memória; os dados são perdidos ao encerrar, conforme o escopo didático.

## Observação didática
O PIX é uma simulação acadêmica, sem conexão com instituições financeiras. As transferências validam contas, situação, valor e saldo antes de alterar os saldos.
