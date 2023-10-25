package motorcyclerental.src.motorcycles;


import motorcyclerental.src.models.RegistrationNumber;

/**
 * AbstractMotorCycle - An abstract class of electric motor cycles with all the common methods defined
 * between both the types if motor cycles
 */
public abstract class AbstractElectricMotorCycle implements ElectricMotorCycle {

    /**
     * RegirstrationNumber of the motor cycle. It is a final variable so cannot be changed once initialised
     */
    private final RegistrationNumber registrationNumber;
    /**
     * battery capacity of the electric motor cycle. It is a final variable so cannot be changed once initialised
     */
    private final int batteryCapacity;
    /**
     * current charge level of the electric motor cycle. Initially equal to <code>batteryCapacity</code>
     */
    private int currentChargeLevel;
    /**
     * boolean indicating if the motor cycle is rented or not
     */
    private boolean isMotorCycleRented;
    /**
     * Create an abstract electric motor cycle with the given RegistrationNumber, battery capacity, current charge level
     * and is battery fully charged or not
     * @param registrationNumber <code>RegistrationNumber</code> of the motor cycle
     * @param batteryCapacity battery capacity of the motor cycle
     * @param currentChargeLevel current charge level of the motor cycle. Initially it is equal to battery capacity
     */
    protected AbstractElectricMotorCycle(RegistrationNumber registrationNumber, int batteryCapacity,
                                      int currentChargeLevel) {
        this.registrationNumber = registrationNumber;
        this.batteryCapacity = batteryCapacity;
        this.currentChargeLevel = currentChargeLevel;
    }

    /**
     * Get the registration number of the electric motor cycle
     * @return RegistrationNumber
     */
    public RegistrationNumber getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * Get the battery capacity if the motor cycle
     * @return battery capacity
     */
    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    /**
     * Get the current charge level of the motor cycle
     * @return current charge level of the motor cycle
     */
    public int getCurrentChargeLevel() {
        return currentChargeLevel;
    }

    /**
     * Get whether the battery is fully charged or not
     * @return is battery fully charged
     */
    public boolean isBatteryFullyCharged() {
        return batteryCapacity == currentChargeLevel;
    }

    /**
     * Charge the battery to the battery capacity and return the charge required for the same
     * @return charge required to charge the battery to full capacity
     */
    public int chargeBatteryToFullCapacity() {
        int batteryLevelToBeCharged = batteryCapacity - currentChargeLevel;
        currentChargeLevel += batteryLevelToBeCharged;
        return batteryLevelToBeCharged;
    }

    /**
     * Change the current charge level of the battery. Also set <code>isBatteryFullyCharged</code> to true if
     * <code>currentChargeLevel</code> becomes equal to <code>batteryCapacity</code> otherwise it is false
     * @param chargeLevel charge to be added to the battery
     * @return charge added if current charge + chargeLevel is less than batteryCapacity,
     * 0 if current charge is less than or equal to 0,
     * charge added to make battery fully charged if chargeLevel + currentChargeLevel is greated than batteryCapacity
     */
    public int changeCurrentChargeLevel(int chargeLevel) {
        int chargeAdded = chargeLevel;
        if (currentChargeLevel + chargeLevel > batteryCapacity) {
            chargeAdded = batteryCapacity - chargeLevel;
            currentChargeLevel += chargeAdded;
        } else {
            if (currentChargeLevel <= 0) {
                return 0;
            }
            currentChargeLevel += chargeLevel;
        }

        return chargeAdded;
    }

    /**
     * Check if a motor cycle is rented
     * @return true if the motor cycle is rented else false
     */
    public boolean  isMotorCycleRented() {
        return isMotorCycleRented;
    }

    /**
     * Set if a motor cycle is rented
     * @param isMotorCycleRented boolean true if a motor cycle is rented else false
     */
    public void setIsMotorCycleRented(boolean isMotorCycleRented) {
        this.isMotorCycleRented = isMotorCycleRented;
    }
}
