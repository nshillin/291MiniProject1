package views;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import controllers.LoginController;

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
		searchButton.setBounds(128, 64, 192, 28);
		searchButton.setText("Search Flights");
		
		Button bookingsButton = new Button(shell, SWT.NONE);
		bookingsButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LoginController.bookingView(shell);
			}
		});
		bookingsButton.setBounds(128, 98, 192, 28);
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
		
		Button recDepartureButton = new Button(shell, SWT.NONE);
		recDepartureButton.setText("Record Departure");
		recDepartureButton.setBounds(128, 132, 192, 28);
		
		Button recordArrivalButton = new Button(shell, SWT.NONE);
		recordArrivalButton.setText("Record Arrival");
		recordArrivalButton.setBounds(128, 167, 192, 28);

	}
}
