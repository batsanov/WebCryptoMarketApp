package com.example.cryptoWebsite.prices;

import ch.qos.logback.core.util.FixedDelay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PricesService {
    private PricesRestClient pricesRestClient;

    @Autowired
    public PricesService(PricesRestClient pricesRestClient) {
        this.pricesRestClient = pricesRestClient;
    }

    public Map<String, Double> getInformation() {
        return pricesRestClient.getCryptoInformation();
    }


}
