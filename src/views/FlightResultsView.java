package views;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.List;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import controllers.LoginController;
import models.FlightSearch;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;

import java.io.Console;
import java.sql.*;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class FlightResultsView {

	protected Shell shell;
	private static Table table;
	private static String url = "jdbc:oracle:thin:@gwynne.cs.ualberta.ca:1521:CRS";
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
	
	public static void fetchDatafromDB(String startIndex, String finalIndex) {
	    try {
	    	//This code is just for easy testing purposes, will remove and use our object once I confirm functionality.
	    	System.out.print("Username: ");
	    	Console co = System.console();
	    	String m_userName = co.readLine();
	    	
	    	// obtain password
	    	char[] passwordArray = co.readPassword("Password: ");
	    	String m_password = new String(passwordArray);
	    	String m_driverName = String.format("oracle.jdbc.driver.OracleDriver");
	    	
	    	Class drvClass = Class.forName (m_driverName);
	    	DriverManager.registerDriver((Driver)	
	    	drvClass.newInstance());	
	        Connection conn = DriverManager.getConnection(url, m_userName, m_password);
	        Statement st = conn.createStatement();
	        String query = "SELECT  flightno, src, dst, dep_time, est_dur from flights";
	        ResultSet rs = st.executeQuery(query);
	        java.sql.ResultSetMetaData rsmd = rs.getMetaData();
	        int columnsNumber = rsmd.getColumnCount();

	        TableItem item;
	        while (rs.next()) {
	            // Create a new TableItem for each entry in the result set (each row)
	            item = new TableItem(table, SWT.NONE);
	            for (int i = 1; i <= columnsNumber; i++) {
	                // Populate the item
	                item.setText(i - 1, rs.getString(i));
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		shell.setSize(LoginController.size);
		shell.setLocation(LoginController.position);
		
		CheckboxTableViewer flightResults = CheckboxTableViewer.newCheckList(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table = flightResults.getTable();
		table.setBounds(10, 43, 430, 158);
		
		Label lblFlightsFound = new Label(shell, SWT.NONE);
		lblFlightsFound.setBounds(10, 23, 92, 14);
		lblFlightsFound.setText("Flights Found:");
		
		Button btnBookFlight = new Button(shell, SWT.NONE);
		btnBookFlight.setBounds(20, 207, 140, 28);
		btnBookFlight.setText("Book Flight");
		
		sortByConnections = new Button(shell, SWT.CHECK);
		sortByConnections.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				Boolean sortBy = sortByConnections.getEnabled();
				currentFlightSearch.setSortByConnections(sortBy);
				String query = currentFlightSearch.getSearchQuery();
			}
		});
		sortByConnections.setBounds(293, 207, 147, 28);
		sortByConnections.setText("Sort By Connections");

	}
}
