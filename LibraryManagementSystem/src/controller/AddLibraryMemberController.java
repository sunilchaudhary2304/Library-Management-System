

package controller;

/****************************************LMS*****************************************************************************
 * @author      : Group 9 
 * Date         : 10/16/2016
 * Description  : To add the Library Members to the System @authentication: Admin. @Methods : onAddMemberClick(),saveNewMeber().
 * 				: onAddMemberClick(): Checks for validation, adds members and perform data are already exist or not. 
 * Version      : 1.00  
 * 
 ************************************************************************************************************************/

import java.net.URL;
import java.util.ResourceBundle;

import business.Address;
import business.LibraryMember;
import dataaccess.DataAccessFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;


public class AddLibraryMemberController implements Initializable 
{
	@FXML private TextField txtMemberId;
	@FXML private TextField txtLastName;
	@FXML private TextField txtPhoneNumber;
	@FXML private TextField txtFirstName;
	@FXML private TextField txtStreet;
	@FXML private TextField txtCity;
	@FXML private TextField txtZip;
	@FXML private TextField txtState;
	@FXML private Label     lblMessageLabMem;

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
	}
	
	//fires on AddMember button click
	@FXML public void onAddMemberClick(ActionEvent event) 
	{
		if(validateLabMemer())
		{
			try
			{		
				//lblMessageLabMem.setText("test");
				Address address = new Address(txtStreet.getText(), txtCity.getText(), txtState.getText(), txtZip.getText());
				LibraryMember libraryMember = new LibraryMember(txtFirstName.getText(), txtLastName.getText(), txtPhoneNumber.getText(), address, txtMemberId.getText());	
				
				this.saveNewMember(libraryMember);
				
				popupMessage("Member Added Successfully!");
				
				clearMemberData();
			    lblMessageLabMem.setText("");
				}
				catch(Exception ex)
				{
					popupMessage(ex.getMessage());
				}
		   }
		
	}
	
	public void clearMemberData()
	{
		txtFirstName.clear();
		txtLastName.clear();
		txtMemberId.clear();
		txtStreet.clear();
		txtCity.clear();
		txtState.clear();
		txtZip.clear();
		txtPhoneNumber.clear();	
	}
	
	
	public void popupMessage(String message)
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Alert");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	DataAccessFacade da = new DataAccessFacade();

	public void saveNewMember(LibraryMember member)
	{
		//check if member of same id is present or not
		LibraryMember exitingMember=findMember(member.getMemberID());
		//if doesnot present then save the member to dataAccess
		if(exitingMember == null)
		{
			da.saveLibraryMember(member);		    
		}
		else
		{
			throw new IllegalArgumentException("A library member with memberId = " + member.getMemberID() + " already exists!");	
		}
	}
	
	public LibraryMember findMember(String memberId)
	{
		LibraryMember member = da.readLibraryMember(memberId);		
		return member;
	}
	
	// validation for the library member is here......
	private boolean validateLabMemer()
	{
		if (txtMemberId.getText().equals("")) {
			lblMessageLabMem.setText("Member ID: Value is required.");
			txtMemberId.requestFocus();
			return false;
		}
		
		if (txtFirstName.getText().equals("")) {
			lblMessageLabMem.setText("First Name: Value is required.");
			txtFirstName.requestFocus();
			return false;
		}
		if (txtLastName.getText().equals("")) {
			lblMessageLabMem.setText("Last Name: Value is required.");
			txtLastName.requestFocus();
			return false;
		}		
		
		if (txtStreet.getText().equals("")) {
			lblMessageLabMem.setText("Address Sreet: Value is required.");
			txtStreet.requestFocus();
			return false;
		}
		if (txtCity.getText().equals("")) {
			lblMessageLabMem.setText("Address City: Value is required.");
			txtCity.requestFocus();
			return false;
		}
		if (txtState.getText().equals("")) {
			lblMessageLabMem.setText("Address State: Value is required.");
			txtState.requestFocus();
			return false;
		}
		if (txtZip.getText().equals("")) {
			lblMessageLabMem.setText("Address Zip: Value is required.");
			txtZip.requestFocus();
			return false;
		}
		if (!txtZip.getText().matches("[0-9]*")) {
			lblMessageLabMem.setText("Zip Code: Should be Number.");
			txtZip.requestFocus();
			return false;
		}
		if ((txtZip.getText().length()) != 5) {
			lblMessageLabMem.setText("Zip Code: should be 5 digit.");
			txtZip.requestFocus();
			return false;
		}
		
		if (txtPhoneNumber.getText().equals("")) {
			lblMessageLabMem.setText("Phone Number: Value is required.");
			txtPhoneNumber.requestFocus();
			return false;
		}
		return true;
	}

}
	
