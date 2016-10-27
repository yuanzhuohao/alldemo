package com.example.proxy;

/**
 * Created by Jess Yuan on 27/10/2016.
 */

public class App {
    public static void main(String[] args) {
        WizardTowerProxy towerProxy = new WizardTowerProxy();

        towerProxy.enter(new Wizard("Jess"));
        towerProxy.enter(new Wizard("Mark"));
        towerProxy.enter(new Wizard("Sharly"));
        towerProxy.enter(new Wizard("Monica"));
        towerProxy.enter(new Wizard("Smith"));
    }
}
