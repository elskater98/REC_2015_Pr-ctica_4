import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class MachineCompositeTest {

    public MachineComposite machineComposite;

    @BeforeEach
    void setup(){
        machineComposite = new MachineComposite();
    }

    @Test
    void addComponent(){

        machineComposite.addComponent(new Machine());
        machineComposite.addComponent(new Machine());
        assertEquals(2,machineComposite.getComponentsSize());
        machineComposite.addComponent(new Machine());
        assertEquals(3,machineComposite.getComponentsSize());
    }

    @Test
    void addComponentComposite(){

        addComponent();
        MachineComposite aux_machinecomposite = new MachineComposite();
        aux_machinecomposite.addComponent(new Machine());
        aux_machinecomposite.addComponent(new Machine());
        assertEquals(2,aux_machinecomposite.getComponentsSize());

        machineComposite.addComponent(aux_machinecomposite);
        assertEquals(4,machineComposite.getComponentsSize());
    }

    @Test
    void isBrokenSimple(){
        assertFalse(machineComposite.isBroken());
    }

    @Test
    void setBrokenSimple(){
       machineComposite.setBroken();
        assertTrue(machineComposite.isBroken());
    }

    @Test
    void  setBrokenComposite(){
        Machine machine = new Machine();
        Machine machine1 = new Machine();
        machine.setBroken();

        machineComposite.addComponent(machine);
        machineComposite.addComponent(machine1);
        assertTrue(machineComposite.isBroken());

        machine.repair();
        assertFalse(machineComposite.isBroken());
    }

    @Test
    void repair(){
        setBrokenSimple();
        machineComposite.repair();
        assertFalse(machineComposite.isBroken());
    }

    @Test
    void repairComposite(){
        Machine machine = new Machine();
        Machine machine1 = new Machine();
        Machine machine2 = new Machine();

        machineComposite.addComponent(machine);
        machineComposite.addComponent(machine1);
        machineComposite.addComponent(machine2);

        machine1.setBroken();
        machine1.setBroken();
        machine2.setBroken();

        assertTrue(machineComposite.isBroken());

        machine1.repair();
        assertTrue(machineComposite.isBroken());

        machine2.repair();
        assertFalse(machineComposite.isBroken());

        MachineComposite mc = new MachineComposite();
        machineComposite.addComponent(mc);

        Machine machine3 = new Machine();
        Machine machine4 = new Machine();

        mc.addComponent(machine3);
        mc.addComponent(machine4);

        machine3.setBroken();

        assertTrue(mc.isBroken());
        assertTrue(machineComposite.isBroken());

        machine3.repair();
        assertFalse(mc.isBroken());

        assertFalse(machineComposite.isBroken());

    }
}
