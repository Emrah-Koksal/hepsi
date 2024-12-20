package com.hepsiBurada.pages;

import com.hepsiBurada.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.Random;

public abstract class BasePage {
    public BasePage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(css = "div .initialComponent-hk7c_9tvgJ8ELzRuGJwC")
    public WebElement searchBox;
    @FindBy(xpath = "//input[@data-test-id='search-bar-input']")
    public WebElement searchBoxInput;
    @FindBy(id = "onetrust-accept-btn-handler")
    public WebElement acceptCookieButton;

    @FindBy(css = ".moria-ProductCard-gyqBb")
    public List<WebElement> listedIphones;

    public void selectRandomIphone() {
        Random random = new Random();
        System.out.println("Listed size of iphones: " + listedIphones.size());
        int randomNo = random.nextInt(listedIphones.size());
        System.out.println(randomNo);
        listedIphones.get(randomNo).click();

    }

    public void acceptCookies() {

        try {
            acceptCookieButton.click();
            System.out.println("Cookies are accepted");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
