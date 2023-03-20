import javax.swing.*;
import java.awt.*;

public class JDiagram extends JComponent {

    private Model model;

    public void initialize(Model m){
        this.model = m;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D c = (Graphics2D) g;
        c.setColor(Color.black);
        c.drawLine(15, 20, 15, getHeight()-15);
        c.drawLine(15, getHeight()-15, getWidth()-20, getHeight()-15);

        for (Point p: this.model.getValues()){
            System.out.println(p.x + " " + p.y);
            Point P = this.model.scalePoint(p, getWidth()-15-20, getHeight()-15-20);
            c.fillRect(P.x+13, getHeight()-17-P.y, 4, 4);
        }
    }
}
