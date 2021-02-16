package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.time.Duration;


import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {

    @Test
    void shouldDeliveryByCardForDifferentDate() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue(DataGenerator.Registration.generateByCustomer("ru").getCity());
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(DataGenerator.Registration.forwardDate(4));
        $("[data-test-id=name] input").setValue(DataGenerator.Registration.generateByCustomer("ru").getFullName());
        $("[data-test-id=phone] input").setValue(DataGenerator.Registration.generateByCustomer("ru").getPhoneNumber());
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $(withText("Успешно")).shouldNotBe(Condition.visible);
        $("[data-test-id='notification'] .notification__content").shouldHave(exactText("Встреча успешно забронирована на " +DataGenerator.Registration.forwardDate(4)));
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(DataGenerator.Registration.forwardDate(3));
        $("button.button").click();
        $(withText("Необходимо подтверждение")).shouldBe(Condition.visible, Duration.ofMillis(15000));
        $("[data-test-id=replan-notification] .button").click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofMillis(15000));
        $("[data-test-id=success-notification] .notification__content").shouldHave(exactText("Встреча " + "успешно запланирована на "+DataGenerator.Registration.forwardDate(3)));
    }
}
