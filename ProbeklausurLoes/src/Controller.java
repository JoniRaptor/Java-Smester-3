import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;


public class Controller implements ActionListener, Serializable {

    private static final long serialVersionUID = -451179081073371970L;

    private Model model;
    private View view;

    public void initialize(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Point p = new Point(Integer.parseInt(view.getTfX().getText()), Integer.parseInt(view.getTfY().getText()));
        model.addPoint(p);
    }

}
