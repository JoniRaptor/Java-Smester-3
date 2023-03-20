import javax.swing.table.DefaultTableModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class Model {
    private static final String[] TABLE_COLUMS = {"Zeitpunkt", "Aktion"};
    private static final String LABEL_KOMMEN = "KOMMEN";
    private static final String LABEL_PAUSE_START = "PAUSE START";
    private static final String LABEL_PAUSE_END = "PAUSE ENDE";
    private static final String LABEL_END = "GEHEN";

    private final PropertyChangeSupport propertyChangeSupport;
    private final AZTableModel tableModel;

    private boolean hasStarted = false;
    private boolean isInPause = false;

    public Model() {
        propertyChangeSupport = new PropertyChangeSupport(this);
        tableModel = new AZTableModel(Model.TABLE_COLUMS, 0);
    }

    public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
    }

    public void addStartTime() {
        hasStarted = true;

        tableModel.addRow(new Object[]{LocalDateTime.now(), Model.LABEL_KOMMEN});

        propertyChangeSupport.firePropertyChange("hasStarted", false, true);
    }

    public void addPauseTime() {
        isInPause = !isInPause;

        if (isInPause) {
            tableModel.addRow(new Object[]{LocalDateTime.now(), Model.LABEL_PAUSE_START});
        } else {
            tableModel.addRow(new Object[]{LocalDateTime.now(), Model.LABEL_PAUSE_END});
        }

        propertyChangeSupport.firePropertyChange("isInPause", !isInPause, isInPause);
    }

    public void addEndTime() {
        hasStarted = false;

        tableModel.addRow(new Object[]{LocalDateTime.now(), Model.LABEL_END});

        String strTotalTime = Model.LABEL_END + ", ∑ " + getTotalTime();
        tableModel.getDataVector().lastElement().set(1, strTotalTime);

        propertyChangeSupport.firePropertyChange("hasStarted", !hasStarted, hasStarted);
    }

    public void clear() {
        tableModel.setRowCount(0);
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    private String getTotalTime() {
        Vector<Vector> data = tableModel.getDataVector();

        long wholeWorkingTime = Duration
                .between(
                    (LocalDateTime)data.firstElement().firstElement(),
                    (LocalDateTime)data.lastElement().firstElement()
                ).toMillis();

        int numBreakPoints = data.size() - 2;
        long wholePauseTime = 0;
        for (int i = 1; i <= numBreakPoints - 1; i += 2) {
            wholePauseTime = Duration
                    .between(
                            (LocalDateTime) data.elementAt(i).firstElement(),
                            (LocalDateTime) data.elementAt(i + 1).firstElement()
                    ).toMillis();
        }

        long overallWorkTime = wholeWorkingTime - wholePauseTime;
        return String.format("%dh, %dm",
                TimeUnit.MILLISECONDS.toHours(overallWorkTime),
                TimeUnit.MILLISECONDS.toMinutes(overallWorkTime) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(overallWorkTime)));
    }
}
