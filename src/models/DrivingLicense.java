package motorcyclerental.src.models;

import java.util.Date;
import java.util.Objects;

/**
 * DrivingLicense - Driving license given to a person
 */
public final class DrivingLicense {

    private final String drivingLicensePartOne;
    private final String drivingLicensePartTwo;
    private final String drivingLicensePartThree;

    /**
     * String representation of the driving license issued
     */
    private final String drivingLicense;

    /**
     * Date of issue of the driving license
     */
    private final Date dateOfIssue;

    /**
     * Boolean indication whether a license is a full driving license or not
     */
    private boolean isFullDrivingLicense;

    /**
     * Create driving license with given drivingLicensePartOne, drivingLicensePartTwo, drivingLicensePartThree,
     * drivingLicense, dateOfIssue, isFullDrivingLicense
     *
     * @param drivingLicensePartOne first part of driving license
     * @param drivingLicensePartTwo second part of driving license
     * @param drivingLicensePartThree third part of driving license
     * @param drivingLicense complete driving license as a string
     * @param dateOfIssue date of issue of driving license
     * @param isFullDrivingLicense is a driving license a full driving license or not
     */
    protected DrivingLicense(String drivingLicensePartOne, String drivingLicensePartTwo, String drivingLicensePartThree,
                          String drivingLicense, Date dateOfIssue, boolean isFullDrivingLicense) {
        this.drivingLicensePartOne = drivingLicensePartOne;
        this.drivingLicensePartTwo = drivingLicensePartTwo;
        this.drivingLicensePartThree = drivingLicensePartThree;
        this.drivingLicense = drivingLicense;
        this.isFullDrivingLicense = isFullDrivingLicense;
        this.dateOfIssue = dateOfIssue;
    }

    /**
     * get driving license
     * @return the complete driving license string
     */
    public String getDrivingLicense() {
        return drivingLicense;
    }

    /**
     * Get if a driving license is a full driving license or not
     * @return boolean representing if a driving license or not
     */
    public boolean isFullDrivingLicense() {
        return isFullDrivingLicense;
    }

    /**
     * Set if a driving license is full or not
     * @param fullDrivingLicense boolean indicating of driving license is full or not
     */
    public void setFullDrivingLicense(boolean fullDrivingLicense) {
        isFullDrivingLicense = fullDrivingLicense;
    }

    /**
     * Get the first part of the driving license
     *
     * @return first part of the driving license
     */
    public String getDrivingLicensePartOne() {
        return drivingLicensePartOne;
    }

    /**
     * Get the second part of the driving license
     *
     * @return second part of the driving license
     */
    public String getDrivingLicensePartTwo() {
        return drivingLicensePartTwo;
    }

    /**
     * Get the third part of the driving license
     *
     * @return third part of the driving license
     */
    public String getDrivingLicensePartThree() {
        return drivingLicensePartThree;
    }

    /**
     * Get the date of issue of the driving license
     *
     * @return date of issue of the driving license
     */
    public Date getDateOfIssue() {
        return new Date(dateOfIssue.getTime());
    }

    /**
     *  @see java.lang.Object#equals(Object) ()
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DrivingLicense that = (DrivingLicense) o;
        return isFullDrivingLicense == that.isFullDrivingLicense
                && Objects.equals(drivingLicensePartOne, that.drivingLicensePartOne)
                && Objects.equals(drivingLicensePartTwo, that.drivingLicensePartTwo)
                && Objects.equals(drivingLicensePartThree, that.drivingLicensePartThree)
                && Objects.equals(drivingLicense, that.drivingLicense) && Objects.equals(dateOfIssue, that.dateOfIssue);
    }

    /**
     *  @see Object#hashCode() ()
     */
    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + (drivingLicensePartOne == null ? 0 : drivingLicensePartOne.hashCode());
        result = 31 * result + (drivingLicensePartTwo == null ? 0 : drivingLicensePartTwo.hashCode());
        result = 31 * result + (drivingLicensePartThree == null ? 0 : drivingLicensePartThree.hashCode());
        result = 31 * result + (drivingLicense == null ? 0 : drivingLicense.hashCode());
        result = 31 * result + (dateOfIssue == null ? 0 : dateOfIssue.hashCode());

        return result;
    }

    @Override
    public String toString() {
        return "DrivingLicense{" +
                "drivingLicensePartOne='" + drivingLicensePartOne + '\'' +
                ", drivingLicensePartTwo='" + drivingLicensePartTwo + '\'' +
                ", drivingLicensePartThree='" + drivingLicensePartThree + '\'' +
                ", drivingLicense='" + drivingLicense + '\'' +
                ", dateOfIssue=" + dateOfIssue +
                ", isFullDrivingLicense=" + isFullDrivingLicense +
                '}';
    }
}
