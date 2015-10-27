package views;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import controllers.LoginController;
import controllers.QueryHandler;
import models.Booking;
import models.Sch_Flight;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;


import java.util.LinkedList;

import org.eclipse.swt.widgets.Label;

// Displays all of user's bookings
public class BookingView {

	protected Shell shell;
	private Table table;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BookingView window = new BookingView();
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
		shell.setText("My Bookings");
		
		Button backButton = new Button(shell, SWT.NONE);
		backButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LoginController.menuView(shell);
			}
		});
		backButton.setText("back");
		backButton.setBounds(0, 0, 66, 28);
		
		
		final LinkedList<Booking> bookings = QueryHandler.getBookings();
		
		final Combo bookingCombo = new Combo(shell, SWT.READ_ONLY);
		// the ticket number, the passenger name, the departure date and the price
		for (int i = 0; i < bookings.size(); i++) {
			Float price = bookings.get(i).getPrice();
			bookingCombo.add("Flight: " + bookings.get(i).getFlightNumber() + 
					", Passenger: " + bookings.get(i).getpName() +
					", Departure: " + bookings.get(i).getDepDate().toString() + 
					", Price: " + price.toString());
		}
		bookingCombo.select(0);
		bookingCombo.setBounds(58, 106, 355, 22);
		
		Label lblBookings = new Label(shell, SWT.NONE);
		lblBookings.setAlignment(SWT.CENTER);
		lblBookings.setBounds(58, 78, 355, 22);
		lblBookings.setText("Bookings:");
		
		Button btnMoreInfo = new Button(shell, SWT.NONE);
		btnMoreInfo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Integer position = table.getSelectionIndex();
				Booking booking  = bookings.get(position);
				LoginController.indBookingView(shell, booking);
			}
		});
		btnMoreInfo.setBounds(58, 134, 355, 28);
		btnMoreInfo.setText("More Info");
		
		if (bookings == null || bookings.size() == 0) {
			lblBookings.setText("You have no bookings");
			btnMoreInfo.setEnabled(false);
		}
	}
}
