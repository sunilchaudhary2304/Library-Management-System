package controller;

import business.Book;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SearchBookController {

	@FXML
	private Label showState;
	@FXML
	private Button Search;

	@FXML
	private TextField TxtISBN;

	@FXML
	public void btnSearch(ActionEvent event) {
		printInConsole();
	}

	public void printInConsole() {
		String ISBN = TxtISBN.getText();
		DataAccess da = new DataAccessFacade();
		if (da.readBook(ISBN) != null) {

			Book newBook = da.readBook(ISBN);

			TxtISBN.setText("");
			showState.setText("");
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Library Book Detail!!!");
			alert.setHeaderText(null);
			alert.setContentText(" Book ISBN :" + newBook.getIsbn() +
								// "\n Book Author: " + newBook.getAuthors() +
								 "\n No. of Copies: " + newBook.getNoOfCopies()+ 
								 "\n Maximum Allowed Date: " + newBook.getMaxLendDaysAllowed()
					);
			alert.showAndWait();
			
			/*
			 * List<CheckoutRecordEntry> recordEntries = new
			 * ArrayList<CheckoutRecordEntry>();
			 * 
			 * recordEntries = newBook.getCopies().get();
			 * System.out.println(newBook.getNoOfCopies());
			 * 
			 * setBookTable(newBook);
			 */

		}
		// CheckOutRecordTable
		// if (da.readLibraryMember(memId)!=null)
		// {
		// LibraryMember aMember=da.readLibraryMember(memId);
		// List<CheckoutRecordEntry> recordEntries=new
		// ArrayList<CheckoutRecordEntry>();
		// recordEntries=aMember.getRecord().getEntries();
		//
		// System.out.println("Title\t\t\tISBN\tCheck Out Date\t\tDue Date");
		// for(CheckoutRecordEntry x: recordEntries)
		// {
		// System.out.println( x.getBook().getTitle()+"\t" +
		// x.getBook().getIsbn()+"\t" +x.getCheckoutDate()+"\t\t"
		// +x.getDueDate());
		// }
		//
		// showState.setText("Print Sucessed.");
		// setBookTable(recordEntries);
		// }
		else {
			TxtISBN.setText("");
			showState.setText("");
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Library Book Not Found");
			alert.setHeaderText(null);
			alert.setContentText("This Book does not exist in Library!! Please try again");
			alert.showAndWait();
		}
	}
/*
	public void setBookTable(List<CheckoutRecordEntry> recordEntries) {

		// A list that allows listeners to track changes when they occur
		ObservableList<CheckOutRecordTable> bookRecordData = FXCollections.observableArrayList();

		List<CheckOutRecordTable> checkOutRecordTable = new ArrayList<CheckOutRecordTable>();

		for (CheckoutRecordEntry x : recordEntries) {
			checkOutRecordTable.add(new CheckOutRecordTable(x.getBook().getTitle(), x.getBook().getIsbn(),
					x.getCheckoutDate(), x.getDueDate()));
		}
		bookRecordData.addAll(checkOutRecordTable);
		// ShowCheckState.setItems(bookRecordData);

		CheckOutBookTitle.setCellValueFactory(new PropertyValueFactory<CheckOutRecordTable, String>("bookTitle"));
		CheckOutISBN.setCellValueFactory(new PropertyValueFactory<CheckOutRecordTable, String>("Isbn"));
		CheckoutDate.setCellValueFactory(new PropertyValueFactory<CheckOutRecordTable, LocalDate>("checkOutDate"));
		DueDate.setCellValueFactory(new PropertyValueFactory<CheckOutRecordTable, LocalDate>("dueDate"));
	}*/
}