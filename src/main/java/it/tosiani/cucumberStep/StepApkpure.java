package it.tosiani.cucumberStep;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import cucumber.api.java.*;
import cucumber.api.java.en.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import it.tosiani.drivers.ManagmentDriver;
import it.tosiani.utility.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.List;
import java.util.Properties;

import static it.tosiani.utility.GlobalParameters.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

public class StepApkpure {
    private WebElement webElement = null;
    private AndroidDriver<?> driver = null;
    private DesiredCapabilities desiredCapabilities = null;
    private Properties prop = null;
    private String ERRORE = "";

    private boolean risultato;
    private String nameApp1 = "";
    private String nameApp2 = "po";
    private int i = 0;

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


    public String getERRORE() {
        return ERRORE;
    }

    public void setERRORE(String ERRORE) {
        this.ERRORE = ERRORE;
    }

    @Given("Open and accept cookie")
    public void accept(){
        try{
            Thread.sleep(5000);
            if (driver.findElement(By.id(prop.getProperty("btn.no.cookie"))).isDisplayed())
                driver.findElement(By.id(prop.getProperty("btn.no.cookie"))).click();
            Thread.sleep(2000);
            if (driver.findElement(By.id(prop.getProperty("btn.deny.permission"))).isDisplayed())
                driver.findElement(By.id(prop.getProperty("btn.deny.permission"))).click();
            Thread.sleep(500);
            if (driver.findElement(By.id(prop.getProperty("btn.deny.permission"))).isDisplayed())
                driver.findElement(By.id(prop.getProperty("btn.deny.permission"))).click();
            Thread.sleep(3000);
        }catch (NoSuchElementException | InterruptedException e) {
            setERRORE(e.getMessage());
        }
    }

    @Then("check if you get to the home screen")
    public void checkHome(){
        assertThat(driver.findElement(By.id(prop.getProperty("input.search.bar"))).isDisplayed(), is(true));
    }

    public boolean clickFirst(List<WebElement> elements){
        for (WebElement element: elements){
            element.click();
            return true;
        }
        return false;
    }

    @Given("^search for: (.*)$")
    public void search(String qry) throws InterruptedException {
        //accept();
        driver.findElement(By.id(prop.getProperty("input.search.bar"))).click();
        Thread.sleep(1000);
        driver.findElement(By.id(prop.getProperty("input.search.bar2"))).sendKeys(qry);
        Thread.sleep(1000);
        /*for (WebElement element: driver.findElements(By.className(prop.getProperty("class.element.search")))){
            element.click();
            break;
        }*/
        risultato = clickFirst(driver.findElement(By.className(prop.getProperty("class.block.search")))
                .findElements(By.className(prop.getProperty("class.element.search"))));
        Thread.sleep(5000);
    }

    @Then("check if it works")
    public void checkBoolean(){
        assertThat(risultato, is(true));
    }

    @Given("the store page")
    public void clickStore() throws InterruptedException {
        driver.findElement(By.xpath(prop.getProperty("xpath.page.store"))).click();
        Thread.sleep(1000);
    }

    @When("^selecting an item on first position$")
    public void clickItem() throws InterruptedException {
        nameApp1 = driver.findElement(By.className(prop.getProperty("class.block.store")))
                .findElements(By.className(prop.getProperty("class.element.store"))).get(3)
                .findElements(By.className(prop.getProperty("class.text"))).get(0).getText();
        Utils.getScreenshot();
        driver.findElement(By.className(prop.getProperty("class.block.store")))
                .findElements(By.className(prop.getProperty("class.element.store"))).get(3).click();
        Thread.sleep(5000);
        //System.out.println("nome1: "+nameApp1);
        nameApp2 = driver.findElement(By.id(prop.getProperty("id.title.element"))).getText();
        //System.out.println("nome2: "+nameApp2);
    }

    @Then("check if he clicked on the right item")
    public void checkString(){
        assertThat(nameApp1, is(nameApp2));
    }

    @When("click the install button")
    public void clickInstall() throws InterruptedException {
        driver.findElement(By.className(prop.getProperty("class.elements.search")))
                .findElements(By.className(prop.getProperty("class.element.result"))).get(2)
                .findElement(By.className("android.widget.ImageView")).click();
        Thread.sleep(5000);
    }

    @Then("check if it asks for confirmation")
    public void checkError() throws InterruptedException {
        driver.findElement(By.id(prop.getProperty("id.btn.install"))).click();
        Thread.sleep(1000);
        risultato = driver.findElement(By.id(prop.getProperty("id.controll.install"))).isDisplayed();
        System.out.println(risultato);
        checkBoolean();
    }

    @Then("check if it goes into login")
    public void checkLogin() throws InterruptedException {
        driver.findElement(By.id(prop.getProperty("id.btn.likes"))).click();
        Thread.sleep(1000);
        risultato = driver.findElement(By.id(prop.getProperty("id.signin.btn"))).isDisplayed();
        System.out.println(risultato);
        checkBoolean();
    }

    @Given("the comunity page")
    public void clickComunity() throws InterruptedException {
        driver.findElement(By.xpath(prop.getProperty("xpath.page.comunity"))).click();
        Thread.sleep(1000);
    }

    @When("click on add btn")
    public void clickAddComunity() throws InterruptedException {
        driver.findElement(By.id(prop.getProperty("id.btn.add"))).click();
        Thread.sleep(2000);
        Utils.getScreenshot();
        risultato = driver.findElement(By.xpath(prop.getProperty("xpath.layaut.add"))).isDisplayed();
    }

    @Given("the news page")
    public void clickNews() throws InterruptedException {
        driver.findElement(By.xpath(prop.getProperty("xpath.news"))).click();
        Thread.sleep(1000);
    }

    @When("print news on screen")
    public void printNews() throws InterruptedException {
        for (WebElement element: driver.findElement(By.className(prop.getProperty("class.news.block")))
                .findElements(By.className(prop.getProperty("class.news.element")))) {
            for (WebElement ele: element.findElements(By.className("android.widget.TextView"))){
                System.out.println(ele.getText());
            }
            Thread.sleep(2000);
            i++;
        }
        Utils.getScreenshot();
    }

    @Then("check if it found anything")
    public void checkInt(){
        assertThat(i,is(not(0)));
    }


    @After
    public void doSomethingAfter(){
        ManagmentDriver.stopDriver();
    }
}
