package ru.netology.geo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.lang.reflect.Executable;
import java.math.BigDecimal;
import java.util.stream.Stream;

public class GeoServiceImplTest {
    private GeoService geoService;

    @BeforeEach
    public void beforeEachTests() {
        System.out.println("Инициализация эксземпляров перед выполненем каждого теста.");
        geoService = new GeoServiceImpl();
    }

    @AfterEach
    public void afterEachTests() {
        System.out.println("Устанавливаем пустую ссылку для эксземпляров после выполнения каждого теста.\r\n");
        geoService = null;
    }

    @ParameterizedTest
    @MethodSource("test_byIp_returnLocationParams")
    public void test_byIp_returnLocation(String ipAdress, Location location_expected) {
        Location location_actual = geoService.byIp(ipAdress);
        Assertions.assertEquals(location_expected,location_actual);
    }

    public static Stream<Arguments> test_byIp_returnLocationParams() {
        return Stream.of(
                Arguments.of(GeoServiceImpl.LOCALHOST, new Location(null, null, null, 0)),
                Arguments.of(GeoServiceImpl.MOSCOW_IP, new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of(GeoServiceImpl.NEW_YORK_IP, new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("172.1.2.110", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.4.18.49", new Location("New York", Country.USA, null, 0))
        );
    }

    @Test
    public void CheckOnNull_UnknownAdress_thenCorrect() {
        Location location_actual = geoService.byIp("10.12.10.1");
        Assertions.assertNull(location_actual);
    }
}
