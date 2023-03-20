import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Controller implements PropertyChangeListener, ActionListener {

    private Model m;
    private View v;

    public void initialize(Model m, View v){
        this.m = m;
        this.v = v;

        m.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propname = evt.getPropertyName();
        boolean newValue = (boolean) evt.getNewValue();

        if("IsInPause".equals(propname)){
            v.getBtnEnde().setEnabled(!newValue);
        }else if("hasStarted".equals(propname)){
            if(newValue){
                v.getBtnStart().setEnabled(false);
                v.getBtnPause().setEnabled(true);
                v.getBtnEnde().setEnabled(true);
            }else{
                v.getBtnStart().setEnabled(true);
                v.getBtnPause().setEnabled(false);
                v.getBtnEnde().setEnabled(false);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String ac = e.getActionCommand();

        if ("S".equals(ac)){
            m.clear();
            m.addStartTime();
        }else if("P".equals(ac)){
            m.addPauseTime();
        }else if("E".equals(ac)){
            m.addEndTime();
        }
    }
}
