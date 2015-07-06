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
		System.out.println("|                        HM)Home  X)Exit H)Help                               |");		
		System.out.println("|           A)Add User| V)Views Users| U)Update User| D)Delete User           |");		
		System.out.println("|                L)Logout  DE)Deactived User | AC) Active User                |");
		System.out.println("|_____________________________________________________________________________|");
		System.out.print("\n-> Choose: ");
		return in.next().toUpperCase();
	}
	
	public void displayLoginMenu(User user){
		Scanner in=new Scanner(System.in);
		//Process pro = new Process();
		System.out.println("=============================>} Login Pages {<================================");
		System.out.print("Username: ");
		user.setUsername(new Validation().UsernameValidate(in.next()));
		System.out.print("Password: ");
		user.setPassword(new Validation().PassEncrypt(in.next()));
	}
	
	public void helpe(){
		System.out.println("+=================================>} HELP {<==================================+");
		System.out.println("+-----------------------------------------------------------------------------+");
		System.out.println("| 1.  HM)Home : Display homepage. (*Choose: HM)                               |");
		System.out.println("| 2.  A)Add User : Add new user (*Choose: A)                                  |");
		System.out.println("| 3.  V)View User : View user by Id. (*Choose: V)                             |");
		System.out.println("| 4.  U)Edit : Edit User by ID. (*Choose: U)                                  |");
		System.out.println("| 5.  D)Delete : Delete User by ID. (*Choose: D)                              |");
		System.out.println("| 6.  DE)Deactive User by ID(*Choose: DE)                                     |");
		System.out.println("| 7.  H)Help : Guiline application. (*Choose: H)                              |");
		System.out.println("| 8.  X)Exit : Close Program (*Choose: X)                                     |");
		System.out.println("| 9.  AC)Active : Active User by ID (*Choose: AC)                             |");
		System.out.println("+-----------------------------------------------------------------------------+");
	}
	
}

