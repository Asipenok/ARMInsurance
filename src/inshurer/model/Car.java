package inshurer.model;

import java.time.LocalDate;
import java.util.Date;

public class Car {
    String brand;
    String model;
    String vin;
    String number;
    LocalDate year;
    int coast;
    String currency;


    public Car(String brand, String model, String vin, String number, LocalDate year, String coast, String currency) {
        this.brand = brand;
        this.model = model;
        this.vin = vin;
        this.number = number;
        this.year = year;
        this.coast = Integer.parseInt(coast);
        this.currency = currency;
    }


}
