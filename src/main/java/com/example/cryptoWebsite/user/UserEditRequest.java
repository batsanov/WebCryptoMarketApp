package com.example.cryptoWebsite.user;

public class UserEditRequest {
   private String input;
   private String attribute;

    public UserEditRequest(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
