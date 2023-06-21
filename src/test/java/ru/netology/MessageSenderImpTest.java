package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class MessageSenderImpTest {

    @ParameterizedTest
    @MethodSource("test_messageSender_other_testLanguageRusParams")
    public void test_messageSender_other_testRusLanguage(String ipAdress, Location location, String expected) {

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipAdress);

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(Mockito.anyString())).thenReturn(location);

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Mockito.any(Country.class))).thenReturn(expected);

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        String actual = messageSender.send(headers);

        Assertions.assertEquals(expected, actual);
    }

    public static Stream<Arguments> test_messageSender_other_testLanguageRusParams() {
        return Stream.of(
                Arguments.of("174.1.32.11", new Location("Moscow", Country.RUSSIA, "Polbina", 1), "Добро пожаловать"),
                Arguments.of("174.65.11.21", new Location("Moscow", Country.RUSSIA, "Polyanka", 1), "Добро пожаловать"),
                Arguments.of("174.10.31.10", new Location("Moscow", Country.RUSSIA, "Kremlin", 1), "Добро пожаловать"),
                Arguments.of("174.12.12.31", new Location("Moscow", Country.RUSSIA, "Tverskaya", 1), "Добро пожаловать")
        );
    }


    @ParameterizedTest
    @MethodSource("test_messageSender_other_testLanguageUsaParams")
    public void test_messageSender_other_testUsaLanguage(String ipAdress, Location location, String expected) {

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipAdress);

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(Mockito.anyString())).thenReturn(location);

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Mockito.any(Country.class))).thenReturn(expected);

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        String actual = messageSender.send(headers);

        Assertions.assertEquals(expected, actual);
    }

    public static Stream<Arguments> test_messageSender_other_testLanguageUsaParams() {
        return Stream.of(
                Arguments.of("96.45.183.149", new Location("York", Country.USA, null, 2), "Welcome"),
                Arguments.of("96.55.81.144", new Location("York", Country.USA, null, 2), "Welcome"),
                Arguments.of("96.4.100.166", new Location("York", Country.USA, null, 2), "Welcome")
        );
    }
}
