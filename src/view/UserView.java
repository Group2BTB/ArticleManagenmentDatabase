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
		udto.setFullName(in.next());
		System.out.println("Input Email: ");
		udto.setEmail(in.next());
		System.out.println("Input Username: ");
		udto.setUsername(in.next());
		System.out.println("Input password: ");
		udto.setPassword(in.next());
		System.out.println("Input UserType: ");
		String userType = in.next();
		
		return udto;
	}
	
	public User inputID(User udto){
		int inputChoice;
		Scanner in=new Scanner(System.in);
		System.out.println("Input UserID you want to update: ");
		udto.setId(in.nextInt());
		System.out.println("what do you want to update? : [ 1.Fullname | 2.Username | 3.Password | 4.Email | 5.All ] ");
		inputChoice=in.nextInt();
		System.out.print("Input new Fullname: ");
		udto.setFullName(in.next());
		
		return udto;
	
	}
	
	
}
