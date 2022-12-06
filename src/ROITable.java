import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTable;

public class ROITable extends JPanel{
	private MainFrame mainFrame;
	private JTable ROITable;
	private int tableSize;
	
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
		
		//construct table
		
		/*
		TableModel dataModel = new AbstractTableModel() {
	        public int getColumnCount() { return 10; }
	        public int getRowCount() { return 10;}
	        public Object getValueAt(int row, int col) { return (row*col); }
	    };*/
	    
	    JScrollPane scrollpane = new JScrollPane(constructTable());
		add(scrollpane, BorderLayout.CENTER);
		
	}
	
	JTable constructTable() {
		String[] columnNames = {"#", "Product", "Price", "Order ID"};
		JTable table = new JTable();
		table.setFillsViewportHeight(true);
		
		
		
		return table;
	}
}
