package motorcyclerental.src.factory;


import motorcyclerental.src.constants.MotorCycleConstants;
import motorcyclerental.src.models.RegistrationNumber;
import motorcyclerental.src.motorcycles.LargeElectricMotorCycle;
import motorcyclerental.src.motorcycles.ElectricMotorCycle;
import motorcyclerental.src.motorcycles.SmallElectricMotorCycles;
import motorcyclerental.src.models.RentalUtil;

/**
 * MotorCycleFactory - Factory class to get an instance of a motor cycle based on the type of motor cycle
 */
public class MotorCycleFactory {

    /**
     *Return an object of a motor cycle based on its type
     *
     * @param typeOfMotorCycle - type of the motor cycle whose object is needed
     * @return MotorCycle - object of the motor cycle
     * @throws IllegalArgumentException if <code>typeOfMotorCycle</code> is unknown
     */
    public static ElectricMotorCycle getMotorCycleInstance(String typeOfMotorCycle) {
        switch(typeOfMotorCycle) {
            case MotorCycleConstants.LARGE_MOTOR_CYCLE:
                return new LargeElectricMotorCycle(getRegistrationNumber());
            case MotorCycleConstants.SMALL_MOTOR_CYCLE:
                return new SmallElectricMotorCycles(getRegistrationNumber());
            default:
                throw new IllegalArgumentException("Unknowm motor cycle type");
        }
    }

    /**
     * Get a registration number for a motor cycle while returning the object from the getMotorCycleInstance method
     * @return RegistrationNumber - object of RegistrationNumber
     */
    private static RegistrationNumber getRegistrationNumber() {
        return  RentalUtil.generateVehicleRegistrationNumber();
    }
}
