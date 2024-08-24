/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package SrcCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

/**
 * FXML Controller class
 *
 * @author Habiba
 */
public class SubCategoriesScene implements Initializable {

    @FXML
    private Button LogOut;
    @FXML
    private Button Fashion;
    @FXML
    private Button Perfumes;
    @FXML
    private Button Mobiles;

    private SubCategories subCategories;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void UserLogout(ActionEvent event) throws IOException {
        NewFXMain m = new NewFXMain();
        m.changeScene("Login.fxml");
    }

    @FXML
    private void FashionCat(ActionEvent event) throws FileNotFoundException, IOException {
        SubCategories subCategories = new SubCategories("Fashion");
        subCategories.printCategoryDetails();
        NewFXMain m = new NewFXMain();
        m.changeScene("Products.fxml");
    }

    @FXML
    private void PerfumesCat(ActionEvent event) throws FileNotFoundException, IOException {
        subCategories = new SubCategories("Perfumes");
        ArrayList<Product> products = subCategories.getProducts();
        for (int i = 0; i < products.size(); i++) {
            products.get(i).getDetails();
        }
        NewFXMain m = new NewFXMain();
        m.changeScene("Products.fxml");
    }

    @FXML
    private void MobilesCat(ActionEvent event) throws FileNotFoundException, IOException {
        subCategories = new SubCategories("Mobiles");
        subCategories.printCategoryDetails();
        NewFXMain m = new NewFXMain();
        m.changeScene("Products.fxml");
    }

    public String getCatName() {
        return subCategories.getNameOfCategory();
    }

    public ArrayList<Product> getAllProducts() {
        return subCategories.getProducts();
    }

}
