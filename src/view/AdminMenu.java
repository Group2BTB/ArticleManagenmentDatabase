package view;
import java.util.Scanner;
import java.sql.Date;

import dto.User;
import process.Process;
public class AdminMenu {
	
	public String DisplayAminPage() throws Exception{
		Scanner in=new Scanner(System.in);
		User user=new User();
		Process pro=new Process();
		//System.out.println("1.AddUser\n2.ViewUser\n3.UpdateUser\n4.DeleteUser\n5.ViewAllUsers");
		System.out.println();
		System.out.println("+=========================>} USER Management {<===============================+");
		System.out.println("|                                                                             |");
		System.out.println("|                        HM)Home  X)Exit H)Help                               |");
		System.out.println("|                                                                             |");
		System.out.println("|       A)Add User | V)Views Users | U)Update User | D)Delete User            |");
		System.out.println("|                                                                             |");
		System.out.println("|_____________________________________________________________________________|");
		System.out.print("\n*Choose: ");

		System.out.println("==>"); 
		return in.next();

	}
}
