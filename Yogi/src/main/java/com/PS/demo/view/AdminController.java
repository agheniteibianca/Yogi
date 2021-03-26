package com.PS.demo.view;

import com.PS.demo.model.Product;
import com.PS.demo.model.User;
import com.PS.demo.service.CommentService;
import com.PS.demo.service.OfferService;
import com.PS.demo.service.ProductService;
import com.PS.demo.service.UserService;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class AdminController implements Initializable {
    @Autowired
    private ConfigurableApplicationContext springContext ;
    //globals
    Product selectedProduct;
    User selectedUser;

    //services
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private OfferService offerService;


    //TABLES

    @FXML
    TableView<Product> productTable;
    @FXML
    TableColumn<Product, Long> id;
    @FXML
    TableColumn<Product, String> name;
    @FXML
    TableColumn<Product, String> description;
    @FXML
    TableColumn<Product, Float> price;
    @FXML
    TableColumn<Product, Date> date;


    @FXML
    TableView<User> usersTable;
    @FXML
    TableColumn<User, Long> userIdColumn;
    @FXML
    TableColumn<User, String> userUsernameColumn;
    @FXML
    TableColumn<User, String> passwordColumn;
    @FXML
    TableColumn<User, Integer> userItemsSoldColumn;
    @FXML
    TableColumn<User, Integer> emailColumn;


    //PANES
    @FXML
    AnchorPane mainPane;
    @FXML
    AnchorPane signUpPane;
    @FXML
    AnchorPane deleteUserPane;
    @FXML
    AnchorPane deleteProductPane;

    //BUTTONS
    @FXML
    Button allProductsBtn;
    @FXML
    Button allUsersBtn;

    //INPUTS
    @FXML
    private TextField newUsername;
    @FXML
    private TextField newPassword;
    @FXML
    private TextField newEmail;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        ObservableList selectedItems = productTable.getSelectionModel().getSelectedItems();
    }

    public void refreshTable(){
        productTable.getItems().clear();
        List<Product> allProducts = productService.findAll();
        for(Product cur: allProducts) {
            productTable.getItems().add(cur);
        }
    }

    public void refreshUsersTable(){
        usersTable.getItems().clear();
        List<User> allUsers = userService.findAll();
        for(User cur: allUsers) {
            if(cur.getId() != 1)
                usersTable.getItems().add(cur);
        }
    }

    private void events(){
        productTable.getSelectionModel().setCellSelectionEnabled(true);
        ObservableList selectedCells = productTable.getSelectionModel().getSelectedCells();

        selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change c) {
                if (productTable.getSelectionModel().getSelectedItem() != null) {
                    selectedProduct = productTable.getSelectionModel().getSelectedItem();
                    deleteProductPane.setVisible(true);
                }
            }
        });

        usersTable.getSelectionModel().setCellSelectionEnabled(true);
        ObservableList selectedUserCells = usersTable.getSelectionModel().getSelectedCells();

        selectedUserCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change c) {
                if (usersTable.getSelectionModel().getSelectedItem() != null) {
                    selectedUser = usersTable.getSelectionModel().getSelectedItem();
                    deleteUserPane.setVisible(true);
                }
            }
        });
    }

    public void populateWindow(){


        mainPane.setVisible(true);

        signUpPane.setVisible(false);
        deleteProductPane.setVisible(false);
        deleteUserPane.setVisible(false);

        allProductsBtn.setDisable(true);
        allUsersBtn.setDisable(false);



        //setup tables

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        date.setCellValueFactory(new PropertyValueFactory<>("date_listed"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));


        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        userUsernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        userItemsSoldColumn.setCellValueFactory(new PropertyValueFactory<>("itemssold"));

        refreshTable();
        refreshUsersTable();
        usersTable.setVisible(false);

        events();

    }

    @FXML
    public void showAllProducts(javafx.event.ActionEvent actionEvent){
        allProductsBtn.setDisable(true);
        allUsersBtn.setDisable(false);

        productTable.setVisible(true);
        usersTable.setVisible(false);

    }

    @FXML
    public void showAllUsers(javafx.event.ActionEvent actionEvent){
        allProductsBtn.setDisable(false);
        allUsersBtn.setDisable(true);

        productTable.setVisible(false);
        usersTable.setVisible(true);
    }

    @FXML
    public void logout(javafx.event.ActionEvent actionEvent) throws Exception {
        FXMLLoader loader = new FXMLLoader(new File("C:\\Users\\aghen\\OneDrive\\Desktop\\PS\\Proiect\\bun\\src\\main\\java\\com\\PS\\demo\\view\\Login.fxml").toURI().toURL());
        loader.setControllerFactory(springContext::getBean);


        Parent root = (Parent) loader.load();
        LoginController loginController = loader.getController();


        Stage stage = (Stage) allProductsBtn.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void closePopups() {
        signUpPane.setVisible(false);
        deleteProductPane.setVisible(false);
        deleteUserPane.setVisible(false);

    }
    @FXML
    public void addNewUser(javafx.event.ActionEvent actionEvent) throws Exception {
        signUpPane.setVisible(true);
    }

    @FXML
    public void pressSignUp(javafx.event.ActionEvent actionEvent) {
        String usernameInput = newUsername.getText();
        String passwordInput = newPassword.getText();
        String emailInput = newEmail.getText();

        User newUser = new User(null,usernameInput,passwordInput,emailInput);
        userService.addUser(newUser);
        refreshUsersTable();
        closePopups();
    }

    @FXML
    public void deleteUser(javafx.event.ActionEvent actionEvent) throws Exception {
        userService.deleteById(selectedUser.getId());
        refreshUsersTable();
        refreshTable();
        closePopups();
    }
    @FXML
    public void deleteProduct(javafx.event.ActionEvent actionEvent) throws Exception {
        productService.deleteById(selectedProduct.getId());
        refreshTable();
        closePopups();
    }

    @FXML
    public void closePopupsX(MouseEvent event) throws Exception {
        signUpPane.setVisible(false);
        deleteProductPane.setVisible(false);
        deleteUserPane.setVisible(false);

    }
    @FXML
    public void closePopups(javafx.event.ActionEvent actionEvent) throws Exception {
        signUpPane.setVisible(false);
        deleteProductPane.setVisible(false);
        deleteUserPane.setVisible(false);

    }




}
