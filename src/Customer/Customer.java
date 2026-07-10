
package Customer;

import java.time.LocalDate;


public class Customer {
    private String fullName;
    private String phone;
    private String address;
    private double point;
    private LocalDate createDate;

    public Customer() {
    }

    public Customer( String fullName, String phone, String address, double point, LocalDate createDate) {
        
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.point = point;
        this.createDate = createDate;
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
        return  fullName + ", " + phone + ", " + address 
                + ", " + point + ", " + createDate;
    }
    
    
    
    
    
}
