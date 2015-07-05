package view;
import java.util.Scanner;
import java.sql.Date;

import dto.User;
import process.Process;
import process.Validation;
public class AdminMenu {
	
	public String displayAminPage() throws Exception{
		Scanner in=new Scanner(System.in);
		User user=new User();
		Process pro=new Process();
		System.out.println();
		System.out.println("+=========================>} USER Management {<===============================+");
		System.out.println("|                                                                             |");
		System.out.println("|                        HM)Home  X)Exit H)Help                               |");
		System.out.println("|                                                                             |");
		System.out.println("| A)Add User| V)Views Users| U)Update User| D)Delete User| DE)Deactived User  |");
		System.out.println("|                                                                             |");
		System.out.println("|_____________________________________________________________________________|");
		System.out.print("\n-> Choose: ");
		return in.next().toUpperCase();
	}
	
	public void displayLoginMenu(User user){
		Scanner in=new Scanner(System.in);
		//Process pro = new Process();
		System.out.println("========================>} Login Pages {<===========================");
		System.out.print("Username: ");
		user.setUsername(new Validation().UsernameValidate(in.next()));
		System.out.print("Password: ");
		user.setPassword(new Validation().PassEncrypt(in.next()));
	}
	
}
