package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Locators
    private By fromCity = By.id("fromCity");
    private By toCity = By.id("toCity");
    private By cityInput = By.xpath("//input[@placeholder='From' or @placeholder='To']");
    private By departure = By.xpath("//label[@for='departure']");
    private By searchButton = By.xpath("//a[contains(text(),'Search')]");
    private By loginModalClose = By.cssSelector(".loginModal.displayBlock .langCardClose");

    // Methods
    public void closeLoginPopupIfPresent() {
        try {
            WebDriverWait popupWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement close = popupWait.until(ExpectedConditions.visibilityOfElementLocated(loginModalClose));
            close.click();
        } catch (Exception e) {
            // Try ESC key as a fallback
            try {
                new Actions(driver).sendKeys(Keys.ESCAPE).build().perform();
                System.out.println("ESC key sent to close popup.");
            } catch (Exception ex) {
                System.out.println("Login popup not found or already closed.");
            }
        }
    }

    public void activatePageByClickingAnywhere() {
        try {
            new Actions(driver).moveByOffset(10, 10).click().build().perform();
        } catch (Exception e) {
            System.out.println("Page activation click failed.");
        }
    }

    public void enterSourceCity(String city) {
        wait.until(ExpectedConditions.elementToBeClickable(fromCity)).click();
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(cityInput));
        input.clear();
        input.sendKeys(city);
        new Actions(driver).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    public void enterDestinationCity(String city) {
        wait.until(ExpectedConditions.elementToBeClickable(toCity)).click();
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(cityInput));
        input.clear();
        input.sendKeys(city);
        new Actions(driver).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    public void selectDepartureDate() {
        wait.until(ExpectedConditions.elementToBeClickable(departure)).click();
        // Select first available date (change this logic if needed)
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='DayPicker-Day'])[1]"))).click();
    }

    public void clickSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }
}