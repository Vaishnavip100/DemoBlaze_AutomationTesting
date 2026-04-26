package listeners;

import base.BaseTest;
import utils.ExtentManager;
import utils.ScreenshotUtil;

import com.aventstack.extentreports.*;
import org.openqa.selenium.WebDriver;
import org.testng.*;

public class TestListener implements ITestListener {
    ExtentReports extent=ExtentManager.getInstance();
    ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
        test=extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver=BaseTest.getDriver();
        String path=ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName());

        test.fail(result.getThrowable());

        try {
            test.addScreenCaptureFromPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}