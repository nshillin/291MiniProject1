package views;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import controllers.LoginController;
import controllers.QueryHandler;
import models.Sch_Flight;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;

// View to change arr and dep times
public class ArrDepView {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ArrDepView window = new ArrDepView();
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
		shell.setText("Record Departure and Arrival Time");
		
		Button backButton = new Button(shell, SWT.NONE);
		backButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LoginController.menuView(shell);
			}
		});
		backButton.setText("back");
		backButton.setBounds(0, 0, 66, 28);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setAlignment(SWT.RIGHT);
		lblNewLabel.setBounds(100, 87, 59, 14);
		lblNewLabel.setText("Flight:");
		
		final LinkedList<Sch_Flight> flightList = QueryHandler.getSingleFlights();
		final Combo flightCombo = new Combo(shell, SWT.READ_ONLY);
		for (int i = 0; i < flightList.size(); i++) {
			flightCombo.add(flightList.get(i).getFlightNumber() + " on " + flightList.get(i).getDepartureDate().toString());
		}
		flightCombo.select(0);
		flightCombo.setBounds(165, 83, 250, 22);
		
		final Combo arrdepCombo = new Combo(shell, SWT.READ_ONLY);
		arrdepCombo.add("Arrival");
		arrdepCombo.add("Departure");
		arrdepCombo.select(0);
		arrdepCombo.setBounds(165, 111, 125, 22);
		
		final DateTime flightTime = new DateTime(shell, SWT.BORDER | SWT.TIME);
		flightTime.setBounds(165, 146, 125, 27);
		
		Button btnRecord = new Button(shell, SWT.NONE);
		btnRecord.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Sch_Flight flight = flightList.get(flightCombo.getSelectionIndex());
				if (arrdepCombo.getText().equals("Arrival")) {
					Timestamp arr = flight.getAct_arr_time();
					@SuppressWarnings("deprecation")
					Timestamp new_arr_time = new Timestamp(arr.getYear(), arr.getMonth(), 
							arr.getDate(), flightTime.getHours(), flightTime.getMinutes(), flightTime.getSeconds(), 0);
					flight.setAct_arr_time(new_arr_time);
				} else {
					Timestamp dep = flight.getAct_dep_time();
					@SuppressWarnings("deprecation")
					Timestamp new_dep_time = new Timestamp(dep.getYear(), dep.getMonth(), 
							dep.getDate(), flightTime.getHours(), flightTime.getMinutes(), flightTime.getSeconds(), 0);
					flight.setAct_dep_time(new_dep_time);
				} 
				LoginController.recordArrDep(shell, flight);
			}
		});
		
		btnRecord.setBounds(175, 179, 94, 28);
		btnRecord.setText("Record");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setAlignment(SWT.RIGHT);
		lblNewLabel_1.setBounds(100, 149, 59, 14);
		lblNewLabel_1.setText("Time:");
		
		Label lblDepartureOrArrival = new Label(shell, SWT.NONE);
		lblDepartureOrArrival.setAlignment(SWT.RIGHT);
		lblDepartureOrArrival.setBounds(13, 115, 146, 14);
		lblDepartureOrArrival.setText("Departure or Arrival:");
	}
}
