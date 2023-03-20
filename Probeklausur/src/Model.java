import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Model {

    private PropertyChangeSupport pcs;

    public void addPropertychangeListener(PropertyChangeListener listener){pcs.addPropertyChangeListener(listener); }

    private List<Point> values;

    public Model() {
        this.pcs = new PropertyChangeSupport(this);
        this.values = new ArrayList<Point>();
    }

    public List<Point> getValues() {
        return values;
    }

    public Point scalePoint(Point p, int width, int height) {
        int maxX = values
                .stream()
                .mapToInt(v -> v.x)
                .max().orElseThrow(NoSuchElementException::new);

        int maxY = values
                .stream()
                .mapToInt(v -> v.y)
                .max().orElseThrow(NoSuchElementException::new);
        System.out.println(maxX + "" + maxY);

        return new Point(width * p.x / maxX, height * p.y / maxY);
    }

    public void addPoint(Point p){
        int OldValue = values.size();

        this.values.add(p);

        pcs.firePropertyChange("addPoint", OldValue, values.size());
    }
}
