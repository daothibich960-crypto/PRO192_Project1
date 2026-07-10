
package Employee;

import Emun.Gender;
import Emun.Position;

public class FullTimeEmployee extends Employee {
    private double baseSalary;
    private double allowance;
    private double bonus;

    public FullTimeEmployee() {
    }

    public FullTimeEmployee(String employeeID, String fullName, String phone, Gender gender, Position position, boolean status,
            double baseSalary, double allowance, double bonus) {
        super(employeeID, fullName, phone, gender, position, status);
        this.baseSalary = baseSalary;
        this.allowance = allowance;
        this.bonus = bonus;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getAllowance() {
        return allowance;
    }

    public void setAllowance(double allowance) {
        this.allowance = allowance;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    
    @Override
    public double calculateSalary() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
