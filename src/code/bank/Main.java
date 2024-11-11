package code.bank;

import code.bank.exceptions.ATMNotEnoughBillsException;
import code.bank.exceptions.ATMTwoOptionsException;
import code.bank.menu.MenuExecutor;
import code.bank.model.ATM;
import code.bank.services.atm.ATMPrintableFullImpl;

public class Main {
    public static void main(String[] args) {
        MenuExecutor menu = new MenuExecutor("Private bank",20);

        menu.start();
    }
}