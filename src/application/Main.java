package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.chart.BubbleChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Main extends Application {
	Stage window;
	// main Scenes
	Scene mainPage, customerPage, mediaPage, rentPage;
	// customer Scenes
	Scene addCustomerPage, deleteCustomerPage, updateCustomerPage, searchCustomerPage, printCustomerPage;
	// media Scenes
	Scene addMediaPage, deleteMediaPage, updateMediaPage, searchMediaPage, printMediaPage;
	Scene addMovieMedia, addMusicMedia, addGameMedia;
	Scene deleteMovieMedia, deleteMusicMedia, deleteGameMedia;
	Scene updateMovieMedia, updateMusicMedia, updateGameMedia;
	// rent Scenes
	Scene addToCartPage, printCartRentPage, printRentedRentPage, returnMediaPage;

	MediaRentalManager manager = new MediaRentalManager();

	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;

		// ================== Main page =============================================
		Image mainBack = new Image(getClass().getResourceAsStream("mainBack.png"));
		ImageView mainBackImage = new ImageView(mainBack);
		mainBackImage.setFitHeight(421);
		mainBackImage.setFitWidth(736);

		Image FbImage = new Image(getClass().getResourceAsStream("facebook.png"));
		ImageView FbImageView = new ImageView(FbImage);
		FbImageView.setFitHeight(70);
		FbImageView.setFitWidth(70);
		FbImageView.setLayoutX(650);
		FbImageView.setLayoutY(20);
		FbImageView.setOnMousePressed(e -> getHostServices().showDocument("https://www.fb.com/mohalad12"));

		Image mainBackLogo = new Image(getClass().getResourceAsStream("mainBackLogoImage.png"));
		ImageView mainBackLogoImage = new ImageView(mainBackLogo);
		mainBackLogoImage.setFitHeight(220);
		mainBackLogoImage.setFitWidth(243);
		mainBackLogoImage.setLayoutX(240);
		mainBackLogoImage.setLayoutY(-24);

		Label mainLabel = new Label("Media Rental System");
		mainLabel.setLayoutX(260);
		mainLabel.setLayoutY(149);
		mainLabel.setFont(Font.font("Haettenschweiler", 33));
		mainLabel.setTextFill(Color.web("#ffffff"));

		Label mainNameLabel = new Label("Mohammad Ziad Ladadweh");
		mainNameLabel.setLayoutX(230);
		mainNameLabel.setLayoutY(370);
		mainNameLabel.setFont(Font.font("Haettenschweiler", 33));
		mainNameLabel.setTextFill(Color.web("#ffffff"));

		// Customer Button
		Image customerImage = new Image(getClass().getResourceAsStream("customerImage.png"));
		ImageView customerImageView = new ImageView(customerImage);
		customerImageView.setFitHeight(70);
		customerImageView.setFitWidth(70);
		Button customerButton = new Button("Customer", customerImageView);
		customerButton.setLayoutX(53);
		customerButton.setLayoutY(238);
		customerButton.setPrefWidth(200);
		customerButton.setPrefHeight(123);
		customerButton.setFont(Font.font("Lato light", FontWeight.LIGHT, 17));
		customerButton.setContentDisplay(ContentDisplay.TOP);
		customerButton.setOnAction(e -> window.setScene(customerPage));

		// Media Button
		Image mediaImage = new Image(getClass().getResourceAsStream("mediaImage.png"));
		ImageView mediaImageView = new ImageView(mediaImage);
		mediaImageView.setFitHeight(80);
		mediaImageView.setFitWidth(129);
		Button MediaButton = new Button("Media", mediaImageView);
		MediaButton.setLayoutX(269);
		MediaButton.setLayoutY(238);
		MediaButton.setPrefWidth(200);
		MediaButton.setPrefHeight(123);
		MediaButton.setFont(Font.font("Lato light", FontWeight.LIGHT, 17));
		MediaButton.setContentDisplay(ContentDisplay.TOP);
		MediaButton.setOnAction(e -> window.setScene(mediaPage));

		// Rent Button
		Image rentImage = new Image(getClass().getResourceAsStream("rentImage.png"));
		ImageView rentImageView = new ImageView(rentImage);
		rentImageView.setFitHeight(70);
		rentImageView.setFitWidth(74);
		Button rentButton = new Button("Rent", rentImageView);
		rentButton.setLayoutX(484);
		rentButton.setLayoutY(238);
		rentButton.setPrefWidth(200);
		rentButton.setPrefHeight(123);
		rentButton.setFont(Font.font("Lato light", FontWeight.LIGHT, 17));
		rentButton.setContentDisplay(ContentDisplay.TOP);
		rentButton.setOnAction(e -> window.setScene(rentPage));

		Pane mainPane = new Pane();
		mainPane.getChildren().addAll(mainBackImage, mainBackLogoImage, mainLabel, FbImageView, mainNameLabel,
				customerButton, MediaButton, rentButton);
		Scene mainPage = new Scene(mainPane, 736, 421);

//========================= Customer page ==========================================
		Image customerBackground = new Image(getClass().getResourceAsStream("mainBack.png"));
		ImageView customerBackgroundImage = new ImageView(customerBackground);
		customerBackgroundImage.setFitHeight(421);
		customerBackgroundImage.setFitWidth(736);

		Image customerImagelogo = new Image(getClass().getResourceAsStream("customerImage.png"));
		ImageView customerImagelogoView = new ImageView(customerImagelogo);
		customerImagelogoView.setFitHeight(90);
		customerImagelogoView.setFitWidth(90);
		customerImagelogoView.setLayoutX(256);
		customerImagelogoView.setLayoutY(14);

		Label customerLabel = new Label("Customer");
		customerLabel.setLayoutX(368);
		customerLabel.setLayoutY(54);
		customerLabel.setFont(Font.font("Lato Black", 22));
		customerLabel.setTextFill(Color.web("#ffffff"));

		// Add Customer
		Image addCustomerImage = new Image(getClass().getResourceAsStream("addCustomer1.png"));
		ImageView addCustomerImageView = new ImageView(addCustomerImage);
		addCustomerImageView.setFitHeight(55);
		addCustomerImageView.setFitWidth(55);
		Button addCustomerButtonPage = new Button("Add Customer", addCustomerImageView);
		addCustomerButtonPage.setLayoutX(108);
		addCustomerButtonPage.setLayoutY(168);
		addCustomerButtonPage.setPrefWidth(148);
		addCustomerButtonPage.setPrefHeight(102);
		addCustomerButtonPage.setFont(Font.font("Fenton", 18));
		addCustomerButtonPage.setContentDisplay(ContentDisplay.TOP);
		addCustomerButtonPage.setOnAction(e -> window.setScene(addCustomerPage));

		// Delete Customer
		Image deleteCustomer = new Image(getClass().getResourceAsStream("deleteCustomer.png"));
		ImageView deleteCustomerImage = new ImageView(deleteCustomer);
		deleteCustomerImage.setFitHeight(52);
		deleteCustomerImage.setFitWidth(56);
		Button deleteCustomerButton = new Button("Delete Customer", deleteCustomerImage);
		deleteCustomerButton.setLayoutX(294);
		deleteCustomerButton.setLayoutY(168);
		deleteCustomerButton.setPrefWidth(148);
		deleteCustomerButton.setPrefHeight(102);
		deleteCustomerButton.setFont(Font.font("Fenton", 16));
		deleteCustomerButton.setContentDisplay(ContentDisplay.TOP);
		deleteCustomerButton.setOnAction(e -> window.setScene(deleteCustomerPage));

		// Update Customer
		Image updateCustomer = new Image(getClass().getResourceAsStream("updateCustomer.png"));
		ImageView updateCustomerImage = new ImageView(updateCustomer);
		updateCustomerImage.setFitHeight(58);
		updateCustomerImage.setFitWidth(59);
		Button updateCustomerButton = new Button("Update Info", updateCustomerImage);
		updateCustomerButton.setLayoutX(481);
		updateCustomerButton.setLayoutY(168);
		updateCustomerButton.setPrefWidth(148);
		updateCustomerButton.setPrefHeight(102);
		updateCustomerButton.setFont(Font.font("Fenton", 17));
		updateCustomerButton.setContentDisplay(ContentDisplay.TOP);
		updateCustomerButton.setOnAction(e -> window.setScene(updateCustomerPage));

		// Search Customer
		Image searchCustomer = new Image(getClass().getResourceAsStream("searchCustomer.png"));
		ImageView searchCustomerImage = new ImageView(searchCustomer);
		searchCustomerImage.setFitHeight(53);
		searchCustomerImage.setFitWidth(70);
		Button searchCustomerButton = new Button("Search a Customer", searchCustomerImage);
		searchCustomerButton.setLayoutX(201);
		searchCustomerButton.setLayoutY(295);
		searchCustomerButton.setPrefWidth(148);
		searchCustomerButton.setPrefHeight(102);
		searchCustomerButton.setFont(Font.font("Fenton", 14));
		searchCustomerButton.setContentDisplay(ContentDisplay.TOP);
		searchCustomerButton.setOnAction(e -> window.setScene(searchCustomerPage));

		Image printCustomer = new Image(getClass().getResourceAsStream("addCustomer1.png"));
		ImageView printCustomerImage = new ImageView(printCustomer);
		printCustomerImage.setFitHeight(55);
		printCustomerImage.setFitWidth(55);
		Button printCustomerButton = new Button("Add Customer", printCustomerImage);
		printCustomerButton.setLayoutX(108);
		printCustomerButton.setLayoutY(168);
		printCustomerButton.setPrefWidth(148);
		printCustomerButton.setPrefHeight(102);
		printCustomerButton.setFont(Font.font("Fenton", 18));
		printCustomerButton.setContentDisplay(ContentDisplay.TOP);
		printCustomerButton.setOnAction(e -> window.setScene(printCustomerPage));

		// return to main page
		Image backCustomer = new Image(getClass().getResourceAsStream("Back.png"));
		ImageView backCustomerImage = new ImageView(backCustomer);
		backCustomerImage.setFitHeight(48);
		backCustomerImage.setFitWidth(65);
		Button backCustomerButton = new Button("Return to main page", backCustomerImage);
		backCustomerButton.setLayoutX(393);
		backCustomerButton.setLayoutY(295);
		backCustomerButton.setPrefWidth(148);
		backCustomerButton.setPrefHeight(102);
		backCustomerButton.setFont(Font.font("Fenton", 13));
		backCustomerButton.setContentDisplay(ContentDisplay.TOP);
		backCustomerButton.setOnAction(e -> window.setScene(mainPage));

		Pane customerPane = new Pane();
		customerPane.getChildren().addAll(customerBackgroundImage, customerImagelogoView, customerLabel,
				printCustomerButton, addCustomerButtonPage, deleteCustomerButton, updateCustomerButton,
				searchCustomerButton, backCustomerButton);
		customerPage = new Scene(customerPane, 736, 421);

		// ========================= Add Customer page
		// ==========================================

		Image addCustomerBackground = new Image(getClass().getResourceAsStream("mainBack.png"));
		ImageView addCustomerBackgroundImage = new ImageView(addCustomerBackground);
		addCustomerBackgroundImage.setFitHeight(421);
		addCustomerBackgroundImage.setFitWidth(736);

		Image addCustomerImagelogo = new Image(getClass().getResourceAsStream("addCustomer1.png"));
		ImageView addCustomerImagelogoView = new ImageView(addCustomerImagelogo);
		addCustomerImagelogoView.setFitHeight(77);
		addCustomerImagelogoView.setFitWidth(82);
		addCustomerImagelogoView.setLayoutX(257);
		addCustomerImagelogoView.setLayoutY(26);

		Label addCustomerLabel = new Label("Add Customer");
		addCustomerLabel.setLayoutX(334);
		addCustomerLabel.setLayoutY(52);
		addCustomerLabel.setFont(Font.font("Lato Black", 20));
		addCustomerLabel.setTextFill(Color.web("#ffffff"));

		// label Customer id
		Label customerIdLabel = new Label("Customer ID :");
		customerIdLabel.setLayoutX(161);
		customerIdLabel.setLayoutY(114);
		customerIdLabel.setFont(Font.font("Fenton", 15));
		customerIdLabel.setTextFill(Color.web("#000000"));

		TextField customerIdText = new TextField();
		customerIdText.setLayoutX(101);
		customerIdText.setLayoutY(141);
		customerIdText.setPrefHeight(32);
		customerIdText.setPrefWidth(217);

		// label Customer name
		Label customerNameLabel = new Label("Customer Name :");
		customerNameLabel.setLayoutX(462);
		customerNameLabel.setLayoutY(114);
		customerNameLabel.setFont(Font.font("Fenton", 15));
		customerNameLabel.setTextFill(Color.web("#000000"));

		TextField customerNameText = new TextField();
		customerNameText.setLayoutX(417);
		customerNameText.setLayoutY(141);
		customerNameText.setPrefHeight(32);
		customerNameText.setPrefWidth(217);

		// label Customer address
		Label customerAddressLabel = new Label("Customer Address :");
		customerAddressLabel.setLayoutX(141);
		customerAddressLabel.setLayoutY(217);
		customerAddressLabel.setFont(Font.font("Fenton", 15));
		customerAddressLabel.setTextFill(Color.web("#000000"));

		TextField customerAddressText = new TextField();
		customerAddressText.setLayoutX(101);
		customerAddressText.setLayoutY(241);
		customerAddressText.setPrefHeight(32);
		customerAddressText.setPrefWidth(217);

		// label Customer Mobile
		Label customerMobileLabel = new Label("Customer Mobile :");
		customerMobileLabel.setLayoutX(463);
		customerMobileLabel.setLayoutY(217);
		customerMobileLabel.setFont(Font.font("Fenton", 15));
		customerMobileLabel.setTextFill(Color.web("#000000"));

		TextField customerMobileText = new TextField();
		customerMobileText.setLayoutX(417);
		customerMobileText.setLayoutY(241);
		customerMobileText.setPrefHeight(32);
		customerMobileText.setPrefWidth(217);

		// label Customer Plan
		Label customerPlanLabel = new Label("Plan :");
		customerPlanLabel.setLayoutX(274);
		customerPlanLabel.setLayoutY(307);
		customerPlanLabel.setFont(Font.font("Fenton", 15));
		customerPlanLabel.setTextFill(Color.web("#000000"));

		RadioButton limitedAddRadio = new RadioButton("LIMITED");
		limitedAddRadio.setLayoutX(327);
		limitedAddRadio.setLayoutY(308);
		limitedAddRadio.setFont(Font.font("Fenton", 12));

		RadioButton unlimitedAddRadio = new RadioButton("UNLIMITED");
		unlimitedAddRadio.setLayoutX(401);
		unlimitedAddRadio.setLayoutY(308);
		unlimitedAddRadio.setFont(Font.font("Fenton", 12));

		ToggleGroup planGroup = new ToggleGroup();
		limitedAddRadio.setToggleGroup(planGroup);
		unlimitedAddRadio.setToggleGroup(planGroup);

		// deafult Plan selected is limited
		limitedAddRadio.setSelected(true);

		// label Customer not found
		Label addCustomerFoundLabel = new Label("");
		addCustomerFoundLabel.setLayoutX(290);
		addCustomerFoundLabel.setLayoutY(200);
		addCustomerFoundLabel.setFont(Font.font("Fenton", 12));
		addCustomerFoundLabel.setTextFill(Color.web("#000000"));

		// Add Button
		Image addButton = new Image(getClass().getResourceAsStream("add.png"));
		ImageView addButtonImage = new ImageView(addButton);
		addButtonImage.setFitHeight(30);
		addButtonImage.setFitWidth(30);
		Button addButtonDone = new Button("Add", addButtonImage);
		addButtonDone.setLayoutX(296);
		addButtonDone.setLayoutY(336);
		addButtonDone.setPrefWidth(57);
		addButtonDone.setPrefHeight(57);
		addButtonDone.setFont(Font.font("Fenton", 12));
		addButtonDone.setContentDisplay(ContentDisplay.TOP);
		addButtonDone.setOnAction(e -> {
			Customer addC = manager.searchByID(customerIdText.getText());
			if (addC == null) {
				String Splan = "";
				if (limitedAddRadio.isSelected()) {
					Splan = "LIMITED";
				} else if (unlimitedAddRadio.isSelected()) {
					Splan = "UNLIMITED";
				}
				manager.addCustomer(customerNameText.getText(), customerAddressText.getText(), Splan,
						customerIdText.getText(), customerMobileText.getText());
				addCustomerFoundLabel.setText("The customer has been added succsessfully");
				customerIdText.setText("");
				customerNameText.setText("");
				customerAddressText.setText("");
				customerMobileText.setText("");
				limitedAddRadio.setSelected(false);
				unlimitedAddRadio.setSelected(false);
			} else {
				addCustomerFoundLabel.setText("The customer id is already exist");
				customerIdText.setText("");
			}
		});

		// Back button
		Image backAddCustomer = new Image(getClass().getResourceAsStream("Back.png"));
		ImageView backAddCustomerImage = new ImageView(backAddCustomer);
		backAddCustomerImage.setFitHeight(30);
		backAddCustomerImage.setFitWidth(30);
		Button backAddCustomerButton = new Button("Back", backAddCustomerImage);
		backAddCustomerButton.setLayoutX(400);
		backAddCustomerButton.setLayoutY(336);
		backAddCustomerButton.setPrefWidth(57);
		backAddCustomerButton.setPrefHeight(57);
		backAddCustomerButton.setFont(Font.font("Fenton", 12));
		backAddCustomerButton.setContentDisplay(ContentDisplay.TOP);
		backAddCustomerButton.setOnAction(e -> {
			addCustomerFoundLabel.setText("");
			customerIdText.setText("");
			customerNameText.setText("");
			customerAddressText.setText("");
			customerMobileText.setText("");
			window.setScene(customerPage);
		});

		Pane addCustomerPane = new Pane();
		addCustomerPane.getChildren().addAll(addCustomerBackgroundImage, addCustomerImagelogoView, addCustomerLabel,
				customerPlanLabel, unlimitedAddRadio, limitedAddRadio, customerIdLabel, customerIdText,
				customerNameLabel, customerNameText, customerAddressLabel, customerAddressText, customerMobileLabel,
				customerMobileText, addButtonImage, addButtonDone, addCustomerFoundLabel, backAddCustomerImage,
				backAddCustomerButton);
		addCustomerPage = new Scene(addCustomerPane, 736, 421);

		// ========================= Delete Customer page
		// ==========================================

		Image deleteCustomerBackground = new Image(getClass().getResourceAsStream("mainBack.png"));
		ImageView deleteCustomerBackgroundImage = new ImageView(deleteCustomerBackground);
		deleteCustomerBackgroundImage.setFitHeight(421);
		deleteCustomerBackgroundImage.setFitWidth(736);

		Image deleteCustomerImagelogo = new Image(getClass().getResourceAsStream("deleteCustomer.png"));
		ImageView deleteCustomerImagelogoView = new ImageView(deleteCustomerImagelogo);
		deleteCustomerImagelogoView.setFitHeight(77);
		deleteCustomerImagelogoView.setFitWidth(82);
		deleteCustomerImagelogoView.setLayoutX(257);
		deleteCustomerImagelogoView.setLayoutY(26);

		Label deleteCustomerLabel = new Label("Delete Customer");
		deleteCustomerLabel.setLayoutX(334);
		deleteCustomerLabel.setLayoutY(52);
		deleteCustomerLabel.setFont(Font.font("Lato Black", 20));
		deleteCustomerLabel.setTextFill(Color.web("#ffffff"));

		// label Customer id
		Label deleteCustomerIdLabel = new Label("Customer ID :");
		deleteCustomerIdLabel.setLayoutX(161);
		deleteCustomerIdLabel.setLayoutY(131);
		deleteCustomerIdLabel.setFont(Font.font("Fenton", 15));
		deleteCustomerIdLabel.setTextFill(Color.web("#000000"));

		TextField deleteCustomerIdText = new TextField();
		deleteCustomerIdText.setLayoutX(101);
		deleteCustomerIdText.setLayoutY(158);
		deleteCustomerIdText.setPrefHeight(32);
		deleteCustomerIdText.setPrefWidth(217);

		// label Customer name
		Label deleteCustomerNameLabel = new Label("Customer Name :");
		deleteCustomerNameLabel.setLayoutX(462);
		deleteCustomerNameLabel.setLayoutY(131);
		deleteCustomerNameLabel.setFont(Font.font("Fenton", 15));
		deleteCustomerNameLabel.setTextFill(Color.web("#000000"));

		TextField deleteCustomerNameText = new TextField();
		deleteCustomerNameText.setLayoutX(417);
		deleteCustomerNameText.setLayoutY(158);
		deleteCustomerNameText.setPrefHeight(32);
		deleteCustomerNameText.setPrefWidth(217);

		// label Customer address
		Label deleteCustomerAddressLabel = new Label("Customer Address :");
		deleteCustomerAddressLabel.setLayoutX(141);
		deleteCustomerAddressLabel.setLayoutY(234);
		deleteCustomerAddressLabel.setFont(Font.font("Fenton", 15));
		deleteCustomerAddressLabel.setTextFill(Color.web("#000000"));

		TextField deleteCustomerAddressText = new TextField();
		deleteCustomerAddressText.setLayoutX(101);
		deleteCustomerAddressText.setLayoutY(258);
		deleteCustomerAddressText.setPrefHeight(32);
		deleteCustomerAddressText.setPrefWidth(217);

		// label Customer Mobile
		Label deleteCustomerMobileLabel = new Label("Customer Mobile :");
		deleteCustomerMobileLabel.setLayoutX(463);
		deleteCustomerMobileLabel.setLayoutY(234);
		deleteCustomerMobileLabel.setFont(Font.font("Fenton", 15));
		deleteCustomerMobileLabel.setTextFill(Color.web("#000000"));

		TextField deleteCustomerMobileText = new TextField();
		deleteCustomerMobileText.setLayoutX(417);
		deleteCustomerMobileText.setLayoutY(258);
		deleteCustomerMobileText.setPrefHeight(32);
		deleteCustomerMobileText.setPrefWidth(217);

		// label Customer not found
		Label deleteCustomerFoundLabel = new Label("");
		deleteCustomerFoundLabel.setLayoutX(290);
		deleteCustomerFoundLabel.setLayoutY(211);
		deleteCustomerFoundLabel.setFont(Font.font("Fenton", 12));
		deleteCustomerFoundLabel.setTextFill(Color.web("#000000"));

		// delete Find Button
		Image deleteFindButton = new Image(getClass().getResourceAsStream("search.png"));
		ImageView deleteFindButtonImage = new ImageView(deleteFindButton);
		deleteFindButtonImage.setFitHeight(30);
		deleteFindButtonImage.setFitWidth(30);
		Button deleteFindButtonDone = new Button("Find", deleteFindButtonImage);
		deleteFindButtonDone.setLayoutX(239);
		deleteFindButtonDone.setLayoutY(336);
		deleteFindButtonDone.setPrefWidth(57);
		deleteFindButtonDone.setPrefHeight(57);
		deleteFindButtonDone.setFont(Font.font("Fenton", 12));
		deleteFindButtonDone.setContentDisplay(ContentDisplay.TOP);
		deleteFindButtonDone.setOnAction(e -> {

			Customer deleteFind = manager.searchByID(deleteCustomerIdText.getText());
			if (deleteFind == null) {
				deleteCustomerFoundLabel.setText("The Customer ID is not Found !");
				deleteCustomerIdText.setText("");
				deleteCustomerNameText.setText("");
				deleteCustomerAddressText.setText("");
				deleteCustomerMobileText.setText("");
			} else {
				deleteCustomerNameText.setText(deleteFind.getName());
				deleteCustomerAddressText.setText(deleteFind.getAddress());
				deleteCustomerMobileText.setText(deleteFind.getMobile());
				deleteCustomerNameText.setEditable(false);
				deleteCustomerAddressText.setEditable(false);
				deleteCustomerMobileText.setEditable(false);
				deleteCustomerFoundLabel.setText("The Customer is Found !");
			}

		});

		// Delete Button
		Image deleteButton = new Image(getClass().getResourceAsStream("delete.png"));
		ImageView deleteButtonImage = new ImageView(deleteButton);
		deleteButtonImage.setFitHeight(30);
		deleteButtonImage.setFitWidth(30);
		Button deleteButtonDone = new Button("Delete", deleteButtonImage);
		deleteButtonDone.setLayoutX(344);
		deleteButtonDone.setLayoutY(336);
		deleteButtonDone.setPrefWidth(57);
		deleteButtonDone.setPrefHeight(57);
		deleteButtonDone.setFont(Font.font("Fenton", 12));
		deleteButtonDone.setContentDisplay(ContentDisplay.TOP);
		deleteButtonDone.setOnAction(e -> {
			Customer deleteAfter = manager.searchByID(deleteCustomerIdText.getText());
			manager.customer.remove(deleteAfter);
			deleteCustomerIdText.setText("");
			deleteCustomerNameText.setText("");
			deleteCustomerAddressText.setText("");
			deleteCustomerMobileText.setText("");
			deleteCustomerNameText.setEditable(true);
			deleteCustomerAddressText.setEditable(true);
			deleteCustomerMobileText.setEditable(true);
			deleteCustomerFoundLabel.setText("The Customer has been deleted successfully !");
		});

		// Back button
		Image backDeleteCustomer = new Image(getClass().getResourceAsStream("Back.png"));
		ImageView backDeleteCustomerImage = new ImageView(backDeleteCustomer);
		backDeleteCustomerImage.setFitHeight(30);
		backDeleteCustomerImage.setFitWidth(30);
		Button backDeleteCustomerButton = new Button("Back", backDeleteCustomerImage);
		backDeleteCustomerButton.setLayoutX(448);
		backDeleteCustomerButton.setLayoutY(336);
		backDeleteCustomerButton.setPrefWidth(57);
		backDeleteCustomerButton.setPrefHeight(57);
		backDeleteCustomerButton.setFont(Font.font("Fenton", 12));
		backDeleteCustomerButton.setContentDisplay(ContentDisplay.TOP);
		backDeleteCustomerButton.setOnAction(e -> {
			deleteCustomerFoundLabel.setText("");
			window.setScene(customerPage);
		});

		Pane deleteCustomerPane = new Pane();
		deleteCustomerPane.getChildren().addAll(deleteCustomerBackgroundImage, deleteCustomerImagelogoView,
				deleteCustomerFoundLabel, deleteCustomerLabel, deleteCustomerIdLabel, deleteCustomerIdText,
				deleteCustomerNameLabel, deleteCustomerNameText, deleteCustomerAddressLabel, deleteCustomerAddressText,
				deleteCustomerMobileLabel, deleteCustomerMobileText, deleteButtonImage, deleteFindButtonImage,
				deleteFindButtonDone, deleteButtonDone, backDeleteCustomerImage, backDeleteCustomerButton);
		deleteCustomerPage = new Scene(deleteCustomerPane, 736, 421);

		// ========================= Update Customer Info page
		// ==========================================

		Image updateCustomerBackground = new Image(getClass().getResourceAsStream("mainBack.png"));
		ImageView updateCustomerBackgroundImage = new ImageView(updateCustomerBackground);
		updateCustomerBackgroundImage.setFitHeight(421);
		updateCustomerBackgroundImage.setFitWidth(736);

		Image updateCustomerImagelogo = new Image(getClass().getResourceAsStream("updateCustomer.png"));
		ImageView updateCustomerImagelogoView = new ImageView(updateCustomerImagelogo);
		updateCustomerImagelogoView.setFitHeight(77);
		updateCustomerImagelogoView.setFitWidth(82);
		updateCustomerImagelogoView.setLayoutX(257);
		updateCustomerImagelogoView.setLayoutY(26);

		Label updateCustomerLabel = new Label("Update Informations");
		updateCustomerLabel.setLayoutX(334);
		updateCustomerLabel.setLayoutY(52);
		updateCustomerLabel.setFont(Font.font("Lato Black", 20));
		updateCustomerLabel.setTextFill(Color.web("#ffffff"));

		// label Customer id
		Label updateCustomerIdLabel = new Label("Customer ID :");
		updateCustomerIdLabel.setLayoutX(161);
		updateCustomerIdLabel.setLayoutY(131);
		updateCustomerIdLabel.setFont(Font.font("Fenton", 15));
		updateCustomerIdLabel.setTextFill(Color.web("#000000"));

		TextField updateCustomerIdText = new TextField();
		updateCustomerIdText.setLayoutX(101);
		updateCustomerIdText.setLayoutY(158);
		updateCustomerIdText.setPrefHeight(32);
		updateCustomerIdText.setPrefWidth(217);

		// label Customer name
		Label updateCustomerNameLabel = new Label("Customer Name :");
		updateCustomerNameLabel.setLayoutX(462);
		updateCustomerNameLabel.setLayoutY(131);
		updateCustomerNameLabel.setFont(Font.font("Fenton", 15));
		updateCustomerNameLabel.setTextFill(Color.web("#000000"));

		TextField updateCustomerNameText = new TextField();
		updateCustomerNameText.setLayoutX(417);
		updateCustomerNameText.setLayoutY(158);
		updateCustomerNameText.setPrefHeight(32);
		updateCustomerNameText.setPrefWidth(217);

		// label Customer address
		Label updateCustomerAddressLabel = new Label("Customer Address :");
		updateCustomerAddressLabel.setLayoutX(141);
		updateCustomerAddressLabel.setLayoutY(234);
		updateCustomerAddressLabel.setFont(Font.font("Fenton", 15));
		updateCustomerAddressLabel.setTextFill(Color.web("#000000"));

		TextField updateCustomerAddressText = new TextField();
		updateCustomerAddressText.setLayoutX(101);
		updateCustomerAddressText.setLayoutY(258);
		updateCustomerAddressText.setPrefHeight(32);
		updateCustomerAddressText.setPrefWidth(217);

		// label Customer Mobile
		Label updateCustomerMobileLabel = new Label("Customer Mobile :");
		updateCustomerMobileLabel.setLayoutX(463);
		updateCustomerMobileLabel.setLayoutY(234);
		updateCustomerMobileLabel.setFont(Font.font("Fenton", 15));
		updateCustomerMobileLabel.setTextFill(Color.web("#000000"));

		TextField updateCustomerMobileText = new TextField();
		updateCustomerMobileText.setLayoutX(417);
		updateCustomerMobileText.setLayoutY(258);
		updateCustomerMobileText.setPrefHeight(32);
		updateCustomerMobileText.setPrefWidth(217);

		// label Customer not found
		Label updateCustomerFoundLabel = new Label("");
		updateCustomerFoundLabel.setLayoutX(290);
		updateCustomerFoundLabel.setLayoutY(211);
		updateCustomerFoundLabel.setFont(Font.font("Fenton", 12));
		updateCustomerFoundLabel.setTextFill(Color.web("#000000"));

		// update Find Button
		Image updateFindButton = new Image(getClass().getResourceAsStream("search.png"));
		ImageView updateFindButtonImage = new ImageView(updateFindButton);
		updateFindButtonImage.setFitHeight(30);
		updateFindButtonImage.setFitWidth(30);
		Button updateFindButtonDone = new Button("Find", updateFindButtonImage);
		updateFindButtonDone.setLayoutX(239);
		updateFindButtonDone.setLayoutY(336);
		updateFindButtonDone.setPrefWidth(57);
		updateFindButtonDone.setPrefHeight(57);
		updateFindButtonDone.setFont(Font.font("Fenton", 12));
		updateFindButtonDone.setContentDisplay(ContentDisplay.TOP);
		updateFindButtonDone.setOnAction(e -> {

			Customer updateFind = manager.searchByID(updateCustomerIdText.getText());
			if (updateFind == null) {
				updateCustomerFoundLabel.setText("The Customer ID is not Found !");
				updateCustomerIdText.setText("");
				updateCustomerNameText.setText("");
				updateCustomerAddressText.setText("");
				updateCustomerMobileText.setText("");
			} else {
				updateCustomerNameText.setText(updateFind.getName());
				updateCustomerAddressText.setText(updateFind.getAddress());
				updateCustomerMobileText.setText(updateFind.getMobile());
				updateCustomerNameText.setEditable(true);
				updateCustomerAddressText.setEditable(true);
				updateCustomerMobileText.setEditable(true);
				updateCustomerFoundLabel.setText("The Customer is Found !");
			}

		});

		// Update Button
		Image updateButton = new Image(getClass().getResourceAsStream("update.png"));
		ImageView updateButtonImage = new ImageView(updateButton);
		updateButtonImage.setFitHeight(30);
		updateButtonImage.setFitWidth(30);
		Button updateButtonDone = new Button("Update", updateButtonImage);
		updateButtonDone.setLayoutX(344);
		updateButtonDone.setLayoutY(336);
		updateButtonDone.setPrefWidth(57);
		updateButtonDone.setPrefHeight(57);
		updateButtonDone.setFont(Font.font("Fenton", 12));
		updateButtonDone.setContentDisplay(ContentDisplay.TOP);
		updateButtonDone.setOnAction(e -> {
			Customer updateAfter = manager.searchByID(updateCustomerIdText.getText());

			if (updateCustomerIdText.getText() == "" || updateCustomerNameText.getText() == ""
					|| updateCustomerAddressText.getText() == "" || updateCustomerMobileText.getText() == "") {
				updateCustomerFoundLabel.setText("Please Fill the fields");
			} else {
				updateAfter.setName(updateCustomerNameText.getText());
				updateAfter.setAddress(updateCustomerAddressText.getText());
				updateAfter.setMobile(updateCustomerMobileText.getText());

				updateCustomerIdText.setText("");
				updateCustomerNameText.setText("");
				updateCustomerAddressText.setText("");
				updateCustomerMobileText.setText("");

				updateCustomerFoundLabel.setText("The Customer has been updated successfully !");
			}
		});

		// Back button
		Image backUpdateCustomer = new Image(getClass().getResourceAsStream("Back.png"));
		ImageView backUpdateCustomerImage = new ImageView(backUpdateCustomer);
		backUpdateCustomerImage.setFitHeight(30);
		backUpdateCustomerImage.setFitWidth(30);
		Button backUpdateCustomerButton = new Button("Back", backUpdateCustomerImage);
		backUpdateCustomerButton.setLayoutX(448);
		backUpdateCustomerButton.setLayoutY(336);
		backUpdateCustomerButton.setPrefWidth(57);
		backUpdateCustomerButton.setPrefHeight(57);
		backUpdateCustomerButton.setFont(Font.font("Fenton", 12));
		backUpdateCustomerButton.setContentDisplay(ContentDisplay.TOP);
		backUpdateCustomerButton.setOnAction(e -> window.setScene(customerPage));

		Pane updateCustomerPane = new Pane();
		updateCustomerPane.getChildren().addAll(updateCustomerBackgroundImage, updateCustomerImagelogoView,
				updateCustomerLabel, updateCustomerIdLabel, updateCustomerIdText, updateCustomerNameLabel,
				updateCustomerNameText, updateCustomerAddressLabel, updateCustomerAddressText,
				updateCustomerMobileLabel, updateCustomerMobileText, updateButtonImage, updateButtonDone,
				updateFindButtonDone, updateFindButtonImage, backUpdateCustomerImage, backUpdateCustomerButton,
				updateCustomerFoundLabel);
		updateCustomerPage = new Scene(updateCustomerPane, 736, 421);

//========================= Search a Customer page ==========================================

		Image searchCustomerBackground = new Image(getClass().getResourceAsStream("mainBack.png"));
		ImageView searchCustomerBackgroundImage = new ImageView(searchCustomerBackground);
		searchCustomerBackgroundImage.setFitHeight(421);
		searchCustomerBackgroundImage.setFitWidth(736);

		Image searchCustomerImagelogo = new Image(getClass().getResourceAsStream("searchCustomer.png"));
		ImageView searchCustomerImagelogoView = new ImageView(searchCustomerImagelogo);
		searchCustomerImagelogoView.setFitHeight(77);
		searchCustomerImagelogoView.setFitWidth(82);
		searchCustomerImagelogoView.setLayoutX(257);
		searchCustomerImagelogoView.setLayoutY(26);

		Label searchCustomerLabel = new Label("Search a Customer");
		searchCustomerLabel.setLayoutX(334);
		searchCustomerLabel.setLayoutY(52);
		searchCustomerLabel.setFont(Font.font("Lato Black", 20));
		searchCustomerLabel.setTextFill(Color.web("#ffffff"));

		// label Customer id
		Label searchCustomerIdLabel = new Label("Customer ID :");
		searchCustomerIdLabel.setLayoutX(100);
		searchCustomerIdLabel.setLayoutY(181);
		searchCustomerIdLabel.setFont(Font.font("Fenton", 15));
		searchCustomerIdLabel.setTextFill(Color.web("#000000"));

		TextField searchCustomerIdText = new TextField();
		searchCustomerIdText.setLayoutX(40);
		searchCustomerIdText.setLayoutY(208);
		searchCustomerIdText.setPrefHeight(32);
		searchCustomerIdText.setPrefWidth(217);

		TextArea searchCustomerTextArea = new TextArea();
		searchCustomerTextArea.setLayoutX(275);
		searchCustomerTextArea.setLayoutY(119);
		searchCustomerTextArea.setPrefHeight(263);
		searchCustomerTextArea.setPrefWidth(430);
		searchCustomerTextArea.setEditable(false);

		// Search Button
		Image searchButton = new Image(getClass().getResourceAsStream("search.png"));
		ImageView searchButtonImage = new ImageView(searchButton);
		searchButtonImage.setFitHeight(30);
		searchButtonImage.setFitWidth(30);
		Button searchButtonDone = new Button("Search", searchButtonImage);
		searchButtonDone.setLayoutX(72);
		searchButtonDone.setLayoutY(253);
		searchButtonDone.setPrefWidth(57);
		searchButtonDone.setPrefHeight(57);
		searchButtonDone.setFont(Font.font("Fenton", 12));
		searchButtonDone.setContentDisplay(ContentDisplay.TOP);
		searchButtonDone.setOnAction(e -> {

			Customer searchFind = manager.searchByID(searchCustomerIdText.getText());
			if (searchFind == null) {
				searchCustomerTextArea.setText("The Customer ID is not Found !");
				searchCustomerIdText.setText("");
			} else {
				searchCustomerTextArea.setText(searchFind.toString());
				searchCustomerIdText.setText("");
			}

		});

		// Back button
		Image backSearchCustomer = new Image(getClass().getResourceAsStream("Back.png"));
		ImageView backSearchCustomerImage = new ImageView(backSearchCustomer);
		backSearchCustomerImage.setFitHeight(30);
		backSearchCustomerImage.setFitWidth(30);
		Button backSearchCustomerButton = new Button("Back", backSearchCustomerImage);
		backSearchCustomerButton.setLayoutX(167);
		backSearchCustomerButton.setLayoutY(254);
		backSearchCustomerButton.setPrefWidth(57);
		backSearchCustomerButton.setPrefHeight(57);
		backSearchCustomerButton.setFont(Font.font("Fenton", 12));
		backSearchCustomerButton.setContentDisplay(ContentDisplay.TOP);
		backSearchCustomerButton.setOnAction(e -> {
			searchCustomerTextArea.setText("");
			window.setScene(customerPage);
		});

		Pane searchCustomerPane = new Pane();
		searchCustomerPane.getChildren().addAll(searchCustomerBackgroundImage, searchCustomerImagelogoView,
				searchCustomerLabel, searchCustomerIdLabel, searchCustomerIdText, searchCustomerTextArea,
				searchButtonImage, searchButtonDone, backSearchCustomerImage, backSearchCustomerButton);
		searchCustomerPage = new Scene(searchCustomerPane, 736, 421);

//========================================= Media Page ==========================================					

		Image mediaBackground = new Image(getClass().getResourceAsStream("mainBack.png"));
		ImageView mediaBackgroundImage = new ImageView(mediaBackground);
		mediaBackgroundImage.setFitHeight(421);
		mediaBackgroundImage.setFitWidth(736);

		Image mediaImagelogo = new Image(getClass().getResourceAsStream("mediaImage.png"));
		ImageView mediaImagelogoView = new ImageView(mediaImagelogo);
		mediaImagelogoView.setFitHeight(90);
		mediaImagelogoView.setFitWidth(148);
		mediaImagelogoView.setLayoutX(243);
		mediaImagelogoView.setLayoutY(36);

		Label mediaLabel = new Label("Media");
		mediaLabel.setLayoutX(401);
		mediaLabel.setLayoutY(70);
		mediaLabel.setFont(Font.font("Lato Black", 22));
		mediaLabel.setTextFill(Color.web("#ffffff"));

		Image addMediaImage = new Image(getClass().getResourceAsStream("addMedia.png"));
		ImageView addMediaImageView = new ImageView(addMediaImage);
		addMediaImageView.setFitHeight(52);
		addMediaImageView.setFitWidth(56);
		Button addMediaButton = new Button("Add Media", addMediaImageView);
		addMediaButton.setLayoutX(108);
		addMediaButton.setLayoutY(168);
		addMediaButton.setPrefWidth(148);
		addMediaButton.setPrefHeight(102);
		addMediaButton.setFont(Font.font("Fenton", 18));
		addMediaButton.setContentDisplay(ContentDisplay.TOP);
		addMediaButton.setOnAction(e -> window.setScene(addMediaPage));

		Image deleteMediaImage = new Image(getClass().getResourceAsStream("deleteMedia.png"));
		ImageView deleteMediaImageView = new ImageView(deleteMediaImage);
		deleteMediaImageView.setFitHeight(52);
		deleteMediaImageView.setFitWidth(56);
		Button deleteMediaButton = new Button("Delete Media", deleteMediaImageView);
		deleteMediaButton.setLayoutX(294);
		deleteMediaButton.setLayoutY(168);
		deleteMediaButton.setPrefWidth(148);
		deleteMediaButton.setPrefHeight(102);
		deleteMediaButton.setFont(Font.font("Fenton", 18));
		deleteMediaButton.setContentDisplay(ContentDisplay.TOP);
		deleteMediaButton.setOnAction(e -> window.setScene(deleteMediaPage));

		Image updateMediaImage = new Image(getClass().getResourceAsStream("updateMedia.png"));
		ImageView updateMediaImageView = new ImageView(updateMediaImage);
		updateMediaImageView.setFitHeight(52);
		updateMediaImageView.setFitWidth(56);
		Button updateMediaButton = new Button("Update Media", updateMediaImageView);
		updateMediaButton.setLayoutX(481);
		updateMediaButton.setLayoutY(168);
		updateMediaButton.setPrefWidth(148);
		updateMediaButton.setPrefHeight(102);
		updateMediaButton.setFont(Font.font("Fenton", 18));
		updateMediaButton.setContentDisplay(ContentDisplay.TOP);
		updateMediaButton.setOnAction(e -> window.setScene(updateMediaPage));

		Image searchMediaImage = new Image(getClass().getResourceAsStream("searchMedia.png"));
		ImageView searchMediaImageView = new ImageView(searchMediaImage);
		searchMediaImageView.setFitHeight(52);
		searchMediaImageView.setFitWidth(56);
		Button searchMediaButton = new Button("Search Media", searchMediaImageView);
		searchMediaButton.setLayoutX(108);
		searchMediaButton.setLayoutY(295);
		searchMediaButton.setPrefWidth(148);
		searchMediaButton.setPrefHeight(102);
		searchMediaButton.setFont(Font.font("Fenton", 18));
		searchMediaButton.setContentDisplay(ContentDisplay.TOP);
		searchMediaButton.setOnAction(e -> window.setScene(searchMediaPage));

		Image printMediaImage = new Image(getClass().getResourceAsStream("printInfo.png"));
		ImageView printMediaImageView = new ImageView(printMediaImage);
		printMediaImageView.setFitHeight(52);
		printMediaImageView.setFitWidth(56);
		Button printMediaButton = new Button("Print All Media Info", printMediaImageView);
		printMediaButton.setLayoutX(295);
		printMediaButton.setLayoutY(295);
		printMediaButton.setPrefWidth(148);
		printMediaButton.setPrefHeight(102);
		printMediaButton.setFont(Font.font("Fenton", 18));
		printMediaButton.setContentDisplay(ContentDisplay.TOP);
		printMediaButton.setOnAction(e -> window.setScene(printMediaPage));

		Image backMediaImage = new Image(getClass().getResourceAsStream("back.png"));
		ImageView backMediaImageView = new ImageView(backMediaImage);
		backMediaImageView.setFitHeight(52);
		backMediaImageView.setFitWidth(60);
		Button backMediaButton = new Button("Return to main page", backMediaImageView);
		backMediaButton.setLayoutX(481);
		backMediaButton.setLayoutY(295);
		backMediaButton.setPrefWidth(148);
		backMediaButton.setPrefHeight(102);
		backMediaButton.setFont(Font.font("Fenton", 13));
		backMediaButton.setContentDisplay(ContentDisplay.TOP);
		backMediaButton.setOnAction(e -> window.setScene(mainPage));

		Pane mediaPane = new Pane();
		mediaPane.getChildren().addAll(mediaBackgroundImage, mediaImagelogoView, mediaLabel, addMediaButton,
				deleteMediaButton, updateMediaButton, searchMediaButton, printMediaButton, backMediaButton);
		mediaPage = new Scene(mediaPane, 736, 421);

//========================================= Add Media Page ============================

		Image addMediaBackground = new Image(getClass().getResourceAsStream("mainBack.png"));
		ImageView addMediaBackgroundImage = new ImageView(addMediaBackground);
		addMediaBackgroundImage.setFitHeight(421);
		addMediaBackgroundImage.setFitWidth(736);

		Image addMediaImagelogo = new Image(getClass().getResourceAsStream("addMedia.png"));
		ImageView addMediaImagelogoView = new ImageView(addMediaImagelogo);
		addMediaImagelogoView.setFitHeight(77);
		addMediaImagelogoView.setFitWidth(82);
		addMediaImagelogoView.setLayoutX(257);
		addMediaImagelogoView.setLayoutY(26);

		Label addMediaLabel = new Label("Add Media");
		addMediaLabel.setLayoutX(339);
		addMediaLabel.setLayoutY(46);
		addMediaLabel.setFont(Font.font("Lato Black", 20));
		addMediaLabel.setTextFill(Color.web("#ffffff"));

		ComboBox<String> addMediaComboBox = new ComboBox<>();
		addMediaComboBox.getItems().addAll("Movie", "Music", "Game");
		addMediaComboBox.setLayoutX(334);
		addMediaComboBox.setLayoutY(121);
		addMediaComboBox.setPrefWidth(150);
		addMediaComboBox.setPromptText("Please Select Here");
		addMediaComboBox.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (addMediaComboBox.getValue().equals("Movie")) {
					addMovieMedia(primaryStage);
				} else if (addMediaComboBox.getValue().equals("Music")) {
					addMusicMedia(primaryStage);
				} else if (addMediaComboBox.getValue().equals("Game")) {
					addGameMedia(primaryStage);
				}
			}
		});

		Label mediaTypeLabel = new Label("Media Type :");
		mediaTypeLabel.setLayoutX(234);
		mediaTypeLabel.setLayoutY(124);
		mediaTypeLabel.setFont(Font.font("Fenton", 16));
		mediaTypeLabel.setTextFill(Color.web("#ffffff"));

		Image backAddMediaImage = new Image(getClass().getResourceAsStream("back.png"));
		ImageView backAddMediaImageView = new ImageView(backAddMediaImage);
		backAddMediaImageView.setFitHeight(30);
		backAddMediaImageView.setFitWidth(30);
		Button backAddMediaButton = new Button("Back", backAddMediaImageView);
		backAddMediaButton.setLayoutX(649);
		backAddMediaButton.setLayoutY(341);
		backAddMediaButton.setPrefWidth(57);
		backAddMediaButton.setPrefHeight(57);
		backAddMediaButton.setFont(Font.font("Fenton", 13));
		backAddMediaButton.setContentDisplay(ContentDisplay.TOP);
		backAddMediaButton.setOnAction(e -> window.setScene(mediaPage));

		Pane addMediaPane = new Pane();
		addMediaPane.getChildren().addAll(addMediaBackgroundImage, addMediaImagelogoView, addMediaLabel,
				addMediaComboBox, mediaTypeLabel, backAddMediaImageView, backAddMediaButton);
		addMediaPage = new Scene(addMediaPane, 736, 421);

