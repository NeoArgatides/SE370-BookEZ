import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
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

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ROITable extends JPanel implements dbAO_IF {
	private static final long serialVersionUID = 1L;
	private MainFrame mainFrame;
	private JTable roiTable = new JTable();
	private JLabel tableProfitLbl = new JLabel();
	private double totalProfit = 0;
	private static final DecimalFormat decfor = new DecimalFormat("0.00");
	
	private ReceiptDAO receiptDAO;
	private UserDAO userDAO;
	ROITable(MainFrame mainFrame, ReceiptDAO receiptDAO, UserDAO userDAO) throws SQLException {


		setBackground(new Color(153, 204, 255));
		this.mainFrame = mainFrame;
		this.receiptDAO = receiptDAO;
		this.userDAO = userDAO;
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

		JButton sortBtn = new JButton("Sort");
		tableBackPanel.add(sortBtn);
		sortBtn.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		sort(6);
				System.out.print("jdsfbjdasfb");
        	}
        });

		JPanel tableProfitPanel = new JPanel();
		tableProfitPanel.setBackground(new Color(153, 204, 255));
		add(tableProfitPanel, BorderLayout.SOUTH);
		tableProfitPanel.setLayout(new BorderLayout(0, 0));

		//add table
		refreshTable();
	    JScrollPane scrollPane = new JScrollPane(roiTable);
		add(scrollPane, BorderLayout.CENTER);

		Font f = tableProfitLbl.getFont();
		tableProfitLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tableProfitLbl.setFont(f.deriveFont(f.getStyle() | Font.BOLD, 15));
		tableProfitPanel.add(tableProfitLbl);
	}

	public void refreshTable() throws SQLException {
		User loggedUser;

		loggedUser = userDAO.getUserByUsername(mainFrame.getUser());
		totalProfit = 0;
		String[] columnNames = new String[] {"#", "Order #", "Total", "Shipping Cost", "Price", "Shipping Paid", "Tax"};
		DefaultTableModel model = new DefaultTableModel(0,0);
		model.setColumnIdentifiers(columnNames);
		
		if(loggedUser!=null )
		{
		 System.out.println("refreshed/loggeduser");
		 totalProfit = 0;
		 int i = 1;
		 try {
			List<Receipt> receipts = receiptDAO.getReceiptsForUser(loggedUser);
			for (Receipt receipt : receipts) {
				String orderNum = String.valueOf(receipt.getId());
				String total = String.valueOf(receipt.getTotal());
				String shipCost = String.valueOf(receipt.getShippingPaid());
				String soldPrice = String.valueOf(receipt.getPrice());
				String shipPaid = String.valueOf(receipt.getShippingPaid());
				String tax = String.valueOf(receipt.getTax());
				// modify this to add on the database
				model.addRow(new Object[] {String.valueOf(i), orderNum, total, shipCost, soldPrice, shipPaid, tax});
				System.out.println(i+ " "+ receipt.getOrderNumber() + ", " + receipt.getTotal() + ", " + receipt.getShippingCost() + ", " + receipt.getPrice() + ", " + receipt.getShippingPaid() + ", " + receipt.getTax());

				totalProfit += mainFrame.getManager().profitCalc(total, shipCost, tax);
				i++;
			}

			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Number of rows in the model: " + model.getRowCount());
			tableProfitLbl.setText("Total profit: $" + String.valueOf(decfor.format(totalProfit)) + "");
			roiTable.setModel(model);
		} else
		{
			System.out.println("other2");
		}
	}

	public void sort(int col){
		SortingStrat_IF sortingStrat = null;

		String colName = roiTable.getColumnName(col);

		switch(colName) {
			case "#":
				sortingStrat = new BubbleSort();
				break;
			case "Order #":
				sortingStrat = new QuickSort();
				break;
			case "Total":
				sortingStrat = new QuickSort();
				break;
			case "Shipping Cost":
				sortingStrat = new BubbleSort();
				break;
			case "Price":
				sortingStrat = new QuickSort();
				break;
			case "Shipping Paid":
				sortingStrat = new BubbleSort();
				break;
			case "Tax":
				sortingStrat = new BubbleSort();
		}

		roiTable = sortingStrat.sort(roiTable, col);
	}



	public class TableHeaderMouseListener extends MouseAdapter {
		
		private JTable table;

		public TableHeaderMouseListener() {
			this.table = roiTable;
			System.out.println("init");
		}
		
		public void mouseClicked(MouseEvent event) {
			Point point = event.getPoint();
			int column = table.columnAtPoint(point);
			sort(column);
			System.out.println("click");
		}
	}

}
