# Doação de Sangue API 🏥

## Descrição

A Doação de Sangue API é uma plataforma que conecta doadores de sangue a hospitais que necessitam de doações. Além disso, ela permite o agendamento de doações de sangue, facilitando a doação de sangue de forma organizada e eficiente.

## Recursos Principais

### Cadastro de Hospitais

- Endpoint para adicionar informações sobre hospitais, incluindo nome, endereço e informações de contato.

### Cadastro de Doadores

- Endpoint para registrar informações de doadores, incluindo nome e tipo sanguíneo.

### Agendamento de Doações

- Endpoint que permite que os doadores agendem suas doações de sangue, escolhendo a data e horário mais convenientes.

## Tecnologias Utilizadas

- **Spring Boot**: Utilizado para desenvolver a aplicação, facilitando a criação de APIs RESTful.
- **PostgreSQL**: Banco de dados principal para armazenar informações de hospitais, doadores e agendamentos.
- **H2**: Banco de dados em memória utilizado durante o desenvolvimento e testes.
- **Swagger**: Documentação da API, tornando-a mais acessível e fácil de usar.
- **SOLID**: Princípios de design de software são aplicados para manter o código limpo, modular e de fácil manutenção.
- **Documentação do Projeto**: Um documento detalhado descrevendo a arquitetura, os endpoints da API e as decisões de design está disponível para consulta.
- **Boas Práticas de Desenvolvimento**: A aplicação segue as melhores práticas de desenvolvimento de software, incluindo padrões de nomenclatura, tratamento de erros e medidas de segurança.

## Como Iniciar

Para iniciar a aplicação, siga os seguintes passos:

1. Clone este repositório.
2. Configure as propriedades do banco de dados no arquivo `application.properties`.
3. Execute a classe principal `DoacaoDeSangueApiApplication.java`.

A aplicação estará disponível em [http://localhost:8080](http://localhost:8080).

## Documentação da API

A documentação completa da API está disponível no Swagger. Você pode acessá-la em [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) após iniciar a aplicação.

## Contribuindo

Sinta-se à vontade para contribuir com melhorias, correções de bugs ou novos recursos. Abra uma [issue](link-para-issues) para discutir sua ideia ou envie um [pull request](link-para-pull-requests) com suas alterações.

## Licença

Este projeto está licenciado sob a Licença MIT - consulte o arquivo [LICENSE](LICENSE) para obter detalhes.
