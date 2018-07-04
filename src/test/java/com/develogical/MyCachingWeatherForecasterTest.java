package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Region;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MyCachingWeatherForecasterTest {
    @Test
    public void testFetchTemperature()
    {
        MyForecaster delegate = Mockito.mock(MyForecaster.class);
        MyForecaster f = new CachingWeatherForecaster( delegate );
        Forecast l1 = f.forecastFor( Region.LONDON, Day.MONDAY );
        verify(delegate, times(1) ).forecastFor(Region.LONDON, Day.MONDAY );
    }

    @Test
    public void testCacheFetchTemperature() {
        MyForecaster delegate = Mockito.mock(MyForecaster.class);
        MyForecaster f = new CachingWeatherForecaster(delegate);
        Forecast l1 = f.forecastFor(Region.LONDON, Day.MONDAY);
        Forecast l2 = f.forecastFor(Region.LONDON, Day.TUESDAY);
        Forecast l3 = f.forecastFor(Region.LONDON, Day.MONDAY);
        verify(delegate, times(1)).forecastFor(Region.LONDON, Day.MONDAY);
        verify(delegate, times(1)).forecastFor(Region.LONDON, Day.TUESDAY);
        verify(delegate, never()).forecastFor(Region.EDINBURGH, Day.SUNDAY);
    }

}
