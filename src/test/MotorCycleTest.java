import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import motorcyclerental.src.constants.MotorCycleConstants;
import motorcyclerental.src.factory.MotorCycleFactory;
import motorcyclerental.src.models.DrivingLicense;
import motorcyclerental.src.models.RentalPerson;
import motorcyclerental.src.motorcycles.ElectricMotorCycle;
import motorcyclerental.src.rentalservice.MotorCycleRentalService;
import motorcyclerental.src.models.RentalUtil;

public class MotorCycleTest {

    private MotorCycleRentalService motorCycleRentalService;

    @Before
    public void init() {
        motorCycleRentalService = new MotorCycleRentalService();
    }


    @Test
    public void testMotorCycle() {
        ElectricMotorCycle motorCycle = MotorCycleFactory.getMotorCycleInstance(MotorCycleConstants.LARGE_MOTOR_CYCLE);

        Assert.assertEquals(75, motorCycle.getBatteryCapacity());
        Assert.assertEquals(75, motorCycle.getCurrentChargeLevel());

        Assert.assertTrue(motorCycle.isBatteryFullyCharged());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testMotorCycleRideFailure() {
        ElectricMotorCycle motorCycle = MotorCycleFactory.getMotorCycleInstance(MotorCycleConstants.SMALL_MOTOR_CYCLE);
        motorCycle.ride(20);

    }

    @Test(expected = UnsupportedOperationException.class)
    public void testMotorCycleRideFailure2() {
        RentalPerson rentalPerson = new RentalPerson("John", "Doe", RentalUtil.getDate("20-10-1995"));
        DrivingLicense drivingLicense = RentalUtil.generateDrivingLicense(rentalPerson, RentalUtil.getDate("17-09-2010"));
        drivingLicense.setFullDrivingLicense(true);
        ElectricMotorCycle motorCycle = motorCycleRentalService.issueMotorCycle(rentalPerson,
                drivingLicense, MotorCycleConstants.LARGE_MOTOR_CYCLE);
        motorCycle.ride(40);
        motorCycle.ride(10);

    }

    @Test
    public void testMotorCycleRide() {
        RentalPerson rentalPerson = new RentalPerson("John", "Doe", RentalUtil.getDate("20-10-1995"));
        DrivingLicense drivingLicense = RentalUtil.generateDrivingLicense(rentalPerson, RentalUtil.getDate("17-09-2010"));
        drivingLicense.setFullDrivingLicense(true);
        ElectricMotorCycle motorCycle = motorCycleRentalService.issueMotorCycle(rentalPerson,
                drivingLicense, MotorCycleConstants.LARGE_MOTOR_CYCLE);

        Assert.assertTrue(motorCycle.isBatteryFullyCharged());

        int batterySpent = motorCycle.ride(40);

        Assert.assertEquals(-5, motorCycle.getCurrentChargeLevel());
        Assert.assertEquals(80, batterySpent);
//        Assert.assertFalse(motorCycle.isBatteryFullyCharged());
        Assert.assertEquals(80, motorCycle.chargeBatteryToFullCapacity());

    }


    @Test
    public void testMotorCycleEqualMethod() {
        ElectricMotorCycle motorCycle = MotorCycleFactory.getMotorCycleInstance(MotorCycleConstants.SMALL_MOTOR_CYCLE);
        ElectricMotorCycle motorCycle1 = MotorCycleFactory.getMotorCycleInstance(MotorCycleConstants.SMALL_MOTOR_CYCLE);

        ElectricMotorCycle motorCycle2 = MotorCycleFactory.getMotorCycleInstance(MotorCycleConstants.LARGE_MOTOR_CYCLE);
        ElectricMotorCycle motorCycle3 = MotorCycleFactory.getMotorCycleInstance(MotorCycleConstants.LARGE_MOTOR_CYCLE);
        Assert.assertFalse(motorCycle.equals(motorCycle1));
        Assert.assertFalse(motorCycle2.equals(motorCycle3));
    }


}
