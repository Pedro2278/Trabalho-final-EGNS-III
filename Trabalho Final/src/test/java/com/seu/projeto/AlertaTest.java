package com.seu.projeto;

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
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Usar o WebDriverManager para configurar automaticamente o ChromeDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }

    @Test
    public void testAlertaDeConfirmacao() {
        // Encontrar o botão que ativa o alerta de confirmação
        WebElement alertaButton = driver.findElement(By.cssSelector("button[onclick='jsConfirm()']"));
        alertaButton.click();

        // Esperar e alternar para o alerta
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alerta = driver.switchTo().alert();
        alerta.accept(); // Aceita o alerta

        // Verificar a mensagem de resultado
        WebElement resultadoMessage = driver.findElement(By.id("result"));
        assertTrue(resultadoMessage.getText().contains("You clicked: Ok"), "Alerta não foi aceito corretamente.");
    }

    @Test
    public void testAlertaPrompt() {
        // Encontrar o botão que ativa o alerta do tipo prompt
        WebElement promptButton = driver.findElement(By.cssSelector("button[onclick='jsPrompt()']"));
        promptButton.click();

        // Esperar e alternar para o alerta
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alerta = driver.switchTo().alert();
        String mensagemEntrada = "Teste de prompt!";
        alerta.sendKeys(mensagemEntrada); // Digita no prompt
        alerta.accept(); // Confirma o prompt

        // Verificar a mensagem de resultado
        WebElement resultadoMessage = driver.findElement(By.id("result"));
        assertTrue(resultadoMessage.getText().contains(mensagemEntrada), "Texto do prompt não foi enviado corretamente.");
    }

    @Test
    public void testCampoObrigatorioEmFormulario() {
        // Acessar a página do formulário
        driver.get("https://ultimateqa.com/filling-out-forms/");

        // Tentar enviar o formulário sem preencher os campos obrigatórios
        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit']"));
        submitButton.click();

        // Esperar a mensagem de erro aparecer
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("et_pb_contact_error")));

        // Verificar se a mensagem de erro está visível
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

        firstNameField.sendKeys("João");
        lastNameField.sendKeys("Silva");
        emailField.sendKeys("joao.silva@exemplo.com");
        messageField.sendKeys("Esta é uma mensagem de teste.");

        // Submeter o formulário
        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit']"));
        submitButton.click();

        // Esperar a confirmação de sucesso
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("et_pb_contact_success")));

        // Verificar se a mensagem de sucesso aparece
        assertTrue(successMessage.isDisplayed(), "Mensagem de sucesso não apareceu após o envio do formulário.");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
