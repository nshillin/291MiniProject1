package views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import controllers.LoginController;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;

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
		
		DateTime dateTime = new DateTime(shell, SWT.BORDER | SWT.TIME);
		dateTime.setBounds(165, 146, 125, 27);
		
		Combo combo = new Combo(shell, SWT.NONE);
		combo.setBounds(165, 83, 250, 22);
		
		Button btnRecord = new Button(shell, SWT.NONE);
		btnRecord.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnRecord.setBounds(175, 179, 94, 28);
		btnRecord.setText("Record");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setAlignment(SWT.RIGHT);
		lblNewLabel_1.setBounds(100, 149, 59, 14);
		lblNewLabel_1.setText("Time:");
		
		Combo combo_1 = new Combo(shell, SWT.NONE);
		combo_1.setBounds(165, 111, 125, 22);
		
		Label lblDepartureOrArrival = new Label(shell, SWT.NONE);
		lblDepartureOrArrival.setAlignment(SWT.RIGHT);
		lblDepartureOrArrival.setBounds(13, 115, 146, 14);
		lblDepartureOrArrival.setText("Departure or Arrival:");
	}
}
