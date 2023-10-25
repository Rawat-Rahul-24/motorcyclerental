package motorcyclerental.src.rentalservice;


import motorcyclerental.src.constants.MotorCycleConstants;
import motorcyclerental.src.factory.MotorCycleFactory;
import motorcyclerental.src.models.DrivingLicense;
import motorcyclerental.src.models.RentalPerson;
import motorcyclerental.src.models.RentalUtil;
import motorcyclerental.src.motorcycles.ElectricMotorCycle;

import java.util.*;

/**
 * MotorCycleRentalService - All the methods related to renting of an electric motor cycle are defined here.
 */
public class MotorCycleRentalService {

    /**
     * Set containing the electric motor cycles rented. It maintains uniqueness of the motor cycles rented
     * such that once a motor cycle is rented it should not be rented again
     */
    private final Set<ElectricMotorCycle> rentedMotorCycles = new HashSet<>();

    /**
     * Map containing RentalPerson as key and ElectricMotorCycle as value to maintain the mapping between the person
     * and the motor cycle rented to them. It also helps in checking that a person cannot rent the motor cycle twice
     */
    private final Map<RentalPerson, ElectricMotorCycle> motorCycleRentedToPersonsMap = new HashMap<>();

    /**
     * Number of large motor cycles rented at any given moment
     */
    private int largeMotorCyclesRented = 0;

    /**
     *Number of small motor cycles rented at any given moment
     */
    private int smallMotorCyclesRented = 0;


    /**
     * Get the number of motor cycles available of any type at any given moment
     * @param typeOfMotoCycle - type of motor cycle
     * @return int number of motor cycles available at any given moment
     */
    public int availableMotorCycles(String typeOfMotoCycle) {
        if (MotorCycleConstants.LARGE_MOTOR_CYCLE.equals(typeOfMotoCycle)) {
            return MotorCycleConstants.LARGE_MOTOR_CYCLE_COUNT - largeMotorCyclesRented;
        } else {
            return MotorCycleConstants.SMALL_MOTOR_CYCLE_COUNT - smallMotorCyclesRented;
        }
    }

    /**
     * return all the motor cycles rented at any given moment
     * @return Set of all the motor cycles rented at any given moment
     */
    public Set<ElectricMotorCycle> getRentedMotorCycles() {
        return rentedMotorCycles;
    }

    /**
     * Get a motor cycle rented by the person passed in the argument
     * @param rentalPerson person who might have rented a motor cycle
     * @return <code>ElectricMotorCycle</code>
     * @throws UnsupportedOperationException if no motor cycle is rented to the person
     */
    public ElectricMotorCycle getMotorCycle(RentalPerson rentalPerson) {
        if (!motorCycleRentedToPersonsMap.containsKey(rentalPerson)) {
            throw new UnsupportedOperationException("No motorcycle is rented out by this person");
        }
        return motorCycleRentedToPersonsMap.get(rentalPerson);
    }

    /**
     * Get the motor cycle if issued to a person after all the checks have passed,
     * also update the Set of <code>ElectricMotorCycles</code> if issued successfully,
     * and the Map of RentedPerson and ElectricMotorCycle if issued successfully
     *
     * @param rentalPerson <code>RentalPerson</code> who wants to rent a motor cycle
     * @param drivingLicense <code>DrivingLicense</code> of the person trying to rent a motor cycle
     * @param typeOfMotorCycle type of motor cycle
     * @return <code>ElectricMotorCycle</code> if a motor cycle is issued successfully
     * @throws UnsupportedOperationException if cannot rent a motor cycle to a person
     */
    public ElectricMotorCycle issueMotorCycle(RentalPerson rentalPerson, DrivingLicense drivingLicense,
                                              String typeOfMotorCycle) {
        if (!drivingLicense.isFullDrivingLicense()) {
            throw new UnsupportedOperationException("Motor cycle cannot be issued");
        }

        if (motorCycleRentedToPersonsMap.containsKey(rentalPerson)) {
            throw new UnsupportedOperationException("Motor cycle cannot be issued");
        }

        if (availableMotorCycles(typeOfMotorCycle) == 0) {
            throw new UnsupportedOperationException("Motor cycle cannot be issued");
        }

        if (!isPersonValidToRentMotorCycle(rentalPerson, drivingLicense, typeOfMotorCycle)) {
            throw new UnsupportedOperationException("Motor cycle cannot be issued");
        }

        ElectricMotorCycle motorCycle = MotorCycleFactory.getMotorCycleInstance(typeOfMotorCycle);
        motorCycle.setIsMotorCycleRented(true);
        motorCycleRentedToPersonsMap.put(rentalPerson, motorCycle);
        if (MotorCycleConstants.LARGE_MOTOR_CYCLE.equals(typeOfMotorCycle)) {
            largeMotorCyclesRented++;
        } else {
            smallMotorCyclesRented++;
        }
        rentedMotorCycles.add(motorCycle);
        return motorCycle;
    }

    /**
     * Checks if the person is valid to rent a motor cycle
     * @param rentalPerson <code>RentalPerson</code> trying to rent a motor cycle
     * @param drivingLicense <code>DrivingLicense</code> of the person trying to rent a motor cycle
     * @param typeOfMotorCycle type of motor cycle
     * @return true if a person is valid to rent a motor cycle else false if not
     */
    private boolean isPersonValidToRentMotorCycle(RentalPerson rentalPerson, DrivingLicense drivingLicense,
                                                  String typeOfMotorCycle) {

        long daysInMillis = 24 * 60 * 60 * 1000;

        if (!RentalUtil.checkIfDrivingLicenseIsIssuedToPerson(rentalPerson, drivingLicense)) {
            return false;
        }

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(rentalPerson.getDob());
        int  personAge = Calendar.getInstance().get(Calendar.YEAR) - calendar.get(Calendar.YEAR);
        calendar.setTime(drivingLicense.getDateOfIssue());
        long drivingLicenseIssueYearDifference = ((Calendar.getInstance().getTimeInMillis()
                - calendar.getTimeInMillis()) / daysInMillis) / 365;


        if (MotorCycleConstants.SMALL_MOTOR_CYCLE.equals(typeOfMotorCycle) && (personAge < 20
                || drivingLicenseIssueYearDifference < 1)) {
            return false;
        }

        return !MotorCycleConstants.LARGE_MOTOR_CYCLE.equals(typeOfMotorCycle) || (personAge >= 25
                && drivingLicenseIssueYearDifference >= 5);
    }

    /**
     * Terminate the rental of a motor cycle
     * @param rentalPerson <code>RentalPerson</code> trying to terminate the rental of a motor cycle
     * @return charged required to make the battery reach full capacity if a motor cycle was rented to a person,
     * else return -1 if no motor cycle was rented
     */
    public int terminateRental(RentalPerson rentalPerson) {
        if (motorCycleRentedToPersonsMap.containsKey(rentalPerson)) {
            ElectricMotorCycle motorCycle = motorCycleRentedToPersonsMap.get(rentalPerson);
            motorCycleRentedToPersonsMap.remove(rentalPerson);
            rentedMotorCycles.remove(motorCycle);
            String typeOfMotorCycle = motorCycle.getClass().getSimpleName();
            if (MotorCycleConstants.LARGE_MOTOR_CYCLE.equals(typeOfMotorCycle)) {
                largeMotorCyclesRented--;
            } else {
                smallMotorCyclesRented--;
            }
            motorCycle.setIsMotorCycleRented(false);
            return motorCycle.chargeBatteryToFullCapacity();

        }

        return -1;
    }

}
