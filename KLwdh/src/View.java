import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;

public class View extends JFrame implements PropertyChangeListener, Serializable {

    private Model m;

    private  Controller c;

    private JTextField xt;

    private JTextField yt;

    public JTextField getXt() {
        return xt;
    }

    public JTextField getYt() {
        return yt;
    }

    public void initialize(Model m, Controller c){
        this.m = m;
        this.c = c;

        m.addPropertyChangeListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(500, 300);

        addComponents();

        setVisible(true);
    }

    private void addComponents(){
        JLabel x = new JLabel("X:");
        xt = new JTextField();
        xt.setPreferredSize(new Dimension(50, 24));

        JLabel y = new JLabel("Y:");
        yt = new JTextField();
        yt.setPreferredSize(new Dimension(50, 24));

        JButton Badd = new JButton("Hinzufügen");
        Badd.addActionListener(c);

        JPanel p1 = new JPanel();
        p1.add(x);
        p1.add(xt);
        p1.add(y);
        p1.add(yt);
        p1.add(Badd);

        add(p1, BorderLayout.NORTH);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String code = evt.getPropertyName();

        if ("addPoint".equals(code)){
            repaint();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.drawLine(20, 75, 20, getHeight()-20);
        g.drawLine(20, getHeight()-20, getWidth()-20, getHeight()-20);

        for (Point p: m.getValues()){
            Point pt = m.scalePoint(p, getWidth()-20, getHeight()-75-20);
            g.fillRect(pt.x-2, getHeight()-20-pt.y-2, 4, 4);
        }
    }
}
