package Service;

import Customer.Customer;
import Employee.Employee;
import Inventory.Inventory;
import Invoice.Invoice;
import Invoice.InvoiceDetail;
import List.CustomerList;
import List.EmployeeList;
import List.InvoiceList;
import Product.Product;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StatisticService {

    private InvoiceList invoiceList;
    private EmployeeList employeeList;
    private CustomerList customerList;
    private Inventory inventory;

    public StatisticService(InvoiceList invoiceList, EmployeeList employeeList, CustomerList customerList, Inventory inventory) {
        this.invoiceList = invoiceList;
        this.employeeList = employeeList;
        this.customerList = customerList;
        this.inventory = inventory;
    }

    
    public double getRevenueByDate(LocalDate date) {
        double total = 0;
        for (Invoice i : invoiceList.getList()) {
            if (i.getInvoiceDate().toLocalDate().equals(date)) {
                total += i.getTotalAmount();
            }
        }
        return total;
    }

    public double getRevenueByMonth(int month, int year) {
        double total = 0;
        for (Invoice i : invoiceList.getList()) {
            LocalDate d = i.getInvoiceDate().toLocalDate();
            if (d.getMonthValue() == month && d.getYear() == year) {
                total += i.getTotalAmount();
            }
        }
        return total;
    }

    public double getRevenueBetween(LocalDate from, LocalDate to) {
        double total = 0;
        for (Invoice i : invoiceList.getList()) {
            LocalDate d = i.getInvoiceDate().toLocalDate();
            if (!d.isBefore(from) && !d.isAfter(to)) {
                total += i.getTotalAmount();
            }
        }
        return total;
    }

    public int getTotalInvoice() {
        int total = invoiceList.getTotalInvoice();
        return total;
    }

    public int getTotalInvoiceByDate(LocalDate date) {
        int total = 0;
        for (Invoice i : invoiceList.getList()) {
            if (i.getInvoiceDate().toLocalDate() == date) {
                total += 1;
            }
        }
        return total;
    }

    public int getTotalInvoiceByMonth(int month, int year) {
        int total = 0;
        for (Invoice i : invoiceList.getList()) {
            LocalDate d = i.getInvoiceDate().toLocalDate();
            if (d.getMonthValue() == month && d.getYear() == year) {
                total++;
            }
        }
        return total;
    }

    public int getTotalInvoiceBetWeen(LocalDate from, LocalDate to) {
        int total = 0;
        for (Invoice i : invoiceList.getList()) {
            LocalDate d = i.getInvoiceDate().toLocalDate();
            if (!d.isBefore(from) && !d.isAfter(to)) {
                total++;
            }
        }
        return total;
    }

    public int getTotalCustomer() {
        int total = (int) customerList.getToatlCustomer();
        return total;
    }

    public int getTotalCustomerByDate(LocalDate date) {
        ArrayList<String> phones = new ArrayList<>();
        int le = 0;
        for (Invoice i : invoiceList.getList()) {
            if (i.getInvoiceDate().toLocalDate() == date) {
                String phone = i.getCustomerPhone();
                if (phone != null && !phone.isEmpty()) {
                    phones.add(phone);
                } else {
                    le += 1;
                }
            }

        }
        return (phones.size() + le);
    }

    public int getTotalCustomerByMonth(int month, int year) {
        ArrayList<String> memberPhones = new ArrayList<>();
        int walkInCount = 0; // đếm khách lẻ, không phone

        for (Invoice inv : invoiceList.getList()) {
            LocalDate d = inv.getInvoiceDate().toLocalDate();
            if (d.getMonthValue() != month || d.getYear() != year) {
                continue;
            }

            String phone = inv.getCustomerPhone();
            if (phone != null && !phone.isEmpty()) {
                // Khách thành viên -> gộp trùng theo phone
                if (!memberPhones.contains(phone)) {
                    memberPhones.add(phone);
                }
            } else {
                // Khách lẻ -> không có cách gộp trùng, mỗi hóa đơn tính là 1 khách
                walkInCount++;
            }
        }
        return memberPhones.size() + walkInCount;
    }

    // Khách hàng thân thiết 
    public ArrayList<Customer> getTopCustomer(int top) {
        ArrayList<Customer> list = new ArrayList<>();
        for (Customer c : customerList.getList()) {
            list.add(c);
        }
        list.sort((c1, c2) -> Double.compare(c2.getPoint(), c1.getPoint()));
        ArrayList<Customer> list1 = new ArrayList<>();
        for (int i = 0; i < Math.min(top, list.size()); i++) {
            list1.add(list.get(i));
        }
        return list1;
    }

    public ArrayList<Employee> getTopEmployee(int top) {
        ArrayList<Employee> list = new ArrayList<>();
        HashMap<String, Double> revenue = new HashMap<>();
        for (Invoice i : invoiceList.getList()) {
            String emID = i.getInvoiceID();
            revenue.put(emID, revenue.getOrDefault(emID, 0.0) + i.getTotalAmount());
        }
        List<Employee> em = employeeList.getList();
        em.sort((e1, e2) -> Double.compare(
                revenue.getOrDefault(e2.getEmployeeID(), 0.0),
                revenue.getOrDefault(e1.getEmployeeID(), 0.0))
        );
        ArrayList<Employee> list1 = new ArrayList<>();
        for (int i = 0; i < Math.min(top, em.size()); i++) {
            list1.add(em.get(i));
        }
        return list1;
    }

    public double getRevenueEmployee(String employeeID) {
        double total = 0;
        for (Invoice i : invoiceList.getList()) {
            if (i.getEmployeeID().equalsIgnoreCase(employeeID)) {
                total += i.getTotalAmount();
            }
        }
        return total;
    }

    public int getInvoiceCountEmployee(String employeeID) {
        int count = 0;
        for (Invoice i : invoiceList.getList()) {
            if (i.getEmployeeID().equalsIgnoreCase(employeeID)) {
                count++;
            }
        }
        return count;
    }
    // Hàm hỗ trợ nội bộ: gộp số lượng bán ra của từng productID trên toàn bộ hóa đơn

    private HashMap<String, Integer> countSoldQuantityAll() {
        HashMap<String, Integer> sold = new HashMap<>();
        for (Invoice i : invoiceList.getList()) {
            {
                for (InvoiceDetail d : i.getDetail()) {
                    sold.put(d.getProductID(),
                            sold.getOrDefault(d.getProductID(), 0) + d.getQuantity());
                }
            }

        }
        return sold;
    }
    // lấy số lượng sản phâm đã bán ra theo productID
    public int getSoldQuantity(String productID){
        int count = countSoldQuantityAll().getOrDefault(productID, 0);
        return count;
    }
    // lấy danh sách sản phẩm bán chạy nhất 
    public ArrayList<Product> getTopSellingProduct(int top){
        HashMap<String ,Integer> sold = countSoldQuantityAll();
        ArrayList<String> productIDs = new ArrayList<>(sold.keySet());//
        productIDs.sort((p1,p2) -> sold.get(p2) - sold.get(p1));
        ArrayList<Product> list1 = new ArrayList<>();
//        for (int i = 0; i < Math.min(top, productIDs.size()); i++){
//            Product p = inventory.getProduct(productIDs.get(i));
//            if (p != null) list1.add(p);
//        }
        return list1;
    }
    // lấy danh sách sản phẩm bán được ít nhất
    public ArrayList<Product> getLeastSellingProduct(int top){
        HashMap<String,Integer> sold = countSoldQuantityAll();
        
        ArrayList<String> productIDs = new ArrayList<>(sold.keySet());
        productIDs.sort((p1,p2) -> sold.get(p1) - sold.get(p2));
        
        ArrayList<Product> list1 = new ArrayList<>();
//        for (int i = 0; i < Math.min(top, productIDs.size()); i++){
//            Product p = inventory.getProduct(productIDs.get(i));
//            if (p != null) list1.add(p);
//        }
        return list1;
    }
    public void printRevenueReport() {
        System.out.println("===== BÁO CÁO DOANH THU =====");
        System.out.println("Tổng số hóa đơn: " + getTotalInvoice());
        double total = 0;
        for (Invoice inv : invoiceList.getList()) {
            total += inv.getTotalAmount();
        }
        System.out.println("Tổng doanh thu: " + total);
    }

    public void printEmployeeReport() {
        System.out.println("===== BÁO CÁO NHÂN VIÊN =====");
        for (Employee e : employeeList.getList()) {
            System.out.println(e.getEmployeeID() + " - Doanh thu: "
                    + getRevenueEmployee(e.getEmployeeID())
                    + " - Số hóa đơn: " + getInvoiceCountEmployee(e.getEmployeeID()));
        }
    }

    public void printCustormerReport() {
        System.out.println("===== BÁO CÁO KHÁCH HÀNG =====");
        System.out.println("Tổng số khách hàng: " + getTotalCustomer());
        System.out.println("Top khách hàng thân thiết:");
        for (Customer c : getTopCustomer(5)) {
            System.out.println(c.toString());
        }
    }

    public void printProductReport() {
        System.out.println("===== BÁO CÁO SẢN PHẨM =====");
        System.out.println("Top sản phẩm bán chạy:");
        for (Product p : getTopSellingProduct(5)) {
            System.out.println(p.toString() + " - Đã bán: " + getSoldQuantity(p.getProductId()));
        }
    }

   
}

