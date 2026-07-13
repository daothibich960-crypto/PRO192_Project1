
package Employee;

import Enum.Gender;
import Enum.Position;
import Utils.Formatter;

public class FullTimeEmployee extends Employee {
    private double baseSalary;
    private double allowance;
    private double bonus;

    public FullTimeEmployee() {
    }

    public FullTimeEmployee(String employeeID, String fullName, String phone, Gender gender, Position position, boolean status
           ) {
        super(employeeID, fullName, phone, gender, position, status);
        
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
       double salary = baseSalary*getPosition().getHeSoLuong() + allowance + bonus;
       return salary;
    }

    @Override
    public String toString() {
        return super.toString() +", " +  Formatter.currency(baseSalary) + 
                ", " + Formatter.currency(allowance) + ", " + Formatter.currency(bonus);
    }
    
    
}
