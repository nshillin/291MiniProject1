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

public class LoginView {
	private Text passwordText;
	private Text usernameText;
	public static Shell shell;
	private Button btnBack;

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
		shell.setText("Login");
		shell.setSize(LoginController.size);
		shell.setLocation(LoginController.position);
		
		passwordText = new Text(shell, SWT.PASSWORD | SWT.BORDER);
		passwordText.setBounds(200, 131, 132, 19);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(88, 109, 106, 14);
		lblNewLabel.setText("Username (email):");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(133, 134, 61, 14);
		lblNewLabel_1.setText("Password: ");
		
		usernameText = new Text(shell, SWT.BORDER);
		usernameText.setBounds(200, 106, 132, 19);
		
		Button btnEnter = new Button(shell, SWT.NONE);
		btnEnter.setBounds(186, 159, 94, 28);
		btnEnter.setText("Enter");
		
		btnBack = new Button(shell, SWT.NONE);
		btnBack.setText("back");
		btnBack.setBounds(0, 0, 66, 28);
		btnBack.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				LoginController.back(shell);
			}
		    });
	}
	
	public String getUserName() {
        String userName = "";
		return userName;
	}
	
	public String getPassword() {
        String password = "";
        return password;
	}
}
