package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.$;

public class AppCardDeliveryTaskOneTest {

    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }
    @Test
    public void shouldBeSuccessfullyCompleted() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Ижевск");
        String planningDate = generateDate(3,"dd.MM.yyyy");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Задияров Дмитрий");
        $("[data-test-id=phone] input").setValue("+79100000000");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $(".notification__content]")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на "+ planningDate));

    }
}

