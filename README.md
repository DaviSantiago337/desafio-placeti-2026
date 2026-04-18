Desafio Técnico - Gerenciamento de Cidades e Comércios
Este projeto foi desenvolvido como parte do processo seletivo para a Place TI.

🛠️ Tecnologias Utilizadas
Backend
Java 21.
Spring Boot 3.
H2 Database.
Spring Data JPA
JUnit & Mockito
Bean Validation

Frontend
Angular 17.
PrimeNG.
TypeScript.

🏗️ Arquitetura do Projeto
O projeto segue padrões de mercado para garantir a escalabilidade e manutenção:

Camada de Controller: Exposição dos endpoints REST.

Camada de Service: Concentração das regras de negócio e validações.

Camada de Repository: Interface de comunicação com o banco de dados.

DTOs (Data Transfer Objects): Utilizados para a transferência de dados entre o frontend e o backend, garantindo que as entidades de banco de dados não sejam expostas diretamente.

Regra de Negócio Implementada:

Relacionamento de 1 para N (Uma cidade pode possuir vários comércios, mas um comércio pertence a apenas uma cidade).

Tipos de Comércio restritos a: FARMÁCIA, PADARIA, POSTO_GASOLINA e LANCHONETE.

🚀 Como Executar o Projeto
1. Pré-requisitos
Java 21 instalado.

Node.js (versão 18 ou superior) instalado.

Maven instalado (ou usar o mvnw incluso).

2. Rodando o Backend (Spring Boot)
Navegue até a pasta backend-spring-boot
Abra o terminal (PowerShell/CMD) e execute:

./mvnw spring-boot:run
O servidor estará disponível em: http://localhost:8080

O console do banco de dados H2 pode ser acessado em: http://localhost:8080/h2-console

3. Rodando o Frontend (Angular)
Navegue até a pasta frontend-angular.
Abra um novo terminal e instale a dependência:

npm install

Inicie a aplicação:

npm start
Acesse no seu navegador: http://localhost:4200

👨‍💻 Autor
Davi Santiago
