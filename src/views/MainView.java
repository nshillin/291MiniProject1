package views;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import controllers.LoginController;
import org.eclipse.swt.widgets.Button;


import org.eclipse.swt.SWT;

public class MainView {

	public static Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	
	public static void main(String[] args) {
		try {
			MainView window = new MainView();
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
		shell.setText("C291MiniProject1");
		shell.setLocation(LoginController.position);
		
		Button registerButton = new Button(shell, SWT.NONE);
		registerButton.setBounds(183, 138, 94, 28);
		registerButton.setText("Register");
		registerButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				LoginController.registerView(shell);
			}
		    });
		
		Button loginButton = new Button(shell, SWT.NONE);
		loginButton.setText("Login");
		loginButton.setBounds(183, 104, 94, 28);
		loginButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				LoginController.loginView(shell);
			}
		    });
	}
}
