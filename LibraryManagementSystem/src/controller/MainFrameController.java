package controller;

/****************************************LMS*****************************************************************************
 * @author      : Group 9 
 * Date         : 10/16/2016
 * Description  : Added Buttons and redirect all the program from it. All the pages will show in the main frame panel
 *               (in splitter). 
 * Version      : 1.00  
 * 
 ************************************************************************************************************************/

import java.io.IOException;

import business.Auth;
import business.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MainFrameController  
{
    @FXML private Button btnAddBook;
    @FXML private Button btnAddMember;
    @FXML private Label lblUserId;
    @FXML private Pane functionPane;
    @FXML private VBox butionsControl;
    @FXML private Button btnCheckoutBook;
    @FXML private Button btnLogout;
    @FXML private Button btnPrintCheckoutRecord;
    @FXML private Label lblLoginAs;
    @FXML private VBox header; 
    @FXML private Button btnSearchBook;
    @FXML private Button btnAddBookCopy;
   
   
    @FXML public void onAddMemberClick(ActionEvent event)
    {
        try
        {	
    		Parent child = FXMLLoader.load(getClass().getResource("/view/AddLibraryMember.fxml"));
    		functionPane.getChildren().clear();
    		functionPane.getChildren().add(child);
        }
        catch (IOException e)
        {
        	e.printStackTrace();
        }
    }

    @FXML public void onCheckoutBookClick(ActionEvent event)
    {
    	try 
    	{  	
    		Parent child = FXMLLoader.load(getClass().getResource("/view/CheckOutBook.fxml"));
    		functionPane.getChildren().clear();
    		functionPane.getChildren().add(child);
        }
    	catch (IOException e)
    	{
        	e.printStackTrace();
       	}
    }


    @FXML public void onAddBookClick(ActionEvent event)
    {
    	try 
    	{  	
    		Parent child = FXMLLoader.load(getClass().getResource("/view/AddBooks.fxml"));
    		functionPane.getChildren().clear();
    		functionPane.getChildren().add(child);
        }
    	catch (IOException e) 
    	{
        	e.printStackTrace();
       	}
    }
    
    
    @FXML public void onSearchBook(ActionEvent event)
    {
    	try 
    	{  	
    		Parent child = FXMLLoader.load(getClass().getResource("/view/SearchBook.fxml"));
    		functionPane.getChildren().clear();
    		functionPane.getChildren().add(child);
        }
    	catch (IOException e) 
    	{
        	e.printStackTrace();
       	}
    }
    
    
    @FXML public void onAddBookCopyClick(ActionEvent event)
    {
    	try 
    	{  	
    		Parent child = FXMLLoader.load(getClass().getResource("/view/AddBookCopy.fxml"));
    		functionPane.getChildren().clear();
    		functionPane.getChildren().add(child);
        }
    	catch (IOException e) 
    	{
        	e.printStackTrace();
       	}
    }    

    
    @FXML public void onPrintCheckoutRecordClick(ActionEvent event) 
    {
    	try 
    	{  	
    		Parent child = FXMLLoader.load(getClass().getResource("/view/PrintCheckoutRecordWin.fxml"));
    		functionPane.getChildren().clear();
    		functionPane.getChildren().add(child);
        }
    	catch (IOException e)
    	{
        	e.printStackTrace();
       	}
    }
    @FXML
	public void logoutClick(ActionEvent event) {
		try {
			Stage stage = new Stage();
			Window window = header.getScene().getWindow();
			if (window instanceof Stage) {
				((Stage) window).close();
			}
			Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
			stage.setTitle("Library Management System");
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
         

//	getting Id and Authorization role from login form
    public void initData(String username, String Rolename) 
    {
		lblUserId.setText(username);
		lblLoginAs.setText(Rolename);	
	}

    public void RoleFuction(User user)
    {
		if (user.getAuthorization().equals(Auth.ADMIN))
		{
			butionsControl.getChildren().remove(btnCheckoutBook);
			butionsControl.getChildren().remove(btnPrintCheckoutRecord);
			butionsControl.getChildren().remove(btnSearchBook);
		}
		else if (user.getAuthorization().equals(Auth.LIBRARIAN))
		{
			butionsControl.getChildren().remove(btnAddMember);
			butionsControl.getChildren().remove(btnAddBook);
			butionsControl.getChildren().remove(btnAddBookCopy);
		}	
	}
}
