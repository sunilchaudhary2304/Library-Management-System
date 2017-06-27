package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Book extends Publication implements Serializable {
	private static final long serialVersionUID = 1L;
	private String isbn;
	private boolean available;
	private List<Author> authors;
	private List<BookCopy> copies = new ArrayList<BookCopy>();
	private String copiesNumString;
	private int maxLendDaysAllowed;
	private int noOfCopies;

	public Book(String isbn, String title, int maxLendDaysAllowed, int noOfcopies, List<Author> authors) {
		super(title);
		this.isbn = isbn;
		this.maxLendDaysAllowed = maxLendDaysAllowed;
		this.authors = authors;
		this.noOfCopies = noOfcopies;
		for (int i = 0; i < noOfCopies; i++) {
			addCopy();
		}
	}

	public String getCopiesNumString() {
		return getCopiesString();
	}
	/*Abinash added*/
	public int getNoOfCopies(){
		return this.noOfCopies;
	}
	
	public void setNoOFCopies(int no){
		this.noOfCopies = no;
	}

	public void setCopiesNumString(String copiesNumString) {
		this.copiesNumString = copiesNumString;
	}

	public List<BookCopy> getCopies() {
		return copies;
	}

	public void setCopies(List<BookCopy> copies) {
		this.copies = copies;
	}

	public String getCopiesString() {

		StringBuilder sb = new StringBuilder();
		sb.append("");
		sb.append(this.copies.size());
		String strI = sb.toString();

		return strI;
	}

	public void addCopy() {
		BookCopy copy = new BookCopy(this, this.copies.size() + 1);
		copies.add(copy);
	}

	public void removeCopy() {
		BookCopy copy = new BookCopy(this, this.copies.size() - 1);
		copies.remove(copies.size() - 1);
	}

	public int getCopysize() {
		return copies.size();
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public void isAvailable(boolean b) {
		available = b;
	}

	@Override
	public String toString() {

		return "Title:" + this.getTitle() + ", ISBN:" + this.getIsbn();
	}

	public int getMaxLendDaysAllowed() {
		return maxLendDaysAllowed;
	}

	public void setMaxLendDaysAllowed(int maxLendDaysAllowed) {
		this.maxLendDaysAllowed = maxLendDaysAllowed;
	}
	

}

abstract class Publication implements Serializable {
	
	private static final long serialVersionUID = 2010893663327964921L;
	private LocalDate dateDue;
	private String title;
	public void setDateDue(LocalDate d) {
		dateDue = d;
	}
	public Publication(String title) {
		this.title = title;
	}
	public LocalDate getDateDue() {
		return dateDue;
	}
	public String getTitle() {
		return title;
	}
}