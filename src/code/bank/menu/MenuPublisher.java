package code.bank.menu;

import code.bank.exceptions.ATMMinimumAmountException;
import code.bank.exceptions.ATMNotEnoughBillsException;
import code.bank.exceptions.ATMNotEnoughMoneyException;
import code.bank.exceptions.ATMTwoOptionsException;
import code.bank.model.ATM;

import java.util.Scanner;

public class MenuPublisher {
    public int showBankMenu(String bankName) {
        int answer;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("\n\n---------------------[Bank \"" + bankName + "\" Menu]---------------------");
            System.out.println("1. Print all ATMs info");
            System.out.println("2. Show total amount of money in ATMs");
            System.out.println("3. Move to operations with ATM");
            System.out.println("0. Close the menu");
            System.out.println("-------------------[Bank \"" + bankName + "\" Menu end]-------------------");
            System.out.print("Enter your choice: ");
            answer = input.nextInt();
            if (answer < 0 || answer > 3) {
                System.out.println("Invalid input. Try again.");
            }
        } while (answer < 0 || answer > 3);
        return answer;
    }

    public int showATMMenu(int number) {
        int answer;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("\n\n---------------------[ATM №\"" + number + "\" Menu]---------------------");
            System.out.println("1. Print ATM info");
            System.out.println("2. Withdraw money");
            System.out.println("3. Put money");
            System.out.println("0. Back to bank menu");
            System.out.println("-------------------[ATM №\"" + number + "\" Menu end]-------------------");
            System.out.print("Enter your choice: ");
            answer = input.nextInt();
            if (answer < 0 || answer > 3) {
                System.out.println("Invalid input. Try again.");
            }
        } while (answer < 0 || answer > 3);
        return answer;
    }

    public int showATMTwoOptionsException(ATMTwoOptionsException e) {
        int answer;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("\n\n---------------------[Error]---------------------");
            System.out.println(e.getMessage());
            System.out.println("You may consider one of the following options: ");
            System.out.println("1. Withdraw more - " + e.getOptionGreater());
            System.out.println("2. Withdraw less - " + e.getOptionSmaller());
            System.out.println("3. Try another amount of money");
            System.out.println("---------------------[Error]---------------------");
            System.out.print("Enter your choice: ");
            answer = input.nextInt();
            if (answer < 1 || answer > 3) {
                System.out.println("Invalid input. Try again.");
            }
        } while (answer < 1 || answer > 3);
        return answer;
    }

    public int showATMNotEnoughBillsException(ATMNotEnoughBillsException e) {
        int answer;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("\n\n---------------------[Error]---------------------");
            System.out.println(e.getMessage());
            System.out.println("You may consider one of the following options: ");
            System.out.println("1. Withdraw maximum amount of money that ATM can give - " + e.getMaxMoney());
            System.out.println("2. Try another amount of money");
            System.out.println("---------------------[Error]---------------------");
            System.out.print("Enter your choice: ");
            answer = input.nextInt();
            if (answer < 1 || answer > 2) {
                System.out.println("Invalid input. Try again.");
            }
        } while (answer < 1 || answer > 2);
        return answer;
    }

    public int showATMMinimumAmountException(ATMMinimumAmountException e) {
        int answer;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("\n\n---------------------[Error]---------------------");
            System.out.println(e.getMessage());
            System.out.println("You may consider one of the following options: ");
            System.out.println("1. Withdraw minimum amount of money that ATM can give - " + e.getMinMoney());
            System.out.println("2. Try another amount of money");
            System.out.println("---------------------[Error]---------------------");
            System.out.print("Enter your choice: ");
            answer = input.nextInt();
            if (answer < 1 || answer > 2) {
                System.out.println("Invalid input. Try again.");
            }
        } while (answer < 1 || answer > 2);
        return answer;
    }

    public void showATMNotEnoughMoneyException(ATMNotEnoughMoneyException e) {
        System.out.println("\n\n---------------------[Error]---------------------");
        System.out.println(e.getMessage());
        System.out.println("Maximum amount of money that ATM can give - " + e.getErrorMoneyAmount());
        System.out.println("Minimum amount of money that ATM can give - " + e.getMinMoneyAmount());
        System.out.println("---------------------[Error]---------------------");
    }
}