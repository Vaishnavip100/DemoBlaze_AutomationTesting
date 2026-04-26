package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class HomePage extends BasePage {
	private By loginBtn=By.id("login2");
	private By signupBtn=By.id("signin2");
	private By logoutBtn=By.id("logout2");
	private By welcomeUser=By.id("nameofuser");
    
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickLogin() {
        click(loginBtn);
    }

    public void clickSignUp() {
        click(signupBtn);
    }

    public void clickLogout() {
        click(logoutBtn);
        waitForLogout();
    }

    public String getLoggedInUser() {
        return getText(welcomeUser);
    }


    public boolean isUserLoggedIn() {
        try {
            return getElement(welcomeUser).isDisplayed();
        } catch (Exception e) {
            return false; 
        }
    }

    public void waitForLogout() {
        waitForInvisibility(welcomeUser);
    }
}