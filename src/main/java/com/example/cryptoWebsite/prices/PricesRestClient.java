package com.example.cryptoWebsite.prices;

import org.json.JSONObject;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

@Component
public class PricesRestClient {
    private static String apiKey = "43ab5758-8fa6-44ef-8f9c-cf2459aeba89";


    public Map<String, Double> getCryptoInformation() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri
                (URI.create("https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?id=1,1027,6636,5426"))
                .header("X-CMC_PRO_API_KEY",apiKey).build();
      return  client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
              .thenApply(this::parse)
                .join();
    }

    public Map<String, Double> parse(String responseBody) {
        Map<String, Double> result = new LinkedHashMap<String, Double>();
        final String [] coinIds = {"1", "1027", "6636", "5426"} ;

        JSONObject cryptoTest = new JSONObject(responseBody);
        JSONObject cryptos =  cryptoTest.getJSONObject("data");

        for (int i = 0; i < cryptos.length(); i++) {
            JSONObject crypto = cryptos.getJSONObject(coinIds[i]);
            String name = crypto.getString("name").toLowerCase(Locale.ROOT);

            double price = crypto.getJSONObject("quote")
                                 .getJSONObject("USD")
                                 .getDouble("price");

            result.put(name, price);
        }

        return result;
    }

}