//========================================= delete Media Page ============================

		Image deleteMediaBackground = new Image(getClass().getResourceAsStream("mainBack.png"));
		ImageView deleteMediaBackgroundImage = new ImageView(deleteMediaBackground);
		deleteMediaBackgroundImage.setFitHeight(421);
		deleteMediaBackgroundImage.setFitWidth(736);

		Image deleteMediaImagelogo = new Image(getClass().getResourceAsStream("addMedia.png"));
		ImageView deleteMediaImagelogoView = new ImageView(deleteMediaImagelogo);
		deleteMediaImagelogoView.setFitHeight(77);
		deleteMediaImagelogoView.setFitWidth(82);
		deleteMediaImagelogoView.setLayoutX(257);
		deleteMediaImagelogoView.setLayoutY(26);

		Label deleteMediaLabel = new Label("Delete Media");
		deleteMediaLabel.setLayoutX(339);
		deleteMediaLabel.setLayoutY(46);
		deleteMediaLabel.setFont(Font.font("Lato Black", 20));
		deleteMediaLabel.setTextFill(Color.web("#ffffff"));

		ComboBox<String> deleteMediaComboBox = new ComboBox<>();
		deleteMediaComboBox.getItems().addAll("Movie", "Music", "Game");
		deleteMediaComboBox.setLayoutX(334);
		deleteMediaComboBox.setLayoutY(121);
		deleteMediaComboBox.setPrefWidth(150);
		deleteMediaComboBox.setPromptText("Please Select Here");

		deleteMediaComboBox.setOnAction(e -> {
			if (deleteMediaComboBox.getValue().equals("Movie")) {
				deleteMovieMedia(primaryStage);
			} else if (deleteMediaComboBox.getValue().equals("Music")) {
				deleteMusicMedia(primaryStage);
			} else if (deleteMediaComboBox.getValue().equals("Game")) {
				deleteGameMedia(primaryStage);
			}
		});

		Label deleteMediaTypeLabel = new Label("Media Type :");
		deleteMediaTypeLabel.setLayoutX(234);
		deleteMediaTypeLabel.setLayoutY(124);
		deleteMediaTypeLabel.setFont(Font.font("Fenton", 16));
		deleteMediaTypeLabel.setTextFill(Color.web("#ffffff"));

		Image backDeleteMediaImage = new Image(getClass().getResourceAsStream("back.png"));
		ImageView backDeleteMediaImageView = new ImageView(backDeleteMediaImage);
		backDeleteMediaImageView.setFitHeight(30);
		backDeleteMediaImageView.setFitWidth(30);
		Button backDeleteMediaButton = new Button("Back", backDeleteMediaImageView);
		backDeleteMediaButton.setLayoutX(649);
		backDeleteMediaButton.setLayoutY(341);
		backDeleteMediaButton.setPrefWidth(57);
		backDeleteMediaButton.setPrefHeight(57);
		backDeleteMediaButton.setFont(Font.font("Fenton", 13));
		backDeleteMediaButton.setContentDisplay(ContentDisplay.TOP);
		backDeleteMediaButton.setOnAction(e -> window.setScene(mediaPage));

		Pane deleteMediaPane = new Pane();
		deleteMediaPane.getChildren().addAll(deleteMediaBackgroundImage, deleteMediaImagelogoView, deleteMediaLabel,
				deleteMediaComboBox, deleteMediaTypeLabel, backDeleteMediaImageView, backDeleteMediaButton);
		deleteMediaPage = new Scene(deleteMediaPane, 736, 421);

