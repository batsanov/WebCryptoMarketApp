package com.example.cryptoWebsite.prices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PricesController {

    private static Map<String, Double> currentPrices;
    private PricesService pricesService;

    @Autowired
    public PricesController(PricesService pricesService) {
        this.pricesService = pricesService;
    }

    @Scheduled(fixedDelay = 15000)
    public Map<String, Double> getCryptoPrices(){
        currentPrices = pricesService.getInformation();
       return  currentPrices;
    }

    public Map<String, Double> getCurrentPrices() {
        return currentPrices;
    }
}
