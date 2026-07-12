
package Customer;

import Utils.DateUtil;
import Utils.Formatter;
import Utils.IDGenerator;
import java.time.LocalDate;


public class Customer {
    private String customerID;
    private String fullName;
    private String phone;
    private String address;
    private double point;
    private LocalDate createDate;

    public Customer() {
    }

    public Customer( String fullName, String phone, String address) {
        this.customerID = IDGenerator.generateCustomerID();
        this.fullName = fullName;
        this.phone = phone;
        this.address = null;
        this.point = 0;
        this.createDate = LocalDate.now();
    }

    public Customer( String customerID,String fullName, String phone, String address) {
        this.fullName = fullName;
        this.phone = phone;
        this.address = null;
        this.point = 0;
        this.createDate = LocalDate.now();
    }
    

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double totalInvoice) {
        this.point = point;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return  fullName + ", " + Formatter.phone(phone) + ", " + address 
                + ", " + point + ", " + DateUtil.format(createDate);
    }
    
    
    
    
    
}
