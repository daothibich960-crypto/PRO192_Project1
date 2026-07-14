
package Employee;

import Enum.Gender;
import Enum.Position;
import Utils.Formatter;


public abstract class Employee {
    private String employeeID;
    private String fullName;
    private String phone;
    private Gender gender;
    private Position position;
    private boolean status;

    public Employee() {
    }

    public Employee(String employeeID, String fullName, String phone, Gender gender, Position position, boolean status) {
        this.employeeID = employeeID;
        this.fullName = fullName;
        this.phone = phone;
        this.gender = gender;
        this.position = position;
        this.status = status;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return employeeID + ", " + fullName + ", " + Formatter.phone(phone) + ", " + gender +", " + position + ", "
                 + status;
    }
    
    public abstract double calculateSalary();
    
    
    
}
