package code.bank.exceptions;

public class ATMTwoOptionsException extends RuntimeException {
    private final int errorMoney;
    private final int optionGreater;
    private final int optionSmaller;

    public ATMTwoOptionsException(String message, int errorMoney, int optionGreater, int optionSmaller) {
        super(message);
        this.errorMoney = errorMoney;
        this.optionGreater = optionGreater;
        this.optionSmaller = optionSmaller;
    }

    public int getErrorMoney() {
        return errorMoney;
    }


    public int getOptionGreater() {
        return optionGreater;
    }

    public int getOptionSmaller() {
        return optionSmaller;
    }
}
