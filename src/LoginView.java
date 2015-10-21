import java.util.*;
import java.io.*;

public class LoginView {
	
    public LoginView() {
        
    }
    
	private static void main(String args[]) {
        LoginView loginView = new LoginView();
        loginView.clearScreen();
		System.out.print("Username: ");
	}	

	public void clearScreen() {
        try
        {
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
