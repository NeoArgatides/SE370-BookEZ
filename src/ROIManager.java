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

                for(File file : files){//itterate through collected files 
                 
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
        System.out.println("*******----START of outputWriter()----*****");
        //strings to collect information
        String orderNum, total, shipCost, soldPrice, shipPaid, tax, profitC;
        
        nextEnd = 0;//setting int to 0 for each new file being read 

        //collecting each string segment from the files 
        orderNum = convertAndFind(s, "Order number ", nextEnd, 13);
        System.out.println("orderNum: "+orderNum);
        total = convertAndFind(s, "$", nextEnd, 1);
        System.out.println("total: "+total);
        shipCost = convertAndFind(s, "$", nextEnd, 1);
        System.out.println("shipCost: "+shipCost);
        soldPrice = convertAndFind(s, "$", nextEnd, 1);
        System.out.println("soldPrice: "+soldPrice);
        shipPaid = convertAndFind(s, "$", nextEnd, 1);
        System.out.println("shipPaid: "+shipPaid);
        tax = "0";
        System.out.println("tax: "+tax);

        System.out.println("TESTING");
        // System.out.println("information: "+ orderNum + " " + total + " " + shipCost + " " + soldPrice + " " + shipPaid + " " + tax);
        //adding all collected information to output.text file

        /************** */
        User loggedUser;
        try {
            loggedUser = userDAO.getUserByUsername(mainFrame.getUser());
            System.out.println("Testing userid: " + loggedUser.getId());

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("*******----END of outputWriter()----*****");

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
        double t = Double.parseDouble(total.substring(1));
        double sC = Double.parseDouble(shipCost.substring(1));
        double ta = Double.parseDouble(tax.substring(1));
        
        profit = t - sC - ta;//calculating profit 
        
        profit = Math.round(profit * 100) / 100.0;//rounding off for set precision

        return profit;//returning profit obtained

    }//end of profit calculation
	
	private void addInfoToDatabase(String orderNum, String total, String shipCost, String soldPrice, String shipPaid, String tax) throws IOException {
        System.out.println("IN addToDatabase()");
        System.out.println("orderNum: "+orderNum);
        System.out.println("total: "+total);
        System.out.println("shipCost: "+shipCost);
        System.out.println("soldPrice: "+soldPrice);
        System.out.println("shipPaid: "+shipPaid);
        System.out.println("tax: "+tax);


        System.out.println("information: "+ orderNum + " " + total + " " + shipCost + " " + soldPrice + " " + shipPaid + " " + tax);

        File file = new File("accounts.txt");

		try {
		    Scanner scanner = new Scanner(file);

		    int lineNum = 0;
		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
                if(!line.equals("")) {
                    if (line.substring(0, line.indexOf(",")).equals(mainFrame.getUser())) {
                        Path path = Paths.get("accounts.txt");
                        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
                        String userLine = lines.get(lineNum);
                        lines.set(lineNum, userLine.replace("\n", "") + orderNum + ',' + total + ',' + shipCost + ',' + soldPrice + ',' + shipPaid + ',' + tax + ',');
                        Files.write(path, lines, StandardCharsets.UTF_8);
                    }
                }
                lineNum++;
		    }
		    scanner.close();
		} catch(FileNotFoundException e) { 
			e.printStackTrace();
		}
    }//end of creating ROI table 
}