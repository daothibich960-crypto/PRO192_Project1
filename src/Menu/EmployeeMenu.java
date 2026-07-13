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
            System.out.println("--------Part Time----------");
            employeeList.displayPartTimeEmployee();
        }

    }

    private void addEmployee() {
        System.out.println("Nhân viên mới là:\n1.Nhân viên part time\n2.Nhân viên full time");
        int choice = Input.readIntInRange("Chọn: ", 1, 2);
        sc.nextLine();
        switch (choice) {
            case 1:
                System.out.print("Nhập họ và tên: ");
                String name = sc.nextLine();
                String phone = null ;
                phone = checkPhone(phone);
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
                    PositionMenu();
                    int choice1 = Input.readIntInRange("Chọn:", 1, 7);
                    
                    switch(choice1){
                        case 1:
                            position = Position.MANAGER;
                            break;
                        case 2:
                            position = Position.TEA_MASTER;
                            break;
                        case 3:
                            position = Position.TEA_LADY;
                            break;
                        case 4:
                            position = Position.TEA_ASSISTANT;
                            break;
                        case 5:
                            position = Position.SALES_STAFF;
                            break;
                        case 6:
                            position = Position.CASHIER;
                            break;
                        case 7:
                            position = Position.WAREHOUSE_STAFF;
                            break;
                        default:
                            System.out.println("Giá trị nhập không hợp lệ.");
                            break;
                    }
                } catch (Exception e) {
                    System.out.println("Nhập vị trí không hợp lệ.");
                }
                System.out.print("Nhập trạng thái làm việc(Onl<true> | OFF<false>) :");
                boolean status = sc.nextBoolean();
                String employeeID = IDGenerator.generateEmployeeID();
                Employee e = new PartTimeEmployee(employeeID, name, phone, gender1, position, status);

                employeeList.addEmployee(e);
                break;
            case 2:
                
                System.out.print("Nhập họ và tên: ");
                String name2 = sc.nextLine();
                String phone2 = null;
                phone2 = checkPhone(phone2);
                Gender gender2 = null;
                try {
                    System.out.print("Nhập giới tính: ");
                    String gender = sc.nextLine().toUpperCase();
                    gender2 = Gender.valueOf(gender);
                } catch (Exception e1) {
                    System.out.print("Giới tính nhập không hợp lệ");
                }
                Position position2 = null;
                try {
                    System.out.println("Nhập ví trị làm việc: ");
                    PositionMenu();
                    int choice1 = Input.readIntInRange("Chọn:", 1, 7);
                    
                    switch(choice1){
                        case 1:
                            position2 = Position.MANAGER;
                            break;
                        case 2:
                            position2 = Position.TEA_MASTER;
                            break;
                        case 3:
                            position2 = Position.TEA_LADY;
                            break;
                        case 4:
                            position2 = Position.TEA_ASSISTANT;
                            break;
                        case 5:
                            position2 = Position.SALES_STAFF;
                            break;
                        case 6:
                            position2 = Position.CASHIER;
                            break;
                        case 7:
                            position2 = Position.WAREHOUSE_STAFF;
                            break;
                        default:
                            System.out.println("Giá trị nhập không hợp lệ.");
                            break;
                    }
                   
                } catch (Exception e1) {
                    System.out.println("Nhập vị trí không hợp lệ.");
                }
                System.out.print("Nhập trạng thái làm việc(Onl<true> | OFF<false>) :");
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
    private void PositionMenu(){
        System.out.println("====== Vị trí làm việc =========");
        System.out.println("1.Quản lí cửa hàng\n2.Nghệ nhân pa trà(tea master)"
                + "\n3.Trà nương\n4.Trà nô\n5.Nhân viên bán hàng\n6.Thu ngân\n7.Nhân viên kho hàng");
            
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
                String phone = null;
                phone = checkPhone(phone);
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
        String phone = null;
        phone = checkPhone(phone);
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
            PositionMenu();
                    int choice1 = Input.readIntInRange("Chọn:", 1, 7);
                    
                    switch(choice1){
                        case 1:
                            position = Position.MANAGER;
                            break;
                        case 2:
                            position = Position.TEA_MASTER;
                            break;
                        case 3:
                            position = Position.TEA_LADY;
                            break;
                        case 4:
                            position = Position.TEA_ASSISTANT;
                            break;
                        case 5:
                            position = Position.SALES_STAFF;
                            break;
                        case 6:
                            position = Position.CASHIER;
                            break;
                        case 7:
                            position = Position.WAREHOUSE_STAFF;
                            break;
                        default:
                            System.out.println("Giá trị nhập không hợp lệ.");
                            break;
                    }
        } catch (Exception e) {
            System.out.println("Nhập vị trí không hợp lệ.");
        }
        System.out.print("Nhập trạng thái làm việc(Onl<true> | OFF<false>) :");
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

    private String checkPhone(String phone) {
        do {
            phone = Input.readString("Số điện thoại: ");
            if (!Validation.isValidPhone(phone)) {
                System.out.println("Số điện thoại không hợp lệ (phải có 10 số, bắt đầu bằng 0)!");
            }
        } while (!Validation.isValidPhone(phone));
        return phone;
    }

}
