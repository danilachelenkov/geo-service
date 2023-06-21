package ru.netology.i18n;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;

import java.util.stream.Stream;

public class LocalizationSenderImplTest {
    private LocalizationService localizationService;

    @BeforeEach
    public void beforeEachTests() {
        System.out.println("Инициализация эксземпляров перед выполненем каждого теста.");
        localizationService = new LocalizationServiceImpl();
    }

    @AfterEach
    public void afterEachTests() {
        System.out.println("Устанавливаем пустую ссылку для эксземпляров после выполнения каждого теста.\r\n");
        localizationService = null;
    }

    @ParameterizedTest
    @MethodSource("test_localizationService_returnMessageParams")
    public void test_localizationService_returnMessage(Country country, String expected) {

        String actual_message = localizationService.locale(country);
        Assertions.assertEquals(expected,actual_message);
    }

    public static Stream<Arguments> test_localizationService_returnMessageParams() {
        return Stream.of(
                Arguments.of(Country.USA, "Welcome"),
                Arguments.of(Country.RUSSIA, "Добро пожаловать"),
                Arguments.of(Country.BRAZIL, "Welcome"),
                Arguments.of(Country.GERMANY, "Welcome")
        );
    }

  @Test
    public void test_CheckException_thenCorrect() {
       Throwable exception = Assertions.assertThrows( NullPointerException .class, () -> localizationService.locale(null));
    }

}
