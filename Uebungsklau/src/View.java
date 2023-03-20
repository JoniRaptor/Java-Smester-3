import javax.swing.*;
import java.awt.*;

public class View extends JFrame {

    private Model m;

    private JButton btnStart;
    private JButton btnPause;

    private JButton btnEnde;
    private Controller c;

    public JButton getBtnStart() {
        return btnStart;
    }

    public JButton getBtnPause() {
        return btnPause;
    }

    public JButton getBtnEnde() {
        return btnEnde;
    }

    public void initialize(Model m, Controller c){
        this.m = m;
        this.c = c;

        setTitle("");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(400, 200);

        addComponent();

        setVisible(true);
    }

    private void addComponent(){
        btnStart = new JButton("S");
        btnStart.addActionListener(c);
        btnPause = new JButton("P");
        btnPause.addActionListener(c);
        btnPause.setEnabled(false);
        btnEnde = new JButton("E");
        btnEnde.addActionListener(c);
        btnEnde.setEnabled(false);

        JToolBar toolbar = new JToolBar();
        toolbar.add(btnStart);
        toolbar.add(btnPause);
        toolbar.add(btnEnde);

        add(toolbar, BorderLayout.PAGE_START);

        JTable table = new JTable((m.getTableModel()));
        add (new JScrollPane(table));
    }
}
