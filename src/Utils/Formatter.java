
package Utils;

import java.text.NumberFormat;
import java.util.Locale;

public class Formatter {
    private static final NumberFormat CURRENCY_FORMAT =
            NumberFormat.getInstance(new Locale("vi", "VN"));

    // Format số tiền: 25000 -> "25.000đ"
    public static String currency(double amount) {
        return CURRENCY_FORMAT.format(amount) + " VND";
    }

    // Format số điện thoại dễ đọc: 0912345678 -> "0912 345 678"
    public static String phone(String phone) {
        if (phone == null || phone.length() != 10) return phone;
        return phone.substring(0, 4) + " " + phone.substring(4, 7) + " " + phone.substring(7);
    }

    // Cắt chuỗi quá dài khi hiển thị bảng (VD: tên sản phẩm dài trong danh sách)
    public static String truncate(String text, int maxLength) {
        if (text == null || text.length() <= maxLength) return text;
        return text.substring(0, maxLength - 3) + "...";
    }
    
}
