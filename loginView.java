import java.util.*;
import java.io.*;

public class loginView {
	
	private static void main(String args[]) {
       		clearScreen();
		System.out.print("Username: "); 
	}	

	public void clearScreen() {
                if (os.contains("Windows")) {
                        Runtime.getRuntime().exec("cls");
                }
                else
                {
                        Runtime.getRuntime().exec("clear");
                }

	} 		
}
