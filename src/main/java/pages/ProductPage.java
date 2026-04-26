package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductPage extends BasePage {
    private By phonesCategory=By.linkText("Phones");
    private By laptopsCategory=By.linkText("Laptops");
    private By homeLink=By.cssSelector("a.nav-link[href='index.html']");

    private By productTitles=By.cssSelector(".card-title a");

    private By productNameLocator=By.cssSelector(".name");
    private By productPrice=By.cssSelector(".price-container");
    private By productDescription=By.id("more-information");
    
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public int getProductCount() {
        return getElements(productTitles).size(); 
    }

    public void openFirstProduct() {
        getElements(productTitles).get(0).click(); 
        waitForElement(productNameLocator);
    }

    public String readProductName() {
        return getText(productNameLocator);
    }

    public String readProductPrice() {
        return getText(productPrice);
    }

    public String readProductDescription() {
        return getText(productDescription);
    }

    public void clickHome() {
        click(homeLink);
        waitForElement(productTitles);
    }

    public void selectProductByName(String productName) {
        By product=By.xpath("//a[text()='" + productName + "']");
        click(product);
        waitForElement(productNameLocator);
    }

    public int getProductPriceValue() {
        String priceText=readProductPrice();
        return Integer.parseInt(priceText.replaceAll("[^0-9]", ""));
    }

    public List<String> getProductNames() {
        List<String> names=new ArrayList<>();
        for (WebElement element:getElements(productTitles)) {
            names.add(element.getText().trim());
        }
        return names;
    }

    public void openPhones() {
        click(phonesCategory);
        waitForElement(productTitles);
    }

    public void openLaptops() {
        click(laptopsCategory);
        waitForElement(productTitles);
    }

    public void goHome() {
        click(homeLink);
        waitForElement(productTitles);
    }
}