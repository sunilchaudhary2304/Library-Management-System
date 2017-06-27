package dataaccess;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

import business.Book;
import business.LibraryMember;
import business.User;




public class DataAccessFacade implements DataAccess 
{
	enum StorageType 
	{
		BOOKS, MEMBERS, USERS;
	}
	
	public static final String OUTPUT_DIR = System.getProperty("user.dir") + "\\src\\dataaccess\\storage";
	public static final String DATE_PATTERN = "MM/dd/yyyy";
	
	public static HashMap<String, Book> booksMap;
	public static HashMap<String, LibraryMember> membersMap;
	public static HashMap<String, User> usersMap;
	
	
	public static void saveBookMap(List<Book> bookList)
	{
		HashMap<String, Book> books = new HashMap<String, Book>();
		bookList.forEach(book -> books.put(book.getIsbn(), book));
		saveToStorage(StorageType.BOOKS, books);
	}

   //access storage	
	static void saveToStorage(StorageType type, Object ob)
	{
		ObjectOutputStream out = null;
		try 
		{
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			out = new ObjectOutputStream(Files.newOutputStream(path));
			out.writeObject(ob);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			if (out != null) 
			{
				try 
				{
					out.close();
				} 
				catch (Exception e)
				{
				}
			}
		}
	}

	static Object readFromStorage(StorageType type) 
	{
		ObjectInputStream in = null;
		Object retVal = null;
		try 
		{
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			in = new ObjectInputStream(Files.newInputStream(path));
			retVal = in.readObject();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally
		{
			if (in != null) 
			{
				try 
				{
					in.close();
				} 
				catch (Exception e) 
				{
				}
			}
		}
		return retVal;
	}

	static 
	{
		DataAccess da = new DataAccessFacade();
		booksMap = da.readBooksMap();
		usersMap = da.readUserMap();
		membersMap=da.readMemberMap();
		if (booksMap == null) 
		{			
			booksMap = new HashMap<String, Book>();
		}

		if (membersMap == null)
		{	
			membersMap = new HashMap<String, LibraryMember>();
		}
		if(usersMap==null)
		{	
			usersMap = new HashMap<String, User>();
		}
	}
	
	
	//read methods that return full maps

	@SuppressWarnings("unchecked")
	public HashMap<String, Book> readBooksMap() 
	{
		return (HashMap<String, Book>) readFromStorage(StorageType.BOOKS);
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, User> readUserMap()
	{
		return (HashMap<String, User>) readFromStorage(StorageType.USERS);
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, LibraryMember> readMemberMap()
	{
		return (HashMap<String, LibraryMember>) readFromStorage(StorageType.MEMBERS);
	}

	
	
	//LibraryMember
	public void saveLibraryMember(LibraryMember member)
	{
		membersMap.put(member.getMemberID(), member);
		saveToStorage(StorageType.MEMBERS, membersMap);
	}
	
	public LibraryMember readLibraryMember(String memberId)
	{
		LibraryMember lm = membersMap.get(memberId);
		return lm;
	}
	
	//User
	public void saveSystemUser(User user)
	{
		usersMap.put(user.getId(), user);
		saveToStorage(StorageType.USERS, usersMap);
	}
	
	public User readSystemUser(String userId) 
	{
		User ur = usersMap.get(userId);
		return ur;
	}
	
	//Book
	public void saveBook(Book book) 
	{
		booksMap.put(book.getIsbn(), book);
		saveToStorage(StorageType.BOOKS, booksMap);
	}
	
	public Book readBook(String userIsbn) 
	{
		Book bk = booksMap.get(userIsbn);
		return bk;
	}
}
