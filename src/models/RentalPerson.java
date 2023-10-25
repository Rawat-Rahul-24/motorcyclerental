package motorcyclerental.src.models;

import java.util.Date;
import java.util.Objects;

/**
 * RentalPerson - person renting an electric motor cycle from the company
 */
public final class RentalPerson {

    /**
     * First name of the person. It is made final so it cannot be changed after initialised
     */
    private final String firstName;

    /**
     * Last name of the person. It is made final so it cannot be changed after initialised
     */
    private final String lastName;

    /**
     * Date of birth of the person. It is made final so it cannot be changed after initialised
     */
    private final Date dob;

    /**
     * Get the first name of the person
     * @return First name of the person
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get the last name of the person
     * @return Last name of the person
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get the date of birth of the person
     * @return Date of birth of the person
     */
    public Date getDob() {
        return new Date(dob.getTime());
    }


    /**
     *  @see java.lang.Object#equals(Object) ()
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalPerson that = (RentalPerson) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName)
                && Objects.equals(dob, that.dob);
    }


    /**
     *  @see Object#hashCode() ()
     */
    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + (firstName == null ? 0 : firstName.hashCode());
        result = 31 * result + (lastName == null ? 0 : lastName.hashCode());
        result = 31 * result + (dob == null ? 0 : dob.hashCode());

        return result;

    }

    /**
    *@see java.lang.Object#toString()
    */
     @Override
    public String toString() {
        return "RentalPerson{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                '}';
    }

    /**
     * Create a rental person with the first name, lastname and date of birth passed
     * @param firstName first name of the person
     * @param lastName last name of the person
     * @param dob date of birth of the person
     */
    public RentalPerson(String firstName, String lastName, Date dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }
}
