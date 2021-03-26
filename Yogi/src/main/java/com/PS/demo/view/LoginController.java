package com.PS.demo.view;

import com.PS.demo.model.Product;
import com.PS.demo.model.User;
import com.PS.demo.service.ProductService;
import com.PS.demo.service.UserService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    private ConfigurableApplicationContext springContext ;

    @Autowired
    private UserService userService;

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button loginButton;
    @FXML
    private Button canLoginButton;


    @FXML
    private TextField newUsername;
    @FXML
    private TextField newPassword;
    @FXML
    private TextField newEmail;
    @FXML
    private Text signUpText;

    @FXML
    AnchorPane loginPane;
    @FXML
    AnchorPane signUpPane;


    @FXML
    public void openSignUpWindow(MouseEvent event) {
        loginPane.setVisible(false);
        signUpPane.setVisible(true);
        canLoginButton.setVisible(false);
    }


    @FXML
    public void canLogin(javafx.event.ActionEvent actionEvent) {
        signUpPane.setVisible(false);
        loginPane.setVisible(true);
    }

    @FXML
    public void closeSignUpWindow(MouseEvent event) {
        signUpPane.setVisible(false);
        loginPane.setVisible(true);
    }

    @FXML
    public void pressLogin(javafx.event.ActionEvent actionEvent) {
        String requested_username = username.getText();
        String requested_password = password.getText();
        User gasit = userService.findFirstByUsernameAndPassword(requested_username, requested_password);


        if(gasit == null){
            signUpText.setUnderline(true);
        }
        else{
            try {
                if(gasit.getId() == 1){ //admin
                    FXMLLoader loader = new FXMLLoader(new File("C:\\Users\\aghen\\OneDrive\\Desktop\\PS\\Proiect\\bun\\src\\main\\java\\com\\PS\\demo\\view\\AdminMenu.fxml").toURI().toURL());
                    loader.setControllerFactory(springContext::getBean);

                    Parent root = (Parent) loader.load();
                    AdminController adminController = loader.getController();

                    adminController.populateWindow();

                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                }
                else {

                    FXMLLoader loader = new FXMLLoader(new File("C:\\Users\\aghen\\OneDrive\\Desktop\\PS\\Proiect\\bun\\src\\main\\java\\com\\PS\\demo\\view\\MainMenu.fxml").toURI().toURL());
                    loader.setControllerFactory(springContext::getBean);


                    Parent root = (Parent) loader.load();
                    MainMenuController mainMenuController = loader.getController();

                    mainMenuController.populateWindow(gasit);

                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                }

            }catch (IOException io){
                io.printStackTrace();
            }
        }

    }

    @FXML
    public void pressSignUp(javafx.event.ActionEvent actionEvent) {
        String usernameInput = newUsername.getText();
        String passwordInput = newPassword.getText();
        String emailInput = newEmail.getText();

        User newUser = new User(null,usernameInput,passwordInput,emailInput);
        userService.addUser(newUser);
        canLoginButton.setVisible(true);
    }
}
