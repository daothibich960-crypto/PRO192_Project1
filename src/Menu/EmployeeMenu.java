package Menu;

import Employee.Employee;
import Employee.FullTimeEmployee;
import Employee.PartTimeEmployee;
import Enum.Gender;
import Enum.Position;
import List.EmployeeList;
import static Utils.Formatter.phone;
import Utils.IDGenerator;
import Utils.Input;
import Utils.Validation;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeMenu {

    private EmployeeList employeeList;
    private Scanner sc;

    public EmployeeMenu(EmployeeList employeeList,Scanner sc) {
        this.employeeList = employeeList;
        this.sc = sc;
    }

    public void show() {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = Input.readIntInRange("Chọn: ", 0, 8);

            switch (choice) {
                case 1:

                    addEmployee();
                    break;
                case 2:
                    removeEmployee();
                    break;
                case 3:
                    updateEmployee();
                    break;
                case 4:
                    searchByID();
                    break;
                case 5:
                    searchByName();
                    break;
                case 6:
                    System.out.println("-------Display----------");
                    employeeList.displayAllEmployee();
                    break;
                case 7:
                    displayByType();
                    break;
                case 8:
                    showStatistics();
                    break;
                case 0:
                    running = false;
                    System.out.println("Quay lại menu chính...");
                    break;
            }
        }
    }

    private void printMenu() {
        System.out.println("\n===== QUẢN LÝ NHÂN VIÊN =====");
        System.out.println("1. Thêm nhân viên");
        System.out.println("2. Xóa nhân viên");
        System.out.println("3. Cập nhật nhân viên");
        System.out.println("4. Tìm theo mã nhân viên");
        System.out.println("5. Tìm theo tên");
        System.out.println("6. Hiển thị tất cả nhân viên");
        System.out.println("7. Hiển thị theo loại (Full-time/Part-time)");
        System.out.println("8. Thống kê số lượng nhân viên");
        System.out.println("0. Quay lại");
    }

    private void showStatistics() {
        System.out.println("Tổng số nhân viên: " + employeeList.getTotalEmployee());
        System.out.println("Nhân viên toàn thời gian: " + employeeList.getTotalFullTimeEmployee());
        System.out.println("Nhân viên bán thời gian: " + employeeList.getTotalPartTimeEmployee());

    }

    private void displayByType() {
        System.out.println("1. Nhân viên toàn thời gian");
        System.out.println("2. Nhân viên bán thời gian");
        int type = Input.readIntInRange("Chọn: ", 1, 2);
        if (type == 1) {
            System.out.println("--------Full Time----------");
            employeeList.displayFullTimeEmployee();
        } else {
            System.out.println("--------Full Time----------");
            employeeList.displayPartTimeEmployee();
        }

    }

    private void addEmployee() {
        System.out.println("Nhân viên mới là:\n1.Nhân viên part time\n2.Nhân viên full time");
        int choice = Input.readIntInRange("Chọn: ", 1, 2);
        switch (choice) {
            case 1:
                System.out.print("Nhập họ và tên: ");
                String name = sc.nextLine();
                System.out.print("Nhập số điện thoại: ");
                String phone = "";
                checkPhone(phone);
                Gender gender1 = null;
                try {
                    System.out.print("Nhập giới tính: ");
                    String gender = sc.nextLine().toUpperCase();
                    gender1 = Gender.valueOf(gender);
                } catch (Exception e) {
                    System.out.print("Giới tính nhập không hợp lệ");
                }
                Position position = null;
                try {
                    System.out.println("Nhập ví trị làm việc: ");
                    String pos1 = sc.nextLine().toUpperCase();
                    position = Position.valueOf(pos1);
                } catch (Exception e) {
                    System.out.println("Nhập vị trí không hợp lệ.");
                }
                System.out.print("Nhập trạng thái làm việc(Onl | OFF) :");
                boolean status = sc.nextBoolean();
                String employeeID = IDGenerator.generateEmployeeID();
                Employee e = new PartTimeEmployee(employeeID, name, phone, gender1, position, status);

                employeeList.addEmployee(e);
                break;
            case 2:
                System.out.print("Nhập họ và tên: ");
                String name2 = sc.nextLine();
                System.out.print("Nhập số điện thoại: ");
                String phone2 = "";
                checkPhone(phone2);
                Gender gender2 = null;
                try {
                    System.out.print("Nhập giới tính: ");
                    String gender = sc.nextLine().toUpperCase();
                    gender2 = Gender.valueOf(gender);
                    break;
                } catch (Exception e1) {
                    System.out.print("Giới tính nhập không hợp lệ");
                }
                Position position2 = null;
                try {
                    System.out.println("Nhập ví trị làm việc: ");
                    String pos1 = sc.nextLine().toUpperCase();
                    position2 = Position.valueOf(pos1);
                    break;
                } catch (Exception e1) {
                    System.out.println("Nhập vị trí không hợp lệ.");
                }
                System.out.print("Nhập trạng thái làm việc(Onl | OFF) :");
                boolean status2 = sc.nextBoolean();
                String employeeID2 = IDGenerator.generateEmployeeID();
                Employee e2 = new FullTimeEmployee(employeeID2, name2, phone2, gender2, position2, status2);

                employeeList.addEmployee(e2);
                break;
            default:
                System.out.println("Nhập không hợp lệ.");
                break;
        }

    }

    private void removeEmployee() {
        System.out.println("Nhập xóa bằng:\n1.Xóa bằng ID\n2.Xóa bằng số điện thoại");
        int choice = Input.readIntInRange("Chọn: ", 1, 2);
        switch (choice) {
            case 1:
                System.out.print("Nhập ID của nhân viên: ");
                String id = sc.nextLine();
                employeeList.removeByID(id);
                break;
            case 2:
                System.out.print("Nhập số điện thoại  của nhân viên: ");
                String phone = sc.nextLine();
                employeeList.removeByPhone(phone);
                break;
            default:
                System.out.println("Nhập không hợp lệ.");
                break;
        }

    }

    private void updateEmployee() {
        System.out.print("Nhập ID của nhân viên cần cập nhập thông tin: ");
        String id = sc.nextLine();
        if (!employeeList.containsEmployee(id)) {
            return;
        }

        System.out.print("Nhập họ và tên: ");
        String name = sc.nextLine();
        System.out.print("Nhập số điện thoại: ");
        String phone = "";
        checkPhone(phone);
        Gender gender1 = null;
        try {
            System.out.print("Nhập giới tính: ");
            String gender = sc.nextLine().toUpperCase();
            gender1 = Gender.valueOf(gender);
        } catch (Exception e) {
            System.out.print("Giới tính nhập không hợp lệ");
        }
        Position position = null;
        try {
            System.out.println("Nhập ví trị làm việc: ");
            String pos1 = sc.nextLine().toUpperCase();
            position = Position.valueOf(pos1);
        } catch (Exception e) {
            System.out.println("Nhập vị trí không hợp lệ.");
        }
        System.out.print("Nhập trạng thái làm việc(Onl | OFF) :");
        boolean status = sc.nextBoolean();
        employeeList.updateEmployee(phone, name, phone, gender1, position, status);
    }

    private void searchByID() {
        System.out.print("Nhập ID của nhân viên cần cập nhập thông tin: ");
        String id = sc.nextLine();
        Employee e = employeeList.searchByID(id);
        System.out.println("-------Search--------");
        System.out.println(e);
    }

    private void searchByName() {
        System.out.print("Nhập họ và tên: ");
        String name = sc.nextLine();
        ArrayList<Employee> list = employeeList.searchByName(name);
        System.out.println("-------Search--------");
        for (Employee e : list) {
            System.out.println(e);
        }
    }

    private void checkPhone(String phone) {
        do {
            phone = Input.readString("Số điện thoại: ");
            if (!Validation.isValidPhone(phone)) {
                System.out.println("Số điện thoại không hợp lệ (phải có 10 số, bắt đầu bằng 0)!");
            }
        } while (!Validation.isValidPhone(phone));
    }

}
