package List;

import Employee.Employee;
import Employee.FullTimeEmployee;
import Employee.PartTimeEmployee;
import Enum.Gender;
import Enum.Position;
import FileIO.FileIO;
import Utils.Formatter;
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
                        && e.getEmployeeID().equals(employee.getEmployeeID()) == true) {
                    System.out.println("Thêm nhân viên thất bại! Trùng ID hoặc Số điện thoại.");
                    return;
                }
            }
        }
        list.add(employee);
    }

    public void removeByID(String employeeID) {
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

    public void removeByPhone(String phone) {
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

    public boolean containsEmployee(String employeeID) {
        if (!list.isEmpty()) {
            for (Employee e : list) {
                if (e.getEmployeeID().equals(employeeID)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Employee searchByID(String employeeID) {
        if (!list.isEmpty()) {
            for (Employee e : list) {
                if (e.getEmployeeID().equals(employeeID)) {
                    return e;
                }
            }
        }
        return null;
    }

    public Employee searchByPhone(String phone) {
        if (!list.isEmpty()) {
            for (Employee e : list) {
                if (e.getPhone().equals(phone)) {
                    return e;
                }
            }
        }
        return null;
    }

    public ArrayList<Employee> searchByName(String name) {
        ArrayList<Employee> list1 = new ArrayList<>();
        if (!list.isEmpty()) {
            for (Employee e : list) {
                if (e.getFullName().equalsIgnoreCase(name)) {
                    list1.add(e);
                }
            }
            return list1;
        }
        return null;
    }

    public int getTotalEmployee() {
        int count = list.size();
        return count;
    }

    public int getTotalPartTimeEmployee() {
        if (list.isEmpty()) {
            return 0;
        }
        int count = 0;
        for (Employee e : list) {
            if (e instanceof PartTimeEmployee) {
                count += 1;
            }
        }
        return count;
    }

    public int getTotalFullTimeEmployee() {
        if (list.isEmpty()) {
            return 0;
        }
        int count = 0;
        for (Employee e : list) {
            if (e instanceof FullTimeEmployee) {
                count += 1;
            }
        }
        return count;
    }

    public boolean updateEmployee(String employeeID, String fullName, String phone, Gender gender, Position pos,
            boolean status) {
        if (list.isEmpty()) {
            return false;
        }
        Employee e = getEmployee(employeeID);
        if (e == null) {
            return false;
        }
        e.setFullName(fullName);
        e.setGender(gender);
        e.setPhone(phone);
        e.setPosition(pos);
        e.setStatus(status);
        return true;
    }

    public void displayAllEmployee() {

        if (list.isEmpty()) {
            System.out.println("Danh sách nhân viên trống!");
            return;
        }

        System.out.println("=====================================================================================");
        System.out.printf("%-8s %-22s %-15s %-8s %-18s %-20s %-12s%n",
                "MÃ",
                "TÊN",
                "SĐT",
                "GIỚI TÍNH",
                "CHỨC VỤ",
                "LOẠI",
                "TRẠNG THÁI");
        System.out.println("=====================================================================================");

        for (Employee e : list) {

            String type = (e instanceof FullTimeEmployee)
                    ? "Toàn thời gian"
                    : "Bán thời gian";

            System.out.printf("%-8s %-22s %-15s %-8s %-18s %-20s %-12s%n",
                    e.getEmployeeID(),
                    e.getFullName(),
                    e.getPhone(),
                    e.getGender(),
                    e.getPosition(),
                    type,
                    e.isStatus() ? "Đang làm" : "Ngừng làm");
        }

        System.out.println("=====================================================================================");
    }

    public void displayFullTimeEmployee() {

        if (list.isEmpty()) {
            System.out.println("Danh sách nhân viên trống!");
            return;
        }

        System.out.println("===============================================================================================");
        System.out.printf("%-8s %-22s %-15s %-8s %-16s %-12s %-15s %-15s %-15s %-15s%n",
                "MÃ", "TÊN", "SĐT", "GIỚI TÍNH", "CHỨC VỤ", "TRẠNG THÁI",
                "LƯƠNG CƠ BẢN", "PHỤ CẤP", "THƯỞNG", "TỔNG LƯƠNG");
        System.out.println("===============================================================================================");

        boolean found = false;

        for (Employee e : list) {
            if (e instanceof FullTimeEmployee) {

                FullTimeEmployee ft = (FullTimeEmployee) e;

                System.out.printf("%-8s %-22s %-15s %-8s %-16s %-12s %-15s %-15s %-15s %-15s%n",
                        ft.getEmployeeID(),
                        ft.getFullName(),
                        ft.getPhone(),
                        ft.getGender(),
                        ft.getPosition(),
                        ft.isStatus() ? "Đang làm" : "Ngừng làm",
                        Formatter.currency(ft.getBaseSalary()),
                        Formatter.currency(ft.getAllowance()),
                        Formatter.currency(ft.getBonus()),
                        Formatter.currency(ft.calculateSalary()));

                found = true;
            }
        }

        if (!found) {
            System.out.println("Không có nhân viên toàn thời gian.");
        }

        System.out.println("===========================================================================================");
    }

    public void displayPartTimeEmployee() {

        if (list.isEmpty()) {
            System.out.println("Danh sách nhân viên trống!");
            return;
        }

        System.out.println("===================================================================================================");
        System.out.printf("%-8s %-22s %-15s %-8s %-16s %-12s %-12s %-15s %-15s%n",
                "MÃ", "TÊN", "SĐT", "GIỚI TÍNH", "CHỨC VỤ", "TRẠNG THÁI",
                "SỐ GIỜ", "LƯƠNG/GIỜ", "TỔNG LƯƠNG");
        System.out.println("===================================================================================================");

        boolean found = false;

        for (Employee e : list) {
            if (e instanceof PartTimeEmployee) {

                PartTimeEmployee pt = (PartTimeEmployee) e;

                System.out.printf("%-8s %-22s %-15s %-8s %-16s %-12s %-12.1f %-15s %-15s%n",
                        pt.getEmployeeID(),
                        pt.getFullName(),
                        pt.getPhone(),
                        pt.getGender(),
                        pt.getPosition(),
                        pt.isStatus() ? "Đang làm" : "Ngừng làm",
                        pt.getHourWorked(),
                        Formatter.currency(pt.getSalaryPerHour()),
                        Formatter.currency(pt.calculateSalary()));

                found = true;
            }
        }

        if (!found) {
            System.out.println("Không có nhân viên bán thời gian.");
        }

        System.out.println("===============================================================================================================================");
    }
    //  đọc file .txt

    public void loadFromFile(String filePath) {
        list.clear();
        List<Employee> loaded = FileIO.readFile(filePath, fields -> {
            String type = fields[0].trim();   // "FT" hoặc "PT"
            String empID = fields[1].trim();
            String fullName = fields[2].trim();
            String phone = fields[3].trim();
            Gender gender = Gender.valueOf(fields[4].trim());
            Position pos = Position.valueOf(fields[5].trim());
            boolean status = Boolean.parseBoolean(fields[6].trim());
            if (type.equalsIgnoreCase("FT") || type.equalsIgnoreCase("FULLTIME")) {
                FullTimeEmployee ft = new FullTimeEmployee(empID, fullName, phone, gender, pos, status);
                ft.setBaseSalary(Double.parseDouble(fields[7].trim()));
                ft.setAllowance(Double.parseDouble(fields[8].trim()));
                ft.setBonus(Double.parseDouble(fields[9].trim()));
                return ft;
            } else {
                PartTimeEmployee pt = new PartTimeEmployee(empID, fullName, phone, gender, pos, status);
                pt.setHourWorked(Double.parseDouble(fields[7].trim()));
                pt.setSalaryPerHour(Double.parseDouble(fields[8].trim()));
                return pt;
            }
        });
        list.addAll(loaded);
    }
// lưu file .txt

    public void saveToFile(String filePath) {
        FileIO.writeFile(filePath, list, e -> {
            String base = e.getEmployeeID() + "|"
                    + e.getFullName() + "|"
                    + e.getPhone() + "|"
                    + e.getGender() + "|"
                    + e.getPosition() + "|"
                    + e.isStatus();
            if (e instanceof FullTimeEmployee) {
                FullTimeEmployee ft = (FullTimeEmployee) e;
                return "FT|" + base + "|" + ft.getBaseSalary() + "|" + ft.getAllowance() + "|" + ft.getBonus();
            } else {
                PartTimeEmployee pt = (PartTimeEmployee) e;
                return "PT|" + base + "|" + pt.getHourWorked() + "|" + pt.getSalaryPerHour();
            }
        });
    }

}