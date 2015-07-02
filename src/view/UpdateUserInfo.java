package view;

import java.util.Scanner;

import dto.User;

public class UpdateUserInfo {
	public int inputChoice;
	public void inputID(User udto){
		Scanner in=new Scanner(System.in);
		System.out.println("Input UserID you want to update: ");
		udto.setId(in.nextInt());
		System.out.println("what do you want to update:\t1.Fullname\t2.Username\t3.Password\t4.Email\t5.All");
		inputChoice=in.nextInt();
		System.out.print("Input new Fullname: ");
		udto.setFullName(in.next());
	
	}
	public UpdateUserInfo() {
		
		// TODO Auto-generated constructor stub
	}

}
