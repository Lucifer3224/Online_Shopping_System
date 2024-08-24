package SrcCode;

import SrcCode.NewFXMain;
import SrcCode.Register;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Habiba
 */
public class RegisterScene {

    @FXML
    private TextField Username;
    @FXML
    private PasswordField Password;
    @FXML
    private Button BackToLoginbutton;
    @FXML
    private Button Registerbutton;
    @FXML
    private TextField Name;
    @FXML
    private TextField Address;
    @FXML
    private TextField Age;
    @FXML
    private TextField PhoneNumber;
    @FXML
    private TextField Email;

    /**
     * Initializes the controller class.
     */
    
    public RegisterScene() {

    }
    
    @FXML
    private void BackToUserLogIn(ActionEvent event) {
        NewFXMain m = new NewFXMain();
        try {
            m.changeScene("Login.fxml");
        } catch (IOException ex) {
            Logger.getLogger(RegisterScene.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void userRegister(ActionEvent event) {
        NewFXMain m = new NewFXMain();
        String checkers = Age.getText();
        boolean numeric = true;
        int num = 0;
        try {
            num = Integer.parseInt(checkers);
        } catch (NumberFormatException e) {
            numeric = false;
        }
        if (numeric && num != 0) {
            System.out.println(" is a number");
            Register acc = new Register(Name.getText(), Email.getText(), Username.getText(), Password.getText(), Address.getText(), PhoneNumber.getText(), num);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registration Successful");
            alert.setHeaderText(null);
            alert.setContentText("Your account has been created successfully.");
            alert.showAndWait();
            try {
                m.changeScene("Login.fxml");
            } catch (IOException ex) {
                Logger.getLogger(RegisterScene.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println(" is not a number");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registration Failed");
            alert.setHeaderText(null);
            alert.setContentText("Try to register again");
            alert.showAndWait();
            Password.clear();
            Age.clear();
            PhoneNumber.clear();

        }

    }

}
