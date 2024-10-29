package Steps;

import Pages.HomepagePage;
import Pages.LoginPage;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class StepDefinition {

    public static WebDriver driver;
    LoginPage loginPage;
    HomepagePage homepagePage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        loginPage = new LoginPage();
        homepagePage = new HomepagePage();

    }

    //---------------------------GIVEN

    @Given("user is on login page")
    public void userIsOnLoginPage() {
        driver.navigate().to("https://www.saucedemo.com/");
    }

    //----------------------------WHEN

    @When("user inputs valid username")
    public void userInputsValidUsername() {
        loginPage.inputUsername("standard_user");
    }

    //----------------------------AND

    @And("user inputs valid password")
    public void userInputsValidPassword() {
        loginPage.inputPassword("secret_sauce");
    }

    @And("user clicks on Login button")
    public void userClicksOnLoginButton() {
        loginPage.clickOnLoginButton();
    }

    //----------------------------THEN

    @Then("user is logged in")
    public void userIsLoggedIn() {
        Assert.assertTrue(homepagePage.cartButton.isDisplayed());
    }

    @And("redirected to homepage")
    public void redirectedToHomepage() {
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }
}
