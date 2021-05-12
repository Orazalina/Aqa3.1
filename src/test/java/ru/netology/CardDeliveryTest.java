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
    private Faker faker;

    @BeforeAll
    static void setupAll() {
        Configuration.browser = "firefox";
    }

    @BeforeEach
    void setUpAll() {
        faker = new Faker(new Locale("ru"));

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

    @Test
    void shouldAddFalseCity() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("К");
        $("[data-test-id=date] [placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE, when(false));
        $("[data-test-id=name] input").setValue("Иван Петров");
        $("[data-test-id=phone] input").setValue("+77777777777");
        $("[data-test-id=agreement] .checkbox__box").click();
        $(".button").click();
        $("[data-test-id=city] .input__sub").shouldBe(Condition.visible, Duration.ofSeconds(15)).shouldHave(Condition.exactText("Доставка в выбранный город недоступна"));
    }
}
