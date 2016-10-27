package com.example.single;

/**
 * Created by Jess Yuan on 27/10/2016.
 */

public class InitializingOnDemandHolder {

    public InitializingOnDemandHolder() {
    }

    public static InitializingOnDemandHolder getInstance() {
        return Holder.holder;
    }

    private static class Holder {
        private final static InitializingOnDemandHolder holder = new InitializingOnDemandHolder();
    }

}
