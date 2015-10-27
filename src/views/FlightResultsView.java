package views;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import controllers.LoginController;
import models.Flight;
import models.FlightResult;
import models.FlightSearch;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class FlightResultsView {

	protected Shell shell;
	private static Table table;
	private static FlightSearch currentFlightSearch;
	private Button sortByConnections;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			FlightResultsView window = new FlightResultsView();
			currentFlightSearch = FlightSearch.getInstance();
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
		shell.setSize(450, 300);
		shell.setText("Flights");
		shell.setSize(LoginController.size);
		shell.setLocation(LoginController.position);
		
		final CheckboxTableViewer flightResults = CheckboxTableViewer.newCheckList(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table = flightResults.getTable();
		table.setBounds(10, 43, 430, 158);
		FlightSearch.FindSearchedFlights(table);
		Label lblFlightsFound = new Label(shell, SWT.NONE);
		lblFlightsFound.setBounds(10, 23, 92, 14);
		lblFlightsFound.setText("Flights Found:");
		
		Button btnBookFlight = new Button(shell, SWT.NONE);
		btnBookFlight.setBounds(20, 207, 140, 28);
		btnBookFlight.setText("Book Flight");
		btnBookFlight.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				int[] indices = table.getSelectionIndices();
				if (indices.length == 1) {
					Flight flightToBook = FlightResult.getInstance().getFlightResults().get(indices[0]);
					LoginController.bookingCreationView(shell, flightToBook);
				}
			}
		    });
		
		sortByConnections = new Button(shell, SWT.CHECK);
		sortByConnections.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				table.setVisible(false);
				table.removeAll();
				Boolean sortBy = sortByConnections.getEnabled();
				currentFlightSearch.setSortByConnections(sortBy);
				FlightSearch.FindSearchedFlights(table);
				table.setVisible(true);
			}
		});
		sortByConnections.setBounds(293, 207, 147, 28);
		sortByConnections.setText("Sort By Connections");

		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(84, 23, 350, 14);
		lblNewLabel.setText("src-dst-dep_date-arr_time- flightnos - NoOfConnections- layovertime - price");
		
		Button backToFlightSearch = new Button(shell, SWT.NONE);
		backToFlightSearch.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				LoginController.searchForFlightsView(shell);
			}
		});
		backToFlightSearch.setBounds(166, 229, 134, 32);
		backToFlightSearch.setText("Back To Search");
	}
}
