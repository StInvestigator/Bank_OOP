package code.bank.model;

import code.bank.exceptions.ATMMinimumAmountException;
import code.bank.exceptions.ATMNotEnoughBillsException;
import code.bank.exceptions.ATMNotEnoughMoneyException;
import code.bank.exceptions.ATMTwoOptionsException;
import code.bank.services.atm.ATMPrintable;

import java.util.Arrays;

public class ATM implements ATMInterface {
    private int number;
    private int[] nominals;
    private int[] banknotes;
    private int minOutMoneyAmount;
    private int maxOutBanknoteCount;
    private ATMPrintable printable;

    /**
     * @param nominals            не может быть пустым. Иначе получите IllegalArgumentException
     * @param banknotes           массив имеет строго такое же количество элементов, как и  nominals. Если переданный массив будет больше - лишние значения обрежутся, если меньше - недостающие запишутся нулями.
     * @param minOutMoneyAmount   минимальное значение - 1.
     * @param maxOutBanknoteCount минимальное значение - 10.
     */

    public ATM(int number, int[] nominals, int[] banknotes, int minOutMoneyAmount,
               int maxOutBanknoteCount, ATMPrintable printable)
            throws IllegalArgumentException {
        if (nominals.length == 0) {
            throw new IllegalArgumentException("Nominal array cant be empty");
        }
        this.number = number;
        this.nominals = nominals;
        this.banknotes = Arrays.copyOf(banknotes, banknotes.length);
        this.minOutMoneyAmount = Math.max(minOutMoneyAmount, 1);
        this.maxOutBanknoteCount = Math.max(maxOutBanknoteCount, 10);
        this.printable = printable;
    }

    public void fill(int[] banknotes) {
        for (int i = 0; i < Math.min(banknotes.length, this.banknotes.length); i++) {
            this.banknotes[i] += banknotes[i];
        }
    }

    public int[] withdraw(int amount) throws ATMTwoOptionsException,
            ATMNotEnoughBillsException, ATMMinimumAmountException, ATMNotEnoughMoneyException {
        if (amount < minOutMoneyAmount) {
            throw new ATMMinimumAmountException("You are trying to withdraw sum smaller than minimal.", amount, minOutMoneyAmount);
        }
        if(maxMoneyToGive() < minOutMoneyAmount) {
            throw new ATMNotEnoughMoneyException("ATM currently doesnt have enough money to make any transactions. Please, come back later.", maxMoneyToGive(), minOutMoneyAmount);
        }
        int[] banknotes = this.banknotes.clone();
        int tmpAmount = amount;
        int banknotesCount = 0;
        int[] result = new int[nominals.length];
        for (int i = banknotes.length - 1; i >= 0; ) {

            if (nominals[i] <= tmpAmount && banknotes[i] > 0) {
                banknotes[i]--;
                result[i]++;
                tmpAmount -= nominals[i];
                banknotesCount++;
                if (banknotesCount == maxOutBanknoteCount) {
                    break;
                }
            } else {
                i--;
            }
        }
        if (tmpAmount == 0) {
            this.banknotes = banknotes;
            return result;
        } else {
            int sum = sumMoney(result);
            int maxAmount = maxMoneyToGive();
            if (sumMoney(banknotes) == 0 || sum >= maxAmount) {
                throw new ATMNotEnoughBillsException(String.format("Cannot withdraw %d",
                        amount), amount, maxAmount);
            } else {
                tmpAmount = amount;
                int upperBound = 0;
                while (upperBound == 0) {
                    if (isGiveAble(++tmpAmount)) {
                        upperBound = tmpAmount;
                    }
                }
                throw new ATMTwoOptionsException(String.format("Cannot withdraw %d", amount)
                        , amount, upperBound, sum);
            }
        }
    }

    private boolean isGiveAble(int amount) {
        if (amount < minOutMoneyAmount) {
            return false;
        }
        int[] banknotes = this.banknotes.clone();
        int tmpAmount = amount;
        int banknotesCount = 0;
        for (int i = banknotes.length - 1; i >= 0; ) {

            if (nominals[i] <= tmpAmount && banknotes[i] > 0) {
                banknotes[i]--;
                tmpAmount -= nominals[i];
                banknotesCount++;
                if (banknotesCount == maxOutBanknoteCount) {
                    break;
                }
            } else {
                i--;
            }
        }
        return tmpAmount == 0;
    }

    public void print() {
        printable.print(this);
    }

    private int maxMoneyToGive() {
        int count = 0;
        int money = 0;
        int[] banknotes = this.banknotes.clone();
        for (int i = banknotes.length - 1; i >= 0; ) {
            if (banknotes[i] > 0) {
                money += nominals[i];
                banknotes[i]--;
                count++;
            } else {
                i--;
            }
            if (count == maxOutBanknoteCount) {
                break;
            }
        }
        return money;
    }

    private int sumMoney(int[] banknotes) {
        int sum = 0;
        for (int i = banknotes.length - 1; i >= 0; i--) {
            sum += banknotes[i] * nominals[i];
        }
        return sum;
    }

    public int sumBills(int[] banknotes) {
        int count = 0;
        for (int i = banknotes.length - 1; i >= 0; i--) {
            count += banknotes[i];
        }
        return count;
    }

    public int getAllMoney() {
        return sumMoney(banknotes);
    }

    public void setMinOutMoneyAmount(int minOutMoneyAmount) {
        this.minOutMoneyAmount = Math.max(minOutMoneyAmount, 1);
    }

    public void setMaxOutBanknoteCount(int maxOutBanknoteCount) {
        this.maxOutBanknoteCount = Math.max(maxOutBanknoteCount, 10);
    }

    public int getNumber() {
        return number;
    }

    public int[] getNominals() {
        return nominals;
    }

    public int[] getBanknotes() {
        return banknotes;
    }

    public int getMinOutMoneyAmount() {
        return minOutMoneyAmount;
    }

    public int getMaxOutBanknoteCount() {
        return maxOutBanknoteCount;
    }
}