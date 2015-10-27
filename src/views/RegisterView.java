package views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import controllers.LoginController;
import controllers.QueryHandler;
import org.eclipse.wb.swt.SWTResourceManager;

// View used to register new user
public class RegisterView {
	private Text passwordText;
	private Text usernameText;

	protected Shell shell;
	private Text cnfrmPasswordText;
	private Label lblConfirmPassword;
	private Button btnBack;
	private static Label errLabel;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			RegisterView window = new RegisterView();
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
		shell.setText("Register");
		shell.setLocation(LoginController.position);
		
		
		usernameText = new Text(shell, SWT.BORDER);
		usernameText.setBounds(200, 87, 132, 19);
		
		passwordText = new Text(shell, SWT.PASSWORD | SWT.BORDER);
		passwordText.setBounds(200, 112, 132, 19);
		
		cnfrmPasswordText = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		cnfrmPasswordText.setBounds(200, 137, 132, 19);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(88, 95, 106, 14);
		lblNewLabel.setText("Username (email):");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(133, 115, 61, 14);
		lblNewLabel_1.setText("Password: ");
		
		lblConfirmPassword = new Label(shell, SWT.NONE);
		lblConfirmPassword.setText("Confirm Password: ");
		lblConfirmPassword.setBounds(88, 140, 106, 14);
		
		Button btnEnter = new Button(shell, SWT.NONE);
		btnEnter.setBounds(186, 177, 94, 28);
		btnEnter.setText("Enter");
		btnEnter.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				checkLogin();
			}
		    });
		
		btnBack = new Button(shell, SWT.NONE);
		btnBack.setBounds(0, 0, 66, 28);
		btnBack.setText("back");
		btnBack.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				LoginController.logout(shell);
			}
		    });
		
		errLabel = new Label(shell, SWT.NONE);
		errLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		errLabel.setAlignment(SWT.CENTER);
		errLabel.setBounds(88, 162, 284, 28);
	}
	
	private void checkLogin() {
		String username = usernameText.getText();
		String password = passwordText.getText();
		
		if (usernameText.getText().isEmpty()) {
			errorMessage("No username entered.");
		}
		else if (passwordText.getText().isEmpty()) {
			errorMessage("No password entered.");
		}
		else if (passwordText.getText().length() > 4) {
			errorMessage("Password too long. Must be 4 characters or less.");
		}
		else if  (!password.equals(cnfrmPasswordText.getText())) {
			errorMessage("Passwords do not match.");
		}
		else {
			errorMessage("");
			LoginController.register(username, password, shell);
		}

	}
	
	public static void errorMessage(String error) {
		errLabel.setText(error);
	}

}
