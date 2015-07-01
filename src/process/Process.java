package process;

import java.sql.SQLException;

import view.InsertFormUser;
import dao.UserDAO;
import dto.User;

public class Process {
	
	public void respondProcess(int opt) throws Exception{
		switch (opt) {
		case 1:
			User udto=new User();
			new InsertFormUser(udto);
			new UserDAO().insertView(udto);
			break;

		default:
			break;
		}
	}
}
