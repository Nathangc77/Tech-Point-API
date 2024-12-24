# TechPoint

## Descrição
TechPoint é uma API REST desenvolvida para ajudar empresas a controlar os registros de ponto dos funcionários. O projeto inclui funcionalidades de autenticação, registro diário de atividades e ferramentas administrativas para gerenciar empregados.

---

## Tecnologias Utilizadas
- **Java** com Spring Boot
- **Maven** para gerenciamento de dependências
- **Postman** para realizar e testar requisições HTTP

---

## Instalação

Siga as etapas abaixo para configurar o ambiente e executar o projeto:

1. **Pré-requisitos**:
    - Instale o **Java JDK** (recomendado: versão 17 ou superior).
    - Instale o **Maven**.
    - Baixe e instale o **Postman**.
    - Configure uma IDE para Java, como IntelliJ IDEA ou Eclipse.

2. **Clonando o Repositório**:
   ```bash
   git clone https://github.com/Nathangc77/Tech-Point.git
   cd techpoint
   ```

3. **Configurando o Projeto**:
    - Certifique-se de que as dependências Maven sejam baixadas automaticamente ao abrir o projeto na IDE.

4. **Executando o Projeto**:
    - Na sua IDE, localize a classe principal e execute o projeto.
    - A API estará disponível em: `http://localhost:8080`.

5. **Documentação da API**:
    - A documentação gerada pelo Swagger pode ser acessada em: `http://localhost:8080/swagger-ui/index.html`.

---

## Funcionalidades

### Empregado:
- **Login**.
- **Registros diários**: entrada, almoço, saída.
- **Visualização de registros**.

### Administrador:
Além das funcionalidades dos empregados, os administradores podem:
- Criar, alterar e deletar registros.
- Cadastrar novos funcionários.
- Visualizar todos os funcionários cadastrados.

---

## Login
Para acessar os endpoints da API, é necessário realizar login. Use as credenciais abaixo para testar:

- **Administrador**:
    - Usuário: `EMP-1`
    - Senha: `123456`

- **Empregado Padrão**:
    - Usuário: `EMP-2`
    - Senha: `123456`

Sem realizar o login, não será possível acessar os endpoints.

### Observação
Funcionários cadastrados posteriormente terão como senha padrão o seu CPF.

---

## Arquivos do Postman
Os arquivos para importação no Postman estão localizados na pasta raiz do projeto.

---

## Público-Alvo
Este projeto é destinado a:
- Desenvolvedores interessados em estudar APIs REST.
- Empresas que procuram soluções de controle de ponto.

---

## Status do Projeto
Atualmente, o projeto está **concluído**. No entanto, estou aberto a implementar novas funcionalidades no futuro.

---

## Contribuição
Contribuições são bem-vindas! Caso deseje colaborar, entre em contato comigo através do e-mail abaixo.

---

## Licença
Este projeto está licenciado sob a Licença MIT. Consulte o arquivo LICENSE para mais detalhes.

---

## Contato

- E-mail: [nathangc7@gmail.com](mailto:nathangc7@gmail.com)
- LinkedIn: [Nathan Moreira](https://www.linkedin.com/in/nathan-moreira-a07037191/)

---

Agradeço por explorar o TechPoint! Caso tenha dúvidas ou sugestões, entre em contato.

