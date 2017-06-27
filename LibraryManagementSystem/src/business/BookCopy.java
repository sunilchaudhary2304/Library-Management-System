package business;

import java.io.Serializable;

public class BookCopy implements Serializable {
	private static final long serialVersionUID = 1L;
	private Publication publication;
	private int copyId; 
	
	public BookCopy(Publication p, int copyId) {
		this.publication = p;
		this.copyId = copyId;
	}
	
	public void setPublication(Publication publication) {
		this.publication = publication;
	}
	public void setCopyId(int copyId) {
		this.copyId = copyId;
	}
	
	public int getCopyId() {
		return this.copyId;
	}
	
	public String toString() {
		return publication.toString();
	}
	public Publication getPublication() {
		return publication;
	}

	
	
	
}
