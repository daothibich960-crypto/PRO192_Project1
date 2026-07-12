
package Service;

import Customer.Customer;
import List.CustomerList;

public class MemberShipService {

    private CustomerList list;

    public MemberShipService(CustomerList list) {
        this.list = list;
    }
    
    public Customer earnPoint(String phone,double invoiceTotal){
        Customer c = list.getCustomer(phone);
        if (c == null){
            c = registerNewMember(phone,"Khach Hang moi","null");
        }
        double point = calculatePoint(invoiceTotal);
        list.addPoint(phone, point);// thêm điểm dựa vào phone của khách hàng để cộng điểm
        return list.getCustomer(phone);
    }
    private Customer registerNewMember(String phone,String name,String address){
        Customer c = new Customer(phone,name,address);
        list.addCustomer(c);
        return c;
    }
    private double calculatePoint(double invoiceTotal){
         // Quy đổi điểm: ví dụ mỗi 1.000đ = 1 điểm
        double point = invoiceTotal/1000;
        return point;
    }
}
