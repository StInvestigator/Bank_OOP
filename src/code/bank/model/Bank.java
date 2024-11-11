package code.bank.model;

import code.bank.services.atm.RandomATMStaticArrImpl;
import code.bank.utils.ATMFactory;

public class Bank {
    private final String name;
    private final ATMInterface[] atms;

    public Bank(String name, int ATMCount) {
        this.name = name;
        atms = new ATMInterface[ATMCount];
        for (int i = 0; i < ATMCount; i++) {
            ATMFactory factory = new ATMFactory(new RandomATMStaticArrImpl());
            atms[i] = factory.createATM(i+1);
        }
    }

    public Bank(String name, ATMInterface[] atms) {
        this.name = name;
        this.atms = atms;
    }

    public long getMoneySum(){
        long sum = 0;
        for (ATMInterface atm : atms) {
            sum += atm.getAllMoney();
        }
        return sum;
    }

    public ATMInterface findATM(int number){
        for (ATMInterface atm : atms) {
            if (atm.getNumber() == number) {
                return atm;
            }
        }
        return null;
    }

    public ATMInterface[] getAtms() {
        return atms;
    }

    public String getName() {
        return name;
    }
}
