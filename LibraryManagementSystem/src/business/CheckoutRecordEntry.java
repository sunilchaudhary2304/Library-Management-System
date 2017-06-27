package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import dataaccess.DataAccessFacade;


public class CheckoutRecordEntry implements Serializable {
	
	private static final long serialVersionUID = -8272800923661353744L;
	private Book book;
	private LocalDate checkoutDate;
	private LocalDate dueDate;
	public CheckoutRecordEntry(Book book, LocalDate checkoutDate, LocalDate dueDate)
	{ 
		this.book = book;
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
	}
	public String toString() 
	{
		return "[" + "checkoutdate:" + 
	        checkoutDate.format(DateTimeFormatter.ofPattern(DataAccessFacade.DATE_PATTERN)) +
	        ", dueDate: " + dueDate.format(DateTimeFormatter.ofPattern(DataAccessFacade.DATE_PATTERN)) +
	        ", publication: " + book + "]";
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}
	public void setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
	
}
