package com.PS.demo.view;

import com.PS.demo.PsApplication;
import com.PS.demo.model.Comment;
import com.PS.demo.model.Offer;
import com.PS.demo.model.Product;
import com.PS.demo.model.User;
import com.PS.demo.service.CommentService;
import com.PS.demo.service.OfferService;
import com.PS.demo.service.ProductService;
import com.PS.demo.service.UserService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

import javax.swing.event.ChangeListener;
import javax.transaction.Transactional;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Controller
public class MainMenuController implements Initializable {
    @Autowired
    private ConfigurableApplicationContext springContext ;
    //globals
    Product selectedProduct;
    MenuSelection selection;
    Offer selectedOffer;

    //services
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private OfferService offerService;

    //panes
    @FXML
    AnchorPane addProductPane;
    @FXML
    AnchorPane mainPane;
    @FXML
    AnchorPane addCommentPane;
    @FXML
    private AnchorPane productPane;
    @FXML
    private AnchorPane makeOfferPane;
    @FXML
    private AnchorPane myProductPane;
    @FXML
    private AnchorPane acceptOfferPane;


    //TABLES

    @FXML
    private Label eticheta;
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
    TableView<Comment> commentTable;
    @FXML
    TableColumn<Comment, Long> commentFromColumn;
    @FXML
    TableColumn<Comment, String> commentBodyColumn;
    @FXML
    TableColumn<Comment, Date> commentDateColumn;


    @FXML
    TableView<Offer> myOfferTable;
    @FXML
    TableColumn<Offer, String> offererColumn;
    @FXML
    TableColumn<Offer, String> offerColumn;
    @FXML
    TableColumn<Offer, Float> priceColumn;
    @FXML
    TableColumn<Offer, Date> offerDateColumn;

    //BUTTONS

    @FXML
    private Button openAddProductWindowButton;
    @FXML
    private Button allProductsBtn;
    @FXML
    private Button showMyProductsBtn;
    @FXML
    private Button saveProductChangesBtn;
    @FXML
    private Button editProductBtn;






    @FXML
    private TextField productName;
    @FXML
    private TextArea productDescription;
    @FXML
    private TextField productPrice;
    @FXML
    private TextArea commentInput;
    @FXML
    private TextArea offerInput;
    @FXML
    private TextField suggestedPriceInput;
    @FXML
    private TextField newTitleInput;
    @FXML
    private TextArea newDescriptionInput;


    @FXML
    private Text displayedUsername;
    @FXML
    private Text displayedName;
    @FXML
    private Text displayedDescription;



    @FXML
    private Text mydisplayedName;
    @FXML
    private Text mydisplayedDescription;


