package main;

import connection.Connect;

public class Main {
	
	Connect con = Connect.getInstance();
	
	public Main() {
		new Login();
	}

	public static void main(String[] args) {
		new Main();
	}
	
}
