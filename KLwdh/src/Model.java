import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Model {

    private List<Point> values;

    public List<Point> getValues() {
        return values;
    }

    private PropertyChangeSupport pcs;

    public Model() {
        this.values = new ArrayList<Point>();
        this.pcs = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener pl){
        pcs.addPropertyChangeListener(pl);
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

        return new Point(width * p.x / maxX, height * p.y / maxY);
    }

    public void addPoint(Point p){
        int old = this.values.size();
        this.values.add(p);

        pcs.firePropertyChange("addPoint", old, this.values.size());
    }
}
