package com.example.cryptoWebsite.trading;

import com.example.cryptoWebsite.prices.PricesController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "trading")
public class TradingController {
    private final PricesController pricesController;
    private TradingService tradingService;


    @Autowired
    public TradingController(PricesController pricesController, TradingService tradingService) {
        this.pricesController = pricesController;
        this.tradingService = tradingService;
    }

    @PostMapping(path = "{action}/{crypto}")
    public String trade(
                        @PathVariable("action") String action,
                        @PathVariable("crypto") String crypto,
                        @RequestParam Double value,
                        RedirectAttributes redirectAttributes){

        try{
            Double price = pricesController.getCurrentPrices().get(crypto);
            TradingRequest request = new TradingRequest(action,crypto,value,price);
            tradingService.trade(request);
            return "redirect:/user/profile";
        }
        catch (Exception e){
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/trading/error";
        }



    }

    @GetMapping(path = "error")
    public String getError(){
        return "error_trading";
    }

    @GetMapping(path="page")
    public String getTrading(Model model){
        model.addAttribute("prices", pricesController.getCurrentPrices());

        return "trading_page";
    }

}
