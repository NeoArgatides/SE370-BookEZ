import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	private JPanel mainPanel = new JPanel();
	private JPanel loginPanel = new JPanel();
	private JPanel menuPanel = new JPanel();
	private JPanel uploadPanel = new JPanel();
	private JPanel tablePanel = new JPanel();
	private CardLayout cl = new CardLayout();
	
	MainFrame() {
		mainPanel.setLayout(cl);
		mainPanel.add(loginPanel, "1");
		mainPanel.add(menuPanel, "2");
		mainPanel.add(uploadPanel, "3");
		mainPanel.add(tablePanel, "4");
		mainPanel.setSize(2400, 1350);
		
		cl.show(mainPanel, "1");
	}
}
