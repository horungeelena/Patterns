package ru.netology;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {}

    public static class Registration {
        private Registration() {}

        public static RegistrationByCustomerInfo generateByCustomer(String locale) {
            Faker faker = new Faker (new Locale("ru"));
            return new RegistrationByCustomerInfo(
                    faker.address().city(),
                    faker.name().fullName(),
                    faker.phoneNumber().phoneNumber());
        }

        public static String forwardDate(int plusDays) {
            LocalDate today = LocalDate.now();
            LocalDate newDate = today.plusDays(plusDays);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            return formatter.format(newDate);
        }
    }
}
