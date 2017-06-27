package controller;

import business.Book;
import dataaccess.DataAccessFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class AddBookCopyController {

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private TextField txtBookNoCopies;

	@FXML
	private Button btnAddBookCopy;

	@FXML
	private Label lblMessage;

	@FXML
	private Font x1;

	@FXML
	private TextField txtISBN;

	@FXML
	private GridPane gridPane;

	@FXML
	void AddBookCopy(ActionEvent event) {
		// search if book exist with same isbn no
		// if false return cannot add book copy add book first
		// if true return no of book and add new book copy

		String no = txtBookNoCopies.getText();
		String ISBN = txtISBN.getText();
		// System.out.println(ISBN + no);

		// Save new book in DataAccessFacade
		DataAccessFacade dataAccessFacade = new DataAccessFacade();
		// Book b = dataAccessFacade.readBook(ISBN);

		try {
			Book b = dataAccessFacade.readBook(ISBN);

			if (b != null) {

				int oldCopyNo = b.getNoOfCopies(); 
				int newCopyNo = oldCopyNo + Math.abs(Integer.parseInt(no));

				b.setNoOFCopies(newCopyNo);
				dataAccessFacade.saveBook(b);

				// Displaying the success alert
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Added SuccessFull");
				alert.setHeaderText(null);
				alert.setContentText("Book has been added SuccessFully!!! \n Total No of Book = " + newCopyNo);
				alert.showAndWait();
				
				clearBookData();
				lblMessage.setText("");

			} else {
				lblMessage.setText("Book withs ISBN : " + ISBN + " does not exist!!! \n Please add book first!");
			}

		} catch (Exception e) {

		}

	}

	private void clearBookData() {
		txtISBN.clear();
		txtBookNoCopies.clear();		
	}
}
