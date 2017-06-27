package business;

import java.io.Serializable;

/* Immutable */
final public class Author extends Person implements Serializable 
{
	
	private static final long serialVersionUID = 7508481940058530471L;
	private String shortBio;
	private String credentials;
	
	public Author(String firstName, String lastName, String phoneNo, String credentials, Address address, String shortBio) 
	{
		super(firstName, lastName, phoneNo, address);
		this.credentials = credentials;
		this.shortBio = shortBio;
	}
	
	
	public String toString()
	{
			return this.getFirstName()+" "+this.getLastName();		
	}
	
	public String getShortBio() 
	{
		return shortBio;
	}

	public void setShortBio(String shortBio)
	{
		this.shortBio = shortBio;
	}

	public String getCredentials() 
	{
		return credentials;
	}

	public void setCredentials(String credentials) 
	{
		this.credentials = credentials;
	}
}
