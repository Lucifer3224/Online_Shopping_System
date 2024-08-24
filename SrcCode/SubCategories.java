package SrcCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class SubCategories implements Comparable<SubCategories> {

    private String nameOfCategory;
    private String description;
    private ArrayList<Product> products;

    public SubCategories(String nameOfCategory) throws FileNotFoundException {
        this.nameOfCategory = nameOfCategory;
        this.products = new ArrayList<>();

        Path path = Paths.get("data\\SubCategories\\" + nameOfCategory);

        if (Files.exists(path)) {
            try (DirectoryStream<Path> fileStream = Files.newDirectoryStream(path)) {
                for (Path productPath : fileStream) {
                    File productFile = productPath.toFile();
                    Product p = new Product(productFile);
                    products.add(p);
                }
            } catch (IOException e) {
                System.err.println("Error reading subcategory directory: " + e.getMessage());
            }
        } else {
            try {
                Files.createDirectories(path);
                System.out.println("Directory created successfully.");
                addNewCategory(nameOfCategory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getNameOfCategory() {
        return nameOfCategory;
    }

    public void setNameOfCategory(String nameOfCategory) {
        this.nameOfCategory = nameOfCategory;
    }

    public ArrayList<Product> getProducts() {
        Collections.sort(products);
        return products;
    }

    public void printCategoryDetails() {
        Collections.sort(products);
        products = this.getProducts();
        for (Product product : products) {
            product.printDetails();
        }
    }

    public static class ProductNotFoundException extends Exception {
        public ProductNotFoundException(String wanted) {
            super(wanted);
        }
    }

    void searchProduct(String nameOfProduct) throws ProductNotFoundException {
        int flag = 0;
        for (int i = 0; i < products.size(); i++) {
            if ((products.get(i).getName()).equals(nameOfProduct)) {
                flag = 1;
                System.out.print(products.get(i).getDetails());
                break;
            }
        }
        if (flag == 0)
            throw new ProductNotFoundException("there is no such product found");
    }

    public void addNewCategory(String category) {
        Path path = Paths.get("data\\SubCategories\\" + category);
        if (Files.exists(path)) {
            this.updateFile();
            System.out.println("Category Created");
        } else {
            try {
                Files.createDirectories(path);
                System.out.println("Directory created successfully.");
                this.updateFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateFile() {
        try {
            FileWriter writer = new FileWriter(new File("data\\Categories\\SubCategories.txt"), true);
            writer.append("\n..\\\\SubCategories\\\\" + nameOfCategory);
            writer.close();

            System.out.println("Successfully updated the file.");

        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public int compareTo(SubCategories other) {
        return this.nameOfCategory.compareTo(other.nameOfCategory); // sort by name in ascending order
    }

    public static void main(String[] args)
            throws FileNotFoundException, NullPointerException {
        SubCategories subCategories = new SubCategories("Fashion");
        // subCategories.printCategoryDetails();
        System.out.println(subCategories.getProducts());
    }
}