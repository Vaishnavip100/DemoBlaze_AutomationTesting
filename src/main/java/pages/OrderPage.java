package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage extends BasePage {
	private By placeOrderBtn=By.xpath("//button[text()='Place Order']");

	private By nameField=By.id("name");
	private By countryField=By.id("country");
	private By cityField=By.id("city");
	private By cardField=By.id("card");
	private By monthField=By.id("month");
	private By yearField=By.id("year");

	private By purchaseBtn=By.xpath("//button[text()='Purchase']");

	private By confirmationBox=By.className("sweet-alert");
	private By confirmationText=By.cssSelector(".sweet-alert p");
	private By okBtn=By.xpath("//button[text()='OK']");
    
    public OrderPage(WebDriver driver) {
        super(driver);
    }

    public void clickPlaceOrder() {
        click(placeOrderBtn);
        waitForElement(nameField);
    }

    public void fillOrderForm(String name, String country, String city, String card, String month, String year) {
        type(nameField, name);
        type(countryField, country);
        type(cityField, city);
        type(cardField, card);
        type(monthField, month);
        type(yearField, year);
    }

    public void clickPurchase() {
        click(purchaseBtn);
        waitForElement(confirmationBox);
    }

    public String getConfirmationText() {
        return getText(confirmationText);
    }

    public void confirmOK() {
        click(okBtn);
        waitForInvisibility(confirmationBox);
    }

    public void clickPurchaseWithoutWait() {
        click(purchaseBtn); 
    }

    public String getAlertText() {
        return getAlertTextIfPresent(); 
    }

    public void acceptAlert() {
        acceptAlertIfPresent(); 
    }
}