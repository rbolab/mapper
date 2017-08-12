package com.example.demo.service;

import com.example.demo.domain.KeyValue;
import de.codecentric.namespace.weatherservice.general.GetCityForecastByZIP;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FolioDataFinder {

    public List<List<KeyValue>> findAllTradinfAdvisorInfos() {
        List<List<KeyValue>> items = new ArrayList<>();

        List<KeyValue> keyValues = new ArrayList<>();
        keyValues.add(new KeyValue("setId", 1));
        keyValues.add(new KeyValue("setName", "WNT Fund"));
        items.add(keyValues);

        keyValues = new ArrayList<>();
        keyValues.add(new KeyValue("setId", 2));
        keyValues.add(new KeyValue("setName", "Bridgewater"));
        items.add(keyValues);

        return items;
    }



    public GetCityForecastByZIP callSoapWS(int i){
        return null;
    }

}
