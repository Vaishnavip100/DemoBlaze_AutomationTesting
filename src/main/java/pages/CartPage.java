package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends BasePage {
	private By addToCartBtn=By.linkText("Add to cart");
	private By cartLink=By.id("cartur");

	private By cartRows=By.xpath("//tbody/tr");
	private By productNames=By.xpath("//tbody/tr/td[2]");
	private By totalPrice=By.id("totalp");
    
    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void clickAddToCart() {
        click(addToCartBtn);
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public void openCart() {
        click(cartLink);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(cartRows, 0));
    }

    public int getProductCount() {
        return getElements(cartRows).size();
    }

    public boolean isProductInCart(String productName) {
        List<WebElement> names=getElements(By.xpath("//tbody/tr/td[2]"));
        if (names.isEmpty()) return false;

        for (WebElement e:names) {
            if (e.getText().trim().equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }

    public void deleteProductByName(String productName) {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(cartRows, 0));
        int before=getElements(cartRows).size();
        List<WebElement> rows=getElements(cartRows);
        for (WebElement row:rows) {
            String name=row.findElement(By.xpath("./td[2]")).getText().trim();

            if (name.equalsIgnoreCase(productName)) {
                row.findElement(By.linkText("Delete")).click();
                break;
            }
        }
        wait.until(ExpectedConditions.numberOfElementsToBeLessThan(cartRows, before));
    }

    public int getProductPriceFromCart(String productName) {
        List<WebElement> rows=getElements(cartRows);
        for (WebElement row:rows) {
            String name=row.findElement(By.xpath("./td[2]")).getText().trim();

            if (name.equalsIgnoreCase(productName)) {
                String price=row.findElement(By.xpath("./td[3]")).getText();
                return Integer.parseInt(price);
            }
        }
        throw new RuntimeException("Product not found in cart: " + productName);
    }

    public int getTotalPrice() {
        String total=getText(totalPrice);
        return Integer.parseInt(total);
    }
}