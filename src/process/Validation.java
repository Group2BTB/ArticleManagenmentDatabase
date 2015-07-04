package process;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Validation {
	public String[] checkNull(String str) {

		String[] value = { "Error", "" };
		String subStr;
		String subStr2;
		String[] keys = { "SB","B","F", "P", "N", "L", "S", "A", "E", "D","CA", "H", "X",
				"HM", "SO" };

		if (str == null)
			return value;

		str = str.trim();

		if (str.equalsIgnoreCase(""))
			return value;
		else {
			subStr = str.substring(0, 1);
			subStr2 = str.substring(1).trim();
		}

		if (str.trim().length() > 1) {

			if (str.substring(0, 2).trim().length() > 1) {
				if (str.substring(0, 2).equalsIgnoreCase("RD")) {

					String subStr3 = str.substring(0, 2);
					String subStr4 = str.substring(2).trim();

					if (subStr4.equalsIgnoreCase(""))
						return value;
					else if (subStr4.matches(".*"
							+ str.trim().equalsIgnoreCase(str) + "*"))
						return value;
					else {

						value[0] = subStr3;
						value[1] = subStr4;
						return value;
					}
				}
				
			}

			if (subStr.equalsIgnoreCase("G") || subStr.equalsIgnoreCase("R")
					|| subStr.equalsIgnoreCase("E")
					|| subStr.equalsIgnoreCase("D")) {

				if (subStr2.matches(".*"+str.trim().equalsIgnoreCase(str)+"*")) {
					return value;
				} else {
					value[0] = subStr;
					value[1] = subStr2;
					return value;
				}
			}

		}

		if (str.trim().length() > 1) {

			subStr = str.substring(0, 2);
			subStr2 = str.substring(2).trim();
		}

		byte t = 0;

		for (int i = 0; i < keys.length; i++) {

			if (subStr.equalsIgnoreCase(keys[i])) {
				t = (byte) 1;
				break;
			}
		}

		if (t == 1) {

			if (subStr2.length() == 0) {
				value[0] = subStr;
				return value;
			}
		}

		return value;
	}	
	
	public String[] spliteStr(String str) {
		while(true){
			if(str.contains("  ")){
				str = str.replace("  ", " ");
			}else
				break;
		}
		return str.split(" ");
	}
	
	//to encrypted password into database

	public StringBuilder PassEncrypt(String Password){
		String GeneratePass = null;
		StringBuilder sb = null;
		
		try {
			// create MessageDigest for instance md5
			MessageDigest md=MessageDigest.getInstance("md5");
			md.update(Password.getBytes());
			byte[] bytes=md.digest();
			sb=new StringBuilder();
			// loop for covert the password encrypt into hexadecimal numberal system
			for(int i=0;i<bytes.length;i++){
				sb.append(Integer.toString((bytes[i]&0xff)+ 0x100,16).substring(1));
			}  
			// Generate password to String
			GeneratePass=sb.toString();		
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sb;
	}
	
	//to validate email
	public String EmailValidate(String email){
		String emString;
		boolean b=true;
		Scanner name = new Scanner(System.in);
		 do {				 
			 	// check email condition  	 	
			 	if (!b ) {
				 email = name.nextLine();
				}		 	
		        String email_regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		        emString = email;
		        b = emString.matches(email_regex);

		        System.out.println("Your email was invalid, Please input again!");

		        //System.out.println("Your email < " + emString + " > was " + b);

		        
		    } while (!b);
		    return emString;

	}
	public void UserValidate(){
			
	}
}
