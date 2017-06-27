package controller;

/****************************************LMS*****************************************************************************
 * @author      : Group 9 
 * Date         : 10/16/2016
 * Description  : To perform the Checkout process by librarian. Book checked out for the Member.   
 *                Checkout process done by taking ISBN and member Id. Where member id gives the member details and ISBN
 *                gives the book details to checkout. 
 *                
 *                setBookTable(List<>)   = gives the checkout record in list and set to the table. 
 *                checkoutBook(mid,ISBN) = validation for id and ISBN, Check available book copy and Perform checkout. 
 * Version      : 1.00  
 * 
 ************************************************************************************************************************/ 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List; 

import business.Book;
import business.CheckOutRecordTable;
import business.CheckoutRecordEntry; 
import business.LibraryMember;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


public class CheckOutBookController
{
    
    @FXML private VBox vbCheckOutRecord;
    @FXML private AnchorPane pnCheckOutForm;
    @FXML private ScrollPane pnCheckOutRecord;
    @FXML private Button btnCheckOut;
    @FXML private AnchorPane pnSubCheckOutForm;
    
    @FXML private TextField txtISBN;
    @FXML private TextField txtMemberId;

    @FXML private Label lblISBN;
    @FXML private Label lblMemberId;
    @FXML private Label lblStudentName;

    @FXML private TableView<CheckOutRecordTable> tblCheckOutRecord;
    
    @FXML private TableColumn<CheckOutRecordTable, String> thBook;
    @FXML private TableColumn<CheckOutRecordTable, LocalDate> thDueDate;
    @FXML private TableColumn<CheckOutRecordTable, LocalDate> thCheckOutDate;
    @FXML private TableColumn<CheckOutRecordTable, String> thISBN;


    
    @FXML public void checkOutForm(ActionEvent event) 
    {
    	String memberId = txtMemberId.getText();
    	String isbn = txtISBN.getText();
    	
    	checkoutBook(memberId, isbn);
    }

	public LibraryMember searchMember(String memberId)
	{
    	DataAccess da=new DataAccessFacade();
		LibraryMember lm = da.readLibraryMember(memberId);
		return lm;
	}
	
	public Book searchBook(String isbn)
	{
		DataAccess da=new DataAccessFacade();
		Book b = da.readBook(isbn);
		return b;
	}
	 
    public void checkoutBook(String memberId, String isbn) 
    {	
    	DataAccess da=new DataAccessFacade();	
    	Book book = searchBook(isbn);
		LibraryMember member = searchMember(memberId);
		
		
		if(member  == null)
		{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("LibraryMember");
			alert.setHeaderText(null);
			alert.setContentText("This ID not exist in Library");
			alert.showAndWait();
			return;
		} 
		
		if(book  == null)
		{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Book");
			alert.setHeaderText(null);
			alert.setContentText("This Book not exist in Library");
			alert.showAndWait();
			return;
		}  
		HashMap<String, Book> map = da.readBooksMap();
		  
		 // Find out the available book copy  (IsAvailable) 
		if (map.get(isbn).getCopysize()<=0)
		{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Book");
			alert.setHeaderText(null);
			alert.setContentText("No Book available for checkout");
			alert.showAndWait();
		}

		LocalDate today = LocalDate.now();
		LocalDate dueDate = LocalDate.now().plusDays( book.getMaxLendDaysAllowed());
		member.checkout(book, today, dueDate);
		
		//removing the copy of book
		map.get(isbn).removeCopy();
		da.saveLibraryMember(member);
		da.saveBook(book);
		
		 
		
		List<Book> allBooks = new ArrayList<Book>();
		for (Book value : map.values())
		{
			allBooks.add(value);
		}		
		DataAccessFacade.saveBookMap(allBooks);
		
		member = searchMember(memberId);
		
		List<CheckoutRecordEntry> recordEntries=new ArrayList<CheckoutRecordEntry>();
    	recordEntries=member.getRecord().getEntries();
    	 
    	/*System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
    	for(CheckoutRecordEntry  e : recordEntries){
    		System.out.println(e.getCheckoutDate()+" "
    							+ "  "+e.getDueDate()+" "
    							+" "+e.getCopy()+" "
    							+" "+e.getClass());
    		 
    	}*/
    	setBookTable(recordEntries);
		
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Book");
		alert.setHeaderText("");
		alert.setContentText("Book checkouted Successfully!");
		alert.showAndWait();
    }
   
 
    
    @SuppressWarnings("unchecked")
   	public void setBookTable(List<CheckoutRecordEntry> recordEntries) 
    {

       	//A list that allows listeners to track changes when they occur
       	ObservableList<CheckOutRecordTable> bookRecordData = FXCollections
       					.observableArrayList();
     	List<CheckOutRecordTable> checkOutRecordTable = new ArrayList<CheckOutRecordTable>();
     	
     	for(CheckoutRecordEntry x: recordEntries)
     	{
       		checkOutRecordTable.add(new CheckOutRecordTable(
       				x.getBook().getTitle(), x.getBook().getIsbn(),
       				x.getCheckoutDate(), x.getDueDate()));
       		//System.out.print(" "+ x.getCheckoutDate());
       	}
       	
     	bookRecordData.addAll(checkOutRecordTable);
       	tblCheckOutRecord.setItems(bookRecordData);
       			
       	thBook.setCellValueFactory(new PropertyValueFactory<CheckOutRecordTable, String>("bookTitle"));
       	thISBN.setCellValueFactory(new PropertyValueFactory<CheckOutRecordTable, String>("Isbn"));
       	thCheckOutDate.setCellValueFactory(new PropertyValueFactory<CheckOutRecordTable,LocalDate>("checkOutDate"));
       	thDueDate.setCellValueFactory(new PropertyValueFactory<CheckOutRecordTable, LocalDate>("dueDate"));
    }		
    
    
    public TableColumn<CheckOutRecordTable, String> getThISBN() 
    {
		return thISBN;
	}

	public TableColumn<CheckOutRecordTable, LocalDate> getThCheckOutDate() 
	{
		return thCheckOutDate;
	}

	public TableColumn<CheckOutRecordTable, LocalDate> getThDueDate() 
	{
		return  thDueDate;
	}

	public TableColumn<CheckOutRecordTable, String> getThBook()
	{
		return thBook;
	}

	public Label getLblStudentName() 
	{
		return lblStudentName;
	}
	
	public ScrollPane getPnCheckOutRecord()
	{
		return pnCheckOutRecord;
	}

	public TableView<CheckOutRecordTable> getTblCheckOutRecord()
	{
		return  tblCheckOutRecord;
	}

   public TextField getTxtMemberId() 
   {
		return txtMemberId;
	}


	public TextField getTxtISBN()
	{
		return txtISBN;
	} 
 
}