package SrcCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class LoginScene {

    @FXML
    private Button Loginbutton;
    @FXML
    private Button Registerbutton;
    @FXML
    private ToggleGroup userType;
    @FXML
    private RadioButton Admin;
    @FXML
    private RadioButton Customer;

    @FXML
    private Label wrongLogIn;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    public LoginScene() {

    }

    @FXML
    public void userLogIn(ActionEvent event) throws IOException {
        checkLogin();
    }

    private void checkLogin() throws IOException {
        NewFXMain m = new NewFXMain();
        if (Admin.isSelected()) {
            System.out.println("The Admin is selected.");
            if (validateUser("data\\Admins\\Data.txt")) {
                System.out.println("Change to admin scene");
            }
        } else if (Customer.isSelected()) {
            System.out.println("The Customer is selected.");
            if (validateUser("data\\Customers\\Data.txt")) {
                m.changeScene("SubCategories.fxml");
            }
        }
    }

    private boolean validateUser(String filePath) {
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            boolean userFound = false;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] userData = line.split(" ");
                if (userData[0].equals(username.getText().toString())) {
                    userFound = true;
                    if (userData[1].equals(password.getText().toString())) {
                        return true;
                    }
                }
            }
            scanner.close();

            if (!userFound) {
                System.out.println("Username doesn't exist, register an account");
            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred. File Not Found");
            e.printStackTrace();
        }
        return false;
    }

    @FXML
    private void userRegister(ActionEvent event) throws IOException {
        NewFXMain m = new NewFXMain();
        m.changeScene("Register.fxml");
    }

}
