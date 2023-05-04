import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.pdfbox.pdmodel.PDDocument; 
import org.apache.pdfbox.text.PDFTextStripper;

public class ROIManager {
	private Integer nextEnd;
	private MainFrame mainFrame;
	////DAO 
	private ReceiptDAO receiptDAO;
	private UserDAO userDAO;
    ///
	ROIManager(MainFrame mainFrame,ReceiptDAO receiptDAO, UserDAO userDAO) {
		this.mainFrame = mainFrame;
        this.receiptDAO = receiptDAO;
		this.userDAO = userDAO;
	}
	
	public void extractData(Upload uploadPage) {
		JFileChooser fileUpload = new JFileChooser();//creating file chooser//testing 
        fileUpload = new JFileChooser();//creating file chooser
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Documents", "pdf");
        fileUpload.setFileFilter(filter);//applying pdf filter to files uploaded
        
        fileUpload.setMultiSelectionEnabled(true);//testing 

        int response = fileUpload.showOpenDialog(null);//saves users device for file selection

        if(response == JFileChooser.APPROVE_OPTION){//make sure file selecteds path is retrieved
        
            try{
                File files[] = fileUpload.getSelectedFiles();//array of files that contains selected files  

		while (fileIterator.hasNext()) {
			File file = fileIterator.next();
                //for(File file : files){//itterate through collected files 
                 
			
                    //getting path name and convering into a displayable method for user 
                    String path = file.getAbsolutePath() + "\n";//collect path 
                    path = convertAndFind(path, "Ebay Orders/", 0, 12);
                    uploadPage.displayFile(path + "\n");//displaying what file was uploaded

                    FileInputStream fis = new FileInputStream(file.getAbsolutePath());//create new input stream
                    PDDocument pdfDocument = PDDocument.load(fis);//load in pdf document 
                    PDFTextStripper pdfTextStripper = new PDFTextStripper();//obtain text
                    String docText = pdfTextStripper.getText(pdfDocument);//turning text into string 

                    outputWriter(docText);//getting info from each pdf and adding to output.text file

                    pdfDocument.close();//closing document
                    fis.close();//closing file input stream
                }
                
            } catch(java.io.IOException ex){//catching exception thrown for invalid document inputs
                System.out.println("File cannot be opened: " + ex);//printing error message 
            } 
        }
	}
	
	//used to extract the desired information from an ebay order reciept pdf and add new info to output.text file
    //boolean used to determine wether the extracted information should be stored in the vector
    protected void outputWriter(String s){
        //strings to collect information
        String orderNum, total, shipCost, soldPrice, shipPaid, tax, profitC;
        
        nextEnd = 0;//setting int to 0 for each new file being read 

        //collecting each string segment from the files 
        orderNum = convertAndFind(s, "Order number ", nextEnd, 13);
        total = convertAndFind(s, "$", nextEnd, 1);
        shipCost = convertAndFind(s, "Cost: $", nextEnd, 7);
        System.out.println(shipCost);
        soldPrice = convertAndFind(s, "$", nextEnd, 1);
        shipPaid = convertAndFind(s, "$", nextEnd, 1);
        tax = convertAndFind(s, "$", nextEnd, 1);
        /************** */
        User loggedUser;
        try {
            
            loggedUser = userDAO.getUserByUsername(mainFrame.getUser());
            // receiptDAO.addReceiptToDatabase(loggedUser, orderNum, total, shipCost, soldPrice, shipPaid, tax);
            // orderNum = "12345";
            // total = "10.50";
            // shipCost = "5.00";
            // soldPrice = "15.00";
            // shipPaid = "2.00";
            // tax = "1.50";

        try {
            boolean success = receiptDAO.addReceiptToDatabase(loggedUser, orderNum, total, shipCost, soldPrice, shipPaid, tax);
            if (success) {
                System.out.println("Receipt added to database.");
            } else {
                System.out.println("Failed to add receipt to database.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding receipt to database: " + e.getMessage());
        }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//end of extractInfo
	
	String convertAndFind(String docText, String id, int idInt, int mod){
        String result;//result to be returned 
        nextEnd = idInt;//setting to idInt 

        int startResult = docText.indexOf(id, nextEnd);//finding the string from next location
        int endResult = docText.indexOf("\n", startResult);//finding end of line 
        result = docText.substring(startResult + mod, endResult);//getting result from positions 

        nextEnd = endResult;//setting to the end of result in order to continue to look through the text
        return result;//returning result 

    }//end of convert and find
	
	double profitCalc(String total, String shipCost, String tax){

        double profit = 0.0;//used for calculations
        
        //turning each string into a double for calculations 
        //        double t = Double.parseDouble(total.substring(1));

        double t = Double.parseDouble(total);
        double sC = Double.parseDouble(shipCost);
        double ta = Double.parseDouble(tax);
        
        profit = t - sC - ta;//calculating profit 
        
        profit = Math.round(profit * 100) / 100.0;//rounding off for set precision

        return profit;//returning profit obtained

    }//end of profit calculation
}
