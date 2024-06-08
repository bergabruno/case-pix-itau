### Projeto de Cadastro de Chaves PIX

## Descrição do Projeto
Este projeto é uma implementação de um módulo de cadastro de chaves PIX utilizando Java com MongoDB. O objetivo é viabilizar a inclusão, alteração, deleção e consulta de chaves PIX, vinculando-as à agência e conta do correntista.

## Tecnologias Utilizadas
- **Linguagem**: Java
- **Banco de Dados**: MongoDB
- **Arquitetura**: MVC (Model-View-Controller)
- **API**: RESTful
- **Validações**: Classe Validator
- **Gerenciamento de Dependências**: Maven/Gradle

## Funcionalidades Implementadas
### Inclusão de Chaves PIX
- Permite a inclusão de diferentes tipos de chaves: celular, e-mail, CPF, CNPJ e chave aleatória.
- Limite de 5 chaves por conta para pessoas físicas e 20 chaves para pessoas jurídicas.
- Geração de um código de registro único (UUID) para cada chave registrada.
- Validação de duplicidade e formato das chaves conforme regras específicas.

### Alteração de Chaves PIX
- Permite a alteração dos dados associados a uma chave registrada, exceto o próprio valor da chave.
- Validação de campos obrigatórios e regras de negócio.

### Deleção de Chaves PIX
- Inativa uma chave registrada, impedindo sua alteração ou consulta futura.
- Registra a data e hora da inativação.

### Consulta de Chaves PIX
- Disponibiliza consulta por ID, tipo de chave, agência e conta, nome do correntista, data de inclusão e data de inativação.
- Implementação de filtros combinados para consultas mais precisas.


### Pacotes
- **controller**: Contém as classes que expõem os endpoints REST.
- **model**: Contém as classes de modelo que representam as entidades do sistema.
- **repository**: Contém as interfaces de repositório para interação com o MongoDB.
- **service**: Contém as classes de serviço que implementam a lógica de negócio.
- **validator**: Contém as classes de validação que garantem a integridade dos dados.

## Como Executar o Projeto
1. Clone o repositório: `git clone https://github.com/seu-usuario/seu-repositorio.git`
2. Navegue até o diretório do projeto: `cd seu-repositorio`
3. Compile o projeto: `mvn clean install` ou `./gradlew build`
4. Execute a aplicação: `mvn spring-boot:run` ou `./gradlew bootRun`
5. Acesse a API através do Postman ou qualquer cliente HTTP: `http://localhost:8080/maintenance_key_pix/v1/pix`


## Testes
- Os testes unitários estão localizados no diretório `src/test/java/br/com/itau/pix`.
- A cobertura de testes é de 90%.

## Contato
- Nome: Bruno Bergamasco
- E-mail: brunoberga77@gmail.com
