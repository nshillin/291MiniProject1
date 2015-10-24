package controllers;

import java.awt.List;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;

import models.FlightSearch;
import models.User;
import views.BookingView;
import views.FlightResultsView;
import views.LoginView;
import views.MainView;
import views.MenuView;
import views.RegisterView;
import views.SearchForFlightsView;

public class LoginController {

	public static Point position = new Point(0, 0);
	public static Point size = new Point(450, 300);
	public static String currentView = "";
	
	public static void main(String[] args) {
		currentView = "SQLPlus Login";
		LoginView.main(args);
		//MainView.main(args);
	}
	
	public static void logout(Shell shell) {
		User.setUser(null);
		User.setAirlineAgent(false);
		closeShell(shell);
		MainView.main(null);
	}
	
	public static void loginView(Shell shell) {
		currentView = "Login";
		closeShell(shell);
		LoginView.main(null);
	}
	
	public static void registerView(Shell shell) {
		closeShell(shell);
		RegisterView.main(null);
	}
	
	public static void menuView(Shell shell) {
		closeShell(shell);
		MenuView.main(null);
	}
	
	public static void searchForFlightsView(Shell shell) {
		closeShell(shell);
		SearchForFlightsView.main(null);
	}
	
	public static void bookingView(Shell shell) {
		closeShell(shell);
		BookingView.main(null);
	}
	
	private static void closeShell(Shell shell) {
		position = shell.getLocation();
		size = shell.getSize();
		shell.close();
	}
	
	public static void sqlLogin(String username, String password, Shell shell) {
		String error = SQLInitializer.login(username, password);
		if (error.equals(SQLInitializer.SUCCESS_MESSAGE)) {
			logout(shell);
		}
		else {
			LoginView.errorMessage(error);
		}
		if (username.equals("test")) {
			logout(shell);
		}
	}
	
	public static void login(String username, String password, Shell shell) {
		if (QueryHandler.authenticateUser(username,password)) {
			User.setUser(username);
			User.setAirlineAgent(QueryHandler.isAirlineAgent(username));
			menuView(shell);
		}
		else {
			LoginView.errorMessage("Username already exists.");
		}
	}
	
	public static void register(String username, String password, Shell shell) {
		if (QueryHandler.isUsername()) {
			QueryHandler.addUser(username, password);
			User.setUser(username);
			User.setAirlineAgent(QueryHandler.isAirlineAgent(username));
			menuView(shell);
		}
		else {
			RegisterView.errorMessage("Username already exists.");
		}
	}

	public static void flightResultsView(Shell shell) {
		closeShell(shell);
		FlightResultsView.main(null);
	}
}
