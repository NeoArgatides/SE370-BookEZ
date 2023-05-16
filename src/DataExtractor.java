import java.io.File;
import java.io.FileInputStream;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class DataExtractor implements DataExtractor_IF {

    private ROIManager r;
    // ExtractData ex;

    public DataExtractor(ROIManager r) {
        this.r = r;
    }

    @Override
    public void extractData(Upload uploadPage) {
        JFileChooser fileUpload = new JFileChooser(); // creating file chooser
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Documents", "pdf");
        fileUpload.setFileFilter(filter); // applying pdf filter to files uploaded

        fileUpload.setMultiSelectionEnabled(true); // testing

        int response = fileUpload.showOpenDialog(null); // saves users device for file selection

        if (response == JFileChooser.APPROVE_OPTION) { // make sure file selecteds path is retrieved

            try {
                File files[] = fileUpload.getSelectedFiles(); // array of files that contains selected files
                FileIterator fileIterator = new FileIterator(files); // Create an instance of FileIterator

                while (fileIterator.hasNext()) {
                    File file = fileIterator.next();

                    // getting path name and converting into a displayable method for user
                    String path = file.getAbsolutePath() + "\n"; // collect path
                    path = r.convertAndFind(path, "Ebay Orders/", 0, 12);
                    uploadPage.displayFile(path + "\n"); // displaying what file was uploaded

                    FileInputStream fis = new FileInputStream(file.getAbsolutePath()); // create new input stream
                    PDDocument pdfDocument = PDDocument.load(fis); // load in pdf document
                    PDFTextStripper pdfTextStripper = new PDFTextStripper(); // obtain text
                    String docText = pdfTextStripper.getText(pdfDocument); // turning text into string

                    r.outputWriter(docText); // getting info from each pdf and adding to output.text file

                    pdfDocument.close(); // closing document
                    fis.close(); // closing file input stream
                }

            } catch (java.io.IOException ex) { // catching exception thrown for invalid document inputs
                System.out.println("File cannot be opened: " + ex); // printing error message
            }
        }
    }

}
