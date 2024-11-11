package code.bank.menu;


import code.bank.exceptions.ATMMinimumAmountException;
import code.bank.exceptions.ATMNotEnoughBillsException;
import code.bank.exceptions.ATMNotEnoughMoneyException;
import code.bank.exceptions.ATMTwoOptionsException;
import code.bank.model.ATM;
import code.bank.model.ATMInterface;
import code.bank.model.Bank;

import java.util.Arrays;
import java.util.Scanner;

public class MenuExecutor {
    private MenuPublisher menu;
    private Bank bank;

    public MenuExecutor(String bankName, int count) {
        this.menu = new MenuPublisher();
        this.bank = new Bank(bankName, count);
    }

    public MenuExecutor(Bank bank) {
        this.menu = new MenuPublisher();
        this.bank = bank;
    }

    public MenuExecutor(MenuPublisher menu, Bank bank) {
        this.menu = menu;
        this.bank = bank;
    }

    public void start() {
        int answer;
        do {
            answer = menu.showBankMenu(bank.getName());

            switch (answer) {
                case 1:
                    executePrintATMs();
                    break;
                case 2:
                    executePrintTotalMoney();
                    break;
                case 3:
                    startATM(executeMoveToATM());
                    break;
            }
        } while (answer != 0);
    }

    private void startATM(int number) {
        int answer;
        ATMInterface atm = bank.findATM(number);
        do {
            answer = menu.showATMMenu(number);

            switch (answer) {
                case 1:
                    executePrintATM(atm);
                    break;
                case 2:
                    executeWithdrawFromATM(atm);
                    break;
                case 3:
                    executePutMoneyToATM(atm);
                    break;
            }
        } while (answer != 0);
    }

    private void executePrintATMs() {
        for (ATMInterface atm : bank.getAtms()) {
            atm.print();
        }
    }

    private void executePrintATM(ATMInterface atm) {
        atm.print();

    }

    private void executePrintTotalMoney() {
        System.out.println("Total money amount in all ATMs: " + bank.getMoneySum());
    }

    private int executeMoveToATM() {
        Scanner scanner = new Scanner(System.in);
        int ATMNumber;
        while (true) {
            System.out.print("Enter ATM number: ");
            try {
                ATMNumber = scanner.nextInt();
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Wrong input. Try again.");
                continue;
            }
            if (bank.findATM(ATMNumber) != null) {
                return ATMNumber;
            }
            System.out.print("Unexciting number. Try again.");
        }
    }

    private void executeWithdrawFromATM(ATMInterface atm) {
        int amount;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter amount to withdraw: ");
            amount = scanner.nextInt();
            if (amount <= 0) {
                System.out.println("Amount must be greater than 0. Try again");
            }
        } while (amount <= 0);
        int billsCount;
        try {
            billsCount = atm.sumBills(atm.withdraw(amount));
            System.out.println("You withdrew this amount of money in " + billsCount + " bills");
        } catch (ATMNotEnoughBillsException e) {
            int answer = menu.showATMNotEnoughBillsException(e);
            switch (answer) {
                case 1:
                    billsCount = atm.sumBills(atm.withdraw(e.getMaxMoney()));
                    System.out.println("You withdrew this amount of money in " + billsCount + " bills");
                    break;
                case 2:
                    executeWithdrawFromATM(atm);
                    break;
            }
        } catch (
                ATMTwoOptionsException e) {
            int answer = menu.showATMTwoOptionsException(e);
            switch (answer) {
                case 1:
                    billsCount = atm.sumBills(atm.withdraw(e.getOptionGreater()));
                    System.out.println("You withdrew this amount of money in " + billsCount + " bills");
                    break;
                case 2:
                    billsCount = atm.sumBills(atm.withdraw(e.getOptionSmaller()));
                    System.out.println("You withdrew this amount of money in " + billsCount + " bills");
                    break;
                case 3:
                    executeWithdrawFromATM(atm);
                    break;
            }
        } catch (ATMMinimumAmountException e) {
            int answer = menu.showATMMinimumAmountException(e);
            switch (answer) {
                case 1:
                    atm.withdraw(e.getMinMoney());
                    break;
                case 2:
                    executeWithdrawFromATM(atm);
                    break;
            }
        } catch (ATMNotEnoughMoneyException e) {
            menu.showATMNotEnoughMoneyException(e);
        }
    }

    public void executePutMoneyToATM(ATMInterface atm) {
        Scanner scanner = new Scanner(System.in);
        int[] banknotes;
        do {
            System.out.println("Nominals list: " + Arrays.toString(atm.getNominals()));
            System.out.println("Enter amount of bills you want to put that corresponds to the each nominal from the list above (with whitespaces as separators): ");
            String input = scanner.nextLine();
            try {
                banknotes = Arrays.stream(input.split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                if (banknotes.length > atm.getNominals().length) {
                    System.out.println("Wrong input. Try again");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Wrong input. Try again");
            }
        } while (true);
        atm.fill(banknotes);
        System.out.println("Success!");
    }

    public void setMenu(MenuPublisher menu) {
        this.menu = menu;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
}