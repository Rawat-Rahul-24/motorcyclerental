package motorcyclerental.src.models;

/**
 * RegistrationNumber - Registration Number of the motor cycle
 */
public final class RegistrationNumber {

    /**
     * First component of the registration number
     */
    private final String firstComponent;

    /**
     *Second component of the registration number
     */
    private final String secondComponent;

    /**
     * Create Registration Number with the first and second components passed
     * @param firstComponent - first component of the registration number
     * @param secondComponent - second component of the registration number
     */
    protected RegistrationNumber(String firstComponent, String secondComponent) {
        this.firstComponent = firstComponent;
        this.secondComponent = secondComponent;
    }

    /**
     * Get the first component of the registration number
     * @return first component of the registration number as a string
     */
    public String getFirstComponent() {
        return firstComponent;
    }

    /**
     * Get the second component of the registration number
     * @return second component of the registration number as a string
     */
    public String getSecondComponent() {
        return secondComponent;
    }


    /**
     *  @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return firstComponent + ' ' + secondComponent;
    }
}
