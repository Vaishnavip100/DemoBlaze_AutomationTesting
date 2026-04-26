package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AuthPage;
import pages.HomePage;
import utils.ConfigReader;

public class FormValidationTest extends BaseTest {
	ConfigReader config=new ConfigReader();

    //Signup with existing username
    @Test
    public void verifySignupWithExistingUser() {
        HomePage home=new HomePage(getDriver());
        AuthPage auth=new AuthPage(getDriver());

        home.clickSignUp();
        auth.signup(config.getUsername(), config.getPassword());

        String alertText=auth.getAlertTextIfPresent();

        Assert.assertNotNull(alertText, "Alert not shown");
        Assert.assertTrue(alertText.toLowerCase().contains("already exist"),"Expected duplicate user alert but got: " + alertText);

        auth.acceptAlertIfPresent();
    }

    //Login with empty username
    @Test
    public void verifyLoginWithEmptyUsername() {
        HomePage home=new HomePage(getDriver());
        AuthPage auth=new AuthPage(getDriver());

        home.clickLogin();

        auth.login("", "1234");

        String alertText=auth.getAlertTextIfPresent();

        Assert.assertNotNull(alertText, "Alert not shown");
        Assert.assertTrue(alertText.toLowerCase().contains("please fill"),"Expected validation alert but got: " + alertText);

        auth.acceptAlertIfPresent();
    }

    //Verify modal fields retain values
    @Test
    public void verifySignupFieldsRetainValues() {
        HomePage home=new HomePage(getDriver());
        AuthPage auth=new AuthPage(getDriver());

        home.clickSignUp();

        String username="sampleUser";
        String password="1234";

        auth.enterSignupUsername(username);
        auth.enterSignupPassword(password);

        String actualUser = auth.getSignupUsernameValue();
        String actualPass = auth.getSignupPasswordValue();

        Assert.assertEquals(actualUser, username,"Username not retained in field");
        Assert.assertEquals(actualPass, password,"Password not retained in field");
    }
}