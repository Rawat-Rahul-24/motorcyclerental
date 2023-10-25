package motorcyclerental.src.motorcycles;


import motorcyclerental.src.constants.MotorCycleConstants;
import motorcyclerental.src.models.RegistrationNumber;

import java.util.Objects;

public class LargeElectricMotorCycle extends AbstractElectricMotorCycle {


    /**
     * Create a LargeMotorCycle with the RegistrationNumber passed. The other parameters are initialised
     * in the super class constructor i.e. <code>AbstractElectricMotorCycle</code>
     * @param registrationNumber <code>RegistrationNumber</code> of the motor cycle
     */
    public LargeElectricMotorCycle(RegistrationNumber registrationNumber) {
        super(registrationNumber, MotorCycleConstants.LARGE_MOTOR_CYCLE_BATTERY_CAPACITY,
                MotorCycleConstants.LARGE_MOTOR_CYCLE_BATTERY_CAPACITY);
    }

    /**
     * Constant indication the consumption rate of a large motor cycle
     */
    private static final int BATTERY_CONSUMPTION_RATE = 2;

    /**
     * method to ride the motor cycle and calculate the battery spent on riding the kilometers passed in the argument
     * @param kilometresRiden kilometres to ride the motor cycle
     * @return int battery spent on riding the motor cycle
     */
    public int ride(int kilometresRiden) {
        if (super.getCurrentChargeLevel() <= 0 || !this.isMotorCycleRented()) {
            throw new UnsupportedOperationException("Cannot ride motor cycle");
        }

        int batterySpent = BATTERY_CONSUMPTION_RATE * kilometresRiden;
        super.changeCurrentChargeLevel(-batterySpent);
        return batterySpent;
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
