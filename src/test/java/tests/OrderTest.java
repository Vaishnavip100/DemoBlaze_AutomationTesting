package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProductPage;
import pages.CartPage;
import pages.OrderPage;

public class OrderTest extends BaseTest {

    //Successful Order Placement
    @Test
    public void verifySuccessfulOrderPlacement() {
        ProductPage product=new ProductPage(getDriver());
        CartPage cart=new CartPage(getDriver());
        OrderPage order=new OrderPage(getDriver());

        String productName="Samsung galaxy s6";

        product.openPhones();
        product.selectProductByName(productName);
        
        cart.clickAddToCart();
        cart.openCart();

        order.clickPlaceOrder();

        order.fillOrderForm("Vaishnavi","India","Vijayawada","1234567890","12","2026");

        order.clickPurchase();

        String confirmation=order.getConfirmationText();

        Assert.assertTrue(confirmation.contains("Id"),"Order ID not found in confirmation");
        Assert.assertTrue(confirmation.contains("Amount"),"Amount missing in confirmation");

        order.confirmOK();
    }

    //Verify Order ID present
    @Test
    public void verifyOrderIdInConfirmation() {
        ProductPage product=new ProductPage(getDriver());
        CartPage cart=new CartPage(getDriver());
        OrderPage order=new OrderPage(getDriver());

        product.openPhones();
        product.selectProductByName("Samsung galaxy s6");
        cart.clickAddToCart();
        cart.openCart();

        order.clickPlaceOrder();
        order.fillOrderForm("Test", "India", "City", "1234", "10", "2025");
        order.clickPurchase();

        String confirmation = order.getConfirmationText();

        Assert.assertTrue(confirmation.matches("(?s).*Id: \\d+.*"),"Order ID not present");
        order.confirmOK();
    }

    //Negative Test (empty fields)
    @Test
    public void verifyOrderWithEmptyFields() {
        ProductPage product=new ProductPage(getDriver());
        CartPage cart=new CartPage(getDriver());
        OrderPage order=new OrderPage(getDriver());

        product.openPhones();
        product.selectProductByName("Samsung galaxy s6");
        cart.clickAddToCart();

        cart.openCart();

        order.clickPlaceOrder();
        order.fillOrderForm("", "", "", "", "", "");
        order.clickPurchaseWithoutWait();

        String alertText=order.getAlertText();

        Assert.assertTrue(alertText.contains("Please fill out"),"Expected validation alert not shown");
        order.acceptAlert();
    }
}