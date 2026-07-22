package App;



import Customer.Customer;
import Employee.Employee;
import Inventory.Inventory;
import Invoice.Invoice;
import List.CustomerList;
import List.EmployeeList;
import List.InvoiceList;
import Menu.CustomerMenu;
import Menu.EmployeeMenu;
import Menu.ProductMenu;
import Menu.PurchaseMenu;
import Menu.SaleMenu;
import Menu.StatisticMenu;
import Menu.SupplierMenu;
import Service.MemberShipService;
import Service.SaleService;
import Service.StatisticService;
import Supplier.SupplierList;
import Utils.IDGenerator;
import Utils.Input;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

    public class App {

        private Inventory inventory;
        private CustomerList customerList;
        private EmployeeList employeeList;
        private InvoiceList invoiceList;
        private MemberShipService memberShipService;
        private SaleService saleService;
        private StatisticService statisticService;
        private SupplierList supplierList;
        private Scanner sc = new Scanner(System.in);

        public App() {
            // Khởi tạo toàn bộ dữ liệu + service 1 lần duy nhất khi chương trình chạy
            supplierList = new SupplierList();
            supplierList.loadFromFile("Data/supplier.txt");

            inventory = new Inventory();
            inventory.loadFromFile("Data/product.txt", supplierList);

            customerList = new CustomerList();
            customerList.loadFromFile("Data/customer.txt");
            initCustomerCounter();

            employeeList = new EmployeeList();
            employeeList.loadFromFile("Data/employee.txt");
            initEmployeeCounter();

            invoiceList = new InvoiceList();
            invoiceList.loadFromFile("Data/invoice.txt");
            initInvoiceCounter();

            this.memberShipService = new MemberShipService(customerList);
            this.saleService = new SaleService(invoiceList, customerList, inventory, memberShipService);
            this.statisticService = new StatisticService(invoiceList, employeeList, customerList, inventory);
        }

        public App(Inventory inventory1, CustomerList customerList1, EmployeeList employeeList,
                   InvoiceList invoiceList, SupplierList supplierList, Scanner sc) {
            this.inventory = inventory1;
            this.customerList = customerList1;
            this.employeeList = employeeList;
            this.invoiceList = invoiceList;
            this.supplierList = supplierList;
            this.sc = sc;
            this.memberShipService = new MemberShipService(customerList);
            this.saleService = new SaleService(invoiceList, customerList, inventory, memberShipService);
            this.statisticService = new StatisticService(invoiceList, employeeList, customerList, inventory);
        }

        public void run() {

            boolean running = true;
            while (running) {
                printMenu();
                int choice = Input.readIntInRange("Chọn: ", 0, 7);

                switch (choice) {
                    case 1:
                        new ProductMenu(inventory, supplierList, sc).show();
                        break;
                    case 2:
                        new CustomerMenu(customerList, sc).show();
                        break;
                    case 3:
                        new EmployeeMenu(employeeList, sc).show();
                        break;
                    case 4:
                        new SaleMenu(saleService, sc).show();
                        break;
                    case 5:
                        new StatisticMenu(statisticService, sc).show();
                        break;
                    case 6:
                        new SupplierMenu(supplierList, sc).show();
                        break;
                    case 7:
                        new PurchaseMenu(inventory, supplierList, sc).show();
                        break;
                    case 0:
                        running = false;
                        saveAllData();
                        System.out.println("Cảm ơn đã sử dụng chương trình. Hẹn gặp lại!");
                        break;
                }
            }
        }

        private void printMenu() {
            System.out.println("\n╔══════════════════════════════════════════════════════════╗");
            System.out.println("║                QUẢN LÝ QUÁN TRÀ                          ║");
            System.out.println("╠══════════════════════════════════════════════════════════╣");
            System.out.println("║ 1. Quản lý sản phẩm                                      ║");
            System.out.println("║ 2. Quản lý khách hàng                                    ║");
            System.out.println("║ 3. Quản lý nhân viên                                     ║");
            System.out.println("║ 4. Bán hàng                                              ║");
            System.out.println("║ 5. Thống kê / Báo cáo                                    ║");
            System.out.println("║ 6. Quản lý nhà cung cấp                                  ║"); // Thêm dòng này
            System.out.println("║ 7. Quản lý kho                                           ║"); // Thêm dòng này
            System.out.println("║ 0. Thoát                                                 ║");
            System.out.println("╚══════════════════════════════════════════════════════════╝");
        }

        public static void main(String[] args) {
            System.out.println("Working dir: " + System.getProperty("user.dir"));
            new App().run();
        }

        private void saveAllData() {
            inventory.saveToFile("Data/product.txt");
            supplierList.saveToFile("Data/supplier.txt");
            customerList.saveToFile("Data/customer.txt");
            employeeList.saveToFile("Data/employee.txt");
            invoiceList.saveToFile("Data/invoice.txt");
        }

        private void initInvoiceCounter() {
            int max = 0;
            for (Invoice inv : invoiceList.getList()) {
                int num = IDGenerator.extractNumber(inv.getInvoiceID());
                if (num > max) {
                    max = num;
                }
            }
            IDGenerator.initInvoiceCounter(max);
        }

        private void initEmployeeCounter() {
            int max = 0;
            for (Employee e : employeeList.getList()) {
                int num = IDGenerator.extractNumber(e.getEmployeeID());
                if (num > max) {
                    max = num;
                }
            }
            IDGenerator.initEmployeeCounter(max);
        }

        private void initCustomerCounter() {
            int max = 0;
            for (Customer c : customerList.getList()) {
                int num = IDGenerator.extractNumber(c.getCustomerID()); // đổi tên getter đúng với Customer.java của bạn
                if (num > max) {
                    max = num;
                }
            }
            IDGenerator.initCustomerCounter(max);
        }

    }


