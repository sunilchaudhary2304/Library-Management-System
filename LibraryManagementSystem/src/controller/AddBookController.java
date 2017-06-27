package controller;
/****************************************LMS*****************************************************************************
 * @author      : Group 9 
 * Date         : 10/16/2016
 * Description  : To add the Book to the System @authentication: Admin. @Methods : addAuther(), addBook(), ClearBookData().
 * 				: AddAuthor() : Takes the address fields, Validation, adds author then adds book to the data access facade. 
 * Version      : 1.00  
 * 
 ************************************************************************************************************************/

import java.util.ArrayList;
import java.util.List;

import business.Address;
import business.Author;
import business.Book;
import dataaccess.DataAccessFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;

public class AddBookController {
	@FXML
	private TextField txtBookTitle;
	@FXML
	private TextField txtISBN;
	@FXML
	private TextField txtBookMaxLendDays;
	@FXML
	private TextField txtBookNoCopies;
	@FXML
	private Button btnAddBookAuthor;
	@FXML
	private Button btnAddBook;
	@FXML
    private Label lblAuthorDesc;
	
	@FXML
	private Label lblMessageAuthor;
	@FXML 
	private Label lblMessagebook;
	@FXML
	private TextField txtBookAuthorFirstName;
	@FXML
	private TextField txtBookAuthorLastName;
	@FXML
	private TextField txtBookAuthorCredentials;
	@FXML
	private TextField txtBookAuthorStreet;
	@FXML
	private TextField txtBookAuthorCity;
	@FXML
	private TextField txtBookAuthorState;
	@FXML
	private TextField txtBookAuthorZip;
	@FXML
	private TextField txtBookAuthorPhoneNo;
	@FXML
	private TextField txtBookAuthorShortBio;
	@FXML
	private GridPane gpAuthor;

	private ObservableList<Author> bookAuthor = FXCollections.observableArrayList();
	private List<Author> authorList = new ArrayList<Author>();
   
	@FXML public void AddAuthor(ActionEvent event) 
	
	{
		if (validateAuthorData())
		{
	//	lblMessageAuthor.setText("hi guys");
		Address address = new Address(txtBookAuthorStreet.getText(), txtBookAuthorCity.getText(), txtBookAuthorState.getText(), txtBookAuthorZip.getText());
		Author bookauthor = new Author(txtBookAuthorFirstName.getText(), txtBookAuthorLastName.getText(), txtBookAuthorPhoneNo.getText(), 
				txtBookAuthorCredentials.getText(), address, txtBookAuthorShortBio.getText());
		

		bookAuthor.add(bookauthor);
		lblAuthorDesc.setText("Author is ready");
		//tblBookList.setItems(bookAuthor);
		clearAuthorData();
		gpAuthor.setVisible(false);
		btnAddBook.setDisable(false);
		lblMessageAuthor.setText("");
		}
	}

	@FXML
	public void AddBook(ActionEvent event) {
	
		if(validateBookData())
		{
	//	lblMessage.setText("hi guys");
		authorList.addAll(bookAuthor);
		Book book = new Book(txtISBN.getText(), txtBookTitle.getText(), Integer.parseInt(txtBookMaxLendDays.getText()),
				Integer.parseInt(txtBookNoCopies.getText()), authorList);

		// Save new book in DataAccessFacade
		DataAccessFacade dataAccessFacade = new DataAccessFacade();
		dataAccessFacade.saveBook(book);

		// Displaying the success alert
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Added SuccessFull");
		alert.setHeaderText(null);
		alert.setContentText("Book has been added SuccessFully");
		alert.showAndWait();
		
		clearBookData();
		gpAuthor.setVisible(true);

		lblMessagebook.setText("");
		}
	}

	private void clearBookData() {
		authorList.clear();
		clearAuthorData();
		txtBookTitle.clear();
		txtISBN.clear();
		txtBookNoCopies.clear();
		txtBookMaxLendDays.clear();
		bookAuthor.clear();
	}

