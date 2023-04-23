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

import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ROITable extends JPanel{
	private static final long serialVersionUID = 1L;
	private MainFrame mainFrame;
	private JTable roiTable = new JTable();
	private JLabel tableProfitLbl = new JLabel();
	private double totalProfit = 0;
	private static final DecimalFormat decfor = new DecimalFormat("0.00");
	
	private User loggedUser = null;
	private ReceiptDAO receiptDAO;

	ROITable(MainFrame mainFrame, ReceiptDAO receiptDAO ) {

		this.loggedUser = mainFrame.getUser();
		this.receiptDAO = receiptDAO;

		
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

		JPanel tableProfitPanel = new JPanel();
		tableProfitPanel.setBackground(new Color(153, 204, 255));
		add(tableProfitPanel, BorderLayout.SOUTH);
		tableProfitPanel.setLayout(new BorderLayout(0, 0));

	
		if(loggedUser!=null){
			refreshTable();
		}
		
	    JScrollPane scrollPane = new JScrollPane(roiTable);
		add(scrollPane, BorderLayout.CENTER);

		Font f = tableProfitLbl.getFont();
		tableProfitLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tableProfitLbl.setFont(f.deriveFont(f.getStyle() | Font.BOLD, 15));
		tableProfitPanel.add(tableProfitLbl);
	}
	//
	public void refreshTable() {
		totalProfit = 0;
		String[] columnNames = new String[] {"#", "Order #", "Total", "Shipping Cost", "Price", "Shipping Paid", "Tax"};
		DefaultTableModel model = new DefaultTableModel(0, 0);
		model.setColumnIdentifiers(columnNames);
		int i = 1;

					
		//modify this to add on the database
		model.addRow(new Object[]{"1", "1234", "$50.00", "$5.00", "$45.00", "$2.00", "$3.00"});
		i++;
		model.addRow(new Object[]{"2", "5678", "$100.00", "$10.00", "$90.00", "$5.00", "$5.00"});
		totalProfit += mainFrame.getManager().profitCalc("100", "200", "122");
	

		tableProfitLbl.setText("Total profit: $" + String.valueOf(decfor.format(totalProfit)) + "");
		roiTable.setModel(model);
		}
	}
	// 	String[] columnNames = new String[] {"#", "Order #", "Total", "Shipping Cost", "Price", "Shipping Paid", "Tax"};
	// 	DefaultTableModel model = new DefaultTableModel(0,0);
	// 	model.setColumnIdentifiers(columnNames);

	// 	if(loggedUser!=null )
	// 	{
	// 	 System.out.println("refreshedw/loggeduser");
	// 	 totalProfit = 0;
	// 	 int i = 1;
	// 	 try {
	// 		List<Receipt> receipts = receiptDAO.getReceiptsForUser(loggedUser);
	// 		for (Receipt receipt : receipts) {
	// 			String orderNum = String.valueOf(receipt.getId());
	// 			String total = String.valueOf(receipt.getTotal());
	// 			String shipCost = String.valueOf(receipt.getShippingPaid());
	// 			String soldPrice = String.valueOf(receipt.getPrice());
	// 			String shipPaid = String.valueOf(receipt.getShippingPaid());
	// 			String tax = String.valueOf(receipt.getTax());
	// 			// modify this to add on the database
	// 			model.addRow(new Object[] {String.valueOf(i), orderNum, total, shipCost, soldPrice, shipPaid, tax});
	// 			System.out.println(i+ " "+ receipt.getOrderNumber() + ", " + receipt.getTotal() + ", " + receipt.getShippingCost() + ", " + receipt.getPrice() + ", " + receipt.getShippingPaid() + ", " + receipt.getTax());

	// 			totalProfit += mainFrame.getManager().profitCalc(total, shipCost, tax);
	// 			i++;
	// 		}

			
	// 	} catch (SQLException e) {
	// 		// TODO Auto-generated catch block
	// 		e.printStackTrace();
	// 	}
	// 	System.out.println("Number of rows in the model: " + model.getRowCount());
	// 	tableProfitLbl.setText("Total profit: $" + String.valueOf(decfor.format(totalProfit)) + "");
	// 	roiTable.setModel(model);
	// }else
	// {
	// System.out.println("other2");
	// }
	/****************************************************** */
	
	// 	totalProfit = 0;
	// 	String[] columnNames = new String[] {"#", "Order #", "Total", "Shipping Cost", "Price", "Shipping Paid", "Tax"};
	// 	DefaultTableModel model = new DefaultTableModel(0, 0);
	// 	model.setColumnIdentifiers(columnNames);

	// 	File file = new File("accounts.txt");

	// 	Scanner scanner = null;
	// 	try {
	// 		scanner = new Scanner(file);
	// 	} catch (FileNotFoundException e) {
	// 		throw new RuntimeException(e);
	// 	}
	// 	int i = 1;
	// 	int nextIndex;

	// 	while (scanner.hasNextLine()) {
	// 		String line = scanner.nextLine();
	// 		if(line.substring(0, line.indexOf(",")).equals(mainFrame.getUser())) {
	// 			nextIndex = line.indexOf(",");
	// 			nextIndex = line.indexOf(",", nextIndex+1);
	// 			while(line.indexOf(",", nextIndex+1) != -1) {
	// 				String orderNum = line.substring(nextIndex+1, line.indexOf(",", nextIndex+1));
	// 				nextIndex = line.indexOf(",", nextIndex+1);
	// 				String total = line.substring(nextIndex+1, line.indexOf(",", nextIndex+1));
	// 				nextIndex = line.indexOf(",", nextIndex+1);
	// 				String shipCost = line.substring(nextIndex+1, line.indexOf(",", nextIndex+1));
	// 				nextIndex = line.indexOf(",", nextIndex+1);
	// 				String soldPrice = line.substring(nextIndex+1, line.indexOf(",", nextIndex+1));
	// 				nextIndex = line.indexOf(",", nextIndex+1);
	// 				String shipPaid = line.substring(nextIndex+1, line.indexOf(",", nextIndex+1));
	// 				nextIndex = line.indexOf(",", nextIndex+1);
	// 				String tax = line.substring(nextIndex+1, line.indexOf(",", nextIndex+1));
	// 				nextIndex = line.indexOf(",", nextIndex+1);
					
	// 				//modify this to add on the database
	// 				model.addRow(new Object[] {String.valueOf(i), orderNum, total, shipCost, soldPrice, shipPaid, tax});
	// 				totalProfit += mainFrame.getManager().profitCalc(total, shipCost, tax);
	// 				i++;
	// 			}
	// 			break;
	// 		}
	// 	}
	// 	scanner.close();
	// 	tableProfitLbl.setText("Total profit: $" + String.valueOf(decfor.format(totalProfit)) + "");
	// 	roiTable.setModel(model);
	

	


