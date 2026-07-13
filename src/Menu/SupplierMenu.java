package Menu;

import Supplier.Supplier;
import Supplier.SupplierList;
import java.util.Scanner;

public class SupplierMenu {

    private SupplierList supplierList;
    private Scanner sc;

    public SupplierMenu(SupplierList supplierList) {

        this.supplierList = supplierList;
        sc = new Scanner(System.in);

    }

    public void menu() {

        int choice;

        do {

            System.out.println("\n========== SUPPLIER MANAGEMENT ==========");
            System.out.println("1. Add Supplier");
            System.out.println("2. Display Supplier");
            System.out.println("3. Search Supplier");
            System.out.println("4. Update Supplier");
            System.out.println("5. Delete Supplier");
            System.out.println("0. Back");

            System.out.print("Choose: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:

                    addSupplier();

                    break;

                case 2:

                    supplierList.displayAllSupplier();

                    break;

                case 3:

                    searchSupplier();

                    break;

                case 4:

                    updateSupplier();

                    break;

                case 5:

                    deleteSupplier();

                    break;

                case 0:

                    break;

                default:

                    System.out.println("Invalid!");

            }

        } while (choice != 0);

    }

    private void addSupplier() {

        System.out.print("Supplier ID: ");
        String id = sc.nextLine();

        System.out.print("Supplier Name: ");
        String name = sc.nextLine();

        System.out.print("Phone: ");
        String phone = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Address: ");
        String address = sc.nextLine();

        Supplier supplier = new Supplier(id, name, phone, email, address);

        supplierList.addSupplier(supplier);

        supplierList.saveToFile();

    }

    private void searchSupplier() {

        System.out.print("Supplier ID: ");

        String id = sc.nextLine();

        Supplier supplier = supplierList.searchSupplier(id);

        if (supplier == null) {

            System.out.println("Supplier not found.");

        } else {

            supplier.displaySupplier();

        }

    }

    private void updateSupplier() {

        System.out.print("Supplier ID: ");

        String id = sc.nextLine();

        System.out.print("New Name: ");

        String name = sc.nextLine();

        System.out.print("New Phone: ");

        String phone = sc.nextLine();

        System.out.print("New Email: ");

        String email = sc.nextLine();

        System.out.print("New Address: ");

        String address = sc.nextLine();

        supplierList.updateSupplier(id, name, phone, email, address);

        supplierList.saveToFile();

    }

    private void deleteSupplier() {

        System.out.print("Supplier ID: ");

        String id = sc.nextLine();

        supplierList.removeSupplier(id);

        supplierList.saveToFile();

    }

}