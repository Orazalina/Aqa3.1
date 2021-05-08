package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.WebDriverProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.Keys.CONTROL;
import static org.openqa.selenium.Keys.DELETE;

public class CardDeliveryTest {

    @BeforeAll
    static void setupAll() {
        Configuration.browser = "firefox";
    }

    @BeforeEach
    void setUp() {
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
    }

    @NotNull
    private String when(boolean trim) {
        Calendar c = new GregorianCalendar();
            c.add(Calendar.DATE, 7);
            if (trim) {
                return new SimpleDateFormat("d").format(c.getTime());
            } else {
                return new SimpleDateFormat("dd.MM.yyyy").format(c.getTime());
            }
    }

    @Test
    void allCorrect() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Казань");
        $("[data-test-id=date] [placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE, when(false));
        $("[data-test-id=name] input").setValue("Иван Петров");
        $("[data-test-id=phone] input").setValue("+77777777777");
        $("[data-test-id=agreement] .checkbox__box").click();
        $(".button").click();
        $("[data-test-id=notification]").shouldBe(Condition.visible, Duration.ofSeconds(15)).shouldHave(Condition.exactText("Успешно! Встреча успешно забронирована на " + when(false)));

    }
}
