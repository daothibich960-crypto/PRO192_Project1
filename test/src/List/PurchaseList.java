package List;

import Purchase.PurchaseReceipt;
import Supplier.Supplier;
import Supplier.SupplierList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PurchaseList {

    private List<PurchaseReceipt> list;

    public PurchaseList() {

        list = new ArrayList<>();

    }

    //================ ADD =================

    public void addReceipt(PurchaseReceipt receipt) {

        if (containsReceipt(receipt.getReceiptId())) {

            System.out.println("Receipt ID already exists.");

            return;

        }

        list.add(receipt);

        saveToFile();

        System.out.println("Add receipt successfully.");

    }

    //================ REMOVE =================

    public void removeReceipt(String receiptId) {

        PurchaseReceipt receipt = searchReceipt(receiptId);

        if (receipt == null) {

            System.out.println("Receipt not found.");

            return;

        }

        list.remove(receipt);

        saveToFile();

        System.out.println("Delete successfully.");

    }

    //================ SEARCH =================

    public PurchaseReceipt searchReceipt(String receiptId) {

        for (PurchaseReceipt receipt : list) {

            if (receipt.getReceiptId().equalsIgnoreCase(receiptId)) {

                return receipt;

            }

        }

        return null;

    }

    //================ CONTAINS =================

    public boolean containsReceipt(String receiptId) {

        return searchReceipt(receiptId) != null;

    }

    //================ DISPLAY =================

    public void displayAllReceipt() {

        if (list.isEmpty()) {

            System.out.println("Purchase receipt list is empty.");

            return;

        }

        System.out.println("\n============= PURCHASE RECEIPT LIST =============");

        for (PurchaseReceipt receipt : list) {

            receipt.displayReceipt();

            System.out.println();

        }

    }

    //================ GET =================

    public int getTotalReceipt() {

        return list.size();

    }

    public List<PurchaseReceipt> getList() {

        return list;

    }

    //================ LOAD FILE =================

    public void loadFromFile(SupplierList supplierList) {

        list.clear();

        try {

            BufferedReader br =
                    new BufferedReader(new FileReader("data/purchase.txt"));

            String line;

            SimpleDateFormat sdf =
                    new SimpleDateFormat("yyyy-MM-dd");

            while ((line = br.readLine()) != null) {

                String[] data = line.split("\\|");

                Supplier supplier =
                        supplierList.searchSupplier(data[2]);

                PurchaseReceipt receipt =
                        new PurchaseReceipt(
                                data[0],
                                sdf.parse(data[1]),
                                supplier);

                list.add(receipt);

            }

            br.close();

        } catch (Exception e) {

            // lần đầu chưa có file thì bỏ qua

        }

    }

    //================ SAVE FILE =================

    public void saveToFile() {

        try {

            new File("data").mkdirs();

            PrintWriter pw =
                    new PrintWriter(new FileWriter("data/purchase.txt"));

            SimpleDateFormat sdf =
                    new SimpleDateFormat("yyyy-MM-dd");

            for (PurchaseReceipt receipt : list) {

                pw.println(
                        receipt.getReceiptId() + "|"
                        + sdf.format(receipt.getImportDate()) + "|"
                        + receipt.getSupplier().getSupplierId());

            }

            pw.close();

        } catch (Exception e) {

            System.out.println("Cannot save purchase.txt");

        }

    }

}