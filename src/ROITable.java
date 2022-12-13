import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.*;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTable;

public class ROITable extends JPanel{
	private static final long serialVersionUID = 1L;
	private MainFrame mainFrame;
	private JTable roiTable = new JTable();
	
	ROITable(MainFrame mainFrame) {
		setBackground(new Color(153, 204, 255));
		this.mainFrame = mainFrame;
		setLayout(new BorderLayout(0, 0));

		roiTable.setFillsViewportHeight(true);
		
		JPanel tableTitlePanel = new JPanel();
		tableTitlePanel.setBackground(new Color(204, 204, 255));
		add(tableTitlePanel, BorderLayout.NORTH);
		tableTitlePanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel tableTitleLbl = new JLabel("BookEZ");
		tableTitleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tableTitleLbl.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tableTitlePanel.add(tableTitleLbl);
		
		JLabel tableInstructLbl = new JLabel("ROI Table");
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

		//add table
		refreshTable();
	    JScrollPane scrollPane = new JScrollPane(roiTable);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	public void refreshTable() {
		String[] columnNames = new String[] {"#", "Order #", "Total", "Shipping Cost", "Price", "Shipping Paid", "Tax"};
		DefaultTableModel model = new DefaultTableModel(0, 0);
		model.setColumnIdentifiers(columnNames);

		File file = new File("accounts.txt");

		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		int i = 1;
		int nextIndex;

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if(line.substring(0, line.indexOf(",")).equals(mainFrame.getUser())) {
				nextIndex = line.indexOf(",");
				nextIndex = line.indexOf(",", nextIndex+1);
				while(line.indexOf(",", nextIndex+1) != -1) {
					String orderNum = line.substring(nextIndex+1, line.indexOf(",", nextIndex+1));
					nextIndex = line.indexOf(",", nextIndex+1);
					String total = line.substring(nextIndex+1, line.indexOf(",", nextIndex+1));
					nextIndex = line.indexOf(",", nextIndex+1);
					String shipCost = line.substring(nextIndex+1, line.indexOf(",", nextIndex+1));
					nextIndex = line.indexOf(",", nextIndex+1);
					String soldPrice = line.substring(nextIndex+1, line.indexOf(",", nextIndex+1));
					nextIndex = line.indexOf(",", nextIndex+1);
					String shipPaid = line.substring(nextIndex+1, line.indexOf(",", nextIndex+1));
					nextIndex = line.indexOf(",", nextIndex+1);
					String tax = line.substring(nextIndex+1, line.indexOf(",", nextIndex+1));
					nextIndex = line.indexOf(",", nextIndex+1);

					model.addRow(new Object[] {String.valueOf(i), orderNum, total, shipCost, soldPrice, shipPaid, tax});
					i++;
				}
				break;
			}
		}
		scanner.close();

		roiTable.setModel(model);
	}
}
