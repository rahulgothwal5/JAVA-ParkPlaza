package com.rahulgothwal.Elastic.Search.service.helper;

import com.rahulgothwal.Elastic.Search.document.Vehicle;
import com.rahulgothwal.Elastic.Search.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Service used to insert some dummy data into vehicle index.
 *
 * @author mirza
 */
@Service
public class VehicleDummyDataService {
    private static final Logger LOG = LoggerFactory.getLogger(VehicleDummyDataService.class);
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private final VehicleService vehicleService;

    public VehicleDummyDataService(final VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    private static Vehicle buildVehicle(final String id,
                                        final String name,
                                        final String number,
                                        final String date) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(id);
        vehicle.setName(name);
        vehicle.setNumber(number);
        try {
            vehicle.setCreated(DATE_FORMAT.parse(date));
        } catch (ParseException e) {
            LOG.error(e.getMessage(), e);
        }

        return vehicle;
    }

    public void insertDummyData() {
        for (int i = 1; i <= 200; i++) {
            String id = Integer.toString(i);
            String number = getRandomNumber();
            String name = getRandomName();
            Date created = getRandomDate();

            Vehicle vehicle = buildVehicle(id, number, name, created);
            vehicleService.save(vehicle);
        }
    }

    private String getRandomNumber() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append((char) ('A' + Math.random() * ('Z' - 'A' + 1)));
        }
        sb.append("-");
        for (int i = 0; i < 3; i++) {
            sb.append((char) ('0' + Math.random() * ('9' - '0' + 1)));
        }
        return sb.toString();
    }

    private String getRandomName() {
        String[] names = {"Audi", "BMW", "VW", "Skoda"};
        return names[(int) (Math.random() * names.length)];
    }

    private Date getRandomDate() {
        LocalDate startDate = LocalDate.of(1990, 1, 1);
        LocalDate endDate = LocalDate.of(2022, 12, 31);
        long startEpochDay = startDate.toEpochDay();
        long endEpochDay = endDate.toEpochDay();
        long randomEpochDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomEpochDay);
        return Date.from(randomDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private Vehicle buildVehicle(String id, String number, String name, Date created) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(id);
        vehicle.setNumber(number);
        vehicle.setName(name);
        vehicle.setCreated(created);
        return vehicle;
    }


}
