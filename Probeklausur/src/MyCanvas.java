import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MyCanvas implements ActionListener {

    private Model model;
    private View view;

    public void initialize(Model m, View v){
        this.view = v;
        this.model = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if("Hinzufuegen".equals(cmd)){
            int x = 0;
            int y = 0;
            try {
                x = Integer.parseInt(view.getXt().getText());
                y = Integer.parseInt(view.getYt().getText());
            } catch (Exception f){

            }
            this.model.addPoint(new Point(x, y));
        }

    }
}
