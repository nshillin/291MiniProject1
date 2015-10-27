package views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import controllers.LoginController;
import controllers.QueryHandler;
import models.Booking;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;

public class IndividualBookingView {

	protected Shell shell;
	public static Booking booking;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			IndividualBookingView window = new IndividualBookingView();
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
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(LoginController.size);
		shell.setLocation(LoginController.position);
		shell.setText("My Booking");
		
		Button backButton = new Button(shell, SWT.NONE);
		backButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LoginController.bookingView(shell);
			}
		});
		backButton.setText("back");
		backButton.setBounds(0, 0, 66, 28);
		
		Button btnCancelBooking = new Button(shell, SWT.NONE);
		btnCancelBooking.setBounds(143, 240, 163, 28);
		btnCancelBooking.setText("Cancel Booking");
		btnCancelBooking.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				QueryHandler.removeBooking(booking, shell);
			}
		});
		
		Label lblTicketNumber = new Label(shell, SWT.NONE);
		lblTicketNumber.setAlignment(SWT.RIGHT);
		lblTicketNumber.setBounds(117, 27, 108, 20);
		lblTicketNumber.setText("Passenger:");
		
		Label lblFlightNumber = new Label(shell, SWT.NONE);
		lblFlightNumber.setAlignment(SWT.RIGHT);
		lblFlightNumber.setText("Flight Number:");
		lblFlightNumber.setBounds(117, 81, 108, 20);
		
		Label lblFare = new Label(shell, SWT.NONE);
		lblFare.setAlignment(SWT.RIGHT);
		lblFare.setText("Fare:");
		lblFare.setBounds(117, 133, 108, 20);
		
		Label lblSeat = new Label(shell, SWT.NONE);
		lblSeat.setAlignment(SWT.RIGHT);
		lblSeat.setText("Seat:");
		lblSeat.setBounds(117, 159, 108, 20);
		
		Label lblPrice = new Label(shell, SWT.NONE);
		lblPrice.setAlignment(SWT.RIGHT);
		lblPrice.setText("Price:");
		lblPrice.setBounds(117, 185, 108, 20);
		
		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setText("Ticket Number:");
		label_5.setAlignment(SWT.RIGHT);
		label_5.setBounds(117, 55, 108, 20);
		
		Label lblDepartureDate = new Label(shell, SWT.NONE);
		lblDepartureDate.setText("Departure Date:");
		lblDepartureDate.setAlignment(SWT.RIGHT);
		lblDepartureDate.setBounds(117, 107, 108, 20);
		
		try {
			Label nameLabel = new Label(shell, SWT.NONE);
			nameLabel.setText(booking.getpName());
			nameLabel.setBounds(231, 27, 108, 20);
			
			Integer ticket = booking.getTicketNumber();
			Label ticketnumberLabel = new Label(shell, SWT.NONE);
			ticketnumberLabel.setText(ticket.toString());
			ticketnumberLabel.setBounds(231, 55, 108, 20);
			
			Label flighnumberLabel = new Label(shell, SWT.NONE);
			flighnumberLabel.setText(booking.getFlightNumber());
			flighnumberLabel.setBounds(231, 81, 108, 20);
			
			Label fareLabel = new Label(shell, SWT.NONE);
			fareLabel.setText(booking.getFare());
			fareLabel.setBounds(231, 133, 108, 20);
			
			Label seatLabel = new Label(shell, SWT.NONE);
			seatLabel.setText(booking.getSeat());
			seatLabel.setBounds(231, 159, 108, 20);
			
			Float price = booking.getPrice();
			Label priceLabel = new Label(shell, SWT.NONE);
			priceLabel.setText(price.toString());
			priceLabel.setBounds(231, 185, 108, 20);
			
			Label dateLabel = new Label(shell, SWT.NONE);
			dateLabel.setText(booking.getDepDate().toString());
			dateLabel.setBounds(231, 107, 108, 20);
		} 
		catch (Exception e) {
			
		}
		//tno, flightno, fare, dep_date, seat
	}
}
