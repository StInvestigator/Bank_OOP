package code.bank.services.atm;

import code.bank.model.ATM;

import java.util.Arrays;
import java.util.Random;

public class RandomATMStaticArrImpl implements RandomATM {
    private static final int[] nominals = new int[]{
            1, 2, 5, 10, 20, 50, 100, 200, 500, 1000
    };

    @Override
    public ATM getRandom(int number) {
        Random rand = new Random();
        int[] randomNominals = Arrays.copyOfRange(nominals, rand.nextInt(0, 4), rand.nextInt(7, 11));
        int[] randomBanknotes = new int[randomNominals.length];
        for (int i = 0; i < randomBanknotes.length; i++) {
            randomBanknotes[i] = rand.nextInt(5, 300);
        }
        return new ATM(number, randomNominals, randomBanknotes,
                randomBanknotes[rand.nextInt(randomBanknotes.length - 2,
                        randomBanknotes.length)],
                rand.nextInt(50, 200), new ATMPrintableFullImpl());
    }
}
