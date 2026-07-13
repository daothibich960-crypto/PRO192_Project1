import java.util.Scanner;

import Inventory.Inventory;
import List.CustomerList;
import List.EmployeeList;
import List.InvoiceList;
import Supplier.SupplierList;
import Menu.CustomerMenu;
import Menu.EmployeeMenu;
import Menu.MainMenu;
import Menu.ProductMenu;
import Menu.PurchaseMenu;
import Menu.SaleMenu;
import Menu.StatisticMenu;
import Service.MemberShipService;
import Service.SaleService;
import Service.StatisticService;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Scanner DUY NHẤT cho toàn bộ chương trình

        Inventory inventory = new Inventory();
        SupplierList supplierList = new SupplierList();
        CustomerList customerList = new CustomerList();
        EmployeeList employeeList = new EmployeeList();
        InvoiceList invoiceList = new InvoiceList();

        MemberShipService memberShipService = new MemberShipService(customerList);
        SaleService saleService = new SaleService(invoiceList, customerList, inventory, memberShipService);
        StatisticService statisticService = new StatisticService(invoiceList, employeeList, customerList, inventory);

        MainMenu menu = new MainMenu(inventory,customerList,employeeList,invoiceList);
        menu.run();
    }
}