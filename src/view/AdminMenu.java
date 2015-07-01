package view;
import java.util.Scanner;
import java.sql.Date;

import dto.User;
import process.Process;
public class AdminMenu {
	public static void main(String[] args) throws Exception {
		Scanner in=new Scanner(System.in);
		User user=new User();
		Process pro=new Process();
		//System.out.println((user.getCreateDate().toString()));
		
		System.out.println("1.AddUser\n2.ViewUser\n3.UpdateUser\n4.DeleteUser\n5.ViewAllUsers");
		System.out.println("==>"); int opt=in.nextInt();
		pro.respondProcess(opt);
	}
}
