import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Model implements Serializable {
    @Serial
    private static final long serialVersionUID = -3693643856850048214L;
    private List<String> students;

    private PropertyChangeSupport pcs;

    public Model() {
        pcs = new PropertyChangeSupport(this); //Model ist bean über das Kommuniziert wird

        students = new ArrayList<String>();
        students.add("Kevin Klein");
    }

    public void addPropChangeListener(PropertyChangeListener listener){
        pcs.addPropertyChangeListener(listener);
    }

    public List<String> getStudents() {
        return students;
    }

    public void setStudents(List<String> students) {
        List<String>oldValue = this.students;
        this.students = students;

        pcs.firePropertyChange("students", oldValue, students);
    }

    public void addStudent(String student){
        int oldValue = students.size();
        students.add(student);

        pcs.firePropertyChange("students", oldValue, students.size());
    }
}
