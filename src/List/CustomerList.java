package List;

import Customer.Customer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerList {

    private List<Customer> list;

    public CustomerList() {
        list = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        for (Customer c : list) {
            if (c.getPhone().equals(customer.getPhone())) {
                System.out.println("Thêm khách hàng thất bại !!!");
                return;
            } else {
                list.add(customer);
            }
        }
    }

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

    public void displayAllCustomer() {
        if (list.isEmpty()) {
            return;
        }
        for (Customer c : list) {
            System.out.println(c);
        }
    }

    public Customer searchByPhone(String phone) {
        if (!list.isEmpty()) {
            for (Customer c : list) {
                if (c.getPhone().equals(phone)) {
                    return c;
                }
            }
        }
        return null;
    }

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

    public void addPoint(String phone, double point) {
        if (list.isEmpty()) {
            return;
        }
        for (Customer c : list) {
            if (c.getPhone().equals(phone)) {
                double point1 = c.getPoint() + point;
                c.setPoint(point1);
            } else {
                return;
            }
        }
    }

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
            } else {
                return;
            }
        }
    }
    public long getToatlCustomer(){
        long count = list.size();
        return count;
    }
    public void clear(){
        list.clear();
    }

}
