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
        WebDriverManager.edgedriver().setup(); // O WebDriverManager configura automaticamente o EdgeDriver para o navegador Microsoft Edge, garantindo que a versão correta do driver seja usada.
        
        // Configurar o WebDriver para o Edge
        WebDriver driver = new EdgeDriver(); // Inicializa o WebDriver para controlar o navegador Microsoft Edge.

        try {
            // Acessar a página
            driver.get("https://ultimateqa.com/filling-out-forms/"); // Acessa a URL do formulário que será testado.

            // Definir um tempo de espera para carregamento completo da página
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Cria uma instância de WebDriverWait para aguardar até 15 segundos por elementos na página.

            // Esperar até que o campo de email esteja visível
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("et_pb_contact_email_0"))); // Aguarda até que o campo de e-mail esteja presente na página, o que significa que a página carregou o formulário corretamente.

            // Interagir com os elementos do formulário
            WebElement nome = driver.findElement(By.id("et_pb_contact_name_0")); // Localiza o campo de nome do formulário.
            nome.sendKeys("João Teste"); // Preenche o campo de nome com "João Teste".

            WebElement email = driver.findElement(By.id("et_pb_contact_email_0")); // Localiza o campo de e-mail do formulário.
            email.sendKeys("teste@email.com"); // Preenche o campo de e-mail com "teste@email.com".

            WebElement mensagem = driver.findElement(By.id("et_pb_contact_message_0")); // Localiza o campo de mensagem do formulário.
            mensagem.sendKeys("Isso é um teste de formulário."); // Preenche o campo de mensagem com o texto "Isso é um teste de formulário."

            // Clicar no botão de envio (certifique-se de que o seletor do botão está correto)
            WebElement botaoEnviar = driver.findElement(By.cssSelector("button.et_pb_contact_submit")); // Localiza o botão de envio do formulário usando o seletor CSS.
            botaoEnviar.click(); // Clica no botão de envio para submeter o formulário.

            // Esperar até que a mensagem de sucesso apareça
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".et-pb-contact-message"))); // Aguarda até que a mensagem de sucesso seja visível após o envio do formulário.

            // Verificar a mensagem de sucesso
            WebElement sucessoMensagem = driver.findElement(By.cssSelector(".et-pb-contact-message")); // Localiza o elemento que contém a mensagem de sucesso.
            if (sucessoMensagem.isDisplayed()) { // Verifica se a mensagem de sucesso está visível na página.
                System.out.println("Teste realizado com sucesso!"); // Se a mensagem de sucesso estiver visível, imprime "Teste realizado com sucesso!" no console.
            } else {
                System.out.println("Erro: Mensagem de sucesso não apareceu."); // Caso contrário, imprime que houve um erro e a mensagem de sucesso não apareceu.
            }

        } catch (Exception e) {
            e.printStackTrace(); // Em caso de erro, imprime o erro completo no console.
        } finally {
            // Fechar o navegador
            driver.quit(); // Encerra a sessão do WebDriver e fecha o navegador, independentemente de o teste ter sido bem-sucedido ou não.
        }
    }
}
