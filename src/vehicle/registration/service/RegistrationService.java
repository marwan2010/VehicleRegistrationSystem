
package vehicle.registration.service;

import vehicle.registration.exception.*;
import vehicle.registration.model.Vehicle;

import java.util.IntSummaryStatistics;
import java.util.*;
import java.util.stream.Collectors;

public class RegistrationService {

    private final List<Vehicle> vehicleList = new ArrayList<>();
    private final Map<String, Vehicle> plateIndex = new HashMap<>();
    private final Set<String> registeredPlates = new HashSet<>();

    public void registerVehicle(Vehicle vehicle) {

        String plate = vehicle.getPlateNumber().toUpperCase();

        if (registeredPlates.contains(plate)) {
            throw new DuplicatePlateException(plate);
        }

        vehicleList.add(vehicle);
        plateIndex.put(plate, vehicle);
        registeredPlates.add(plate);
    }

    public Vehicle findByPlate(String plate) {

        plate = plate.toUpperCase();

        Vehicle vehicle = plateIndex.get(plate);

        if (vehicle == null) {
            throw new VehicleNotFoundException(plate);
        }

        return vehicle;
    }

    public void deleteVehicle(String plate) {

        Vehicle vehicle = findByPlate(plate);

        vehicleList.remove(vehicle);
        plateIndex.remove(plate.toUpperCase());
        registeredPlates.remove(plate.toUpperCase());
    }

    public void updateOwner(String plate, String newOwner) {

        Vehicle vehicle = findByPlate(plate);
        vehicle.setOwnerName(newOwner);
    }

    public List<Vehicle> getAllVehicles() {
        return Collections.unmodifiableList(vehicleList);
    }

    public List<Vehicle> filterByType(
            String type) {

        return vehicleList.stream()

                .filter(v ->
                        v.getVehicleType()
                                .equalsIgnoreCase(type))

                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByOwner(
            String owner) {

        return vehicleList.stream()

                .filter(v ->
                        v.getOwnerName()
                                .toLowerCase()
                                .contains(owner.toLowerCase()))

                .collect(Collectors.toList());
    }
    public List<Vehicle> getExpiredRegistrations(
            int currentYear) {

        return vehicleList.stream()

                .filter(v ->
                        (currentYear -
                                v.getRegistrationYear()) > 5)

                .collect(Collectors.toList());
    }
    public void printStatistics() {

        IntSummaryStatistics stats =

                vehicleList.stream()

                        .mapToInt(
                                Vehicle::getRegistrationYear)

                        .summaryStatistics();

        System.out.println(
                "========== STATISTICS ==========");

        System.out.println(
                "Total Vehicles: "
                        + stats.getCount());

        System.out.println(
                "Average Year: "
                        + stats.getAverage());

        System.out.println(
                "Newest Vehicle: "
                        + stats.getMax());

        System.out.println(
                "Oldest Vehicle: "
                        + stats.getMin());
    }
}
