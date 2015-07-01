package process;

import java.sql.SQLException;
import java.util.Scanner;

import view.InsertFormUser;
import view.UpdateUserInfo;
import dao.UserDAO;
import dto.User;

public class Process {
	
	public void respondProcess(int opt) throws Exception{
		User udto=new User();
		Scanner in=new Scanner(System.in);
		switch (opt) {
		case 1:
			
			new InsertFormUser(udto);
			new UserDAO().insertView(udto);
			break;
		case 3: 
			new UpdateUserInfo().inputUserID(in);
			updateView(opt);
		default:
			break;
		}
	}
	
}
