package views;

import java.io.Console;

public class LoginView {
	
	public static int NULL = 0;
	public static int LOGIN = 1;
	public static int REGISTER = 2;
	
	public int loginOrRegister() {
		// Ask user if login or register
		Integer request = NULL;
		while (request == NULL) {
			System.out.print("1:Login, 2:Register\n"
					+ ": ");
	        Console co = System.console();
	        String response = co.readLine();
	        try {
	        	request = Integer.parseInt(response);
	        } catch (Exception e) {
	        	request = NULL;
	        }
		}
		return request;
	}
	
	public String getUserName() {
		System.out.print("Username: ");
        Console co = System.console();
        String userName = co.readLine();
		return userName;
	}
	
	public String getPassword() {
        Console co = System.console();
		char[] passwordArray = co.readPassword("Password: ");
        String password = new String(passwordArray);
        return password;
	}
	
	public void clearScreen() {
		final String ANSI_CLS = "\u001b[2J";
        final String ANSI_HOME = "\u001b[H";
        System.out.print(ANSI_CLS+ANSI_HOME);
        System.out.flush();
	} 
	
}
