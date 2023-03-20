import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View extends JFrame implements PropertyChangeListener {

    private Model model;
    private MyCanvas canvas;
    private JTextField xt;
    private JTextField yt;

    private JDiagram d;

    public JTextField getXt() {
        return xt;
    }

    public JTextField getYt() {
        return yt;
    }

    public void initialize(Model m, MyCanvas c){

    this.canvas = c;

    this.model = m;

    this.model.addPropertychangeListener(this);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    addComponents();

    setSize(800, 400);



    setVisible(true);
    }

    private void addComponents(){
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout());

        JLabel x = new JLabel("x:");
        this.xt = new JTextField("0");
        xt.setPreferredSize(new Dimension(50, 24));
        JLabel y = new JLabel("y:");
        this.yt = new JTextField("0");
        yt.setPreferredSize(new Dimension(50, 24));

        JButton ad = new JButton("Hinzufuegen");
        ad.addActionListener(this.canvas);

        p1.add(x);
        p1.add(xt);
        p1.add(y);
        p1.add(yt);
        p1.add(ad);

        this.add(p1, BorderLayout.NORTH);
        d = new JDiagram();
        d.initialize(this.model);

        add(d);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("addPoint".equals(evt.getPropertyName())){
            this.d.repaint();
        }

    }
}
