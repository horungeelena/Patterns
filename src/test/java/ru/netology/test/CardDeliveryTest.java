package ru.netology.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.data.DataGenerator;
import ru.netology.data.RegistrationByCustomerInfo;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldDeliveryByCardForDifferentDate() {
        RegistrationByCustomerInfo user = DataGenerator.Registration.generateByCustomer("ru");
        $("[data-test-id='city'] input").setValue(user.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        $("[data-test-id='date'] input").setValue(DataGenerator.Registration.forwardDate(3));
        $("[data-test-id='name'] input").setValue(DataGenerator.Registration.generateByCustomer("ru").getFullName());
        $("[data-test-id='phone'] input").setValue(DataGenerator.Registration.generateByCustomer("ru").getPhoneNumber());
        $("[data-test-id='agreement']").click();
        $$("[type='button']").find(exactText("Запланировать")).click();
        $(withText(DataGenerator.Registration.forwardDate(5)));
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        $("[data-test-id='date'] input").setValue(DataGenerator.Registration.forwardDate(5));
        $$("[type='button']").find(exactText("Запланировать")).click();
        $$("[type='button']").find(exactText("Перепланировать")).click();
        $(byText(DataGenerator.Registration.forwardDate(5))).shouldHave(text("Встреча успешно запланирована на " + DataGenerator.Registration.forwardDate(5)))
                .shouldBe(Condition.visible,Duration.ofMillis(15000));
    }
}
