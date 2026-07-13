package Utils;

import java.util.concurrent.ThreadLocalRandom;

public class IDGenerator {

    public static String generate(String prefix) {
        return prefix + System.currentTimeMillis();
    }

    private static int generateRandomNumber() {
        return ThreadLocalRandom.current().nextInt(001, 1000);
    }

    public static String generateInvoiceID() {
        return generate("HD") +generateRandomNumber();
    }

    public static String generateCustomerID() {
        return generate("KH") + generateRandomNumber();
    }

    public static String generateEmployeeID() {
        return generate("NV") + generateRandomNumber();
    }

}
