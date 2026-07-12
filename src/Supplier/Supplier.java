
package Supplier;

public class Supplier {

    private String supplierId;
    private String supplierName;
    private String phone;
    private String email;
    private String address;

    public Supplier() {
    }

    public Supplier(String supplierId, String supplierName,
            String phone, String email, String address) {

        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    //==================== Getter & Setter ====================

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //==================== Display ====================

    public void displaySupplier() {

        System.out.printf("%-10s %-25s %-15s %-30s %-25s\n",
                supplierId,
                supplierName,
                phone,
                email,
                address);
    }

    @Override
    public String toString() {

        return String.format("%-10s %-25s %-15s %-30s %-25s",
                supplierId,
                supplierName,
                phone,
                email,
                address);
    }

}
