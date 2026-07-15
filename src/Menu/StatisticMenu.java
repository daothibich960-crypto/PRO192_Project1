
package Menu;

import Customer.Customer;
import Employee.Employee;
import Product.Product;
import Service.StatisticService;
import Utils.Formatter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class StatisticMenu {
    private StatisticService statisticService;
    private Scanner scanner;

    public StatisticMenu(StatisticService statisticService, Scanner scanner) {
        this.statisticService = statisticService;
        this.scanner = scanner;
    }

    public void show() {
        int choice;
        do {
            System.out.println("\n===== THỐNG KÊ / BÁO CÁO =====");
            System.out.println("1. Doanh thu theo ngày");
            System.out.println("2. Doanh thu theo tháng");
            System.out.println("3. Doanh thu theo khoảng thời gian");
            System.out.println("4. Top khách hàng thân thiết");
            System.out.println("5. Top nhân viên bán hàng");
            System.out.println("6. Top sản phẩm bán chạy");
            System.out.println("7. Sản phẩm bán chậm nhất");
            System.out.println("8. Báo cáo tổng hợp doanh thu");
            System.out.println("9. Báo cáo nhân viên");
            System.out.println("10. Báo cáo khách hàng");
            System.out.println("11. Báo cáo sản phẩm");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1: revenueByDate(); break;
                case 2: revenueByMonth(); break;
                case 3: revenueBetween(); break;
                case 4: topCustormers(); break;
                case 5: topEmployees(); break;
                case 6: topSellingProducts(); break;
                case 7: leastSellingProducts(); break;
                case 8: statisticService.printRevenueReport(); break;
                case 9: statisticService.printEmployeeReport(); break;
                case 10: statisticService.printCustormerReport(); break;
                case 11: statisticService.printProductReport(); break;
                case 0: System.out.println("Quay lại menu chính..."); break;
                default: System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }

    private void revenueByDate() {
        LocalDate date = readDate("Nhập ngày (yyyy-MM-dd): ");
        double revenue = statisticService.getRevenueByDate(date);
        int count = statisticService.getTotalInvoiceByDate(date);
        System.out.println("Doanh thu ngày " + date + ": " + Formatter.currency(revenue) + "VND (" + count + " hóa đơn)");
    }

    private void revenueByMonth() {
        System.out.print("Tháng: ");
        int month = Integer.parseInt(scanner.nextLine());
        System.out.print("Năm: ");
        int year = Integer.parseInt(scanner.nextLine());

        double revenue = statisticService.getRevenueByMonth(month, year);
        int count = statisticService.getTotalInvoiceByMonth(month, year);
        System.out.println("Doanh thu tháng " + month + "/" + year + ": " +Formatter.currency(revenue) + "đ (" + count + " hóa đơn)");
    }

    private void revenueBetween() {
        LocalDate from = readDate("Từ ngày (yyyy-MM-dd): ");
        LocalDate to = readDate("Đến ngày (yyyy-MM-dd): ");
        double revenue = statisticService.getRevenueBetween(from, to);
        System.out.println("Doanh thu từ " + from + " đến " + to + ": " + Formatter.currency(revenue) + "đ");
    }

    private void topCustormers() {
        System.out.print("Xem top bao nhiêu khách hàng: ");
        int top = Integer.parseInt(scanner.nextLine());

        ArrayList<Customer> list = statisticService.getTopCustomer(top);
        int i = 1;
        for (Customer c : list) {
            System.out.println((i++) + ". " + c.toString());
        }
    }

 private void topEmployees() {

    System.out.print("Xem top bao nhiêu nhân viên: ");
    int top = Integer.parseInt(scanner.nextLine());

    ArrayList<Employee> list = statisticService.getTopEmployee(top);

    if (list.isEmpty()) {
        System.out.println("Không có dữ liệu!");
        return;
    }

    System.out.println("\n===================================== TOP NHÂN VIÊN =====================================");
    System.out.printf("%-5s %-8s %-22s %-18s %-10s %-18s%n",
            "Top", "ID", "Tên nhân viên", "Chức vụ", "Hóa đơn", "Doanh thu");
    System.out.println("------------------------------------------------------------------------------------------");

    int rank = 1;

    for (Employee e : list) {

        int invoiceCount = statisticService.getInvoiceCountEmployee(e.getEmployeeID());
        double revenue = statisticService.getRevenueEmployee(e.getEmployeeID());

        System.out.printf("%-5d %-8s %-22s %-18s %-10d %-18s%n",
                rank++,
                e.getEmployeeID(),
                e.getFullName(),
                e.getPosition(),
                invoiceCount,
                Formatter.currency(revenue));
    }

    System.out.println("==========================================================================================");
}
private void topSellingProducts() {

    System.out.print("Xem top bao nhiêu sản phẩm: ");
    int top = Integer.parseInt(scanner.nextLine());

    ArrayList<Product> list = statisticService.getTopSellingProduct(top);

    if (list.isEmpty()) {
        System.out.println("Không có dữ liệu!");
        return;
    }

    System.out.println("\n====================================== TOP SẢN PHẨM BÁN CHẠY ======================================");
    System.out.printf("%-5s %-10s %-30s %-15s %-12s %-15s%n",
            "Top", "ID", "Tên sản phẩm", "Đơn giá", "Đã bán", "Tồn kho");
    System.out.println("---------------------------------------------------------------------------------------------------");

    int rank = 1;

    for (Product p : list) {

        int sold = statisticService.getSoldQuantity(p.getProductId());

        System.out.printf("%-5d %-10s %-30s %-15s %-12d %-15d%n",
                rank++,
                p.getProductId(),
                p.getProductName(),
                Formatter.currency(p.getSellingPrice()),
                sold,
                p.getStockQuantity());
    }

    System.out.println("===================================================================================================");
}
private void leastSellingProducts() {

    System.out.print("Xem bao nhiêu sản phẩm bán chậm nhất: ");
    int top = Integer.parseInt(scanner.nextLine());

    ArrayList<Product> list = statisticService.getLeastSellingProduct(top);

    if (list.isEmpty()) {
        System.out.println("Không có dữ liệu!");
        return;
    }

    System.out.println("\n=================================== SẢN PHẨM BÁN CHẬM NHẤT ===================================");
    System.out.printf("%-5s %-10s %-30s %-15s %-12s %-15s%n",
            "Top", "ID", "Tên sản phẩm", "Đơn giá", "Đã bán", "Tồn kho");
    System.out.println("-----------------------------------------------------------------------------------------------");

    int rank = 1;

    for (Product p : list) {

        int sold = statisticService.getSoldQuantity(p.getProductId());

        System.out.printf("%-5d %-10s %-30s %-15s %-12d %-15d%n",
                rank++,
                p.getProductId(),
                p.getProductName(),
                Formatter.currency(p.getSellingPrice()),
                sold,
                p.getStockQuantity());
    }

    System.out.println("===============================================================================================");
}
  
    // Hàm hỗ trợ đọc ngày, tránh lặp code ở nhiều chỗ
    private LocalDate readDate(String prompt) {
    while (true) {
        try {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            return LocalDate.parse(input); // Định dạng mặc định: yyyy-MM-dd

        } catch (DateTimeParseException e) {
            System.out.println("❌ Ngày không hợp lệ!");
            System.out.println("Vui lòng nhập theo định dạng: yyyy-MM-dd");
            System.out.println("Ví dụ: 2026-07-15\n");
        }
    }
}
    
}
