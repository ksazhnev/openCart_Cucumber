package pageObjects;
//Test Git modification 

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountRegistrationPage {
    WebDriver driver;

    public AccountRegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "firstname")
    WebElement txtFirstName;

    @FindBy(name = "lastname")
    WebElement txtLastName;

    @FindBy(name = "email")
    WebElement txtEmail;

    @FindBy(name = "telephone")
    WebElement txtTelephone;

    @FindBy(name = "password")
    WebElement txtPassword;

    @FindBy(name = "confirm")
    WebElement txtConfirmPassword;

    @FindBy(name = "agree")
    WebElement chkPrivacyPolicy;

    @FindBy(xpath = "//input[@value='Continue']")
    WebElement btnContinue;

    @FindBy(xpath = "//h1[normalize-space()='Register Account']")
    WebElement msgRegister;

    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement msgConfirmation;

    public void setFirstName(String fname) {
        txtFirstName.sendKeys(fname);
    }

    public void setLastName(String lname) {
        txtLastName.sendKeys(lname);
    }

    public void setEmail(String email) {
        txtEmail.sendKeys(email);
    }

    public void setTelephone(String tel) {
        txtTelephone.sendKeys(tel);
    }

    public void setPassword(String pwd) {
        txtPassword.sendKeys(pwd);
    }

    public void setPasswordConfirm(String cnfpwd) {
        txtConfirmPassword.sendKeys(cnfpwd);
    }


    public void setPrivacyPocily() {
        chkPrivacyPolicy.click();
    }

    public void clickContinue() {
        btnContinue.click();
    }

    public boolean isRegisterPageDisplayed() {
        try {
            return (msgRegister.isDisplayed());
        } catch (Exception e) {
            return (false);
        }
    }

    public String getConfirmationMsg() {
        try {
            return (msgConfirmation.getText());
        } catch (Exception e) {
            return (e.getMessage());
        }
    }
}
