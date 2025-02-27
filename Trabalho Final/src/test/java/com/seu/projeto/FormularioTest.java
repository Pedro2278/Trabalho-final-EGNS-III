package com.seu.projeto;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class FormularioTest {
    public static void main(String[] args) {
        // Gerenciar o EdgeDriver automaticamente com WebDriverManager
        WebDriverManager.edgedriver().setup();
        
        // Configurar o WebDriver para o Edge
        WebDriver driver = new EdgeDriver();

        try {
            // Acessar a página
            driver.get("https://ultimateqa.com/filling-out-forms/");

            // Definir um tempo de espera para carregamento completo da página
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            // Esperar até que o campo de email esteja visível
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("et_pb_contact_email_0")));

            // Interagir com os elementos do formulário
            WebElement nome = driver.findElement(By.id("et_pb_contact_name_0"));
            nome.sendKeys("João Teste");

            WebElement email = driver.findElement(By.id("et_pb_contact_email_0"));
            email.sendKeys("teste@email.com");

            WebElement mensagem = driver.findElement(By.id("et_pb_contact_message_0"));
            mensagem.sendKeys("Isso é um teste de formulário.");

            // Clicar no botão de envio (certifique-se de que o seletor do botão está correto)
            WebElement botaoEnviar = driver.findElement(By.cssSelector("button.et_pb_contact_submit"));
            botaoEnviar.click();

            // Esperar até que a mensagem de sucesso apareça
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".et-pb-contact-message")));

            // Verificar a mensagem de sucesso
            WebElement sucessoMensagem = driver.findElement(By.cssSelector(".et-pb-contact-message"));
            if (sucessoMensagem.isDisplayed()) {
                System.out.println("Teste realizado com sucesso!");
            } else {
                System.out.println("Erro: Mensagem de sucesso não apareceu.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fechar o navegador
            driver.quit();
        }
    }
}
