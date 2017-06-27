package controller;
/****************************************LMS*****************************************************************************
 * @author      : Group 9 
 * Date         : 10/16/2016
 * Description  : To perform validation and verification of login user name and Password to access the system. 
 *              : checkLogin(username,pwd) : Takes data through the data access facade then compare the values to the 
 *                Controller value and if yes form loader loads new page and check role also. In case of user and password 
 *                are not match. Controller pops up the error message
 * Version      : 1.00  
 * 
 ************************************************************************************************************************/

import java.io.IOException;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class LoginController
{
//    @FXML private Button btnLogin;
    @FXML private AnchorPane header;
    @FXML private PasswordField txtPassword;
    @FXML private TextField txtUsername;
    
    // fires on Login button click
    @FXML public void onLoginClick(ActionEvent event)
    {
    	String username=txtUsername.getText();
    	String password=txtPassword.getText();    	 	
    	checkLogin(username,password); 
    }

    private void checkLogin(String username, String password) 
    {
    	//checks username and password that matches from the data access
    	DataAccess da = new DataAccessFacade();
    	if (da.readUserMap().get(username)!=null && da.readUserMap().get(username).getPassword().equals(password)) 
    	{
    		try 
    		{   			
    	    	// if username and password is matched then login frame is close and opens main frame
    			Stage stage = new Stage();
    	    	Window window = header.getScene().getWindow();
    	    	if (window instanceof Stage)
    	    	{
    	    		((Stage) window).close();
    	    	}   	   
    	    		
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainFrame.fxml"));	
    			Parent root = loader.load();
    	    		
    	    	// Passing Username and authorization from login window to Mainframe window
    			MainFrameController controller = loader.<MainFrameController> getController();
    			controller.initData(username, da.readUserMap().get(username).getAuthorization().name());
    			controller.RoleFuction(da.readUserMap().get(username));
    				
    			stage.setTitle("Library Management System");
    	    	Scene scene = new Scene(root);
    	    	stage.setScene(scene);
    	    	stage.show();
    	    }
    		catch (IOException e)
    		{
    			e.printStackTrace();
    		}
		}
    	else 
    	{
			txtUsername.setText("");
			txtPassword.setText("");
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Library Management System");
			alert.setHeaderText(null);
			alert.setContentText("Invalid Username or Password !! Please Try Again");
			alert.showAndWait();
		}
		
	}
}

