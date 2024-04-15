# API de Gestão de Estoque de Bebidas

## Descrição

Esta é uma API RESTful para gerir dados de armazenamento e estoque de um depósito de bebidas. Atualmente, o estoque é responsável por armazenar dois tipos de bebidas (alcoólicas e não alcoólicas), mas está preparado para suportar a adição de novos tipos no futuro. A API permite o cadastro, consulta e gerenciamento de bebidas e seções de armazenamento, bem como o histórico de entradas e saídas de estoque.

## Motivação

A motivação para criar esta API veio da necessidade de um sistema robusto e flexível para gerenciar o estoque de bebidas em um depósito. Através desta aplicação, buscamos facilitar a gestão de estoque, garantindo que todas as regras de negócio sejam respeitadas, como a separação de bebidas alcoólicas e não alcoólicas e a manutenção de um histórico detalhado de todas as movimentações.

## Funcionalidades

- Cadastro e consulta das bebidas armazenadas em cada seção.
- Consulta do volume total no estoque por cada tipo de bebida.
- Consulta dos locais disponíveis de armazenamento para um determinado volume de bebida.
- Consulta das seções disponíveis para venda de determinado tipo de bebida.
- Cadastro de histórico de entrada e saída de bebidas em caso de venda e recebimento.
- Consulta do histórico de entradas e saídas por tipo de bebida e seção, com ordenação por data e seção.

## Regras de Negócio

- Uma seção não pode ter dois ou mais tipos diferentes de bebidas.
- Não há entrada ou saída de estoque sem respectivo registro no histórico.
- Registro deve conter horário, tipo, volume, seção e responsável pela entrada.
- Uma seção não pode receber bebidas não alcoólicas se recebeu alcoólicas no mesmo dia.
- O endpoint de consulta de histórico de entrada e saída de estoque deve retornar os resultados ordenados por data e seção, podendo alterar a ordenação via parâmetros.
- Para situações de erro, é necessário que a resposta da requisição seja coerente em exibir uma mensagem condizente com o erro.

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- Lombok
- MySQL
- JPA/Hibernate
- Flyway
- Docker
- Swagger

## Estrutura do Projeto

### Camadas

- **Controller:** Camada responsável por expor os endpoints da API.
- **Service:** Camada responsável por implementar as regras de negócio.
- **Repository:** Camada responsável por interagir com o banco de dados.
- **Model:** Classes que representam as entidades e os dados manipulados pela aplicação.

### Tratamento de Exceções

Foi implementado um `ControllerAdvice` para tratamento centralizado de erros, garantindo respostas consistentes e informativas.

### Exceções Personalizadas

Foram criadas exceções personalizadas para identificar e categorizar erros específicos, como `SecaoNotFoundException`, `BebidaNotFoundException`, `CapacidadeExcedidaException`, `VolumeInsuficienteException`, e `SecaoRestricaoException`.

## Perguntas Respondidas

### 1. O que achou do teste? Grau de dificuldade, desafios encontrados, etc.

Achei o teste desafiador e interessante. Ele cobre uma ampla gama de habilidades, desde a modelagem de dados até a implementação de regras de negócio complexas e o tratamento de exceções. Os principais desafios encontrados foram garantir que todas as regras de negócio fossem respeitadas e implementar um sistema de histórico detalhado e preciso.

### 2. Alteraria algo no teste para analisar alguma outra habilidade?

O teste já é bastante abrangente, mas poderia incluir a necessidade de integração com algum serviço externo ou a implementação de um sistema de notificações para eventos importantes, como baixa no estoque ou recebimento de novas bebidas, para avaliar habilidades de integração e comunicação assíncrona.

### 3. Entende que testes unitários são necessários para garantia da qualidade do código entregue?

Sim, testes unitários são fundamentais para garantir a qualidade do código e a correção das funcionalidades implementadas. A seguir, apresento alguns exemplos de testes unitários.
