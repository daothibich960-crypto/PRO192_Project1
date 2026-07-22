package List;

import Customer.Customer;
import FileIO.FileIO;
import Utils.DateUtil;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerList {

    private List<Customer> list;

    public CustomerList() {
        list = new ArrayList<>();

    }

    public List<Customer> getList() {
        return list;
    }

    public CustomerList(List<Customer> list) {
        this.list = list;
    }
// thêm khách hàng mới 
    public void addCustomer(Customer customer) {
        for (Customer c : list) {
            if (c.getPhone().equals(customer.getPhone())) {
                System.out.println("Thêm khách hàng thất bại !!!");
                return;
            }
        }
        list.add(customer);
    }
// xóa khách hàng 
    public void removeCustomer(String phone) {
        if (list.isEmpty()) {
            return;
        }
        for (Customer c : list) {
            if (c.getPhone().equals(phone)) {
                list.remove(c);
            }
        }
    }
// lấy khách hàng bằng số điện thoại 
    public Customer getCustomer(String phone) {
        if (!list.isEmpty()) {
            for (Customer c : list) {
                if (c.getPhone().equals(phone)) {
                    return c;
                }
            }
        }
        return null;
    }
// kiểm tra xem khách hàng đã tồn tại chưa bằng số điện thoại 
    public boolean containsCustomer(String phone) {
        if (!list.isEmpty()) {
            for (Customer c : list) {
                if (c.getPhone().equals(phone)) {
                    return true;
                }
            }
        }
        return false;
    }
// tìm kiếm khách hàng bằng số điện thoại 
    public Customer searchByPhone(String phone) {
        if (!list.isEmpty()) {
            for (Customer c : list) {
                if (c.getPhone().equalsIgnoreCase(phone.trim())) {
                    return c;
                }
            }
        }
        return null;
    }
// tìm kiếm khách hàng bằng tên 
    public ArrayList<Customer> searchByName(String name) {
        if (!list.isEmpty()) {
            ArrayList<Customer> list1 = new ArrayList<>();
            for (Customer c : list) {
                if (c.getFullName().equalsIgnoreCase(name)) {
                    list1.add(c);
                }
            }
            return list1;
        }
        return null;
    }
// set<cập nhật>  điểm của khách hàng 
    public void addPoint(String phone, double point) {
        if (list.isEmpty()) {
            return;
        }
        for (Customer c : list) {
            if (c.getPhone().equals(phone)) {
                double point1 = c.getPoint() + point;
                c.setPoint(point1);
                return;
            }
        }
    }
// xóa điểm của khách hàng 
    public void deductPoint(String phone, double point) {
        if (list.isEmpty()) {
            return;
        }
        for (Customer c : list) {
            if (c.getPhone().equals(phone)) {
                if (c.getPoint() < point) {
                    System.out.println("Không đủ điểm.");
                    return;
                } else {
                    double point1 = c.getPoint() - point;
                    c.setPoint(point1);
                }
                return;
            }
        }
    }
// lấy tổng khách hàng ở trong danh sách khách hàng 
    public long getToatlCustomer() {
        long count = list.size();
        return count;
    }
// xóa toàn bộ danh sách khách hàng 
    public void clear() {
        list.clear();
    }
// cập nhập thông tin khách hàng 
    public boolean updateCustomer(String name, String phone, String address) {
        if (list.isEmpty()) {
            return false;
        }
        Customer c = getCustomer(phone);
        if (c == null) {
            return false;
        }
        c.setAddress(address);
        c.setFullName(name);
        return true;
    }
    
    
// hiện thị tất cả khách hàng trong dánh sách 
    public void displayAllCustomer() {

    if (list.isEmpty()) {
        System.out.println("Danh sách khách hàng trống!");
        return;
    }

    System.out.println("========================================================================================");
    System.out.printf("%-8s %-25s %-15s %-30s %-12s %-15s%n",
            "ID", "Name", "Phone", "Address", "Point", "Create Date");
    System.out.println("========================================================================================");

    for (Customer c : list) {
        System.out.printf("%-8s %-25s %-15s %-30s %-12.0f %-15s%n",
                c.getCustomerID(),
                c.getFullName(),
                c.getPhone(),
                c.getAddress(),
                c.getPoint(),
                c.getCreateDate());
    }

    System.out.println("========================================================================================");
    System.out.println("Tổng số khách hàng: " + list.size());
}
    // đọc file .txt dự liệu
    public void loadFromFile(String filePath) {
        list.clear();
        List<Customer> loaded = FileIO.readFile(filePath, fields -> {
            Customer c = new Customer(
                    fields[0].trim(), // customerID
                    fields[1].trim(), // fullName
                    fields[2].trim(), // phone
                    fields[3].trim() // address
            );
            c.setPoint(Double.parseDouble(fields[4].trim()));
            c.setCreateDate(DateUtil.parse(fields[5].trim()));
            return c;
        });
        list.addAll(loaded);
    }
    // lưu dữ liệu vào file .txt
    public void saveToFile(String filePath) {
        FileIO.writeFile(filePath, list, c
                -> c.getCustomerID() + "|"
                + c.getFullName() + "|"
                + c.getPhone() + "|"
                + c.getAddress() + "|"
                + c.getPoint() + "|"
                + DateUtil.format(c.getCreateDate())
        );
    }


}
