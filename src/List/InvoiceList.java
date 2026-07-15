package List;

import Enum.InvoiceStatus;
import Enum.PayMethod;
import FileIO.FileIO;
import Invoice.Invoice;
import Invoice.InvoiceDetail;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InvoiceList {

    private List<Invoice> list;

    public InvoiceList() {
        list = new ArrayList<>();
    }

    public List<Invoice> getList() {
        return list;
    }

    
    public void addInvoice(Invoice invoice) {
        Invoice exis = findInvoice(invoice.getInvoiceID());
        if (exis == null){
            list.add(invoice);
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
    
     public void loadFromFile(String filePath) {
        list.clear();
        List<Invoice> loaded = FileIO.readFile(filePath, fields -> {

            String invoiceID     = fields[0].trim();
            String employeeID    = fields[1].trim();
            String customerPhone = fields[2].trim();
            LocalDateTime invoiceDate = LocalDateTime.parse(fields[3].trim());
            String payMethodStr  = fields[4].trim();
            String statusStr     = fields[5].trim();
            double totalAmount   = Double.parseDouble(fields[6].trim());

            Invoice inv = new Invoice(invoiceID, employeeID, customerPhone);
            inv.setInvoiceDate(invoiceDate);

            if (!payMethodStr.equalsIgnoreCase("NONE")) {
                String payMethodUpper = payMethodStr.toUpperCase();
                if (payMethodUpper.equals("TRANSFER")) {
                    inv.setPayMethod(PayMethod.BANK_TRANSFER);
                } else {
                    inv.setPayMethod(PayMethod.valueOf(payMethodUpper));
                }
            }

            String statusUpper = statusStr.toUpperCase();
            if (statusUpper.equals("COMPLETED") || statusUpper.equals("COMPLETE")) {
                inv.setStatus(InvoiceStatus.COMPLETE);
            } else if (statusUpper.equals("CANCELLED") || statusUpper.equals("CANCEL")) {
                inv.setStatus(InvoiceStatus.CANCEL);
            } else {
                inv.setStatus(InvoiceStatus.valueOf(statusUpper));
            }

            // Nếu có block detail (fields[7]) thì parse tiếp
            if (fields.length > 7 && !fields[7].trim().isEmpty()) {
                String detailsBlock = fields[7].trim();
                String[] detailItems = detailsBlock.split(";");
                ArrayList<InvoiceDetail> detailList = new ArrayList<>();

                for (String item : detailItems) {
                    String[] d = item.split(",");
                    InvoiceDetail detail = new InvoiceDetail();
                    detail.setProductID(d[0].trim());
                    detail.setProductName(d[1].trim());
                    detail.setQuantity(Integer.parseInt(d[2].trim()));
                    detail.setPrice(Double.parseDouble(d[3].trim()));
                    detail.setSubTotal(Double.parseDouble(d[4].trim()));
                    detailList.add(detail);
                }
                inv.setDetail(detailList);
            }

            // Đảm bảo totalAmount khớp với chi tiết đã load (phòng khi file bị sửa tay sai)
            inv.calculateTotal();

            return inv;
        });
        list.addAll(loaded);
    }

    public void saveToFile(String filePath) {
        FileIO.writeFile(filePath, list, inv -> {

            String payMethodStr = (inv.getPayMethod() != null) ? inv.getPayMethod().toString() : "NONE";

            StringBuilder detailsBlock = new StringBuilder();
            ArrayList<InvoiceDetail> details = inv.getDetail();
            for (int i = 0; i < details.size(); i++) {
                InvoiceDetail d = details.get(i);
                detailsBlock.append(d.getProductID()).append(",")
                        .append(d.getProductName()).append(",")
                        .append(d.getQuantity()).append(",")
                        .append(d.getPrice()).append(",")
                        .append(d.getSubTotal());
                if (i < details.size() - 1) {
                    detailsBlock.append(";");
                }
            }

            return inv.getInvoiceID()     + "|" +
                   inv.getEmployeeID()    + "|" +
                   inv.getCustomerPhone() + "|" +
                   inv.getInvoiceDate()   + "|" +
                   payMethodStr           + "|" +
                   inv.getStatus()        + "|" +
                   inv.getTotalAmount()   + "|" +
                   detailsBlock.toString();
        });
    }
    
    
    
    
    
    
    
    

}
