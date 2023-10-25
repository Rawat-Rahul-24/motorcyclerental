import org.junit.Before;
import org.junit.Test;
import motorcyclerental.src.models.RentalPerson;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RentalPersonTest {

    private RentalPerson rentalPerson;
    private Date dob;

    @Before
    public void init() {
        dob = Calendar.getInstance().getTime();
        rentalPerson = new RentalPerson("John", "Doe", dob);
    }

    @Test
    public void testRentalPersonConstructor() {
        assertEquals("John", rentalPerson.getFirstName());
    }

    @Test
    public void testRentalPersonEquality() {
        RentalPerson rentalPerson1 = new RentalPerson("John", "Doe", dob);

        assertTrue(rentalPerson.equals(rentalPerson1));
    }

}
