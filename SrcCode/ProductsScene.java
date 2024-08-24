//package SrcCode;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Scanner;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.ListView;
//
//public class ProductsScene extends SubCategoriesScene {
//
//    @FXML
//    private ListView<Product> productList;
//
//    private ObservableList<Product> products = FXCollections.observableArrayList();
//
//    @FXML
//    public void initialize() throws FileNotFoundException {
//        // Load products from file or database
//        loadProducts();
//
//        // Set the products list to the ListView
//        productList.setItems(products);
//    }
//
//    private void loadProducts() throws FileNotFoundException {
////        SubCategoriesScene subCategoriesScene = new SubCategoriesScene();
////        subCategoriesScene.FashionCat(null);
////        SubCategories selected = new SubCategories(subCategoriesScene.SelectedSubCategory());
////        File productFile = new File(selected.getProductFilePath());
////        Scanner scanner = new Scanner(productFile);
////        while (scanner.hasNextLine()) {
////            String[] productData = scanner.nextLine().split(",");
////            String name = productData[0];
////            double price = Double.parseDouble(productData[1]);
////            int quantity = Integer.parseInt(productData[2]);
////            Product product = new Product(name, price, quantity);
////            products.add(product);
////        }
////        scanner.close();
//    }
//
//    @FXML
//    public void backToHome() throws IOException {
//        NewFXMain m = new NewFXMain();
//        m.changeScene("SubCategoriesScene.fxml");
//    }
//}
package SrcCode;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class ProductsScene extends SubCategoriesScene implements Initializable {

    @FXML
    private ListView<String> productList;
    @FXML
    private Button BackToHomeButton;
    @FXML
    private ListView<String> ProductDetails;

    private ArrayList<String> productsName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        ArrayList<Product> products = super.getAllProducts();
//        List<String> productsNameList = new ArrayList<>();
//        for (Product product : products) {
//            productsNameList.add(product.getName());
//        }
//        ObservableList<String> productNames = FXCollections.observableArrayList(productsNameList);
//        productList.getItems().addAll(productNames);
//        productList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            // Do something when the selected item changes
//        });
//        productNames.clear();
    }

    public void setProducts(ArrayList<Product> products) throws IOException {
//        if (products != null) {
//            productsName.clear();
//            for (Product product : products) {
//                productsName.add(product.getName());
//            }
//        }
    }

    @FXML
    private void backToHome(ActionEvent event) throws IOException {
        NewFXMain m = new NewFXMain();
        m.changeScene("SubCategories.fxml");
    }
}
