package com.hepsiBurada.step_definitions;

import com.hepsiBurada.utilities.ConfigurationReader;
import com.hepsiBurada.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.restassured.RestAssured;


import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class Hooks {


//    @Before
//    public void setUp() {
//        Driver.get().get(ConfigurationReader.get("url"));
//        Driver.get().manage().window().maximize();
//        Driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");
        }

        Driver.closeDriver();
    }

    @Before("@api")
    public void api() {
        RestAssured.baseURI = ConfigurationReader.get("apiUrl");
    }

}