//========================================= update Media Page ============================

		Image updateMediaBackground = new Image(getClass().getResourceAsStream("mainBack.png"));
		ImageView updateMediaBackgroundImage = new ImageView(updateMediaBackground);
		updateMediaBackgroundImage.setFitHeight(421);
		updateMediaBackgroundImage.setFitWidth(736);

		Image updateMediaImagelogo = new Image(getClass().getResourceAsStream("updateMedia.png"));
		ImageView updateMediaImagelogoView = new ImageView(updateMediaImagelogo);
		updateMediaImagelogoView.setFitHeight(77);
		updateMediaImagelogoView.setFitWidth(82);
		updateMediaImagelogoView.setLayoutX(257);
		updateMediaImagelogoView.setLayoutY(26);

		Label updateMediaLabel = new Label("Update Media");
		updateMediaLabel.setLayoutX(339);
		updateMediaLabel.setLayoutY(46);
		updateMediaLabel.setFont(Font.font("Lato Black", 20));
		updateMediaLabel.setTextFill(Color.web("#ffffff"));

		ComboBox<String> updateMediaComboBox = new ComboBox<>();
		updateMediaComboBox.getItems().addAll("Movie", "Music", "Game");
		updateMediaComboBox.setLayoutX(334);
		updateMediaComboBox.setLayoutY(121);
		updateMediaComboBox.setPrefWidth(150);
		updateMediaComboBox.setPromptText("Please Select Here");

		updateMediaComboBox.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (updateMediaComboBox.getValue().equals("Movie")) {
					updateMovieMedia(primaryStage);
				} else if (updateMediaComboBox.getValue().equals("Music")) {
					updateMusicMedia(primaryStage);
				} else if (updateMediaComboBox.getValue().equals("Game")) {
					updateGameMedia(primaryStage);
				}
			}
		});

		Label updateMediaTypeLabel = new Label("Media Type :");
		updateMediaTypeLabel.setLayoutX(234);
		updateMediaTypeLabel.setLayoutY(124);
		updateMediaTypeLabel.setFont(Font.font("Fenton", 16));
		updateMediaTypeLabel.setTextFill(Color.web("#ffffff"));

		Image backUpdateMediaImage = new Image(getClass().getResourceAsStream("back.png"));
		ImageView backUpdateMediaImageView = new ImageView(backUpdateMediaImage);
		backUpdateMediaImageView.setFitHeight(30);
		backUpdateMediaImageView.setFitWidth(30);
		Button backUpdateMediaButton = new Button("Back", backUpdateMediaImageView);
		backUpdateMediaButton.setLayoutX(649);
		backUpdateMediaButton.setLayoutY(341);
		backUpdateMediaButton.setPrefWidth(57);
		backUpdateMediaButton.setPrefHeight(57);
		backUpdateMediaButton.setFont(Font.font("Fenton", 13));
		backUpdateMediaButton.setContentDisplay(ContentDisplay.TOP);
		backUpdateMediaButton.setOnAction(e -> window.setScene(mediaPage));

		Pane updateMediaPane = new Pane();
		updateMediaPane.getChildren().addAll(updateMediaBackgroundImage, updateMediaImagelogoView, updateMediaLabel,
				updateMediaComboBox, updateMediaTypeLabel, backUpdateMediaImageView, backUpdateMediaButton);
		updateMediaPage = new Scene(updateMediaPane, 736, 421);

//====================================== Search a Media Page =====================================
		Image searchMediaBackground = new Image(getClass().getResourceAsStream("mainBack.png"));
		ImageView searchMediaBackgroundImage = new ImageView(searchMediaBackground);
		searchMediaBackgroundImage.setFitHeight(421);
		searchMediaBackgroundImage.setFitWidth(736);

		Image searchMediaImagelogo = new Image(getClass().getResourceAsStream("searchMedia.png"));
		ImageView searchMediaImagelogoView = new ImageView(searchMediaImagelogo);
		searchMediaImagelogoView.setFitHeight(77);
		searchMediaImagelogoView.setFitWidth(82);
		searchMediaImagelogoView.setLayoutX(257);
		searchMediaImagelogoView.setLayoutY(26);

		Label searchMediaLabel = new Label("Search a Media");
		searchMediaLabel.setLayoutX(350);
		searchMediaLabel.setLayoutY(52);
		searchMediaLabel.setFont(Font.font("Lato Black", 20));
		searchMediaLabel.setTextFill(Color.web("#ffffff"));

		// label Media Code
		Label searchMediaCodeLabel = new Label("Media Code :");
		searchMediaCodeLabel.setLayoutX(100);
		searchMediaCodeLabel.setLayoutY(181);
		searchMediaCodeLabel.setFont(Font.font("Fenton", 15));
		searchMediaCodeLabel.setTextFill(Color.web("#000000"));

		TextField searchMediaCodeText = new TextField();
		searchMediaCodeText.setLayoutX(30);
		searchMediaCodeText.setLayoutY(208);
		searchMediaCodeText.setPrefHeight(32);
		searchMediaCodeText.setPrefWidth(217);

		TextArea searchMediaTextArea = new TextArea();
		searchMediaTextArea.setLayoutX(257);
		searchMediaTextArea.setLayoutY(122);
		searchMediaTextArea.setPrefHeight(268);
		searchMediaTextArea.setPrefWidth(457);
		searchMediaTextArea.setEditable(false);

		// Search Button
		Image mediaSearchButton = new Image(getClass().getResourceAsStream("search.png"));
		ImageView searchMediaButtonImage = new ImageView(mediaSearchButton);
		searchMediaButtonImage.setFitHeight(30);
		searchMediaButtonImage.setFitWidth(30);
		Button searchMediaButtonDone = new Button("Search", searchMediaButtonImage);
		searchMediaButtonDone.setLayoutX(72);
		searchMediaButtonDone.setLayoutY(253);
		searchMediaButtonDone.setPrefWidth(57);
		searchMediaButtonDone.setPrefHeight(57);
		searchMediaButtonDone.setFont(Font.font("Fenton", 12));
		searchMediaButtonDone.setContentDisplay(ContentDisplay.TOP);
		searchMediaButtonDone.setOnAction(e -> {

			Media searchMedia = manager.searchByCode(searchMediaCodeText.getText());
			if (searchMedia == null) {
				searchMediaTextArea.setText("The Media Code is not Found !");
				searchMediaCodeText.setText("");
			} else {
				searchMediaTextArea.setText(searchMedia.toString());
				searchMediaCodeText.setText("");
			}
		});

		// Back button
		Image backSearchMedia = new Image(getClass().getResourceAsStream("Back.png"));
		ImageView backSearchMediaImage = new ImageView(backSearchMedia);
		backSearchMediaImage.setFitHeight(30);
		backSearchMediaImage.setFitWidth(30);
		Button backSearchMediaButton = new Button("Back", backSearchMediaImage);
		backSearchMediaButton.setLayoutX(160);
		backSearchMediaButton.setLayoutY(254);
		backSearchMediaButton.setPrefWidth(57);
		backSearchMediaButton.setPrefHeight(57);
		backSearchMediaButton.setFont(Font.font("Fenton", 12));
		backSearchMediaButton.setContentDisplay(ContentDisplay.TOP);
		backSearchMediaButton.setOnAction(e -> {
			searchMediaTextArea.setText("");
			window.setScene(mediaPage);
		});

		Pane searchMediaPane = new Pane();
		searchMediaPane.getChildren().addAll(searchMediaBackgroundImage, searchMediaImagelogoView, searchMediaLabel,
				searchMediaCodeLabel, searchMediaCodeText, searchMediaTextArea, searchMediaButtonImage,
				searchMediaButtonDone, backSearchMediaImage, backSearchMediaButton);
		searchMediaPage = new Scene(searchMediaPane, 736, 421);

//====================================== Print All Media Info Page =====================================
		Image printMediaBackground = new Image(getClass().getResourceAsStream("mainBack.png"));
		ImageView printMediaBackgroundImage = new ImageView(printMediaBackground);
		printMediaBackgroundImage.setFitHeight(421);
		printMediaBackgroundImage.setFitWidth(736);

		Image printMediaImagelogo = new Image(getClass().getResourceAsStream("printInfo.png"));
		ImageView printMediaImagelogoView = new ImageView(printMediaImagelogo);
		printMediaImagelogoView.setFitHeight(77);
		printMediaImagelogoView.setFitWidth(82);
		printMediaImagelogoView.setLayoutX(39);
		printMediaImagelogoView.setLayoutY(20);

		Label printMediaLabel = new Label("Print Media");
		printMediaLabel.setLayoutX(125);
		printMediaLabel.setLayoutY(40);
		printMediaLabel.setFont(Font.font("Lato Black", 20));
		printMediaLabel.setTextFill(Color.web("#ffffff"));

		Label printTitleMediaLabel = new Label("All Media Information :");
		printTitleMediaLabel.setLayoutX(335);
		printTitleMediaLabel.setLayoutY(85);
		printTitleMediaLabel.setFont(Font.font("Lato Black", 20));
		printTitleMediaLabel.setTextFill(Color.web("#ffffff"));

		// Print Area
		TextArea printMediaTextArea = new TextArea();
		printMediaTextArea.setLayoutX(162);
		printMediaTextArea.setLayoutY(122);
		printMediaTextArea.setPrefHeight(268);
		printMediaTextArea.setPrefWidth(552);
		printMediaTextArea.setEditable(false);

		// Print Button
		Image mediaPrintButton = new Image(getClass().getResourceAsStream("printInfo.png"));
		ImageView printMediaButtonImage = new ImageView(mediaPrintButton);
		printMediaButtonImage.setFitHeight(30);
		printMediaButtonImage.setFitWidth(30);
		Button printMediaButtonDone = new Button("Print", printMediaButtonImage);
		printMediaButtonDone.setLayoutX(49);
		printMediaButtonDone.setLayoutY(190);
		printMediaButtonDone.setPrefWidth(57);
		printMediaButtonDone.setPrefHeight(57);
		printMediaButtonDone.setFont(Font.font("Fenton", 12));
		printMediaButtonDone.setContentDisplay(ContentDisplay.TOP);
		printMediaButtonDone.setOnAction(e -> {
			printMediaTextArea.setText(manager.getAllMediaInfo());
		});

		// Back button
		Image backPrintMedia = new Image(getClass().getResourceAsStream("Back.png"));
		ImageView backPrintMediaImage = new ImageView(backPrintMedia);
		backPrintMediaImage.setFitHeight(30);
		backPrintMediaImage.setFitWidth(30);
		Button backPrintMediaButton = new Button("Back", backPrintMediaImage);
		backPrintMediaButton.setLayoutX(49);
		backPrintMediaButton.setLayoutY(263);
		backPrintMediaButton.setPrefWidth(57);
		backPrintMediaButton.setPrefHeight(57);
		backPrintMediaButton.setFont(Font.font("Fenton", 12));
		backPrintMediaButton.setContentDisplay(ContentDisplay.TOP);
		backPrintMediaButton.setOnAction(e -> {
			printMediaTextArea.setText("");
			window.setScene(mediaPage);
		});

		Pane printMediaPane = new Pane();
		printMediaPane.getChildren().addAll(printMediaBackgroundImage, printMediaImagelogoView, printMediaLabel,
				printTitleMediaLabel, printMediaTextArea, printMediaButtonImage, printMediaButtonDone,
				backPrintMediaImage, backPrintMediaButton);
		printMediaPage = new Scene(printMediaPane, 736, 421);

//========================================= Rent Page ==========================================					

		Image rentBackground = new Image(getClass().getResourceAsStream("mainBack.png"));
		ImageView rentBackgroundImage = new ImageView(rentBackground);
		rentBackgroundImage.setFitHeight(421);
		rentBackgroundImage.setFitWidth(736);

		Image rentImagelogo = new Image(getClass().getResourceAsStream("rentImage.png"));
		ImageView rentImagelogoView = new ImageView(rentImagelogo);
		rentImagelogoView.setFitHeight(90);
		rentImagelogoView.setFitWidth(110);
		rentImagelogoView.setLayoutX(270);
		rentImagelogoView.setLayoutY(36);

		Label rentLabel = new Label("Rent");
		rentLabel.setLayoutX(401);
		rentLabel.setLayoutY(70);
		rentLabel.setFont(Font.font("Lato Black", 22));
		rentLabel.setTextFill(Color.web("#ffffff"));

		Image addToCartImage = new Image(getClass().getResourceAsStream("addToCart.png"));
		ImageView addToCartImageView = new ImageView(addToCartImage);
		addToCartImageView.setFitHeight(52);
		addToCartImageView.setFitWidth(56);
		Button addToCartButton = new Button("Add To Cart", addToCartImageView);
		addToCartButton.setLayoutX(108);
		addToCartButton.setLayoutY(168);
		addToCartButton.setPrefWidth(148);
		addToCartButton.setPrefHeight(102);
		addToCartButton.setFont(Font.font("Fenton", 18));
		addToCartButton.setContentDisplay(ContentDisplay.TOP);
		addToCartButton.setOnAction(e -> window.setScene(addToCartPage));

		Image printCartImage = new Image(getClass().getResourceAsStream("printCart.png"));
		ImageView printCartImageView = new ImageView(printCartImage);
		printCartImageView.setFitHeight(52);
		printCartImageView.setFitWidth(56);
		Button printCartButton = new Button("Print Cart", printCartImageView);
		printCartButton.setLayoutX(294);
		printCartButton.setLayoutY(168);
		printCartButton.setPrefWidth(148);
		printCartButton.setPrefHeight(102);
		printCartButton.setFont(Font.font("Fenton", 18));
		printCartButton.setContentDisplay(ContentDisplay.TOP);
		printCartButton.setOnAction(e -> window.setScene(printCartRentPage));

		Image printRentImage = new Image(getClass().getResourceAsStream("printRent.png"));
		ImageView printRentImageView = new ImageView(printRentImage);
		printRentImageView.setFitHeight(52);
		printRentImageView.setFitWidth(56);
		Button printRentButton = new Button("Print Rent", printRentImageView);
		printRentButton.setLayoutX(481);
		printRentButton.setLayoutY(168);
		printRentButton.setPrefWidth(148);
		printRentButton.setPrefHeight(102);
		printRentButton.setFont(Font.font("Fenton", 18));
		printRentButton.setContentDisplay(ContentDisplay.TOP);
		printRentButton.setOnAction(e -> window.setScene(printRentedRentPage));

		Image returnMediaImage = new Image(getClass().getResourceAsStream("returnMedia.png"));
		ImageView returnMediaImageView = new ImageView(returnMediaImage);
		returnMediaImageView.setFitHeight(52);
		returnMediaImageView.setFitWidth(56);
		Button returnMediaButton = new Button("Return Media", returnMediaImageView);
		returnMediaButton.setLayoutX(108);
		returnMediaButton.setLayoutY(295);
		returnMediaButton.setPrefWidth(148);
		returnMediaButton.setPrefHeight(102);
		returnMediaButton.setFont(Font.font("Fenton", 18));
		returnMediaButton.setContentDisplay(ContentDisplay.TOP);
		returnMediaButton.setOnAction(e -> window.setScene(returnMediaPage));

		Image smileRentImage = new Image(getClass().getResourceAsStream("smile.png"));
		ImageView smileRentImageView = new ImageView(smileRentImage);
		smileRentImageView.setFitHeight(102);
		smileRentImageView.setFitWidth(120);
		smileRentImageView.setLayoutX(310);
		smileRentImageView.setLayoutY(295);

		Image backRentImage = new Image(getClass().getResourceAsStream("back.png"));
		ImageView backRentImageView = new ImageView(backRentImage);
		backRentImageView.setFitHeight(52);
		backRentImageView.setFitWidth(60);
		Button backRentButton = new Button("Return to main page", backRentImageView);
		backRentButton.setLayoutX(481);
		backRentButton.setLayoutY(295);
		backRentButton.setPrefWidth(148);
		backRentButton.setPrefHeight(102);
		backRentButton.setFont(Font.font("Fenton", 13));
		backRentButton.setContentDisplay(ContentDisplay.TOP);
		backRentButton.setOnAction(e -> window.setScene(mainPage));

		Pane rentPane = new Pane();
		rentPane.getChildren().addAll(rentBackgroundImage, rentImagelogoView, smileRentImageView, rentLabel,
				addToCartButton, printCartButton, printRentButton, returnMediaButton, backRentButton);
		rentPage = new Scene(rentPane, 736, 421);

