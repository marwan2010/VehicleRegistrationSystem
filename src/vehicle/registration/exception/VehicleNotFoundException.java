
package vehicle.registration.exception;

public class VehicleNotFoundException extends RuntimeException {

    public VehicleNotFoundException(String plate) {
        super("No vehicle found with plate: " + plate);
    }
}
