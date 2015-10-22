package views;

import java.io.Console;

public class LoginView {
	
	public static int LOGIN = 0;
	public static int REGISTER = 1;
	
	public int loginOrRegister() {
		// Ask user if login or register
		return LOGIN;
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
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            //  Handle any exceptions.
        }
	} 
	
}
