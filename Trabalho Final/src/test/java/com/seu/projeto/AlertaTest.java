package com.seu.projeto;

// Importação das bibliotecas necessárias
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlertaTest {
    private WebDriver driver; // Declaração da variável WebDriver que irá controlar o navegador

    @BeforeEach
    public void setUp() {
        // Usar o WebDriverManager para configurar automaticamente o ChromeDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(); // Inicializa o WebDriver com o ChromeDriver
        driver.manage().window().maximize(); // Maximiza a janela do navegador
        driver.get("https://the-internet.herokuapp.com/javascript_alerts"); // Acessa a URL onde os testes serão feitos
    }

    @Test
    public void testAlertaDeConfirmacao() {
        // Encontrar o botão que ativa o alerta de confirmação
        WebElement alertaButton = driver.findElement(By.cssSelector("button[onclick='jsConfirm()']"));
        alertaButton.click(); // Clica no botão para ativar o alerta de confirmação

        // Esperar até que o alerta esteja presente
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent()); // Aguarda até que o alerta seja exibido

        // Alterna para o alerta
        Alert alerta = driver.switchTo().alert();
        alerta.accept(); // Aceita o alerta, clicando no botão "Ok"

        // Verificar a mensagem de resultado
        WebElement resultadoMessage = driver.findElement(By.id("result"));
        // Assegura que a mensagem "You clicked: Ok" foi exibida na página
        assertTrue(resultadoMessage.getText().contains("You clicked: Ok"), "Alerta não foi aceito corretamente.");
    }

    @Test
    public void testAlertaPrompt() {
        // Encontrar o botão que ativa o alerta do tipo prompt
        WebElement promptButton = driver.findElement(By.cssSelector("button[onclick='jsPrompt()']"));
        promptButton.click(); // Clica no botão para ativar o alerta do tipo prompt

        // Esperar até que o alerta esteja presente
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent()); // Aguarda até que o alerta seja exibido

        // Alterna para o alerta
        Alert alerta = driver.switchTo().alert();
        String mensagemEntrada = "Teste de prompt!"; // Mensagem que será inserida no prompt
        alerta.sendKeys(mensagemEntrada); // Digita a mensagem no campo de texto do prompt
        alerta.accept(); // Confirma a ação no prompt, clicando em "Ok"

        // Verificar a mensagem de resultado
        WebElement resultadoMessage = driver.findElement(By.id("result"));
        // Assegura que o texto do prompt foi corretamente inserido na página
        assertTrue(resultadoMessage.getText().contains(mensagemEntrada), "Texto do prompt não foi enviado corretamente.");
    }

    @Test
    public void testCampoObrigatorioEmFormulario() {
        // Acessar a página do formulário
        driver.get("https://ultimateqa.com/filling-out-forms/");

        // Tentar enviar o formulário sem preencher os campos obrigatórios
        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit']"));
        submitButton.click(); // Clica no botão de envio do formulário sem preencher os campos

        // Esperar a mensagem de erro aparecer
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("et_pb_contact_error")));

        // Verificar se a mensagem de erro está visível, indicando que campos obrigatórios não foram preenchidos
        assertTrue(errorElement.isDisplayed(), "Mensagem de erro não foi exibida para campos obrigatórios.");
    }

    @Test
    public void testFormularioPreenchidoCorretamente() {
        // Acessar a página do formulário
        driver.get("https://ultimateqa.com/filling-out-forms/");

        // Preencher os campos do formulário
        WebElement firstNameField = driver.findElement(By.id("et_pb_contact_name_0"));
        WebElement lastNameField = driver.findElement(By.id("et_pb_contact_name_1"));
        WebElement emailField = driver.findElement(By.id("et_pb_contact_email_0"));
        WebElement messageField = driver.findElement(By.id("et_pb_contact_message_0"));

        firstNameField.sendKeys("João"); // Preenche o campo "Nome"
        lastNameField.sendKeys("Silva"); // Preenche o campo "Sobrenome"
        emailField.sendKeys("joao.silva@exemplo.com"); // Preenche o campo "E-mail"
        messageField.sendKeys("Esta é uma mensagem de teste."); // Preenche o campo "Mensagem"

        // Submeter o formulário
        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit']"));
        submitButton.click(); // Clica no botão de envio do formulário

        // Esperar a confirmação de sucesso
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("et_pb_contact_success")));

        // Verificar se a mensagem de sucesso aparece, indicando que o formulário foi enviado com sucesso
        assertTrue(successMessage.isDisplayed(), "Mensagem de sucesso não apareceu após o envio do formulário.");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Fecha o navegador após o teste
        }
    }
}
