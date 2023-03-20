import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View extends JFrame implements PropertyChangeListener {
    private int width = 800;
    private int height = 400;
    private int axesMarginBottomAndLeft = 15;
    private int axesMarginRight = 20;
    private int axesMarginTop = 100;
    private JTextField txtX;
    private JTextField txtY;
    private Controller controller;
    private Model model;
    private java.util.List<Point> points;

    public void initialize(Controller c, Model m) {
        this.controller = c;
        this.model = m;

        model.addPropChangeListener(this);

        setTitle("Diagramm");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        addComponents();
        setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        int w = getWidth();
        int h = getHeight();
        g2.setStroke(new BasicStroke(2));
        g2.drawLine(axesMarginBottomAndLeft, h - axesMarginBottomAndLeft, w - (axesMarginBottomAndLeft + axesMarginRight), h - axesMarginBottomAndLeft); // Draw x-axis
        g2.drawLine(axesMarginBottomAndLeft, h - axesMarginBottomAndLeft, axesMarginBottomAndLeft, axesMarginTop); // Draw y-axis

        int arrowXX = w - (axesMarginBottomAndLeft + axesMarginRight);
        int arrowXY = h - axesMarginBottomAndLeft;
        Polygon arrowX = new Polygon();
        arrowX.addPoint( arrowXX + 5,arrowXY);
        arrowX.addPoint( arrowXX, arrowXY + 5);
        arrowX.addPoint( arrowXX,arrowXY - 5);
        g2.fillPolygon(arrowX);

        int arrowYX = axesMarginBottomAndLeft;
        int arrowYY = axesMarginTop;
        Polygon arrowY = new Polygon();
        arrowY.addPoint( arrowYX,arrowYY - 5);
        arrowY.addPoint( arrowYX + 5, arrowYY);
        arrowY.addPoint( arrowYX - 5, arrowYY);
        g2.fillPolygon(arrowY);

        points = new java.util.ArrayList<Point>();

        for (Point p : model.getValues()) {
            points.add(p);
            Point scaledPoint = model.scalePoint(p, getCoordinateWidth(), getCoordinateHeight());
            g2.drawRect(scaledPoint.x-2, (getHeight() - axesMarginBottomAndLeft) - scaledPoint.y - 2, 4, 4);
        }
    }

    public void addComponents() {
        JLabel lblX = new JLabel("X:");
        txtX = new JTextField("0");
        txtX.setPreferredSize( new Dimension( 50, 24 ) );
        JLabel lblY = new JLabel("Y:");
        txtY = new JTextField("0");
        txtY.setPreferredSize( new Dimension( 50, 24 ) );
        JButton btnAdd = new JButton("Hinzufügen");
        btnAdd.addActionListener(controller);

        JPanel inputPanel = new JPanel();

        inputPanel.add(lblX);
        inputPanel.add(txtX);
        inputPanel.add(lblY);
        inputPanel.add(txtY);
        inputPanel.add(btnAdd);

        add(inputPanel, BorderLayout.PAGE_START);
    }

    public JTextField getTfX() {
        return txtX;
    }

    public JTextField getTfY() {
        return txtY;
    }

    public int getCoordinateWidth() {
        return getWidth() - axesMarginRight - axesMarginBottomAndLeft;
    }
    public int getCoordinateHeight() {
        return getHeight() - axesMarginBottomAndLeft - axesMarginTop;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}
