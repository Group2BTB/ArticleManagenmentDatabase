package view;

import java.util.Scanner;

public class UpdateUserInfo {
	
	Scanner in=new Scanner(System.in);
	public void inputUserID(Scanner in){
		System.out.println("Input UserID you want to update: ");
		int id=in.nextInt();
		updateChoice(id);
	}
	
	public void updateChoice(int id){
		System.out.println("Are you want to update:\t1.Fullname\t2.Username\t3.Password\t4.Email\t5.All");
		int opt=in.nextInt();
		getUpdateChoice(id, opt);
	}
}
