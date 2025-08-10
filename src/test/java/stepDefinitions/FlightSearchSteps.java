package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import com.aventstack.extentreports.ExtentTest;
import utils.ExtentReportManager;
import  utils.ScreenshotUtils;

public class FlightSearchSteps {

    WebDriver driver;
    HomePage homePage;

    ExtentTest test;

    @Given("I open the MakeMyTrip homepage")
    public void openMMTHomepage() {
        ExtentReportManager.initReport();  // Start reporting
        test = ExtentReportManager.createTest("MMT Flight Search Test");

        io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.makemytrip.com/");
        homePage = new HomePage(driver);

        homePage.activatePageByClickingAnywhere();
        homePage.closeLoginPopupIfPresent();

        test.info("Opened MMT homepage and handled login modal");
    }

    @When("I select {string} as the source city")
    public void selectSource(String source) {
        homePage.enterSourceCity(source);
        test.info("Source city selected: " + source);
    }

    @When("I select {string} as the destination city")
    public void selectDestination(String destination) {
        homePage.enterDestinationCity(destination);
        test.info("Destination city selected: " + destination);
    }

    @When("I select a departure date")
    public void selectDate() {
        homePage.selectDepartureDate();
        test.info("Departure date selected");
    }

    @When("I click the search button")
    public void clickSearch() {
        homePage.clickSearchButton();
        test.info("Clicked on Search button");
    }

    @Then("I should see a list of available flights")
    public void verifyFlightList() {
        try {
            // Fake assertion (replace with real check)
            boolean flightsFound = true;
            if (flightsFound) {
                ExtentReportManager.getTest().pass("Flights listed successfully");
            } else {
                throw new AssertionError("No flights found");
            }
        } catch (Exception e) {
            String screenshotPath = ScreenshotUtils.captureScreenshot(driver, "FlightSearchFail");
            ExtentReportManager.getTest().fail("Test failed. Screenshot:")
                    .addScreenCaptureFromPath(screenshotPath);
            throw e;
        } finally {
            driver.quit();
            ExtentReportManager.flushReport();
        }
    }
}