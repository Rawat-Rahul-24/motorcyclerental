package motorcyclerental.src.motorcycles;

import motorcyclerental.src.models.RegistrationNumber;

/**
 * ElectricMotorCycle - interface containing all the methods available for the electric motor cycles
 */
public interface ElectricMotorCycle {

    /**
     * Get the registration number of the motor cycle
     * @return RegistrationNumber
     */
    RegistrationNumber getRegistrationNumber();

    /**
     * Get the battery capacity if the motor cycle
     * @return battery capacity
     */
    int getBatteryCapacity();

    /**
     * Get the current charge level of the motor cycle
     * @return current charge level of the motor cycle
     */
    int getCurrentChargeLevel();

    /**
     * Get whether the battery is fully charged or not
     * @return is battery fully charged
     */
    boolean isBatteryFullyCharged();

    /**
     * Charge the battery to the battery capacity and return the charge required for the same
     * @return charge required to charge the battery to full capacity
     */
    int chargeBatteryToFullCapacity();

    /**
     * method to ride the motor cycle and calculate the battery spent on riding the kilometers passed in the argument
     * @param kilometresRiden kilometres to ride the motor cycle
     * @return int battery spent on riding the motor cycle
     */
    int ride(int kilometresRiden);

    /**
     * Check if a motor cycle is rented
     * @return true if the motor cycle is rented else false
     */
    boolean isMotorCycleRented();

    /**
     * set if a motor cycle is rented or not
     * @param isMotorCycleRented boolean indicating if the motor cycle is rented or not
     */
    void setIsMotorCycleRented(boolean isMotorCycleRented);
}
