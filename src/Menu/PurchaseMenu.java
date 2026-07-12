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

    public PurchaseMenu() {

        sc = new Scanner(System.in);

        purchaseList = new PurchaseList();

        supplierList = new SupplierList();

        inventory = new Inventory();

    }

    public void menu() {

        int choice;

        do {

            System.out.println();
            System.out.println("========== PURCHASE MANAGEMENT ==========");
            System.out.println("1. Supplier Management");
            System.out.println("2. Create Purchase Receipt");
            System.out.println("3. Display Purchase Receipt");
            System.out.println("4. Inventory");
            System.out.println("5. Stock In");
            System.out.println("6. Stock Out");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:

                    supplierMenu();

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

                case 5:

                    stockIn();

                    break;

                case 6:

                    stockOut();

                    break;

                case 0:

                    System.out.println("Exit...");

                    break;

                default:

                    System.out.println("Invalid!");

            }

        } while (choice != 0);

    }

    private void supplierMenu() {

        System.out.println("Supplier Menu");

    }

    private void createReceipt() {

        System.out.println("Create Purchase Receipt");

    }

    private void stockIn() {

        System.out.println("Stock In");

    }

    private void stockOut() {

        System.out.println("Stock Out");

    }

   

}