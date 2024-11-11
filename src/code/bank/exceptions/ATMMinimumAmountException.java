package code.bank.exceptions;

public class ATMMinimumAmountException extends RuntimeException {
    private final int errorMoney;
    private final int minMoney;

    public ATMMinimumAmountException(String message, int errorMoney, int minMoney) {
        super(message);
        this.errorMoney = errorMoney;
        this.minMoney = minMoney;
    }

    public int getErrorMoney() {
        return errorMoney;
    }

    public int getMinMoney() {
        return minMoney;
    }
}
