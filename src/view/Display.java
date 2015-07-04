package view;

import process.Process;

public class Display {
	
	public static void main(String[] args) {
		try {
			new Process().respondProcess();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
