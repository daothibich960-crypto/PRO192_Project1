
package FileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


public class FileIO {

   private static final String DELIMITER = "\\|"; // dùng cho split (regex)
    private static final String JOIN_DELIMITER = "|"; // dùng khi ghi

    // Đọc file -> List<T>, cần truyền vào hàm "parser" biết cách biến 1 dòng String thành object T
    public static <T> List<T> readFile(String filePath, Function<String[], T> parser) {
        List<T> list = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Không thể tạo file: " + filePath);
            }
            return list; // file mới tạo -> trả về danh sách rỗng
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] fields = line.split(DELIMITER);
                try {
                    T obj = parser.apply(fields);
                    list.add(obj);
                } catch (Exception e) {
                    System.out.println("Lỗi dòng dữ liệu: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi đọc file: " + filePath);
        }
        return list;
    }

    // Ghi List<T> -> file, cần truyền vào hàm "formatter" biết cách biến object T thành 1 dòng String
    public static <T> void writeFile(String filePath, List<T> list, Function<T, String> formatter) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (T obj : list) {
                bw.write(formatter.apply(obj));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Lỗi ghi file: " + filePath);
        }
    }
    
}