    private User loggedUser;

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        ObservableList selectedItems = productTable.getSelectionModel().getSelectedItems();
    }

    public void refreshTable(){

        productTable.getItems().clear();
        List<Product> allProducts = productService.findAll();
        for(Product cur: allProducts) {
            if(cur.getIs_sold() == false && cur.getOwner().getId() != loggedUser.getId())
                productTable.getItems().add(cur);
        }
    }
    public void refreshCommentTable(){
        commentTable.getItems().clear();
        List<Comment> allComments = commentService.findAll();
        for(Comment cur: allComments) {
            commentTable.getItems().add(cur);
        }
    }

    public void refreshOfferTable(){
        myOfferTable.getItems().clear();
        List<Offer> allOffers = offerService.findByProduct(selectedProduct);
        for(Offer cur: allOffers) {
            myOfferTable.getItems().add(cur);
        }
    }

    public void refreshTableAddMyProducts(){
        productTable.getItems().clear();
        List<Product> allProductsOpt = productService.findByOwner(loggedUser);

        for(Product cur: allProductsOpt) {
            productTable.getItems().add(cur);
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

                    if(selection == MenuSelection.ALLPRODUCTS) {
                        //make changes to window
                        displayedName.setText(selectedProduct.getName());
                        displayedDescription.setText(selectedProduct.getDescription());
                        displayedUsername.setText(selectedProduct.getOwner().getUsername());

                        ObservableList<Node> children = mainPane.getChildren();
                        for (Node child : children) {
                            child.setVisible(false);
                        }
                        productPane.setVisible(true);
                    }
                    else if(selection == MenuSelection.MYPRODUCTS){
                        mydisplayedName.setText(selectedProduct.getName());
                        mydisplayedDescription.setText(selectedProduct.getDescription());

                        ObservableList<Node> children = mainPane.getChildren();
                        for (Node child : children) {
                            child.setVisible(false);
                        }
                        newTitleInput.setVisible(false);
                        newDescriptionInput.setVisible(false);
                        editProductBtn.setVisible(true);
                        saveProductChangesBtn.setVisible(false);
                        myProductPane.setVisible(true);
                        refreshOfferTable();


                    }
                }
            }
        });
    }

    private void Offerevents(){
        myOfferTable.getSelectionModel().setCellSelectionEnabled(true);
        ObservableList selectedCells = myOfferTable.getSelectionModel().getSelectedCells();

        selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change c) {
                if (myOfferTable.getSelectionModel().getSelectedItem() != null) {
                    selectedOffer = myOfferTable.getSelectionModel().getSelectedItem();
                        acceptOfferPane.setVisible(true);
                }
            }
        });
    }



    @FXML
    public void showMyProducts(javafx.event.ActionEvent actionEvent) {
        allProductsBtn.setDisable(false);
        showMyProductsBtn.setDisable(true);
        openAddProductWindowButton.setDisable(false);

        selection = MenuSelection.MYPRODUCTS;

        refreshTableAddMyProducts();
    }

    @FXML
    public void showAllProducts(javafx.event.ActionEvent actionEvent){
        allProductsBtn.setDisable(true);
        showMyProductsBtn.setDisable(false);
        openAddProductWindowButton.setDisable(false);

        selection = MenuSelection.ALLPRODUCTS;

        refreshTable();
    }
    @FXML
    public void openAddProductWindow(javafx.event.ActionEvent actionEvent) {
        allProductsBtn.setDisable(false);
        showMyProductsBtn.setDisable(false);
        openAddProductWindowButton.setDisable(true);

        selection = MenuSelection.ADDPRODUCT;


        ObservableList<Node> children = mainPane.getChildren();
        for(Node child: children){
            child.setVisible(false);
        }
        addProductPane.setVisible(true);
        refreshTable();

        allProductsBtn.setDisable(true);
        showMyProductsBtn.setDisable(false);
        openAddProductWindowButton.setDisable(false);

        selection = MenuSelection.ALLPRODUCTS;
    }


    @FXML
    public void closeAddProductWindow(MouseEvent event) {
        ObservableList<Node> children = mainPane.getChildren();
        for(Node child: children){
            child.setVisible(true);
        }
        addProductPane.setVisible(false);
        productPane.setVisible(false);
        addCommentPane.setVisible(false);
        makeOfferPane.setVisible(false);
        myProductPane.setVisible(false);
        acceptOfferPane.setVisible(false);

        allProductsBtn.setDisable(true);
        showMyProductsBtn.setDisable(false);
        openAddProductWindowButton.setDisable(false);

        selection = MenuSelection.ALLPRODUCTS;

        refreshTable();
    }

    @FXML
    public void closeProductWindow(MouseEvent event) {

        ObservableList<Node> children = mainPane.getChildren();
        for(Node child: children){
            child.setVisible(true);
        }
        addProductPane.setVisible(false);
        productPane.setVisible(false);
        addCommentPane.setVisible(false);
        makeOfferPane.setVisible(false);
        myProductPane.setVisible(false);
        acceptOfferPane.setVisible(false);

        refreshTable();
    }

    @FXML
    public void closeAddCommentWindow(MouseEvent event) {
        addProductPane.setVisible(false);
        addCommentPane.setVisible(false);
        refreshCommentTable();
    }
    @FXML
    public void closeMakeOfferWindow(MouseEvent event) {
        makeOfferPane.setVisible(false);
    }
    @FXML
    public void closeAcceptOfferWindow(MouseEvent event) {
        acceptOfferPane.setVisible(false);
    }




    @FXML
    public void addProduct(javafx.event.ActionEvent actionEvent) {

        String nameInput = productName.getText();
        String descriptionInput = productDescription.getText();
        Float priceInput = Float.valueOf(productPrice.getText());
        Date newDate = new Date();
        System.out.println(loggedUser);
        Product newProduct = new Product(null,nameInput,descriptionInput,priceInput,false,newDate,loggedUser,null,null,null,null);
        productService.addProduct(newProduct);


        addProductPane.setVisible(false);
        ObservableList<Node> children = mainPane.getChildren();
        for(Node child: children){
            child.setVisible(true);
        }

        addProductPane.setVisible(false);
        productPane.setVisible(false);
        addCommentPane.setVisible(false);
        makeOfferPane.setVisible(false);
        myProductPane.setVisible(false);
        acceptOfferPane.setVisible(false);

        refreshTable();
    }
    @FXML
    public void addComment(javafx.event.ActionEvent actionEvent) {
        addCommentPane.setVisible(true);
    }
    @FXML
    public void postComment(javafx.event.ActionEvent actionEvent) {
        String bodyInput = commentInput.getText();
        Date newDate = new Date();
        Comment newComment = new Comment(null,bodyInput,newDate,loggedUser, selectedProduct);
        commentService.addComment(newComment);
        addCommentPane.setVisible(false);
        refreshCommentTable();
    }

    @FXML
    public void openMakeOfferWindow(javafx.event.ActionEvent actionEvent){
        if(selectedProduct != null){
            makeOfferPane.setVisible(true);
        }
        else{
            System.out.println("nu ai selectat");
        }
    }

    @FXML
    public void makeOffer(javafx.event.ActionEvent actionEvent) {
        String bodyInput = offerInput.getText();
        Date newDate = new Date();
        Float priceInput = Float.valueOf(suggestedPriceInput.getText());

        Offer newOffer = new Offer(null,newDate,bodyInput,priceInput,false,loggedUser,selectedProduct.getOwner(),selectedProduct);
        offerService.addOffer(newOffer);
        addCommentPane.setVisible(false);
        refreshCommentTable();
    }

    @FXML
    public void startEditingProduct(javafx.event.ActionEvent actionEvent) {
        newTitleInput.setText(selectedProduct.getName());
        newTitleInput.setVisible(true);
        newDescriptionInput.setText(selectedProduct.getDescription());
        newDescriptionInput.setVisible(true);

        mydisplayedName.setDisable(true);
        mydisplayedDescription.setDisable(true);

        saveProductChangesBtn.setVisible(true);
        editProductBtn.setVisible(false);
    }

    @FXML
    public void editProductInfo(javafx.event.ActionEvent actionEvent) {
        String newTitle = newTitleInput.getText();
        if(newTitle == null){
            newTitle = selectedProduct.getName();
        }
        String newDescription = newDescriptionInput.getText();
        if(newDescription == null){
            newDescription = selectedProduct.getDescription();
        }
        Float newPrice = selectedProduct.getPrice();
        selectedProduct = productService.updateProduct(selectedProduct.getId(),newTitle,newDescription,newPrice);

        mydisplayedName.setText(selectedProduct.getName());
        mydisplayedDescription.setText(selectedProduct.getDescription());

        mydisplayedName.setDisable(false);
        mydisplayedDescription.setDisable(false);

        newTitleInput.setVisible(false);
        newDescriptionInput.setVisible(false);

        saveProductChangesBtn.setVisible(false);

        editProductBtn.setVisible(true);

    }





    @FXML
    public void deleteOffer(javafx.event.ActionEvent actionEvent) {

        offerService.deleteOffer(selectedOffer);
        refreshOfferTable();
        acceptOfferPane.setVisible(false);
    }




    public void populateWindow(User gasit){


        this.setLoggedUser(gasit);

        allProductsBtn.setDisable(true);
        showMyProductsBtn.setDisable(false);
        openAddProductWindowButton.setDisable(false);

        selection = MenuSelection.ALLPRODUCTS;

        mainPane.setVisible(true);
        ObservableList<Node> children = mainPane.getChildren();
        for (Node child : children) {
            child.setVisible(true);
        }

        addProductPane.setVisible(false);
        productPane.setVisible(false);
        addCommentPane.setVisible(false);
        makeOfferPane.setVisible(false);
        myProductPane.setVisible(false);
        acceptOfferPane.setVisible(false);

        eticheta.setText(gasit.getUsername());

        //setup tables

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        date.setCellValueFactory(new PropertyValueFactory<>("date_listed"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));


        commentFromColumn.setCellValueFactory(new PropertyValueFactory<>("owner"));
        commentBodyColumn.setCellValueFactory(new PropertyValueFactory<>("body"));
        commentDateColumn.setCellValueFactory(new PropertyValueFactory<>("date_posted"));



        offererColumn.setCellValueFactory(new PropertyValueFactory<>("sender"));
        offerColumn.setCellValueFactory(new PropertyValueFactory<>("text"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("offeredPrice"));
        offerDateColumn.setCellValueFactory(new PropertyValueFactory<>("datePosted"));



        refreshTableAddMyProducts();
        refreshCommentTable();
        refreshTable();

        events();
        Offerevents();


    }

    @FXML
    public void acceptOffer(javafx.event.ActionEvent actionEvent) {
        offerService.acceptOffer(selectedOffer);
        acceptOfferPane.setVisible(false);
        productService.sellItem(selectedProduct);
        userService.increaseSold(loggedUser);

        populateWindow(loggedUser);
    }

    @FXML
    public void logout(javafx.event.ActionEvent actionEvent) throws Exception {
        FXMLLoader loader = new FXMLLoader(new File("C:\\Users\\aghen\\OneDrive\\Desktop\\PS\\Proiect\\bun\\src\\main\\java\\com\\PS\\demo\\view\\Login.fxml").toURI().toURL());
        loader.setControllerFactory(springContext::getBean);


        Parent root = (Parent) loader.load();
        LoginController loginController = loader.getController();


        Stage stage = (Stage) openAddProductWindowButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }


}
