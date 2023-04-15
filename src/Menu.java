import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.io.FileReader;

public class Menu extends JPanel {
	private static final long serialVersionUID = 1L;
	private MainFrame mainFrame;
	
	Menu(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		setBackground(new Color(153, 204, 255));
		setLayout(new BorderLayout(0, 0));
		
		JPanel menuTitlePanel = new JPanel();
		menuTitlePanel.setBackground(new Color(204, 204, 255));
		add(menuTitlePanel, BorderLayout.NORTH);
		menuTitlePanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel menuTitleLbl = new JLabel("BookEZ");
		menuTitleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		menuTitleLbl.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		menuTitlePanel.add(menuTitleLbl);
		
		JLabel menuInstructLbl = new JLabel("Menu");
		menuInstructLbl.setHorizontalAlignment(SwingConstants.CENTER);
		menuTitlePanel.add(menuInstructLbl);
		
		JPanel menuBackPanel = new JPanel();
		menuBackPanel.setBackground(new Color(153, 204, 255));
		add(menuBackPanel, BorderLayout.WEST);
		
		JButton menuBackBtn = new JButton("Back to login");
		menuBackPanel.add(menuBackBtn);
		menuBackBtn.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		mainFrame.goToLogin();
        	}
        });
		
		JPanel menuOptionPanel = new JPanel();
		menuOptionPanel.setBackground(new Color(153, 204, 255));
		add(menuOptionPanel, BorderLayout.EAST);
		
		JButton menuUploadBtn = new JButton("Upload Data");
		menuOptionPanel.add(menuUploadBtn);
		menuUploadBtn.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		mainFrame.goToUpload();
        	}
        });
		
		JButton menuTableBtn = new JButton("View ROI Table");
		menuOptionPanel.add(menuTableBtn);
		menuTableBtn.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		mainFrame.goToTable();
        	}
        });
		
		JPanel menuBottonPanel = new JPanel();
		menuBottonPanel.setBackground(new Color(153, 204, 255));
		add(menuBottonPanel, BorderLayout.SOUTH);
		
		JLabel menuImageLbl = new JLabel("");
		menuBottonPanel.add(menuImageLbl);
		ImageIcon imgIcon = new ImageIcon("enchanting.gif");
		menuImageLbl.setIcon(imgIcon);
	}
}
