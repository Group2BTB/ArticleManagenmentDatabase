package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import process.Process;
import process.Validation;
import utilities.DBConnection;
import dto.User;
public class UserView {
	
	//default constructor
	public UserView() {
		
		// TODO Auto-generated constructor stub
	}
	
	//to display the insert user form to complete
	public User InsertFormUser(User udto) {
		Scanner in = new Scanner(System.in);
		System.out.print("Input Fullname: ");
		udto.setFullName(new Validation().fullnameValidate(in.next()));
		System.out.print("Input Email: ");
		udto.setEmail(new Validation().EmailValidate(in.next()));
		System.out.print("Input Username: ");
		udto.setUsername(new Validation().UsernameValidate(in.next()));
		System.out.print("Input password: ");
		udto.setPassword(new Validation().PassEncrypt(in.next()));
		System.out.print("Input UserType: ");
		udto.setType(in.next());
		
		return udto;
	}
	//This function is used for Delete User from Database
	public User DeleteUser(User udto){		
		Scanner in=new Scanner(System.in);
		System.out.print("Input UserID you want to delete : ");	
		try {
			int id = in.nextInt();
			System.out.print("Are you sure you want to delete this User??? : [y/n] ==> ");	
			String yesNo = in.next();
			if(yesNo.matches("y")){
				udto.setId(id);
			System.out.println("\nDelete successfully!!!");
		
			}
			else{
				System.out.println("Delete is cancelled");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("\n *** Input is not valide!!! (Number only) \n");
			new UserView().DeleteUser(udto);
		}
		return udto;
	}	
	//This function is used for Deactive User from Database
	public User deActiveUser(User udto){		
		Scanner in=new Scanner(System.in);
		System.out.print("Input UserID you want to Deactive : ");
		try {
			int id = in.nextInt();
			
			System.out.print("Are you sure you want to Deactive this User??? : [y/n] ==> ");	
			String yesNo = in.next().toLowerCase();
			if(yesNo.matches("y")){
			 udto.setId(id);
			 System.out.println("\nThis User is Deactived!!!");
			}
			else{
				System.out.println("Deactive is cancelled");
			}		
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("\n *** Input is not valide!!! (Number only) \n");
			new UserView().deActiveUser(udto);
		}
		return udto;
	}	
	
	//to display input user id to update and choice to update
	public int UpdateUser(User udto){
		int inputChoice = 0;// declare a variable to get input choice method
		Scanner in=new Scanner(System.in);
		System.out.print("Input UserID you want to update : ");
		
		
		ResultSet rs = null;
		
		try(Connection con = DBConnection.getConnection(); 
				PreparedStatement pre = con.prepareStatement("select * from tbl_user where id=?",ResultSet.CONCUR_UPDATABLE,ResultSet.TYPE_SCROLL_SENSITIVE);
			) {
			
			int id = in.nextInt();
			pre.setInt(1, id);			
			udto.setId(id);			
			
			rs = pre.executeQuery();
			
			if(rs.last() == false)				 
					new UserView().UpdateUser(udto);							
						
			System.out.print("what do you want to update? : [ 1.Fullname | 2.Username | 3.Password | 4.Email ] ");
			
			inputChoice=in.nextInt();
			if(inputChoice!=1 ||inputChoice!=2 ||inputChoice!=3 ||inputChoice!=4 ||inputChoice!=5){
			//check on input choice method
			switch (inputChoice) {
			case 1://if inputChoice == 1
				System.out.print("Input new Fullname: ");
				udto.setFullName(new Validation().fullnameValidate(in.next()));
				break;
			case 2://if inputChoice == 2
				System.out.print("Input new Username: ");
				udto.setUsername(new Validation().fullnameValidate(in.next()));
				break;
			case 3://if inputChoice == 3
				System.out.print("Input new Password: ");
				udto.setPassword(new Validation().PassEncrypt(in.next()));
				break;
			case 4://if inputChoice == 4
				System.out.print("Input new Email: ");
				udto.setEmail(in.next());
				break;				
			default:
					
			}
			
			}else{	
				System.out.println("Syntax Error!!!");
				new UserView().UpdateUser(udto);
				
			}
				

		}catch(InputMismatchException ex){
			
			System.out.println("\n *** Input is not valide!!! (Number only) \n");
			new UserView().UpdateUser(udto);
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("\n *** Input is not valide!!! (Number only) \n");
			new UserView().UpdateUser(udto);
		}
		finally{
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return inputChoice;// return input choice method
		
	}
	
	
	//to get the input user id from user by keyboard
	public User getUserId(User udto){
		Scanner in=new Scanner(System.in);
		System.out.print("Input UserID you want to View : ");
		try {
			udto.setId(in.nextInt());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("\n *** Input in not valide!! (Number only) \n");
			new UserView().getUserId(udto);
		}
		
		return udto;
	}
	
	//to display the user info by id selected
	public User viewUserInfo(User udto){
		System.out.println("User Detail");
		System.out.println("ID\t: " + udto.getId());
		System.out.println("Fullname: " + udto.getFullName());
		System.out.println("Username: " + udto.getUsername());
		System.out.println("Email\t: " + udto.getEmail());
		System.out.println("Usertype: " + udto.getType());
		try {
			new Process().respondProcess();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return udto;
	}
	
}



