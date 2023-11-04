package fourth.weather;


import org.junit.jupiter.api.Test;
import seminars.fourth.weather.WeatherReporter;
import seminars.fourth.weather.WeatherService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WeatherReporterTest {

    @Test
    void testWeatherTemperature() {
        WeatherService weatherService = mock(WeatherService.class);
        when(weatherService.getCurrentTemperature()).thenReturn(23);
        WeatherReporter weatherReporter = new WeatherReporter(weatherService);
        assertEquals("Текущая температура: 25 градусов.",
                weatherReporter.generateReport());
    }

}