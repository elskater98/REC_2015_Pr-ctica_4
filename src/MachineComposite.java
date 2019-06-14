import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MachineComposite extends MachineComponent implements Observer {

    private List<MachineComponent> components =  new ArrayList<>();
    private List<MachineComponent> broken_components = new ArrayList<>();


    public void addComponent(MachineComponent mc) {
         components.add(mc);
         mc.addObserver(this);
         if(mc.isBroken()){
             this.broken_components.add(mc);
             if(broken_components.size() == 1 && !this.broken){
                 notifyChangestoObservers();
             }
         }
    }

    public boolean isBroken() {
        if(broken){
            return true;
        }

        for (MachineComponent mc : components){
            if(mc.isBroken()){
                return true;
            }
        }

        return false;
    }

    private void setBrokenComponent(MachineComponent machineComponent){
        boolean old_broken = isBroken(); //save the old value
        this.broken_components.add(machineComponent);
        if(!old_broken){
            notifyChangestoObservers();
        }
    }

    private void repairComponent(MachineComponent machineComponent){
        boolean old_broken = isBroken(); //save the old value
        this.broken_components.remove(machineComponent);
        if(old_broken){
            notifyChangestoObservers();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        MachineComponent machineComponent = (MachineComponent)o;
        if(!machineComponent.isBroken()){
            repairComponent(machineComponent);
        }else{
            setBrokenComponent(machineComponent);
        }
    }

    public int getComponentsSize() {
        return components.size();
    }
}
