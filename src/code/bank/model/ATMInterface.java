package code.bank.model;

public interface ATMInterface {
    void print();
    int getAllMoney();
    void fill(int[] banknotes);
    int[] withdraw(int amount);
    int sumBills(int[] banknotes);
    int[] getNominals();
    int getNumber();
}
