package List;

import Employee.Employee;
import Employee.FullTimeEmployee;
import Employee.PartTimeEmployee;
import Enum.Gender;
import Enum.Position;
import java.util.ArrayList;
import java.util.List;

public class EmployeeList {

    private List<Employee> list;

    public EmployeeList() {
        list = new ArrayList<>();
    }

    public List<Employee> getList() {
        return list;
    }

    
    public void addEmployee(Employee employee) {
        if (!list.isEmpty()) {
            for (Employee e : list) {
                if (e.getPhone().equals(employee.getPhone()) == true
                        && e.getEmployeeID().equals(employee.getEmployeeID()) == true ) {
                    System.out.println("Thêm nhân viên thất bại! Trùng ID hoặc Số điện thoại.");
                    return ;
                }
            }
        }
        list.add(employee);
    }

    public void removeByID (String employeeID) {
        if (list.isEmpty()) {
            return;
        }
        for (Employee e : list) {
            if (e.getEmployeeID().equals(employeeID)) {
                list.remove(e);
                break;
            }
        }
    }

    public void removeByPhone (String phone) {
        if (list.isEmpty()) {
            return;
        }
        for (Employee e : list) {
            if (e.getPhone().equals(phone)) {
                list.remove(e);
                break;
            }
        }
    }

    public Employee getEmployee(String employeeID) {
        if (!list.isEmpty()) {
            for (Employee e : list) {
                if (e.getEmployeeID().equals(employeeID)) {
                    return e;
                }
            }
        }
        return null;
    }
    public boolean containsEmployee(String employeeID){
        if (!list.isEmpty()){
            for (Employee e : list){
                if (e.getEmployeeID().equals(employeeID)) return true;
            }
        }
        return false;
    }

    public void displayAllEmployee(){
        if (list.isEmpty())return ;
        for (Employee e: list){
            System.out.println(e);
        }
    }
    public void displayPartTimeEmployee(){
        if (list.isEmpty()) return ;
        for (Employee e: list){
            if(e instanceof PartTimeEmployee ){
                System.out.println(e);
            }
        }
        
    }
    public void displayFullTimeEmployee(){
        if (list.isEmpty()) return ;
        for (Employee e: list){
            if (e instanceof FullTimeEmployee ){
                System.out.println(e);
            }
        }
        
    }
    public Employee searchByID(String employeeID){
        if (!list.isEmpty()){
            for (Employee e: list){
                if (e.getEmployeeID().equals(employeeID)){
                    return e;
                }
            }
        } 
        return null;
    }
    public Employee searchByPhone(String phone){
        if (!list.isEmpty()){
            for (Employee e: list){
                if (e.getPhone().equals(phone)) return e;
            }
        }
        return null;
    }
    public ArrayList<Employee> searchByName(String name){
        ArrayList<Employee> list1 = new ArrayList<>();
        if (!list.isEmpty()){
            for (Employee e: list){
                if (e.getFullName().equalsIgnoreCase(name)) {
                    list1.add(e);
                }
            }
            return list1;
        }
        return null;
    }
    public int getTotalEmployee(){
        int count = list.size();
        return count;
    }
    public int getTotalPartTimeEmployee(){
        if (list.isEmpty()) return 0;
        int count = 0;
        for (Employee e: list){
            if (e instanceof PartTimeEmployee ){
                count += 1;
            }
        }
        return count;
    }
    public int getTotalFullTimeEmployee(){
         if (list.isEmpty()) return 0;
        int count = 0;
        for (Employee e: list){
            if (e instanceof FullTimeEmployee ){
                count += 1;
            }
        }
        return count;
    }
    
    public boolean updateEmployee(String employeeID, String fullName, String phone, Gender gender , Position pos
    , boolean status ){
        if (list.isEmpty()) return false;
        Employee e = getEmployee(employeeID);
        if (e == null) return false;
        e.setFullName(fullName);
        e.setGender(gender);
        e.setPhone(phone);
        e.setPosition(pos);
        e.setStatus(status);
        return true;
    }
    
    
    
    
    
    
    
    
    
}
