package code.bank.exceptions;

public class ATMNotEnoughMoneyException extends RuntimeException {
    private final int errorMoneyAmount;
    private final int minMoneyAmount;

    public ATMNotEnoughMoneyException(String message, int errorMoneyAmount, int minMoneyAmount) {
        super(message);
        this.errorMoneyAmount = errorMoneyAmount;
        this.minMoneyAmount = minMoneyAmount;
    }

    public int getErrorMoneyAmount() {
        return errorMoneyAmount;
    }

    public int getMinMoneyAmount() {
        return minMoneyAmount;
    }
}
