# BiblioTech

Sistema de empréstimo de livros para a biblioteca do campus.

## 1. O projeto

Hoje, a Dona Marli controla os empréstimos na ponta do lápis, o que gera atrasos e perda de controle do acervo. Do outro lado, alunos como o Pedro precisam ir até o balcão só para saber se um livro está na estante. O BiblioTech resolve isso automatizando o cadastro de livros, leitores e empréstimos, permitindo que qualquer pessoa consulte a disponibilidade do acervo em segundos, direto de onde estiver.

## 2. Histórias de usuário

| # | História de usuário |
|---|---|
| HU01 | Como leitor, quero consultar a disponibilidade de um livro, para saber se posso pegá-lo emprestado sem ir até o balcão. |
| HU02 | Como leitor, quero devolver um livro, para não ficar com pendência na biblioteca. |
| HU03 | Como bibliotecária, quero registrar um empréstimo, para saber quem está com cada exemplar. |
| HU04 | Como bibliotecária, quero cadastrar um livro novo, para que ele possa ser encontrado no sistema. |
| HU05 | Como bibliotecária, quero ver os empréstimos atrasados, para cobrar a devolução. |
| HU06 | Como leitor, quero reservar um livro que está emprestado, para garantir que serei o próximo a retirá-lo assim que voltar. |

## 3. Requisitos

### Requisitos funcionais

| # | Requisito funcional | Veio da |
|---|---|---|
| RF01 | O sistema deve permitir que a bibliotecária cadastre um livro no acervo. | HU04 |
| RF02 | O sistema deve permitir que a bibliotecária cadastre um leitor. | regra de acesso: só quem tem cadastro leva livro |
| RF03 | O sistema deve permitir que o leitor consulte a disponibilidade de um livro. | HU01 |
| RF04 | O sistema deve permitir que a bibliotecária registre a devolução de um livro. | HU02 |
| RF05 | O sistema deve permitir que a bibliotecária registre o empréstimo de um livro. | HU03 |
| RF06 | O sistema deve permitir que o leitor reserve um livro que esteja emprestado. | HU06 |

### Requisitos não funcionais

| # | Requisito não funcional |
|---|---|
| RNF01 | A consulta de disponibilidade deve responder em menos de 3 segundos. |
| RNF02 | Somente usuários identificados como bibliotecários podem alterar o acervo. |

## 4. Diagramas (feitos em APS)

### Casos de uso

![Diagrama de casos de uso do BiblioTech](docs/casos-de-uso.svg)

### Classes

![Diagrama de classes do BiblioTech](docs/classes.svg)