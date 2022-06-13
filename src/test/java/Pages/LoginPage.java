package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wdwait;
    WebElement usernameBox;
    WebElement passwordBox;
    WebElement loginButton;
    WebElement loggedOutNotification;
    WebElement failedLoginNotification;
        //Obavezan je konstruktor sa driverom i webdriverwait-om (ako se koristi)
    public LoginPage(WebDriver driver, WebDriverWait wdwait) {
        this.driver = driver;
        this.wdwait = wdwait;
    }
        //Ovde krecu geteri za WebElemente koji se nalaze na ovoj stranici
    //Geteri nam sluze da lakse rukujemo sa navedenim WebElementima bez potrebe da ih inicijalizujemo
    public WebElement getUsernameBox() {
        return driver.findElement(By.id("username"));
    }

    public WebElement getPasswordBox() {
        return driver.findElement(By.id("password"));
    }

    public WebElement getLoggedOutNotification() {
        return driver.findElement(By.id("flash"));
    }

    public WebElement getFailedLoginNotification() {
        //Naknadno sam uocio da se crvene notifikacije pojavljuju pod flash error css-om, a zelene (uspesne) pod flash success
        //Nisam menjao prvu zato sto se u ovim testovima, na ovoj stranici pojavljuje samo jedna zelena i to nakon logout-a
        //To mi nece remetiti testove kada ocekujem crvenu notifikaciju, ali svejedno sam sledecu napravio da bude preciznija
        //U normalnim uslovima bih se oslanjao na tekst notifikacije, napravio bih listu web elemenata da razlikujem
        //notifikacije jednu od druge, ali zbog problema sa uporedjivanjem teksta odustao sam od te ideje jer svakako nece raditi
        //dok ne smislim kako da potrefim ocekivanu poruku sa onom koja se dobija
        return driver.findElement(By.cssSelector(".flash.error"));
    }

    public WebElement getLoginButton() {
        //Za ovaj geter nisam koristio klasu nego css, jer sam preko try catch bloka utvrdio da program hvata
        //taj lokator preko klase "radius" i kada je samo logout dugme prisutno
        return driver.findElement(By.cssSelector(".fa.fa-2x.fa-sign-in"));
    }
    //-------------------------------------------------------
    //Ovde krecu metode preko kojih se preglednije daju instrukcije na datoj stranici
    //Kada je rec o unosenju username-a i password-a, metodi prosledjujemo string koji ce se upisati u text box.
    //Potrebno je da se prvo odradi clear kako bismo bili sigurni da username i password saljemo u prazan text box
    public void enterUsername(String username) {
        getUsernameBox().clear();
        getUsernameBox().sendKeys(username);
    }

    public void enterPassword(String password) {
        getPasswordBox().clear();
        getPasswordBox().sendKeys(password);
    }
    //Ovo je jednostavna metoda koja ce da izvrsi klik na login dugme
    public void clickOnLoginButton() {
        getLoginButton().click();
    }

    //Dosetio sam se da moze da se napravi metoda koja ce vracati String iz notifikacije
    public String actualNotification() {
        return getFailedLoginNotification().getText();
    }
}