//================================================ Add To Cart Page =====================================

		Image rentAddBackground = new Image(getClass().getResourceAsStream("mainBack.png"));
		ImageView rentAddBackgroundImage = new ImageView(rentAddBackground);
		rentAddBackgroundImage.setFitHeight(421);
		rentAddBackgroundImage.setFitWidth(736);

		Image rentAddImagelogo = new Image(getClass().getResourceAsStream("addToCart.png"));
		ImageView rentAddImagelogoView = new ImageView(rentAddImagelogo);
		rentAddImagelogoView.setFitHeight(65);
		rentAddImagelogoView.setFitWidth(85);
		rentAddImagelogoView.setLayoutX(279);
		rentAddImagelogoView.setLayoutY(20);

		Label rentAddLabel = new Label("Add To Cart");
		rentAddLabel.setLayoutX(362);
		rentAddLabel.setLayoutY(40);
		rentAddLabel.setFont(Font.font("Lato Black", 22));
		rentAddLabel.setTextFill(Color.web("#ffffff"));

		// label rent customerId
		Label customerIdAddRentLabel = new Label("Customer ID :");
		customerIdAddRentLabel.setLayoutX(75);
		customerIdAddRentLabel.setLayoutY(71);
		customerIdAddRentLabel.setFont(Font.font("Fenton", 15));
		customerIdAddRentLabel.setTextFill(Color.web("#000000"));

		TextField customerIdAddRentText = new TextField();
		customerIdAddRentText.setLayoutX(33);
		customerIdAddRentText.setLayoutY(99);
		customerIdAddRentText.setPrefHeight(25);
		customerIdAddRentText.setPrefWidth(188);

		// label mediaCode
		Label mediaCodeAddRentLabel = new Label("Media Code :");
		mediaCodeAddRentLabel.setLayoutX(557);
		mediaCodeAddRentLabel.setLayoutY(71);
		mediaCodeAddRentLabel.setFont(Font.font("Fenton", 15));
		mediaCodeAddRentLabel.setTextFill(Color.web("#000000"));

		TextField mediaCodeAddRentText = new TextField();
		mediaCodeAddRentText.setLayoutX(515);
		mediaCodeAddRentText.setLayoutY(99);
		mediaCodeAddRentText.setPrefHeight(25);
		mediaCodeAddRentText.setPrefWidth(188);

		// label rent rentedDate
		Label rentedDateAddRentLabel = new Label("Rented Date :");
		rentedDateAddRentLabel.setLayoutX(311);
		rentedDateAddRentLabel.setLayoutY(102);
		rentedDateAddRentLabel.setFont(Font.font("Fenton", 15));
		rentedDateAddRentLabel.setTextFill(Color.web("#000000"));

		DatePicker rentedDateAddRentText = new DatePicker();
		rentedDateAddRentText.setLayoutX(274);
		rentedDateAddRentText.setLayoutY(130);
		rentedDateAddRentText.setPrefHeight(25);
		rentedDateAddRentText.setPrefWidth(188);
		rentedDateAddRentText.setEditable(false);

		// label rent customerInfo
		Label customerInfoAddRentLabel = new Label("Customer Info :");
		customerInfoAddRentLabel.setLayoutX(75);
		customerInfoAddRentLabel.setLayoutY(142);
		customerInfoAddRentLabel.setFont(Font.font("Fenton", 15));
		customerInfoAddRentLabel.setTextFill(Color.web("#000000"));

		TextArea customerInfoTextArea = new TextArea();
		customerInfoTextArea.setLayoutX(33);
		customerInfoTextArea.setLayoutY(170);
		customerInfoTextArea.setPrefHeight(216);
		customerInfoTextArea.setPrefWidth(292);
		customerInfoTextArea.setEditable(false);

		// label rent mediaInfo
		Label mediaInfoAddRentLabel = new Label("Media Info :");
		mediaInfoAddRentLabel.setLayoutX(557);
		mediaInfoAddRentLabel.setLayoutY(142);
		mediaInfoAddRentLabel.setFont(Font.font("Fenton", 15));
		mediaInfoAddRentLabel.setTextFill(Color.web("#000000"));

		TextArea mediaInfoTextArea = new TextArea();
		mediaInfoTextArea.setLayoutX(411);
		mediaInfoTextArea.setLayoutY(170);
		mediaInfoTextArea.setPrefHeight(216);
		mediaInfoTextArea.setPrefWidth(292);
		mediaInfoTextArea.setEditable(false);

		// label found
		Label FoundLabel = new Label("");
		FoundLabel.setLayoutX(290);
		FoundLabel.setLayoutY(400);
		FoundLabel.setFont(Font.font("Fenton", 12));
		FoundLabel.setTextFill(Color.web("#000000"));

		// add To Cart Button
		Image addToCartButtonImage = new Image(getClass().getResourceAsStream("addToCart.png"));
		ImageView addToCartButtonImageView = new ImageView(addToCartButtonImage);
		addToCartButtonImageView.setFitHeight(30);
		addToCartButtonImageView.setFitWidth(30);
		Button addToCartButtonDone = new Button("Add to cart", addToCartButtonImageView);
		addToCartButtonDone.setLayoutX(340);
		addToCartButtonDone.setLayoutY(172);
		addToCartButtonDone.setPrefWidth(57);
		addToCartButtonDone.setPrefHeight(57);
		addToCartButtonDone.setFont(Font.font("Fenton", 8));
		addToCartButtonDone.setContentDisplay(ContentDisplay.TOP);
		addToCartButtonDone.setOnAction(e -> {
			boolean addcart = manager.addToCart(customerIdAddRentText.getText(), mediaCodeAddRentText.getText());
			if (addcart) {
				FoundLabel.setText("The Media Code has been added to cart successfully");
				Customer customerPrint = manager.searchByID(customerIdAddRentText.getText());
				Media mediaPrint = manager.searchByCode(mediaCodeAddRentText.getText());
				customerInfoTextArea.setText(customerPrint.toString() + "\n" + customerPrint.printIntrested() + "\n"
						+ customerPrint.printRented());
				mediaInfoTextArea.setText(mediaPrint.toString());
			} else {
				FoundLabel.setLayoutX(200);
				FoundLabel.setLayoutY(400);
				FoundLabel.setText("The Customer ID is not found or the Media Code is already part of the cart");
			}
			customerIdAddRentText.setText("");
			mediaCodeAddRentText.setText("");
			rentedDateAddRentText.setValue(null);
		});

		// Process button
		Image processRentButtonImage = new Image(getClass().getResourceAsStream("processRent.png"));
		ImageView processRentButtonImageView = new ImageView(processRentButtonImage);
		processRentButtonImageView.setFitHeight(30);
		processRentButtonImageView.setFitWidth(30);
		Button processRentButton = new Button("Process", processRentButtonImageView);
		processRentButton.setLayoutX(340);
		processRentButton.setLayoutY(250);
		processRentButton.setPrefWidth(57);
		processRentButton.setPrefHeight(57);
		processRentButton.setFont(Font.font("Fenton", 11));
		processRentButton.setContentDisplay(ContentDisplay.TOP);
		processRentButton.setOnAction(e -> {
			String process = manager.processRequests();
			customerInfoTextArea.setText("The cart has been processed successfully");
			mediaInfoTextArea.setText(process);
			FoundLabel.setText("");
		});

		// Back button
		Image backRentButtonImage = new Image(getClass().getResourceAsStream("Back.png"));
		ImageView backRentButtonImageView = new ImageView(backRentButtonImage);
		backRentButtonImageView.setFitHeight(30);
		backRentButtonImageView.setFitWidth(30);
		Button backAddRentButton = new Button("Back", backRentButtonImageView);
		backAddRentButton.setLayoutX(340);
		backAddRentButton.setLayoutY(327);
		backAddRentButton.setPrefWidth(57);
		backAddRentButton.setPrefHeight(57);
		backAddRentButton.setFont(Font.font("Fenton", 12));
		backAddRentButton.setContentDisplay(ContentDisplay.TOP);
		backAddRentButton.setOnAction(e -> {
			customerIdAddRentText.setText("");
			mediaCodeAddRentText.setText("");
			customerInfoTextArea.setText("");
			mediaInfoTextArea.setText("");
			FoundLabel.setText("");
			window.setScene(rentPage);
		});

		Pane rentAddToCartPane = new Pane();
		rentAddToCartPane.getChildren().addAll(rentAddBackgroundImage, rentAddImagelogoView, rentAddLabel,
				customerIdAddRentLabel, customerIdAddRentText, mediaCodeAddRentLabel, mediaCodeAddRentText,
				rentedDateAddRentLabel, FoundLabel, rentedDateAddRentText, customerInfoAddRentLabel,
				customerInfoTextArea, mediaInfoAddRentLabel, mediaInfoTextArea, addToCartButtonImageView,
				addToCartButtonDone, processRentButtonImageView, processRentButton, backRentButtonImageView,
				backAddRentButton);
		addToCartPage = new Scene(rentAddToCartPane, 736, 421);

//========================= Print Cart Rent Page ==========================================

		Image printCartBackground = new Image(getClass().getResourceAsStream("mainBack.png"));
		ImageView printCartBackgroundImage = new ImageView(printCartBackground);
		printCartBackgroundImage.setFitHeight(421);
		printCartBackgroundImage.setFitWidth(736);

		Image printCartImagelogo = new Image(getClass().getResourceAsStream("printCart.png"));
		ImageView printCartImagelogoView = new ImageView(printCartImagelogo);
		printCartImagelogoView.setFitHeight(77);
		printCartImagelogoView.setFitWidth(82);
		printCartImagelogoView.setLayoutX(240);
		printCartImagelogoView.setLayoutY(26);

		Label printCartLabel = new Label("Print Intrested Cart");
		printCartLabel.setLayoutX(334);
		printCartLabel.setLayoutY(52);
		printCartLabel.setFont(Font.font("Lato Black", 20));
		printCartLabel.setTextFill(Color.web("#ffffff"));

		// label Customer id
		Label printCartCustomerIdLabel = new Label("Customer ID :");
		printCartCustomerIdLabel.setLayoutX(100);
		printCartCustomerIdLabel.setLayoutY(181);
		printCartCustomerIdLabel.setFont(Font.font("Fenton", 15));
		printCartCustomerIdLabel.setTextFill(Color.web("#000000"));

		TextField printCartCustomerIdText = new TextField();
		printCartCustomerIdText.setLayoutX(40);
		printCartCustomerIdText.setLayoutY(208);
		printCartCustomerIdText.setPrefHeight(32);
		printCartCustomerIdText.setPrefWidth(217);

		TextArea printCartCustomerTextArea = new TextArea();
		printCartCustomerTextArea.setLayoutX(275);
		printCartCustomerTextArea.setLayoutY(119);
		printCartCustomerTextArea.setPrefHeight(263);
		printCartCustomerTextArea.setPrefWidth(430);
		printCartCustomerTextArea.setEditable(false);

		// Print Button
		Image printCartRentButton = new Image(getClass().getResourceAsStream("printCart.png"));
		ImageView printCartRentButtonImage = new ImageView(printCartRentButton);
		printCartRentButtonImage.setFitHeight(30);
		printCartRentButtonImage.setFitWidth(30);
		Button printCartRentButtonDone = new Button("Print", printCartRentButtonImage);
		printCartRentButtonDone.setLayoutX(72);
		printCartRentButtonDone.setLayoutY(253);
		printCartRentButtonDone.setPrefWidth(57);
		printCartRentButtonDone.setPrefHeight(57);
		printCartRentButtonDone.setFont(Font.font("Fenton", 12));
		printCartRentButtonDone.setContentDisplay(ContentDisplay.TOP);
		printCartRentButtonDone.setOnAction(e -> {
			Customer print = manager.searchByID(printCartCustomerIdText.getText());
			if (print == null) {
				printCartCustomerTextArea.setText("The Customer ID is not Found !");
			} else {
				printCartCustomerTextArea.setText(manager.printIntrested(printCartCustomerIdText.getText()));
			}
			printCartCustomerIdText.setText("");
		});

		// Back button
		Image backPrintCartRent = new Image(getClass().getResourceAsStream("Back.png"));
		ImageView backPrintCartRentImage = new ImageView(backPrintCartRent);
		backPrintCartRentImage.setFitHeight(30);
		backPrintCartRentImage.setFitWidth(30);
		Button backPrintCartRentButton = new Button("Back", backPrintCartRentImage);
		backPrintCartRentButton.setLayoutX(167);
		backPrintCartRentButton.setLayoutY(254);
		backPrintCartRentButton.setPrefWidth(57);
		backPrintCartRentButton.setPrefHeight(57);
		backPrintCartRentButton.setFont(Font.font("Fenton", 12));
		backPrintCartRentButton.setContentDisplay(ContentDisplay.TOP);
		backPrintCartRentButton.setOnAction(e -> {
			printCartCustomerTextArea.setText("");
			printCartCustomerIdText.setText("");
			window.setScene(rentPage);
		});

		Pane PrintCartRentPane = new Pane();
		PrintCartRentPane.getChildren().addAll(printCartBackgroundImage, printCartImagelogoView, printCartLabel,
				printCartCustomerIdLabel, printCartCustomerIdText, printCartCustomerTextArea, printCartRentButtonImage,
				printCartRentButtonDone, backPrintCartRentImage, backPrintCartRentButton);
		printCartRentPage = new Scene(PrintCartRentPane, 736, 421);

//========================= Print rentedCart Rent Page ==========================================

		Image printRentedBackground = new Image(getClass().getResourceAsStream("mainBack.png"));
		ImageView printRentedBackgroundImage = new ImageView(printRentedBackground);
		printRentedBackgroundImage.setFitHeight(421);
		printRentedBackgroundImage.setFitWidth(736);

		Image printRentedImagelogo = new Image(getClass().getResourceAsStream("printRent.png"));
		ImageView printRentedImagelogoView = new ImageView(printRentedImagelogo);
		printRentedImagelogoView.setFitHeight(77);
		printRentedImagelogoView.setFitWidth(82);
		printRentedImagelogoView.setLayoutX(240);
		printRentedImagelogoView.setLayoutY(26);

		Label printRentedLabel = new Label("Print Rented Cart");
		printRentedLabel.setLayoutX(334);
		printRentedLabel.setLayoutY(52);
		printRentedLabel.setFont(Font.font("Lato Black", 20));
		printRentedLabel.setTextFill(Color.web("#ffffff"));

		// label Customer id
		Label printRentedCustomerIdLabel = new Label("Customer ID :");
		printRentedCustomerIdLabel.setLayoutX(100);
		printRentedCustomerIdLabel.setLayoutY(181);
		printRentedCustomerIdLabel.setFont(Font.font("Fenton", 15));
		printRentedCustomerIdLabel.setTextFill(Color.web("#000000"));

		TextField printRentedCustomerIdText = new TextField();
		printRentedCustomerIdText.setLayoutX(40);
		printRentedCustomerIdText.setLayoutY(208);
		printRentedCustomerIdText.setPrefHeight(32);
		printRentedCustomerIdText.setPrefWidth(217);

		TextArea printRentedCustomerTextArea = new TextArea();
		printRentedCustomerTextArea.setLayoutX(275);
		printRentedCustomerTextArea.setLayoutY(119);
		printRentedCustomerTextArea.setPrefHeight(263);
		printRentedCustomerTextArea.setPrefWidth(430);

		// Print Button
		Image printRentedRentButton = new Image(getClass().getResourceAsStream("printRent.png"));
		ImageView printRentedRentButtonImage = new ImageView(printRentedRentButton);
		printRentedRentButtonImage.setFitHeight(30);
		printRentedRentButtonImage.setFitWidth(30);
		Button printRentedRentButtonDone = new Button("Print", printRentedRentButtonImage);
		printRentedRentButtonDone.setLayoutX(72);
		printRentedRentButtonDone.setLayoutY(253);
		printRentedRentButtonDone.setPrefWidth(57);
		printRentedRentButtonDone.setPrefHeight(57);
		printRentedRentButtonDone.setFont(Font.font("Fenton", 12));
		printRentedRentButtonDone.setContentDisplay(ContentDisplay.TOP);
		printRentedRentButtonDone.setOnAction(e -> {
			Customer print = manager.searchByID(printRentedCustomerIdText.getText());
			if (print == null) {
				printRentedCustomerTextArea.setText("The Customer ID is not Found !");
			} else {
				printRentedCustomerTextArea.setText(manager.printRented(printRentedCustomerIdText.getText()));
			}
			printRentedCustomerIdText.setText("");
		});

		// Back button
		Image backPrintRentedRent = new Image(getClass().getResourceAsStream("Back.png"));
		ImageView backPrintRentedRentImage = new ImageView(backPrintRentedRent);
		backPrintRentedRentImage.setFitHeight(30);
		backPrintRentedRentImage.setFitWidth(30);
		Button backPrintRentedRentButton = new Button("Back", backPrintRentedRentImage);
		backPrintRentedRentButton.setLayoutX(167);
		backPrintRentedRentButton.setLayoutY(254);
		backPrintRentedRentButton.setPrefWidth(57);
		backPrintRentedRentButton.setPrefHeight(57);
		backPrintRentedRentButton.setFont(Font.font("Fenton", 12));
		backPrintRentedRentButton.setContentDisplay(ContentDisplay.TOP);
		backPrintRentedRentButton.setOnAction(e -> {
			printRentedCustomerTextArea.setText("");
			printRentedCustomerIdText.setText("");
			window.setScene(rentPage);
		});

		Pane PrintRentedRentPane = new Pane();
		PrintRentedRentPane.getChildren().addAll(printRentedBackgroundImage, printRentedImagelogoView, printRentedLabel,
				printRentedCustomerIdLabel, printRentedCustomerIdText, printRentedCustomerTextArea,
				printRentedRentButtonImage, printRentedRentButtonDone, backPrintRentedRentImage,
				backPrintRentedRentButton);
		printRentedRentPage = new Scene(PrintRentedRentPane, 736, 421);

