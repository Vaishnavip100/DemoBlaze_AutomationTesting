package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProductPage;
import pages.CartPage;

public class CartTest extends BaseTest {

    //Add one product → verify name & price
    @Test
    public void verifyAddSingleProductToCart() {
        ProductPage product=new ProductPage(getDriver());
        CartPage cart=new CartPage(getDriver());

        String productName="Samsung galaxy s6";

        product.openPhones();
        product.selectProductByName(productName);
        
        int productPrice=product.getProductPriceValue();
        
        cart.clickAddToCart();
        cart.openCart();

        Assert.assertTrue(cart.isProductInCart(productName),"First product missing");
        Assert.assertTrue(cart.isProductInCart(productName),"Product not found in cart"); 
        
        int cartPrice = cart.getProductPriceFromCart(productName); 
        Assert.assertEquals(cartPrice, productPrice,"Price mismatch between product page and cart");
    }

    //Add two products → verify both present
    @Test
    public void verifyAddTwoProductsToCart() {

        ProductPage product=new ProductPage(getDriver());
        CartPage cart=new CartPage(getDriver());

        String product1="Samsung galaxy s6";
        String product2="Sony vaio i5";

        product.openPhones();
        product.selectProductByName(product1);
        cart.clickAddToCart();

        product.goHome();
        product.openLaptops();
        product.selectProductByName(product2);
        cart.clickAddToCart();

        cart.openCart();

        Assert.assertTrue(cart.isProductInCart(product1),"First product missing");
        Assert.assertTrue(cart.isProductInCart(product2),"Second product missing");
    }

    //Delete one product → verify removed
    @Test
    public void verifyDeleteProductFromCart() {
        ProductPage product=new ProductPage(getDriver());
        CartPage cart=new CartPage(getDriver());

        String productName="Samsung galaxy s6";

        product.openPhones();
        product.selectProductByName(productName);
        
        cart.clickAddToCart();
        cart.openCart();

        Assert.assertTrue(cart.isProductInCart(productName),"Product not added");

        cart.deleteProductByName(productName);

        Assert.assertFalse(cart.isProductInCart(productName),"Product still present after deletion");
    }

    //Verify total price updates after deletion
    @Test
    public void verifyTotalPriceAfterDeletion() {
        ProductPage product=new ProductPage(getDriver());
        CartPage cart=new CartPage(getDriver());

        String product1="Samsung galaxy s6";
        String product2="Sony vaio i5";

        product.openPhones();
        product.selectProductByName(product1);
        cart.clickAddToCart();

        product.goHome();

        product.openLaptops();
        product.selectProductByName(product2);
        cart.clickAddToCart();

        cart.openCart();

        Assert.assertTrue(cart.isProductInCart(product1),"Product1 missing");
        Assert.assertTrue(cart.isProductInCart(product2),"Product2 missing");

        int totalBefore=cart.getTotalPrice();

        cart.deleteProductByName(product1);

        int totalAfter=cart.getTotalPrice();

        Assert.assertTrue(totalAfter<totalBefore,"Total price did not update after deletion");
    }
}