	private void clearAuthorData() {
		txtBookAuthorCity.clear();
		txtBookAuthorCredentials.clear();
		txtBookAuthorFirstName.clear();
		txtBookAuthorLastName.clear();
		txtBookAuthorPhoneNo.clear();
		txtBookAuthorShortBio.clear();
		txtBookAuthorState.clear();
		txtBookAuthorStreet.clear();
		txtBookAuthorZip.clear();
	}
	
	// validation portion for the text boxes is here 
	
	private boolean validateBookData() {
		if (txtISBN.getText().equals("")) {
			lblMessagebook.setText("ISBN: Value is required.");
			txtISBN.requestFocus();
			return false;
		}
		if (txtBookTitle.getText().equals("")) {
			lblMessagebook.setText("Title: Value is required.");
			txtBookTitle.requestFocus();
			return false;
		}
		if (txtBookMaxLendDays.getText().equals("")) {
			lblMessagebook.setText("Max Checkout Length: Value is required.");
			txtBookMaxLendDays.requestFocus();
			return false;
		}
		if (!txtBookMaxLendDays.getText().matches("[0-9]*" )) {
			lblMessagebook.setText("Max Checkout Length: Numeric value is required.");
			txtBookMaxLendDays.requestFocus();
			return false;
		}
		
		int val1 = Integer.parseInt(txtBookMaxLendDays.getText());
		//int val2 = Integer.parseInt(txtBookMaxLendDays.getText());
		if (val1 != 21){
			lblMessagebook.setText("Max Checkout Length: Numeric value should be 7 or 21.");
			txtBookMaxLendDays.requestFocus();
			return false;
		}	
			
		if (txtBookNoCopies.getText().equals("")) {
			lblMessagebook.setText("Number of Copies: Value is required.");
			txtBookNoCopies.requestFocus();
			return false;
		}
		
		if (!txtBookNoCopies.getText().matches("[0-9]*")) {
			lblMessagebook.setText("Number of Copies: Numeric value is required.");
			txtBookNoCopies.requestFocus();
			return false;
		}		
		return true;
	}
	
	private boolean validateAuthorData() {
		if (txtBookAuthorFirstName.getText().equals("")) {
			lblMessageAuthor.setText("First Name: Value is required.");
			txtBookAuthorFirstName.requestFocus();
			return false;
		}
		if (txtBookAuthorLastName.getText().equals("")) {
			lblMessageAuthor.setText("Last Name: Value is required.");
			txtBookAuthorLastName.requestFocus();
			return false;
		}
		if (txtBookAuthorStreet.getText().equals("")) {
			lblMessageAuthor.setText("Street: Value is required.");
			txtBookAuthorStreet.requestFocus();
			return false;
		}
		if (txtBookAuthorCity.getText().equals("")) {
			lblMessageAuthor.setText("City: Value is required.");
			txtBookAuthorCity.requestFocus();
			return false;
		}
		if (txtBookAuthorState.getText().equals("")) {
			lblMessageAuthor.setText("State: Value is required.");
			txtBookAuthorState.requestFocus();
			return false;
		}
		if (txtBookAuthorZip.getText().equals("")) {
			lblMessageAuthor.setText("Zip: Value is required.");
			txtBookAuthorZip.requestFocus();
			return false;
		}
		if (!txtBookAuthorZip.getText().matches("[0-9]*")) {
			lblMessageAuthor.setText("Zip Code: Should be Number.");
			txtBookAuthorZip.requestFocus();
			return false;
		}
		if ((txtBookAuthorZip.getText().length()) != 5) {
			lblMessageAuthor.setText("Zip Code: should be 5 digit.");
			txtBookAuthorZip.requestFocus();
			return false;
		}
		if (txtBookAuthorPhoneNo.getText().equals("")) {
			lblMessageAuthor.setText("Phone: Value is required.");
			txtBookAuthorPhoneNo.requestFocus();
			return false;
		}
		if (txtBookAuthorShortBio.getText().equals("")) {
			lblMessageAuthor.setText("Bio: Value is required.");
			txtBookAuthorShortBio.requestFocus();
			return false;
		}
		if (txtBookAuthorCredentials.getText().equals("")) {
			lblMessageAuthor.setText("Bio: Value is required.");
			txtBookAuthorCredentials.requestFocus();
			return false;
		}
		

		return true;
	}


}
