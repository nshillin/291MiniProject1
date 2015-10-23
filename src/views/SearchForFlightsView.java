package views;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

public class SearchForFlightsView {

	protected Shell shell;
	private Text departingCity;
	private Text arrivingCity;
	private static Button btnSearchForFlights;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SearchForFlightsView window = new SearchForFlightsView();
			window.open();
			btnSearchForFlights.addSelectionListener(new SelectionListener(){
				 public void widgetSelected(SelectionEvent event) 
				 {
				 
				 }
				 
				 public void widgetDefaultSelected(SelectionEvent event) 
				 {
				        
				 }
			});
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
		shell.setText("SWT Application");
		
		departingCity = new Text(shell, SWT.BORDER);
		departingCity.setBounds(149, 65, 164, 31);
		
		Label lblFlight = new Label(shell, SWT.NONE);
		lblFlight.setBounds(149, 45, 60, 14);
		lblFlight.setText("Going To:");
		
		DateTime dateTime = new DateTime(shell, SWT.BORDER);
		dateTime.setBounds(149, 122, 86, 27);
		
		Label lblDepartingBy = new Label(shell, SWT.NONE);
		lblDepartingBy.setBounds(149, 102, 86, 14);
		lblDepartingBy.setText("Departing By:");
		
		Label lblLeavingBy = new Label(shell, SWT.NONE);
		lblLeavingBy.setBounds(149, 155, 86, 14);
		lblLeavingBy.setText("Leaving From:");
		
		arrivingCity = new Text(shell, SWT.BORDER);
		arrivingCity.setBounds(149, 175, 164, 31);
		
		btnSearchForFlights = new Button(shell, SWT.NONE);
		btnSearchForFlights.setBounds(149, 215, 129, 28);
		btnSearchForFlights.setText("Search For Flights");

	}
}
