package com.epam.hackathon.emotional_help.testing.api.dataProviders;

import com.epam.hackathon.emotional_help.testing.api.utils.PropertiesReader;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class TestDataProvider {
    private final PropertiesReader propertiesReader = PropertiesReader
            .getInstance("testData.properties");

    public String getPropertyValue(String propertyName) {
        return propertiesReader.getPropety(propertyName);
    }
}
