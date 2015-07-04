package view;

import process.Process;

public class Display {
	
	public static void main(String[] args) {
		Process pro = new Process();
		try {
			pro.userControl();
//			pro.respondProcess();
			//new Process().userControl();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
