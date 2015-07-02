package view;

import java.util.Scanner;
import dto.User;
public class UserView {
	
	public UserView() {
		
		// TODO Auto-generated constructor stub
	}

	public User InsertFormUser(User udto) {
		Scanner in = new Scanner(System.in);
		System.out.println("Input Fullname: ");
		udto.setFullName(in.nextLine());
		System.out.println("Input Email: ");
		udto.setEmail(in.nextLine());
		System.out.println("Input Username: ");
		udto.setUsername(in.nextLine());
		System.out.println("Input password: ");
		udto.setPassword(in.nextLine());
		System.out.println("Input UserType: ");
		String userType = in.nextLine();
		
		return udto;
	}
	
	public int UpdateUser(User udto){
		int inputChoice;
		Scanner in=new Scanner(System.in);
		System.out.println("Input UserID you want to update: ");
		udto.setId(in.nextInt());
		System.out.println("what do you want to update? : [ 1.Fullname | 2.Username | 3.Password | 4.Email | 5.All ] ");
		
		inputChoice=in.nextInt();
		
		switch (inputChoice) {
		case 1:
			System.out.print("Input new Fullname: ");
			udto.setFullName(in.next());
			break;
		case 2:
			System.out.print("Input new Username: ");
			udto.setUsername(in.next());
			break;
		case 3:
			System.out.print("Input new Password: ");
			udto.setPassword(in.next());
			break;
		case 4:
			System.out.print("Input new Email: ");
			udto.setEmail(in.next());
			break;
		case 5:
			System.out.print("Input new Fullname: ");
			udto.setFullName(in.next());
			
			System.out.print("Input new Username: ");
			udto.setUsername(in.next());
			
			System.out.print("Input new Password: ");
			udto.setPassword(in.next());
			
			System.out.print("Input new Email: ");
			udto.setEmail(in.next());
			break;
			
		default:
			break;
		}	
		
		return inputChoice;	
	}	
}
