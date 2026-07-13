package List;

import Supplier.Supplier;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import Purchase.PurchaseReceipt;
import Supplier.SupplierList;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class PurchaseList {

    private List<PurchaseReceipt> list;

    public PurchaseList() {
        list = new ArrayList<>();
    }

   
    // Add
  
    public void addReceipt(PurchaseReceipt receipt) {

        if (containsReceipt(receipt.getReceiptId())) {
            System.out.println("Receipt ID already exists!");
            return;
        }

        list.add(receipt);

        System.out.println("Add receipt successfully.");

    }

    
    // Remove
   
    public void removeReceipt(String receiptId) {

        PurchaseReceipt receipt = searchReceipt(receiptId);

        if (receipt == null) {
            System.out.println("Receipt not found.");
            return;
        }

        list.remove(receipt);

    }

   
    // Search
    
    public PurchaseReceipt searchReceipt(String receiptId) {

        for (PurchaseReceipt receipt : list) {

            if (receipt.getReceiptId().equalsIgnoreCase(receiptId)) {

                return receipt;

            }

        }

        return null;

    }

   
    // Contains
  
    public boolean containsReceipt(String receiptId) {

        return searchReceipt(receiptId) != null;

    }

   
    // Display
  
    public void displayAllReceipt() {

        if (list.isEmpty()) {

            System.out.println("Receipt list is empty.");

            return;

        }

        for (PurchaseReceipt receipt : list) {

            receipt.displayReceipt();

            System.out.println();

        }

    }

    public int getTotalReceipt() {

        return list.size();

    }

    public List<PurchaseReceipt> getList() {

        return list;

    }
 
// Đọc dữ liệu từ file
public void loadFromFile(SupplierList supplierList) {

    list.clear();

    try {

        BufferedReader br = new BufferedReader(new FileReader("Data/purchase.txt"));

        String line;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        while ((line = br.readLine()) != null) {

            String[] data = line.split("\\|");

            Supplier supplier = supplierList.searchSupplier(data[2]);

            PurchaseReceipt receipt = new PurchaseReceipt(
                    data[0],
                    sdf.parse(data[1]),
                    supplier);

            list.add(receipt);

        }

        br.close();

        System.out.println("Load purchase successfully.");

    } catch (Exception e) {

        System.out.println("Cannot read purchase.txt");

    }

}
// Lưu dữ liệu xuống file
public void saveToFile() {

    try {

        PrintWriter pw = new PrintWriter(new FileWriter("Data/purchase.txt"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (PurchaseReceipt receipt : list) {

            pw.println(
                    receipt.getReceiptId() + "|"
                    + sdf.format(receipt.getImportDate()) + "|"
                    + receipt.getSupplier().getSupplierId());

        }

        pw.close();

        System.out.println("Save purchase successfully.");

    } catch (Exception e) {

        System.out.println("Cannot save purchase.txt");

    }

}

}