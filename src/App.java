
import Customer.Customer;
import Employee.FullTimeEmployee;
import Employee.PartTimeEmployee;
import Enum.Gender;
import Enum.Position;
import Invoice.Invoice;
import List.CustomerList;
import List.EmployeeList;

import List.InvoiceList;
import Menu.CustomerMenu;
import Menu.MainMenu;
import Product.Product;
import Product.Tea;
import java.util.Scanner;


public class App {
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
        System.out.println("=== TEST CUSTOMER MENU ===");

        CustomerList custormerList = new CustomerList();

        // Thêm sẵn vài khách hàng mẫu để test tìm kiếm/cập nhật/xóa
        custormerList.addCustomer(new Customer("KH001", "Nguyễn Văn An", "0912345678",
                "123 Lê Lợi, Q1"));
        custormerList.addCustomer(new Customer("KH002", "Trần Thị Bình", "0923456789",
                "45 Nguyễn Huệ, Q1"));

        System.out.println("Đã nạp " + custormerList.getToatlCustomer() + " khách hàng mẫu.\n");
         System.out.println("------------");
        custormerList.displayAllCustomer();

        CustomerMenu customerMenu = new CustomerMenu(custormerList,sc);
        customerMenu.show();

        System.out.println("Kết thúc test.");
    }
}
