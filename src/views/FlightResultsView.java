package views;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.List;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import controllers.LoginController;
import models.Flight;
import models.FlightSearch;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;

import java.io.Console;
import java.sql.*;
import java.util.Date;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class FlightResultsView {

	protected Shell shell;
	private static Table table;
	private static String url = "jdbc:oracle:thin:@gwynne.cs.ualberta.ca:1521:CRS";
	private static FlightSearch currentFlightSearch;
	private Button sortByConnections;
	private String[] tableColumns = {"flightSource","flightDestination","flightDepartureTime","flightArrivalTime","numberOfConnections","flightNumbers","layoverTime", "paid_price"};
	

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
		
		CheckboxTableViewer flightResults = CheckboxTableViewer.newCheckList(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table = flightResults.getTable();
		table.setBounds(10, 43, 430, 158);
		//for (int i = 0; i < tableColumns.length; i++) 
		//{
		//      TableColumn column = new TableColumn(table, SWT.NONE);
		//      column.setText(tableColumns[i]);
		//}
		FlightSearch.FindSearchedFlights(table);
		
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
				table.clearAll();
				Boolean sortBy = sortByConnections.getEnabled();
				currentFlightSearch.setSortByConnections(sortBy);
				FlightSearch.FindSearchedFlights(table);
			}
		});
		sortByConnections.setBounds(293, 207, 147, 28);
		sortByConnections.setText("Sort By Connections");

	}
}
