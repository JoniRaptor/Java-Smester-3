import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class Controller implements ActionListener, Serializable {

    private static final long SerialVersionUID = 5L;
    private Model m;

    private View v;

    public void initialize(Model m, View v) {
        this.m = m;
        this.v = v;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Hinzufügen".equals(e.getActionCommand())) {
            Point p = new Point(Integer.parseInt(v.getXt().getText()), Integer.parseInt(v.getYt().getText()));
            m.addPoint(p);
        }
    }
}
