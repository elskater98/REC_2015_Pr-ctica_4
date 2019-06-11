import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MachineComposite extends MachineComponent implements Observer {

    private List<MachineComponent> components =  new ArrayList<>();
    private List<MachineComponent> broken_components = new ArrayList<>();
    private boolean broken = false;

    public void addComponent(MachineComponent mc) {
         components.add(mc);
         mc.addObserver(this);
         if(mc.isBroken()){
             this.broken_components.add(mc);
             if(broken_components.size() == 1 && !this.broken){
                 setChanged();
                 notifyObservers();
             }
         }
    }

    public boolean isBroken() {
        return (this.broken || this.broken_components.size() > 0);
    }

    public void setBroken(){
        boolean old_broken = this.broken; //save the old value
        this.broken=true; // update to the new value
        if(!old_broken){
            setChanged();
            notifyObservers();//PULL
        }
    }

    public void repair() {
        boolean old_broken = this.broken; //save the old value
        this.broken=false; // update to the new value
        if(old_broken){
            setChanged();
            notifyObservers();//PULL
        }
    }

    private void setBrokenComponent(MachineComponent machineComponent){
        boolean old_broken = this.broken; //save the old value
        this.broken_components.add(machineComponent);
        if(!old_broken){
            setChanged();
            notifyObservers();//PULL
        }
    }

    private void repairComponent(MachineComponent machineComponent){
        boolean old_broken = this.broken; //save the old value
        this.broken_components.remove(machineComponent);
        if(old_broken){
            setChanged();
            notifyObservers();//PULL
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
