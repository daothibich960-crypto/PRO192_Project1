
package Service;

import Customer.Customer;
import List.CustomerList;

public class MemberShipService {

    private CustomerList list;

    public MemberShipService(CustomerList list) {
        this.list = list;
    }
    
    // cộng điểm cho khách hàng sau khi thanh toán hóa đơn thành công 
    public Customer earnPoint(String phone,double invoiceTotal){
        Customer c = list.getCustomer(phone);
        if (c == null){
            c = registerNewMember(phone,"Khach Hang moi","null");
        }
        double point = calculatePoint(invoiceTotal);
        list.addPoint(phone, point);// thêm điểm dựa vào phone của khách hàng để cộng điểm
        return list.getCustomer(phone);
    }
    // tạo khách hàng mới nếu chưa tồn tại khách hàng trong danh sách 
    private Customer registerNewMember(String phone,String name,String address){
        Customer c = new Customer(name,phone, address);
        list.addCustomer(c);
        return c;
    }
    // cách tính điểm 
    private double calculatePoint(double invoiceTotal){
         // Quy đổi điểm: ví dụ mỗi 10.000đ = 1 điểm
        double point = invoiceTotal/10000;
        return point;
    }
}
