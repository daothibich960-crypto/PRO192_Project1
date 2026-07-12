
package Utils;

import java.util.regex.Pattern;

public class Validation {
    // Số điện thoại VN: 10 số, bắt đầu bằng 0
    // dùng để kiểm tra nghiệm vụ: số điện thoại , mã ID 
    private static final Pattern PHONE_PATTERN = Pattern.compile("^0\\d{9}$");

    public static boolean isValidPhone(String phone) {
        return phone != null && PHONE_PATTERN.matcher(phone).matches();
    }

    public static boolean isPositiveNumber(double number) {
        return number > 0;
    }

    public static boolean isNonNegativeNumber(double number) {
        return number >= 0;
    }

    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    // Kiểm tra mã có đúng tiền tố không, dùng cho phân loại Product (TEA, CUP, POT...)
    public static boolean hasPrefix(String id, String prefix) {
        return id != null && id.length() >= prefix.length()
                && id.substring(0, prefix.length()).equalsIgnoreCase(prefix);
    }

    
    
}
