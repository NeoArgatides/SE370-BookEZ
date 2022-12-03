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
	private JPanel loginPanel = new Login(this);
	private JPanel menuPanel = new JPanel();
	private JPanel uploadPanel = new JPanel();
	private CardLayout cl = new CardLayout();
	
	private final JPanel menuTitlePanel = new JPanel();
	private final JLabel menuTitleLbl = new JLabel("BookEZ");
	private final JLabel menuInstructLbl = new JLabel("Menu");
	private final JPanel menuBackPanel = new JPanel();
	private final JButton menuBackBtn = new JButton("Back to login");
	private final JPanel menuOptionPanel = new JPanel();
	private final JButton menuUploadBtn = new JButton("Upload data");
	private final JButton menuTableBtn = new JButton("View ROI Table");
	private final JPanel menuBottomPanel = new JPanel();
	
	private final JPanel uploadTitlePanel = new JPanel();
	private final JLabel menuTitleLbl_1 = new JLabel("BookEZ");
	private final JLabel uploadInstructLbl = new JLabel("Upload Data");
	private final JPanel uploadButtonPanel = new JPanel();
	private final JButton uploadBackBtn = new JButton("Back to Menu");
	
	
	MainFrame() {
		getContentPane().add(mainPanel);
		mainPanel.setBackground(new Color(153, 204, 255));
		mainPanel.setLayout(cl);
		mainPanel.add(loginPanel, "1");
		mainPanel.add(menuPanel, "2");
		mainPanel.add(uploadPanel, "3");
		menuPanel.setBackground(new Color(153, 204, 255));
		menuPanel.setLayout(new BorderLayout(0, 0));
		mainPanel.setVisible(true);
		
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
		menuBackBtn.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		cl.show(mainPanel, "1");
        	}
        });
		menuOptionPanel.setBackground(new Color(153, 204, 255));
		
		menuPanel.add(menuOptionPanel, BorderLayout.CENTER);
		menuOptionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		menuOptionPanel.add(menuUploadBtn);
		menuOptionPanel.add(menuTableBtn);
		menuBottomPanel.setBackground(new Color(153, 204, 255));
		
		/*menuPanel.add(menuBottomPanel, BorderLayout.SOUTH);
		Icon imgIcon = new ImageIcon(this.getClass().getResource("enchanting.gif"));
		JLabel menuBottomLbl = new JLabel(imgIcon);
		menuBottomPanel.add(menuBottomLbl); 
		*/
		
		//upload data
		uploadPanel.setBackground(new Color(153, 204, 255));
		uploadPanel.setLayout(new BorderLayout(0, 0));
		uploadTitlePanel.setBackground(new Color(204, 204, 255));
		
		uploadPanel.add(uploadTitlePanel, BorderLayout.NORTH);
		uploadTitlePanel.setLayout(new GridLayout(0, 1, 0, 0));
		menuTitleLbl_1.setHorizontalAlignment(SwingConstants.CENTER);
		menuTitleLbl_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		uploadTitlePanel.add(menuTitleLbl_1);
		uploadInstructLbl.setHorizontalAlignment(SwingConstants.CENTER);
		
		uploadTitlePanel.add(uploadInstructLbl);
		uploadButtonPanel.setBackground(new Color(153, 204, 255));
		
		uploadPanel.add(uploadButtonPanel, BorderLayout.CENTER);
		GridBagLayout gbl_uploadButtonPanel = new GridBagLayout();
		gbl_uploadButtonPanel.columnWidths = new int[]{0, 0};
		gbl_uploadButtonPanel.rowHeights = new int[]{0, 0};
		gbl_uploadButtonPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_uploadButtonPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		uploadButtonPanel.setLayout(gbl_uploadButtonPanel);
		
		GridBagConstraints gbc_uploadBackBtn = new GridBagConstraints();
		gbc_uploadBackBtn.gridx = 0;
		gbc_uploadBackBtn.gridy = 0;
		uploadButtonPanel.add(uploadBackBtn, gbc_uploadBackBtn);
		
		
	}
	
	File getFile() {
		return file;
	}
	
	void login(String username) {
		cl.show(mainPanel, "2");
		currentUser = username;
		((Login) loginPanel).login();
	}
}
