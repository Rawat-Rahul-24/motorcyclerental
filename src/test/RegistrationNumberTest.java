import org.junit.Assert;
import org.junit.Test;
import motorcyclerental.src.models.RegistrationNumber;
import motorcyclerental.src.models.RentalUtil;

public class RegistrationNumberTest {

    @Test
    public void testRegistrationNumber() {
        RegistrationNumber registrationNumber = RentalUtil.generateVehicleRegistrationNumber();

        Assert.assertNotNull(registrationNumber);

        String rN = registrationNumber.getFirstComponent() + " " + registrationNumber.getSecondComponent();

        Assert.assertEquals(rN, registrationNumber.toString());
    }

    @Test
    public void testRegistrationNumberEquals() {
        RegistrationNumber registrationNumber = RentalUtil.generateVehicleRegistrationNumber();
        RegistrationNumber registrationNumber1 = RentalUtil.generateVehicleRegistrationNumber();

        Assert.assertFalse(registrationNumber.equals(registrationNumber1));
    }
}
