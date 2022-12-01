import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.awt.FlowLayout;

public class MainFrame extends JFrame {
	File file = new File("accounts.txt");
	String currentUser;
	private JPanel mainPanel = new JPanel();
	private JPanel loginPanel = new JPanel();
	private JPanel menuPanel = new JPanel();
	private CardLayout cl = new CardLayout();
	private final JButton registerBtn = new JButton("Register");
	private final JButton loginBtn = new JButton("Login");
	private final JLabel titleLbl = new JLabel("BookEZ");
	private final JLabel loginLbl = new JLabel("Please enter username and password");
	private final JPanel textFieldPanel = new JPanel();
	private final JTextField usernameTextField = new JTextField();
	private final JTextField passwordTextField = new JTextField();
	private final JPanel buttonPanel = new JPanel();
	private final JPanel titlePanel = new JPanel();
	private final JPanel menuTitlePanel = new JPanel();
	private final JLabel menuTitleLbl = new JLabel("BookEZ");
	private final JLabel menuInstructLbl = new JLabel("Menu");
	private final JPanel menuBackPanel = new JPanel();
	private final JButton menuBackBtn = new JButton("Back to login");
	private final JPanel menuOptionPanel = new JPanel();
	private final JButton menuUploadBtn = new JButton("Upload data");
	private final JButton menuTableBtn = new JButton("View ROI Table");
	private final JPanel menuBottomPanel = new JPanel();
	
	MainFrame() {
		getContentPane().add(mainPanel);
		mainPanel.setBackground(new Color(153, 204, 255));
		mainPanel.setLayout(cl);
		mainPanel.add(loginPanel, "1");
		menuPanel.setBackground(new Color(153, 204, 255));
		mainPanel.add(menuPanel, "2");
		menuPanel.setLayout(new BorderLayout(0, 0));
		mainPanel.setVisible(true);
		
		//login page
		loginPanel.setLayout(new BorderLayout(0, 0));
		loginPanel.setBackground(new Color(153, 204, 255));
		textFieldPanel.setBackground(new Color(153, 204, 255));
		loginPanel.add(textFieldPanel, BorderLayout.CENTER);
		GridBagLayout gbl_textFieldPanel = new GridBagLayout();
		gbl_textFieldPanel.columnWidths = new int[]{450, 0};
		gbl_textFieldPanel.rowHeights = new int[]{103, 103, 0};
		gbl_textFieldPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_textFieldPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		textFieldPanel.setLayout(gbl_textFieldPanel);
		usernameTextField.setColumns(10);
		usernameTextField.setPreferredSize(new Dimension(400, 24));
		GridBagConstraints gbc_usernameTextField = new GridBagConstraints();
		gbc_usernameTextField.anchor = GridBagConstraints.SOUTHEAST;
		gbc_usernameTextField.insets = new Insets(0, 0, 5, 0);
		gbc_usernameTextField.gridx = 0;
		gbc_usernameTextField.gridy = 0;
		textFieldPanel.add(usernameTextField, gbc_usernameTextField);
		passwordTextField.setColumns(10);
		passwordTextField.setPreferredSize(new Dimension(400, 24));
		GridBagConstraints gbc_passwordTextField = new GridBagConstraints();
		gbc_passwordTextField.anchor = GridBagConstraints.NORTHEAST;
		gbc_passwordTextField.gridx = 0;
		gbc_passwordTextField.gridy = 1;
		textFieldPanel.add(passwordTextField, gbc_passwordTextField);
		usernameTextField.setUI(new HintTextFieldUI(" Username", true));
		passwordTextField.setUI(new HintTextFieldUI(" Password", true));
		loginPanel.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new GridLayout(1, 0, 0, 0));
		buttonPanel.add(registerBtn);
		buttonPanel.add(loginBtn);
		titlePanel.setBackground(new Color(204, 204, 255));
		loginPanel.add(titlePanel, BorderLayout.NORTH);
		titlePanel.setLayout(new GridLayout(0, 1, 0, 0));
		titlePanel.add(titleLbl);
		titleLbl.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titlePanel.add(loginLbl);
		loginLbl.setHorizontalAlignment(SwingConstants.CENTER);
		registerBtn.addActionListener(new ActionListener()
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
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        			login(username);
        		} else {
        			//System.out.println("taken");
        		}
        	}
        });
		loginBtn.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		String username = usernameTextField.getText();
        		String password = passwordTextField.getText();
        		Scanner sc = null;
        		try {
        			sc = new Scanner(file);
        		} catch (FileNotFoundException e1) {
        			e1.printStackTrace();
        		}
        		
        		String line;
        		int indexUsername;
        		while(sc.hasNextLine()) {
        			line = sc.nextLine();
        			indexUsername = line.indexOf(",");
        			if(line.substring(0, indexUsername).equals(username) && line.substring(indexUsername+1, line.indexOf(",", indexUsername+1)).equals(password)) {
        				login(username);
        			}
        		}
        		
        	}
        });
		loginPanel.setVisible(true);
		cl.show(mainPanel, "1");
		
		//main menu
		menuTitlePanel.setBackground(new Color(204, 204, 255));
		menuPanel.add(menuTitlePanel, BorderLayout.NORTH);
		menuTitlePanel.setLayout(new GridLayout(0, 1, 0, 0));
		menuTitleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		menuTitleLbl.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		menuTitlePanel.add(menuTitleLbl);
		menuInstructLbl.setHorizontalAlignment(SwingConstants.CENTER);
		menuTitlePanel.add(menuInstructLbl);
		menuBackPanel.setBackground(new Color(153, 204, 255));
		
		menuPanel.add(menuBackPanel, BorderLayout.WEST);
		
		menuBackPanel.add(menuBackBtn);
		menuOptionPanel.setBackground(new Color(153, 204, 255));
		
		menuPanel.add(menuOptionPanel, BorderLayout.CENTER);
		menuOptionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		menuOptionPanel.add(menuUploadBtn);
		menuOptionPanel.add(menuTableBtn);
		menuBottomPanel.setBackground(new Color(153, 204, 255));
		
		menuPanel.add(menuBottomPanel, BorderLayout.SOUTH);
		Icon imgIcon = new ImageIcon(this.getClass().getResource("enchanting.gif"));
		JLabel menuBottomLbl = new JLabel(imgIcon);
		menuBottomPanel.add(menuBottomLbl);
		
	}
	
	boolean usernameTaken(String username) {
		Scanner sc = null;
		try {
			sc = new Scanner(file);
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
	
	void login(String username) {
		cl.show(mainPanel, "2");
		currentUser = username;
		usernameTextField.setText("");
		passwordTextField.setText("");
	}
}
