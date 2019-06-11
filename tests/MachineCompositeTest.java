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
        //NO EXISTEIXEN COMPONENTS REPETITS
        machineComposite.addComponent(new Machine());
        machineComposite.addComponent(new Machine());
        assertEquals(2,machineComposite.getComponentsSize());
        machineComposite.addComponent(new Machine());
        assertEquals(3,machineComposite.getComponentsSize());
    }

    @Test
    void isBrokenSimple(){
        assertFalse(machineComposite.isBroken());
    }

    @Test
    void setBroken(){
        machineComposite.setBroken();
        assertTrue(machineComposite.isBroken());
    }

    @Test
    void repair(){
        setBroken();
        machineComposite.repair();
        assertFalse(machineComposite.isBroken());
    }

    @Test
    void setBrokenComponent(){
        machineComposite.addComponent(new Machine());

    }


}
