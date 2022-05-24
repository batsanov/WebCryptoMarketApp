package com.example.cryptoWebsite.trading;

import com.example.cryptoWebsite.user.User;
import com.example.cryptoWebsite.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TradingService {
    UserService userService;

    @Autowired
    public TradingService(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    public boolean trade(TradingRequest request) {
        User user = userService.getCurrentUser();

        Double value = request.getValue();
        String requestType = request.getType();
        Double currentPrice =   request.getCurrentPrice();
        String coinName =  request.getCoinName();

        if (requestType.equals("sell"))
            return sell(user, coinName, value, currentPrice);
        else
            return buy(user, coinName, value, currentPrice);

    }

    private boolean sell(User user, String coinName, Double value , Double currentPrice){
        double currentValueInCoin = user.getPossessions().getCoin(coinName);
        double valueInCoin = value / currentPrice;


        if(currentValueInCoin < valueInCoin)
            throw new IllegalStateException("Not enough " + coinName + " to sell! Current " + coinName + " balance is " + user.getPossessions().getCoin(coinName));

        double updatedValue = currentValueInCoin - valueInCoin;
        double updatedDollars = user.getPossessions().getDollars() + value;

        user.getPossessions().setCoin(coinName, updatedValue);
        user.getPossessions().setDollars(updatedDollars);

        return true;
    }
    private boolean buy(User user, String coinName, Double value , Double currentPrice){
        double currentDollars = user.getPossessions().getDollars();
        double buyPrice = currentPrice*(1+0.02);

        if(currentDollars < value)
            throw new IllegalStateException("Not enough dollars to purchase! Current dollars balance is " + user.getPossessions().getDollars() + "$");



        double currentValue = user.getPossessions().getCoin(coinName);

        double updatedValue = currentValue + value / buyPrice;
        double updatedDollars = currentDollars - value;

        user.getPossessions().setCoin(coinName, updatedValue);
        user.getPossessions().setDollars(updatedDollars);

        return true;
    }
}
