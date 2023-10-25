import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import motorcyclerental.src.models.DrivingLicense;
import motorcyclerental.src.models.RentalPerson;
import motorcyclerental.src.models.RentalUtil;

public class DrivingLicenseTest {
    private RentalPerson rentalPerson;

    @Before
    public void init() {
        rentalPerson = new RentalPerson("John", "Doe", RentalUtil.getDate("24-09-1996"));
    }

    @Test
    public void testDrivingLicense() {
        DrivingLicense drivingLicense = RentalUtil.generateDrivingLicense(rentalPerson, RentalUtil.getDate("24-01-2015"));

        Assert.assertNotNull(drivingLicense);
        String dl = drivingLicense.getDrivingLicensePartOne() + "-" + drivingLicense.getDrivingLicensePartTwo()
                + "-" + drivingLicense.getDrivingLicensePartThree();

        Assert.assertEquals(dl, drivingLicense.getDrivingLicense());

    }

    @Test
    public void testDrivingLicenseEquals() {
        DrivingLicense drivingLicense = RentalUtil.generateDrivingLicense(rentalPerson, RentalUtil.getDate("24-01-2015"));
        DrivingLicense drivingLicense1 = RentalUtil.generateDrivingLicense(rentalPerson, RentalUtil.getDate("24-01-2015"));

        Assert.assertTrue(drivingLicense.equals(drivingLicense1));
    }

    @Test
    public void testDrivingLicenseEquals1() {
        DrivingLicense drivingLicense = RentalUtil.generateDrivingLicense(rentalPerson, RentalUtil.getDate("24-01-2015"));
        rentalPerson = new RentalPerson("John", "Doe", RentalUtil.getDate("25-09-1996"));
        DrivingLicense drivingLicense1 = RentalUtil.generateDrivingLicense(rentalPerson, RentalUtil.getDate("24-01-2015"));

        Assert.assertFalse(drivingLicense.equals(drivingLicense1));
    }
}
