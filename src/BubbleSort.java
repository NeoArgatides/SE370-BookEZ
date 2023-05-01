// import java.util.ArrayList;
// import java.util.Li
import javax.swing.table.DefaultTableModel;
//import javax.swing.table.*;
import javax.swing.JTable;


public class BubbleSort implements SortingStrat_IF{
    
    public JTable sort(JTable table, int column) {
        for (int i = 0; i < table.getRowCount(); i++){
            for (int j = 0; j < table.getRowCount() - i - 1; j++) {
                if (table.getModel().getValueAt(j, column).toString().compareTo(table.getModel().getValueAt(j+1, column).toString()) > 0) { //j is larger than j+1
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.moveRow(j, j, j+1);
                    System.out.println("Swapped row" + j);
                }
            }
        }
        return table;
    }
}