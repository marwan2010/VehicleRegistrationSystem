
package vehicle.registration;

import vehicle.registration.exception.*;
import vehicle.registration.model.*;
import vehicle.registration.service.RegistrationService;
import vehicle.registration.util.InputValidator;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        RegistrationService service = new RegistrationService();

        service.registerVehicle(new Car("CAR-001", "Ahmed Ali", 2019, "EXPIRED", 4));
        service.registerVehicle(new Car("CAR-002", "Sara Kamel", 2023, "ACTIVE", 2));
        service.registerVehicle(new Truck("TRK-001", "Mohamed Said", 2021, "ACTIVE", 10.0));
        service.registerVehicle(new Truck("TRK-002", "Laila Nour", 2017, "EXPIRED", 5.5));
        service.registerVehicle(new Motorcycle("MOT-001", "Omar Fathi", 2022, "ACTIVE", "Sport"));

        while (true) {

            System.out.println("\n========================================");
            System.out.println("VEHICLE REGISTRATION SYSTEM");
            System.out.println("========================================");
            System.out.println("1. Register New Vehicle");
            System.out.println("2. Search Vehicle");
            System.out.println("3. Update Owner");
            System.out.println("4. Delete Vehicle");
            System.out.println("5. List All Vehicles");
            System.out.println(
                    "6. Filter by Vehicle Type");

            System.out.println(
                    "7. Show Owner History");

            System.out.println(
                    "8. Show Expired Registrations");

            System.out.println(
                    "9. Statistics Report");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            try {

                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    case 1:

                        System.out.print("Plate Number: ");
                        String plate = sc.nextLine().toUpperCase();

                        System.out.print("Owner Name: ");
                        String owner = sc.nextLine();

                        System.out.print("Year: ");
                        int year = Integer.parseInt(sc.nextLine());

                        InputValidator.validatePlate(plate);
                        InputValidator.validateOwner(owner);
                        InputValidator.validateYear(year);

                        System.out.print("Doors: ");
                        int doors = Integer.parseInt(sc.nextLine());

                        Vehicle car = new Car(
                                plate,
                                owner,
                                year,
                                "ACTIVE",
                                doors
                        );

                        service.registerVehicle(car);

                        System.out.println("Vehicle registered successfully.");
                        break;

                    case 2:

                        System.out.print("Enter plate: ");
                        plate = sc.nextLine();

                        System.out.println(service.findByPlate(plate));
                        break;

                    case 3:

                        System.out.print("Enter plate: ");
                        plate = sc.nextLine();

                        System.out.print("New owner: ");
                        owner = sc.nextLine();

                        service.updateOwner(plate, owner);

                        System.out.println("Owner updated.");
                        break;

                    case 4:

                        System.out.print("Enter plate: ");
                        plate = sc.nextLine();

                        service.deleteVehicle(plate);

                        System.out.println("Vehicle deleted.");
                        break;

                    case 5:

                        service.getAllVehicles()
                                .forEach(System.out::println);
                        break;

                    case 6:

                        System.out.print(
                                "Type: ");

                        String type =
                                sc.nextLine();

                        service.filterByType(type)
                                .forEach(
                                        System.out::println);

                        break;
                    case 7:

                        System.out.print(
                                "Owner Name: ");

                        owner =
                                sc.nextLine();

                        service.getVehiclesByOwner(owner)
                                .forEach(
                                        System.out::println);

                        break;
                    case 8:

                        service.getExpiredRegistrations(2026)
                                .forEach(
                                        System.out::println);

                        break;
                    case 9:

                        service.printStatistics();

                        break;
                    case 0:

                        System.out.println("Goodbye!");
                        return;

                    default:

                        System.out.println("Invalid choice.");
                }

            } catch (DuplicatePlateException |
                     VehicleNotFoundException |
                     InvalidInputException e) {

                System.out.println("Error: " + e.getMessage());

            } catch (NumberFormatException e) {

                System.out.println("Invalid number input.");
            }
        }
    }
}
