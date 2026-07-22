package Menu;

import Customer.Customer;
import List.CustomerList;
import Utils.IDGenerator;
import Utils.Input;
import Utils.Validation;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerMenu {

    private static final String FILE_PATH = "Data/customer.txt";
    private CustomerList customerList;
    private Scanner scanner;

    public CustomerMenu(CustomerList custormerList, Scanner scanner) {
        this.customerList = custormerList;
        this.scanner = scanner;
    }

    public void show() {
        
        int choice;
        do {
            System.out.println("\n===== QUẢN LÝ KHÁCH HÀNG =====");
            System.out.println("1. Thêm khách hàng");
            System.out.println("2. Xóa khách hàng");
            System.out.println("3. Cập nhật khách hàng");
            System.out.println("4. Tìm theo số điện thoại");
            System.out.println("5. Tìm theo tên");
            System.out.println("6. Hiển thị tất cả khách hàng");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    removeCustomer();
                    break;
                case 3:
                    updateCustomer();
                    break;
                case 4:
                    searchByPhone();
                    break;
                case 5:
                    searchByName();
                    break;
                case 6:
                    customerList.displayAllCustomer();
                    break;
                case 0:
                    System.out.println("Quay lại menu chính...");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }

    // thêm khách hàng mới vào dánh sách khách hàng 
    private void addCustomer() {
        String phone =null;
        phone = checkPhone(phone);
        

        if (customerList.containsCustomer(phone)) {
            System.out.println("Số điện thoại này đã là thành viên!");
            return;
        }

        System.out.print("Họ tên: ");
        String fullName = scanner.nextLine();
        System.out.print("Địa chỉ: ");
        String address = scanner.nextLine();

        String custormerID = IDGenerator.generateCustomerID();
        Customer customer = new Customer(custormerID,fullName, phone, address);
        customerList.addCustomer(customer);
        System.out.println("Thêm khách hàng thành công!");
    }
// kiểm tra xem số điện thoại đã nhập dúng hay chưa 
    private String checkPhone(String phone) {
        do {
            phone = Input.readString("Số điện thoại: ");
            if (!Validation.isValidPhone(phone)) {
                System.out.println("Số điện thoại không hợp lệ (phải có 10 số, bắt đầu bằng 0)!");
            }
        } while (!Validation.isValidPhone(phone));
        return phone;
    }
// xóa khách hàng khỏi dánh sách khách hàng bằng số điện thoại 
    private void removeCustomer() {
        System.out.print("Nhập số điện thoại cần xóa: ");
        String phone = null;
        phone =  checkPhone(phone);

        if (!customerList.containsCustomer(phone)) {
            System.out.println("Không tìm thấy khách hàng!");
            return;
        }
        customerList.removeCustomer(phone);
        System.out.println("Xóa thành công!");
    }
// cập nhập thông tin khách hàng bằng số điện thoại 
    private void updateCustomer() {
        System.out.print("Nhập số điện thoại cần cập nhật: ");
        String phone = null;
        phone = checkPhone(phone);

        if (!customerList.containsCustomer(phone)) {
            System.out.println("Không tìm thấy khách hàng!");
            return;
        }

        System.out.print("Nhập tên mới: ");
        String fullName = scanner.nextLine();
        System.out.print("Nhập địa chỉ: ");
        String address = scanner.nextLine();

        customerList.updateCustomer(fullName, phone, address);
        System.out.println("Cập nhật thành công!");
    }

    // tìm kiếm khách hàng bằng số điện thoại 
    private void searchByPhone() {
        String phone =null;
        phone =  checkPhone(phone);

        Customer c = customerList.searchByPhone(phone);
        if (c == null) {
            System.out.println("Không tìm thấy khách hàng!");
        } 
        System.out.println(c.toString());
    }
// tìm kiếm khách hàng bằng tên 
    private void searchByName() {
        System.out.print("Nhập tên khách hàng: ");
        String name = scanner.nextLine();

        ArrayList<Customer> results = customerList.searchByName(name);
        if (results.isEmpty()) {
            System.out.println("Không tìm thấy khách hàng nào!");
        } else {
            for (Customer c : results) {
                System.out.println(c.toString());
            }
        }
    }

}
