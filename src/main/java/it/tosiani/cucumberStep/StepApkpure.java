package it.tosiani.cucumberStep;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import cucumber.api.java.*;
import cucumber.api.java.en.Given;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import it.tosiani.drivers.ManagmentDriver;
import it.tosiani.utility.Utils;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.Properties;

import static it.tosiani.utility.GlobalParameters.*;

public class StepApkpure {
    static private WebElement webElement = null;
    static private AndroidDriver<?> driver = null;
    static private DesiredCapabilities desiredCapabilities = null;
    static private Properties prop = null;

    @Before
    public void doSomethingBeforeStep(){
        desiredCapabilities = new DesiredCapabilities();

        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, " emulator-5554 ");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, RESOURCES_PATH + File.separator + "APKPure_v3.17.22_apkpure.com" + EXT_ANDROID);

        ManagmentDriver.startAndroidDriver(desiredCapabilities);
        driver = ManagmentDriver.getAndroidDriver();
        prop = Utils.loadProp("apkPure");
    }


    @Given("Open and accept cookie")
    public void accept() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.id(prop.getProperty("btn.no.cookie"))).click();
        Thread.sleep(10000);
    }


    @After
    public void doSomethingAfter(){
        ManagmentDriver.stopDriver();
    }
}
