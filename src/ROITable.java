import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTable;

public class ROITable extends JPanel{
	private MainFrame mainFrame;
	private JTable ROITable;
	
	ROITable(MainFrame mainFrame) {
		setBackground(new Color(153, 204, 255));
		this.mainFrame = mainFrame;
		setLayout(new BorderLayout(0, 0));
		
		JPanel tableTitlePanel = new JPanel();
		tableTitlePanel.setBackground(new Color(204, 204, 255));
		add(tableTitlePanel, BorderLayout.NORTH);
		tableTitlePanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel tableTitleLbl = new JLabel("BookEZ");
		tableTitleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tableTitleLbl.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tableTitlePanel.add(tableTitleLbl);
		
		JLabel tableInstructLbl = new JLabel("Menu");
		tableInstructLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tableTitlePanel.add(tableInstructLbl);
		
		JPanel tableBackPanel = new JPanel();
		tableBackPanel.setBackground(new Color(153, 204, 255));
		add(tableBackPanel, BorderLayout.WEST);
		
		JButton tableBackBtn = new JButton("Back to menu");
		tableBackPanel.add(tableBackBtn);
		tableBackBtn.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		mainFrame.goToMenu();
        	}
        });
		
		JTable table = new JTable();
		add(table, BorderLayout.CENTER);
	}
}
