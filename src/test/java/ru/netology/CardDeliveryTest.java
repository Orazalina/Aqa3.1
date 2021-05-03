package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {

    @Test
    void allCorrect() {
        open("http://localhost:9999");
        //$("").click();
        //$("").setValue("Казань");
        //$("").setValue("31.10.2019");
        //$("").setValue("Иван Петров");
        //$("").setValue("+77777777777");
        //$("").click();
        //$("").click();




    }
}
