package Menu;

import Enum.PayMethod;
import Invoice.Invoice;
import Product.Accessory;
import Product.Product;
import Product.Tea;
import Product.TeaCup;
import Product.TeaPot;
import Service.SaleService;
import Utils.Input;
import java.util.Scanner;

public class SaleMenu {

    private SaleService saleService;
    private Scanner scanner;

    public SaleMenu(SaleService saleService, Scanner scanner) {
        this.saleService = saleService;
        this.scanner = scanner;
    }

    public void show() {
        System.out.print("Nhập mã nhân viên bán hàng: ");
        String employeeID = scanner.nextLine();

        Invoice invoice = saleService.createInvoice(employeeID);
        System.out.println("Đã tạo hóa đơn: " + invoice.getInvoiceID());

        boolean finished = false;
        while (!finished) {
            System.out.println("\n===== BÁN HÀNG - Hóa đơn " + invoice.getInvoiceID() + " =====");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Xóa sản phẩm khỏi hóa đơn");
            System.out.println("3. Cập nhật số lượng");
            System.out.println("4. Xem hóa đơn hiện tại");
            System.out.println("5. Thanh toán");
            System.out.println("6. Hủy hóa đơn");
            System.out.println("0. Quay lại menu chính (hủy bỏ nếu chưa thanh toán)");
            

            int choice = Input.readIntInRange("Chọn: ",0,6);
            switch (choice) {
                case 1:
                    System.out.print("Nhập Product ID của sản phẩm: ");
                    String productID = scanner.nextLine();
                    System.out.print("Nhập tên sản phẩm: ");
                    String name = scanner.nextLine();
                    System.out.print("Nhập số lượng sản phẩm: ");
                    int quantity = scanner.nextInt();
                    Product product = null;
                    if (productID.substring(0, 3).equalsIgnoreCase("TEA")) {
                        product = new Tea(productID, name);

                    } else if (productID.substring(0, 3).equalsIgnoreCase("CUP")) {
                        product = new TeaCup(productID, name);
                    } else if (productID.substring(0, 3).equalsIgnoreCase("POT")) {
                        product = new TeaPot(productID, name);
                    } else if (productID.substring(0, 3).equalsIgnoreCase("ACC")) {
                        product = new Accessory(productID, name);
                    }
                    invoice.addProduct(productID, quantity, product);
                case 2:
                    System.out.print("Nhập Product ID của sản phẩm: ");
                    String productID1 = scanner.nextLine();
                    invoice.removePrroduct(productID1);
                    break;
                case 3:
                    System.out.print("Nhập Product ID của sản phẩm: ");
                    String productID2 = scanner.nextLine();
                    System.out.print("Nhập số lượng sản phẩm (thêm >= 0 || xóa < 0 ): ");
                    int quantity2 = scanner.nextInt();
                    if (quantity2 >= 0) {
                        invoice.updateAddQuantity(productID2, quantity2);
                    }else{
                        invoice.updateDesceaseQuantity(productID2, quantity2);
                    }
                    break;
                case 4:
                    invoice.displayInvoice();
                    break;
                case 5:
                    if (checkout(invoice)) {
                        finished = true; // hóa đơn đã lưu -> kết thúc phiên bán hàng này
                    }
                    break;
                case 6:
                    saleService.cancelInvoice(invoice);
                    System.out.println("Đã hủy hóa đơn.");
                    finished = true;
                    break;
                case 0:
                    // Thoát mà chưa checkout -> hóa đơn tạm này chưa từng lưu vào invoiceList,
                    // nên chỉ cần thoát vòng lặp, không cần gọi gì thêm
                    System.out.println("Thoát, hóa đơn chưa lưu.");
                    finished = true;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    

    private boolean checkout(Invoice invoice) {
        System.out.print("Số điện thoại khách hàng (Enter nếu khách lẻ): ");
        String phone = scanner.nextLine();
        if (!phone.isEmpty()) {
            invoice.setCustomerPhone(phone);
        }

        System.out.println("Hình thức thanh toán: 1. Tiền mặt  2. Chuyển khoản  3. Thẻ 4.Other");
        System.out.print("Chọn: ");
        int payChoice = Integer.parseInt(scanner.nextLine());
        PayMethod  pay = null;
        switch(payChoice){
            case 1:
                pay = PayMethod.CASH;
                break;
            case 2:
                pay = PayMethod.BANK_TRANSFER;
                break;
            case 3:
                pay = PayMethod.CREDIT_CARD;
                break;
            case 4:
                pay = PayMethod.OTHER;
                break;
            default:
                pay = PayMethod.CASH;
                break;
        }
        
        invoice.setPayMethod(pay);
        System.out.println("Tình trạng thanh toán:\n1.Thành công(>0)\n2.Thất bại(<0)");
        int status = scanner.nextInt();
        
        boolean success = saleService.checkout(invoice,status);
        if (success) {
            System.out.println("Thanh toán thành công!");
            invoice.displayInvoice();
        } else {
            System.out.println("Thanh toán thất bại (hóa đơn trống hoặc lỗi khác).");
        }
        return success;
    }

}
