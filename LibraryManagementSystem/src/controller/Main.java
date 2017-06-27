package controller;

/****************************************LMS*****************************************************************************
 * @author      : Group 9 
 * Date         : 10/16/2016
 * Description  : To perform the Initial start of the system by opening the login view. 
 * Version      : 1.00  
 * 
 ************************************************************************************************************************/
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		//Display Login user interface
		Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
		primaryStage.setTitle("Library Management System");
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
