package ru.netology;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {}

    public static class Registration {
        private Registration() {}

        public static RegistrationByCustomerInfo generateByInfo() {
            Faker faker = new Faker (new Locale("ru"));
            return new RegistrationByCustomerInfo(
                    faker.city().city(),
                    faker.name().fullName(),
                    faker.phone().phoneNumber());
        }

        public static String forwardDate(int plusDays) {
            LocalDate today = LocalDate.now();
            LocalDate newDate = today.plusDays(plusDays);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            return formatter.format(newDate);
        }
    }
}
