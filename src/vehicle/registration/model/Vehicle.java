
package vehicle.registration.model;

import java.util.Objects;

public abstract class Vehicle {

    private final String plateNumber;
    private String ownerName;
    private final int registrationYear;
    private String status;

    public Vehicle(String plateNumber, String ownerName,
                   int registrationYear, String status) {

        this.plateNumber = plateNumber.toUpperCase();
        this.ownerName = ownerName;
        this.registrationYear = registrationYear;
        this.status = status;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public int getRegistrationYear() {
        return registrationYear;
    }

    public String getStatus() {
        return status;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVehicleType() {
        return getClass().getSimpleName();
    }

    public abstract String getRegistrationLabel();

    @Override
    public String toString() {
        return String.format(
            "[%s] | %s | Owner: %s | Year: %d | Status: %s",
            plateNumber,
            getVehicleType(),
            ownerName,
            registrationYear,
            status
        );
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Vehicle vehicle = (Vehicle) o;

        return plateNumber.equalsIgnoreCase(vehicle.plateNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plateNumber.toLowerCase());
    }
}
