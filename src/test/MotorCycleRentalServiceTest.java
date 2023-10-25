import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import motorcyclerental.src.constants.MotorCycleConstants;
import motorcyclerental.src.models.DrivingLicense;
import motorcyclerental.src.models.RentalPerson;
import motorcyclerental.src.motorcycles.ElectricMotorCycle;
import motorcyclerental.src.rentalservice.MotorCycleRentalService;
import motorcyclerental.src.models.RentalUtil;

import java.security.SecureRandom;
import java.util.*;

public class MotorCycleRentalServiceTest {

    private MotorCycleRentalService motorCycleRentalService;
    private RentalPerson rentalPerson;
    private DrivingLicense drivingLicense;
    private final Random random = new SecureRandom();

    @Before
    public void init() {
        motorCycleRentalService = new MotorCycleRentalService();
        rentalPerson = new RentalPerson("John", "Doe", RentalUtil.getDate("24-09-1996"));
        drivingLicense = RentalUtil.generateDrivingLicense(rentalPerson, RentalUtil.getDate("24-01-2015"));
    }

    @Test
    public void testMotorCycleRentalService() {

        rentalPerson = new RentalPerson(getRandomName(), getRandomName(), RentalUtil.getDate(getRandomDateString()));
        drivingLicense = RentalUtil.generateDrivingLicense(rentalPerson, RentalUtil.getDate(getRandomDateString()));
        drivingLicense.setFullDrivingLicense(true);
        System.out.println(drivingLicense);
        ElectricMotorCycle motorCycle = motorCycleRentalService
                .issueMotorCycle(rentalPerson, drivingLicense, MotorCycleConstants.LARGE_MOTOR_CYCLE);
        Assert.assertEquals(75, motorCycle.getBatteryCapacity());
        Assert.assertEquals(9, motorCycleRentalService
                .availableMotorCycles(MotorCycleConstants.LARGE_MOTOR_CYCLE));
        Assert.assertEquals(20, motorCycleRentalService
                .availableMotorCycles(MotorCycleConstants.SMALL_MOTOR_CYCLE));

        Assert.assertTrue(motorCycle.isMotorCycleRented());

        ElectricMotorCycle motorCycle1 = motorCycleRentalService.getMotorCycle(rentalPerson);
        Assert.assertTrue(motorCycle.equals(motorCycle1));

        Assert.assertEquals(1, motorCycleRentalService.getRentedMotorCycles().size());
    }

    @Test
    public void testMotorCycleRentalServiceCompleteLoad() {

        List<RentalPerson> rentalPersonList = new ArrayList<>();
        Set<ElectricMotorCycle> motorCycleList = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            rentalPerson = new RentalPerson(getRandomName(), getRandomName(), RentalUtil.getDate(getRandomDateString()));
            drivingLicense = RentalUtil.generateDrivingLicense(rentalPerson, RentalUtil.getDate(getRandomDateString()));
            drivingLicense.setFullDrivingLicense(true);
            ElectricMotorCycle motorCycle = motorCycleRentalService.issueMotorCycle(rentalPerson,
                    drivingLicense, MotorCycleConstants.SMALL_MOTOR_CYCLE);
            motorCycleList.add(motorCycle);
            rentalPersonList.add(rentalPerson);
        }

        for (int i = 0; i < 10; i++) {
            rentalPerson = new RentalPerson(getRandomName(), getRandomName(), RentalUtil.getDate(getRandomDateString()));
            drivingLicense = RentalUtil.generateDrivingLicense(rentalPerson, RentalUtil.getDate(getRandomDateString()));
            drivingLicense.setFullDrivingLicense(true);
            ElectricMotorCycle motorCycle = motorCycleRentalService.issueMotorCycle(rentalPerson,
                    drivingLicense, MotorCycleConstants.LARGE_MOTOR_CYCLE);
            motorCycleList.add(motorCycle);
            rentalPersonList.add(rentalPerson);
        }

        Assert.assertEquals(0,
                motorCycleRentalService.availableMotorCycles(MotorCycleConstants.LARGE_MOTOR_CYCLE));
        Assert.assertEquals(0,
                motorCycleRentalService.availableMotorCycles(MotorCycleConstants.SMALL_MOTOR_CYCLE));
        Assert.assertEquals(30, motorCycleRentalService.getRentedMotorCycles().size());

