package motorcyclerental.src.motorcycles;

import motorcyclerental.src.constants.MotorCycleConstants;
import motorcyclerental.src.models.RegistrationNumber;

import java.util.Objects;

public class SmallElectricMotorCycles extends AbstractElectricMotorCycle {

    /**
     * Contant indicating the battery consumption rate of a small motor cycle
     */
    private static final int BATTERY_CONSUMPTION_RATE = 1;

    /**
     * Create a SmallMotorCycle with the RegistrationNumber passed. The other parameters are initialised
     * in the super class constructor i.e. <code>AbstractElectricMotorCycle</code>
     * @param registrationNumber <code>RegistrationNumber</code> of the motor cycle
     */
    public SmallElectricMotorCycles(RegistrationNumber registrationNumber) {
        super(registrationNumber, MotorCycleConstants.SMALL_MOTOR_CYCLE_BATTERY_CAPACITY,
                MotorCycleConstants.SMALL_MOTOR_CYCLE_BATTERY_CAPACITY);
    }

    /**
     * method to ride the motor cycle and calculate the battery spent on riding the kilometers passed in the argument
     * @param kilometresRiden kilometres to ride the motor cycle
     * @return int battery spent on riding the motor cycle
     */
    public int ride(int kilometresRiden) {
        if (super.getCurrentChargeLevel() == 0 || !super.isMotorCycleRented()) {
            throw new UnsupportedOperationException("Cannot ride motorcycle");
        }

        int batteryConsumed = BATTERY_CONSUMPTION_RATE * kilometresRiden;
        super.changeCurrentChargeLevel(-batteryConsumed);
        return batteryConsumed;
    }

    /**
     *  @see java.lang.Object#equals(Object) ()
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElectricMotorCycle that = (ElectricMotorCycle) o;
        return Objects.equals(super.getRegistrationNumber(), that.getRegistrationNumber())
                && Objects.equals(super.getBatteryCapacity(), that.getBatteryCapacity());
    }


    /**
     *  @see Object#hashCode() ()
     */
    @Override
    public int hashCode() {
        int result = 1;

        result = 31 * result + (super.getRegistrationNumber() == null ? 0 : super.getRegistrationNumber().hashCode());
        result = 31 * result + super.getBatteryCapacity();

        return result;

    }
}
