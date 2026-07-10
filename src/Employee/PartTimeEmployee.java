package Employee;

import Emun.Gender;
import Emun.Position;

public class PartTimeEmployee extends Employee {

    private double hourWorked;
    private double salaryPerHour = 25000;
    

    public PartTimeEmployee() {
    }

    public PartTimeEmployee(String employeeID, String fullName, String phone, Gender gender, Position position, boolean status, double hourWorked, double salaryPerHour) {
        super(employeeID, fullName, phone, gender, position, status);
        this.hourWorked = hourWorked;
        this.salaryPerHour = salaryPerHour;
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
        return super.toString() + hourWorked + ", " + salaryPerHour;
    }

    @Override
    public double calculateSalary() {
        double salary = hourWorked*salaryPerHour;
        return salary;

    }

}
