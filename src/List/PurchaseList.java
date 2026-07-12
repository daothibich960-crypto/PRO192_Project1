package List;

import Purchase.PurchaseReceipt;
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


}