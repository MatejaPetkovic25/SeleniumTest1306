package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoggedInPage {
    WebDriver driver;
    WebDriverWait wdwait;
    WebElement notification;
    WebElement logoutButton;
    WebElement welcomeMessage;

    public LoggedInPage(WebDriver driver, WebDriverWait wdwait) {
        this.driver = driver;
        this.wdwait = wdwait;
    }

    public WebElement getNotification() {
        return driver.findElement(By.cssSelector(".flash.success"));
        //OVde sam preko cssSelectora trazio da bi pronadjena poruka bila zelena, uspesna notifikacija
        //Ako se korisnik uloguje dobice uspesno obavestenje, da sam ostavio samo id flash program bi pronasao samo
        //notifikaciju svejedno da li je crvena ili zelena pa bi prosao Assert o vidljivosti poruke cak i kada je ona pogresna
    }

    public WebElement getLogoutButton() {
        return driver.findElement(By.cssSelector(".button.secondary.radius"));
    }

    public WebElement getWelcomeMessage() {
        return driver.findElement(By.className("subheader"));
    }

    //Nakon getera i setera ovde takodje ispisujemo metode, s tim sto je jedina korisna metoda za ovu stranicu metoda za
    //logout

    public void logOut() {
        getLogoutButton().click();
    }
}
