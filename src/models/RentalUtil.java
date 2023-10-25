package motorcyclerental.src.models;


import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * RentalUtil - Utility class to help generate unique registration number of an <code>ElectricMotorCycle</code>
 * and unique <code>DrivingLicense</code> for a person
 */
public class RentalUtil {

    /**
     * Set of registration numbers generated for motor cycles to maintain  their uniqueness
     */
    private static final Set<String> generatedVehicleRegistrationNumbers = new HashSet<>();

    /**
     * Map of <code>RentalPerson</code> as key and <code>DrivingLicense</code> as value to maintain a mapping
     * of driving license issued to a person
     */
    private static final Map<RentalPerson, DrivingLicense> generatedDrivingLicensesMap =  new HashMap<>();

    /**
     * Set of <code>DrivingLicense</code> issued to maintain  their uniqueness
     */
    private static final Set<DrivingLicense> generatedDrivingLicenses = new HashSet<>();
    private static final Random RANDOM = new SecureRandom();
    private static final char APPENDER = '-';
    private static int ARBITRARY_NUMBER = 100;

    /**
     * Generate a registration number for a motor cycle
     * @return registration number string
     */
    public static RegistrationNumber generateVehicleRegistrationNumber() {

        String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";

        StringBuilder stringBuilder = new StringBuilder();

        do {
            for (int i=0; i<2; i++) {
                stringBuilder.append(alphabets.charAt(RANDOM.nextInt(26)));
            }

            for (int i=0; i<2; i++) {
                stringBuilder.append(numbers.charAt(RANDOM.nextInt(10)));
            }

            stringBuilder.append(" ");

            for (int i=0; i<3; i++) {
                stringBuilder.append(alphabets.charAt(RANDOM.nextInt(26)));
            }
        } while(generatedVehicleRegistrationNumbers.contains(stringBuilder.toString()));

        generatedVehicleRegistrationNumbers.add(stringBuilder.toString());

        String[] registrationNumber = stringBuilder.toString().split(" ");
        return new RegistrationNumber(registrationNumber[0], registrationNumber[1]);
    }

    /**
     * Generate a driving license for a person using person's intials and date of issue of motor cycle
     * @param rentalPerson <code>RentalPerson</code> to whom the driving license will be issued
     * @param dateOfIssue Date of issue of the license
     * @return <code>DrivingLicense</code>
     */
    public static DrivingLicense generateDrivingLicense(RentalPerson rentalPerson, Date dateOfIssue) {

        String firstPart = "";
        String secondPart = "";
        String thirdPart = "";
        String dl = null;

        if (generatedDrivingLicensesMap.containsKey(rentalPerson)) {
            return generatedDrivingLicensesMap.get(rentalPerson);
        }

        Calendar calender = new GregorianCalendar();
        calender.setTime(dateOfIssue);

        do {
            firstPart = rentalPerson.getFirstName().charAt(0) + "" + rentalPerson.getLastName().charAt(0);
            secondPart += calender.get(Calendar.YEAR);
            thirdPart += ARBITRARY_NUMBER++;
            dl = firstPart + APPENDER + secondPart + APPENDER + thirdPart;
        } while(generatedDrivingLicenses.contains(dl));

        DrivingLicense drivingLicense = new DrivingLicense(firstPart, secondPart, thirdPart, dl, dateOfIssue, false);
        generatedDrivingLicensesMap.put(rentalPerson, drivingLicense);

        return drivingLicense;
    }

    /**
     * Get a java.util.Date object from a string
     * @param date Date string
     * @return java.util.Date
     */
    public static Date getDate(String date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            return simpleDateFormat.parse(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Check if the driving license passed is issued to the person passed in the argument
     * @param rentalPerson <code>RentalPerson</code> whose driving license is to be checked
     * @param drivingLicense <code>DrivingLicense</code> to be checked if issued to the person passed
     * @return true if the driving license passed is
     */
    public static boolean checkIfDrivingLicenseIsIssuedToPerson(RentalPerson rentalPerson,
                                                                DrivingLicense drivingLicense) {
        DrivingLicense drivingLicense1 = generatedDrivingLicensesMap.getOrDefault(rentalPerson, null);

        return drivingLicense1 != null && drivingLicense1.equals(drivingLicense);
    }
}
