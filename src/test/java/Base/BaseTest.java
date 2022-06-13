package Base;

import Pages.LoggedInPage;
import Pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    public WebDriver driver;
    public WebDriverWait wdwait;
    public ExcelReader excelReader;
    public LoginPage loginPage;
    public LoggedInPage loggedInPage;

    //Sledi metoda koja se izvrsava na samom pocetku programa, koristi se kako sam naziv kaze za setup, dakle instalira se chromedriver,
    //deklarisu se driver i webdriverwait, zatim excelReader gde navodimo iz kog excel dokumenta vadimo podatke i na kraju
    //deklarisemo svaku stranicu posebno
    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wdwait = new WebDriverWait(driver, Duration.ofSeconds(10));
        excelReader = new ExcelReader("C:\\Users\\Korisnik\\Desktop\\SeleniumTest.xlsx");
        loginPage = new LoginPage(driver, wdwait);
        loggedInPage = new LoggedInPage(driver, wdwait);
    }

    //Naredne tri metode sam ispisao za svaki slucaj odmah na pocetku testa, da bi se potom ispostavilo da mi nisu bile
    //potrebne. kada bih hteo da iskoristim neku pisao bih u testu gde treba nesto da se saceka ovako:
    //visibilityWait(npr. loggedInPage.getNotification)
    //Program bi sacekao da se pojavi navedena notifikacija i potom bi izvrsio Assertove ili sta treba dalje

    public void visibilityWait(WebElement element) {
        wdwait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clickabiltyWait(WebElement element) {
        wdwait.until(ExpectedConditions.elementToBeClickable(element));
    }

    //Ovo je metoda za skrolovanje mada je ovde apsolutno nepotrebna jer na stranicama ni ne moze da se skroluje
    public void scrollIntoView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    //Standardna afterClass metoda koja se izvrsava na samom kraju svih testova, gde se chrome gasi
    @AfterClass
    public void tearDown() {
        driver.close();
        driver.quit();
    }




}
