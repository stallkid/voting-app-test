# Desafio Técnico - Backend Votos

## Introdução
O projeto foi documentado pelo [Swagger](https://coop-voting-app.herokuapp.com/swagger-ui.html) para facilitar os testes das APIs, foi feito o deployment do Spring boot pela plataforma do Heroku, utilizando banco de dados MongoDB no cluster do [Atlas](https://www.mongodb.com/atlas/database)

## Regras de negócio do Projeto
O projeto foi feito de maneira em que a pauta pode ser criada sem necessáriamente ser aberta no mesmo momento, após a criação da pauta é preciso chamar a API responsável por abrir a mesma, passando data e hora no seguinte padrão 'yyyy-mm-dd hh:mm:ss', caso o valor seja nulo será acrescido 1 minuto na hora atual.

Após a criação e abertura da Pauta, será possível realizar votações com CPFs únicos passando pelo serviço de validação de CPF fornecido no documento do teste, o mesmo deve ser passado sem formatação no padrão '###########', enquanto isso tem um agendador de tarefa rodando em segundo plano para verificar se há pautas fechadas, caso haja será alterado seu status para true e enviado os resultados para a fila do CloudAMQP.

É possível consultar a votação a qualquer momento através da API de consulta.

## Stack de tecnologias
Para o desenvolvimento foi utilizado as seguintes tecnologias
- [Spring boot](https://spring.io/projects/spring-boot) 2.4.3
- [MongoDB](https://www.mongodb.com/) 4.4
- [CloudAMQP](https://www.cloudamqp.com/)

### Links para a aplicação
[https://coop-voting-app.herokuapp.com/swagger-ui.html](https://coop-voting-app.herokuapp.com/swagger-ui.html)
