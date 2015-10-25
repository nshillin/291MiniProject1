package views;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import controllers.LoginController;
import controllers.QueryHandler;
import models.Booking;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import java.sql.Date;
import java.util.LinkedList;

import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.TableViewer;

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

	    for (int i=0; i<titles.length; i++) {
	      table.getColumn (i).pack ();
	    }     
	    	    
	    table.setSize(table.computeSize(SWT.DEFAULT, 330));

	}
}
