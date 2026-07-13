package Employee;

import Enum.Gender;
import Enum.Position;
import Utils.Formatter;

public class PartTimeEmployee extends Employee {

    private double hourWorked;
    private double salaryPerHour = 25000;
    

    public PartTimeEmployee() {
    }

    public PartTimeEmployee(String employeeID, String fullName, String phone, Gender gender, Position position, boolean status) {
        super(employeeID, fullName, phone, gender, position, status);
        this.hourWorked = 0;
    }

    public double getHourWorked() {
        return hourWorked;
    }

    public void setHourWorked(double hourWorked) {
        this.hourWorked = hourWorked;
    }

    public double getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(double salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }

    @Override
    public String toString() {
        return super.toString() + hourWorked + ", " + Formatter.currency(salaryPerHour);
    }

    @Override
    public double calculateSalary() {
        double salary = hourWorked*salaryPerHour* getPosition().getHeSoLuong();
        return salary;

    }

}
