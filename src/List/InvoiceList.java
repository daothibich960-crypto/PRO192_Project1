package List;

import Invoice.Invoice;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InvoiceList {

    private List<Invoice> list;

    public InvoiceList() {
        list = new ArrayList<>();
    }

    public void addInvoice(Invoice invoice) {
        Invoice exis = findInvoice(invoice.getInvoiceID());
        if (exis == null){
            list.add(exis);
        }else{
            System.out.println("Hóa đơn đã tồn tại.");
        }

    }
    public void removeInvoice(String invoiceID){
        Invoice exis = findInvoice(invoiceID);
        if (exis != null){
            list.remove(exis);
        }
        
    }
    public boolean containsInvoice(String invoiceID){
        Invoice exis = findInvoice(invoiceID);
        if (exis == null) return false;
        return true;
    }
    public void displayAllInvoice(){
        for (Invoice i: list){
            System.out.println(i);
        }
    }

    
    public Invoice findInvoice(String invoiceID) {
        if (!list.isEmpty()) {
            for (Invoice i : list) {
                if (i.getInvoiceID().equalsIgnoreCase(invoiceID)) {
                    return i;
                }
            }
        }
        return null;
    }
    public Invoice searchByInvoiceID(String invoiceID){
        Invoice exis = findInvoice(invoiceID);
        if (exis != null) return exis;
        return null;
    }
    public Invoice searchByEmployeeID(String employeeID){
        if (!list.isEmpty()){
            for (Invoice i: list){
                if (i.getEmployeeID().equalsIgnoreCase(employeeID)){
                    return i;
                }
            }
        }
        return null;
    }
    public Invoice searchByCustomerPhone(String phone){
        if (!list.isEmpty()){
            for (Invoice i: list){
                if (i.getCustomerPhone().equals(phone)){
                    return i;
                }
            }
        }
        return null;
    }
    public ArrayList<Invoice> searchByDate(LocalDate date){
        if (!list.isEmpty()){
            ArrayList<Invoice> list1 = new ArrayList<>();
            for (Invoice i: list){
                if (i.getInvoiceDate().equals(date)){
                    list1.add(i);
                }
            }
            return list1;
        }
        return null;
    }
    
    public int getTotalInvoice(){
        int count = list.size();
        return count;
    }
    
    
    
    
    
    
    
    
    
    

}
