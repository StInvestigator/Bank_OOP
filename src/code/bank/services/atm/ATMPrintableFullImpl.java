package code.bank.services.atm;

import code.bank.model.ATM;

import java.util.Arrays;

public class ATMPrintableFullImpl implements ATMPrintable {
    @Override
    public void print(ATM atm) {
        System.out.println("-------------------[ATM №" + atm.getNumber() + " information]----------------");
        System.out.println("Nominals: " + Arrays.toString(atm.getNominals()));
        System.out.println("Banknotes amount: " + Arrays.toString(atm.getBanknotes()));
        System.out.println("Minimum transaction sum: " + atm.getMinOutMoneyAmount());
        System.out.println("Maximum number of banknotes for issuing: " + atm.getMaxOutBanknoteCount());
        System.out.println("Total money amount: " + atm.getAllMoney());
        System.out.println("-----------------[ATM №" + atm.getNumber() + " information end]--------------");
    }
}
