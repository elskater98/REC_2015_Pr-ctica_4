public class Machine extends MachineComponent{

    private boolean broken = false;

    public void setBroken() {
        boolean old_broken = this.broken; //save the old value
        this.broken=true; // update to the new value
        if(!old_broken){
            notifyChangestoObservers();
        }
    }

    public void repair() {
        boolean old_broken = this.broken; //save the old value
        this.broken=false; // update to the new value
        if(old_broken){
            notifyChangestoObservers();
        }
    }

    public boolean isBroken() { return broken; }
}
