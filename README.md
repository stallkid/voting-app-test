# Desafio Técnico - Backend Votos

## Introdução
O projeto foi documentado pelo [Swagger](https://coop-voting-app.herokuapp.com/swagger-ui.html) para facilitar os testes das APIs, foi feito o deployment do Spring boot pela plataforma do Heroku, utilizando banco de dados MongoDB no cluster do [Atlas](https://www.mongodb.com/atlas/database)

## Regras de negócio do Projeto
O projeto foi feito de maneira em que a pauta pode ser criada sem necessáriamente ser aberta no mesmo momento, após a criação da pauta é preciso chamar a API responsável por abrir a mesma, passando data e hora no seguinte padrão 'yyyy-mm-dd hh:mm:ss', caso o valor seja nulo será acrescido 1 minuto na hora atual.

Após a criação e abertura da Pauta, será possível realizar votações com CPFs únicos passando pelo serviço de validação de CPF fornecido no documento do teste, o mesmo deve ser passado sem formatação no padrão '###########', enquanto isso tem um agendador de tarefa rodando em segundo plano para verificar se há pautas fechadas, caso haja será alterado seu status para true e enviado os resultados para a fila do CloudAMQP.

É possível consultar a votação a qualquer momento através da API de consulta.

## Escolhas tomadas durante o desenvolvimento
A aplicação foi desenvolvida tendo em mente agilidade e performance, com Spring boot foi possível ter várias ferramentas prontas para o uso.
No eco sistema do Spring a ferramenta mais utilizada foi o Spring Data, pois ele abstrai todas as funcionalidades do JPA Hibernate.
Para o banco de dados foi escolhido o MongoDB, por ser NoSQL não foi preciso um tempo maior para o desenho dos esquemas do banco de dados, assim ganhando bastante tempo em apenas desenhando o JSON pelo qual o Frontend utilizaria, alem da grande performance por não precisar fazer buscas e várias indexações durante o uso.
A documentação foi feita com Swagger por ser uma lib bem robusta e ter uma ótima integração com o Springboot.
A mensageria escolhida foi o RabbitMQ hospedado num servidor AWS por meio da plataforma CloudAMQP pelo fato de ser gratuito e não precisar utilizar o cartão de crédito.
Para o versionamento da aplicação seria utilizado o git flow caso ouvesse um time maior, para ter um melhor controle nos merges, mas para focar na agilidade foi feito apenas commits únicos por funcionalidades aplicadas diretamente na branch main.
Visando uma maior qualidade de código, foi também criado testes unitários nos serviços para garantir que a aplicação mantenha suas regras de negócio funcionando de maneira esperada.

## Stack de tecnologias
Para o desenvolvimento foi utilizado as seguintes tecnologias
- [Spring boot](https://spring.io/projects/spring-boot) 2.4.3
- [MongoDB](https://www.mongodb.com/) 4.4
- [CloudAMQP](https://www.cloudamqp.com/)
- [Mockito](https://site.mockito.org/)

### Link para a aplicação
[https://coop-voting-app.herokuapp.com/swagger-ui.html](https://coop-voting-app.herokuapp.com/swagger-ui.html)