//========================= Return Rent Page ==========================================

		Image returnMediaBackground = new Image(getClass().getResourceAsStream("mainBack.png"));
		ImageView returnMediaBackgroundImage = new ImageView(returnMediaBackground);
		returnMediaBackgroundImage.setFitHeight(421);
		returnMediaBackgroundImage.setFitWidth(736);

		Image returnMediaImagelogo = new Image(getClass().getResourceAsStream("returnMedia.png"));
		ImageView returnMediaImagelogoView = new ImageView(returnMediaImagelogo);
		returnMediaImagelogoView.setFitHeight(77);
		returnMediaImagelogoView.setFitWidth(82);
		returnMediaImagelogoView.setLayoutX(240);
		returnMediaImagelogoView.setLayoutY(26);

		Label returnMediaLabel = new Label("Return Media");
		returnMediaLabel.setLayoutX(334);
		returnMediaLabel.setLayoutY(52);
		returnMediaLabel.setFont(Font.font("Lato Black", 20));
		returnMediaLabel.setTextFill(Color.web("#ffffff"));

		// label Customer id
		Label returnMediaCustomerIdLabel = new Label("Customer ID :");
		returnMediaCustomerIdLabel.setLayoutX(100);
		returnMediaCustomerIdLabel.setLayoutY(150);
		returnMediaCustomerIdLabel.setFont(Font.font("Fenton", 15));
		returnMediaCustomerIdLabel.setTextFill(Color.web("#000000"));

		TextField returnMediaCustomerIdText = new TextField();
		returnMediaCustomerIdText.setLayoutX(40);
		returnMediaCustomerIdText.setLayoutY(170);
		returnMediaCustomerIdText.setPrefHeight(32);
		returnMediaCustomerIdText.setPrefWidth(217);

		// label media code
		Label returnMediaCodeLabel = new Label("Media Code :");
		returnMediaCodeLabel.setLayoutX(100);
		returnMediaCodeLabel.setLayoutY(210);
		returnMediaCodeLabel.setFont(Font.font("Fenton", 15));
		returnMediaCodeLabel.setTextFill(Color.web("#000000"));

		TextField returnMediaCodeText = new TextField();
		returnMediaCodeText.setLayoutX(40);
		returnMediaCodeText.setLayoutY(230);
		returnMediaCodeText.setPrefHeight(32);
		returnMediaCodeText.setPrefWidth(217);

		TextArea returnMediaCustomerTextArea = new TextArea();
		returnMediaCustomerTextArea.setLayoutX(275);
		returnMediaCustomerTextArea.setLayoutY(119);
		returnMediaCustomerTextArea.setPrefHeight(263);
		returnMediaCustomerTextArea.setPrefWidth(430);
		returnMediaCustomerTextArea.setText("");

		// Return Button
		Image returnMediaRentButton = new Image(getClass().getResourceAsStream("returnMedia.png"));
		ImageView returnMediaRentButtonImage = new ImageView(returnMediaRentButton);
		returnMediaRentButtonImage.setFitHeight(30);
		returnMediaRentButtonImage.setFitWidth(30);
		Button returnMediaRentButtonDone = new Button("Return", returnMediaRentButtonImage);
		returnMediaRentButtonDone.setLayoutX(72);
		returnMediaRentButtonDone.setLayoutY(275);
		returnMediaRentButtonDone.setPrefWidth(57);
		returnMediaRentButtonDone.setPrefHeight(57);
		returnMediaRentButtonDone.setFont(Font.font("Fenton", 12));
		returnMediaRentButtonDone.setContentDisplay(ContentDisplay.TOP);
		returnMediaRentButtonDone.setOnAction(e -> {
			boolean returnM = manager.returnMedia(returnMediaCustomerIdText.getText(), returnMediaCodeText.getText());
			if (returnM == true) {
				returnMediaCustomerTextArea.setText("The Media Code has been returned successfully");
			} else {
				returnMediaCustomerTextArea.setText("The Customer ID is not found or the Media Code is not rented");
			}
			returnMediaCustomerIdText.setText("");
			returnMediaCodeText.setText("");
		});

		// Back button
		Image backReturnMediaRent = new Image(getClass().getResourceAsStream("Back.png"));
		ImageView backReturnMediaRentImage = new ImageView(backReturnMediaRent);
		backReturnMediaRentImage.setFitHeight(30);
		backReturnMediaRentImage.setFitWidth(30);
		Button backReturnMediaRentButton = new Button("Back", backReturnMediaRentImage);
		backReturnMediaRentButton.setLayoutX(167);
		backReturnMediaRentButton.setLayoutY(275);
		backReturnMediaRentButton.setPrefWidth(57);
		backReturnMediaRentButton.setPrefHeight(57);
		backReturnMediaRentButton.setFont(Font.font("Fenton", 12));
		backReturnMediaRentButton.setContentDisplay(ContentDisplay.TOP);
		backReturnMediaRentButton.setOnAction(e -> {
			returnMediaCustomerIdText.setText("");
			returnMediaCodeText.setText("");
			returnMediaCustomerTextArea.setText("");
			window.setScene(rentPage);
		});

		Pane PrintReturnMediaRentPane = new Pane();
		PrintReturnMediaRentPane.getChildren().addAll(returnMediaBackgroundImage, returnMediaImagelogoView,
				returnMediaLabel, returnMediaCodeLabel, returnMediaCodeText, returnMediaCustomerTextArea,
				returnMediaRentButtonImage, returnMediaRentButtonDone, backReturnMediaRentImage,
				backReturnMediaRentButton, returnMediaCustomerIdLabel, returnMediaCustomerIdText);
		returnMediaPage = new Scene(PrintReturnMediaRentPane, 736, 421);

		window.setScene(mainPage);
		window.setTitle("Media Rental System");
		// window.setMaximized(true);
		window.show();

	}

	public void addMovieMedia(Stage primaryStage) {
		// ========================================= Add Movie Media Page
		// ============================

		window = primaryStage;

		Image addMovieMediaBackground = new Image(getClass().getResourceAsStream("mainBack.png"));
		ImageView addMovieMediaBackgroundImage = new ImageView(addMovieMediaBackground);
		addMovieMediaBackgroundImage.setFitHeight(421);
		addMovieMediaBackgroundImage.setFitWidth(736);

		Image addMovieMediaImagelogo = new Image(getClass().getResourceAsStream("addMedia.png"));
		ImageView addMovieMediaImagelogoView = new ImageView(addMovieMediaImagelogo);
		addMovieMediaImagelogoView.setFitHeight(77);
		addMovieMediaImagelogoView.setFitWidth(82);
		addMovieMediaImagelogoView.setLayoutX(39);
		addMovieMediaImagelogoView.setLayoutY(20);

		Label addMovieMediaLabel = new Label("Add Movie");
		addMovieMediaLabel.setLayoutX(122);
		addMovieMediaLabel.setLayoutY(40);
		addMovieMediaLabel.setFont(Font.font("Lato Black", 20));
		addMovieMediaLabel.setTextFill(Color.web("#ffffff"));

		ComboBox<String> addMovieMediaComboBox = new ComboBox<>();
		addMovieMediaComboBox.getItems().addAll("Movie", "Music", "Game");
		addMovieMediaComboBox.setLayoutX(334);
		addMovieMediaComboBox.setLayoutY(121);
		addMovieMediaComboBox.setPrefWidth(150);
		addMovieMediaComboBox.setPromptText("Please Select Here");

		addMovieMediaComboBox.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (addMovieMediaComboBox.getValue().equals("Movie")) {
					addMovieMedia(primaryStage);
				} else if (addMovieMediaComboBox.getValue().equals("Music")) {
					addMusicMedia(primaryStage);
				} else if (addMovieMediaComboBox.getValue().equals("Game")) {
					addGameMedia(primaryStage);
				}
			}
		});

		Label movieMediaTypeLabel = new Label("Media Type :");
		movieMediaTypeLabel.setLayoutX(234);
		movieMediaTypeLabel.setLayoutY(124);
		movieMediaTypeLabel.setFont(Font.font("Fenton", 16));
		movieMediaTypeLabel.setTextFill(Color.web("#ffffff"));

		// Code label
		Label movieCodeLabel = new Label("Code :");
		movieCodeLabel.setLayoutX(196);
		movieCodeLabel.setLayoutY(201);
		movieCodeLabel.setFont(Font.font("Fenton", 15));
		movieCodeLabel.setTextFill(Color.web("#000000"));

		TextField movieCodeText = new TextField();
		movieCodeText.setLayoutX(112);
		movieCodeText.setLayoutY(228);
		movieCodeText.setPrefHeight(32);
		movieCodeText.setPrefWidth(217);

		// MediaTitle label
		Label movieMediaTitleLabel = new Label("Media Title :");
		movieMediaTitleLabel.setLayoutX(454);
		movieMediaTitleLabel.setLayoutY(201);
		movieMediaTitleLabel.setFont(Font.font("Fenton", 15));
		movieMediaTitleLabel.setTextFill(Color.web("#000000"));

		TextField movieMediaTitleText = new TextField();
		movieMediaTitleText.setLayoutX(394);
		movieMediaTitleText.setLayoutY(228);
		movieMediaTitleText.setPrefHeight(32);
		movieMediaTitleText.setPrefWidth(217);

		// NumberOfCopies label
		Label movieNumberOfCopiesLabel = new Label("Number Of Copies :");
		movieNumberOfCopiesLabel.setLayoutX(147);
		movieNumberOfCopiesLabel.setLayoutY(304);
		movieNumberOfCopiesLabel.setFont(Font.font("Fenton", 15));
		movieNumberOfCopiesLabel.setTextFill(Color.web("#000000"));

		TextField movieNumberOfCopiesText = new TextField();
		movieNumberOfCopiesText.setLayoutX(112);
		movieNumberOfCopiesText.setLayoutY(328);
		movieNumberOfCopiesText.setPrefHeight(32);
		movieNumberOfCopiesText.setPrefWidth(217);

		// label Rate
		Label ratingMovieLabel = new Label("Rate :");
		ratingMovieLabel.setLayoutX(483);
		ratingMovieLabel.setLayoutY(304);
		ratingMovieLabel.setFont(Font.font("Fenton", 15));
		ratingMovieLabel.setTextFill(Color.web("#000000"));

		RadioButton FourKRadio = new RadioButton("4K");
		FourKRadio.setLayoutX(442);
		FourKRadio.setLayoutY(336);
		FourKRadio.setFont(Font.font("Fenton", 12));

		RadioButton HDRadio = new RadioButton("HD");
		HDRadio.setLayoutX(485);
		HDRadio.setLayoutY(336);
		HDRadio.setFont(Font.font("Fenton", 12));

		RadioButton SDRadio = new RadioButton("SD");
		SDRadio.setLayoutX(528);
		SDRadio.setLayoutY(336);
		SDRadio.setFont(Font.font("Fenton", 12));

		ToggleGroup planGroup = new ToggleGroup();
		FourKRadio.setToggleGroup(planGroup);
		HDRadio.setToggleGroup(planGroup);
		SDRadio.setToggleGroup(planGroup);

		// deafult Plan selected is limited
		FourKRadio.setSelected(true);

		// label Code not found
		Label addMovieFoundLabel = new Label("");
		addMovieFoundLabel.setLayoutX(290);
		addMovieFoundLabel.setLayoutY(280);
		addMovieFoundLabel.setFont(Font.font("Fenton", 12));
		addMovieFoundLabel.setTextFill(Color.web("#000000"));

		// back add movie
		Image backMovieMediaImage = new Image(getClass().getResourceAsStream("back.png"));
		ImageView backMovieMediaImageView = new ImageView(backMovieMediaImage);
		backMovieMediaImageView.setFitHeight(30);
		backMovieMediaImageView.setFitWidth(30);
		Button backMovieMediaButton = new Button("Back", backMovieMediaImageView);
		backMovieMediaButton.setLayoutX(654);
		backMovieMediaButton.setLayoutY(344);
		backMovieMediaButton.setPrefWidth(57);
		backMovieMediaButton.setPrefHeight(50);
		backMovieMediaButton.setFont(Font.font("Fenton", 13));
		backMovieMediaButton.setContentDisplay(ContentDisplay.TOP);
		backMovieMediaButton.setOnAction(e -> {
			addMovieFoundLabel.setText("");
			movieCodeText.setText("");
			movieMediaTitleText.setText("");
			movieNumberOfCopiesText.setText("");
			FourKRadio.setSelected(false);
			HDRadio.setSelected(false);
			SDRadio.setSelected(false);
			window.setScene(mediaPage);
		});

		// AddDone add movie
		Image addDoneMovieMediaImage = new Image(getClass().getResourceAsStream("add.png"));
		ImageView addDoneMovieMediaImageView = new ImageView(addDoneMovieMediaImage);
		addDoneMovieMediaImageView.setFitHeight(30);
		addDoneMovieMediaImageView.setFitWidth(30);
		Button addDoneMovieMediaButton = new Button("Add", addDoneMovieMediaImageView);
		addDoneMovieMediaButton.setLayoutX(21);
		addDoneMovieMediaButton.setLayoutY(344);
		addDoneMovieMediaButton.setPrefWidth(57);
		addDoneMovieMediaButton.setPrefHeight(57);
		addDoneMovieMediaButton.setFont(Font.font("Fenton", 13));
		addDoneMovieMediaButton.setContentDisplay(ContentDisplay.TOP);
		addDoneMovieMediaButton.setOnAction(e -> {
			Media addMm = manager.searchByCode(movieCodeText.getText());
			if (addMm == null) {
				String Mplan = "";
				if (FourKRadio.isSelected()) {
					Mplan = "4K";
				} else if (HDRadio.isSelected()) {
					Mplan = "HD";
				} else if (SDRadio.isSelected()) {
					Mplan = "SD";
				}
				manager.addMovie(movieCodeText.getText(), movieMediaTitleText.getText(),
						Integer.parseInt(movieNumberOfCopiesText.getText()), Mplan);
				addMovieFoundLabel.setText("The Movie has been added succsessfully");
				movieCodeText.setText("");
				movieMediaTitleText.setText("");
				movieNumberOfCopiesText.setText("");
				FourKRadio.setSelected(false);
				HDRadio.setSelected(false);
				SDRadio.setSelected(false);
			} else {
				addMovieFoundLabel.setText("The media code is already exist");
				movieCodeText.setText("");
			}
		});

		Pane addMovieMediaPane = new Pane();
		addMovieMediaPane.getChildren().addAll(addMovieMediaBackgroundImage, addMovieMediaImagelogoView,
				addMovieMediaLabel, movieMediaTypeLabel, addMovieMediaComboBox, movieCodeLabel, movieCodeText,
				movieMediaTitleLabel, movieMediaTitleText, movieNumberOfCopiesLabel, movieNumberOfCopiesText,
				ratingMovieLabel, addMovieFoundLabel, FourKRadio, HDRadio, SDRadio, backMovieMediaImageView,
				backMovieMediaButton, addDoneMovieMediaImageView, addDoneMovieMediaButton);
		addMovieMedia = new Scene(addMovieMediaPane, 736, 421);

		window.setScene(addMovieMedia);
		// window.setMaximized(true);
		window.show();

	}

	public void addMusicMedia(Stage primaryStage) {
		// ========================================= Add Music Media Page
		// ============================

		window = primaryStage;

		Image addMusicMediaBackground = new Image(getClass().getResourceAsStream("mainBack.png"));
		ImageView addMusicMediaBackgroundImage = new ImageView(addMusicMediaBackground);
		addMusicMediaBackgroundImage.setFitHeight(421);
		addMusicMediaBackgroundImage.setFitWidth(736);

		Image addMusicMediaImagelogo = new Image(getClass().getResourceAsStream("addMedia.png"));
		ImageView addMusicMediaImagelogoView = new ImageView(addMusicMediaImagelogo);
		addMusicMediaImagelogoView.setFitHeight(77);
		addMusicMediaImagelogoView.setFitWidth(82);
		addMusicMediaImagelogoView.setLayoutX(39);
		addMusicMediaImagelogoView.setLayoutY(20);

		Label addMusicMediaLabel = new Label("Add Music");
		addMusicMediaLabel.setLayoutX(122);
		addMusicMediaLabel.setLayoutY(40);
		addMusicMediaLabel.setFont(Font.font("Lato Black", 20));
		addMusicMediaLabel.setTextFill(Color.web("#ffffff"));

		ComboBox<String> addMusicMediaComboBox = new ComboBox<>();
		addMusicMediaComboBox.getItems().addAll("Movie", "Music", "Game");
		addMusicMediaComboBox.setLayoutX(334);
		addMusicMediaComboBox.setLayoutY(121);
		addMusicMediaComboBox.setPrefWidth(150);
		addMusicMediaComboBox.setPromptText("Please Select Here");

		addMusicMediaComboBox.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (addMusicMediaComboBox.getValue().equals("Movie")) {
					addMovieMedia(primaryStage);
				} else if (addMusicMediaComboBox.getValue().equals("Music")) {
					addMusicMedia(primaryStage);
				} else if (addMusicMediaComboBox.getValue().equals("Game")) {
					addGameMedia(primaryStage);
				}
			}
		});

		Label musicMediaTypeLabel = new Label("Media Type :");
		musicMediaTypeLabel.setLayoutX(234);
		musicMediaTypeLabel.setLayoutY(124);
		musicMediaTypeLabel.setFont(Font.font("Fenton", 16));
		musicMediaTypeLabel.setTextFill(Color.web("#ffffff"));

		// Code label
		Label musicCodeLabel = new Label("Code :");
		musicCodeLabel.setLayoutX(200);
		musicCodeLabel.setLayoutY(185);
		musicCodeLabel.setFont(Font.font("Fenton", 15));
		musicCodeLabel.setTextFill(Color.web("#000000"));

		TextField musicCodeText = new TextField();
		musicCodeText.setLayoutX(116);
		musicCodeText.setLayoutY(212);
		musicCodeText.setPrefHeight(32);
		musicCodeText.setPrefWidth(217);

		// MediaTitle label
		Label musicMediaTitleLabel = new Label("Media Title :");
		musicMediaTitleLabel.setLayoutX(458);
		musicMediaTitleLabel.setLayoutY(185);
		musicMediaTitleLabel.setFont(Font.font("Fenton", 15));
		musicMediaTitleLabel.setTextFill(Color.web("#000000"));

		TextField musicMediaTitleText = new TextField();
		musicMediaTitleText.setLayoutX(398);
		musicMediaTitleText.setLayoutY(212);
		musicMediaTitleText.setPrefHeight(32);
		musicMediaTitleText.setPrefWidth(217);

		// NumberOfCopies label
		Label musicNumberOfCopiesLabel = new Label("Number Of Copies :");
		musicNumberOfCopiesLabel.setLayoutX(151);
		musicNumberOfCopiesLabel.setLayoutY(279);
		musicNumberOfCopiesLabel.setFont(Font.font("Fenton", 15));
		musicNumberOfCopiesLabel.setTextFill(Color.web("#000000"));

		TextField musicNumberOfCopiesText = new TextField();
		musicNumberOfCopiesText.setLayoutX(116);
		musicNumberOfCopiesText.setLayoutY(303);
		musicNumberOfCopiesText.setPrefHeight(32);
		musicNumberOfCopiesText.setPrefWidth(217);

		// Song label
		Label musicSongLabel = new Label("Song :");
		musicSongLabel.setLayoutX(483);
		musicSongLabel.setLayoutY(279);
		musicSongLabel.setFont(Font.font("Fenton", 15));
		musicSongLabel.setTextFill(Color.web("#000000"));

		TextField musicSongText = new TextField();
		musicSongText.setLayoutX(399);
		musicSongText.setLayoutY(303);
		musicSongText.setPrefHeight(32);
		musicSongText.setPrefWidth(217);

		// Artist label
		Label musicArtistLabel = new Label("Artist :");
		musicArtistLabel.setLayoutX(338);
		musicArtistLabel.setLayoutY(344);
		musicArtistLabel.setFont(Font.font("Fenton", 15));
		musicArtistLabel.setTextFill(Color.web("#000000"));

		TextField musicArtistText = new TextField();
		musicArtistText.setLayoutX(260);
		musicArtistText.setLayoutY(371);
		musicArtistText.setPrefHeight(32);
		musicArtistText.setPrefWidth(217);

		// label Code not found
		Label addMusicFoundLabel = new Label("");
		addMusicFoundLabel.setLayoutX(290);
		addMusicFoundLabel.setLayoutY(260);
		addMusicFoundLabel.setFont(Font.font("Fenton", 12));
		addMusicFoundLabel.setTextFill(Color.web("#000000"));

		// back add music
		Image backMusicMediaImage = new Image(getClass().getResourceAsStream("back.png"));
		ImageView backMusicMediaImageView = new ImageView(backMusicMediaImage);
		backMusicMediaImageView.setFitHeight(30);
		backMusicMediaImageView.setFitWidth(30);
		Button backMusicMediaButton = new Button("Back", backMusicMediaImageView);
		backMusicMediaButton.setLayoutX(654);
		backMusicMediaButton.setLayoutY(344);
		backMusicMediaButton.setPrefWidth(57);
		backMusicMediaButton.setPrefHeight(50);
		backMusicMediaButton.setFont(Font.font("Fenton", 13));
		backMusicMediaButton.setContentDisplay(ContentDisplay.TOP);
		backMusicMediaButton.setOnAction(e -> {
			addMusicFoundLabel.setText("");
			musicCodeText.setText("");
			musicMediaTitleText.setText("");
			musicNumberOfCopiesText.setText("");
			musicSongText.setText("");
			musicArtistText.setText("");
			window.setScene(mediaPage);

		});

		// AddDone add music
		Image addDoneMusicMediaImage = new Image(getClass().getResourceAsStream("add.png"));
		ImageView addDoneMusicMediaImageView = new ImageView(addDoneMusicMediaImage);
		addDoneMusicMediaImageView.setFitHeight(30);
		addDoneMusicMediaImageView.setFitWidth(30);
		Button addDoneMusicMediaButton = new Button("Add", addDoneMusicMediaImageView);
		addDoneMusicMediaButton.setLayoutX(21);
		addDoneMusicMediaButton.setLayoutY(344);
		addDoneMusicMediaButton.setPrefWidth(57);
		addDoneMusicMediaButton.setPrefHeight(57);
		addDoneMusicMediaButton.setFont(Font.font("Fenton", 13));
		addDoneMusicMediaButton.setContentDisplay(ContentDisplay.TOP);
		addDoneMusicMediaButton.setOnAction(e -> {
			Media addMa = manager.searchByCode(musicCodeText.getText());

			if (addMa == null) {
				manager.addAlbum(musicCodeText.getText(), musicMediaTitleText.getText(),
						Integer.parseInt(musicNumberOfCopiesText.getText()), musicSongText.getText(),
						musicArtistText.getText());
				addMusicFoundLabel.setText("The Music has been added succsessfully");
				musicCodeText.setText("");
				musicMediaTitleText.setText("");
				musicNumberOfCopiesText.setText("");
				musicSongText.setText("");
				musicArtistText.setText("");
			} else {
				addMusicFoundLabel.setText("The media code is already exist");
				musicCodeText.setText("");
			}
		});

		Pane addMusicMediaPane = new Pane();
		addMusicMediaPane.getChildren().addAll(addMusicMediaBackgroundImage, addMusicMediaImagelogoView,
				addMusicMediaLabel, musicMediaTypeLabel, addMusicMediaComboBox, musicCodeLabel, musicCodeText,
				musicMediaTitleLabel, musicMediaTitleText, musicNumberOfCopiesLabel, musicNumberOfCopiesText,
				musicSongLabel, musicSongText, addMusicFoundLabel, musicArtistLabel, musicArtistText,
				backMusicMediaImageView, backMusicMediaButton, addDoneMusicMediaImageView, addDoneMusicMediaButton);
		addMusicMedia = new Scene(addMusicMediaPane, 736, 421);

		window.setScene(addMusicMedia);
		// window.setMaximized(true);
		window.show();

	}

	public void addGameMedia(Stage primaryStage) {
		// ========================================= Add Game Media Page
		// ============================

		window = primaryStage;

		Image addGameMediaBackground = new Image(getClass().getResourceAsStream("mainBack.png"));
		ImageView addGameMediaBackgroundImage = new ImageView(addGameMediaBackground);
		addGameMediaBackgroundImage.setFitHeight(421);
		addGameMediaBackgroundImage.setFitWidth(736);

		Image addGameMediaImagelogo = new Image(getClass().getResourceAsStream("addMedia.png"));
		ImageView addGameMediaImagelogoView = new ImageView(addGameMediaImagelogo);
		addGameMediaImagelogoView.setFitHeight(77);
		addGameMediaImagelogoView.setFitWidth(82);
		addGameMediaImagelogoView.setLayoutX(39);
		addGameMediaImagelogoView.setLayoutY(20);

		Label addGameMediaLabel = new Label("Add Game");
		addGameMediaLabel.setLayoutX(122);
		addGameMediaLabel.setLayoutY(40);
		addGameMediaLabel.setFont(Font.font("Lato Black", 20));
		addGameMediaLabel.setTextFill(Color.web("#ffffff"));

		ComboBox<String> addGameMediaComboBox = new ComboBox<>();
		addGameMediaComboBox.getItems().addAll("Movie", "Music", "Game");
		addGameMediaComboBox.setLayoutX(334);
		addGameMediaComboBox.setLayoutY(121);
		addGameMediaComboBox.setPrefWidth(150);
		addGameMediaComboBox.setPromptText("Please Select Here");

		addGameMediaComboBox.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (addGameMediaComboBox.getValue().equals("Movie")) {
					addMovieMedia(primaryStage);
				} else if (addGameMediaComboBox.getValue().equals("Music")) {
					addMusicMedia(primaryStage);
				} else if (addGameMediaComboBox.getValue().equals("Game")) {
					addGameMedia(primaryStage);
				}
			}
		});

		Label gameMediaTypeLabel = new Label("Media Type :");
		gameMediaTypeLabel.setLayoutX(234);
		gameMediaTypeLabel.setLayoutY(124);
		gameMediaTypeLabel.setFont(Font.font("Fenton", 16));
		gameMediaTypeLabel.setTextFill(Color.web("#ffffff"));

		// Code label
		Label gameCodeLabel = new Label("Code :");
		gameCodeLabel.setLayoutX(196);
		gameCodeLabel.setLayoutY(201);
		gameCodeLabel.setFont(Font.font("Fenton", 15));
		gameCodeLabel.setTextFill(Color.web("#000000"));

		TextField gameCodeText = new TextField();
		gameCodeText.setLayoutX(112);
		gameCodeText.setLayoutY(228);
		gameCodeText.setPrefHeight(32);
		gameCodeText.setPrefWidth(217);

		// MediaTitle label
		Label gameMediaTitleLabel = new Label("Media Title :");
		gameMediaTitleLabel.setLayoutX(454);
		gameMediaTitleLabel.setLayoutY(201);
		gameMediaTitleLabel.setFont(Font.font("Fenton", 15));
		gameMediaTitleLabel.setTextFill(Color.web("#000000"));

		TextField gameMediaTitleText = new TextField();
		gameMediaTitleText.setLayoutX(394);
		gameMediaTitleText.setLayoutY(228);
		gameMediaTitleText.setPrefHeight(32);
		gameMediaTitleText.setPrefWidth(217);

		// NumberOfCopies label
		Label gameNumberOfCopiesLabel = new Label("Number Of Copies :");
		gameNumberOfCopiesLabel.setLayoutX(147);
		gameNumberOfCopiesLabel.setLayoutY(304);
		gameNumberOfCopiesLabel.setFont(Font.font("Fenton", 15));
		gameNumberOfCopiesLabel.setTextFill(Color.web("#000000"));

		TextField gameNumberOfCopiesText = new TextField();
		gameNumberOfCopiesText.setLayoutX(112);
		gameNumberOfCopiesText.setLayoutY(328);
		gameNumberOfCopiesText.setPrefHeight(32);
		gameNumberOfCopiesText.setPrefWidth(217);

		// Weight label
		Label gameWeightLabel = new Label("Weight :");
		gameWeightLabel.setLayoutX(479);
		gameWeightLabel.setLayoutY(304);
		gameWeightLabel.setFont(Font.font("Fenton", 15));
		gameWeightLabel.setTextFill(Color.web("#000000"));

		TextField gameWeightText = new TextField();
		gameWeightText.setLayoutX(395);
		gameWeightText.setLayoutY(328);
		gameWeightText.setPrefHeight(32);
		gameWeightText.setPrefWidth(217);

		// label Code not found
		Label addGameFoundLabel = new Label("");
		addGameFoundLabel.setLayoutX(290);
		addGameFoundLabel.setLayoutY(280);
		addGameFoundLabel.setFont(Font.font("Fenton", 12));
		addGameFoundLabel.setTextFill(Color.web("#000000"));

		// back add game
		Image backGameMediaImage = new Image(getClass().getResourceAsStream("back.png"));
		ImageView backGameMediaImageView = new ImageView(backGameMediaImage);
		backGameMediaImageView.setFitHeight(30);
		backGameMediaImageView.setFitWidth(30);
		Button backGameMediaButton = new Button("Back", backGameMediaImageView);
		backGameMediaButton.setLayoutX(654);
		backGameMediaButton.setLayoutY(344);
		backGameMediaButton.setPrefWidth(57);
		backGameMediaButton.setPrefHeight(50);
		backGameMediaButton.setFont(Font.font("Fenton", 13));
		backGameMediaButton.setContentDisplay(ContentDisplay.TOP);
		backGameMediaButton.setOnAction(e -> {
			addGameFoundLabel.setText("");
			gameCodeText.setText("");
			gameMediaTitleText.setText("");
			gameNumberOfCopiesText.setText("");
			gameWeightText.setText("");
			window.setScene(mediaPage);
		});

		// AddDone add game
		Image addDoneGameMediaImage = new Image(getClass().getResourceAsStream("add.png"));
		ImageView addDoneGameMediaImageView = new ImageView(addDoneGameMediaImage);
		addDoneGameMediaImageView.setFitHeight(30);
		addDoneGameMediaImageView.setFitWidth(30);
		Button addDoneGameMediaButton = new Button("Add", addDoneGameMediaImageView);
		addDoneGameMediaButton.setLayoutX(21);
		addDoneGameMediaButton.setLayoutY(344);
		addDoneGameMediaButton.setPrefWidth(57);
		addDoneGameMediaButton.setPrefHeight(50);
		addDoneGameMediaButton.setFont(Font.font("Fenton", 13));
		addDoneGameMediaButton.setContentDisplay(ContentDisplay.TOP);
		addDoneGameMediaButton.setOnAction(e -> {
			Media addMg = manager.searchByCode(gameCodeText.getText());

			if (addMg == null) {
				manager.addGame(gameCodeText.getText(), gameMediaTitleText.getText(),
						Integer.parseInt(gameNumberOfCopiesText.getText()),
						Double.parseDouble(gameWeightText.getText()));
				addGameFoundLabel.setText("The Game has been added succsessfully");
				gameCodeText.setText("");
				gameMediaTitleText.setText("");
				gameNumberOfCopiesText.setText("");
				gameWeightText.setText("");
			} else {
				addGameFoundLabel.setText("The media code is already exist");
				gameCodeText.setText("");

			}
		});

		Pane addGameMediaPane = new Pane();
		addGameMediaPane.getChildren().addAll(addGameMediaBackgroundImage, addGameMediaImagelogoView, addGameMediaLabel,
				gameMediaTypeLabel, addGameMediaComboBox, gameCodeLabel, gameCodeText, gameMediaTitleLabel,
				gameMediaTitleText, gameNumberOfCopiesLabel, gameNumberOfCopiesText, gameWeightLabel, gameWeightText,
				backGameMediaImageView, addGameFoundLabel, backGameMediaButton, addDoneGameMediaImageView,
				addDoneGameMediaButton);
		addGameMedia = new Scene(addGameMediaPane, 736, 421);

		window.setScene(addGameMedia);
		// window.setMaximized(true);
		window.show();
	}

	public void deleteMovieMedia(Stage primaryStage) {
		// ========================================= delete Movie Media Page
		// ============================

		window = primaryStage;

		Image deleteMovieMediaBackground = new Image(getClass().getResourceAsStream("mainBack.png"));
		ImageView deleteMovieMediaBackgroundImage = new ImageView(deleteMovieMediaBackground);
		deleteMovieMediaBackgroundImage.setFitHeight(421);
		deleteMovieMediaBackgroundImage.setFitWidth(736);

		Image deleteMovieMediaImagelogo = new Image(getClass().getResourceAsStream("deleteMedia.png"));
		ImageView deleteMovieMediaImagelogoView = new ImageView(deleteMovieMediaImagelogo);
		deleteMovieMediaImagelogoView.setFitHeight(77);
		deleteMovieMediaImagelogoView.setFitWidth(82);
		deleteMovieMediaImagelogoView.setLayoutX(39);
		deleteMovieMediaImagelogoView.setLayoutY(20);

		Label deleteMovieMediaLabel = new Label("Delete Movie");
		deleteMovieMediaLabel.setLayoutX(122);
		deleteMovieMediaLabel.setLayoutY(40);
		deleteMovieMediaLabel.setFont(Font.font("Lato Black", 20));
		deleteMovieMediaLabel.setTextFill(Color.web("#ffffff"));

		ComboBox<String> deleteMovieMediaComboBox = new ComboBox<>();
		deleteMovieMediaComboBox.getItems().addAll("Movie", "Music", "Game");
		deleteMovieMediaComboBox.setLayoutX(334);
		deleteMovieMediaComboBox.setLayoutY(121);
		deleteMovieMediaComboBox.setPrefWidth(150);
		deleteMovieMediaComboBox.setPromptText("Please Select Here");

		deleteMovieMediaComboBox.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (deleteMovieMediaComboBox.getValue().equals("Movie")) {
					deleteMovieMedia(primaryStage);
				} else if (deleteMovieMediaComboBox.getValue().equals("Music")) {
					deleteMusicMedia(primaryStage);
				} else if (deleteMovieMediaComboBox.getValue().equals("Game")) {
					deleteGameMedia(primaryStage);
				}
			}
		});

		Label deleteMovieMediaTypeLabel = new Label("Media Type :");
		deleteMovieMediaTypeLabel.setLayoutX(234);
		deleteMovieMediaTypeLabel.setLayoutY(124);
		deleteMovieMediaTypeLabel.setFont(Font.font("Fenton", 16));
		deleteMovieMediaTypeLabel.setTextFill(Color.web("#ffffff"));

		// Code label
		Label movieCodeLabel = new Label("Code :");
		movieCodeLabel.setLayoutX(196);
		movieCodeLabel.setLayoutY(201);
		movieCodeLabel.setFont(Font.font("Fenton", 15));
		movieCodeLabel.setTextFill(Color.web("#000000"));

		TextField movieCodeText = new TextField();
		movieCodeText.setLayoutX(112);
		movieCodeText.setLayoutY(228);
		movieCodeText.setPrefHeight(32);
		movieCodeText.setPrefWidth(217);

		// MediaTitle label
		Label movieMediaTitleLabel = new Label("Media Title :");
		movieMediaTitleLabel.setLayoutX(454);
		movieMediaTitleLabel.setLayoutY(201);
		movieMediaTitleLabel.setFont(Font.font("Fenton", 15));
		movieMediaTitleLabel.setTextFill(Color.web("#000000"));

		TextField movieMediaTitleText = new TextField();
		movieMediaTitleText.setLayoutX(394);
		movieMediaTitleText.setLayoutY(228);
		movieMediaTitleText.setPrefHeight(32);
		movieMediaTitleText.setPrefWidth(217);

		// NumberOfCopies label
		Label movieNumberOfCopiesLabel = new Label("Number Of Copies :");
		movieNumberOfCopiesLabel.setLayoutX(147);
		movieNumberOfCopiesLabel.setLayoutY(304);
		movieNumberOfCopiesLabel.setFont(Font.font("Fenton", 15));
		movieNumberOfCopiesLabel.setTextFill(Color.web("#000000"));

		TextField movieNumberOfCopiesText = new TextField();
		movieNumberOfCopiesText.setLayoutX(112);
		movieNumberOfCopiesText.setLayoutY(328);
		movieNumberOfCopiesText.setPrefHeight(32);
		movieNumberOfCopiesText.setPrefWidth(217);

		// label Rate
		Label ratingMovieLabel = new Label("Rate :");
		ratingMovieLabel.setLayoutX(483);
		ratingMovieLabel.setLayoutY(304);
		ratingMovieLabel.setFont(Font.font("Fenton", 15));
		ratingMovieLabel.setTextFill(Color.web("#000000"));

		RadioButton FourKRadio = new RadioButton("4K");
		FourKRadio.setLayoutX(442);
		FourKRadio.setLayoutY(336);
		FourKRadio.setFont(Font.font("Fenton", 12));

		RadioButton HDRadio = new RadioButton("HD");
		HDRadio.setLayoutX(485);
		HDRadio.setLayoutY(336);
		HDRadio.setFont(Font.font("Fenton", 12));

		RadioButton SDRadio = new RadioButton("SD");
		SDRadio.setLayoutX(528);
		SDRadio.setLayoutY(336);
		SDRadio.setFont(Font.font("Fenton", 12));

		ToggleGroup planGroup = new ToggleGroup();
		FourKRadio.setToggleGroup(planGroup);
		HDRadio.setToggleGroup(planGroup);
		SDRadio.setToggleGroup(planGroup);

		// deafult Plan selected is limited
		FourKRadio.setDisable(true);
		HDRadio.setDisable(true);
		SDRadio.setDisable(true);

		// label Movie not found
		Label deleteMovieFoundLabel = new Label("");
		deleteMovieFoundLabel.setLayoutX(290);
		deleteMovieFoundLabel.setLayoutY(280);
		deleteMovieFoundLabel.setFont(Font.font("Fenton", 12));
		deleteMovieFoundLabel.setTextFill(Color.web("#000000"));

		// deleteMovie Find Button
		Image deleteMovieFindButton = new Image(getClass().getResourceAsStream("search.png"));
		ImageView deleteMovieFindButtonImage = new ImageView(deleteMovieFindButton);
		deleteMovieFindButtonImage.setFitHeight(30);
		deleteMovieFindButtonImage.setFitWidth(30);
		Button deleteMovieFindButtonDone = new Button("Find", deleteMovieFindButtonImage);
		deleteMovieFindButtonDone.setLayoutX(21);
		deleteMovieFindButtonDone.setLayoutY(270);
		deleteMovieFindButtonDone.setPrefWidth(57);
		deleteMovieFindButtonDone.setPrefHeight(57);
		deleteMovieFindButtonDone.setFont(Font.font("Fenton", 12));
		deleteMovieFindButtonDone.setContentDisplay(ContentDisplay.TOP);
		deleteMovieFindButtonDone.setOnAction(e -> {
			Movie movieFind = manager.findMovie(movieCodeText.getText());
			if (movieFind == null) {
				deleteMovieFoundLabel.setText("The Media Code is not Found !");
				movieCodeText.setText("");
				movieMediaTitleText.setText("");
				movieNumberOfCopiesText.setText("");
			} else {
				movieMediaTitleText.setText(movieFind.getTitle());
				movieNumberOfCopiesText.setText(Integer.toString(movieFind.getNumCopy()));
				movieMediaTitleText.setEditable(false);
				movieNumberOfCopiesText.setEditable(false);
				deleteMovieFoundLabel.setText("The Media is Found !");
			}

		});

		// back add movie
		Image backMovieMediaImage = new Image(getClass().getResourceAsStream("back.png"));
		ImageView backMovieMediaImageView = new ImageView(backMovieMediaImage);
		backMovieMediaImageView.setFitHeight(30);
		backMovieMediaImageView.setFitWidth(30);
		Button backMovieMediaButton = new Button("Back", backMovieMediaImageView);
		backMovieMediaButton.setLayoutX(654);
		backMovieMediaButton.setLayoutY(344);
		backMovieMediaButton.setPrefWidth(57);
		backMovieMediaButton.setPrefHeight(50);
		backMovieMediaButton.setFont(Font.font("Fenton", 13));
		backMovieMediaButton.setContentDisplay(ContentDisplay.TOP);
		backMovieMediaButton.setOnAction(e -> {
			movieCodeText.setText("");
			movieMediaTitleText.setText("");
			movieNumberOfCopiesText.setText("");
			window.setScene(mediaPage);
		});

		// deleteDone add movie
		Image deleteDoneMovieMediaImage = new Image(getClass().getResourceAsStream("delete.png"));
		ImageView deleteDoneMovieMediaImageView = new ImageView(deleteDoneMovieMediaImage);
		deleteDoneMovieMediaImageView.setFitHeight(30);
		deleteDoneMovieMediaImageView.setFitWidth(28);
		Button deleteDoneMovieMediaButton = new Button("Delete", deleteDoneMovieMediaImageView);
		deleteDoneMovieMediaButton.setLayoutX(21);
		deleteDoneMovieMediaButton.setLayoutY(344);
		deleteDoneMovieMediaButton.setPrefWidth(57);
		deleteDoneMovieMediaButton.setPrefHeight(57);
		deleteDoneMovieMediaButton.setFont(Font.font("Fenton", 13));
		deleteDoneMovieMediaButton.setContentDisplay(ContentDisplay.TOP);
		deleteDoneMovieMediaButton.setOnAction(e -> {
			Movie movieFind = manager.findMovie(movieCodeText.getText());
			manager.media.remove(movieFind);
			deleteMovieFoundLabel.setText("The Media has been deleted Successfully !");
			movieCodeText.setText("");
			movieMediaTitleText.setText("");
			movieNumberOfCopiesText.setText("");
		});

		Pane deleteMovieMediaPane = new Pane();
		deleteMovieMediaPane.getChildren().addAll(deleteMovieMediaBackgroundImage, deleteMovieFindButtonImage,
				deleteMovieFindButtonDone, deleteMovieMediaImagelogoView, deleteMovieMediaLabel,
				deleteMovieMediaTypeLabel, deleteMovieFoundLabel, deleteMovieMediaComboBox, movieCodeLabel,
				movieCodeText, movieMediaTitleLabel, movieMediaTitleText, movieNumberOfCopiesLabel,
				movieNumberOfCopiesText, ratingMovieLabel, FourKRadio, HDRadio, SDRadio, backMovieMediaImageView,
				backMovieMediaButton, deleteDoneMovieMediaImageView, deleteDoneMovieMediaButton);
		deleteMovieMedia = new Scene(deleteMovieMediaPane, 736, 421);

		window.setScene(deleteMovieMedia);
		// window.setMaximized(true);
		window.show();

	}

	public void deleteMusicMedia(Stage primaryStage) {
		// ========================================= delete Music Media Page
		// ============================

		window = primaryStage;

		Image deleteMusicMediaBackground = new Image(getClass().getResourceAsStream("mainBack.png"));
		ImageView deleteMusicMediaBackgroundImage = new ImageView(deleteMusicMediaBackground);
		deleteMusicMediaBackgroundImage.setFitHeight(421);
		deleteMusicMediaBackgroundImage.setFitWidth(736);

		Image deleteMusicMediaImagelogo = new Image(getClass().getResourceAsStream("deleteMedia.png"));
		ImageView deleteMusicMediaImagelogoView = new ImageView(deleteMusicMediaImagelogo);
		deleteMusicMediaImagelogoView.setFitHeight(77);
		deleteMusicMediaImagelogoView.setFitWidth(82);
		deleteMusicMediaImagelogoView.setLayoutX(39);
		deleteMusicMediaImagelogoView.setLayoutY(20);

		Label deleteMusicMediaLabel = new Label("Delete Music");
		deleteMusicMediaLabel.setLayoutX(122);
		deleteMusicMediaLabel.setLayoutY(40);
		deleteMusicMediaLabel.setFont(Font.font("Lato Black", 20));
		deleteMusicMediaLabel.setTextFill(Color.web("#ffffff"));

		ComboBox<String> deleteMusicMediaComboBox = new ComboBox<>();
		deleteMusicMediaComboBox.getItems().addAll("Movie", "Music", "Game");
		deleteMusicMediaComboBox.setLayoutX(334);
		deleteMusicMediaComboBox.setLayoutY(121);
		deleteMusicMediaComboBox.setPrefWidth(150);
		deleteMusicMediaComboBox.setPromptText("Please Select Here");

		deleteMusicMediaComboBox.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (deleteMusicMediaComboBox.getValue().equals("Movie")) {
					deleteMovieMedia(primaryStage);
				} else if (deleteMusicMediaComboBox.getValue().equals("Music")) {
					deleteMusicMedia(primaryStage);
				} else if (deleteMusicMediaComboBox.getValue().equals("Game")) {
					deleteGameMedia(primaryStage);
				}
			}
		});

		Label musicMediaTypeLabel = new Label("Media Type :");
		musicMediaTypeLabel.setLayoutX(234);
		musicMediaTypeLabel.setLayoutY(124);
		musicMediaTypeLabel.setFont(Font.font("Fenton", 16));
		musicMediaTypeLabel.setTextFill(Color.web("#ffffff"));

		// Code label
		Label musicCodeLabel = new Label("Code :");
		musicCodeLabel.setLayoutX(200);
		musicCodeLabel.setLayoutY(185);
		musicCodeLabel.setFont(Font.font("Fenton", 15));
		musicCodeLabel.setTextFill(Color.web("#000000"));

		TextField musicCodeText = new TextField();
		musicCodeText.setLayoutX(116);
		musicCodeText.setLayoutY(212);
		musicCodeText.setPrefHeight(32);
		musicCodeText.setPrefWidth(217);

		// MediaTitle label
		Label musicMediaTitleLabel = new Label("Media Title :");
		musicMediaTitleLabel.setLayoutX(458);
		musicMediaTitleLabel.setLayoutY(185);
		musicMediaTitleLabel.setFont(Font.font("Fenton", 15));
		musicMediaTitleLabel.setTextFill(Color.web("#000000"));

		TextField musicMediaTitleText = new TextField();
		musicMediaTitleText.setLayoutX(398);
		musicMediaTitleText.setLayoutY(212);
		musicMediaTitleText.setPrefHeight(32);
		musicMediaTitleText.setPrefWidth(217);

		// NumberOfCopies label
		Label musicNumberOfCopiesLabel = new Label("Number Of Copies :");
		musicNumberOfCopiesLabel.setLayoutX(151);
		musicNumberOfCopiesLabel.setLayoutY(279);
		musicNumberOfCopiesLabel.setFont(Font.font("Fenton", 15));
		musicNumberOfCopiesLabel.setTextFill(Color.web("#000000"));

		TextField musicNumberOfCopiesText = new TextField();
		musicNumberOfCopiesText.setLayoutX(116);
		musicNumberOfCopiesText.setLayoutY(303);
		musicNumberOfCopiesText.setPrefHeight(32);
		musicNumberOfCopiesText.setPrefWidth(217);

		// Song label
		Label musicSongLabel = new Label("Song :");
		musicSongLabel.setLayoutX(483);
		musicSongLabel.setLayoutY(279);
		musicSongLabel.setFont(Font.font("Fenton", 15));
		musicSongLabel.setTextFill(Color.web("#000000"));

		TextField musicSongText = new TextField();
		musicSongText.setLayoutX(399);
		musicSongText.setLayoutY(303);
		musicSongText.setPrefHeight(32);
		musicSongText.setPrefWidth(217);

		// Artist label
		Label musicArtistLabel = new Label("Artist :");
		musicArtistLabel.setLayoutX(338);
		musicArtistLabel.setLayoutY(344);
		musicArtistLabel.setFont(Font.font("Fenton", 15));
		musicArtistLabel.setTextFill(Color.web("#000000"));

		TextField musicArtistText = new TextField();
		musicArtistText.setLayoutX(260);
		musicArtistText.setLayoutY(371);
		musicArtistText.setPrefHeight(32);
		musicArtistText.setPrefWidth(217);

		musicMediaTitleText.setEditable(false);
		musicNumberOfCopiesText.setEditable(false);
		musicArtistText.setEditable(false);
		musicSongText.setEditable(false);

		// label Music not found
		Label deleteMusicFoundLabel = new Label("");
		deleteMusicFoundLabel.setLayoutX(290);
		deleteMusicFoundLabel.setLayoutY(260);
		deleteMusicFoundLabel.setFont(Font.font("Fenton", 12));
		deleteMusicFoundLabel.setTextFill(Color.web("#000000"));

		// deleteMovie Find Button
		Image deleteMusicFindButton = new Image(getClass().getResourceAsStream("search.png"));
		ImageView deleteMusicFindButtonImage = new ImageView(deleteMusicFindButton);
		deleteMusicFindButtonImage.setFitHeight(30);
		deleteMusicFindButtonImage.setFitWidth(30);
		Button deleteMusicFindButtonDone = new Button("Find", deleteMusicFindButtonImage);
		deleteMusicFindButtonDone.setLayoutX(21);
		deleteMusicFindButtonDone.setLayoutY(270);
		deleteMusicFindButtonDone.setPrefWidth(57);
		deleteMusicFindButtonDone.setPrefHeight(57);
		deleteMusicFindButtonDone.setFont(Font.font("Fenton", 12));
		deleteMusicFindButtonDone.setContentDisplay(ContentDisplay.TOP);
		deleteMusicFindButtonDone.setOnAction(e -> {

			Album musicFind = manager.findMusic(musicCodeText.getText());
			if (musicFind == null) {
				deleteMusicFoundLabel.setText("The Media Code is not Found !");
				musicCodeText.setText("");
				musicMediaTitleText.setText("");
				musicNumberOfCopiesText.setText("");
				musicArtistText.setText("");
				musicSongText.setText("");
			} else {
				musicMediaTitleText.setText(musicFind.getTitle());
				musicNumberOfCopiesText.setText(Integer.toString(musicFind.getNumCopy()));
				musicArtistText.setText(musicFind.getArtist());
				musicSongText.setText(musicFind.getSongs());
				musicMediaTitleText.setEditable(false);
				musicNumberOfCopiesText.setEditable(false);
				musicArtistText.setEditable(false);
				musicSongText.setEditable(false);
				deleteMusicFoundLabel.setText("The Media is Found !");
			}

		});

		// back add music
		Image backDeleteMusicMediaImage = new Image(getClass().getResourceAsStream("back.png"));
		ImageView backDeleteMusicMediaImageView = new ImageView(backDeleteMusicMediaImage);
		backDeleteMusicMediaImageView.setFitHeight(30);
		backDeleteMusicMediaImageView.setFitWidth(30);
		Button backDeleteMusicMediaButton = new Button("Back", backDeleteMusicMediaImageView);
		backDeleteMusicMediaButton.setLayoutX(654);
		backDeleteMusicMediaButton.setLayoutY(344);
		backDeleteMusicMediaButton.setPrefWidth(57);
		backDeleteMusicMediaButton.setPrefHeight(50);
		backDeleteMusicMediaButton.setFont(Font.font("Fenton", 13));
		backDeleteMusicMediaButton.setContentDisplay(ContentDisplay.TOP);
		backDeleteMusicMediaButton.setOnAction(e -> {
			musicCodeText.setText("");
			musicMediaTitleText.setText("");
			musicNumberOfCopiesText.setText("");
			musicSongText.setText("");
			musicArtistText.setText("");
			window.setScene(mediaPage);
		});

		// deleteDone add music
		Image deleteDoneMusicMediaImage = new Image(getClass().getResourceAsStream("delete.png"));
		ImageView deleteDoneMusicMediaImageView = new ImageView(deleteDoneMusicMediaImage);
		deleteDoneMusicMediaImageView.setFitHeight(30);
		deleteDoneMusicMediaImageView.setFitWidth(28);
		Button deleteDoneMusicMediaButton = new Button("Delete", deleteDoneMusicMediaImageView);
		deleteDoneMusicMediaButton.setLayoutX(21);
		deleteDoneMusicMediaButton.setLayoutY(344);
		deleteDoneMusicMediaButton.setPrefWidth(57);
		deleteDoneMusicMediaButton.setPrefHeight(57);
		deleteDoneMusicMediaButton.setFont(Font.font("Fenton", 13));
		deleteDoneMusicMediaButton.setContentDisplay(ContentDisplay.TOP);
		deleteDoneMusicMediaButton.setOnAction(e -> {
			Media musicFind = manager.searchByCode(musicCodeText.getText());
			manager.media.remove(musicFind);
			deleteMusicFoundLabel.setText("The Media has been deleted Successfully !");
			musicCodeText.setText("");
			musicMediaTitleText.setText("");
			musicNumberOfCopiesText.setText("");
			musicSongText.setText("");
			musicArtistText.setText("");
		});

		Pane deleteMusicMediaPane = new Pane();
		deleteMusicMediaPane.getChildren().addAll(deleteMusicMediaBackgroundImage, deleteMusicMediaImagelogoView,
				deleteMusicMediaLabel, deleteMusicFoundLabel, deleteMusicFindButtonImage, deleteMusicFindButtonDone,
				musicMediaTypeLabel, deleteMusicMediaComboBox, musicCodeLabel, musicCodeText, musicMediaTitleLabel,
				musicMediaTitleText, musicNumberOfCopiesLabel, musicNumberOfCopiesText, musicSongLabel, musicSongText,
				musicArtistLabel, musicArtistText, backDeleteMusicMediaImageView, backDeleteMusicMediaButton,
				deleteDoneMusicMediaImageView, deleteDoneMusicMediaButton);
		deleteMusicMedia = new Scene(deleteMusicMediaPane, 736, 421);

		window.setScene(deleteMusicMedia);
		// window.setMaximized(true);
		window.show();

	}

	public void deleteGameMedia(Stage primaryStage) {
		// ========================================= delete Game Media Page
		// ============================

		window = primaryStage;

		Image deleteGameMediaBackground = new Image(getClass().getResourceAsStream("mainBack.png"));
		ImageView deleteGameMediaBackgroundImage = new ImageView(deleteGameMediaBackground);
		deleteGameMediaBackgroundImage.setFitHeight(421);
		deleteGameMediaBackgroundImage.setFitWidth(736);

		Image deleteGameMediaImagelogo = new Image(getClass().getResourceAsStream("deleteMedia.png"));
		ImageView deleteGameMediaImagelogoView = new ImageView(deleteGameMediaImagelogo);
		deleteGameMediaImagelogoView.setFitHeight(77);
		deleteGameMediaImagelogoView.setFitWidth(82);
		deleteGameMediaImagelogoView.setLayoutX(39);
		deleteGameMediaImagelogoView.setLayoutY(20);

		Label deleteGameMediaLabel = new Label("Delete Game");
		deleteGameMediaLabel.setLayoutX(122);
		deleteGameMediaLabel.setLayoutY(40);
		deleteGameMediaLabel.setFont(Font.font("Lato Black", 20));
		deleteGameMediaLabel.setTextFill(Color.web("#ffffff"));

		ComboBox<String> deleteGameMediaComboBox = new ComboBox<>();
		deleteGameMediaComboBox.getItems().addAll("Movie", "Music", "Game");
		deleteGameMediaComboBox.setLayoutX(334);
		deleteGameMediaComboBox.setLayoutY(121);
		deleteGameMediaComboBox.setPrefWidth(150);
		deleteGameMediaComboBox.setPromptText("Please Select Here");

		deleteGameMediaComboBox.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (deleteGameMediaComboBox.getValue().equals("Movie")) {
					deleteMovieMedia(primaryStage);
				} else if (deleteGameMediaComboBox.getValue().equals("Music")) {
					deleteMusicMedia(primaryStage);
				} else if (deleteGameMediaComboBox.getValue().equals("Game")) {
					deleteGameMedia(primaryStage);
				}
			}
		});

		Label gameMediaTypeLabel = new Label("Media Type :");
		gameMediaTypeLabel.setLayoutX(234);
		gameMediaTypeLabel.setLayoutY(124);
		gameMediaTypeLabel.setFont(Font.font("Fenton", 16));
		gameMediaTypeLabel.setTextFill(Color.web("#ffffff"));

		// Code label
		Label gameCodeLabel = new Label("Code :");
		gameCodeLabel.setLayoutX(196);
		gameCodeLabel.setLayoutY(201);
		gameCodeLabel.setFont(Font.font("Fenton", 15));
		gameCodeLabel.setTextFill(Color.web("#000000"));

		TextField gameCodeText = new TextField();
		gameCodeText.setLayoutX(112);
		gameCodeText.setLayoutY(228);
		gameCodeText.setPrefHeight(32);
		gameCodeText.setPrefWidth(217);

		// MediaTitle label
		Label gameMediaTitleLabel = new Label("Media Title :");
		gameMediaTitleLabel.setLayoutX(454);
		gameMediaTitleLabel.setLayoutY(201);
		gameMediaTitleLabel.setFont(Font.font("Fenton", 15));
		gameMediaTitleLabel.setTextFill(Color.web("#000000"));

		TextField gameMediaTitleText = new TextField();
		gameMediaTitleText.setLayoutX(394);
		gameMediaTitleText.setLayoutY(228);
		gameMediaTitleText.setPrefHeight(32);
		gameMediaTitleText.setPrefWidth(217);

		// NumberOfCopies label
		Label gameNumberOfCopiesLabel = new Label("Number Of Copies :");
		gameNumberOfCopiesLabel.setLayoutX(147);
		gameNumberOfCopiesLabel.setLayoutY(304);
		gameNumberOfCopiesLabel.setFont(Font.font("Fenton", 15));
		gameNumberOfCopiesLabel.setTextFill(Color.web("#000000"));

		TextField gameNumberOfCopiesText = new TextField();
		gameNumberOfCopiesText.setLayoutX(112);
		gameNumberOfCopiesText.setLayoutY(328);
		gameNumberOfCopiesText.setPrefHeight(32);
		gameNumberOfCopiesText.setPrefWidth(217);

		// Weight label
		Label gameWeightLabel = new Label("Weight :");
		gameWeightLabel.setLayoutX(479);
		gameWeightLabel.setLayoutY(304);
		gameWeightLabel.setFont(Font.font("Fenton", 15));
		gameWeightLabel.setTextFill(Color.web("#000000"));

		TextField gameWeightText = new TextField();
		gameWeightText.setLayoutX(395);
		gameWeightText.setLayoutY(328);
		gameWeightText.setPrefHeight(32);
		gameWeightText.setPrefWidth(217);

		gameMediaTitleText.setEditable(false);
		gameNumberOfCopiesText.setEditable(false);
		gameWeightText.setEditable(false);

		// label Movie not found
		Label deleteGameFoundLabel = new Label("");
		deleteGameFoundLabel.setLayoutX(290);
		deleteGameFoundLabel.setLayoutY(280);
		deleteGameFoundLabel.setFont(Font.font("Fenton", 12));
		deleteGameFoundLabel.setTextFill(Color.web("#000000"));

		// deleteGameFind Button
		Image deleteGameFindButton = new Image(getClass().getResourceAsStream("search.png"));
		ImageView deleteGameFindButtonImage = new ImageView(deleteGameFindButton);
		deleteGameFindButtonImage.setFitHeight(30);
		deleteGameFindButtonImage.setFitWidth(30);
		Button deleteGameFindButtonDone = new Button("Find", deleteGameFindButtonImage);
		deleteGameFindButtonDone.setLayoutX(21);
		deleteGameFindButtonDone.setLayoutY(270);
		deleteGameFindButtonDone.setPrefWidth(57);
		deleteGameFindButtonDone.setPrefHeight(57);
		deleteGameFindButtonDone.setFont(Font.font("Fenton", 12));
		deleteGameFindButtonDone.setContentDisplay(ContentDisplay.TOP);
		deleteGameFindButtonDone.setOnAction(e -> {

			Game gameFind = manager.findGame(gameCodeText.getText());
			if (gameFind == null) {
				deleteGameFoundLabel.setText("The Media Code is not Found !");
				gameCodeText.setText("");
				gameMediaTitleText.setText("");
				gameNumberOfCopiesText.setText("");
				gameWeightText.setText("");
			} else {
				gameMediaTitleText.setText(gameFind.getTitle());
				gameNumberOfCopiesText.setText(Integer.toString(gameFind.getNumCopy()));
				gameWeightText.setText(Double.toString(gameFind.getWeight()));
				gameMediaTitleText.setEditable(false);
				gameNumberOfCopiesText.setEditable(false);
				gameWeightText.setEditable(false);
				deleteGameFoundLabel.setText("The Media is Found !");
			}

		});

		// back add game
		Image backDeleteGameMediaImage = new Image(getClass().getResourceAsStream("back.png"));
		ImageView backDeleteGameMediaImageView = new ImageView(backDeleteGameMediaImage);
		backDeleteGameMediaImageView.setFitHeight(30);
		backDeleteGameMediaImageView.setFitWidth(30);
		Button backDeleteGameMediaButton = new Button("Back", backDeleteGameMediaImageView);
		backDeleteGameMediaButton.setLayoutX(654);
		backDeleteGameMediaButton.setLayoutY(344);
		backDeleteGameMediaButton.setPrefWidth(57);
		backDeleteGameMediaButton.setPrefHeight(50);
		backDeleteGameMediaButton.setFont(Font.font("Fenton", 13));
		backDeleteGameMediaButton.setContentDisplay(ContentDisplay.TOP);
		backDeleteGameMediaButton.setOnAction(e -> {
			deleteGameFoundLabel.setText("");
			gameCodeText.setText("");
			gameMediaTitleText.setText("");
			gameNumberOfCopiesText.setText("");
			gameWeightText.setText("");
			window.setScene(mediaPage);
		});

		// deleteDone add game
		Image deleteDoneGameMediaImage = new Image(getClass().getResourceAsStream("delete.png"));
		ImageView deleteDoneGameMediaImageView = new ImageView(deleteDoneGameMediaImage);
		deleteDoneGameMediaImageView.setFitHeight(30);
		deleteDoneGameMediaImageView.setFitWidth(28);
		Button deleteDoneGameMediaButton = new Button("Delete", deleteDoneGameMediaImageView);
		deleteDoneGameMediaButton.setLayoutX(21);
		deleteDoneGameMediaButton.setLayoutY(344);
		deleteDoneGameMediaButton.setPrefWidth(57);
		deleteDoneGameMediaButton.setPrefHeight(57);
		deleteDoneGameMediaButton.setFont(Font.font("Fenton", 13));
		deleteDoneGameMediaButton.setContentDisplay(ContentDisplay.TOP);
		deleteDoneGameMediaButton.setOnAction(e -> {
			Game gameFind = manager.findGame(gameCodeText.getText());
			manager.media.remove(gameFind);
			gameCodeText.setText("");
			gameMediaTitleText.setText("");
			gameNumberOfCopiesText.setText("");
			gameWeightText.setText("");
		});

		Pane deleteGameMediaPane = new Pane();
		deleteGameMediaPane.getChildren().addAll(deleteGameMediaBackgroundImage, deleteGameMediaImagelogoView,
				deleteGameMediaLabel, gameMediaTypeLabel, deleteGameMediaComboBox, gameCodeLabel, gameCodeText,
				gameMediaTitleLabel, deleteGameFindButtonImage, deleteGameFoundLabel, deleteGameFindButtonDone,
				gameMediaTitleText, gameNumberOfCopiesLabel, gameNumberOfCopiesText, gameWeightLabel, gameWeightText,
				backDeleteGameMediaImageView, backDeleteGameMediaButton, deleteDoneGameMediaImageView,
				deleteDoneGameMediaButton);
		deleteGameMedia = new Scene(deleteGameMediaPane, 736, 421);

		window.setScene(deleteGameMedia);
		// window.setMaximized(true);
		window.show();
	}

	public void updateMovieMedia(Stage primaryStage) {
		// ========================================= update Movie Media Page
		// ============================

		window = primaryStage;

		Image updateMovieMediaBackground = new Image(getClass().getResourceAsStream("mainBack.png"));
		ImageView updateMovieMediaBackgroundImage = new ImageView(updateMovieMediaBackground);
		updateMovieMediaBackgroundImage.setFitHeight(421);
		updateMovieMediaBackgroundImage.setFitWidth(736);

		Image updateMovieMediaImagelogo = new Image(getClass().getResourceAsStream("updateMedia.png"));
		ImageView updateMovieMediaImagelogoView = new ImageView(updateMovieMediaImagelogo);
		updateMovieMediaImagelogoView.setFitHeight(77);
		updateMovieMediaImagelogoView.setFitWidth(82);
		updateMovieMediaImagelogoView.setLayoutX(39);
		updateMovieMediaImagelogoView.setLayoutY(20);

		Label updateMovieMediaLabel = new Label("Update Movie");
		updateMovieMediaLabel.setLayoutX(122);
		updateMovieMediaLabel.setLayoutY(40);
		updateMovieMediaLabel.setFont(Font.font("Lato Black", 20));
		updateMovieMediaLabel.setTextFill(Color.web("#ffffff"));

		ComboBox<String> updateMovieMediaComboBox = new ComboBox<>();
		updateMovieMediaComboBox.getItems().addAll("Movie", "Music", "Game");
		updateMovieMediaComboBox.setLayoutX(334);
		updateMovieMediaComboBox.setLayoutY(121);
		updateMovieMediaComboBox.setPrefWidth(150);
		updateMovieMediaComboBox.setPromptText("Please Select Here");

		updateMovieMediaComboBox.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (updateMovieMediaComboBox.getValue().equals("Movie")) {
					updateMovieMedia(primaryStage);
				} else if (updateMovieMediaComboBox.getValue().equals("Music")) {
					updateMusicMedia(primaryStage);
				} else if (updateMovieMediaComboBox.getValue().equals("Game")) {
					updateGameMedia(primaryStage);
				}
			}
		});

		Label updateMovieMediaTypeLabel = new Label("Media Type :");
		updateMovieMediaTypeLabel.setLayoutX(234);
		updateMovieMediaTypeLabel.setLayoutY(124);
		updateMovieMediaTypeLabel.setFont(Font.font("Fenton", 16));
		updateMovieMediaTypeLabel.setTextFill(Color.web("#ffffff"));

		// Code label
		Label movieCodeLabel = new Label("Code :");
		movieCodeLabel.setLayoutX(196);
		movieCodeLabel.setLayoutY(201);
		movieCodeLabel.setFont(Font.font("Fenton", 15));
		movieCodeLabel.setTextFill(Color.web("#000000"));

		TextField movieCodeText = new TextField();
		movieCodeText.setLayoutX(112);
		movieCodeText.setLayoutY(228);
		movieCodeText.setPrefHeight(32);
		movieCodeText.setPrefWidth(217);

		// MediaTitle label
		Label movieMediaTitleLabel = new Label("Media Title :");
		movieMediaTitleLabel.setLayoutX(454);
		movieMediaTitleLabel.setLayoutY(201);
		movieMediaTitleLabel.setFont(Font.font("Fenton", 15));
		movieMediaTitleLabel.setTextFill(Color.web("#000000"));

		TextField movieMediaTitleText = new TextField();
		movieMediaTitleText.setLayoutX(394);
		movieMediaTitleText.setLayoutY(228);
		movieMediaTitleText.setPrefHeight(32);
		movieMediaTitleText.setPrefWidth(217);

		// NumberOfCopies label
		Label movieNumberOfCopiesLabel = new Label("Number Of Copies :");
		movieNumberOfCopiesLabel.setLayoutX(147);
		movieNumberOfCopiesLabel.setLayoutY(304);
		movieNumberOfCopiesLabel.setFont(Font.font("Fenton", 15));
		movieNumberOfCopiesLabel.setTextFill(Color.web("#000000"));

		TextField movieNumberOfCopiesText = new TextField();
		movieNumberOfCopiesText.setLayoutX(112);
		movieNumberOfCopiesText.setLayoutY(328);
		movieNumberOfCopiesText.setPrefHeight(32);
		movieNumberOfCopiesText.setPrefWidth(217);

		// label Rate
		Label ratingMovieLabel = new Label("Rate :");
		ratingMovieLabel.setLayoutX(483);
		ratingMovieLabel.setLayoutY(304);
		ratingMovieLabel.setFont(Font.font("Fenton", 15));
		ratingMovieLabel.setTextFill(Color.web("#000000"));

		RadioButton FourKRadio = new RadioButton("4K");
		FourKRadio.setLayoutX(442);
		FourKRadio.setLayoutY(336);
		FourKRadio.setFont(Font.font("Fenton", 12));

		RadioButton HDRadio = new RadioButton("HD");
		HDRadio.setLayoutX(485);
		HDRadio.setLayoutY(336);
		HDRadio.setFont(Font.font("Fenton", 12));

		RadioButton SDRadio = new RadioButton("SD");
		SDRadio.setLayoutX(528);
		SDRadio.setLayoutY(336);
		SDRadio.setFont(Font.font("Fenton", 12));

		ToggleGroup planGroup = new ToggleGroup();
		FourKRadio.setToggleGroup(planGroup);
		HDRadio.setToggleGroup(planGroup);
		SDRadio.setToggleGroup(planGroup);

		// label Movie not found
		Label updateMovieFoundLabel = new Label("");
		updateMovieFoundLabel.setLayoutX(290);
		updateMovieFoundLabel.setLayoutY(280);
		updateMovieFoundLabel.setFont(Font.font("Fenton", 12));
		updateMovieFoundLabel.setTextFill(Color.web("#000000"));

		// updateMovie Find Button
		Image updateMovieFindButton = new Image(getClass().getResourceAsStream("search.png"));
		ImageView updateMovieFindButtonImage = new ImageView(updateMovieFindButton);
		updateMovieFindButtonImage.setFitHeight(30);
		updateMovieFindButtonImage.setFitWidth(30);
		Button updateMovieFindButtonDone = new Button("Find", updateMovieFindButtonImage);
		updateMovieFindButtonDone.setLayoutX(21);
		updateMovieFindButtonDone.setLayoutY(270);
		updateMovieFindButtonDone.setPrefWidth(57);
		updateMovieFindButtonDone.setPrefHeight(57);
		updateMovieFindButtonDone.setFont(Font.font("Fenton", 12));
		updateMovieFindButtonDone.setContentDisplay(ContentDisplay.TOP);
		updateMovieFindButtonDone.setOnAction(e -> {
			Movie movieFind = manager.findMovie(movieCodeText.getText());
			if (movieFind == null) {
				updateMovieFoundLabel.setText("The Media Code is not Found !");
				movieCodeText.setText("");
				movieMediaTitleText.setText("");
				movieNumberOfCopiesText.setText("");
			} else {
				movieMediaTitleText.setText(movieFind.getTitle());
				movieNumberOfCopiesText.setText(Integer.toString(movieFind.getNumCopy()));
				updateMovieFoundLabel.setText("The Media is Found !");
				String rate = movieFind.getRating();
				if (rate.equalsIgnoreCase("4K")) {
					FourKRadio.setSelected(true);
				} else if (rate.equalsIgnoreCase("HD")) {
					HDRadio.setSelected(true);
				} else if (rate.equalsIgnoreCase("SD")) {
					SDRadio.setSelected(true);
				}

			}

		});

		// back update movie
		Image backUpdateMovieMediaImage = new Image(getClass().getResourceAsStream("back.png"));
		ImageView backUpdateMovieMediaImageView = new ImageView(backUpdateMovieMediaImage);
		backUpdateMovieMediaImageView.setFitHeight(30);
		backUpdateMovieMediaImageView.setFitWidth(30);
		Button backUpdateMovieMediaButton = new Button("Back", backUpdateMovieMediaImageView);
		backUpdateMovieMediaButton.setLayoutX(654);
		backUpdateMovieMediaButton.setLayoutY(344);
		backUpdateMovieMediaButton.setPrefWidth(57);
		backUpdateMovieMediaButton.setPrefHeight(50);
		backUpdateMovieMediaButton.setFont(Font.font("Fenton", 13));
		backUpdateMovieMediaButton.setContentDisplay(ContentDisplay.TOP);
		backUpdateMovieMediaButton.setOnAction(e -> {

			movieCodeText.setText("");
			movieMediaTitleText.setText("");
			movieNumberOfCopiesText.setText("");
			FourKRadio.setSelected(false);
			HDRadio.setSelected(false);
			SDRadio.setSelected(false);
			window.setScene(mediaPage);
		});

		// updateDone add movie
		Image updateDoneMovieMediaImage = new Image(getClass().getResourceAsStream("update.png"));
		ImageView updateDoneMovieMediaImageView = new ImageView(updateDoneMovieMediaImage);
		updateDoneMovieMediaImageView.setFitHeight(30);
		updateDoneMovieMediaImageView.setFitWidth(30);
		Button updateDoneMovieMediaButton = new Button("Update", updateDoneMovieMediaImageView);
		updateDoneMovieMediaButton.setLayoutX(21);
		updateDoneMovieMediaButton.setLayoutY(344);
		updateDoneMovieMediaButton.setPrefWidth(57);
		updateDoneMovieMediaButton.setPrefHeight(57);
		updateDoneMovieMediaButton.setFont(Font.font("Fenton", 10));
		updateDoneMovieMediaButton.setContentDisplay(ContentDisplay.TOP);
		updateDoneMovieMediaButton.setOnAction(e -> {

			Movie updateMovie = manager.findMovie(movieCodeText.getText());
			String Uplan = "";
			if (FourKRadio.isSelected()) {
				Uplan = "4K";
			} else if (HDRadio.isSelected()) {
				Uplan = "HD";
			} else if (SDRadio.isSelected()) {
				Uplan = "SD";
			}

			if (movieCodeText.getText() == "" || movieMediaTitleText.getText() == ""
					|| movieNumberOfCopiesText.getText() == "") {
				updateMovieFoundLabel.setText("Please Fill the fields");
			} else {
				updateMovie.setNumCopy(Integer.parseInt(movieNumberOfCopiesText.getText()));
				updateMovie.setTitle(movieMediaTitleText.getText());
				updateMovie.setRating(Uplan);
			}
			updateMovieFoundLabel.setText("The Media has been updated successfully");
			movieCodeText.setText("");
			movieMediaTitleText.setText("");
			movieNumberOfCopiesText.setText("");
			FourKRadio.setSelected(false);
			HDRadio.setSelected(false);
			SDRadio.setSelected(false);
		});

		Pane updateMovieMediaPane = new Pane();
		updateMovieMediaPane.getChildren().addAll(updateMovieMediaBackgroundImage, updateMovieFindButtonImage,
				updateMovieFindButtonDone, updateMovieFoundLabel, updateMovieMediaImagelogoView, updateMovieMediaLabel,
				updateMovieMediaTypeLabel, updateMovieMediaComboBox, movieCodeLabel, movieCodeText,
				movieMediaTitleLabel, movieMediaTitleText, movieNumberOfCopiesLabel, movieNumberOfCopiesText,
				ratingMovieLabel, FourKRadio, HDRadio, SDRadio, backUpdateMovieMediaImageView,
				backUpdateMovieMediaButton, updateDoneMovieMediaImageView, updateDoneMovieMediaButton);
		updateMovieMedia = new Scene(updateMovieMediaPane, 736, 421);

		window.setScene(updateMovieMedia);
		// window.setMaximized(true);
		window.show();

	}

	public void updateMusicMedia(Stage primaryStage) {
		// ========================================= update Music Media Page
		// ============================

		window = primaryStage;

		Image updateMusicMediaBackground = new Image(getClass().getResourceAsStream("mainBack.png"));
		ImageView updateMusicMediaBackgroundImage = new ImageView(updateMusicMediaBackground);
		updateMusicMediaBackgroundImage.setFitHeight(421);
		updateMusicMediaBackgroundImage.setFitWidth(736);

		Image updateMusicMediaImagelogo = new Image(getClass().getResourceAsStream("updateMedia.png"));
		ImageView updateMusicMediaImagelogoView = new ImageView(updateMusicMediaImagelogo);
		updateMusicMediaImagelogoView.setFitHeight(77);
		updateMusicMediaImagelogoView.setFitWidth(82);
		updateMusicMediaImagelogoView.setLayoutX(39);
		updateMusicMediaImagelogoView.setLayoutY(20);

		Label updateMusicMediaLabel = new Label("Update Music");
		updateMusicMediaLabel.setLayoutX(122);
		updateMusicMediaLabel.setLayoutY(40);
		updateMusicMediaLabel.setFont(Font.font("Lato Black", 20));
		updateMusicMediaLabel.setTextFill(Color.web("#ffffff"));

		ComboBox<String> updateMusicMediaComboBox = new ComboBox<>();
		updateMusicMediaComboBox.getItems().addAll("Movie", "Music", "Game");
		updateMusicMediaComboBox.setLayoutX(334);
		updateMusicMediaComboBox.setLayoutY(121);
		updateMusicMediaComboBox.setPrefWidth(150);
		updateMusicMediaComboBox.setPromptText("Please Select Here");

		updateMusicMediaComboBox.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (updateMusicMediaComboBox.getValue().equals("Movie")) {
					updateMovieMedia(primaryStage);
				} else if (updateMusicMediaComboBox.getValue().equals("Music")) {
					updateMusicMedia(primaryStage);
				} else if (updateMusicMediaComboBox.getValue().equals("Game")) {
					updateGameMedia(primaryStage);
				}
			}
		});

		Label musicMediaTypeLabel = new Label("Media Type :");
		musicMediaTypeLabel.setLayoutX(234);
		musicMediaTypeLabel.setLayoutY(124);
		musicMediaTypeLabel.setFont(Font.font("Fenton", 16));
		musicMediaTypeLabel.setTextFill(Color.web("#ffffff"));

		// Code label
		Label musicCodeLabel = new Label("Code :");
		musicCodeLabel.setLayoutX(200);
		musicCodeLabel.setLayoutY(185);
		musicCodeLabel.setFont(Font.font("Fenton", 15));
		musicCodeLabel.setTextFill(Color.web("#000000"));

		TextField musicCodeText = new TextField();
		musicCodeText.setLayoutX(116);
		musicCodeText.setLayoutY(212);
		musicCodeText.setPrefHeight(32);
		musicCodeText.setPrefWidth(217);

		// MediaTitle label
		Label musicMediaTitleLabel = new Label("Media Title :");
		musicMediaTitleLabel.setLayoutX(458);
		musicMediaTitleLabel.setLayoutY(185);
		musicMediaTitleLabel.setFont(Font.font("Fenton", 15));
		musicMediaTitleLabel.setTextFill(Color.web("#000000"));

		TextField musicMediaTitleText = new TextField();
		musicMediaTitleText.setLayoutX(398);
		musicMediaTitleText.setLayoutY(212);
		musicMediaTitleText.setPrefHeight(32);
		musicMediaTitleText.setPrefWidth(217);

		// NumberOfCopies label
		Label musicNumberOfCopiesLabel = new Label("Number Of Copies :");
		musicNumberOfCopiesLabel.setLayoutX(151);
		musicNumberOfCopiesLabel.setLayoutY(279);
		musicNumberOfCopiesLabel.setFont(Font.font("Fenton", 15));
		musicNumberOfCopiesLabel.setTextFill(Color.web("#000000"));

		TextField musicNumberOfCopiesText = new TextField();
		musicNumberOfCopiesText.setLayoutX(116);
		musicNumberOfCopiesText.setLayoutY(303);
		musicNumberOfCopiesText.setPrefHeight(32);
		musicNumberOfCopiesText.setPrefWidth(217);

		// Song label
		Label musicSongLabel = new Label("Song :");
		musicSongLabel.setLayoutX(483);
		musicSongLabel.setLayoutY(279);
		musicSongLabel.setFont(Font.font("Fenton", 15));
		musicSongLabel.setTextFill(Color.web("#000000"));

		TextField musicSongText = new TextField();
		musicSongText.setLayoutX(399);
		musicSongText.setLayoutY(303);
		musicSongText.setPrefHeight(32);
		musicSongText.setPrefWidth(217);

		// Artist label
		Label musicArtistLabel = new Label("Artist :");
		musicArtistLabel.setLayoutX(338);
		musicArtistLabel.setLayoutY(344);
		musicArtistLabel.setFont(Font.font("Fenton", 15));
		musicArtistLabel.setTextFill(Color.web("#000000"));

		TextField musicArtistText = new TextField();
		musicArtistText.setLayoutX(260);
		musicArtistText.setLayoutY(371);
		musicArtistText.setPrefHeight(32);
		musicArtistText.setPrefWidth(217);

		// label Movie not found
		Label updateMusicFoundLabel = new Label("");
		updateMusicFoundLabel.setLayoutX(290);
		updateMusicFoundLabel.setLayoutY(260);
		updateMusicFoundLabel.setFont(Font.font("Fenton", 12));
		updateMusicFoundLabel.setTextFill(Color.web("#000000"));

		// updateMovie Find Button
		Image updateMusicFindButton = new Image(getClass().getResourceAsStream("search.png"));
		ImageView updateMusicFindButtonImage = new ImageView(updateMusicFindButton);
		updateMusicFindButtonImage.setFitHeight(30);
		updateMusicFindButtonImage.setFitWidth(30);
		Button updateMusicFindButtonDone = new Button("Find", updateMusicFindButtonImage);
		updateMusicFindButtonDone.setLayoutX(21);
		updateMusicFindButtonDone.setLayoutY(270);
		updateMusicFindButtonDone.setPrefWidth(57);
		updateMusicFindButtonDone.setPrefHeight(57);
		updateMusicFindButtonDone.setFont(Font.font("Fenton", 12));
		updateMusicFindButtonDone.setContentDisplay(ContentDisplay.TOP);
		updateMusicFindButtonDone.setOnAction(e -> {
			Album musicFind = manager.findMusic(musicCodeText.getText());
			if (musicFind == null) {
				updateMusicFoundLabel.setText("The Media Code is not Found !");
				musicCodeText.setText("");
				musicMediaTitleText.setText("");
				musicNumberOfCopiesText.setText("");
				musicArtistText.setText("");
				musicSongText.setText("");
			} else {
				musicMediaTitleText.setText(musicFind.getTitle());
				musicNumberOfCopiesText.setText(Integer.toString(musicFind.getNumCopy()));
				musicArtistText.setText(musicFind.getArtist());
				musicSongText.setText(musicFind.getSongs());
				updateMusicFoundLabel.setText("The Media is Found !");

			}

		});

		// back add music
		Image backUpdateMusicMediaImage = new Image(getClass().getResourceAsStream("back.png"));
		ImageView backUpdateMusicMediaImageView = new ImageView(backUpdateMusicMediaImage);
		backUpdateMusicMediaImageView.setFitHeight(30);
		backUpdateMusicMediaImageView.setFitWidth(30);
		Button backUpdateMusicMediaButton = new Button("Back", backUpdateMusicMediaImageView);
		backUpdateMusicMediaButton.setLayoutX(654);
		backUpdateMusicMediaButton.setLayoutY(344);
		backUpdateMusicMediaButton.setPrefWidth(57);
		backUpdateMusicMediaButton.setPrefHeight(50);
		backUpdateMusicMediaButton.setFont(Font.font("Fenton", 13));
		backUpdateMusicMediaButton.setContentDisplay(ContentDisplay.TOP);
		backUpdateMusicMediaButton.setOnAction(e -> {
			musicCodeText.setText("");
			musicMediaTitleText.setText("");
			musicNumberOfCopiesText.setText("");
			musicSongText.setText("");
			musicArtistText.setText("");
			window.setScene(mediaPage);
		});

		// updateDone add music
		Image updateDoneMusicMediaImage = new Image(getClass().getResourceAsStream("update.png"));
		ImageView updateDoneMusicMediaImageView = new ImageView(updateDoneMusicMediaImage);
		updateDoneMusicMediaImageView.setFitHeight(30);
		updateDoneMusicMediaImageView.setFitWidth(30);
		Button updateDoneMusicMediaButton = new Button("Update", updateDoneMusicMediaImageView);
		updateDoneMusicMediaButton.setLayoutX(21);
		updateDoneMusicMediaButton.setLayoutY(344);
		updateDoneMusicMediaButton.setPrefWidth(57);
		updateDoneMusicMediaButton.setPrefHeight(57);
		updateDoneMusicMediaButton.setFont(Font.font("Fenton", 10));
		updateDoneMusicMediaButton.setContentDisplay(ContentDisplay.TOP);
		updateDoneMusicMediaButton.setOnAction(e -> {
			Album musicFind = manager.findMusic(musicCodeText.getText());

			if (musicCodeText.getText() == "" || musicMediaTitleText.getText() == ""
					|| musicNumberOfCopiesText.getText() == "" || musicSongText.getText() == ""
					|| musicArtistText.getText() == "") {
				updateMusicFoundLabel.setText("Please Fill the fields");
			} else {
				musicFind.setNumCopy(Integer.parseInt(musicNumberOfCopiesText.getText()));
				musicFind.setTitle(musicMediaTitleText.getText());
				musicFind.setArtist(musicArtistText.getText());
				musicFind.setSongs(musicSongText.getText());

			}
			updateMusicFoundLabel.setText("The Media has been updated successfully");
			musicCodeText.setText("");
			musicMediaTitleText.setText("");
			musicNumberOfCopiesText.setText("");
			musicSongText.setText("");
			musicArtistText.setText("");
		});

		Pane updateMusicMediaPane = new Pane();
		updateMusicMediaPane.getChildren().addAll(updateMusicMediaBackgroundImage, updateMusicMediaImagelogoView,
				updateMusicMediaLabel, musicMediaTypeLabel, updateMusicFoundLabel, updateMusicFindButtonImage,
				updateMusicFindButtonDone, updateMusicMediaComboBox, musicCodeLabel, musicCodeText,
				musicMediaTitleLabel, musicMediaTitleText, musicNumberOfCopiesLabel, musicNumberOfCopiesText,
				musicSongLabel, musicSongText, musicArtistLabel, musicArtistText, backUpdateMusicMediaImageView,
				backUpdateMusicMediaButton, updateDoneMusicMediaImageView, updateDoneMusicMediaButton);
		updateMusicMedia = new Scene(updateMusicMediaPane, 736, 421);

		window.setScene(updateMusicMedia);
		// window.setMaximized(true);
		window.show();

	}

	public void updateGameMedia(Stage primaryStage) {
		// ========================================= update Game Media Page
		// ============================

		window = primaryStage;

		Image updateGameMediaBackground = new Image(getClass().getResourceAsStream("mainBack.png"));
		ImageView updateGameMediaBackgroundImage = new ImageView(updateGameMediaBackground);
		updateGameMediaBackgroundImage.setFitHeight(421);
		updateGameMediaBackgroundImage.setFitWidth(736);

		Image updateGameMediaImagelogo = new Image(getClass().getResourceAsStream("updateMedia.png"));
		ImageView updateGameMediaImagelogoView = new ImageView(updateGameMediaImagelogo);
		updateGameMediaImagelogoView.setFitHeight(77);
		updateGameMediaImagelogoView.setFitWidth(82);
		updateGameMediaImagelogoView.setLayoutX(39);
		updateGameMediaImagelogoView.setLayoutY(20);

		Label updateGameMediaLabel = new Label("Update Game");
		updateGameMediaLabel.setLayoutX(122);
		updateGameMediaLabel.setLayoutY(40);
		updateGameMediaLabel.setFont(Font.font("Lato Black", 20));
		updateGameMediaLabel.setTextFill(Color.web("#ffffff"));

		ComboBox<String> updateGameMediaComboBox = new ComboBox<>();
		updateGameMediaComboBox.getItems().addAll("Movie", "Music", "Game");
		updateGameMediaComboBox.setLayoutX(334);
		updateGameMediaComboBox.setLayoutY(121);
		updateGameMediaComboBox.setPrefWidth(150);
		updateGameMediaComboBox.setPromptText("Please Select Here");

		updateGameMediaComboBox.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (updateGameMediaComboBox.getValue().equals("Movie")) {
					updateMovieMedia(primaryStage);
				} else if (updateGameMediaComboBox.getValue().equals("Music")) {
					updateMusicMedia(primaryStage);
				} else if (updateGameMediaComboBox.getValue().equals("Game")) {
					updateGameMedia(primaryStage);
				}
			}
		});

		Label gameMediaTypeLabel = new Label("Media Type :");
		gameMediaTypeLabel.setLayoutX(234);
		gameMediaTypeLabel.setLayoutY(124);
		gameMediaTypeLabel.setFont(Font.font("Fenton", 16));
		gameMediaTypeLabel.setTextFill(Color.web("#ffffff"));

		// Code label
		Label gameCodeLabel = new Label("Code :");
		gameCodeLabel.setLayoutX(196);
		gameCodeLabel.setLayoutY(201);
		gameCodeLabel.setFont(Font.font("Fenton", 15));
		gameCodeLabel.setTextFill(Color.web("#000000"));

		TextField gameCodeText = new TextField();
		gameCodeText.setLayoutX(112);
		gameCodeText.setLayoutY(228);
		gameCodeText.setPrefHeight(32);
		gameCodeText.setPrefWidth(217);

		// MediaTitle label
		Label gameMediaTitleLabel = new Label("Media Title :");
		gameMediaTitleLabel.setLayoutX(454);
		gameMediaTitleLabel.setLayoutY(201);
		gameMediaTitleLabel.setFont(Font.font("Fenton", 15));
		gameMediaTitleLabel.setTextFill(Color.web("#000000"));

		TextField gameMediaTitleText = new TextField();
		gameMediaTitleText.setLayoutX(394);
		gameMediaTitleText.setLayoutY(228);
		gameMediaTitleText.setPrefHeight(32);
		gameMediaTitleText.setPrefWidth(217);

		// NumberOfCopies label
		Label gameNumberOfCopiesLabel = new Label("Number Of Copies :");
		gameNumberOfCopiesLabel.setLayoutX(147);
		gameNumberOfCopiesLabel.setLayoutY(304);
		gameNumberOfCopiesLabel.setFont(Font.font("Fenton", 15));
		gameNumberOfCopiesLabel.setTextFill(Color.web("#000000"));

		TextField gameNumberOfCopiesText = new TextField();
		gameNumberOfCopiesText.setLayoutX(112);
		gameNumberOfCopiesText.setLayoutY(328);
		gameNumberOfCopiesText.setPrefHeight(32);
		gameNumberOfCopiesText.setPrefWidth(217);

		// Weight label
		Label gameWeightLabel = new Label("Weight :");
		gameWeightLabel.setLayoutX(479);
		gameWeightLabel.setLayoutY(304);
		gameWeightLabel.setFont(Font.font("Fenton", 15));
		gameWeightLabel.setTextFill(Color.web("#000000"));

		TextField gameWeightText = new TextField();
		gameWeightText.setLayoutX(395);
		gameWeightText.setLayoutY(328);
		gameWeightText.setPrefHeight(32);
		gameWeightText.setPrefWidth(217);

		// label Movie not found
		Label updateGameFoundLabel = new Label("");
		updateGameFoundLabel.setLayoutX(290);
		updateGameFoundLabel.setLayoutY(280);
		updateGameFoundLabel.setFont(Font.font("Fenton", 12));
		updateGameFoundLabel.setTextFill(Color.web("#000000"));

		// updateMovie Find Button
		Image updateMusicFindButton = new Image(getClass().getResourceAsStream("search.png"));
		ImageView updateGameFindButtonImage = new ImageView(updateMusicFindButton);
		updateGameFindButtonImage.setFitHeight(30);
		updateGameFindButtonImage.setFitWidth(30);
		Button updateGameFindButtonDone = new Button("Find", updateGameFindButtonImage);
		updateGameFindButtonDone.setLayoutX(21);
		updateGameFindButtonDone.setLayoutY(270);
		updateGameFindButtonDone.setPrefWidth(57);
		updateGameFindButtonDone.setPrefHeight(57);
		updateGameFindButtonDone.setFont(Font.font("Fenton", 12));
		updateGameFindButtonDone.setContentDisplay(ContentDisplay.TOP);
		updateGameFindButtonDone.setOnAction(e -> {
			Game gameFind = manager.findGame(gameCodeText.getText());
			if (gameFind == null) {
				updateGameFoundLabel.setText("The Media Code is not Found !");
				gameCodeText.setText("");
				gameMediaTitleText.setText("");
				gameNumberOfCopiesText.setText("");
				gameWeightText.setText("");
			} else {
				gameMediaTitleText.setText(gameFind.getTitle());
				gameNumberOfCopiesText.setText(Integer.toString(gameFind.getNumCopy()));
				gameWeightText.setText(Double.toString(gameFind.getWeight()));
				updateGameFoundLabel.setText("The Media is Found !");

			}

		});

		// back add game
		Image backUpdateGameMediaImage = new Image(getClass().getResourceAsStream("back.png"));
		ImageView backUpdateGameMediaImageView = new ImageView(backUpdateGameMediaImage);
		backUpdateGameMediaImageView.setFitHeight(30);
		backUpdateGameMediaImageView.setFitWidth(30);
		Button backUpdateGameMediaButton = new Button("Back", backUpdateGameMediaImageView);
		backUpdateGameMediaButton.setLayoutX(654);
		backUpdateGameMediaButton.setLayoutY(344);
		backUpdateGameMediaButton.setPrefWidth(57);
		backUpdateGameMediaButton.setPrefHeight(50);
		backUpdateGameMediaButton.setFont(Font.font("Fenton", 13));
		backUpdateGameMediaButton.setContentDisplay(ContentDisplay.TOP);
		backUpdateGameMediaButton.setOnAction(e -> window.setScene(mediaPage));

		// updateDone add game
		Image updateDoneGameMediaImage = new Image(getClass().getResourceAsStream("update.png"));
		ImageView updateDoneGameMediaImageView = new ImageView(updateDoneGameMediaImage);
		updateDoneGameMediaImageView.setFitHeight(30);
		updateDoneGameMediaImageView.setFitWidth(30);
		Button updateDoneGameMediaButton = new Button("Update", updateDoneGameMediaImageView);
		updateDoneGameMediaButton.setLayoutX(21);
		updateDoneGameMediaButton.setLayoutY(344);
		updateDoneGameMediaButton.setPrefWidth(57);
		updateDoneGameMediaButton.setPrefHeight(57);
		updateDoneGameMediaButton.setFont(Font.font("Fenton", 10));
		updateDoneGameMediaButton.setContentDisplay(ContentDisplay.TOP);
		updateDoneGameMediaButton.setOnAction(e -> {
			Game gameFind = manager.findGame(gameCodeText.getText());
			if (gameCodeText.getText() == "" || gameMediaTitleText.getText() == ""
					|| gameNumberOfCopiesText.getText() == "" || gameWeightText.getText() == "") {
				updateGameFoundLabel.setText("Please Fill the fields");
			} else {
				gameFind.setNumCopy(Integer.parseInt(gameNumberOfCopiesText.getText()));
				gameFind.setTitle(gameMediaTitleText.getText());
				gameFind.setWeight(Double.parseDouble(gameWeightText.getText()));
			}
			updateGameFoundLabel.setText("The Media has been updated successfully");
			gameCodeText.setText("");
			gameMediaTitleText.setText("");
			gameNumberOfCopiesText.setText("");
			gameWeightText.setText("");
		});

		Pane updateGameMediaPane = new Pane();
		updateGameMediaPane.getChildren().addAll(updateGameMediaBackgroundImage, updateGameMediaImagelogoView,
				updateGameMediaLabel, gameMediaTypeLabel, updateGameFoundLabel, updateGameFindButtonImage,
				updateGameFindButtonDone, updateGameMediaComboBox, gameCodeLabel, gameCodeText, gameMediaTitleLabel,
				gameMediaTitleText, gameNumberOfCopiesLabel, gameNumberOfCopiesText, gameWeightLabel, gameWeightText,
				backUpdateGameMediaImageView, backUpdateGameMediaButton, updateDoneGameMediaImageView,
				updateDoneGameMediaButton);
		updateGameMedia = new Scene(updateGameMediaPane, 736, 421);

		window.setScene(updateGameMedia);
		// window.setMaximized(true);
		window.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
