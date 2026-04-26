package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProductPage;

import java.util.List;

public class ProductTest extends BaseTest {

    //Phones category → products listed
    @Test
    public void verifyPhonesCategoryProducts() {
        ProductPage product=new ProductPage(getDriver());
        product.openPhones();

        int count=product.getProductCount();
        Assert.assertTrue(count > 0, "No products shown under Phones");

        List<String> names = product.getProductNames();
        Assert.assertFalse(names.isEmpty(), "Product names list is empty");
    }

    //Laptops category → products listed
    @Test
    public void verifyLaptopsCategoryProducts() {
        ProductPage product=new ProductPage(getDriver());
        product.openLaptops();

        int count=product.getProductCount();
        Assert.assertTrue(count > 0, "No products shown under Laptops");

        List<String> names=product.getProductNames();
        Assert.assertFalse(names.isEmpty(), "Product names list is empty");
    }

    //Product details → name, price, description
    @Test
    public void verifyProductDetailsForSelectedProduct() {
        ProductPage product=new ProductPage(getDriver());
        product.openPhones();

        String selectedProduct="Samsung galaxy s6";

        product.selectProductByName(selectedProduct);

        String actualName=product.readProductName();
        Assert.assertEquals(actualName, selectedProduct,"Product name mismatch");

        String price = product.readProductPrice();
        Assert.assertTrue(price.contains("$"),"Product price not displayed");

        String description = product.readProductDescription();
        Assert.assertFalse(description.isEmpty(),"Product description is empty");
    }

    //Home reload → full product listing
    @Test
    public void verifyHomeReloadsAllProducts() {
        ProductPage product=new ProductPage(getDriver());
        product.openPhones();   
        product.clickHome();    

        int count=product.getProductCount();
        Assert.assertTrue(count > 0, "Home did not reload product list");
    }
}