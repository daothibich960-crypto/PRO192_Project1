
package Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DateUtil {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    // Parse chuỗi "dd/MM/yyyy" -> LocalDate
    public static LocalDate parse(String dateStr) {
        return LocalDate.parse(dateStr, DATE_FORMAT);
    }

    // Format LocalDate -> chuỗi "dd/MM/yyyy" để hiển thị
    public static String format(LocalDate date) {
        return date.format(DATE_FORMAT);
    }

    public static String format(LocalDateTime dateTime) {
        return dateTime.format(DATETIME_FORMAT);
    }

    public static boolean isSameMonth(LocalDate date, int month, int year) {
        return date.getMonthValue() == month && date.getYear() == year;
    }

    public static boolean isBetween(LocalDate date, LocalDate from, LocalDate to) {
        return !date.isBefore(from) && !date.isAfter(to);
    }
    
}
