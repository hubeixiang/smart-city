package com.sct.application.authorization.properties;

public class Throttle {
    private ThrottleSignIn throttleSignIn = new ThrottleSignIn();

    public boolean isEnable() {
        return throttleSignIn.isEnabled();
    }

    public ThrottleSignIn getThrottleSignIn() {
        return throttleSignIn;
    }

    public void setThrottleSignIn(ThrottleSignIn throttleSignIn) {
        this.throttleSignIn = throttleSignIn;
    }
}
