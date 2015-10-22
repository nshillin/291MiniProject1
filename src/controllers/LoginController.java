package controllers;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;

import models.User;
import views.LoginView;
import views.MainView;
import views.RegisterView;

public class LoginController {

	public static Point position = new Point(0, 0);
	public static Point size = new Point(450, 300);
	
	public static void main(String[] args) {
		MainView.main(args);
	}
	
	public static void loginView(Shell shell) {
		position = shell.getLocation();
		size = shell.getSize();
		shell.setVisible(false);
		LoginView.main(null);
	}
	
	public static void registerView(Shell shell) {
		position = shell.getLocation();
		size = shell.getSize();
		MainView.shell.close();
		RegisterView.main(null);
	}
	
	public static void back(Shell shell) {
		position = shell.getLocation();
		size = shell.getSize();
		shell.close();
		MainView.main(null);
	}
	
	public static void login(String username, String password) {
		User.setUser(username);
		if (QueryHandler.authenticateUser(password)) {
			QueryHandler.addUser(username, password);
		}
		else {
			RegisterView.errorMessage("Username already exists.");
		}
	}
	
	public static void register(String username, String password) {
		if (QueryHandler.isUsername()) {
			QueryHandler.addUser(username, password);
		}
		else {
			RegisterView.errorMessage("Username already exists.");
		}
	}

}
