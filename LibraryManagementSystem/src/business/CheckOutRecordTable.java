package business;

import java.time.LocalDate;

public class CheckOutRecordTable {
	private String bookTitle;
	private String Isbn;
	private LocalDate checkOutDate;
	private LocalDate dueDate;
	
	
	
	public CheckOutRecordTable(String bookTitle, String Isbn, LocalDate checkOutDate, LocalDate dueDate) 
	{
		this.bookTitle = bookTitle;
		this.Isbn = Isbn;
		this.checkOutDate = checkOutDate;
		this.dueDate = dueDate;
	}



	public String getBookTitle() {
		return bookTitle;
	}



	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}





	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}



	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}



	public LocalDate getDueDate() {
		return dueDate;
	}



	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}



	public String getIsbn() {
		return Isbn;
	}


	public void setIsbn(String isbn) {
		Isbn = isbn;
	}
	
	
	
}
