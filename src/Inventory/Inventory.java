package Inventory;

import Product.Product;
import java.util.ArrayList;

public class Inventory {

    private ArrayList<Product> products;

    public Inventory() {

        products = new ArrayList<>();

    }

   
    // Add Product
   
    public void addProduct(Product product) {

        products.add(product);

    }

  
    // Remove Product
  
    public void removeProduct(String productId) {

        Product product = searchProduct(productId);

        if (product != null) {

            products.remove(product);

        }

    }

  
    // Search
   
    public Product searchProduct(String productId) {

        for (Product product : products) {

            if (product.getProductId().equalsIgnoreCase(productId)) {

                return product;

            }

        }

        return null;

    }


    // Nhập kho
   
    public void stockIn(String productId, int quantity) {

        Product product = searchProduct(productId);

        if (product == null) {

            return;

        }

        product.setStockQuantity(

                product.getStockQuantity() + quantity);

    }

   
    // Xuất kho
 
    public void stockOut(String productId, int quantity) {

        Product product = searchProduct(productId);

        if (product == null) {

            return;

        }

        if (product.getStockQuantity() < quantity) {

            System.out.println("Not enough quantity.");

            return;

        }

        product.setStockQuantity(

                product.getStockQuantity() - quantity);

    }

    
    // Update
   
    public void updateProductQuantity(String productId,
            int quantity) {

        Product product = searchProduct(productId);

        if (product != null) {

            product.setStockQuantity(quantity);

        }

    }


    // So luong hien tai
 
    public int getCurrentQuantity(String productId) {

        Product product = searchProduct(productId);

        if (product == null) {

            return 0;

        }

        return product.getStockQuantity();

    }


    // sắp hêt hàng
 
    public void checkLowStock(int minimumStock) {

        System.out.println("LOW STOCK");

        for (Product product : products) {

            if (product.getStockQuantity() <= minimumStock) {

                System.out.println(product.getProductId()
                        + " - "
                        + product.getProductName());

            }

        }

    }


    // Display
  
    public void displayInventory() {

        System.out.printf("%-10s %-25s %-10s\n",
                "ID",
                "NAME",
                "QUANTITY");

        for (Product product : products) {

            System.out.printf("%-10s %-25s %-10d\n",

                    product.getProductId(),

                    product.getProductName(),

                    product.getStockQuantity());

        }

    }

}