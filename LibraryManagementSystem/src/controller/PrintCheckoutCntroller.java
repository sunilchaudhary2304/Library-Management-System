package controller;
/****************************************LMS*****************************************************************************
 * @author      : Group 9 
 * Date         : 10/16/2016
 * Description  : To return the checkout report data to the table and console (Extra Credit). 
 * Version      : 1.00  
 * 
 ************************************************************************************************************************/

import java.time.LocalDate;
import java.util.ArrayList; 
import java.util.List; 

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView; 
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory; 


public class PrintCheckoutCntroller
{
     
    @FXML private Label showState;
    @FXML private Button Print;
    
    @FXML private TableColumn<CheckOutRecordTable, LocalDate> CheckoutDate;
    @FXML private TableView<CheckOutRecordTable> ShowCheckState;
    @FXML private TableColumn<CheckOutRecordTable, String> CheckOutISBN;
    @FXML private TableColumn<CheckOutRecordTable, String> CheckOutBookTitle;
    @FXML private TableColumn<CheckOutRecordTable, LocalDate> DueDate;
    
    @FXML private TextField TxtMemID;

    
    @FXML public void DoPrint(ActionEvent event) 
    {
    	printInConsole();
    }
    
    public void printInConsole()
    {
    	String memId = TxtMemID.getText(); 
    	DataAccess da=new DataAccessFacade();
    	if (da.readLibraryMember(memId)!=null) 
    	{
    		LibraryMember aMember=da.readLibraryMember(memId);
        	List<CheckoutRecordEntry> recordEntries=new ArrayList<CheckoutRecordEntry>();
        	recordEntries=aMember.getRecord().getEntries();
        	 
        	System.out.println("Title\t\t\tISBN\tCheck Out Date\t\tDue Date");
        	for(CheckoutRecordEntry x: recordEntries)
    		{
    			System.out.println( x.getBook().getTitle()+"\t" + x.getBook().getIsbn()+"\t" +x.getCheckoutDate()+"\t\t" +x.getDueDate());
    		}
    		 
    		showState.setText("Print Sucessed.");
    		setBookTable(recordEntries);	
		}
    	else 
    	{
			TxtMemID.setText("");
			showState.setText("");
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Library Member");
			alert.setHeaderText(null);
			alert.setContentText("This ID does not exist in Library!! Please try again");
			alert.showAndWait();
		}
    }
    
    public void setBookTable(List<CheckoutRecordEntry> recordEntries) 
    {

    	//A list that allows listeners to track changes when they occur
    	ObservableList<CheckOutRecordTable> bookRecordData = FXCollections.observableArrayList();

    	List<CheckOutRecordTable> checkOutRecordTable = new ArrayList<CheckOutRecordTable>();
    	
    	for(CheckoutRecordEntry x: recordEntries)
    	{
    		checkOutRecordTable.add(new CheckOutRecordTable(
    						x.getBook().getTitle(), x.getBook().getIsbn(),
    						x.getCheckoutDate(), x.getDueDate()));
    	}
 		bookRecordData.addAll(checkOutRecordTable);
    	ShowCheckState.setItems(bookRecordData);
    			
    	CheckOutBookTitle.setCellValueFactory(new PropertyValueFactory<CheckOutRecordTable, String>("bookTitle"));
    	CheckOutISBN.setCellValueFactory(new PropertyValueFactory<CheckOutRecordTable, String>("Isbn"));
    	CheckoutDate.setCellValueFactory(new PropertyValueFactory<CheckOutRecordTable,LocalDate>("checkOutDate"));
    	DueDate.setCellValueFactory(new PropertyValueFactory<CheckOutRecordTable, LocalDate>("dueDate"));	
    }		
}