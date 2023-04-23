import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Insets;

public class Upload extends JPanel{
	private static final long serialVersionUID = 1L;
	private final JPanel uploadTitlePanel = new JPanel();
	private final JLabel uploadTitleLbl = new JLabel("BookEZ");
	private final JLabel uploadInstructLbl = new JLabel("Upload Data");
	private final JPanel uploadButtonPanel = new JPanel();
	private MainFrame mainFrame;
	private final JButton uploadFileBtn = new JButton("Upload Image");
	private final JPanel uploadBackPanel = new JPanel();
	private final JButton uploadBackButton = new JButton("Back to menu");
	private final JPanel uploadBottomPanel = new JPanel();
	private final JLabel fileLbl = new JLabel("");
	
	Upload(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		setBackground(new Color(153, 204, 255));
		setLayout(new BorderLayout(0, 0));
		uploadTitlePanel.setBackground(new Color(204, 204, 255));
		
		add(uploadTitlePanel, BorderLayout.NORTH);
		uploadTitlePanel.setLayout(new GridLayout(0, 1, 0, 0));
		uploadTitleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		uploadTitleLbl.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		uploadTitlePanel.add(uploadTitleLbl);
		uploadInstructLbl.setHorizontalAlignment(SwingConstants.CENTER);
		
		uploadTitlePanel.add(uploadInstructLbl);
		
		uploadBackPanel.setBackground(new Color(153, 204, 255));
		add(uploadBackPanel, BorderLayout.WEST);
		uploadBackPanel.add(uploadBackButton);
		uploadBackButton.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		mainFrame.goToMenu();
        	}
        });
		
		uploadButtonPanel.setBackground(new Color(153, 204, 255));
		add(uploadButtonPanel, BorderLayout.CENTER);
		GridBagLayout gbl_uploadButtonPanel = new GridBagLayout();
		gbl_uploadButtonPanel.columnWidths = new int[]{311, 0};
		gbl_uploadButtonPanel.rowHeights = new int[]{29, 235, 0};
		gbl_uploadButtonPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_uploadButtonPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		uploadButtonPanel.setLayout(gbl_uploadButtonPanel);
		GridBagConstraints gbc_uploadFileBtn = new GridBagConstraints();
		gbc_uploadFileBtn.anchor = GridBagConstraints.NORTH;
		gbc_uploadFileBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_uploadFileBtn.insets = new Insets(0, 0, 5, 0);
		gbc_uploadFileBtn.gridx = 0;
		gbc_uploadFileBtn.gridy = 0;
		uploadButtonPanel.add(uploadFileBtn, gbc_uploadFileBtn);
		uploadFileBtn.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		extract();
        	}
        });
		uploadBottomPanel.setBackground(new Color(153, 204, 255));
		
		add(uploadBottomPanel, BorderLayout.SOUTH);
		
		uploadBottomPanel.add(fileLbl);
	}
	
	public void displayFile(String filename) {
		fileLbl.setText(filename);
	}
	
	private void extract() {
		mainFrame.getManager().extractData(this);
		try {
			mainFrame.goToTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
