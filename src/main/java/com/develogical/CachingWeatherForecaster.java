package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Region;

import java.util.*;

public class CachingWeatherForecaster implements MyForecaster {
    private MyForecaster delegate = null;
    private HashMap<String,Forecast> cache = new HashMap<String,Forecast>();

    public CachingWeatherForecaster(MyForecaster delegate) {
        this.delegate = delegate;
    }

    @Override
    public Forecast forecastFor(Region region, Day day) {
        if ( delegate == null ) return null;

        String key = region.toString() + day.toString();
        if ( cache.containsKey(key) )
            return cache.get( key );

        Forecast f = delegate.forecastFor(region, day);
        cache.put( key, f );
        return f;
    }
}
