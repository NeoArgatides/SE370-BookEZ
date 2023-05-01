import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
//
import java.sql.Connection;
import java.sql.SQLException;

public class Login extends JPanel implements dbAO_IF{
	private static final long serialVersionUID = 1L;
	private final JButton registerBtn = new JButton("Register");
	private final JButton loginBtn = new JButton("Login");
	private final JLabel titleLbl = new JLabel("BookEZ");
	private final JLabel loginLbl = new JLabel("Please enter username and password");
	private final JPanel textFieldPanel = new JPanel();
	private final JTextField usernameTextField = new JTextField();
	
	private final JPasswordField passwordTextField = new JPasswordField();
	private final JPanel buttonPanel = new JPanel();
	private final JPanel titlePanel = new JPanel();
	private final JLabel errorLbl = new JLabel("");
	private final JLabel usernameLbl = new JLabel("Username:");
	private final JLabel passwordLbl = new JLabel("Password:");
	private MainFrame mainFrame;

	private Connection connection;
	private UserDAO userDAO;
	private User currentUser;

	// public Login(Connection connection) {
	// 	this.connection = connection;
	// 	userDAO = new UserDAO(connection);
	// }

	Login(MainFrame mainFrame, UserDAO userDAO) {
		//
		// this.connection = conn; 
		this.mainFrame = mainFrame;
		this.userDAO = userDAO;

		setLayout(new BorderLayout(0, 0));
		setBackground(new Color(153, 204, 255));
		textFieldPanel.setBackground(new Color(153, 204, 255));
		add(textFieldPanel, BorderLayout.CENTER);
		GridBagLayout gbl_textFieldPanel = new GridBagLayout();
		textFieldPanel.setLayout(gbl_textFieldPanel);
		
		GridBagConstraints gbc_usernameLbl = new GridBagConstraints();
		gbc_usernameLbl.insets = new Insets(0, 0, 5, 5);
		gbc_usernameLbl.gridx = 0;
		gbc_usernameLbl.gridy = 0;
		textFieldPanel.add(usernameLbl, gbc_usernameLbl);
		usernameTextField.setColumns(10);
		usernameTextField.setPreferredSize(new Dimension(400, 24));
		GridBagConstraints gbc_usernameTextField = new GridBagConstraints();
		gbc_usernameTextField.insets = new Insets(0, 0, 5, 0);
		gbc_usernameTextField.gridx = 2;
		gbc_usernameTextField.gridy = 0;
		textFieldPanel.add(usernameTextField, gbc_usernameTextField);
		usernameTextField.setUI(new HintTextFieldUI(" Username", true));
		
		GridBagConstraints gbc_passwordLbl = new GridBagConstraints();
		gbc_passwordLbl.insets = new Insets(0, 0, 5, 5);
		gbc_passwordLbl.gridx = 0;
		gbc_passwordLbl.gridy = 1;
		textFieldPanel.add(passwordLbl, gbc_passwordLbl);
		passwordTextField.setColumns(10);
		passwordTextField.setPreferredSize(new Dimension(400, 24));
		GridBagConstraints gbc_passwordTextField = new GridBagConstraints();
		gbc_passwordTextField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordTextField.gridx = 2;
		gbc_passwordTextField.gridy = 1;
		textFieldPanel.add(passwordTextField, gbc_passwordTextField);
		//
		// passwordTextField.setUI(new HintTextFieldUI(" Password", true));
		add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new GridLayout(1, 0, 0, 0));
		buttonPanel.add(registerBtn);
		buttonPanel.add(loginBtn);
		titlePanel.setBackground(new Color(204, 204, 255));
		add(titlePanel, BorderLayout.NORTH);
		titlePanel.setLayout(new GridLayout(0, 1, 0, 0));
		titlePanel.add(titleLbl);
		titleLbl.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titlePanel.add(loginLbl);
		loginLbl.setHorizontalAlignment(SwingConstants.CENTER);
		
		GridBagConstraints gbc_errorLbl = new GridBagConstraints();
		gbc_errorLbl.gridx = 2;
		gbc_errorLbl.gridy = 3;
		errorLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		errorLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldPanel.add(errorLbl, gbc_errorLbl);
		errorLbl.setForeground(new Color(178, 34, 34));



		registerBtn.addActionListener(new ActionListener() // NeedsWork To register properly with new Database system
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		String username = usernameTextField.getText();
        		String password = passwordTextField.getText();

        		if(!username.equals("") && !password.equals("") && !usernameTaken(username)) {
        			BufferedWriter bf = null;
        			try {
						bf = new BufferedWriter(new FileWriter("accounts.txt", true));
						bf.write(username + ',' + password + ",\n");
						bf.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
        			mainFrame.login(username);
        		} else {
        			errorLbl.setText("Invalid name/PWD");
        		}
        	}
        });
		//verification
		// loginBtn.addActionListener(j -> {
		// 	char[] p = passwordTextField.getPassword();
		// 	String passwordString = new String(p);
		// });
		
		loginBtn.addActionListener(new ActionListener()
        {

        	public void actionPerformed(ActionEvent e)
        	{
        		String username = usernameTextField.getText();
        		// String password = passwordTextField.getText();
				 char[] passwordChars = passwordTextField.getPassword();

				 //String password = passwordTextField.getPassword();
				String password = new String(passwordChars);

				//Arrays.fill(input, '*');
				//passwordTextField.fill(input, '0');
				//for(int i = 0; i <= 20; i++){
					//String password = new String(input);
				 
				System.out.println(username + " " + password);

				// sign in the user
				try {
					currentUser = userDAO.signIn(username, password);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (currentUser != null) {
					// get the receipts for the user
					System.out.println("signed in");
					mainFrame.login(username);
				} else {
					System.out.println("Invalid username or password");
				}
						
				

				
        		// Scanner sc = null;
        		// try {
        		// 	sc = new Scanner(mainFrame.getFile());
        		// } catch (FileNotFoundException e1) {
        		// 	e1.printStackTrace();
        		// }
        		
        		// String line;
        		// int indexUsername;
        		// while(sc.hasNextLine()) {
        		// 	line = sc.nextLine();
        		// 	indexUsername = line.indexOf(",");
        		// 	if(line.substring(0, indexUsername).equals(username) && line.substring(indexUsername+1, line.indexOf(",", indexUsername+1)).equals(password)) {
        		// 		mainFrame.login(username);
        		// 		break;
        		// 	}
        		// }
        		// if(!sc.hasNextLine()) {
        		// 	errorLbl.setText("Invalid name/PWD");
        		// }
        		
        	}
        });
		setVisible(true);
		
		
	}
	
	public void login() {
		usernameTextField.setText("");
		passwordTextField.setText("");
		errorLbl.setText("");
		// System.out.println("hiiii");
	}
	
	public boolean usernameTaken(String username) {
		Scanner sc = null;
		try {
			sc = new Scanner(mainFrame.getFile());
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		String line;
		while(sc.hasNextLine()) {
			line = sc.nextLine();
			if(line.substring(0 , line.indexOf(",")).equals(username)) {
				return true;
			}
		}
		return false;
	}
}