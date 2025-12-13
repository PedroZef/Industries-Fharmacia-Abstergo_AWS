# Como Contribuir

Ficamos felizes por você querer contribuir com o projeto! Existem várias maneiras de nos ajudar, e agradecemos por cada uma delas.

## Reportando Bugs

Se você encontrar um bug, por favor, abra uma "issue" em nosso repositório no GitHub. Ao reportar um bug, inclua o máximo de detalhes possível.

## Sugerindo Melhorias

Se você tem uma ideia para uma nova funcionalidade ou uma melhoria, abra uma "issue" com a tag "enhancement".

## Submetendo Pull Requests

1. **Faça um Fork do Repositório.**
2. **Clone o seu Fork:** `git clone https://github.com/SEU_USUARIO/Industries-Fharmacia-Abstergo_AWS.git`
3. **Crie uma Nova Branch:** `git checkout -b minha-nova-feature`
4. **Faça suas Alterações.**
5. **Faça o Commit:** `git commit -am "Adiciona nova feature incrível"`
6. **Envie para o seu Fork:** `git push origin minha-nova-feature`
7. **Abra um Pull Request.**

---

## Guia do Desenvolvedor

Esta seção fornece um guia passo a passo e informações sobre a arquitetura para quem deseja desenvolver e contribuir com o projeto.

### 1. Arquitetura do Código: Foco em Manutenibilidade e Baixo Custo

A arquitetura da aplicação Java Spring Boot foi desenhada para minimizar o tempo de desenvolvimento e simplificar a manutenção, o que se traduz diretamente em redução de custos.

#### 1.1. Estrutura de Camadas (Separation of Concerns)

O projeto segue uma arquitetura de camadas bem definida:

- **`controller`**: Responsável apenas por receber requisições web e devolver respostas.
- **`domain`**: Contém as entidades de negócio (`Cliente`, `Produto`) e suas regras.
- **`repository`**: Interfaces que abstraem o acesso ao banco de dados.

**Benefício**: Essa separação clara permite que desenvolvedores encontrem e modifiquem código de forma rápida e segura.

#### 1.2. Produtividade com Spring Data JPA

As interfaces em `repository` (ex: `ClienteRepository`) utilizam o Spring Data JPA, que escreve automaticamente todo o código de acesso ao banco de dados (SQL), acelerando o desenvolvimento e reduzindo a chance de bugs.

```java
// Exemplo: ClienteRepository.java
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
```

#### 1.3. APIs Seguras com DTOs (Data Transfer Objects)

O projeto utiliza o padrão DTO para:

1. **Segurança**: Impede que dados sensíveis da entidade sejam acidentalmente expostos na API.
2. **Manutenção**: Permite modificar a estrutura interna do banco de dados sem quebrar os "contratos" da API.

#### 1.4. Tratamento de Erros Centralizado

A classe `TratadorDeErros.java` usa `@RestControllerAdvice` para centralizar o tratamento de todos os erros da aplicação, evitando a duplicação de código `try-catch`.

---

### 2. Configuração do Ambiente

#### 2.1. Pré-requisitos

- **Java Development Kit (JDK) 21** ou superior.
- **Apache Maven 3.8** ou superior.

#### 2.2. Construindo o Projeto

Para construir o projeto e baixar todas as dependências, execute o seguinte comando na raiz do projeto:

```bash
mvn clean install
```

#### 2.3. Executando a Aplicação

Após a construção, execute a aplicação com um dos comandos abaixo:

1. **Via Maven:**

    ```bash
    mvn spring-boot:run
    ```

2. **Via JAR:**

    ```bash
    java -jar target/fharmacia-0.0.1-SNAPSHOT.jar
    ```

A aplicação estará disponível em `http://localhost:8080`.

---

### 3. Utilizando a API

Você pode usar ferramentas como `curl` ou o [Swagger UI](http://localhost:8080/swagger-ui/index.html) para interagir com a API.

#### Exemplo: Criar um novo cliente

```bash
curl -X POST http://localhost:8080/clientes \
-H "Content-Type: application/json" \
-d '{
    "nome": "João Silva",
    "email": "joao@example.com",
    "cpf": "11144477735",
    "telefone": "11999999999",
    "endereco": {
        "logradouro": "Rua A",
        "numero": "123",
        "complemento": "Apt 456",
        "bairro": "Centro",
        "cidade": "São Paulo",
        "uf": "SP",
        "cep": "01000-000"
    }
}'
```

Para mais exemplos (listar, atualizar, deletar), consulte a coleção Postman ou o Swagger UI.

#### Observação sobre Validação

As anotações de validação `@CPF` e `@Size` foram removidas para resolver um problema de inicialização. Para reativá-las:

1. **Adicione a dependência `caelum-stella-core`** ao `pom.xml`.
2. **Adicione as anotações de volta** aos DTOs.

---

## 4. Ideias para Futuras Contribuições

Procurando por onde começar? Aqui está uma ideia de funcionalidade que seria ótima para o projeto:

### Implementar Exclusão Lógica (Soft Delete) para Clientes

Atualmente, quando um `DELETE` é feito no endpoint `/clientes/{id}`, o cliente é permanentemente removido do banco de dados. Uma melhoria crucial seria implementar a **exclusão lógica**.

**O que fazer:**

1. **Adicionar um campo `ativo`** na entidade `Cliente.java`.

    ```java
    private Boolean ativo;
    ```

2. **Inicializar o campo `ativo` como `true`** no construtor da entidade `Cliente`.
3. **Criar um método `excluir()`** na entidade `Cliente` que apenas muda o campo `ativo` para `false`.

    ```java
    public void excluir() {
        this.ativo = false;
    }
    ```

4. **Atualizar o método `excluir` no `ClienteController`** para buscar o cliente, chamar o novo método `excluir()` da entidade e salvá-lo, em vez de usar `repository.deleteById(id)`.
5. **Atualizar o método de listagem** para retornar apenas os clientes que estão com o status `ativo = true`. Você pode criar um novo método no `ClienteRepository` para isso, como `findAllByAtivoTrue()`.

Essa é uma prática comum em aplicações reais e uma ótima forma de aprender e contribuir com o projeto!
