
package Utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Input {
    private static Scanner scanner = new Scanner(System.in);

    // Trả về Scanner dùng chung cho toàn chương trình (tránh tạo nhiều Scanner)
    public static Scanner getScanner() {
        return scanner;
    }

    public static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    // Chuỗi bắt buộc không được rỗng
    public static String readNonEmptyString(String prompt) {
        String value;
        do {
            System.out.print(prompt);
            value = scanner.nextLine().trim();
            if (value.isEmpty()) {
                System.out.println("Không được để trống, vui lòng nhập lại!");
            }
        } while (value.isEmpty());
        return value;
    }

    public static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập đúng số nguyên!");
            }
        }
    }

    public static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập đúng số!");
            }
        }
    }

    // Đọc số nguyên trong khoảng min-max, dùng cho menu (VD: chọn 0-6)
    public static int readIntInRange(String prompt, int min, int max) {
        int value;
        while (true) {
            value = readInt(prompt);
            if (value < min || value > max) {
                System.out.println("Vui lòng nhập số từ " + min + " đến " + max + "!");
            } else {
                return value;
            }
        }
    }

    public static LocalDate readDate(String prompt) {
        while (true) {
            System.out.print(prompt + " (dd/MM/yyyy): ");
            String line = scanner.nextLine().trim();
            try {
                return DateUtil.parse(line);
            } catch (DateTimeParseException e) {
                System.out.println("Định dạng ngày không đúng, vui lòng nhập lại (VD: 12/07/2026)!");
            }
        }
    }

    // Nhập có/không, trả về boolean
    public static boolean readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt + " (y/n): ");
            String line = scanner.nextLine().trim().toLowerCase();
            if (line.equals("y")) return true;
            if (line.equals("n")) return false;
            System.out.println("Vui lòng nhập y hoặc n!");
        }
    }
    
}
