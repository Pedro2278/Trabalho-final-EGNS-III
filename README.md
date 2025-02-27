# Projeto de Testes Automatizados com Selenium e JUnit

## Descrição do Projeto

Este projeto tem como objetivo automatizar e validar funcionalidades de sites utilizando o Selenium WebDriver com a linguagem de programação **Java** e o framework de testes **JUnit**. O projeto foi desenvolvido para fins educacionais na disciplina de **Engenharia de Software III**. Ele realiza testes de autenticação, interações com formulários e manipulação de alertas JavaScript nos sites **The Internet** e **Ultimate QA**.

O sistema foi configurado para automatizar a execução dos testes, permitindo validar funcionalidades de login, preenchimento de formulários e interações com pop-ups/alertas de forma eficiente.

## Funcionalidades

O projeto implementa testes para as seguintes funcionalidades:

- **Autenticação de Usuário**:
  - Teste de login com credenciais válidas e inválidas.
  - Teste de logout.
  
- **Formulários**:
  - Validação de preenchimento correto de formulários.
  - Validação de campos obrigatórios em formulários.
  
- **Alertas e Pop-ups**:
  - Interação com alertas JavaScript: `alert()`, `confirm()`, `prompt()`.

Os testes são realizados de forma automatizada usando **Selenium WebDriver** e são organizados em **JUnit** para garantir a integridade e execução correta dos mesmos.

## Tecnologias Utilizadas

- **Java** (versão 21.0.1): Linguagem de programação para escrever os testes.
- **Selenium WebDriver** (versão 4.12.1): Biblioteca para automação de testes em navegadores web.
- **JUnit 5** (versão 5.8.2): Framework de testes unitários utilizado para estruturar os testes.
- **WebDriverManager** (versão 5.3.0): Gerencia a versão do ChromeDriver automaticamente.
- **ChromeDriver**: Driver necessário para controlar o navegador Google Chrome.
- **Maven** (versão 3.8.4): Ferramenta de automação de build e gerenciamento de dependências.
- **Google Chrome** (versão 133.0.6943.127): Navegador utilizado para rodar os testes automatizados.

## Estrutura do Projeto

A estrutura do projeto é organizada da seguinte maneira:
/src /main /java /tests - LoginTest.java # Teste de autenticação (login/logout) - AlertaTest.java # Teste de alertas JavaScript (alert, confirm, prompt) - FormularioTest.java # Teste de formulários (preenchimento e validação) /resources - (Outros recursos necessários, se houver) /drivers - chromedriver.exe # Driver do navegador Chrome utilizado para os testes /pom.xml # Arquivo de configuração do Maven /docs


### Descrição dos Arquivos

- **/src/main/java/tests**: Contém os arquivos de código com os testes automatizados escritos em Java.
  - `LoginTest.java`: Testa a funcionalidade de login e logout no site **The Internet**.
  - `AlertaTest.java`: Testa interações com alertas JavaScript (confirmação, prompt e alerta simples).
  - `FormularioTest.java`: Testa o preenchimento de formulários e valida campos obrigatórios no site **Ultimate QA**.

- **/drivers**: Contém o arquivo executável do **ChromeDriver** necessário para rodar os testes no navegador Chrome.

- **/docs**: Contém a documentação em PDF dos casos de teste, com informações detalhadas sobre os testes realizados, incluindo as condições de teste e os resultados esperados.

- **/pom.xml**: Arquivo de configuração do Maven que contém as dependências do projeto, como Selenium e JUnit, além de configurar o WebDriverManager.

## Como Rodar os Testes

### Pré-requisitos

1. **Java 21** ou superior instalado em sua máquina.
2. **Maven** instalado para gerenciamento de dependências.
3. **Google Chrome** instalado (certifique-se de que a versão do Chrome seja compatível com a versão do ChromeDriver).

### Passos para Executar

