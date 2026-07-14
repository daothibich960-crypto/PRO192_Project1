package Menu;

import Inventory.Inventory;
import List.PurchaseList;
import Supplier.SupplierList;
import java.util.Scanner;

public class PurchaseMenu {

    private Scanner sc;
    private PurchaseList purchaseList;
    private SupplierList supplierList;
    private Inventory inventory;

    public PurchaseMenu(Inventory inventory, SupplierList supplierList,
            Scanner sc) {
        this.sc = sc;
        this.inventory = inventory;
        this.supplierList = supplierList;
        this.purchaseList = new PurchaseList();
        this.purchaseList.loadFromFile(supplierList);

    }

    public void show() {

        int choice;

        do {

            System.out.println("\n========== PURCHASE MANAGEMENT ==========");
            System.out.println("1. Supplier Management"); 
            System.out.println("2. Create Purchase Receipt");
            System.out.println("3. Display Purchase Receipt");
            System.out.println("4. Display ALl Product In Inventory");
            System.out.println("0. Exit");

            System.out.print("Choose: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:

                    SupplierMenu supplierMenu = new SupplierMenu(supplierList,sc);
                    supplierMenu.show();

                    break;

                case 2:

                    createReceipt();

                    break;

                case 3:

                    purchaseList.displayAllReceipt();

                    break;

                case 4:

                    inventory.displayInventory();

                    break;

                
                case 0:

                    break;

                default:

                    System.out.println("Invalid!");

            }

        } while (choice != 0);

    }

    private void createReceipt() {

        System.out.println("Create Purchase Receipt");

    }

   

}
