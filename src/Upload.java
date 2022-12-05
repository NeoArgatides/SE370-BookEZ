import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Upload extends JPanel{
	private final JPanel uploadTitlePanel = new JPanel();
	private final JLabel uploadTitleLbl = new JLabel("BookEZ");
	private final JLabel uploadInstructLbl = new JLabel("Upload Data");
	private final JPanel uploadButtonPanel = new JPanel();
	private final JButton uploadBackBtn = new JButton("Back to Menu");
	private MainFrame mainFrame;
	
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
		uploadButtonPanel.setBackground(new Color(153, 204, 255));
		
		add(uploadButtonPanel, BorderLayout.CENTER);
		GridBagLayout gbl_uploadButtonPanel = new GridBagLayout();
		gbl_uploadButtonPanel.columnWidths = new int[]{0, 0};
		gbl_uploadButtonPanel.rowHeights = new int[]{0, 0};
		gbl_uploadButtonPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_uploadButtonPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		uploadButtonPanel.setLayout(gbl_uploadButtonPanel);
		
		GridBagConstraints gbc_uploadBackBtn = new GridBagConstraints();
		gbc_uploadBackBtn.gridx = 0;
		gbc_uploadBackBtn.gridy = 0;
		uploadButtonPanel.add(uploadBackBtn, gbc_uploadBackBtn);
		uploadBackBtn.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		mainFrame.goToMenu();
        	}
        });
	}
}
