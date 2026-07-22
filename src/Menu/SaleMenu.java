package Menu;

import Customer.Customer;
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

    private static final String INVOICE_FILE = "Data/invoice.txt";
    private static final String CUSTOMER_FILE = "Data/customer.txt";
    private static final String PRODUCT_FILE = "Data/product.txt";
    private SaleService saleService;
    private Scanner scanner;

    public SaleMenu(SaleService saleService, Scanner scanner) {
        this.saleService = saleService;
        this.scanner = scanner;
    }

    public void show() {
        String employeeID = Input.readNonEmptyString("Nhập mã nhân viên bán hàng: ");

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

            int choice = Input.readIntInRange("Chọn: ", 0, 6);
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Nhập Product ID của sản phẩm: ");
                    String productID = scanner.nextLine();
                    System.out.print("Nhập số lượng sản phẩm: ");
                    int quantity = scanner.nextInt();

                    saleService.addProduct(invoice, productID, quantity);
                    break;
                case 2:
                    System.out.print("Nhập Product ID của sản phẩm: ");
                    String productID1 = scanner.nextLine();
                    saleService.removeProduct(invoice, productID1);
                    break;
                case 3:
                    System.out.print("Nhập Product ID của sản phẩm: ");
                    String productID2 = scanner.nextLine();
                    System.out.print("Nhập số lượng sản phẩm (thêm >= 0 || xóa < 0 ): ");
                    int quantity2 = scanner.nextInt();
                    saleService.updateQuantity(invoice, productID2, quantity2);
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
                    System.out.println("Dã hủy hóa đơn.");
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
        Customer customer = null;
        if (!phone.isEmpty()) {
            invoice.setCustomerPhone(phone);
            customer = saleService.getCustomerList().searchByPhone(phone); // lấy khách hàng 
        }

        // hỏi khách hàng có muốn chiết khấu trừ điểm vào hóa đơn hay không 
        double discount = 0;
        double  usedPoint = 0;

        if (customer != null && customer.getPoint() > 0) { // cần xác nhận tên getter đúng
            System.out.println("\nKhách hàng hiện có: " + customer.getPoint() + " điểm.");
            boolean useDiscount = Input.readYesNo("Khách hàng có muốn dùng điểm để chiết khấu không? ");

            if (useDiscount) {
                System.out.print("Nhập số điểm muốn dùng: ");
                usedPoint = Integer.parseInt(scanner.nextLine().trim());

                
                if (usedPoint > customer.getPoint()) {
                    System.out.println("Số điểm vượt quá điểm hiện có! Không áp dụng chiết khấu.");
                    usedPoint = 0;
                } else if (usedPoint < 0) {
                    System.out.println("Số điểm không hợp lệ!");
                    usedPoint = 0;
                } else if (usedPoint > invoice.getTotalAmount()){
                    System.out.println("Điểm nhập không được lớn hơn tổng tiền của hóa đơn.");
                    usedPoint = 0;
                }
                else {
                    discount = usedPoint * 1000; // cần xác nhận tỷ lệ quy đổi thật (ví dụ 1 điểm = 1000đ)
                    if (discount > invoice.getTotalAmount()) {
                        discount = invoice.getTotalAmount(); // không cho chiết khấu vượt quá tổng hóa đơn
                    }
                    invoice.chietKhau(discount); // lấy chiết khấu 
                    saleService.truDiemChietKhau(phone, usedPoint); // update điểm đã trừ 
                    System.out.println("Đã áp dụng chiết khấu: " + discount + "đ");
                }
            }
        }
        invoice.displayInvoice();
        System.out.println("");
        System.out.println("Hình thức thanh toán: 1. Tiền mặt  2. Chuyển khoản  3. Thẻ 4.Other");
        System.out.print("Chọn: ");
        int payChoice = Integer.parseInt(scanner.nextLine());
        PayMethod pay = null;
        switch (payChoice) {
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
        boolean confirmed = Input.readYesNo("Xác nhận thanh toán thành công");

        boolean success = saleService.checkout(invoice, confirmed ? 1 : -1);
        if (success) {
            System.out.println("Thanh toán thành công!");
            invoice.displayInvoice();
            saleService.getInventory().saveToFile(PRODUCT_FILE);
        } else {
            System.out.println("Thanh toán thất bại (hóa đơn trống hoặc bị hủy).");
        }
        return success;
    }

}
