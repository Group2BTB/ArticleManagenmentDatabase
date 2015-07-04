package process;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.Scanner;


public class Validation {

	public String[] spliteStr(String str) {
		while (true) {
			if (str.contains("  ")) {
				str = str.replace("  ", " ");
			} else
				break;
		}
		return str.split(" ");
	}

	// to encrypted password into database

	public StringBuilder PassEncrypt(String Password) {
		String GeneratePass = null;
		StringBuilder sb = null;

		try {
			// create MessageDigest for instance md5
			MessageDigest md = MessageDigest.getInstance("md5");
			md.update(Password.getBytes());
			byte[] bytes = md.digest();
			sb = new StringBuilder();
			// loop for covert the password encrypt into hexadecimal numberal
			// system
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
						.substring(1));
			}
			// Generate password to String
			GeneratePass = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sb;
	}

	// Email Validation method
	public String EmailValidate(String email) {

		Scanner sc = new Scanner(System.in);
		while (true) {
			// check character condition
			if (email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
				return email;
			} else {
				System.out.println("Input invalid email, Please input again!");
				// Reinput email
				System.out.print("Input email: ");
				return EmailValidate(sc.next());
			}
		}
	}

	// fullname validation method
	public String fullnameValidate(String fullName) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			// check character condition
			if (fullName.matches("[A-Za-z]+")) {
				return fullName;
			} else {
				System.out.println("Input only charater, Please input again!");
				// Reinput fullname
				System.out.print("Input Fullname: ");
				return fullnameValidate(sc.next());
			}

		}

	}

	// Username validation method
	public String UsernameValidate(String username) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			// check character condition
			if (username.matches("[A-Za-z]+")) {
				
				return username;

			} else {
				System.out.println("Input only charater, Please input again!");
				// Reinput username 
				System.out.print("Input Username: ");
				return UsernameValidate(sc.next());
			}

		}
	}
}
