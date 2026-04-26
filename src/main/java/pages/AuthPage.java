package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BasePage;

public class AuthPage extends BasePage {
    private By signupUsername=By.id("sign-username");
    private By signupPassword=By.id("sign-password");
    private By signupBtn=By.xpath("//button[text()='Sign up']");

    private By loginUsername=By.id("loginusername");
    private By loginPassword=By.id("loginpassword");
    private By loginBtn=By.xpath("//button[text()='Log in']");

    public AuthPage(WebDriver driver) {
        super(driver);
    }

    public void signup(String username, String password) {
        type(signupUsername, username);
        type(signupPassword, password);
        click(signupBtn);
    }

    public void login(String username, String password) {
        type(loginUsername, username);
        type(loginPassword, password);
        click(loginBtn);
    }

    public String getAlertTextIfPresent() {
        try {
            WebDriverWait shortWait=new WebDriverWait(driver, Duration.ofSeconds(5));
            shortWait.until(ExpectedConditions.alertIsPresent());
            return driver.switchTo().alert().getText();
        } catch (Exception e) {
            return null;
        }
    }

    public void acceptAlertIfPresent() {
        try {
            WebDriverWait shortWait=new WebDriverWait(driver, Duration.ofSeconds(5));
            shortWait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
        } catch (Exception e) {
            // ignore
        }
    }

    public void enterSignupUsername(String username) {
        type(signupUsername, username); 
    }

    public void enterSignupPassword(String password) {
        type(signupPassword, password);  
    }

    public String getSignupUsernameValue() {
        return getValue(signupUsername); 
    }

    public String getSignupPasswordValue() {
        return getValue(signupPassword);
    }
}