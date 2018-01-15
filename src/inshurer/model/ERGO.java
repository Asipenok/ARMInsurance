package inshurer.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ERGO {

    //тип авто
    private double vehicleRate = 1;
    //вариант страхования
    private double optionRate = 1;
    //территория действия
    private double territoryRate;
    //количество единиц
    private double quantityRate = 1;
    //средства защиты
    private double protectRate = 1;
    //мультидрайв
    private double level_driverRate = 1;
    //аренда
    private double rent_taxiRate = 1;
    //франшиза угон
    private double condition_franchiseRate = 1;
    //франшиза динамическая
    private double no_condition_franchiseRate = 1;
    //доп виды
    private double additional_typesRate = 1;
    //скидка
    private double bonusRate = 1;
    //убытки
    private double manusRate = 1;
    //вариант оплаты
    private double paymentRate = 1.05;
    //рекламная акция
    private double adsRate;
    private double salonRate;
    private double employeeRate;
    private double carsRate;
    private double rezCalc;

    public double getRezCalc() {
        rezCalc = Double.parseDouble(String.valueOf(new BigDecimal(rezCalc).setScale(2, RoundingMode.HALF_UP).doubleValue()));
        return rezCalc;
    }

    public double getVehicleRate() {
        return vehicleRate;
    }

    public double getTerritoryRate() {
        return territoryRate;
    }

    public double getQuantityRate() {
        return quantityRate;
    }

    public double getProtectRate() {
        return protectRate;
    }

    public double getLevel_driverRate() {
        return level_driverRate;
    }

    public double getRent_taxiRate() {
        return rent_taxiRate;
    }

    public double getCondition_franchiseRate() {
        return condition_franchiseRate;
    }

    public double getNo_condition_franchiseRate() {
        return no_condition_franchiseRate;
    }

    public double getAdditional_typesRate() {
        return additional_typesRate;
    }

    public double getBonusRate() {
        return bonusRate;
    }

    public double getManusRate() {
        return manusRate;
    }

    public double getPaymentRate() {
        return paymentRate;
    }

    public double getAdsRate() {
        return adsRate;
    }

    public double getSalonRate() {
        return salonRate;
    }

    public double getEmployeeRate() {
        return employeeRate;
    }

    public double getCarsRate() {
        return carsRate;
    }

    public double getOptionRate() {
        return optionRate;
    }


    public void calculateRate(String vehicle, String option, String territory, String quantity, String protect, String level_driver, String rent_taxi,
                              String condition_franchise, String no_condition_franchise, String additional_types,
                              String bonus, String manus, String payment, String ads, String salon, String employee, String car) {

        switch (vehicle) {
            case "Легковой автомобиль":
                vehicleRate = 3.7;
                break;
            case "Автобусы, грузовые авто":
                vehicleRate = 2.2;
                break;
            case "Тракторы, прицепы":
                vehicleRate = 1.5;
                break;
        }
        switch (option) {
            case "Вариант 1 - без учета износа, Б - Стандарт":
                optionRate = 1.2;
                break;
            case "Вариант 1 - без учета износа, В - Премиум":
                optionRate = 1.4;
                break;
            case "Вариант 2 - с учетом износа":
                optionRate = 1;
                break;
        }

        switch (territory) {
            case "Все страны мира (за исключением регионов военных действий)":
                territoryRate = 1;
                break;
            case "Республика Беларусь":
                territoryRate = 0.9;
                break;
        }

        switch (quantity) {
            case "2 единицы":
                quantityRate = 0.95;
                break;
            case "3-5 единиц":
                quantityRate = 0.9;
                break;
                default:quantityRate = 1;

        }
        switch (protect) {
            case "механическое":
                protectRate = 0.95;
                break;
            case "электронное":
                protectRate = 0.9;
                break;
            case "оба вида защиты":
                protectRate = 0.85;
                break;
            case "противоугонная маркировка":
                protectRate = 0.8;
                break;
            case "спутник":
                protectRate = 0.75;
                break;
        }
        switch (level_driver) {
            case "стаж более 5 лет":
                level_driverRate = 0.95;
                break;
            case "стаж более 10 лет":
                level_driverRate = 0.9;
                break;
                default:level_driverRate = 1;
        }
        switch (rent_taxi) {
            case "договор аредны (прокат)":
                rent_taxiRate = 1.20;
                break;
            case "такси, обучение вождению":
                rent_taxiRate = 1.80;
                break;
        }
        switch (condition_franchise) {
            case "5%":
                condition_franchiseRate = 0.95;
                break;
            case "10%":
                condition_franchiseRate = 0.9;
                break;
            case "20%":
                condition_franchiseRate = 0.85;
                break;
            case "30%":
                condition_franchiseRate = 0.8;
                break;
        }
        switch (no_condition_franchise) {
            case "200$":
                no_condition_franchiseRate = 0.9;
                break;
            case "300$":
                no_condition_franchiseRate = 0.85;
                break;
            case "400$":
                no_condition_franchiseRate = 0.8;
                break;
            case "500$":
                no_condition_franchiseRate = 0.75;
                break;
            case " ":
                no_condition_franchiseRate = 1;
                break;
        }
        switch (additional_types) {
            case "1 вид":
                additional_typesRate = 0.95;
                break;
            case "2 вида":
                additional_typesRate = 0.9;
                break;
            case "3 вида":
                additional_typesRate = 0.85;
                break;
        }
        switch (bonus) {
            case "A1":
                bonusRate = 0.9;
                break;
            case "A2":
                bonusRate = 0.8;
                break;
            case "A3":
                bonusRate = 0.7;
                break;
            case "A4":
                bonusRate = 0.6;
                break;
            case "A5":
                bonusRate = 0.5;
                break;
        }
        switch (manus) {
            case "B1":
                manusRate = 1.1;
                break;
            case "B2":
                manusRate = 1.3;
                break;
            case "B3":
                manusRate = 1.5;
                break;
            case "B4":
                manusRate = 1.7;
                break;
            case "B5":
                manusRate = 2;
                break;
        }
        switch (payment) {
            case "в два срока":
                paymentRate = 1;
                break;
            case "единовременно":
                paymentRate = 0.9;
                break;
            default:
                paymentRate = 1.05;
        }
        if (ads.equals("No")) {
            adsRate = 1;
        } else {
            adsRate = 0.9;
        }
        if (salon.equals("No")) {
            salonRate = 1;
        } else {
            salonRate = 0.9;
        }
        if (employee.equals("No")) {
            employeeRate = 1;
        } else {
            employeeRate = 0.9;
        }
        if (car.equals("No")) {
            carsRate = 1;
        } else {
            carsRate = 0.9;
        }
        rezCalc = 1.1*1.1*vehicleRate * optionRate * territoryRate * quantityRate * protectRate * level_driverRate * rent_taxiRate *
                condition_franchiseRate * no_condition_franchiseRate * additional_typesRate * bonusRate * manusRate * paymentRate * adsRate *
                salonRate * employeeRate * carsRate;
    }
}
