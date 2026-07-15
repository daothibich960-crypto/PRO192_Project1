package Utils;

public class IDGenerator {

    private static int invoiceCounter = 0;
    private static int customerCounter = 0;
    private static int employeeCounter = 0;

    private static String generate(String prefix, int number) {
        return prefix + String.format("%03d", number);
    }

    public static String generateInvoiceID() {
        invoiceCounter++;
        return generate("HD", invoiceCounter);
    }

    public static void initInvoiceCounter(int currentMax) {
        invoiceCounter = currentMax;
    }

    public static String generateCustomerID() {
        customerCounter++;
        return generate("KH", customerCounter);
    }

    public static void initCustomerCounter(int currentMax) {
        customerCounter = currentMax;
    }

    public static String generateEmployeeID() {
        employeeCounter++;
        return generate("NV", employeeCounter);
    }

    public static void initEmployeeCounter(int currentMax) {
        employeeCounter = currentMax;
    }

    public static int extractNumber(String id) {
        try {
            String numberPart = id.replaceAll("\\D+", "");
            return Integer.parseInt(numberPart);
        } catch (Exception e) {
            return 0;
        }
    }
}