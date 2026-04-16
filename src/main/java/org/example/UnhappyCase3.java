package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UnhappyCase3 {
    public static void main(String[] args) {
        // unhappy case: không nhập gmail
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://saucelabs.com/request-demo");

        WebElement company = driver.findElement(By.xpath("//input[@id='Company']"));
        company.sendKeys("Devv");

        WebElement interest = driver.findElement(By.xpath("//select[@id='Solution_Interest__c']"));
        interest.click();
        WebElement select2 = driver.findElement(By.xpath("//select[@id='Solution_Interest__c']/descendant::option[contains(text(),'Sauce AI Agents')]"));
        select2.click();

        WebElement comment = driver.findElement(By.xpath("//textarea[@name='Sales_Contact_Comments__c']"));
        comment.sendKeys("DevPro");

        WebElement checkbox = driver.findElement(By.xpath("//label[contains(text(),'Please check this box')]"));
        checkbox.click();

        WebElement submit = driver.findElement(By.xpath("//button[@type='submit']"));
        submit.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement errorMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(text(),'Must be valid email')]")
                )
        );

        String errorText = errorMsg.getText();
        System.out.println("Error message: " + errorText);

        // 2. Check KHÔNG redirect
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);

        // 3. ASSERT
        if(errorText.contains("Must be valid email")
                && !currentUrl.contains("thank-you-contact")){

            System.out.println("TEST PASS - Unhappy Case");

        }else{
            throw new RuntimeException("TEST FAIL - Unhappy Case");
        }

        driver.quit();
    }
}
