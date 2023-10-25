import org.junit.Test;
import motorcyclerental.src.constants.MotorCycleConstants;
import motorcyclerental.src.factory.MotorCycleFactory;
import motorcyclerental.src.motorcycles.LargeElectricMotorCycle;
import motorcyclerental.src.motorcycles.ElectricMotorCycle;
import motorcyclerental.src.motorcycles.SmallElectricMotorCycles;

import static org.junit.Assert.*;

public class MotorCycleFactoryTest {


    @Test
    public void testMotorFactory() {
        ElectricMotorCycle motorCycle = MotorCycleFactory.getMotorCycleInstance(MotorCycleConstants.LARGE_MOTOR_CYCLE);
        ElectricMotorCycle motorCycle1 = MotorCycleFactory.getMotorCycleInstance(MotorCycleConstants.SMALL_MOTOR_CYCLE);

        assertTrue(motorCycle instanceof LargeElectricMotorCycle);
        assertTrue(motorCycle1 instanceof SmallElectricMotorCycles);

        assertNotNull(motorCycle.getRegistrationNumber());
        assertNotNull(motorCycle1.getRegistrationNumber());

        assertNotEquals(motorCycle.getRegistrationNumber(), motorCycle1.getRegistrationNumber());

        assertEquals(75, motorCycle.getBatteryCapacity());
        assertEquals(50, motorCycle1.getBatteryCapacity());

        assertEquals(75, motorCycle.getCurrentChargeLevel());
        assertEquals(50, motorCycle1.getCurrentChargeLevel());

        assertTrue(motorCycle.isBatteryFullyCharged());
        assertTrue(motorCycle1.isBatteryFullyCharged());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMotorCycleFactoryFailure() {
        ElectricMotorCycle motorCycle = MotorCycleFactory.getMotorCycleInstance("Medium");
    }
}
