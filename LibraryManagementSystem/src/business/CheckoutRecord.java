package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord implements Serializable {
	
	private static final long serialVersionUID = -3119855589946373695L;
	private LibraryMember member;
	private List<CheckoutRecordEntry> entries = new ArrayList<>();
	
	public void setMember(LibraryMember member) {
		this.member = member;
	}

	public void setEntries(List<CheckoutRecordEntry> entries) {
		this.entries = entries;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public LibraryMember getMember() {
		return member;
	}

	public List<CheckoutRecordEntry> getEntries() {
		return entries;
	}

	public void addEntry(CheckoutRecordEntry c) {
		entries.add(c);
	}
	
	public String toString() {
		return entries.toString();
	}
	
	 
}
