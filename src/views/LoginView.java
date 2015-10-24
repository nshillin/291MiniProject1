package views;


import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

import controllers.LoginController;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.wb.swt.SWTResourceManager;

public class LoginView {
	private Text passwordText;
	private Text usernameText;
	public static Shell shell;
	private Button btnBack;
	private static Label errLabel;

	/**
	 * Launch the application.
	 * @param args
	 */
	
	public static void main(String args[]) {
		try {
			LoginView window = new LoginView();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
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
	 * Create the shell.
	 * @param display
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setText(LoginController.currentView);
		shell.setSize(LoginController.size);
		shell.setLocation(LoginController.position);
		
		passwordText = new Text(shell, SWT.PASSWORD | SWT.BORDER);
		passwordText.setBounds(200, 131, 132, 19);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(127, 114, 67, 14);
		lblNewLabel.setText("Username:");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(133, 134, 61, 14);
		lblNewLabel_1.setText("Password: ");
		
		usernameText = new Text(shell, SWT.BORDER);
		usernameText.setBounds(200, 106, 132, 19);
		
		Button btnEnter = new Button(shell, SWT.NONE);
		btnEnter.setBounds(185, 176, 94, 28);
		btnEnter.setText("Enter");
		btnEnter.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				checkLogin();
			}
		    });
		if (shell.getText().equals("Login")) {
			btnBack = new Button(shell, SWT.NONE);
			btnBack.setText("back");
			btnBack.setBounds(0, 0, 66, 28);
			btnBack.addListener(SWT.Selection, new Listener() {
				@Override
				public void handleEvent(Event arg0) {
					LoginController.logout(shell);
				}
			    });
		}
		
		errLabel = new Label(shell, SWT.NONE);
		errLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		errLabel.setAlignment(SWT.CENTER);
		errLabel.setBounds(54, 156, 364, 28);
		
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
		else {
			errorMessage("");
			if (shell.getText().equals("Login")) {
				LoginController.login(username, password, shell);
			}
			else {
				LoginController.sqlLogin(username, password, shell);
			}
		}

	}
	
	public static void errorMessage(String error) {
		errLabel.setText(error);
	}
}
