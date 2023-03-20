import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serial;
import javax.swing.JFrame;

public class View extends Frame implements PropertyChangeListener{

    @Serial
    private static final long serialVersionUID = -6104375412316282105L;

    private Controller controller;
    private Model model;

    private List listStudents;

    private TextField tfStudent;

    public TextField gettfStudent() {
        return tfStudent;
    }

    public void settfStudent(TextField text) {
        this.tfStudent = text;
    }

    public void initialize(Model model, Controller controller) {
        this.model = model;

        this.controller = controller;

        model.addPropChangeListener(this);

        setTitle("Test");

        setSize(new Dimension(400, 200));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                dispose();
            }
        });

        addComponents();

        setVisible(true);
    }

    public void addComponents(){
        listStudents = new List();

        for (String sName: model.getStudents()){
            listStudents.add(sName);
        }

        add(listStudents);

        Panel panelBottom = new Panel();

        tfStudent = new TextField("Student Name");

        Button b = new Button("Test");
        b.addActionListener(controller);

        panelBottom.add(tfStudent);
        panelBottom.add(b);

        add(panelBottom, BorderLayout.PAGE_END);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("students".equals(evt.getPropertyName())){
            listStudents.removeAll();

            for (String sName: model.getStudents()){
                listStudents.add(sName);
            }
        }
    }
}
