package view;
import java.util.Scanner;

import process.Process;
public class AdminMenu {
	public static void main(String[] args) throws Exception {
		Scanner in=new Scanner(System.in);
		Process pro=new Process();
		System.out.println("1.AddUser\n2.ViewUser\n3.UpdateUser\n4.DeleteUser\n5.ViewAllUsers");
		System.out.println("==>"); int opt=in.nextInt();
		pro.respondProcess(opt);
	}
}
