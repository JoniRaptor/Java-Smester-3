import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AZTableModel extends DefaultTableModel {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public AZTableModel(String[] tableColums, int rowCount) {
        super(tableColums, rowCount);
    }

    @Override
    public Object getValueAt(int row, int column) {
        if (column == 0) {
            return formatter.format((LocalDateTime)super.getValueAt(row, column));
        }

        return super.getValueAt(row, column);
    }
}
