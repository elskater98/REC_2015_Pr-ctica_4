import java.util.Observable;

public abstract class MachineComponent extends Observable {

    protected boolean broken = false;

    public void setBroken(){
        boolean old_broken = isBroken(); //save the old value
        this.broken=true; // update to the new value
        if(!old_broken){
            notifyChangestoObservers();
        }
    }

    public void repair() {
        boolean old_broken = isBroken(); //save the old value
        this.broken=false; // update to the new value
        if(old_broken){
            notifyChangestoObservers();
        }
    }

    public abstract boolean isBroken();

    public void notifyChangestoObservers(){
        setChanged();
        notifyObservers();
    }

}
