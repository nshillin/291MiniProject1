package views;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import controllers.LoginController;
import models.User;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class MenuView {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MenuView window = new MenuView();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(LoginController.size);
		shell.setLocation(LoginController.position);
		shell.setText("Main Menu");
		
		Button searchButton = new Button(shell, SWT.NONE);
		searchButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LoginController.searchForFlightsView(shell);
			}
		});
		searchButton.setBounds(127, 93, 192, 28);
		searchButton.setText("Search Flights");
		
		Button bookingsButton = new Button(shell, SWT.NONE);
		bookingsButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LoginController.bookingView(shell);
			}
		});
		bookingsButton.setBounds(127, 127, 192, 28);
		bookingsButton.setText("My Bookings");
		
		Button logoutButton = new Button(shell, SWT.NONE);
		logoutButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LoginController.logout(shell);
			}
		});
		logoutButton.setText("Logout");
		logoutButton.setBounds(0, 0, 77, 28);
		
		if (User.isAirlineAgent()) {
			Button recDepartureButton = new Button(shell, SWT.NONE);
			recDepartureButton.setText("Record Arrival or Departure");
			recDepartureButton.setBounds(110, 161, 224, 28);
			recDepartureButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					LoginController.arrDepView(shell);
				}
			});
		}
	}
}
