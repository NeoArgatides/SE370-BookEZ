import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	File file = new File("accounts.txt");
	String currentUser;

	private JPanel mainPanel = new JPanel();
	Connection connection = null; // Create connection object
	UserDAO userDAO;
	ReceiptDAO receiptDAO;
	DataExtractor_IF dataExtractor;

	private JPanel loginPanel;
	// private JPanel loginPanel = new Login(this, connection);
	private JPanel uploadPanel = new Upload(this);
	private JPanel catalogPanel = new Catalog(this);
	// private JPanel ROIPanel = new ROITable(this);
	private JPanel ROIPanel;

	private JPanel menuPanel = new Menu(this);
	private CardLayout cl = new CardLayout();
	private ROIManager manager;

	MainFrame() {
		try {
			connection = DBConnection.getInstance().getConnection();
			userDAO = new UserDAO(connection);
			receiptDAO = new ReceiptDAO(connection);

			// create login panel
			loginPanel = new Login(this, userDAO);
			ROIPanel = new ROITable(this, receiptDAO, userDAO);

			// create manager and extractor
			manager = new ROIManager(this, receiptDAO, userDAO);
			dataExtractor = new DataExtractor(manager);

			// set the extractor in manager
			manager.setDataExtractor(dataExtractor);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		/////////
		getContentPane().add(mainPanel);
		mainPanel.setBackground(new Color(153, 204, 255));
		mainPanel.setLayout(cl);
		mainPanel.add(loginPanel, "1");
		mainPanel.add(menuPanel, "2");
		mainPanel.add(uploadPanel, "3");
		mainPanel.add(catalogPanel, "4");
		mainPanel.add(ROIPanel, "5");
		// ((ROITable) ROIPanel).refreshTable();
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
		try {
			((ROITable) ROIPanel).refreshTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cl.show(mainPanel, "5");
	}

	String getUser() {
		return currentUser;
	}

	ROIManager getManager() {
		return manager;
	}
}