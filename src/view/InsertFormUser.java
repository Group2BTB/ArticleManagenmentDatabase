package view;

import java.util.Scanner;

import dto.User;

public class InsertFormUser {
	public InsertFormUser(User udto){
		Scanner in=new Scanner(System.in);
		System.out.println("Input Fullname: ");udto.setFullName(in.next());
		System.out.println("Input Email: ");udto.setEmail(in.next());
		System.out.println("Input Username: ");udto.setUsername(in.next());
		System.out.println("Input password: ");udto.setPassword(in.next());
		System.out.println("Input UserType: ");String userType=in.next();
	}
}