1. **Clone o Repositório**:
   Primeiro, faça o clone do repositório para sua máquina local:
   ```bash
   git clone https://github.com/seu-usuario/nome-do-repositorio.git
   cd nome-do-repositorio
2 - Instale as Dependências: O projeto utiliza o Maven para gerenciar as dependências. Para instalá-las, execute o seguinte comando:
mvn install
3 - Execute os Testes: Para rodar os testes, use o comando Maven abaixo:
mvn test
4 - Verifique os Relatórios: Após a execução, o Maven gerará relatórios dentro do diretório target/surefire-reports. Você pode acessar esses relatórios para verificar os resultados detalhados da execução dos testes.

Configuração do ChromeDriver
O projeto usa o WebDriverManager para gerenciar a versão correta do ChromeDriver que seja compatível com a versão do navegador instalado. Isso garante que a versão do ChromeDriver seja sempre atualizada e compatível com o navegador sem que seja necessário configurá-lo manualmente.

Casos de Teste
1. LoginTest
Objetivo: Validar o processo de login e logout no site The Internet.
Testes
- Teste de login com credenciais válidas.
- Teste de login com credenciais inválidas.
- Teste de logout após login bem-sucedido.
Resultado Esperado:
- O login deve ser bem-sucedido quando as credenciais forem corretas.
- O login deve falhar quando as credenciais forem incorretas.
- O logout deve redirecionar o usuário para a página inicial.
3. AlertaTest
Objetivo: Validar a interação com alertas JavaScript (alert(), confirm(), prompt()).
Testes:
- Teste de interação com o alerta simples (alert()).
- Teste de interação com o alerta de confirmação (confirm()).
- Teste de interação com o alerta de prompt (prompt()).
Resultado Esperado:
- O alerta deve ser aceito ou rejeitado conforme esperado.
- No caso do prompt(), o valor inserido no campo de texto deve ser capturado corretamente.
4. FormularioTest
Objetivo: Validar o preenchimento correto de formulários e a verificação de campos obrigatórios.
Testes:
- Teste de preenchimento correto de todos os campos do formulário.
- Teste de envio do formulário com campos obrigatórios em branco.
Resultado Esperado:
- O formulário deve ser enviado corretamente quando todos os campos forem preenchidos.
- O formulário não deve ser enviado quando os campos obrigatórios estiverem vazios.
Relatório de Execução
- Após a execução dos testes, os resultados serão gerados no diretório target/surefire-reports.
- O Maven gerará um relatório detalhado para cada execução de teste.
- Logs adicionais podem ser encontrados na pasta target para ajudar na depuração de falhas.
Contribuições
Se você deseja contribuir para o projeto, siga os seguintes passos:
1 - Fork o repositório: Crie um fork do repositório clicando no botão "Fork" na página principal do GitHub.
2 - Clone o repositório: Clone seu fork para sua máquina local:
  git clone https://github.com/seu-usuario/nome-do-repositorio.git
3 - Crie uma nova branch: Crie uma nova branch para sua feature ou correção:
  git checkout -b nova-feature
4 - Faça as alterações: Implemente suas melhorias ou correções no código.
5 - Comite as mudanças: Faça o commit das suas alterações com uma mensagem descritiva:
  git commit -am "Adiciona nova feature"
git commit -am "Adiciona nova feature"
git commit -am "Adiciona nova feature"
5 - Push para o repositório remoto: Envie suas alterações para o repositório remoto:
  git push origin nova-feature
7 - Abra um Pull Request: No GitHub, abra um pull request para revisão das suas alterações.

Licença
Este projeto está licenciado sob a MIT License.

Se você tiver dúvidas ou problemas, sinta-se à vontade para abrir uma issue ou entrar em contato.


### Detalhes:

- **Tecnologias Utilizadas**: Explicação detalhada de cada tecnologia usada.
- **Estrutura do Projeto**: Descrição clara sobre os diretórios e arquivos do projeto.
- **Como Executar**: Passos detalhados para rodar o projeto, incluindo pré-requisitos e comandos.
- **Casos de Teste**: Explicação sobre o que cada conjunto de testes valida e o que se espera como resultado.
- **Relatório de Execução**: Como visualizar os resultados dos testes.
- **Contribuições**: Instruções de como contribuir para o projeto.
- **Licença**: Informações sobre a licença do projeto.

Este `README.md` contém todas as informações necessárias para que qualquer pessoa entenda o funcionamento do seu projeto e saiba como executar os testes e contribuir para ele.
