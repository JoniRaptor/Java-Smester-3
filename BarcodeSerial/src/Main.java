import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public static void main(String[] args) {
        Frame f = new Frame("Barcode Example");
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);

                f.dispose();
            }
        });

        BarCode b = new BarCode();
        b.setCode("Hallo");

        f.add(b);
        f.pack();

        f.setVisible(true);
    }

}
