package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardDeliveryTest {
    private RegistrationByCardInfo faker;

    @BeforeAll
    static void setupAll() {
        Configuration.browser = "firefox";
    }

    @BeforeEach
    void setUpAll() {
        faker = DataGenerator.Registration.generateByCard("ru");

    }


    @Test
    void allCorrect() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue(faker.getCity());
        $("[data-test-id=date] [placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE, DataGenerator.Registration.generateDate());
        $("[data-test-id=name] input").setValue(faker.getName());
        $("[data-test-id=phone] input").setValue(faker.getPhone().toString());
        //$("[data-test-id=agreement] .checkbox__box").click();
        //$(".button").click();
        //$("[data-test-id=notification]").shouldBe(Condition.visible, Duration.ofSeconds(15)).shouldHave(Condition.exactText("Успешно! Встреча успешно забронирована на " + when(false)));

    }
}
