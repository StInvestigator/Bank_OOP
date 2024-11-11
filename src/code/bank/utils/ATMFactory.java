package code.bank.utils;

import code.bank.model.ATM;
import code.bank.services.atm.RandomATM;

public class ATMFactory {
    private RandomATM randomATM;

    public ATMFactory(RandomATM randomATM) {
        this.randomATM = randomATM;
    }

    public ATM createATM(int number) {
        return randomATM.getRandom(number);
    }

    public void setRandomATM(RandomATM randomBank) {
        this.randomATM = randomBank;
    }
}
