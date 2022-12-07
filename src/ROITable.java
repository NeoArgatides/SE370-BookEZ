import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
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
		Object[] columnNames = {"#", "Order #", "Total", "Shipping Cost", "Price", "Shipping Paid", "Tax"};
		JTable table = new JTable(new DefaultTableModel(columnNames, 1));
		table.setFillsViewportHeight(true);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		String orderNum = null, total = null, shipCost = null, soldPrice = null, shipPaid = null, tax = null;
		
		File file = new File("accounts.txt");
		try {
		    Scanner scanner = new Scanner(file);
		    int i = 1;
		    int nextIndex;

		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        if(line.substring(0, line.indexOf(",")).equals(mainFrame.getUser())) {
		        	nextIndex = line.indexOf(",");
		        	nextIndex = line.indexOf(",", nextIndex+1);
		        	
		        	while(line.indexOf(",", nextIndex+1) != -1) {
		        		orderNum = line.substring(nextIndex+1, line.indexOf(",", nextIndex+1));
		        		nextIndex = line.indexOf(",", nextIndex+1);
		        		total = line.substring(nextIndex+1, line.indexOf(",", nextIndex+1));
		        		nextIndex = line.indexOf(",", nextIndex+1);
		        		shipCost = line.substring(nextIndex+1, line.indexOf(",", nextIndex+1));
		        		nextIndex = line.indexOf(",", nextIndex+1);
		        		soldPrice = line.substring(nextIndex+1, line.indexOf(",", nextIndex+1));
		        		nextIndex = line.indexOf(",", nextIndex+1);
		        		shipPaid = line.substring(nextIndex+1, line.indexOf(",", nextIndex+1));
		        		nextIndex = line.indexOf(",", nextIndex+1);
		        		tax = line.substring(nextIndex+1, line.indexOf(",", nextIndex+1));
		        		nextIndex = line.indexOf(",", nextIndex+1);
		        		model.addRow(new Object[]{i, orderNum, total, shipCost, soldPrice, shipPaid, tax});
		        	}
		        }
		        i++;
		    }
		} catch(FileNotFoundException e) { 
			e.printStackTrace();
		}
		
		return table;
	}
}
