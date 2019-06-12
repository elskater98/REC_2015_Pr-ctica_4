import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MachineTest {

   private MachineComponent machine;
   @BeforeEach
   void setUp(){
      machine = new Machine();
   }

   @Test
   @DisplayName("Simple test, check if machine is borken")
   void machine_isBroken(){
      assertFalse(machine.isBroken());
   }

   @Test
   @DisplayName("Simple test, check if the status of the machine change to broken")
   void setBroken(){
      machine.setBroken();
      assertTrue(machine.isBroken());
   }

   @Test
   @DisplayName("Simple test, check if works the repair of Machine")
   void repair(){
      machine.setBroken();
      assertTrue(machine.isBroken());
      machine.repair();
      assertFalse(machine.isBroken());
   }

   @Test
   @DisplayName("Complex test, check random status of the machine")
   void machine_complex(){
      assertFalse(machine.isBroken());
      machine.setBroken();
      assertTrue(machine.isBroken());
      machine.setBroken();
      assertTrue(machine.isBroken());
      machine.repair();
      assertFalse(machine.isBroken());
   }

}
