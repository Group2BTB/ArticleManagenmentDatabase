package process;

import java.util.ArrayList;

import dao.UserDAO;
import dto.Article;
import view.AdminMenu;
import view.UserView;

public class UserController {
	public String optionControl(){
		String option = null;
		try {
			option = new AdminMenu().displayAminPage();
			if(option.matches("A")  ||option.matches("V") ||option.matches("U")  ||option.matches("D")  ||option.matches("DE")   ){
				new AdminMenu().displayAminPage();	new AdminMenu().displayAminPage();	
				return option;
			}else{
				System.out.println("Syntax Error!!!!");
				new AdminMenu().displayAminPage();	new AdminMenu().displayAminPage();	
				return option;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void userDisplayControl(){
		UserDAO dao = new UserDAO();
		UserView view = new UserView();
	}
}
