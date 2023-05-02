// import java.util.ArrayList;
// import java.util.Li
import javax.swing.table.DefaultTableModel;
//import javax.swing.table.*;
import javax.swing.JTable;


public class InsertionSort implements SortingStrat_IF{
    
    public JTable sort(JTable table, int column) {
        for (int i = 1; i < table.getRowCount(); i++){
            int j = i;

            while(j > 0 && table.getModel().getValueAt(j, column).toString().compareTo(table.getModel().getValueAt(j-1, column).toString()) < 0) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.moveRow(j, j, j-1);
                j--;
            }
        }
        return table;
    }
}