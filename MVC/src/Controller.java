import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.io.Serializable;

public class Controller implements ActionListener, Serializable {

    @Serial
    private static final long serialVersionUID = 8326282159252128501L;

    private Model model;
    private View view;

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

    public Controller(){

    }

    public void initialize(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if("Test".equals(cmd)){
            model.addStudent(view.gettfStudent().getText());
        }




        //model.addStudent(view.gettfStudent().getText());
    }
}
