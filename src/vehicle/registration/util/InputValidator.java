
package vehicle.registration.util;

import vehicle.registration.exception.InvalidInputException;

import java.time.Year;

public class InputValidator {

    public static void validatePlate(String plate) {

        if (plate == null ||
                !plate.matches("[A-Z0-9-]{3,10}")) {

            throw new InvalidInputException(
                    "Plate number must be 3-10 alphanumeric characters");
        }
    }

    public static void validateOwner(String owner) {

        if (owner == null ||
                !owner.matches("[A-Za-z ]{3,}")) {

            throw new InvalidInputException(
                    "Owner name must contain only letters");
        }
    }

    public static void validateYear(int year) {

        int currentYear = Year.now().getValue();

        if (year < 1990 || year > currentYear) {

            throw new InvalidInputException(
                    "Year must be between 1990 and " + currentYear);
        }
    }
}
