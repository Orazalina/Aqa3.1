package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.WebDriverProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.Keys.CONTROL;
import static org.openqa.selenium.Keys.DELETE;

public class CardDeliveryTest {

    private LocalDate plusDays(int n) {
        LocalDate date = LocalDate.now();
        date = date.plusDays(n);

        return date;
    }


    @Test
    void allCorrect() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Казань");
        $("[placeholder='Дата встречи']").sendKeys(CONTROL + "a", DELETE);
        String newDate = plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder='Дата встречи']").setValue(newDate);
        $("[data-test-id=name] input").setValue("Иван Петров");
        $("[data-test-id=phone] input").setValue("+77777777777");
        $("[data-test-id=agreement] .checkbox__box").click();
        $(".button").click();
        $(Selectors.withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(10));
    }
}
