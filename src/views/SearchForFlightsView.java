package views;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import controllers.LoginController;
import models.FlightSearch;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class SearchForFlightsView {

	protected Shell shell;
	private Text departingCity;
	private Text arrivingCity;
	private Button btnSearchForFlights;
	private Button btnRoundTrip;
	private DateTime dateTime;
	private Label errorMessage;
	private Menu suggestDepartureCity;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SearchForFlightsView window = new SearchForFlightsView();
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
		shell.setText("Search for Flights");
		
		departingCity = new Text(shell, SWT.BORDER);
		departingCity.setBounds(149, 37, 164, 31);
		
		Menu SuggestDepartureCity = new Menu(departingCity);
		departingCity.setMenu(SuggestDepartureCity);
		
		Label lblFlight = new Label(shell, SWT.NONE);
		lblFlight.setBounds(149, 17, 60, 14);
		lblFlight.setText("Going To:");
		
		dateTime = new DateTime(shell, SWT.BORDER);
		dateTime.setBounds(149, 94, 164, 31);
		
		Label lblDepartingBy = new Label(shell, SWT.NONE);
		lblDepartingBy.setBounds(149, 74, 86, 14);
		lblDepartingBy.setText("Departing By:");
		
		Label lblLeavingBy = new Label(shell, SWT.NONE);
		lblLeavingBy.setBounds(149, 131, 86, 14);
		lblLeavingBy.setText("Leaving From:");
		
		arrivingCity = new Text(shell, SWT.BORDER);
		arrivingCity.setBounds(149, 151, 164, 31);
		
		suggestDepartureCity = new Menu(arrivingCity);
		arrivingCity.setMenu(suggestDepartureCity);
		arrivingCity.addListener(SWT.Modify, new Listener() {
			@Override
			public void handleEvent(Event event) {
				String text = arrivingCity.getText();
				if (text.length() == 0) 
				{
					suggestDepartureCity.setVisible(false);
				} else {
					suggestDepartureCity.setVisible(true);
					//This is my code, just commented out because it won't work without database connection.
					//List<MenuItem> matches = FlightSearch.findPossibleAirportDatabaseMatches(text, suggestDepartureCity);
				}
			}
		});
		
		btnSearchForFlights = new Button(shell, SWT.NONE);
		btnSearchForFlights.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(SearchCriteriaIsValid()){
					createFlightSearchCriteria();
					LoginController.flightResultsView(shell);
				}
			}
		});
		btnSearchForFlights.setBounds(149, 224, 164, 31);
		btnSearchForFlights.setText("Search For Flights");
		
		Button btnMainMenu = new Button(shell, SWT.NONE);
		btnMainMenu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				LoginController.menuView(shell);
			}
		});
		btnMainMenu.setBounds(10, 10, 95, 28);
		btnMainMenu.setText("Main Menu");
		
		btnRoundTrip = new Button(shell, SWT.CHECK);
		btnRoundTrip.setBounds(149, 188, 95, 18);
		btnRoundTrip.setText("Round Trip");
		
		errorMessage = new Label(shell, SWT.NONE);
		errorMessage.setAlignment(SWT.CENTER);
		errorMessage.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		errorMessage.setBounds(86, 204, 277, 14);
	}
	
	/**
	 * Checks to see if all fields have appropriate input, so that a FlightSearch will be successful.
	 * @return
	 */
	private Boolean SearchCriteriaIsValid()
	{
		Boolean valid = true;
		if((departingCity.getText().isEmpty()) && (arrivingCity.getText().isEmpty())){
			valid = false;
			errorMessage.setText(String.format("Please enter a source and destination city."));
		}
		else if(departingCity.getText().isEmpty()){
			valid = false;
			errorMessage.setText(String.format("Please enter a departure city."));
		} else if (arrivingCity.getText().isEmpty()){
			valid = false;
			errorMessage.setText(String.format("Please enter a destination."));
		} else {
			errorMessage.setText(String.format(""));
		}
		return valid;
	}
	
	/**
	 * Extracts the FlightSearch information from the view and updates the FlightSearch object accordingly.
	 */
	private void createFlightSearchCriteria() 
	{
		String depCity = departingCity.getSelectionText();
		String arrCity = arrivingCity.getSelectionText();
		int depDay = dateTime.getDay();
		int depMonth = dateTime.getMonth();
		int depYear = dateTime.getYear();
		Boolean roundTrip = btnRoundTrip.getEnabled();
		Date depDate = new Date(depYear, depMonth, depDay);
		FlightSearch currentSearch = FlightSearch.getInstance();
		currentSearch.createFlightSearch(depDate, depCity, arrCity, roundTrip);
	}
}
