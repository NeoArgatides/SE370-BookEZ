import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.io.File;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	File file = new File("accounts.txt");
	String currentUser;
	private JPanel mainPanel = new JPanel();
	private JPanel loginPanel = new Login(this);
	private JPanel uploadPanel = new Upload(this);
	private JPanel catalogPanel = new Catalog(this);
	private JPanel ROIPanel = new ROITable(this);
	private JPanel menuPanel = new Menu(this);
	private CardLayout cl = new CardLayout();
	private ROIManager manager = new ROIManager(this);
	
	MainFrame() {
		getContentPane().add(mainPanel);
		mainPanel.setBackground(new Color(153, 204, 255));
		mainPanel.setLayout(cl);
		mainPanel.add(loginPanel, "1");
		mainPanel.add(menuPanel, "2");
		mainPanel.add(uploadPanel, "3");
		mainPanel.add(catalogPanel, "4");
		mainPanel.add(ROIPanel, "5");
		//((ROITable) ROIPanel).refreshTable();
		mainPanel.setVisible(true);
		
		cl.show(mainPanel, "1");
	}
	
	File getFile() {
		return file;
	}
	
	void login(String username) {
		cl.show(mainPanel, "2");
		currentUser = username;
		((Login) loginPanel).login();
	}
	
	void goToLogin() {
		cl.show(mainPanel, "1");
	}
	
	void goToMenu() {
		cl.show(mainPanel, "2");
	}
	
	void goToUpload() {
		cl.show(mainPanel, "3");
	}
	
	void goToCatalog() {
		cl.show(mainPanel, "4");
	}
	
	void goToTable() {
		((ROITable) ROIPanel).refreshTable();
		cl.show(mainPanel, "5");
	}
	
	String getUser() {
		return currentUser;
	}
	
	ROIManager getManager() {
		return manager;
	}
}
