package com.example.proxy;

/**
 * Created by Jess Yuan on 27/10/2016.
 */

public class WizardTowerProxy extends WizardTower {

    private static final int MAX_OF_WIZARD = 3;

    private int numOfWizard = 0;

    @Override
    protected void enter(Wizard wizard) {
        if (numOfWizard < MAX_OF_WIZARD) {
            super.enter(wizard);
            numOfWizard++;
        } else {
            System.out.println(wizard.getName() + " is not allow enter the tower");
        }
    }
}
