package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AuthPage;
import pages.HomePage;
import utils.ConfigReader;
import utils.ExcelUtil;

public class AuthTest extends BaseTest {
	ConfigReader config=new ConfigReader();

    @DataProvider(name="loginExcelData")
    public Object[][] getLoginData() {
        String path="src/test/resources/testdata/LoginData.xlsx";
        return ExcelUtil.getData(path, "Sheet1");
    }

    
    @Test(dataProvider="loginExcelData")
    public void verifyLogin(String username, String password, String expectedResult) {
        HomePage home=new HomePage(getDriver());
        AuthPage auth=new AuthPage(getDriver());
        home.clickLogin();
        auth.login(username, password);

        if (expectedResult.equalsIgnoreCase("success")) {
            String userText=home.getLoggedInUser();
            Assert.assertTrue(userText.contains("Welcome"),"Expected success but login failed for: " + username);
        } 
        else if (expectedResult.equalsIgnoreCase("failure")) {
            String alertText=auth.getAlertTextIfPresent();
            if (alertText!=null) {
                Assert.assertTrue(alertText.contains("Wrong")||alertText.contains("User"),"Unexpected alert: " + alertText);
                auth.acceptAlertIfPresent();

            } 
            else {
                Assert.assertFalse(home.isUserLoggedIn(),"Login should have failed but user is logged in");
            }
        }
    }

    //Logout Test
    @Test
    public void verifyLogout() {
        HomePage home=new HomePage(getDriver());
        AuthPage auth=new AuthPage(getDriver());
        home.clickLogin();
        auth.login(config.getUsername(), config.getPassword());

        Assert.assertTrue(home.getLoggedInUser().contains("Welcome"));

        home.clickLogout();
        home.waitForLogout();

        Assert.assertFalse(home.isUserLoggedIn(),"User still appears logged in after logout");
    }
    
    //Signup
    @Test
    public void verifySignup() {
        HomePage home=new HomePage(getDriver());
        AuthPage auth=new AuthPage(getDriver());
        home.clickSignUp();

        String username="user" + System.currentTimeMillis();
        auth.signup(username, "1234");

        String alertText = auth.getAlertTextIfPresent();

        Assert.assertTrue(alertText.contains("Sign up successful"),"Signup failed");
        auth.acceptAlertIfPresent();
    }
}