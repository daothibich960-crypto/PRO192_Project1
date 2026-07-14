
package Utils;

public class IDGenerator {
    
    public static String generate(String prefix) {
        return prefix + System.currentTimeMillis();
    }

    public static String generateInvoiceID() {
        return generate("HD") + System.currentTimeMillis();
    }

    public static String generateCustomerID() {
        return generate("KH") +  System.currentTimeMillis();
    }

    public static String generateEmployeeID() {
        return generate("NV") +  System.currentTimeMillis();
    }
    
}
