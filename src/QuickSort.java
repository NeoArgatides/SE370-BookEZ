import javax.swing.JTable;

public class QuickSort implements SortingStrat_IF{
    
    public JTable sort(JTable table, int column){
        table = quickSort(table, 0, table.getRowCount()-1, column);

        return table;
    }

    private JTable quickSort(JTable table, int low, int high, int column) {
        if (low < high) {
            int pivotIndex = partition(table, low, high, column);
            quickSort(table, low, pivotIndex - 1, column);
            quickSort(table, pivotIndex + 1, high, column);
        }

        return table;
    }

    private static int partition(JTable table, int low, int high, int column) {
        //int pivot = products.get(high).getID();
        String pivot = table.getModel().getValueAt(high, column).toString();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (table.getModel().getValueAt(j, column).toString().compareTo(pivot) < 0) {
                i++;
                String temp = table.getModel().getValueAt(j, column).toString();
                table.getModel().setValueAt(pivot, j, column);
                table.getModel().setValueAt(temp, i, column);
            }
        }
        String temp = table.getModel().getValueAt(i+1, column).toString();
        table.getModel().setValueAt(pivot, i+1, column);
        table.getModel().setValueAt(temp, high, column);
        return i + 1;
    }

}
//testuser
//testpassword