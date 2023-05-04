import java.io.File;
import java.util.Iterator;

public class FileIterator implements Iterator<File> {
  private File[] files;
  private int position = 0;
  
  public FileIterator(File[] files){
    this.files = files;
    } //this should be enough of an iterator to qualify
    
    @override
    public boolean hasNext(){
      return position < files.length;
    } //test for next
    
    @override
    public File next(){
      return files[position++];
    } //get the next
}