        Assert.assertEquals(30, motorCycleList.size());

        for (RentalPerson rentalPerson : rentalPersonList) {
            Assert.assertTrue(motorCycleList.contains(motorCycleRentalService.getMotorCycle(rentalPerson)));
        }
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testMotorCycleRentalServiceCannotIssueSmallMotorCycle1() {

        rentalPerson = new RentalPerson("John", "doe", RentalUtil.getDate("24-09-2005"));
        drivingLicense = RentalUtil.generateDrivingLicense(rentalPerson, RentalUtil.getDate("03-09-2016"));
        drivingLicense.setFullDrivingLicense(true);
        motorCycleRentalService
                .issueMotorCycle(rentalPerson, drivingLicense, MotorCycleConstants.SMALL_MOTOR_CYCLE);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testMotorCycleRentalServiceCannotIssueSmallMotorCycle2() {

        rentalPerson = new RentalPerson("John", "doe", RentalUtil.getDate("24-09-2000"));
        drivingLicense = RentalUtil.generateDrivingLicense(rentalPerson, RentalUtil.getDate("03-11-2022"));
        drivingLicense.setFullDrivingLicense(true);
        motorCycleRentalService
                .issueMotorCycle(rentalPerson, drivingLicense, MotorCycleConstants.SMALL_MOTOR_CYCLE);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testMotorCycleRentalServiceCannotIssueSmallMotorCycle3() {

        for (int i = 0; i < 20; i++) {
            drivingLicense.setFullDrivingLicense(true);
            motorCycleRentalService.issueMotorCycle(rentalPerson,
                    drivingLicense, MotorCycleConstants.SMALL_MOTOR_CYCLE);
            rentalPerson = new RentalPerson(getRandomName(), getRandomName(), RentalUtil.getDate(getRandomDateString()));
            drivingLicense = RentalUtil.generateDrivingLicense(rentalPerson, RentalUtil.getDate(getRandomDateString()));
        }
        drivingLicense.setFullDrivingLicense(true);
        motorCycleRentalService
                .issueMotorCycle(rentalPerson, drivingLicense, MotorCycleConstants.SMALL_MOTOR_CYCLE);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testMotorCycleRentalServiceCannotIssueLargeMotorCycle1() {

        rentalPerson = new RentalPerson("John", "doe", RentalUtil.getDate("24-09-2005"));
        drivingLicense = RentalUtil.generateDrivingLicense(rentalPerson, RentalUtil.getDate("03-09-2016"));
        drivingLicense.setFullDrivingLicense(true);
        motorCycleRentalService
                .issueMotorCycle(rentalPerson, drivingLicense, MotorCycleConstants.LARGE_MOTOR_CYCLE);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testMotorCycleRentalServiceCannotIssueLargeMotorCycle2() {

        rentalPerson = new RentalPerson("John", "doe", RentalUtil.getDate("24-09-1995"));
        drivingLicense = RentalUtil.generateDrivingLicense(rentalPerson, RentalUtil.getDate("03-11-2022"));
        drivingLicense.setFullDrivingLicense(true);
        motorCycleRentalService
                .issueMotorCycle(rentalPerson, drivingLicense, MotorCycleConstants.LARGE_MOTOR_CYCLE);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testMotorCycleRentalServiceCannotIssueLargeMotorCycle3() {

        for (int i = 0; i < 10; i++) {
            drivingLicense.setFullDrivingLicense(true);
            motorCycleRentalService.issueMotorCycle(rentalPerson,
                    drivingLicense, MotorCycleConstants.LARGE_MOTOR_CYCLE);
            rentalPerson = new RentalPerson(getRandomName(), getRandomName(), RentalUtil.getDate(getRandomDateString()));
            drivingLicense = RentalUtil.generateDrivingLicense(rentalPerson, RentalUtil.getDate(getRandomDateString()));
        }
        drivingLicense.setFullDrivingLicense(true);
        motorCycleRentalService
                .issueMotorCycle(rentalPerson, drivingLicense, MotorCycleConstants.LARGE_MOTOR_CYCLE);
    }

    @Test
    public void testMotorCycleRentalServiceTerminateRental() {

        drivingLicense.setFullDrivingLicense(true);
        ElectricMotorCycle motorCycle = motorCycleRentalService.issueMotorCycle(rentalPerson,
                drivingLicense, MotorCycleConstants.SMALL_MOTOR_CYCLE);

        Assert.assertTrue(19 == motorCycleRentalService
                .availableMotorCycles(MotorCycleConstants.SMALL_MOTOR_CYCLE));

        motorCycle.ride(20);

        Assert.assertEquals(30, motorCycle.getCurrentChargeLevel());

        int chargeToFullBattery = motorCycleRentalService.terminateRental(rentalPerson);

        Assert.assertEquals(20, chargeToFullBattery);
        Assert.assertEquals(20, motorCycleRentalService
                .availableMotorCycles(MotorCycleConstants.SMALL_MOTOR_CYCLE));
    }

    @Test
    public void testMotorCycleRentalServiceTerminateRentalFailure() {
        int batteryConsumed = motorCycleRentalService.terminateRental(rentalPerson);
        Assert.assertEquals(-1, batteryConsumed);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testMotorCycleRentalServiceDrivingLicenseNotRelatedToPerson() {
        RentalPerson rentalPerson1 = new RentalPerson("Dave", "Johns", RentalUtil.getDate(getRandomDateString()));
        drivingLicense.setFullDrivingLicense(true);
        motorCycleRentalService.issueMotorCycle(rentalPerson1, drivingLicense, MotorCycleConstants.LARGE_MOTOR_CYCLE);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testMotorCycleRentalServiceIssueMotorCycleMoreThanCount() {
        for (int i = 0; i < 10; i++) {
            rentalPerson = new RentalPerson(getRandomName(), getRandomName(), RentalUtil.getDate(getRandomDateString()));
            drivingLicense = RentalUtil.generateDrivingLicense(rentalPerson, RentalUtil.getDate(getRandomDateString()));
            drivingLicense.setFullDrivingLicense(true);
            motorCycleRentalService.issueMotorCycle(rentalPerson,
                    drivingLicense, MotorCycleConstants.LARGE_MOTOR_CYCLE);
        }

        rentalPerson = new RentalPerson(getRandomName(), getRandomName(), RentalUtil.getDate(getRandomDateString()));
        drivingLicense = RentalUtil.generateDrivingLicense(rentalPerson, RentalUtil.getDate(getRandomDateString()));
        drivingLicense.setFullDrivingLicense(true);
        motorCycleRentalService.issueMotorCycle(rentalPerson, drivingLicense, MotorCycleConstants.LARGE_MOTOR_CYCLE);
    }

    private String getRandomName() {
        String characters = "ABCDEFGHJIJKLMNOPQRSTUVWXYZ";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0; i<5; i++) {
            stringBuilder.append(characters.charAt(random.nextInt(26)));
        }

        return stringBuilder.toString();
    }

    private String getRandomDateString() {
        String dF = "0123";
        String dS = "0123456789";
        String mFirst = "01";
        String mS = "0123456789";
        String appender = "-";
        int year = 1970;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dF.charAt(random.nextInt(4)));
        if (Integer.parseInt(stringBuilder.toString()) == 3) {
            stringBuilder.append(dS.charAt(random.nextInt(2)));
        } else {
            stringBuilder.append(dS.charAt(random.nextInt(10)));
        }
        stringBuilder.append(appender);
        stringBuilder.append(mFirst.charAt(random.nextInt(2)));
        if (stringBuilder.toString().charAt(stringBuilder.length() - 1) == '1')
            stringBuilder.append(mS.charAt(random.nextInt(3)));
        else if (stringBuilder.toString().charAt(stringBuilder.length() - 1) == '0')
            stringBuilder.append(mS.substring(1).charAt(random.nextInt(9)));
        else
            stringBuilder.append(mS.charAt(random.nextInt(10)));
        stringBuilder.append(appender);
        stringBuilder.append(year + random.nextInt(27));

        return stringBuilder.toString();
    }

}
