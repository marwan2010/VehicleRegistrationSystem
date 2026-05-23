
package vehicle.registration.exception;

public class DuplicatePlateException extends RuntimeException {

    public DuplicatePlateException(String plate) {
        super("Plate number already registered: " + plate);
    }
}
