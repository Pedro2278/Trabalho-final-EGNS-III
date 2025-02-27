package com.seu.projeto;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {
    private WebDriver driver;  // Instância do WebDriver que controlará o navegador.
    private WebDriverWait wait;  // Instância do WebDriverWait para aguardar até que os elementos estejam visíveis ou interativos.

    // Método que configura o ambiente antes de cada teste.
    @BeforeEach
    public void setUp() {
        // Gerenciar o ChromeDriver automaticamente com WebDriverManager
        WebDriverManager.chromedriver().setup();  // Essa linha configura automaticamente o ChromeDriver necessário para o Selenium.

        // Definir opções para o ChromeDriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");  // Permite conexões com outros domínios, útil para evitar erros de CORS.
        options.addArguments("--no-sandbox");  // Desabilita o sandbox para contornar restrições do ambiente do sistema.
        options.addArguments("--disable-dev-shm-usage");  // Desativa o uso de /dev/shm para evitar problemas em ambientes com recursos limitados.
        options.addArguments("--headless");  // Executa o navegador em modo headless (sem interface gráfica). Remova essa linha se quiser ver o navegador.
        options.addArguments("--window-size=1920,1080");  // Define o tamanho da janela do navegador para garantir que a visualização seja adequada.

        // Cria uma nova instância do ChromeDriver com as opções definidas.
        driver = new ChromeDriver(options);
        
        // Definir o tempo de espera implícito para o WebDriver
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  // Tempo máximo de 10 segundos para o WebDriver aguardar elementos serem carregados.
        driver.manage().window().maximize();  // Maximiza a janela do navegador.

        // Inicializa o WebDriverWait com o tempo de espera para os elementos
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Aguarda até 10 segundos para que um elemento esteja presente ou interativo.
    }

    // Teste para simular um login com falha.
    @Test
    public void testLoginComFalha() {
        driver.get("https://the-internet.herokuapp.com/login");  // Acessa a página de login.

        // Espera até que os campos de nome de usuário e senha estejam presentes
        WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
        WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));

        // Preenche os campos com dados inválidos
        usernameField.sendKeys("usuario_invalido");  // Preenche o campo de nome de usuário com um valor inválido.
        passwordField.sendKeys("senha_invalida");  // Preenche o campo de senha com um valor inválido.
        loginButton.click();  // Clica no botão de login.

        // Espera até que a mensagem de erro apareça
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        
        // Verifica se a mensagem de erro está correta
        assertTrue(errorMessage.getText().contains("Your username is invalid!"),  // Verifica se a mensagem de erro contém o texto esperado.
                   "Mensagem de erro incorreta. Texto encontrado: " + errorMessage.getText());  // Caso a mensagem seja errada, exibe o texto encontrado.
    }

    // Teste para simular um login com sucesso.
    @Test
    public void testLoginComSucesso() {
        driver.get("https://the-internet.herokuapp.com/login");  // Acessa a página de login.

        // Espera até que os campos de nome de usuário e senha estejam presentes
        WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
        WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));

        // Preenche os campos com dados válidos
        usernameField.sendKeys("tomsmith");  // Preenche o campo de nome de usuário com um valor válido.
        passwordField.sendKeys("SuperSecretPassword!");  // Preenche o campo de senha com uma senha válida.
        loginButton.click();  // Clica no botão de login.

        // Espera até que o botão de logout esteja visível após o login bem-sucedido
        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".button.secondary.radius")));
        
        // Verifica se o botão de logout está visível, indicando que o login foi bem-sucedido
        assertTrue(logoutButton.isDisplayed(), "Login falhou. O botão de logout não foi encontrado.");
    }

    // Teste para simular o preenchimento correto de um formulário.
    @Test
    public void testFormularioPreenchidoCorretamente() {
        driver.get("https://ultimateqa.com/filling-out-forms/");  // Acessa a página do formulário.

        // Espera até que o campo de email esteja presente e o botão de envio esteja clicável
        WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='email']")));
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='submit']")));
        
        // Preenche o campo de email com um valor válido
        emailField.sendKeys("teste@dominio.com");
        submitButton.click();  // Clica no botão de envio.

        // Espera até que a mensagem de confirmação seja exibida após o envio do formulário
        WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".et_pb_contact_form_0 .et-pb-contact-message")));
        
        // Verifica se a mensagem de confirmação está visível
        assertTrue(confirmationMessage.isDisplayed(), "Mensagem de confirmação não encontrada.");
    }

    // Método que encerra o WebDriver após cada teste.
    @AfterEach
    public void tearDown() {
        if (driver != null) {  // Verifica se o driver foi inicializado corretamente
            try {
                driver.quit();  // Fecha o navegador e encerra a sessão do WebDriver.
            } catch (Exception e) {
                System.out.println("Erro ao fechar o navegador: " + e.getMessage());  // Caso haja erro ao fechar o navegador, exibe uma mensagem.
            }
        }
    }
}
