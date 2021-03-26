package com.PS.demo;

import com.PS.demo.model.Product;
import com.PS.demo.view.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import com.PS.demo.model.User;
import com.PS.demo.repository.OfferRepository;
import com.PS.demo.repository.PersonalInfoRepository;
import com.PS.demo.repository.ProductRepository;
import com.PS.demo.repository.UserRepository;
import com.PS.demo.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.util.Date;
import java.util.List;

@EnableJpaRepositories
@SpringBootApplication
public class PsApplication extends Application{

	private ConfigurableApplicationContext springContext;
	private Parent root;


	@Override
	public void init() throws Exception {
		springContext = SpringApplication.run(PsApplication.class);
		FXMLLoader fxmlLoader = new FXMLLoader(new File("C:\\Users\\aghen\\OneDrive\\Desktop\\PS\\Proiect\\bun\\src\\main\\java\\com\\PS\\demo\\view\\Login.fxml").toURI().toURL());
		fxmlLoader.setControllerFactory(springContext::getBean);
		root = fxmlLoader.load();
	}

	@Override
	public void start(Stage primaryStage) throws Exception{

		primaryStage.setTitle("Hello World");
		primaryStage.setScene(new Scene(root, 600, 450));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(PsApplication.class, args);
	}

	@Bean
	CommandLineRunner init(PersonalInfoRepository personalInfoRepository, OfferRepository offerRepository,
						   ProductRepository productRepository, UserRepository userRepository, UserService userService,
						   CommentService commentService, OfferService offerService, PersonalInfoService personalInfoService,
						   PmService pmService, ProductMeasurementsService productMeasurementsService,
						   ProductService productService, ReviewService reviewService,TagService tagService){
		return args -> {
			/*
			Long aa = new Long(21);
			Date data = new Date(2004);
			Product new_product = new Product(aa,"Bilu","aaaa",34,false,false,"cdee",data,null,null,null);
			productService.addProduct(new_product);*/
			/*
			User test = new User(null,"admin","admin","Bilu",true,4,4,null,null,null,null,null,null,null,null,null,null);
			userService.addUser(test);

			//Long id_to_be_found = new Long(52);

			User gasit = userService.findFirstByUsernameAndPassword("admin", "admin");
			System.out.println(gasit.getNume());
			*/
			//launch(args);
			/*
			PersonalInfo pi = new PersonalInfo();

			User bilu = new User(null,"Bilu",true,9,6,pi,null);
			userRepository.save(bilu);
			Long id = new Long(1);
			//System.out.println(userRepository.findById(id));
			System.out.println(userService.findFirstById(id));

			Date firstDate1 = new Date(2021, 1, 12);
			Offer oferta = new Offer(null,firstDate1);
			offerRepository.save(oferta);
			//System.out.println(offerRepository.findFirstById(id));
			*/

			//List<Offer> oferte = offerRepository.findAllOffers();
			/*
			Masina masina = new Masina(null, "masina1", null);
			masinaRepository.save(masina);

			Proprietar proprietar = new Proprietar(null, "Ioan", null);
			proprietarRepository.save(proprietar);

			Masina masina1 = new Masina(null, "masina2", null);
			List<Masina> masini = new ArrayList<>();
			masini.add(masina);
			masini.add(masina1);
			masinaRepository.saveAll(masini);
			proprietar.setMasini(masini);
			proprietarRepository.save(proprietar);
			*/


		};
	}

}
