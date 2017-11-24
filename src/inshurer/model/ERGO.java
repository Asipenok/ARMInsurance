package inshurer.model;

public class ERGO {
    //тип авто
    private double vehicleRate = 1;
    //территория действия
    private double territoryRate = 0.9;
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


    private String rezCalc;


    public String calculateRate(String vehicle, String territory, String quantity, String protect, String level_driver, String rent_taxi,
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
        switch (territory) {
            case "Все страны мира (за исключением регионов военных действий":
                territoryRate = 1;
                break;
        }
        switch (quantity) {
            case "2 единицы":
                quantityRate = 0.95;
                break;
            case "3-5 единиц":
                quantityRate = 0.9;
                break;
        }
        switch (protect) {
            case "механическое":
                protectRate = 0.95;
                break;
            case "электронное":
                protectRate = 0.9;
                break;
            case "оба вида зщиты":
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
        }
        if (ads.equals("No")) {
            adsRate = 0.9;
        } else {
            adsRate = 1;
        }
        if (salon.equals("No")) {
            salonRate = 0.9;
        } else {
            salonRate = 1;
        }
        if (employee.equals("No")) {
            employeeRate = 0.9;
        } else {
            employeeRate = 1;
        }
        if (car.equals("No")) {
            carsRate = 0.9;
        } else {
            carsRate = 1;
        }
        rezCalc = String.valueOf(vehicleRate * territoryRate * quantityRate * protectRate * level_driverRate * rent_taxiRate *
                condition_franchiseRate * no_condition_franchiseRate * additional_typesRate * bonusRate * manusRate * paymentRate * adsRate *
                salonRate * employeeRate * carsRate);

      return rezCalc;
    }




}
