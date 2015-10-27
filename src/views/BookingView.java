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
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import java.sql.Date;
import java.util.LinkedList;

import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Label;

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
		
		/*
		TableViewer tableViewer = new TableViewer(shell, SWT.SINGLE | SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setBounds(72, 21, 330, 227);
		String[] titles = { "Ticket #", "Passenger", "Dep Date", "Price" };
	    for (int i = 0; i < titles.length; i++) {
	      TableColumn column = new TableColumn(table, SWT.NONE);
	      column.setText(titles[i]);
	    }
	    
	    LinkedList<Booking> bookings = QueryHandler.getBookings();
	    if (bookings == null || bookings.size() == 0) {
	    	Label lblNewLabel = new Label(shell, SWT.NONE);
		    lblNewLabel.setAlignment(SWT.CENTER);
		    lblNewLabel.setBounds(105, 0, 229, 14);
		    lblNewLabel.setText("You have no bookings");
	    }
	    else {
	    	 for (int i = 0; i < bookings.size(); i++) {
	    		 TableItem item = new TableItem(table, SWT.NONE);
	    		 Booking booking = bookings.get(i);
	    		 Integer ticketNumber = booking.getTicketNumber();	      
	    		 Float price = (float) booking.getTicketNumber();
	   	      
	    		item.setText(0, ticketNumber.toString());
	   	      	item.setText(1, booking.getpName());
	   	      	item.setText(2, booking.getDepDate().toString());
	   	      	item.setText(3, "$"+price.toString());
	   	    }
	    }
		TableItem item = new TableItem(table, SWT.NONE);
	/*	item.setText(0, "hello");
		item.setText(1, "hello");
		item.setText(2, "hello");
		item.setText(3, "hello");

	    
	    for (int i=0; i<titles.length; i++) {
	      table.getColumn (i).pack ();
	    }     
	    
	    tableViewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				// TODO Auto-generated method stub
				Integer position = table.getSelectionIndex();
			}
	    });
	    
	    table.setSize(table.computeSize(SWT.DEFAULT, 330));
	    */
	}
}
