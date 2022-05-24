package com.example.cryptoWebsite.user;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Embeddable
public class Possessions {
    double dollars;
    double solana = 0;
    double bitcoin = 0;
    double ethereum = 0;
    double polkadot = 0;

    public Possessions() {
        dollars = 1000;
    }

    public LinkedHashMap<String,Double> toMap(){
       LinkedHashMap<String, Double> hashMap = new LinkedHashMap();
       hashMap.put("dollars",dollars);
        hashMap.put("solana",solana);
        hashMap.put("bitcoin",bitcoin);
        hashMap.put("ethereum",ethereum);
        hashMap.put("polkadot",polkadot);
        return hashMap;
    }

    public double getDollars() {
        return dollars;
    }

    public void setDollars(double dollars) {
        this.dollars = dollars;
    }


    public double getSolana() {
        return solana;
    }

    public void setSolana(double solana) {
        this.solana = solana;
    }

    public double getBitcoin() {
        return bitcoin;
    }

    public void setBitcoin(double bitcoin) {
        this.bitcoin = bitcoin;
    }

    public double getEthereum() {
        return ethereum;
    }

    public void setEthereum(double ethereum) {
        this.ethereum = ethereum;
    }

    public double getPolkadot() {
        return polkadot;
    }

    public void setPolkadot(double polkadot) {
        this.polkadot = polkadot;
    }

    public void setCoin(String coinName, double value){
        switch (coinName) {
            case "bitcoin":
               setBitcoin(value);
               break;
            case "ethereum":
               setEthereum(value);
                break;
            case "polkadot":
                setPolkadot(value);
                break;
            case "solana":
                setSolana(value);
                break;
        }
    }

    public double getCoin(String coinName) {
        switch (coinName) {
            case "bitcoin":
                return getBitcoin();
            case "ethereum":
                return getEthereum();
            case "polkadot":
                return getPolkadot();
            case "solana":
                return getSolana();
        }
        return 0.0;
    }
}
