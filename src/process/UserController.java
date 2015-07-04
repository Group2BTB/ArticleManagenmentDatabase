package process;

import view.AdminMenu;

public class UserController {
	public String optionControl(){
		String option = null;
		try {
			
			option = new AdminMenu().displayAminPage();
			if(option != "A" ||option != "V"||option != "U"||option != "D"||option != "DE" ){
				System.out.println("Syntax Error!!!!");
				new AdminMenu().displayAminPage();	new AdminMenu().displayAminPage();	
			}else{
				new AdminMenu().displayAminPage();				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return option;
	}
}
