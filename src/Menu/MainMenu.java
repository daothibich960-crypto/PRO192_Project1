package Menu;

import Inventory.Inventory;
import List.CustomerList;
import List.EmployeeList;
import List.InvoiceList;
import Service.MemberShipService;
import Service.SaleService;
import Service.StatisticService;
import Supplier.SupplierList;
import Utils.Input;
import java.util.Scanner;

public class MainMenu {

    private Inventory inventory;
    private CustomerList customerList;
    private EmployeeList employeeList;
    private InvoiceList invoiceList;
    private MemberShipService memberShipService;
    private SaleService saleService;
    private StatisticService statisticService;
    private SupplierList supplierList;
    Scanner sc = new Scanner(System.in);

    public MainMenu() {
        // Khởi tạo toàn bộ dữ liệu + service 1 lần duy nhất khi chương trình chạy
        this.inventory = new Inventory();
        this.customerList = new CustomerList();
        this.employeeList = new EmployeeList();
        this.invoiceList = new InvoiceList();
        this.supplierList = new SupplierList();

        this.memberShipService = new MemberShipService(customerList);
        this.saleService = new SaleService(invoiceList, customerList, inventory, memberShipService);
        this.statisticService = new StatisticService(invoiceList, employeeList, customerList, inventory);
    }

    public MainMenu(Inventory inventory1, CustomerList customerList1, EmployeeList employeeList,
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
                    new ProductMenu(inventory,supplierList, sc).show();
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
        new MainMenu().run();
    }

}
