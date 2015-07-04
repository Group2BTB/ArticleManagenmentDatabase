package view;

import java.util.ArrayList;
import java.util.Scanner;

import process.Process;
import process.Validation;
import dao.UserDAO;
import dto.User;
public class UserView {
	
	//default constructor
	public UserView() {
		// TODO Auto-generated constructor stub
		
		
	}
	
	//to display the insert user form to complete
	public User InsertFormUser(User udto) {
		Scanner in = new Scanner(System.in);
		System.out.println("Input Fullname: ");
		udto.setFullName(new Validation().fullnameValidate(in.next()));
		System.out.println("Input Email: ");
		udto.setEmail(new Validation().EmailValidate(in.next()));
		System.out.println("Input Username: ");
		udto.setUsername(new Validation().UsernameValidate(in.next()));
		System.out.println("Input password: ");
		udto.setPassword(new Validation().PassEncrypt(in.next()));
		System.out.println("Input UserType: ");
		udto.setType(in.next());
		
		return udto;
	}

	public User DeleteUser(User udto){		
		Scanner in=new Scanner(System.in);
		System.out.print("Input UserID you want to delete : ");	
		int id = in.nextInt();
		System.out.print("Are you sure you want to delete this User??? : [y/n] ==> ");	
		String yesNo = in.next();
		if(yesNo.matches("y")){
		udto.setId(id);
		}
		else{
			System.out.println("Delete is cancelled");
		}
		return udto;
	}	
	
	public User deActiveUser(User udto){		
		Scanner in=new Scanner(System.in);
		System.out.print("Input UserID you want to Deactive : ");			
		int id = in.nextInt();
		System.out.print("Are you sure you want to Deactive this User??? : [y/n] ==> ");	
		String yesNo = in.next().toLowerCase();
		if(yesNo.matches("y")){
		 udto.setId(id);
		 System.out.println("This User is Deactived!!!");
		}
		else{
			System.out.println("Deactive is cancelled");
		}
		return udto;
	}	
	
	//to display input user id to update and choice to update
	public int UpdateUser(User udto){
		int inputChoice;// declare a variable to get input choice method
		Scanner in=new Scanner(System.in);
		System.out.println("Input UserID you want to update : ");
		udto.setId(in.nextInt());
		System.out.println("what do you want to update? : [ 1.Fullname | 2.Username | 3.Password | 4.Email | 5.All ] ");
		
		inputChoice=in.nextInt();
		//check on input choice method
		switch (inputChoice) {
		case 1://if inputChoice == 1
			System.out.print("Input new Fullname: ");
			udto.setFullName(in.next());
			break;
		case 2://if inputChoice == 2
			System.out.print("Input new Username: ");
			udto.setUsername(in.next());
			break;
		case 3://if inputChoice == 3
			System.out.print("Input new Password: ");
			udto.setPassword(new Validation().PassEncrypt(in.next()));
			break;
		case 4://if inputChoice == 4
			System.out.print("Input new Email: ");
			udto.setEmail(in.next());
			break;
		case 5://if inputChoice == 5
			System.out.print("Input new Fullname: ");
			udto.setFullName(in.next());
			
			System.out.print("Input new Username: ");
			udto.setUsername(in.next());
			
			System.out.print("Input new Password: ");
			udto.setPassword(new Validation().PassEncrypt(in.next()));
			
			System.out.print("Input new Email: ");
			udto.setEmail(in.next());
			break;			
			default:
			break;
		}			
		return inputChoice;// return input choice method
	}	
	
	//to get the input user id from user by keyboard
	public User getUserId(User udto){
		Scanner in=new Scanner(System.in);
		System.out.println("Input UserID you want to View : ");
		udto.setId(in.nextInt());
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
		return udto;
	}
	
	public void displayUserDetail(ArrayList<User> arrList){
		
	}
	
}
