package code.bank.exceptions;

public class ATMNotEnoughBillsException extends RuntimeException {
    private final int errorMoney;
    private final int maxMoney;

    public ATMNotEnoughBillsException(String message, int errorMoney, int maxMoney) {
        super(message);
        this.errorMoney = errorMoney;
        this.maxMoney = maxMoney;
    }

    public int getErrorMoney() {
        return errorMoney;
    }

    public int getMaxMoney() {
        return maxMoney;
    }
}
