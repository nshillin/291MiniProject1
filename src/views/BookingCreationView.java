package views;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

import controllers.LoginController;
import controllers.QueryHandler;
import models.User;
import models.Flight;

public class BookingCreationView {

	protected Shell shlBookingInfo;
	private Text nameText;
	private Text countryText;
	private Label lblNewLabel_1;
	private Label errLabel;
	
	private Flight flightToBook;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BookingCreationView window = new BookingCreationView();
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
		shlBookingInfo.open();
		shlBookingInfo.layout();
		while (!shlBookingInfo.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlBookingInfo = new Shell();
		shlBookingInfo.setSize(LoginController.size);
		shlBookingInfo.setLocation(LoginController.position);
		shlBookingInfo.setText("Passenger Info");
		
		nameText = new Text(shlBookingInfo, SWT.BORDER);
		nameText.setBounds(178, 63, 140, 21);
		
		countryText = new Text(shlBookingInfo, SWT.BORDER);
		countryText.setBounds(178, 90, 140, 21);
		
		Label lblNewLabel = new Label(shlBookingInfo, SWT.NONE);
		lblNewLabel.setBounds(69, 66, 103, 15);
		lblNewLabel.setText("Passenger Name");
		
		lblNewLabel_1 = new Label(shlBookingInfo, SWT.NONE);
		lblNewLabel_1.setBounds(69, 93, 55, 15);
		lblNewLabel_1.setText("Country");
		
		Button bookButton = new Button(shlBookingInfo, SWT.NONE);
		bookButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				checkInfo();
			}
		    });
		bookButton.setBounds(178, 151, 75, 25);
		bookButton.setText("Book");
		
		errLabel = new Label(shlBookingInfo, SWT.NONE);
		errLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		errLabel.setBounds(324, 66, 88, 15);

	}
	
	private void checkInfo()
	{
		//TODO: Finish coding this
		if (nameText.getText().isEmpty()) {
			errLabel.setText("Required.");
		}
		else if (QueryHandler.isFlightAvailable(flightToBook)) {
			QueryHandler.setPassenger(nameText.getText(), countryText.getText());
			Integer ticketNo = QueryHandler.setTicket(nameText.getText(), flightToBook.getPrice());
		}
	}
}
