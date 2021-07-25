package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.util.SystemOutLogger;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataReader;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;


public class steps {

    WebDriver driver;
    HomePage hp;
    LoginPage lp;
    AccountRegistrationPage regpage;
    MyAccountPage myAccount;
    public List<HashMap<String, String>> datamap;
    Logger logger; //for logging
    ResourceBundle rb; //for reading properties file
    String browser;

    @Before
    public void setup() {
        logger = LogManager.getLogger(this.getClass()); // for logging
        rb = ResourceBundle.getBundle("config");
        browser = rb.getString("browser");

    }
    /*@After
    public void tearDown(Scenario scenario){
        System.out.println("Scenario status ======>"+scenario.getStatus());
        if(scenario.isFailed()){
            byte [] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png", scenario.getName());
        }
    }*/
    @Given("user launches the browser")
    public void user_launches_the_browser() {
        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            logger.info("ChromeDriver is initiated");
        } else if (browser.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            logger.info("EdgeDriver is initiated");
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            logger.info("FirefoxDriver is initiated");

        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Given("opens URL {string}")
    public void opens_url(String url) {
        driver.get(url);
        driver.manage().window().maximize();
        logger.info("Opened URL");

    }

    @When("user navigates to My Account menu")
    public void user_navigate_to_my_account_menu() {
        hp = new HomePage(driver);
        hp.clickMyAccount();
        logger.info("Clicked on My Account");

    }

    @When("click on Login")
    public void click_on_login() {
        hp.clickLogin();
        logger.info("Clicked on login from home page");

    }

    @When("User enters Email as {string} and Password as {string}")
    public void user_enters_email_as_and_password_as(String email, String pwd) {
        lp = new LoginPage(driver);
        lp.setEmailAddress(email);
        logger.info("Entered email address");
        lp.setPassword(pwd);
        logger.info("Entered password");

    }

    @When("Click on Login button")
    public void click_on_login_button() {
        lp.clickLogin();
        logger.info("Clicked on login button");

    }

    @Then("user navigates to My Account page")
    public void user_navigates_to_my_account_page() {

        boolean check = lp.isMyAccountPageExists();

        driver.quit();
        if (check) {
            logger.info("Login successful, opened my account page");
            Assert.assertTrue(true);
        } else {
            logger.error("Login failed, my account page was not opened");
            Assert.fail();
        }

    }

    @Then("User navigates to MyAccount Page with provided credentials from Excel file's rows {string}")
    public void user_navigates_to_my_account_page_with_provided_credentials_from_excel_file_s_rows(String row) {

        datamap = DataReader.data(".//testData/Opencart_LoginData.xlsx", "Sheet1");

        int index = Integer.parseInt(row) - 1;
        System.out.println(index);
        String email = datamap.get(index).get("username");
        String pwd = datamap.get(index).get("password");
        String result = datamap.get(index).get("res");


        lp = new LoginPage(driver);
        lp.setPassword(pwd);
        lp.setEmailAddress(email);
        lp.clickLogin();
        try {
            System.out.println(result);
            if (result.equals("Valid")) {
                if (lp.isMyAccountPageExists()) {
                    myAccount = new MyAccountPage(driver);
                    myAccount.clickLogOut();
                    Assert.assertTrue(true);
                } else {
                    Assert.fail();
                }
                if (result.equals("Invalid")) {
                    if (lp.isMyAccountPageExists()) {
                        myAccount = new MyAccountPage(driver);
                        myAccount.clickLogOut();
                        Assert.fail();
                    } else {
                        Assert.assertTrue(true);

                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Assert.fail();
        }
        driver.quit();

    }

    // Account registration


    @When("click on Register")
    public void click_on_register() {
        hp.clickRegister();
        logger.info("Clicked on register button, from home page");
    }

    @Then("Register Account page loads")
    public void register_account_page_loads() {
        regpage = new AccountRegistrationPage(driver);
        if (regpage.isRegisterPageDisplayed()) {
            logger.info("Register page loaded");
            Assert.assertTrue(true);
        } else {
            logger.error("Fail, register page was not loaded");
            Assert.fail();


        }

    }

    @When("user provide valid register information")
    public void user_provide_valid_register_information() {
        regpage.setFirstName("Cindy");
        logger.info("Entered first name");

        regpage.setLastName("Kennedy");
        logger.info("Entered last name");

        regpage.setEmail(RandomStringUtils.randomAlphabetic(5) + "@gmail.com"); // Random email
        logger.info("Entered email");

        regpage.setTelephone("2345654345");
        logger.info("Entered the phone");
        regpage.setPassword("123456");
        logger.info("Entered password");
        regpage.setPasswordConfirm("123456");
        logger.info("Entered confirm phone");
        regpage.setPrivacyPocily();
        logger.info("Checked privacy checked");
    }

    @When("click continue")
    public void click_continue() {
        regpage.clickContinue();
        logger.info("Clicked continue");
    }

    @Then("user should see {string} message")
    public void user_should_see_message(String msg) {
        System.out.println(regpage.isRegisterPageDisplayed());

        if (regpage.getConfirmationMsg().equals(msg)) {
            logger.info("Registration is successful, registration page is loaded");
            driver.quit();
            Assert.assertTrue(true);
        } else {
            logger.error("Registration is failed, registration page is not loaded");
            driver.quit();
            Assert.assertTrue(false);

        }
    }
}


