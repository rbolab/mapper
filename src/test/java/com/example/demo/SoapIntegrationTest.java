package com.example.demo;

import com.example.demo.service.FolioDataFinder;
import com.example.demo.utils.XmlUtils;
import com.example.demo.utils.XmlUtilsException;
import de.codecentric.namespace.weatherservice.general.GetCityForecastByZIP;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SoapIntegrationTest {

    @Value(value="classpath:soap/GetCityForecastByZIPTest.xml")
    private Resource xmlSoapResponse;

    @MockBean
    private FolioDataFinder folioDataFinder;

    @Before
    public void setUp() throws Exception {
        GetCityForecastByZIP getCityForecastByZIP = XmlUtils.readSoapMessageFromStreamAndUnmarshallBody2Object(xmlSoapResponse.getInputStream(), GetCityForecastByZIP.class);
        given(folioDataFinder.callSoapWS(anyInt())).willReturn(getCityForecastByZIP);
    }

    @Test
    public void testXmlUnmarshalling() throws IOException, XmlUtilsException {
        GetCityForecastByZIP getCityForecastByZIP = folioDataFinder.callSoapWS(2);
        assertEquals("bluewhite", getCityForecastByZIP.getForecastRequest().getFlagcolor());

        System.out.println("coucouc");

    }
}
